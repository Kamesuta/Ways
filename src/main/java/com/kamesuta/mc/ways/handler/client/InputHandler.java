package com.kamesuta.mc.ways.handler.client;

import org.lwjgl.input.Keyboard;

import com.kamesuta.mc.ways.client.gui.load.GuiWayLoad;
import com.kamesuta.mc.ways.client.gui.save.GuiWaySave;
import com.kamesuta.mc.ways.proxy.ClientProxy;
import com.kamesuta.mc.ways.reference.Names;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.settings.KeyBinding;

public class InputHandler {
	public static final InputHandler INSTANCE = new InputHandler();

    private static final KeyBinding KEY_BINDING_LOAD = new KeyBinding(Names.Keys.LOAD, Keyboard.KEY_DIVIDE, Names.Keys.CATEGORY);
    private static final KeyBinding KEY_BINDING_SAVE = new KeyBinding(Names.Keys.SAVE, Keyboard.KEY_MULTIPLY, Names.Keys.CATEGORY);
	private static final KeyBinding KEY_BINDING_CONTROL = new KeyBinding(Names.Keys.CONTROL, Keyboard.KEY_SUBTRACT, Names.Keys.CATEGORY);

	public static final KeyBinding[] KEY_BINDINGS = new KeyBinding[] { KEY_BINDING_LOAD, KEY_BINDING_SAVE, KEY_BINDING_CONTROL };

	private InputHandler() {
	}

	@SubscribeEvent
	public void onKeyInput(InputEvent event) {
		if (ClientProxy.MINECRAFT.currentScreen == null) {
			if (KEY_BINDING_LOAD.isPressed()) {
				ClientProxy.MINECRAFT.displayGuiScreen(new GuiWayLoad(ClientProxy.MINECRAFT.currentScreen));
			}
			if (KEY_BINDING_SAVE.isPressed()) {
				ClientProxy.MINECRAFT.displayGuiScreen(new GuiWaySave(ClientProxy.MINECRAFT.currentScreen));
			}
		}
	}
}
