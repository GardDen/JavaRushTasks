package com.javarush.task.task18.task1808;

/* 
Разделение файла
*/

import java.io.*;

public class Solution {
    /**
     * Требования:
     1. Программа должна три раза считать имена файлов с консоли.
     2. Для чтения из файла используй поток FileInputStream, для записи в файлы - FileOutputStream
     3. Первую половину байт из первого файла нужно записать во второй файл.
     4. Вторую половину байт из первого файла нужно записать в третий файл.
     5. Потоки FileInputStream и FileOutputStream должны быть закрыты.
     */
    public static void main(String[] args) throws IOException {
        read();
    }


    public static void read() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine(), fileName2 = reader.readLine(), fileName3 = reader.readLine();
        /*String fileName1 = "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\" +
                "javarush\\task\\task18\\task1808\\1.txt";
        String fileName2 = "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\" +
                "javarush\\task\\task18\\task1808\\2.txt";
        String fileName3 = "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\" +
                "javarush\\task\\task18\\task1808\\3.txt";*/

        FileInputStream fileInputStream = new FileInputStream(fileName1);
        FileOutputStream fileOutputStream1 = new FileOutputStream(fileName2);
        FileOutputStream fileOutputStream2 = new FileOutputStream(fileName3);

        int numberBytes = fileInputStream.available();
        int halfLess = numberBytes/2;

        //намного красивее бы было через fileOutputStream3.write(buff, count / 2+count%2, count / 2);
        while (fileInputStream.available() > 0) {
            int data = fileInputStream.read();
            if (fileInputStream.available() + 1 > halfLess) {
                fileOutputStream1.write(data);
            } else {
                fileOutputStream2.write(data);
            }
        }

        fileInputStream.close();
        fileOutputStream1.close();
        fileOutputStream2.close();
    }

}
