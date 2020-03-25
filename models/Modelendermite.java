// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public static class Modelendermite extends ModelBase {
	private final ModelRenderer body1;
	private final ModelRenderer body2;
	private final ModelRenderer body3;
	private final ModelRenderer body4;

	public Modelendermite() {
		textureWidth = 64;
		textureHeight = 32;

		body1 = new ModelRenderer(this);
		body1.setRotationPoint(0.0F, 21.0F, -3.5F);
		body1.cubeList.add(new ModelBox(body1, 0, 0, -2.0F, 0.0F, -1.0F, 4, 3, 2, 0.0F, true));

		body2 = new ModelRenderer(this);
		body2.setRotationPoint(0.0F, 20.0F, 0.0F);
		body2.cubeList.add(new ModelBox(body2, 0, 5, -3.0F, 0.0F, -2.5F, 6, 4, 5, 0.0F, true));

		body3 = new ModelRenderer(this);
		body3.setRotationPoint(0.0F, 21.0F, 3.0F);
		body3.cubeList.add(new ModelBox(body3, 0, 14, -1.5F, 0.0F, -0.5F, 3, 3, 1, 0.0F, true));

		body4 = new ModelRenderer(this);
		body4.setRotationPoint(0.0F, 22.0F, 4.0F);
		body4.cubeList.add(new ModelBox(body4, 0, 18, -0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F, true));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		body1.render(f5);
		body2.render(f5);
		body3.render(f5);
		body4.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.body1.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.body1.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.body2.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.body2.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.body3.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.body3.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.body4.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.body4.rotateAngleX = f4 / (180F / (float) Math.PI);
	}
}