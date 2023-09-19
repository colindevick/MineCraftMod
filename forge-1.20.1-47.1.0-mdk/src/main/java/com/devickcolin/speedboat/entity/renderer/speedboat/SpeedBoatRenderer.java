package com.devickcolin.speedboat.entity.renderer.speedboat;
import com.devickcolin.speedboat.entity.client.SpeedBoatModel;
import com.devickcolin.speedboat.entity.custom.Speed_BoatEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SpeedBoatRenderer extends GeoEntityRenderer<Speed_BoatEntity> {

	
	public SpeedBoatRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new SpeedBoatModel());
		// TODO Auto-generated constructor stub
	}
	
	public void render(Speed_BoatEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
		this.animatable = entity;

		defaultRender(poseStack, entity, bufferSource, null, null, entityYaw, partialTick, packedLight);
	}

	
	 
	



	

	


}
