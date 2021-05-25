package me.matamor.pruebas.lib.config;

import java.io.File;

public interface IConfig extends ISection {

    File getFile();

    boolean exists();

    void load();

    void save();

}