package com.example.addon.hud;

import meteordevelopment.meteorclient.utils.render.color.Color;
import com.example.addon.hud;
import meteordevelopment.meteorclient.settings.BoolSetting;
import meteordevelopment.meteorclient.settings.DoubleSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.hud.HudElement;
import meteordevelopment.meteorclient.systems.hud.HudElementInfo;
import meteordevelopment.meteorclient.systems.hud.HudRenderer;
import net.minecraft.util.Identifier;

public class HudExample extends HudElement {

	public static final HudElementInfo<HudExample> INFO = new HudElementInfo<>(Addon.HUD_GROUP, "MeteorPlusLogo", "Shows the MeteorPlus logo in the HUD.", HudExample::new);
	private final SettingGroup sgGeneral = settings.getDefaultGroup();

	private final Setting<Double> scale = sgGeneral.add(new DoubleSetting.Builder()
		.name("scale")
		.description("The scale of the logo.")
		.defaultValue(3)
		.min(0.1)
		.onChanged((size) -> calculateSize())
		.sliderRange(0.1, 10)
		.build()
	);

	private final Setting<Boolean> invert = sgGeneral.add(new BoolSetting.Builder()
		.name("invert")
		.description("Invert the logo.")
		.defaultValue(false)
		.build()
	);

	private final Identifier TEXTURE = new Identifier("plus", "logo.png");


	public HudExample() {
		super(INFO);
		calculateSize();
	}

	public void calculateSize() {
		box.setSize(64 * scale.get(), 50 * scale.get());
	}

	@Override
	public void render(HudRenderer renderer) {
		int w = getWidth();
		int h = getHeight();

		if (!invert.get()) {
			renderer.texture(TEXTURE, box.getRenderX(), box.getRenderY(), w, h, Color.WHITE);
		} else {
			renderer.texture(TEXTURE, box.getRenderX()+w, box.getRenderY(), -w, h, Color.WHITE);
		}
	}
}
