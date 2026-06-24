package dev.lutsu.hidehotbar.mixin;

import dev.lutsu.hidehotbar.config.ToolBarConfig;
import net.minecraft.entity.player.PlayerInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {
    @Shadow private int selectedSlot;

    @Unique
    private int lastSlot = -1;

    @Inject(method = "updateItems", at = @At("HEAD"))
    private void onUpdateItems(CallbackInfo ci) {
        if (this.selectedSlot != this.lastSlot) {
            if (this.lastSlot != -1
                    && ToolBarConfig.INSTANCE.unhide_on_slot_change
                    && ToolBarConfig.INSTANCE.hid) {
                ToolBarConfig.INSTANCE.hid = false;
            }
            this.lastSlot = this.selectedSlot;
        }
    }
}
