package com.devickcolin.speedboat.entity.custom.core;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

public class RecipeBootStrap{
	private static Item YEAST;
	private static Item ETHANOL;
	private static Item CORN_KERNALS;
	
	private static ItemStack WATER;
	
	public static void strapRecipes() {
		ingrediantList();
		BrewingRecipeRegistry.addRecipe(Ingredient.of(WATER), Ingredient.of(Items.WHEAT), new ItemStack(YEAST));
		BrewingRecipeRegistry.addRecipe(Ingredient.of(YEAST), Ingredient.of(CORN_KERNALS), new ItemStack(ETHANOL));
	}
	
	
	private static void ingrediantList() {
		YEAST = ModItemsBootStrap.YEAST.get();
		ETHANOL = ModItemsBootStrap.ETHANOL.get();
		CORN_KERNALS = ModItemsBootStrap.CORN_KERNALS.get();
		WATER = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER);
		
	}
	
	
	

}
