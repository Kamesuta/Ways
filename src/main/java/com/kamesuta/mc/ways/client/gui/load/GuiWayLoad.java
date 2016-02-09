package com.kamesuta.mc.ways.client.gui.load;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.Sys;

import com.kamesuta.mc.ways.FileFilterWays;
import com.kamesuta.mc.ways.Ways;
import com.kamesuta.mc.ways.client.gui.GuiScreenBase;
import com.kamesuta.mc.ways.handler.ConfigurationHandler;
import com.kamesuta.mc.ways.reference.Names;
import com.kamesuta.mc.ways.reference.Reference;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GuiWayLoad extends GuiScreenBase {
	private static final FileFilterWays FILE_FILTER_FOLDER = new FileFilterWays(true);
	private static final FileFilterWays FILE_FILTER_SCHEMATIC = new FileFilterWays(false);

	private GuiWayLoadSlot guiWayLoadSlot;

	private GuiButton btnOpenDir = null;
	private GuiButton btnDone = null;

	private final String strTitle = I18n.format(Names.Gui.Load.TITLE);
	private final String strFolderInfo = I18n.format(Names.Gui.Load.FOLDER_INFO);

	protected File currentDirectory = ConfigurationHandler.waysDirectory;
	protected final List<GuiWayEntry> wayFiles = new ArrayList<GuiWayEntry>();

	public GuiWayLoad(GuiScreen guiScreen) {
		super(guiScreen);
	}

	@Override
	public void initGui() {
		int id = 0;

		this.btnOpenDir = new GuiButton(id++, this.width / 2 - 154, this.height - 36, 150, 20,
				I18n.format(Names.Gui.Load.OPEN_FOLDER));
		this.buttonList.add(this.btnOpenDir);

		this.btnDone = new GuiButton(id++, this.width / 2 + 4, this.height - 36, 150, 20, I18n.format(Names.Gui.DONE));
		this.buttonList.add(this.btnDone);

		this.guiWayLoadSlot = new GuiWayLoadSlot(this);

		reloadWays();
	}

	@Override
	protected void actionPerformed(GuiButton guiButton) {
		if (guiButton.enabled) {
			if (guiButton.id == this.btnOpenDir.id) {
				boolean retry = false;

				try {
					Desktop.getDesktop().browse(ConfigurationHandler.waysDirectory.toURI());
				} catch (IOException e) {
					retry = true;
				}

				if (retry) {
					Reference.logger.info("Opening via Sys class!");
					Sys.openURL("file://" + ConfigurationHandler.waysDirectory.getAbsolutePath());
				}
			} else if (guiButton.id == this.btnDone.id) {
				if (Ways.proxy.isLoadEnabled) {
					loadWay();
				}
				this.mc.displayGuiScreen(this.parentScreen);
			} else {
				this.guiWayLoadSlot.actionPerformed(guiButton);
			}
		}
	}

	@Override
	public void drawScreen(int x, int y, float partialTicks) {
		this.guiWayLoadSlot.drawScreen(x, y, partialTicks);

		drawCenteredString(this.fontRendererObj, this.strTitle, this.width / 2, 4, 0x00FFFFFF);
		drawCenteredString(this.fontRendererObj, this.strFolderInfo, this.width / 2 - 78, this.height - 12, 0x00808080);

		super.drawScreen(x, y, partialTicks);
	}

	@Override
	public void onGuiClosed() {
		// loadWay();
	}

	protected void changeDirectory(String directory) {
		this.currentDirectory = new File(this.currentDirectory, directory);

		reloadWays();
	}

	protected void reloadWays() {
		String name = null;
		Item item = null;

		this.wayFiles.clear();

		try {
			if (!this.currentDirectory.getCanonicalPath()
					.equals(ConfigurationHandler.waysDirectory.getCanonicalPath())) {
				this.wayFiles.add(new GuiWayEntry("..", Items.lava_bucket, 0, true));
			}
		} catch (IOException e) {
			Reference.logger.error("Failed to add GuiWayEntry!", e);
		}

		File[] filesFolders = this.currentDirectory.listFiles(FILE_FILTER_FOLDER);
		if (filesFolders == null) {
			Reference.logger.error("listFiles returned null (directory: {})!", this.currentDirectory);
		} else {
			for (File file : filesFolders) {
				if (file == null) {
					continue;
				}

				name = file.getName();

				File[] files = file.listFiles();
				item = (files == null || files.length == 0) ? Items.bucket : Items.water_bucket;

				this.wayFiles.add(new GuiWayEntry(name, item, 0, file.isDirectory()));
			}
		}

		File[] filesWays = this.currentDirectory.listFiles(FILE_FILTER_SCHEMATIC);
		if (filesWays == null || filesWays.length == 0) {
			this.wayFiles.add(new GuiWayEntry(I18n.format(Names.Gui.Load.NO_WAYS), Blocks.dirt, 0, false));
		} else {
			for (File file : filesWays) {
				name = file.getName();

				this.wayFiles.add(new GuiWayEntry(name, new ItemStack(
						Blocks.grass)/* WayUtil.getIconFromFile(file) */, file.isDirectory()));
			}
		}
	}

	private void loadWay() {
		int selectedIndex = this.guiWayLoadSlot.selectedIndex;

		try {
			if (selectedIndex >= 0 && selectedIndex < this.wayFiles.size()) {
				GuiWayEntry wayEntry = this.wayFiles.get(selectedIndex);
				if (Ways.proxy.loadWay(null, this.currentDirectory, wayEntry.getName())) {
					/*
					 * Way way = ClientProxy.way; if (way != null) {
					 * ClientProxy.moveWayToPlayer(way); }
					 */
				}
			}
		} catch (Exception e) {
			Reference.logger.error("Failed to load way!", e);
		}
	}
}
