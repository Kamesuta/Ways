package com.kamesuta.mc.ways.reference;

public final class Names {
	public static final class Config {
		public static final String SUFFIX = ".config";

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

		public static final String WAY_OFFSET = "wayOffset";
		public static final String WAY_OFFSET_DESC = "Offset of way line height.";

		public static final String LANG_PREFIX = Reference.MODID.toLowerCase() + SUFFIX;
	}

	public static final class Gui {
		public static final class Load {
			public static final String TITLE = "ways.gui.title";
			public static final String FOLDER_INFO = "ways.gui.folderInfo";
			public static final String OPEN_FOLDER = "ways.gui.openFolder";
			public static final String NO_WAYS = "ways.gui.noway";
		}

		public static final class Save {
			public static final String NEW = "ways.gui.new";
			public static final String LOAD = "ways.gui.load";
			public static final String SAVE = "ways.gui.save";
			public static final String SAVE_SELECTION = "ways.gui.saveselection";
			public static final String OFFSET_INC = "ways.gui.offsetInc";
			public static final String OFFSET_DEC = "ways.gui.offsetDec";
		}

		public static final String ON = "ways.gui.on";
		public static final String OFF = "ways.gui.off";
		public static final String DONE = "ways.gui.done";

	}

	public static final class ModId {
		public static final String MINECRAFT = "minecraft";
	}

	public static final class Keys {
		public static final String CATEGORY = "ways.key.category";
		public static final String LOAD = "ways.key.load";
		public static final String SAVE = "ways.key.save";
		public static final String CONTROL = "ways.key.control";
	}

	public static final class Formats {
		public static final String SUFFIX = ".way";
		public static final String SUFFIX_JSON = ".json";
		public static final String FORMAT_JSON = "Json";
		public static final String FORMAT_SERIALIZE = "Serialize";
		public static final String SAVE_SUCCESS = "Successfully saving.";
		public static final String SAVE_ERROR = "Save error has occured.";
		public static final String LOAD_SUCCESS = "Successfully loading.";
		public static final String LOAD_ERROR = "Load error has occured.";
	}
}
