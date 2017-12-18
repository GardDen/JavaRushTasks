package com.javarush.task.task18.task1813;

import java.io.*;

/* 
AmigoOutputStream
*/

public class AmigoOutputStream extends FileOutputStream{
    public static String fileName = "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\" +
            "javarush\\task\\task18\\task1808\\1.txt";
    private FileOutputStream fileOutputStream;

    public AmigoOutputStream(FileOutputStream fileOutputStream) throws FileNotFoundException {
        super(String.valueOf(fileOutputStream));
        this.fileOutputStream = fileOutputStream;
    }


    @Override
    public void write(byte[] b) throws IOException {
        fileOutputStream.write(b);
    }

    public void write(int n) throws IOException {
        fileOutputStream.write(n);
    }

    public void write(byte[] b, int off, int len) throws IOException {
        fileOutputStream.write(b, off, len);
    }

    public void flush() throws IOException {
        fileOutputStream.flush();
    }
    public void close() throws IOException {
        flush();
        fileOutputStream.write("JavaRush © All rights reserved.".getBytes());
        fileOutputStream.close();
    }



    public static void main(String[] args) throws FileNotFoundException {
        new AmigoOutputStream(new FileOutputStream(fileName));
    }

}
