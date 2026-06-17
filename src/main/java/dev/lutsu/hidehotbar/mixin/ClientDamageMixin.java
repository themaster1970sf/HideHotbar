package dev.lutsu.hidehotbar.mixin;

import dev.lutsu.hidehotbar.HideHotbarModClient;
import dev.lutsu.hidehotbar.config.ToolBarConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class ClientDamageMixin {

    @Inject(method = "onDamaged", at = @At("TAIL"))
    private void onDamaged(DamageSource source, CallbackInfo ci) {

        LivingEntity self = (LivingEntity)(Object)this;

        MinecraftClient client = MinecraftClient.getInstance();

        if (self != client.player) return;

        if (HideHotbarModClient.isHotbarHidden() && ToolBarConfig.unhide_on_damage) {
            HideHotbarModClient.toggleHotbar();
        }
    }
}