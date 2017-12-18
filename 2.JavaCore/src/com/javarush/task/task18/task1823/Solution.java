package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.*;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String data;
        while (true) {
            data = reader.readLine();
            if (data.equals("exit")) {
                break;
            } else {
                Thread thread = new ReadThread(data);
                thread.start();
            }
        }
        //System.out.println(resultMap.toString());
        reader.close();
    }

    public static class ReadThread extends Thread {
        private String fileName;

        @Override
        public void run() {
            super.run();
            try {
                read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public ReadThread(String fileName) throws IOException {
            //implement constructor body
            this.fileName = fileName;
        }
        // implement file reading here - реализуйте чтение из файла тут
        public void read() throws IOException {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            byte[] buffer = new byte[fileInputStream.available()];
            fileInputStream.read(buffer);
            fileInputStream.close();

            ArrayList<Byte> list = new ArrayList<>();
            for (byte x : buffer) {
                list.add(x);
            }

            TreeMap<Byte, Integer> map = new TreeMap<>();
            int max = 0;
            for (Byte x : list) {
                int count = Collections.frequency(list, x);
                if (count > max) {
                    max = count;
                }
                map.put(x, count);
            }

            for (Map.Entry<Byte, Integer> item : map.entrySet()) {
                if (item.getValue() == max) {
                    resultMap.put(fileName, Integer.valueOf(item.getKey()));
                }
            }
        }
    }
}
