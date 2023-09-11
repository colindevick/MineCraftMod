package com.devickcolin.speedboat.entity.custom;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModEntityCreator {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
			DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ModHandler.MOD_ID);
	
	public static final RegistryObject<EntityType<Speed_BoatEntity>> SPEED_BOATS = 
			ENTITY_TYPES.register("speedboat", () -> EntityType.Builder.of(Speed_BoatEntity::new , MobCategory.MISC)
					.sized(1.5f, 2.5f)
					.build(new ResourceLocation(ModHandler.MOD_ID,"speedboat").toString()));
	public static void register(IEventBus eventBus) {
		ENTITY_TYPES.register(eventBus);
	}

}
