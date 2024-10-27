package firespread.modid.mixin;

import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

//@Mixin(DefaultBiomeFeatures.class)
@Mixin(MiscPlacedFeatures.class)
public class RemoveSurfaceLavaMixin {
    /*/
    @ModifyConstant(method = "bootstrap", constant = @Constant(intValue = 200))
    private static int modifyConstantA(int value) {
        return Integer.MAX_VALUE;
    }

    @ModifyConstant(method = "bootstrap", constant = @Constant(intValue = 20))
    private static int modifyConstantB(int value) {
        return Integer.MAX_VALUE;
    }
    */
    /*/ one day ill fix this to work
    @ModifyArg(method = "bootstrap", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/feature/PlacedFeatures;register(Lnet/minecraft/registry/Registerable;Lnet/minecraft/registry/RegistryKey;Lnet/minecraft/registry/entry/RegistryEntry;[Lnet/minecraft/world/gen/placementmodifier/PlacementModifier;)V"), index = 3)
    private static PlacementModifier[] modifyArgA(PlacementModifier[] modifiers) {
        return PlacementModifier[]{RarityFilterPlacementModifier.of(Integer.MAX_VALUE), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of()};
    }
    */
}
