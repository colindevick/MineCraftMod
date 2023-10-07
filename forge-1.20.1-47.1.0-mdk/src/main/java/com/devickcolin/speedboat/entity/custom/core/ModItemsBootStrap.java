package com.devickcolin.speedboat.entity.custom.core;

import com.devickcolin.speedboat.entity.custom.items.SpeedBoatItem;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.PotionItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemsBootStrap {
		public static final DeferredRegister<Item> ITEMS =
				DeferredRegister.create(ForgeRegistries.ITEMS, ModHandler.MOD_ID);
		
		public static final RegistryObject<SpeedBoatItem> SPEEDBOAT = ITEMS.register("speedboat",
				() -> new SpeedBoatItem (false, null, new SpeedBoatItem.Properties().stacksTo(1)));
		
		public static final RegistryObject<PotionItem> ETHANOL = ITEMS.register("ethanol",
				() -> new PotionItem(new PotionItem.Properties().stacksTo(1).durability(25)));
		
		//TODO add yeast item picture
		public static final RegistryObject<Item> YEAST = ITEMS.register("yeast",
				() -> new Item(new Item.Properties().stacksTo(64)));
		
		public static final RegistryObject<Item> CORN_COB = ITEMS.register("uncooked_corn",
				() -> new Item(new Item.Properties().stacksTo(64).food(ModFoodBootStrap.UNCOOKED_CORN)));
		
		public static final RegistryObject<Item> CORN_COB_ROASTED = ITEMS.register("cooked_corn",
				() -> new Item(new Item.Properties().stacksTo(64).food(ModFoodBootStrap.COOKED_CORN)));
		
		public static final RegistryObject<Item> CORN_KERNALS = ITEMS.register("kernals_corn",
				() -> new Item(new Item.Properties().stacksTo(64)));
		
		
		public static void register(IEventBus eventBus) {
			ITEMS.register(eventBus);
		}
}
