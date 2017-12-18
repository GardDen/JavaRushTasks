package com.javarush.task.task18.task1828;

/* 
Прайсы 2
*/


import java.io.*;
import java.util.ArrayList;

public class Solution {

    public static void main(String[] args) throws IOException {
        ArrayList<String> listProduct = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        reader = new BufferedReader(new FileReader(fileName));
        while (reader.ready()) {
            listProduct.add(String.format("%s%n", reader.readLine()));
        }
        reader.close();

        if ("-d".equals(args[0])) {
            for (int i = 0; i < listProduct.size(); i++) {
                if (Integer.parseInt(listProduct.get(i).substring(0, 8).trim())== Integer.parseInt(args[1].trim())) {
                    listProduct.remove(i);
                }
            }
            writeFile(listProduct, fileName);
        }
        if ("-u".equals(args[0])) {
            for (int i = 0; i < listProduct.size(); i++) {
                if (Integer.parseInt(listProduct.get(i).substring(0, 8).trim())== Integer.parseInt(args[1].trim())) {
                    listProduct.remove(i);
                    listProduct.add(i, String.format("%-8s%-30s%-8s%-4s%n", args[1], args[2], args[3], args[4]));
                    break;
                }
            }
            writeFile(listProduct, fileName);
        }
    }

    public static void writeFile(ArrayList<String> listProduct, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (String data : listProduct) {
            writer.write(data);
        }
        writer.close();
    }
}
