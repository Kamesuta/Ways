package com.kamesuta.mc.ways.handler.client;

import org.lwjgl.input.Keyboard;

import com.kamesuta.mc.ways.reference.Names;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class InputHandler {
	public static final InputHandler INSTANCE = new InputHandler();

	private static final KeyBinding KEY_BINDING_CONTROL = new KeyBinding(Names.Keys.CONTROL, Keyboard.KEY_SUBTRACT,
			Names.Keys.CATEGORY);

	public static final KeyBinding[] KEY_BINDINGS = new KeyBinding[] { KEY_BINDING_CONTROL };

	private final Minecraft minecraft = Minecraft.getMinecraft();

	private InputHandler() {
	}

	@SubscribeEvent
	public void onKeyInput(InputEvent event) {
		if (this.minecraft.currentScreen == null) {
			if (KEY_BINDING_CONTROL.isPressed()) {
				// this.minecraft.displayGuiScreen(new
				// GuiSchematicControl(this.minecraft.currentScreen));
			}
		}
	}
}
