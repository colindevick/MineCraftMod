package com.devickcolin.speedboat.custom.core;

import org.slf4j.Logger;

import com.devickcolin.speedboat.custom.items.ModPotions;
import com.devickcolin.speedboat.entity.renderer.speedboat.SpeedBoatRenderer;
import com.mojang.logging.LogUtils;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ModHandler.MOD_ID)
public class ModHandler {
	// Define mod id in a common place for everything to reference
	public static final String MOD_ID = "speedboat";
	// Directly reference a slf4j logger
	private static final Logger LOGGER = LogUtils.getLogger();
	// Create a Deferred Register to hold CreativeModeTabs which will all be
	// registered under the "examplemod" namespace

	// Creates a new Block with the id "examplemod:example_block", combining the
	// namespace and path
	// public static final RegistryObject<Block> EXAMPLE_BLOCK =
	// BLOCKS.register("example_block", () -> new
	// Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
	// Creates a new BlockItem with the id "examplemod:example_block", combining the
	// namespace and path
	// public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM =
	// ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new
	// Item.Properties()));

	// Creates a new food item with the id "examplemod:example_id", nutrition 1 and
	// saturation 2

	// Creates a creative tab with the id "examplemod:example_tab" for the example
	// item, that is placed after the combat tab
	/*
	 * public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB =
	 * CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
	 * .withTabsBefore(CreativeModeTabs.COMBAT) .icon(() ->
	 * EXAMPLE_ITEM.get().getDefaultInstance()) .displayItems((parameters, output)
	 * -> { output.accept(EXAMPLE_ITEM.get()); // Add the example item to the tab.
	 * For your own tabs, this method is preferred over the event }).build());
	 */

	public ModHandler() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::commonSetup);
		ModBlocksBootStrap.register(modEventBus);
		ModItemsBootStrap.register(modEventBus);
		ModPotions.register(modEventBus);
		
		MinecraftForge.EVENT_BUS.register(this);
		modEventBus.addListener(this::addCreative);
		
		GeckoLib.initialize();
		

		
		ModEntityCreator.register(modEventBus);
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
	}

	private void addCreative(BuildCreativeModeTabContentsEvent event)  {
		ResourceKey<CreativeModeTab> tab = event.getTabKey();
		
			switch (CreativeTab.keyGetter(tab)) {
			case TOOLS_AND_UTILITIES:
				event.accept(ModItemsBootStrap.SPEEDBOAT);
				break;
			case FOOD_AND_DRINKS:
				event.accept(ModItemsBootStrap.CORN_COB);
				event.accept(ModItemsBootStrap.CORN_COB_ROASTED);
				event.accept(ModItemsBootStrap.CORN_KERNALS);
			default:
				break;
					
			}
		
	}
	// You can use SubscribeEvent and let the Event Bus discover methods to call

	// You can use EventBusSubscriber to automatically register all static methods
	// in the class annotated with @SubscribeEvent
	@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientModEvents {
		@SubscribeEvent
		public static void onClientSetup(FMLClientSetupEvent event) {
			EntityRenderers.register(ModEntityCreator.SPEED_BOATS.get(), SpeedBoatRenderer::new);
			RecipeBootStrap.strapRecipes();
		}
	}
}
