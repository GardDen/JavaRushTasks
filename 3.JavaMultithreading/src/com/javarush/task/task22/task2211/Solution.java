package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/* 
Смена кодировки
*/
public class Solution {
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException {
        copyFileUsingStream(new File(args[0]), new File(args[1]));
    }

    private static void copyFileUsingStream(File source, File dest) throws IOException {

        try (InputStreamReader is = new InputStreamReader(new FileInputStream(source), Charset.forName("utf-8"));
        OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(dest), Charset.forName("windows-1251"))){
            while (is.ready()) {
                os.write(is.read());
            }
        }
    }
}
