package com.devickcolin.speedboat.custom.core;

import net.minecraft.world.food.FoodProperties;

public class ModFoodBootStrap {

	public static final FoodProperties COOKED_CORN = new FoodProperties.Builder().nutrition(3).saturationMod(.5f).build();
	
	public static final FoodProperties UNCOOKED_CORN = new FoodProperties.Builder().nutrition(1).saturationMod(.5f).build();

}
