package firespread.modid.mixin;

import firespread.modid.Firespread;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FireBlock;
import net.minecraft.block.TntBlock;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FireBlock.class)
public abstract class FireSpreadMixin {
    @Shadow protected abstract int getBurnChance(WorldView world, BlockPos pos);

    @Shadow protected abstract void trySpreadingFire(World world, BlockPos pos, int spreadFactor, Random random, int currentAge);

    @Shadow protected abstract boolean isFlammable(BlockState state);

    @Shadow protected abstract boolean areBlocksAroundFlammable(BlockView world, BlockPos pos);

    @Shadow protected abstract boolean isRainingAround(World world, BlockPos pos);

    @Shadow protected abstract BlockState getStateWithAge(WorldAccess world, BlockPos pos, int age);

    @Shadow protected abstract int getSpreadChance(BlockState state);

    @Shadow
    protected static int getFireTickDelay(Random random) { return 30 + random.nextInt(10); }

    @Inject(method = "getBurnChance(Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;)I", at = @At("RETURN"), cancellable = true)
    private void mixinBurnChance(CallbackInfoReturnable<Integer> cir) {
        float multiplier = Firespread.config.burnChanceMultiplier();
        cir.setReturnValue(Math.round(((float) cir.getReturnValue()) * multiplier));
    }

    @Inject(method = "getSpreadChance", at = @At("RETURN"), cancellable = true)
    private void mixinFireSpread(CallbackInfoReturnable<Integer> cir) {
        float multiplier = Firespread.config.fireSpreadMultiplier();
        cir.setReturnValue(Math.round(((float) cir.getReturnValue()) * multiplier));
    }

    // REPLACE
    @Inject(method = "scheduledTick", at = @At("HEAD"), cancellable = true)
    private void modifyScheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        world.scheduleBlockTick(pos, (Block) (Object) this, getFireTickDelay(world.random));
        if (world.getGameRules().getBoolean(GameRules.DO_FIRE_TICK)) {
            if (!state.canPlaceAt(world, pos)) {
                world.removeBlock(pos, false);
            }

            BlockState blockState = world.getBlockState(pos.down());
            boolean bl = blockState.isIn(world.getDimension().infiniburn());
            int i = state.get(FireBlock.AGE);
            if (!bl && world.isRaining() && this.isRainingAround(world, pos) && random.nextFloat() < 0.1F) {
                world.removeBlock(pos, false);
            } else {
                int j = Math.min(15, i + random.nextInt(3) / 2);
                if (i != j) {
                    state = state.with(FireBlock.AGE, j);
                    world.setBlockState(pos, state, 4);
                }

                if (!bl) {
                    if (!this.areBlocksAroundFlammable(world, pos)) {
                        BlockPos blockPos = pos.down();
                        if (!world.getBlockState(blockPos).isSideSolidFullSquare(world, blockPos, Direction.UP) || i > 3) {
                            world.removeBlock(pos, false);
                        }
                        return;
                    }

                    if (random.nextInt(4) == 0 && !this.isFlammable(world.getBlockState(pos.down()))) {
                        world.removeBlock(pos, false);
                        return;
                    }
                }

                boolean bl2 = world.getBiome(pos).isIn(BiomeTags.INCREASED_FIRE_BURNOUT);
                int k = bl2 ? -50 : 0;
                this.trySpreadingFire(world, pos.east(), 300 + k, random, i);
                this.trySpreadingFire(world, pos.west(), 300 + k, random, i);
                this.trySpreadingFire(world, pos.down(), 250 + k, random, i);
                this.trySpreadingFire(world, pos.up(), 250 + k, random, i);
                this.trySpreadingFire(world, pos.north(), 300 + k, random, i);
                this.trySpreadingFire(world, pos.south(), 300 + k, random, i);
                BlockPos.Mutable mutable = new BlockPos.Mutable();

                int horz = Firespread.config.spreadDistanceHorizontal();
                int up = Firespread.config.spreadDistanceVerticalUp();
                int down = Firespread.config.spreadDistanceVerticalDown();
                for (int l = -horz; l <= horz; ++l) {
                    for (int m = -horz; m <= horz; ++m) {
                        for (int n = -down; n <= up; ++n) {
                            if (l != 0 || n != 0 || m != 0) {
                                int o = 100;
                                if (n > 1) {
                                    o += (n - 1) * 100;
                                }

                                mutable.set(pos, l, n, m);
                                int p = this.getBurnChance(world, mutable);
                                if (p > 0) {
                                    int q = (p + 40 + world.getDifficulty().getId() * 7) / 30;
                                    if (bl2) {
                                        q /= 2;
                                    }

                                    if (q > 0 && random.nextInt(o) <= q && (!world.isRaining() || !this.isRainingAround(world, mutable) || (this.isRainingAround(world, mutable) && random.nextFloat() < 0.05F))) {
                                        int r = Math.min(15, i + random.nextInt(5) / 4);
                                        world.setBlockState(mutable, this.getStateWithAge(world, mutable, r), 3);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        ci.cancel(); // Cancel the original method execution
    }

    /*/
    private void trySpreadingFire(World world, BlockPos pos, int spreadFactor, Random random, int currentAge) {
        ++ currentAge = 0
        int i = this.getSpreadChance(world.getBlockState(pos));
        if (random.nextInt(spreadFactor) < i) {
            BlockState blockState = world.getBlockState(pos);
            if (random.nextInt(currentAge + 10) < 5 && !world.hasRain(pos)) {
                int j = Math.min(currentAge + random.nextInt(5) / 4, 15);
                world.setBlockState(pos, this.getStateWithAge(world, pos, j), 3);
            } else {
                world.removeBlock(pos, false);
            }

            Block block = blockState.getBlock();
            if (block instanceof TntBlock) {
                TntBlock.primeTnt(world, pos);
            }
        }

    }
     */

    // REPLACE
    @Inject(method = "trySpreadingFire", at = @At("HEAD"), cancellable = true)
    private void modifiedTrySpreadingFire(World world, BlockPos pos, int spreadFactor, Random random, int currentAge, CallbackInfo ci) {
        // Custom spread logic
        if (random.nextInt(spreadFactor) < this.getSpreadChance(world.getBlockState(pos))) {
            BlockState blockState = world.getBlockState(pos);
            if (random.nextInt(10) < 5 && (!world.hasRain(pos) || (world.hasRain(pos) && random.nextFloat() < 0.1F))) {
                int j = Math.min(currentAge + random.nextInt(5) / 4, 15);
                world.setBlockState(pos, this.getStateWithAge(world, pos, j), 3);
            } else {
                world.removeBlock(pos, false);
            }

            Block block = blockState.getBlock();
            if (block instanceof TntBlock) {
                TntBlock.primeTnt(world, pos);
            }
        }
        ci.cancel(); // Cancel the original method execution
    }
}
