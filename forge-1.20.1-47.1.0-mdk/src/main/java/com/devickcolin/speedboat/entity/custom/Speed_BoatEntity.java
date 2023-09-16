package com.devickcolin.speedboat.entity.custom;


import net.minecraft.util.ByIdMap;
import java.util.function.IntFunction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.util.GeckoLibUtil;
public class Speed_BoatEntity extends Boat implements GeoEntity{
	
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

	  public Speed_BoatEntity(EntityType<? extends Boat>p_38290_, Level p_38291_) {
	      super(p_38290_, p_38291_);
	      this.blocksBuilding = true;
	   }

	  public Speed_BoatEntity(Level p_38293_, double p_38294_, double p_38295_, double p_38296_) {
	      this(EntityType.BOAT, p_38293_);
	      this.setPos(p_38294_, p_38295_, p_38296_);
	      this.xo = p_38294_;
	      this.yo = p_38295_;
	      this.zo = p_38296_;
	   }

	

	
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerControllers(ControllerRegistrar arg0) {
		// TODO Auto-generated method stub
		
	}
	
}


