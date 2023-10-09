package com.devickcolin.speedboat.custom.items;

import com.devickcolin.speedboat.custom.core.ModHandler;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;



	public class ModPotions {
		public final static DeferredRegister<Potion> POTIONS =
				DeferredRegister.create(ForgeRegistries.POTIONS, ModHandler.MOD_ID);
		
		public final static RegistryObject<Potion> ETHONAL = POTIONS.register("ethonal",
				() -> new Potion("Ethonal", new MobEffectInstance(MobEffects.POISON, 9600)));
		
		public static void register(IEventBus eventBus) {
			POTIONS.register(eventBus);
		}
		
		
}
		
		


