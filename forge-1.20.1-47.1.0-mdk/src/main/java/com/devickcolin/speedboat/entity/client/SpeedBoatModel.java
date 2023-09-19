package com.devickcolin.speedboat.entity.client;

import org.joml.Quaternionf;

import com.devickcolin.speedboat.entity.custom.ModHandler;
import com.devickcolin.speedboat.entity.custom.Speed_BoatEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Axis;

import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.WaterPatchModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.Boat;
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
	
	





}
