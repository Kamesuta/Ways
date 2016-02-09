package com.kamesuta.mc.ways;
import java.io.File;
import java.io.FileFilter;

import com.kamesuta.mc.ways.world.way.WayFormat;

public class FileFilterWays implements FileFilter {
    private final boolean directory;

    public FileFilterWays(boolean dir) {
        this.directory = dir;
    }

    @Override
    public boolean accept(File file) {
        if (this.directory) {
            return file.isDirectory();
        }

        return file.getName().toLowerCase().endsWith(WayFormat.getFormat().getSuffix());
    }
}
