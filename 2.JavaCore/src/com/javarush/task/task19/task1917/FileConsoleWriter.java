package com.javarush.task.task19.task1917;

/* 
Свой FileWriter
*/

import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;

public class FileConsoleWriter {
    private FileWriter fileWriter;

    public FileConsoleWriter(String fileName) throws IOException {
        fileWriter = new FileWriter(fileName);
    }

    public FileConsoleWriter(String fileName, boolean append) throws IOException {
        fileWriter = new FileWriter(fileName, append);
    }

    public FileConsoleWriter(File file) throws IOException {
        fileWriter = new FileWriter(file);
    }

    public FileConsoleWriter(File file, boolean append) throws IOException {
        fileWriter = new FileWriter(file, append);
    }

    public FileConsoleWriter(FileDescriptor fd) {
        fileWriter = new FileWriter(fd);
    }

    public void write(char[] cbuf, int off, int len) throws IOException {
        fileWriter.write(cbuf, off, len);
        for (int i = 0; i < len; i++) {
            System.out.print(cbuf[i + off]);
        }
    }

    public void write(int c) throws IOException {
        fileWriter.write(c);
        System.out.println(c);
    }

    public void write(String str) throws IOException {
        fileWriter.write(str);
        System.out.println(str);
    }

    public void write(String str, int off, int len) throws IOException {
        fileWriter.write(str, off, len);
        for (int i = 0; i < len; i++) {
            System.out.print(str.charAt(i + off));
        }
    }

    public void write(char[] c) throws IOException {
        fileWriter.write(c);
        System.out.println(c);
    }

    public void close() throws IOException {
        fileWriter.close();
    }

    public static void main(String[] args) throws IOException {
      /*  String file1 = "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\" +
                "javarush\\task\\task19\\task1917\\test.txt";

        FileConsoleWriter fileConsoleWriter = new FileConsoleWriter(file1);
        FileWriter fileWriter = new FileWriter(file1);
        String buffer = "97687688";
        int n = 0;

        fileConsoleWriter.write(buffer);
        */



    }
}
