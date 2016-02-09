package com.kamesuta.mc.ways.world.way;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.kamesuta.mc.ways.reference.Names;
import com.kamesuta.mc.ways.reference.Reference;
import com.kamesuta.mc.ways.world.storage.Way;

public class WaySerialize extends WayFormat {

	@Override
	public Way readFromFile(File file) {
	    try {
		    ObjectInputStream ois = new ObjectInputStream( new FileInputStream(file) );
		    Way data = (Way) ois.readObject();
			ois.close();
			return data;
		} catch (IOException e) {
			Reference.logger.error(Names.Formats.SAVE_ERROR, e);
		} catch (ClassNotFoundException e) {
			Reference.logger.error(Names.Formats.SAVE_ERROR, e);
		}
	    return null;
	}

	@Override
	public boolean writeToFile(File file, Way way) {
	    try {
	    	ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream(file) );
			oos.writeObject(way);
		    oos.close();
		    Reference.logger.info(Names.Formats.SAVE_SUCCESS);
		    return true;
		} catch (IOException e) {
			Reference.logger.error(Names.Formats.SAVE_ERROR, e);
		}
	    return false;
	}
}
