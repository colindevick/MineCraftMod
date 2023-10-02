package com.devickcolin.speedboat.entity.custom.items;

import java.util.List;
import java.util.function.Predicate;

import com.devickcolin.speedboat.entity.client.Speed_BoatEntity;

import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class EthonalItem extends PotionItem {
	private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);
	private float fuelContained = 25;

	public EthonalItem(Properties p_42979_) {
		super(p_42979_);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unlikely-arg-type")
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand playerHand) {
		ItemStack itemstack = player.getItemInHand(playerHand);
		HitResult hitresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
		if (hitresult.getType() == HitResult.Type.ENTITY) {
			Vec3 vec3 = player.getViewVector(1.0F);
			List<Entity> list = level.getEntities(player,
					player.getBoundingBox().expandTowards(vec3.scale(5.0D)).inflate(1.0D), ENTITY_PREDICATE);
			if (!list.isEmpty()) {
				if (list.contains(Speed_BoatEntity.class)) {
					int boatLocation = list.indexOf(Speed_BoatEntity.class);
					Speed_BoatEntity boat = (Speed_BoatEntity) list.get(boatLocation);

					fuelContained = boat.addFuel(fuelContained);
					if (fuelContained <= 0) {
						itemstack.shrink(1);
						player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
					} else {
						setDamage(itemstack, Mth.floor(fuelContained));
					}
					return InteractionResultHolder.pass(itemstack);
				}
			}

		}
		super.use(level, player, playerHand);
		return InteractionResultHolder.pass(itemstack);
	}
	// @ TODO Add function that fills the speedboat.

}
