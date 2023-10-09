package com.devickcolin.speedboat.custom.core;

import com.devickcolin.speedboat.custom.blocks.CornCrop;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocksBootStrap {
	
	public static final DeferredRegister<Block> BLOCKS =
			DeferredRegister.create(ForgeRegistries.BLOCKS, ModHandler.MOD_ID);
	

	public static final RegistryObject<Block> CORN_CROP = BLOCKS.register("corn_crop",
			() -> new CornCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));
	
	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
	}
	

}
