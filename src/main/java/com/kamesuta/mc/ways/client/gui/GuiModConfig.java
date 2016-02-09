package com.kamesuta.mc.ways.client.gui;

import java.util.ArrayList;
import java.util.List;

import com.kamesuta.mc.ways.handler.ConfigurationHandler;
import com.kamesuta.mc.ways.reference.Names;
import com.kamesuta.mc.ways.reference.Reference;

import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;

public class GuiModConfig extends GuiConfig {
    public GuiModConfig(GuiScreen guiScreen) {
        super(guiScreen, getConfigElements(), Reference.MODID, false, false, GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()));
    }

    @SuppressWarnings("rawtypes")
	private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> elements = new ArrayList<IConfigElement>();
        for (String name : ConfigurationHandler.configuration.getCategoryNames()) {
            final ConfigCategory category = ConfigurationHandler.configuration.getCategory(name).setLanguageKey(Names.Config.LANG_PREFIX + ".category." + name);
            if (category.parent == null) {
                elements.add(new ConfigElement(category));
            }
        }
        return elements;
    }
}
