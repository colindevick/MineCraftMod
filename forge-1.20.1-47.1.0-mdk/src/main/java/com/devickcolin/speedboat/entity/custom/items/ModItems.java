package com.devickcolin.speedboat.entity.custom.items;

import com.devickcolin.speedboat.entity.custom.ModHandler;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
		public static final DeferredRegister<Item> ITEMS =
				DeferredRegister.create(ForgeRegistries.ITEMS, ModHandler.MOD_ID);
		
		public static final RegistryObject<Item> SPEEDBOAT = ITEMS.register("speedboat",
				() -> new Item(new Item.Properties().stacksTo(1)));
		
		public static final RegistryObject<PotionItem> ETHONAL =ITEMS.register("ethonal",
				() -> new PotionItem(new PotionItem.Properties().stacksTo(1)));
		
		public static void register(IEventBus eventBus) {
			ITEMS.register(eventBus);
		}
}
