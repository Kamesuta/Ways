package com.kamesuta.mc.ways.reference;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Reference {
    public static final String MODID = "Ways";
    public static final String NAME = "Ways";
    public static final String VERSION = "${version}";
    public static final String FORGE = "${forgeversion}";
    public static final String MINECRAFT = "${mcversion}";
    public static final String PROXY_SERVER = "com.kamesuta.mc.ways.proxy.ServerProxy";
    public static final String PROXY_CLIENT = "com.kamesuta.mc.ways.proxy.ClientProxy";
    //public static final String GUI_FACTORY = "com.github.lunatrius.schematica.client.gui.GuiFactory";

    public static Logger logger = LogManager.getLogger(Reference.MODID);
}
