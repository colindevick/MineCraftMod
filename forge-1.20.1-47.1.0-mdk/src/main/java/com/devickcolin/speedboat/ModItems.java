package com.devickcolin.speedboat;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
		public static final DeferredRegister<Item> ITEMS =
				DeferredRegister.create(ForgeRegistries.ITEMS, ModHandler.MOD_ID);
		
		public static final RegistryObject<Item> SPEEDBOAT = ITEMS.register("speedboat",
				() -> new Item(new Item.Properties().stacksTo(1)));
		
		public static void register(IEventBus eventBus) {
			ITEMS.register(eventBus);
		}
}
