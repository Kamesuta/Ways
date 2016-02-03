package com.kamesuta.mc.ways.reference;

public final class Names {
	public static final class Config {
		public static final class Category {
			public static final String DEBUG = "debug";
			public static final String RENDER = "render";
			public static final String GENERAL = "general";
			public static final String SERVER = "server";
		}

		public static final String WAYS_DIRECTORY = "waysDirectory";
		public static final String WAYS_DIRECTORY_DESC = "Ways directory.";

		public static final String PLAYER_QUOTA_KILOBYTES = "playerQuotaKilobytes";
		public static final String PLAYER_QUOTA_KILOBYTES_DESC = "Amount of storage provided per-player for ways on the server.";

		public static final String LANG_PREFIX = Reference.MODID.toLowerCase() + ".config";
	}

	public static final class Gui {
		public static final class Control {
		}
	}

	public static final class ModId {
		public static final String MINECRAFT = "minecraft";
	}

	public static final class Keys {
		public static final String CATEGORY = "ways.key.category";
		public static final String CONTROL = "ways.key.control";
	}
}
