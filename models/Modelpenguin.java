// Paste this code into your mod.
// Make sure to generate all required imports

public static class Modelpenguin extends EntityModel {
	private final RendererModel RightLeg;
	private final RendererModel LeftLeg;
	private final RendererModel Body;
	private final RendererModel Head;
	private final RendererModel RightArm;
	private final RendererModel LeftArm;

	public Modelpenguin() {
		textureWidth = 64;
		textureHeight = 64;

		RightLeg = new RendererModel(this);
		RightLeg.setRotationPoint(0.0F, 24.0F, 0.0F);
		RightLeg.cubeList.add(new ModelBox(RightLeg, 10, 38, -4.0F, -1.0F, -3.0F, 3, 1, 5, 0.0F, false));
		RightLeg.cubeList.add(new ModelBox(RightLeg, 36, 45, -4.0F, -2.0F, 0.0F, 3, 1, 2, 0.0F, false));

		LeftLeg = new RendererModel(this);
		LeftLeg.setRotationPoint(0.0F, 24.0F, 0.0F);
		LeftLeg.cubeList.add(new ModelBox(LeftLeg, 10, 32, 1.0F, -1.0F, -3.0F, 3, 1, 5, 0.0F, false));
		LeftLeg.cubeList.add(new ModelBox(LeftLeg, 26, 45, 1.0F, -2.0F, 0.0F, 3, 1, 2, 0.0F, false));

		Body = new RendererModel(this);
		Body.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body.cubeList.add(new ModelBox(Body, 0, 0, -5.0F, -15.0F, -2.0F, 10, 13, 6, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 0, 19, -4.0F, -16.0F, -2.0F, 8, 1, 6, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 0, 45, -2.0F, -17.0F, -1.0F, 4, 1, 4, 0.0F, false));

		Head = new RendererModel(this);
		Head.setRotationPoint(0.0F, 24.0F, 0.0F);
		Head.cubeList.add(new ModelBox(Head, 32, 0, -3.0F, -23.0F, -2.0F, 6, 6, 6, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 16, 45, -1.0F, -19.0F, -5.0F, 2, 1, 3, 0.0F, false));

		RightArm = new RendererModel(this);
		RightArm.setRotationPoint(-2.0F, 21.0F, 0.0F);
		setRotationAngle(RightArm, 0.0F, 0.0F, 0.1745F);
		RightArm.cubeList.add(new ModelBox(RightArm, 0, 32, -5.0F, -10.0F, -1.0F, 1, 9, 4, 0.0F, false));
		RightArm.cubeList.add(new ModelBox(RightArm, 6, 50, -5.0F, -1.0F, 0.0F, 1, 1, 2, 0.0F, false));

		LeftArm = new RendererModel(this);
		LeftArm.setRotationPoint(3.0F, 21.0F, 0.0F);
		setRotationAngle(LeftArm, 0.0F, 0.0F, -0.1745F);
		LeftArm.cubeList.add(new ModelBox(LeftArm, 28, 19, 3.0F, -10.0F, -1.0F, 1, 9, 4, 0.0F, false));
		LeftArm.cubeList.add(new ModelBox(LeftArm, 0, 50, 3.0F, -1.0F, 0.0F, 1, 1, 2, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		RightLeg.render(f5);
		LeftLeg.render(f5);
		Body.render(f5);
		Head.render(f5);
		RightArm.render(f5);
		LeftArm.render(f5);
	}
	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
