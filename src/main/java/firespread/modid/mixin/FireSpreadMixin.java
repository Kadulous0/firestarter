package firespread.modid.mixin;

import firespread.modid.Firespread;
import net.minecraft.block.FireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FireBlock.class)
public abstract class FireSpreadMixin {
    // burn chance configuration
    @Inject(method = "getBurnChance(Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;)I", at = @At("RETURN"), cancellable = true)
    private void mixinBurnChance(CallbackInfoReturnable<Integer> cir) {
        float multiplier = Firespread.config.burnChanceMultiplier();
        cir.setReturnValue(Math.round(((float) cir.getReturnValue()) * multiplier));
    }

    // spread chance configuration
    @Inject(method = "getSpreadChance", at = @At("RETURN"), cancellable = true)
    private void mixinSpreadChance(CallbackInfoReturnable<Integer> cir) {
        float multiplier = Firespread.config.spreadChanceMultiplier();
        cir.setReturnValue(Math.round(((float) cir.getReturnValue()) * multiplier));
    }

    // makes the age treated as fakeAge if fireSlows is false, and keeps original if true
    @ModifyVariable(method = "scheduledTick", at = @At("STORE"), ordinal = 0)
    private int mixinScheduledTickAge(int i) {
        return Firespread.config.fireSlows() ? i : Firespread.config.fakeAge();
    }

    // these next 5 methods are pure mixin crack
    // if im being honest... i really don't know
    // spread distance configs
    @ModifyVariable(method = "scheduledTick", at = @At("STORE"), ordinal = 3)
    private int mixinScheduledTickA(int value) {
        return -1*Firespread.config.spreadDistanceHorizontal();
    }

    @ModifyVariable(method = "scheduledTick", at = @At("STORE"), ordinal = 4)
    private int mixinScheduledTickB(int value) {
        return -1*Firespread.config.spreadDistanceHorizontal();
    }

    @ModifyVariable(method = "scheduledTick", at = @At("STORE"), ordinal = 5)
    private int mixinScheduledTickC(int value) {
        return -1*Firespread.config.spreadDistanceDown();
    }

    @ModifyConstant(method = "scheduledTick",
                    slice = @Slice(
                            from = @At(value = "CONSTANT", ordinal = 3, args = "intValue=300"),
                            to = @At(value = "CONSTANT", ordinal = 0, args = "intValue=100")
                    ),
                    constant = @Constant(intValue = 1))
    private int mixinScheduledTickD(int value) {
        return Firespread.config.spreadDistanceHorizontal();
    }

    @ModifyConstant(method = "scheduledTick",
            slice = @Slice(
                    from = @At(value = "CONSTANT", ordinal = 3, args = "intValue=300"),
                    to = @At(value = "CONSTANT", ordinal = 0, args = "intValue=100")
            ),
            constant = @Constant(intValue = 4))
    private int mixinScheduledTickE(int value) {
        return Firespread.config.spreadDistanceUp();
    }
}
