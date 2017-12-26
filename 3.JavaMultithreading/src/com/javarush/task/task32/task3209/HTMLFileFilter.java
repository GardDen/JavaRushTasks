package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by 1 on 26.12.2017.
 */
public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        String end = f.getName().toLowerCase();
        if (f.isDirectory() || end.endsWith(".html") || end.endsWith(".htm")) {
            return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
