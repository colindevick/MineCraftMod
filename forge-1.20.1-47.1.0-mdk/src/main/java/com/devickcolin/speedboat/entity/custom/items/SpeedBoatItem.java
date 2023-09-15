package com.devickcolin.speedboat.entity.custom.items;


import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;

public class SpeedBoatItem extends Item {

	public SpeedBoatItem(Properties p_41383_) {
		super(p_41383_);
		// TODO Auto-generated constructor stub
	}
	
	//@ TODO add interaction that places boat when using on water.
	 public InteractionResult useOn(UseOnContext p_220235_) {
	      Level level = p_220235_.getLevel();
	      BlockPos blockpos = p_220235_.getClickedPos();
	      Player player = p_220235_.getPlayer();
	      ItemStack itemstack = p_220235_.getItemInHand();
	      BlockState blockstate = level.getBlockState(blockpos);
	      Entity entity = 
	      BlockType 
	      if (if blockstate == level.getF ) {
	         level.playSound((Player)null, blockpos, SoundEvents.GENERIC_SPLASH, SoundSource.BLOCKS, 1.0F, 1.0F);
	         player.setItemInHand(p_220235_.getHand(), ItemUtils.createFilledResult(itemstack, player, new ItemStack(Items.GLASS_BOTTLE)));
	         player.awardStat(Stats.ITEM_USED.get(itemstack.getItem()));
	         if (!level.isClientSide) {
	            ServerLevel serverlevel = (ServerLevel)level;

	            for(int i = 0; i < 5; ++i) {
	               serverlevel.sendParticles(ParticleTypes.SPLASH, (double)blockpos.getX() + level.random.nextDouble(), (double)(blockpos.getY() + 1), (double)blockpos.getZ() + level.random.nextDouble(), 1, 0.0D, 0.0D, 0.0D, 1.0D);
	            }
	         }

	         level.playSound((Player)null, blockpos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
	         level.gameEvent((Entity)null, GameEvent.FLUID_PLACE, blockpos);
	         level.setBlockAndUpdate(blockpos, Blocks.MUD.defaultBlockState());
	         return InteractionResult.sidedSuccess(level.isClientSide);
	      } else {
	         return InteractionResult.PASS;
	      }
	   }
	
	

	

	
	
}
