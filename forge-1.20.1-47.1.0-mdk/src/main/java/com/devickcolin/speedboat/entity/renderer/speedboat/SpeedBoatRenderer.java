package com.devickcolin.speedboat.entity.renderer.speedboat;
import org.joml.Matrix4f;

import com.devickcolin.speedboat.entity.client.SpeedBoatModel;
import com.devickcolin.speedboat.entity.custom.Speed_BoatEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.util.RenderUtils;

public class SpeedBoatRenderer extends GeoEntityRenderer<Speed_BoatEntity> {

	
	public SpeedBoatRenderer(Context renderManager) {
		super(renderManager, new SpeedBoatModel());
		// TODO Auto-generated constructor stub
		
	}
	
	
	public void renderRecursively(PoseStack poseStack, Speed_BoatEntity animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight,
			  int packedOverlay, float red, float green, float blue, float alpha) {
		if (bone.getName().equals("Culling") ) {
			buffer = bufferSource.getBuffer(RenderType.waterMask());
			
			System.out.println("Culled");
		}
		
		super.renderRecursively(poseStack, animatable, bone, renderType, bufferSource, buffer, false, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
		
}
	
}
	

	
	 
	



	

	



