package com.devickcolin.speedboat.entity.custom;

import java.util.List;

import com.devickcolin.speedboat.entity.custom.items.ModItems;

import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.util.GeckoLibUtil;

public class Speed_BoatEntity extends Boat implements GeoEntity, net.minecraftforge.common.extensions.IForgeBoat {
	private static final EntityDataAccessor<Integer> DATA_ID_BUBBLE_TIME = SynchedEntityData
			.defineId(Speed_BoatEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> DATA_ID_HURT = SynchedEntityData.defineId(Speed_BoatEntity.class,
			EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> DATA_ID_HURTDIR = SynchedEntityData
			.defineId(Speed_BoatEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Float> DATA_ID_DAMAGE = SynchedEntityData.defineId(Speed_BoatEntity.class,
			EntityDataSerializers.FLOAT);
	private static final boolean bubbleColumnDirectionIsDown = false;
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	private float invFriction;
	private Speed_BoatEntity.Status oldStatus;
	private double waterLevel;
	private double lastYd;
	private Speed_BoatEntity.Status status;
	private float landFriction;
	private float deltaRotation;
	private boolean inputLeft;
	private boolean inputRight;
	private boolean inputUp;
	private boolean inputDown;
	private float outOfControlTicks;
	private int lerpSteps;
	private float bubbleMultiplier;
	private float bubbleAngleO;
	private float bubbleAngle;
	private boolean isAboveBubbleColumn;
	private double lerpXRot;
	private double lerpYRot;
	private double lerpZ;
	private double lerpY;
	private double lerpX;

	public Speed_BoatEntity(EntityType<? extends Speed_BoatEntity> speedBoats, Level p_38291_) {
		super(speedBoats, p_38291_);
		this.blocksBuilding = true;
	}

	public Speed_BoatEntity(Level p_38293_, double p_38294_, double p_38295_, double p_38296_) {
		this(ModEntityCreator.SPEED_BOATS.get(), p_38293_);
		this.setPos(p_38294_, p_38295_, p_38296_);
		this.xo = p_38294_;
		this.yo = p_38295_;
		this.zo = p_38296_;
	}
	
	public float getBubbleAngle(float p_38353_) {
	      return Mth.lerp(p_38353_, this.bubbleAngleO, this.bubbleAngle);
	   }

	public AnimatableInstanceCache getAnimatableInstanceCache() {
		// TODO Auto-generated method stub
		return this.cache;
	}

	@Override
	public void registerControllers(ControllerRegistrar arg0) {
		// TODO Auto-generated method stub

	}
	
	public void lerpTo(double p_38299_, double p_38300_, double p_38301_, float p_38302_, float p_38303_, int p_38304_, boolean p_38305_) {
	      this.lerpX = p_38299_;
	      this.lerpY = p_38300_;
	      this.lerpZ = p_38301_;
	      this.lerpYRot = (double)p_38302_;
	      this.lerpXRot = (double)p_38303_;
	      this.lerpSteps = 10;
	   }

	public double getPassengersRidingOffset() {
		return -0.1D;
	}

	public void setHurtDir(int p_38363_) {
		this.entityData.set(DATA_ID_HURTDIR, p_38363_);
	}

	public int getHurtDir() {
		return this.entityData.get(DATA_ID_HURTDIR);
	}

	@Override
	public Item getDropItem() {
		return ModItems.SPEEDBOAT.get();
	}

	public void setDamage(float p_38312_) {
		this.entityData.set(DATA_ID_DAMAGE, p_38312_);
	}

	protected void addAdditionalSaveData(CompoundTag p_38359_) {

	}

	public float getDamage() {
		return this.entityData.get(DATA_ID_DAMAGE);
	}

	public void setHurtTime(int p_38355_) {
		this.entityData.set(DATA_ID_HURT, p_38355_);
	}

	public int getHurtTime() {
		return this.entityData.get(DATA_ID_HURT);
	}

	protected void addPassenger(Entity passenger) {
		super.addPassenger(passenger);
		if (this.isControlledByLocalInstance() && this.lerpSteps > 0) {
			this.lerpSteps = 0;
			this.absMoveTo(this.lerpX, this.lerpY, this.lerpZ, (float) this.lerpYRot, (float) this.lerpXRot);
		}
	}
	protected Vec3 getRelativePortalPosition(Direction.Axis p_38335_, BlockUtil.FoundRectangle p_38336_) {
	      return LivingEntity.resetForwardDirectionOfRelativePortalPosition(super.getRelativePortalPosition(p_38335_, p_38336_));
	   }
	 protected void positionRider(Entity p_289552_, Entity.MoveFunction p_289571_) {
	      if (this.hasPassenger(p_289552_)) {
	         float f = this.getSinglePassengerXOffset();
	         float f1 = (float)((this.isRemoved() ? (double)0.01F : this.getPassengersRidingOffset()) + p_289552_.getMyRidingOffset());
	         if (this.getPassengers().size() > 1) {
	            int i = this.getPassengers().indexOf(p_289552_);
	            if (i == 0) {
	               f = 0.2F;
	            } else {
	               f = -0.6F;
	            }

	            if (p_289552_ instanceof Animal) {
	               f += 0.2F;
	            }
	         }

	         Vec3 vec3 = (new Vec3((double)f, 0.0D, 0.0D)).yRot(-this.getYRot() * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
	         p_289571_.accept(p_289552_, this.getX() + vec3.x, this.getY() + (double)f1, this.getZ() + vec3.z);
	         p_289552_.setYRot(p_289552_.getYRot() + this.deltaRotation);
	         p_289552_.setYHeadRot(p_289552_.getYHeadRot() + this.deltaRotation);
	         this.clampRotation(p_289552_);
	         if (p_289552_ instanceof Animal && this.getPassengers().size() == this.getMaxPassengers()) {
	            int j = p_289552_.getId() % 2 == 0 ? 90 : 270;
	            p_289552_.setYBodyRot(((Animal)p_289552_).yBodyRot + (float)j);
	            p_289552_.setYHeadRot(p_289552_.getYHeadRot() + (float)j);
	         }

	      }
	   }

	protected boolean canAddPassenger(Entity p_38390_) {
		return this.getPassengers().size() < this.getMaxPassengers() && !this.canBoatInFluid(this.getEyeInFluidType());
	}

	@SuppressWarnings("resource")
	public void tick() {
		this.oldStatus = this.status;
		this.status = this.getStatus();
		if (this.status != Speed_BoatEntity.Status.UNDER_WATER
				&& this.status != Speed_BoatEntity.Status.UNDER_FLOWING_WATER) {
			this.outOfControlTicks = 0.0F;
		} else {
			++this.outOfControlTicks;
		}

		if (!this.level().isClientSide && this.outOfControlTicks >= 60.0F) {
			this.ejectPassengers();
		}

		if (this.getHurtTime() > 0) {
			this.setHurtTime(this.getHurtTime() - 1);
		}

		if (this.getDamage() > 0.0F) {
			this.setDamage(this.getDamage() - 1.0F);
		}

		this.baseTick();
		this.tickLerp();
		if (this.isControlledByLocalInstance()) {

			this.floatBoat();
			if (this.level().isClientSide) {
				this.controlBoat();

			}

			this.move(MoverType.SELF, this.getDeltaMovement());
		} else {
			this.setDeltaMovement(Vec3.ZERO);
		}

		this.tickBubbleColumn();

		this.checkInsideBlocks();
		List<Entity> list = this.level().getEntities(this,
				this.getBoundingBox().inflate((double) 0.2F, (double) -0.01F, (double) 0.2F),
				EntitySelector.pushableBy(this));
		if (!list.isEmpty()) {
			boolean flag = !this.level().isClientSide && !(this.getControllingPassenger() instanceof Player);

			for (int j = 0; j < list.size(); ++j) {
				Entity entity = list.get(j);
				if (!entity.hasPassenger(this)) {
					if (flag && this.getPassengers().size() < this.getMaxPassengers() && !entity.isPassenger()
							&& this.hasEnoughSpaceFor(entity) && entity instanceof LivingEntity
							&& !(entity instanceof WaterAnimal) && !(entity instanceof Player)) {
						entity.startRiding(this);
					} else {
						this.push(entity);
					}
				}
			}
		}

	}

	@Override
	protected void defineSynchedData() {
		this.entityData.define(DATA_ID_HURT, 0);
		this.entityData.define(DATA_ID_HURTDIR, 1);
		this.entityData.define(DATA_ID_DAMAGE, 0.0F);
		this.entityData.define(DATA_ID_BUBBLE_TIME, 0);
	}

	protected void destroy(DamageSource p_219862_) {
		this.spawnAtLocation(this.getDropItem());
	}

	
	public boolean isControlledByLocalInstance() {
		return true;
	}
	
	protected void clampRotation(Entity p_38322_) {
	      p_38322_.setYBodyRot(this.getYRot());
	      float f = Mth.wrapDegrees(p_38322_.getYRot() - this.getYRot());
	      float f1 = Mth.clamp(f, -105.0F, 105.0F);
	      p_38322_.yRotO += f1 - f;
	      p_38322_.setYRot(p_38322_.getYRot() + f1 - f);
	      p_38322_.setYHeadRot(p_38322_.getYRot());
	   }

	   public void onPassengerTurned(Entity p_38383_) {
	      this.clampRotation(p_38383_);
	   }
	private void floatBoat() {
		double d0 = (double) -0.04F;
		double d1 = this.isNoGravity() ? 0.0D : (double) -0.04F;
		double d2 = 0.0D;
		this.invFriction = 0.05F;
		if (this.oldStatus == Speed_BoatEntity.Status.IN_AIR && this.status != Speed_BoatEntity.Status.IN_AIR
				&& this.status != Speed_BoatEntity.Status.ON_LAND) {
			this.waterLevel = this.getY(1.0D);
			this.setPos(this.getX(), (double) (this.getWaterLevelAbove() - this.getBbHeight() - 1.0D), this.getZ());
			this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D));
			this.lastYd = 0.0D;
			this.status = Speed_BoatEntity.Status.IN_WATER;
		} else {
			if (this.status == Speed_BoatEntity.Status.IN_WATER) {
				d2 = (this.waterLevel - this.getY()) / (double) this.getBbHeight();
				this.invFriction = 0.9F;
			} else if (this.status == Speed_BoatEntity.Status.UNDER_FLOWING_WATER) {
				d1 = -7.0E-4D;
				this.invFriction = 0.9F;
			} else if (this.status == Speed_BoatEntity.Status.UNDER_WATER) {
				d2 = (double) 0.01F;
				this.invFriction = 0.45F;
			} else if (this.status == Speed_BoatEntity.Status.IN_AIR) {
				this.invFriction = 0.9F;
			} else if (this.status == Speed_BoatEntity.Status.ON_LAND) {
				this.invFriction = this.landFriction;
				if (this.getControllingPassenger() instanceof Player) {
					this.landFriction /= 2.0F;
				}
			}

			Vec3 vec3 = this.getDeltaMovement();
			this.setDeltaMovement(vec3.x * (double) this.invFriction, vec3.y + d1, vec3.z * (double) this.invFriction);
			this.deltaRotation *= this.invFriction;
			if (d2 > 0.0D) {
				Vec3 vec31 = this.getDeltaMovement();
				this.setDeltaMovement(vec31.x, (vec31.y + d2 * 0.06153846016296973D) * 0.75D, vec31.z);
			}
		}

	}
	
	public void setInput(boolean p_38343_, boolean p_38344_, boolean p_38345_, boolean p_38346_) {
	      this.inputLeft = p_38343_;
	      this.inputRight = p_38344_;
	      this.inputUp = p_38345_;
	      this.inputDown = p_38346_;
	   }

	public LivingEntity getControllingPassenger() {
		Entity entity = this.getFirstPassenger();
		LivingEntity livingentity1;
		if (entity instanceof LivingEntity livingentity) {
			livingentity1 = livingentity;
		} else {
			livingentity1 = null;
		}

		return livingentity1;
	}

	public float getWaterLevelAbove() {
		AABB aabb = this.getBoundingBox();
		int i = Mth.floor(aabb.minX);
		int j = Mth.ceil(aabb.maxX);
		int k = Mth.floor(aabb.maxY);
		int l = Mth.ceil(aabb.maxY - this.lastYd);
		int i1 = Mth.floor(aabb.minZ);
		int j1 = Mth.ceil(aabb.maxZ);
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		label39: for (int k1 = k; k1 < l; ++k1) {
			float f = 0.0F;

			for (int l1 = i; l1 < j; ++l1) {
				for (int i2 = i1; i2 < j1; ++i2) {
					blockpos$mutableblockpos.set(l1, k1, i2);
					FluidState fluidstate = this.level().getFluidState(blockpos$mutableblockpos);
					if (this.canBoatInFluid(fluidstate)) {
						f = Math.max(f, fluidstate.getHeight(this.level(), blockpos$mutableblockpos));
					}

					if (f >= 1.0F) {
						continue label39;
					}
				}
			}

			if (f < 1.0F) {
				return (float) blockpos$mutableblockpos.getY() + f;
			}
		}

		return (float) (l + 1);
	}

	private void tickLerp() {
		if (this.isControlledByLocalInstance()) {
			this.lerpSteps = 0;
			this.syncPacketPositionCodec(this.getX(), this.getY(), this.getZ());
		}

		if (this.lerpSteps > 0) {
			double d0 = this.getX() + (this.lerpX - this.getX()) / (double) this.lerpSteps;
			double d1 = this.getY() + (this.lerpY - this.getY()) / (double) this.lerpSteps;
			double d2 = this.getZ() + (this.lerpZ - this.getZ()) / (double) this.lerpSteps;
			double d3 = Mth.wrapDegrees(this.lerpYRot - (double) this.getYRot());
			this.setYRot(this.getYRot() + (float) d3 / (float) this.lerpSteps);
			this.setXRot(this.getXRot() + (float) (this.lerpXRot - (double) this.getXRot()) / (float) this.lerpSteps);
			--this.lerpSteps;
			this.setPos(d0, d1, d2);
			this.setRot(this.getYRot(), this.getXRot());
		}
	}

	private void controlBoat() {
		if (this.isVehicle()) {
			float f = 0.0F;
			if (this.inputLeft) {
				--this.deltaRotation;
			}

			if (this.inputRight) {
				++this.deltaRotation;
			}

			if (this.inputRight != this.inputLeft && !this.inputUp && !this.inputDown) {
				f += 0.005F;
			}

			this.setYRot(this.getYRot() + this.deltaRotation);
			if (this.inputUp) {
				f += 0.075F;
			}

			if (this.inputDown) {
				f -= 0.005F;
			}

			this.setDeltaMovement(
					this.getDeltaMovement().add((double) (Mth.sin(-this.getYRot() * ((float) Math.PI / 180F)) * f),
							0.0D, (double) (Mth.cos(this.getYRot() * ((float) Math.PI / 180F)) * f)));
		}
	}

	private Speed_BoatEntity.Status isUnderwater() {
		AABB aabb = this.getBoundingBox();
		double d0 = aabb.maxY + 0.001D;
		int i = Mth.floor(aabb.minX);
		int j = Mth.ceil(aabb.maxX);
		int k = Mth.floor(aabb.maxY);
		int l = Mth.ceil(d0);
		int i1 = Mth.floor(aabb.minZ);
		int j1 = Mth.ceil(aabb.maxZ);
		boolean flag = false;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for (int k1 = i; k1 < j; ++k1) {
			for (int l1 = k; l1 < l; ++l1) {
				for (int i2 = i1; i2 < j1; ++i2) {
					blockpos$mutableblockpos.set(k1, l1, i2);
					FluidState fluidstate = this.level().getFluidState(blockpos$mutableblockpos);
					if (this.canBoatInFluid(fluidstate) && d0 < (double) ((float) blockpos$mutableblockpos.getY()
							+ fluidstate.getHeight(this.level(), blockpos$mutableblockpos))) {
						if (!fluidstate.isSource()) {
							return Speed_BoatEntity.Status.UNDER_FLOWING_WATER;
						}

						flag = true;
					}
				}
			}
		}

		return flag ? Speed_BoatEntity.Status.UNDER_WATER : null;
	}

	private boolean checkInWater() {
		AABB aabb = this.getBoundingBox();
		int i = Mth.floor(aabb.minX);
		int j = Mth.ceil(aabb.maxX);
		int k = Mth.floor(aabb.minY);
		int l = Mth.ceil(aabb.minY + 0.001D);
		int i1 = Mth.floor(aabb.minZ);
		int j1 = Mth.ceil(aabb.maxZ);
		boolean flag = false;
		this.waterLevel = -Double.MAX_VALUE;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for (int k1 = i; k1 < j; ++k1) {
			for (int l1 = k; l1 < l; ++l1) {
				for (int i2 = i1; i2 < j1; ++i2) {
					blockpos$mutableblockpos.set(k1, l1, i2);
					FluidState fluidstate = this.level().getFluidState(blockpos$mutableblockpos);
					if (this.canBoatInFluid(fluidstate)) {
						float f = (float) l1 + fluidstate.getHeight(this.level(), blockpos$mutableblockpos);
						this.waterLevel = Math.max((double) f, this.waterLevel);
						flag |= aabb.minY < (double) f;
					}
				}
			}
		}

		return flag;
	}

	private Speed_BoatEntity.Status getStatus() {
		Speed_BoatEntity.Status boat$status = this.isUnderwater();
		if (boat$status != null) {
			this.waterLevel = this.getBoundingBox().maxY;
			return boat$status;
		} else if (this.checkInWater()) {
			return Speed_BoatEntity.Status.IN_WATER;
		} else {
			float f = this.getGroundFriction();
			if (f > 0.0F) {
				this.landFriction = f;
				return Speed_BoatEntity.Status.ON_LAND;
			} else {
				return Speed_BoatEntity.Status.IN_AIR;
			}
		}
	}

	private void tickBubbleColumn() {
		if (this.level().isClientSide) {
			int i = this.getBubbleTime();
			if (i > 0) {
				this.bubbleMultiplier += 0.05F;
			} else {
				this.bubbleMultiplier -= 0.1F;
			}

			this.bubbleMultiplier = Mth.clamp(this.bubbleMultiplier, 0.0F, 1.0F);
			this.bubbleAngleO = this.bubbleAngle;
			this.bubbleAngle = 10.0F * (float) Math.sin((double) (0.5F * (float) this.level().getGameTime()))
					* this.bubbleMultiplier;
		} else {
			if (!this.isAboveBubbleColumn) {
				this.setBubbleTime(0);
			}

			int k = this.getBubbleTime();
			if (k > 0) {
				--k;
				this.setBubbleTime(k);
				int j = 60 - k - 1;
				if (j > 0 && k == 0) {
					this.setBubbleTime(0);
					Vec3 vec3 = this.getDeltaMovement();
					if (Speed_BoatEntity.bubbleColumnDirectionIsDown) {
						this.setDeltaMovement(vec3.add(0.0D, -0.7D, 0.0D));
						this.ejectPassengers();
					} else {
						this.setDeltaMovement(vec3.x, this.hasPassenger((p_150274_) -> {
							return p_150274_ instanceof Player;
						}) ? 2.7D : 0.6D, vec3.z);
					}
				}

				this.isAboveBubbleColumn = false;
			}
		}

	}

	private int getBubbleTime() {
		return this.entityData.get(DATA_ID_BUBBLE_TIME);
	}

	private void setBubbleTime(int p_38367_) {
		this.entityData.set(DATA_ID_BUBBLE_TIME, p_38367_);
	}
	
	public static enum Status {
	      IN_WATER,
	      UNDER_WATER,
	      UNDER_FLOWING_WATER,
	      ON_LAND,
	      IN_AIR;
	   }

}
