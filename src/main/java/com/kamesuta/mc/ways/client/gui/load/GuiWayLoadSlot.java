package com.kamesuta.mc.ways.client.gui.load;

import com.kamesuta.mc.ways.client.gui.GuiHelper;
import com.kamesuta.mc.ways.proxy.ClientProxy;

import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;

public class GuiWayLoadSlot extends GuiSlot {
    private final GuiWayLoad guiWayLoad;

    protected int selectedIndex = -1;

    public GuiWayLoadSlot(GuiWayLoad guiWayLoad) {
        super(ClientProxy.MINECRAFT, guiWayLoad.width, guiWayLoad.height, 16, guiWayLoad.height - 40, 24);
        this.guiWayLoad = guiWayLoad;
    }

    @Override
    protected int getSize() {
        return this.guiWayLoad.wayFiles.size();
    }

    @Override
    protected void elementClicked(int index, boolean par2, int par3, int par4) {
        GuiWayEntry way = this.guiWayLoad.wayFiles.get(index);
        if (way.isDirectory()) {
            this.guiWayLoad.changeDirectory(way.getName());
            this.selectedIndex = -1;
        } else {
            this.selectedIndex = index;
        }
    }

    @Override
    protected boolean isSelected(int index) {
        return index == this.selectedIndex;
    }

    @Override
    protected void drawBackground() {
    }

    @Override
    protected void drawContainerBackground(Tessellator tessellator) {
    }

    @Override
    protected void drawSlot(int index, int x, int y, int par4, Tessellator tessellator, int par6, int par7) {
        if (index < 0 || index >= this.guiWayLoad.wayFiles.size()) {
            return;
        }

        GuiWayEntry way = this.guiWayLoad.wayFiles.get(index);
        String wayName = way.getName();

        if (way.isDirectory()) {
            wayName += "/";
        } else {
            wayName = wayName.replaceAll("(?i)\\.way$", "");
        }

        GuiHelper.drawItemStack(ClientProxy.MINECRAFT.renderEngine, ClientProxy.MINECRAFT.fontRenderer, x, y, way.getItemStack());

        this.guiWayLoad.drawString(ClientProxy.MINECRAFT.fontRenderer, wayName, x + 24, y + 6, 0x00FFFFFF);
    }
}
