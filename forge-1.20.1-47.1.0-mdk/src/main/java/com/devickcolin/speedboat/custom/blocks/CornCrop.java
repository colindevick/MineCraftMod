package com.devickcolin.speedboat.custom.blocks;

import com.devickcolin.speedboat.custom.core.ModItemsBootStrap;
import com.devickcolin.speedboat.custom.core.RecipeBootStrap;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.extensions.IForgeBlockState;

public class CornCrop extends CropBlock implements IForgeBlockState {
	private final int MAX_AGE_NOT_MATURE = 4;
	private final int HARVEST_AGE_BOTTOM = 9;
	private final int HARVEST_AGE_TOP = 10;
	

	private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[] 
			{ Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), 
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D), 
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), 
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D), 
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D), };
	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 10);

	public CornCrop(Properties p_52247_) {
		super(p_52247_);

	}

	protected ItemLike getBaseSeedId() {
		return ModItemsBootStrap.CORN_KERNALS.get();
	}

	public VoxelShape getShape(BlockState p_52297_, BlockGetter p_52298_, BlockPos p_52299_,
			CollisionContext p_52300_) {
		return SHAPE_BY_AGE[this.getAge(p_52297_)];
	}

	public int getAge(BlockState pState) {
		return pState.getValue(AGE);
	}

	protected IntegerProperty getAgeProperty() {
		return AGE;
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(AGE);
	}

	public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
		return super.canSurvive(pState, pLevel, pPos) || (pLevel.getBlockState(pPos.below(1)).is(this)
				&& pLevel.getBlockState(pPos.below(1)).getValue(AGE) >= 4)
				|| (pLevel.getBlockState(pPos).getValue(AGE) == 9 && !pLevel.getBlockState(pPos.above(1)).is(this));
	}

	

	public void growCrops(Level level, BlockPos pPos, BlockState pState) {
		if (!(this.getAge(pState) == HARVEST_AGE_BOTTOM || this.getAge(pState) == HARVEST_AGE_TOP)) {
			int nextAge = this.getAge(pState) + this.getBonemealAgeIncrease(level);
			int maxAge = this.getMaxAge();

			if (nextAge >= maxAge) {
				if (!(nextAge == maxAge) && !((nextAge - maxAge) > maxAge)) {
					growBasedoncropstate(level, pPos, pState, nextAge, maxAge);
				} else if ((nextAge - maxAge) > maxAge) {
					makeHarvestable(level, pPos, pState);
				} else {
					growBasedoncropstate(level, pPos, pState, nextAge, maxAge);
				}
			} else {
				level.setBlock(pPos, this.getStateForAge(nextAge), 2);
			}

		}

	}

	// Determines if this is the lower crop block and adjusts
	// age state accordingly
	private void growBasedoncropstate(Level level, BlockPos pPos, BlockState pState, int nextAge, int maxAge) {
		if ((level.getBlockState(pPos.above(1)).is(Blocks.AIR) || level.getBlockState(pPos.above(1)).is(this))
				&& !level.getBlockState((pPos).below(1)).is(this)) {
			if (nextAge != maxAge) {
				level.setBlock(pPos.above(1), this.getStateForAge(nextAge), 2);
			}
			level.setBlock(pPos, this.getStateForAge(maxAge), 2);
		}
		net.minecraftforge.common.ForgeHooks.onCropsGrowPost(level, pPos, pState);
	}

	public boolean isRandomlyTicking(BlockState p_52288_) {
		return !(this.getAge(p_52288_) == HARVEST_AGE_TOP);
	}
	
	//Only the bottom block grows, and passes it to either the growOn
	@SuppressWarnings("deprecation")
	public void randomTick(BlockState pState, ServerLevel level, BlockPos pPos, RandomSource pRan) {
		if (!level.isAreaLoaded(pPos, 1))
			return; // Forge: prevent loading unloaded chunks when checking neighbor's light
		if (level.getRawBrightness(pPos, 0) >= 9) {
			if (level.getBlockState(pPos).isRandomlyTicking() && !level.getBlockState(pPos.below(1)).is(this)) {
				float f = getGrowthSpeed(this, level, pPos);
				if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(level, pPos, pState,
						pRan.nextInt((int) (25.0F / f) + 1) == 0)) {
					int maxAge = this.getMaxAge();
					int nextAge = level.getBlockState(pPos.above(1)).is(this) && this.getAge(pState) + 1 != maxAge
							? level.getBlockState(pPos.above(1)).getValue(AGE) + 1
							: this.getAge(pState) + 1;
					if (nextAge >= maxAge) {
						if (!(nextAge == maxAge) && !((nextAge - maxAge) > maxAge)) {
							growBasedoncropstate(level, pPos, pState, nextAge, maxAge);
						} else if ((nextAge - maxAge) > maxAge) {
							makeHarvestable(level, pPos, pState);
						} else {
							growBasedoncropstate(level, pPos, pState, nextAge, maxAge);
						}
					} else {
						level.setBlock(pPos, this.getStateForAge(nextAge), 2);
						net.minecraftforge.common.ForgeHooks.onCropsGrowPost(level, pPos, pState);
					}
				}
			}
		}
	}

	public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState p_52260_, boolean p_52261_) {
		return !this.isMaxAge(p_52260_)
				|| pLevel.getBlockState(pPos).getValue(AGE) > this.getMaxAge() && !(this.getAge(p_52260_) == HARVEST_AGE_BOTTOM);
	}

	// Final state of the blocks where you can harvest the corn
	private void makeHarvestable(Level level, BlockPos pPos, BlockState pState) {
		level.setBlock(pPos, this.getStateForAge(HARVEST_AGE_BOTTOM), 2);
		level.setBlock(pPos.above(1), this.getStateForAge(HARVEST_AGE_TOP), 2);
	}

	public int getMaxAge() {
		return MAX_AGE_NOT_MATURE;
	}
}
