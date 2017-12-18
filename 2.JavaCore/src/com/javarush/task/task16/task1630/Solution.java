package com.javarush.task.task16.task1630;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class    Solution {
    public static String firstFileName;
    public static String secondFileName;

    //add your code here - добавьте код тут
    /**Статический блок класса Solution должен считывать
     *  с консоли имена двух файлов и сохранять их в переменные firstFileName и secondFileName.*/
    static {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            firstFileName = reader.readLine();
            secondFileName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        //add your code here - добавьте код тут
        f.join();
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    //add your code here - добавьте код тут
    /**Объяви в классе Solution public static класс ReadFileThread.
     * Класс ReadFileThread должен реализовывать интерфейс ReadFileInterface.
     * Класс ReadFileThread должен быть унаследован от подходящего класса.*/
    public static class ReadFileThread extends Thread implements ReadFileInterface {
        String name;
        String fullText = "";

        @Override
        public void setFileName(String fullFileName) {
            name = fullFileName;
        }

        @Override
        /**Метод run класса ReadFileThread должен считывать строки из файла,
         * установленного методом setFileName. А метод getFileContent, этого же класса,
         * должен возвращать вычитанный контент.
         * Возвращаемое значение - это одна строка, состоящая из строк файла, разделенных пробелами.*/
        public String getFileContent() {
            return fullText;
        }

        @Override
        public void run() {
            String s;
            try{
                BufferedReader br = new BufferedReader(new FileReader(name));
                while((s = br.readLine())!= null) {
                    fullText += s + " ";
                }
                fullText = fullText.substring(0, fullText.length() - 1);
                br.close();
            }
            catch (IOException e){
                System.out.println("Ошибка чтене из файла: " + e.toString());
            }
        }
    }
}
