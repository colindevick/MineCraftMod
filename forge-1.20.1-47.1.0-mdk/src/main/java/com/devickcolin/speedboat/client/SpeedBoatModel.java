package com.devickcolin.speedboat.client;

import com.devickcolin.speedboat.custom.core.ModHandler;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
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
		CoreGeoBone culling = getAnimationProcessor().getBone("Culling");
		CoreGeoBone engine = getAnimationProcessor().getBone("Engine");
		if (animatable.level().isClientSide()) {
			float boatRoatation = animatable.getRotation();
			culling.setRotY(-boatRoatation * Mth.DEG_TO_RAD);
			hull.setRotY(culling.getRotY());
			animatable.setRotation(-hull.getRotY() * Mth.RAD_TO_DEG);
			if (animatable.getLeftinput()) {
				engine.setRotY(-75f * Mth.DEG_TO_RAD);
			}
			
			if (animatable.getRightinput()) {
				engine.setRotY(75f * Mth.DEG_TO_RAD);
			}

			if (!animatable.getRightinput() && !animatable.getLeftinput()) {
				engine.setRotY(0);
			}
			
		}
	}

}
