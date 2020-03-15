// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public static class Modeltakenmodel extends ModelBase {
	private final ModelRenderer head;
	private final ModelRenderer headwear;
	private final ModelRenderer body;
	private final ModelRenderer left_arm;
	private final ModelRenderer right_arm;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;

	public Modeltakenmodel() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, true));

		headwear = new ModelRenderer(this);
		headwear.setRotationPoint(0.0F, 0.0F, 0.0F);
		headwear.cubeList.add(new ModelBox(headwear, 32, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.25F, true));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 16, 16, -4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F, true));

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		setRotationAngle(left_arm, -1.5708F, 0.0F, 0.0F);
		left_arm.cubeList.add(new ModelBox(left_arm, 40, 16, 9.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, true));

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(5.0F, 2.0F, 0.0F);
		setRotationAngle(right_arm, -1.5708F, 0.0F, 0.0F);
		right_arm.cubeList.add(new ModelBox(right_arm, 40, 16, -13.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, true));

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(-1.9F, 12.0F, 0.0F);
		left_leg.cubeList.add(new ModelBox(left_leg, 0, 16, 1.9F, 0.0F, -2.0F, 4, 12, 4, 0.0F, true));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(1.9F, 12.0F, 0.0F);
		right_leg.cubeList.add(new ModelBox(right_leg, 0, 16, -5.9F, 0.0F, -2.0F, 4, 12, 4, 0.0F, true));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		head.render(f5);
		headwear.render(f5);
		body.render(f5);
		left_arm.render(f5);
		right_arm.render(f5);
		left_leg.render(f5);
		right_leg.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.right_arm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.left_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.left_arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.right_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
	}
}