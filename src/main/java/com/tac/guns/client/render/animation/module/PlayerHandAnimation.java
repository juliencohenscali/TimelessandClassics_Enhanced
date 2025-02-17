package com.tac.guns.client.render.animation.module;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tac.guns.client.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.util.HandSide;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PlayerHandAnimation {
    public static void render(GunAnimationController controller, ItemCameraTransforms.TransformType transformType, MatrixStack matrices, IRenderTypeBuffer renderBuffer, int light){
        if(!transformType.isFirstPerson()) return;
        matrices.push();
        {
            controller.applyRightHandTransform(matrices);
            RenderUtil.renderFirstPersonArm(Minecraft.getInstance().player, HandSide.RIGHT, matrices, renderBuffer, light);
        }
        matrices.pop();
        matrices.push();
        {
            controller.applyLeftHandTransform(matrices);
            RenderUtil.renderFirstPersonArm(Minecraft.getInstance().player, HandSide.LEFT, matrices, renderBuffer, light);
        }
        matrices.pop();
    }
}
