package com.kamesuta.mc.ways.world.way;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.kamesuta.mc.ways.reference.Names;
import com.kamesuta.mc.ways.world.storage.Way;

public abstract class WayFormat {
	public static final Map<String, WayFormat> FORMATS = new HashMap<String, WayFormat>();
	public static final String FORMAT_DEFAULT;
	public static String format;

	public abstract Way readFromFile(File file);

	public Way readFromFile(File directory, String filename) {
		return readFromFile(new File(directory, filename));
	}

	public abstract boolean writeToFile(File file, Way way);

	public boolean writeToFile(File directory, String filename, Way way) {
		return writeToFile(new File(directory, filename), way);
	}

	public String getSuffix()
	{
		return Names.Formats.SUFFIX;
	}

	public static WayFormat getFormat(String type)
	{
		return FORMATS.get(type);
	}

	public static WayFormat getFormat()
	{
		return getFormat(format);
	}

	static {
		FORMATS.put(Names.Formats.FORMAT_JSON, new WayJson());
		FORMATS.put(Names.Formats.FORMAT_SERIALIZE, new WaySerialize());

		FORMAT_DEFAULT = Names.Formats.FORMAT_JSON;
		format = FORMAT_DEFAULT;
	}
}
