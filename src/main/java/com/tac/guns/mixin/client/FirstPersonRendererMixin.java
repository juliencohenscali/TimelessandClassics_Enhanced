package com.tac.guns.mixin.client;

import com.tac.guns.client.render.animation.module.AnimationMeta;
import com.tac.guns.client.render.animation.module.GunAnimationController;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.FirstPersonRenderer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FirstPersonRenderer.class)
public class FirstPersonRendererMixin {
    @Shadow
    private ItemStack itemStackMainHand;
    private ItemStack prevItemStack = ItemStack.EMPTY;
    @Shadow
    private float equippedProgressMainHand;
    @Shadow
    private float prevEquippedProgressMainHand;

    @Inject(method = "tick",at = @At("HEAD"))
    public void applyDrawAndHolster(CallbackInfo ci){
        if(Minecraft.getInstance().player == null) return;
        ItemStack mainHandItemStack = Minecraft.getInstance().player.getHeldItemMainhand();
        GunAnimationController controller = GunAnimationController.fromItem(mainHandItemStack.getItem());
        GunAnimationController controller1 = GunAnimationController.fromItem(this.prevItemStack.getItem());
        if(prevItemStack.isItemEqual(mainHandItemStack)) return;
        //if(isSameWeapon(Minecraft.getInstance().player)) return;
        prevItemStack = mainHandItemStack;
        if(controller1 != null && controller != controller1) {
            controller1.stopAnimation();
        }
        if(controller != null && controller == controller1){
            //Stop the previous item's animation
            AnimationMeta meta = controller.getAnimationFromLabel(GunAnimationController.AnimationLabel.DRAW);
            if(!controller.getPreviousAnimation().equals(meta)) controller.stopAnimation();
            controller.runAnimation(GunAnimationController.AnimationLabel.DRAW);
        }else if(controller != null && controller.getAnimationFromLabel(GunAnimationController.AnimationLabel.DRAW) != null) {
            this.itemStackMainHand = mainHandItemStack;
            controller.runAnimation(GunAnimationController.AnimationLabel.DRAW);
        }
    }
    /*
             */
    @Inject(method = "tick",at = @At("RETURN"))
    public void cancelEquippedProgress(CallbackInfo ci){
        if(Minecraft.getInstance().player == null) return;
        ItemStack mainHandItemStack = Minecraft.getInstance().player.getHeldItemMainhand();
        GunAnimationController controller = GunAnimationController.fromItem(mainHandItemStack.getItem());
        if(controller == null ) return;
        equippedProgressMainHand = 1.0f;
        prevEquippedProgressMainHand = 1.0f;
    }
}
