package com.kamesuta.mc.ways.world.way;

public class UnsupportedFormatException extends Exception {
    public UnsupportedFormatException(String format) {
        super(String.format("Unsupported format: %s", format));
    }
}
