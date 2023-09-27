package com.devickcolin.speedboat.entity.client;

import java.util.Optional;

import com.devickcolin.speedboat.entity.custom.ModHandler;
import com.devickcolin.speedboat.entity.custom.Speed_BoatEntity;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import software.bernie.example.entity.BikeEntity;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class SpeedBoatModel extends GeoModel<Speed_BoatEntity> {
	
	public ResourceLocation getModelResource(Speed_BoatEntity animatable) {
		// TODO Auto-generated method stub
		return new ResourceLocation(ModHandler.MOD_ID, "geo/speedboat.geo.json");
	}

	public ResourceLocation getTextureResource(Speed_BoatEntity animatable) {
		// TODO Auto-generated method stub
		return new ResourceLocation(ModHandler.MOD_ID, "textures/entity/boat_texture.png");
	}

	public ResourceLocation getAnimationResource(Speed_BoatEntity animatable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public void setCustomAnimations(Speed_BoatEntity animatable, long instanceId,
			AnimationState<Speed_BoatEntity> animationState) {
		super.setCustomAnimations(animatable, instanceId, animationState);
		CoreGeoBone hull = getAnimationProcessor().getBone("Hull");
		Entity entityData = animationState.getData(DataTickets.ENTITY);
		
		if (hull != null) {
			hull.updateRotation(0 ,-Mth.DEG_TO_RAD*entityData.getYRot(), 0);
		}
		

	}

}
