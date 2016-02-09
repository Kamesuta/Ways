package com.kamesuta.mc.ways.client.gui.save;

import com.kamesuta.mc.ways.Ways;
import com.kamesuta.mc.ways.client.gui.GuiScreenBase;
import com.kamesuta.mc.ways.client.gui.load.GuiWayLoad;
import com.kamesuta.mc.ways.handler.ConfigurationHandler;
import com.kamesuta.mc.ways.proxy.ClientProxy;
import com.kamesuta.mc.ways.reference.Names;
import com.kamesuta.mc.ways.world.way.WayFormat;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;

public class GuiWaySave extends GuiScreenBase {
	private int centerX = 0;
	private int centerY = 0;

	private GuiButton btnLoad = null;
	private GuiButton btnEnable = null;
	private GuiButton btnSave = null;
	private GuiTextField tfFilename = null;
	private GuiButton btnNew = null;
	private GuiButton btnMove = null;
	private GuiTextField tfOffset;
	private GuiButton btnOffsetDec;
	private GuiButton btnOffsetInc;

	private String filename = "";

	private final String strSaveSelection = I18n.format(Names.Gui.Save.SAVE_SELECTION);
	private final String strOn = I18n.format(Names.Gui.ON);
	private final String strOff = I18n.format(Names.Gui.OFF);

	public GuiWaySave(GuiScreen guiScreen) {
		super(guiScreen);
	}

	@Override
	public void initGui() {
		this.centerX = this.width / 2;
		this.centerY = this.height / 2;

		this.buttonList.clear();

		int id = 0;

		this.btnLoad = new GuiButton(id++, 10, this.height - 30, 50, 20, I18n.format(Names.Gui.Save.LOAD));
		this.buttonList.add(this.btnLoad);

		this.btnNew = new GuiButton(id++, 70, this.height - 30, 50, 20, I18n.format(Names.Gui.Save.NEW));
		this.buttonList.add(this.btnNew);

		this.btnMove = new GuiButton(id++, 130, this.height - 30, 50, 20, I18n.format("ways.gui.move"));
		this.buttonList.add(this.btnMove);

		this.btnEnable = new GuiButton(id++, this.width - 210, this.height - 30, 50, 20,
				Ways.proxy.isRecording ? this.strOn : this.strOff);
		this.buttonList.add(this.btnEnable);

		this.tfFilename = new GuiTextField(this.fontRendererObj, this.width - 155, this.height - 29, 100, 18);
		this.tfFilename.setMaxStringLength(1024);
		this.tfFilename.setText(this.filename);
		this.textFields.add(this.tfFilename);

		this.btnSave = new GuiButton(id++, this.width - 50, this.height - 30, 40, 20, I18n.format(Names.Gui.Save.SAVE));
		this.buttonList.add(this.btnSave);

		// Offset set
		this.btnOffsetDec = new GuiButton(id++, this.width - 30, this.height - 60, 20, 20, I18n.format(Names.Gui.Save.OFFSET_DEC));
		this.buttonList.add(this.btnOffsetDec);

		this.tfOffset = new GuiTextField(this.fontRendererObj, this.width - 75, this.height - 59, 40, 18);
		this.tfOffset.setText(String.format("%.2f", ConfigurationHandler.wayOffset));
		this.textFields.add(this.tfOffset);

		this.btnOffsetInc = new GuiButton(id++, this.width - 100, this.height - 60, 20, 20, I18n.format(Names.Gui.Save.OFFSET_INC));
		this.buttonList.add(this.btnOffsetInc);
	}

	@Override
	protected void actionPerformed(GuiButton guiButton) {
		if (guiButton.enabled) {
			if (guiButton.id == this.btnEnable.id) {
				Ways.proxy.isRecording = !Ways.proxy.isRecording && Ways.proxy.isSaveEnabled;
				this.btnEnable.displayString = Ways.proxy.isRecording ? this.strOn : this.strOff;
			} else if (guiButton.id == this.btnLoad.id) {
				Ways.proxy.isRecording = false;
				this.btnEnable.displayString = Ways.proxy.isRecording ? this.strOn : this.strOff;
				ClientProxy.MINECRAFT.displayGuiScreen(new GuiWayLoad(ClientProxy.MINECRAFT.currentScreen));
			} else if (guiButton.id == this.btnNew.id) {
				Ways.proxy.isRecording = true;
				this.btnEnable.displayString = Ways.proxy.isRecording ? this.strOn : this.strOff;
				Ways.proxy.newWay();
			} else if (guiButton.id == this.btnOffsetInc.id) {
				ConfigurationHandler.wayOffset += 0.1;
				this.tfOffset.setText(String.format("%.2f", ConfigurationHandler.wayOffset));
			} else if (guiButton.id == this.btnOffsetDec.id) {
				ConfigurationHandler.wayOffset -= 0.1;
				this.tfOffset.setText(String.format("%.2f", ConfigurationHandler.wayOffset));
			} else if (guiButton.id == this.btnSave.id) {
				String path = this.tfFilename.getText() + WayFormat.getFormat().getSuffix();
				if (Ways.proxy.saveWay(ConfigurationHandler.waysDirectory, path)) {
					this.filename = "";
					this.tfFilename.setText(this.filename);
				}
			}
		}
	}

	@Override
	protected void keyTyped(char character, int code) {
		super.keyTyped(character, code);
		this.filename = this.tfFilename.getText();
		try {
			ConfigurationHandler.wayOffset = Double.parseDouble(this.tfOffset.getText());
		} catch (NumberFormatException e) {}
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		// drawDefaultBackground();

		drawString(this.fontRendererObj, this.strSaveSelection, this.width - 205, this.height - 45, 0xFFFFFF);

		super.drawScreen(par1, par2, par3);
	}

	@Override
	public void onGuiClosed() {
		ConfigurationHandler.propWayOffset.set(ConfigurationHandler.wayOffset);
		ConfigurationHandler.loadConfiguration();
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
