package com.devickcolin.speedboat.entity;

// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class SpeedBoat<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "speedboat"), "main");
	private final ModelPart Engine;
	private final ModelPart Hull;
	private final ModelPart WindSheild;

	public SpeedBoat(ModelPart root) {
		this.Engine = root.getChild("Engine");
		this.Hull = root.getChild("Hull");
		this.WindSheild = root.getChild("WindSheild");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Engine = partdefinition.addOrReplaceChild("Engine", CubeListBuilder.create().texOffs(0, 15).addBox(-3.0F, -8.0F, 10.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 23).addBox(-1.0F, -4.0F, 11.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.0F, -8.0F, 5.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Prop = Engine.addOrReplaceChild("Prop", CubeListBuilder.create().texOffs(0, 6).addBox(-1.0F, 2.0F, 11.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(7, 2).addBox(-2.0F, 2.0F, 13.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(7, 0).addBox(1.0F, 3.0F, 13.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition BladeBottom_r1 = Prop.addOrReplaceChild("BladeBottom_r1", CubeListBuilder.create().texOffs(0, 2).addBox(0.5F, -1.5F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.5F, -1.5F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, 12.5F, -1.5708F, 0.0F, 1.5708F));

		PartDefinition Hull = partdefinition.addOrReplaceChild("Hull", CubeListBuilder.create().texOffs(0, 15).addBox(6.0F, -6.0F, -12.0F, 2.0F, 5.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(24, 20).addBox(-8.0F, -6.0F, -12.0F, 2.0F, 5.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Front_r1 = Hull.addOrReplaceChild("Front_r1", CubeListBuilder.create().texOffs(0, 40).addBox(12.0F, -6.0F, -8.0F, 2.0F, 5.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(20, 45).addBox(-10.0F, -5.0F, -8.0F, 2.0F, 4.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition Bottom_r1 = Hull.addOrReplaceChild("Bottom_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, -1.0F, -7.0F, 20.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition WindSheild = partdefinition.addOrReplaceChild("WindSheild", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = WindSheild.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(40, 45).addBox(-6.0F, -3.49F, 1.5F, 12.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -12.5F, -0.5672F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_,
			float p_102623_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renderToBuffer(PoseStack p_103111_, VertexConsumer p_103112_, int p_103113_, int p_103114_,
			float p_103115_, float p_103116_, float p_103117_, float p_103118_) {
		// TODO Auto-generated method stub
		
	}

	
}