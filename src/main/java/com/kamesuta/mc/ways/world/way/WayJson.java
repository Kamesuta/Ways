package com.kamesuta.mc.ways.world.way;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.kamesuta.mc.ways.reference.Names;
import com.kamesuta.mc.ways.reference.Reference;
import com.kamesuta.mc.ways.world.storage.Way;

public class WayJson extends WayFormat {
	@Override
	public Way readFromFile(File file) {
		try {
			JsonReader jsr = new JsonReader(new InputStreamReader(new FileInputStream(file)));
			Way way = new Gson().fromJson(jsr, Way.class);
			Reference.logger.debug(way);
			jsr.close();
			return way;
		} catch (JsonIOException e) {
			Reference.logger.error(Names.Formats.LOAD_ERROR, e);
		} catch (JsonSyntaxException e) {
			Reference.logger.error(Names.Formats.LOAD_ERROR, e);
		} catch (IOException e) {
			Reference.logger.error(Names.Formats.LOAD_ERROR, e);
		}
		return null;
	}

	@Override
	public boolean writeToFile(File file, Way way) {
		try {
			JsonWriter jsw = new JsonWriter(new OutputStreamWriter(new FileOutputStream(file)));
			new Gson().toJson(way, Way.class, jsw);
			jsw.close();
			Reference.logger.info(Names.Formats.SAVE_SUCCESS);
			return true;
		} catch (JsonIOException e) {
			Reference.logger.error(Names.Formats.SAVE_ERROR, e);
		} catch (JsonSyntaxException e) {
			Reference.logger.error(Names.Formats.SAVE_ERROR, e);
		} catch (IOException e) {
			Reference.logger.error(Names.Formats.SAVE_ERROR, e);
		}
		return false;
	}

	@Override
	public String getSuffix()
	{
		return Names.Formats.SUFFIX_JSON;
	}
}
