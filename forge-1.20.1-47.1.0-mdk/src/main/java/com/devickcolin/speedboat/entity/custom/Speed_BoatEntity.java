package com.devickcolin.speedboat.entity.custom;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.util.GeckoLibUtil;
public class Speed_BoatEntity extends Boat implements GeoEntity{
	
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

	  public Speed_BoatEntity(EntityType<? extends Boat> p_38290_, Level p_38291_) {
	      super(p_38290_, p_38291_);
	      this.blocksBuilding = true;
	   }


	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		// TODO Auto-generated method stub
		return this.cache;
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


