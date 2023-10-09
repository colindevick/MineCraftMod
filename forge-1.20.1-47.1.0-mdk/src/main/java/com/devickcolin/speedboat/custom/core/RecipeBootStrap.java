package com.devickcolin.speedboat.custom.core;
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
	public static final Item YEAST = ModItemsBootStrap.YEAST.get();
	public static final Item ETHANOL = ModItemsBootStrap.ETHANOL.get();
	public static final Item CORN_KERNALS = ModItemsBootStrap.CORN_KERNALS.get();
	public static final ItemStack WATER = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER);
	
	
	public static void strapRecipes() {
		BrewingRecipeRegistry.addRecipe(Ingredient.of(WATER), Ingredient.of(Items.WHEAT), new ItemStack(YEAST));
		BrewingRecipeRegistry.addRecipe(Ingredient.of(YEAST), Ingredient.of(CORN_KERNALS), new ItemStack(ETHANOL));
	}

}
