package com.devickcolin.speedboat.entity.custom;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
public class Speed_BoatEntity extends Boat implements GeoEntity{
	




	public Speed_BoatEntity(EntityType<? extends Boat> p_38290_, Level p_38291_) {
		super(p_38290_, p_38291_);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getTick(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void registerControllers(ControllerRegistrar arg0) {
		// TODO Auto-generated method stub
		
	}


	
		
	}


