package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
Составить цепочку слов
*/
public class Solution {

    public static void main(String[] args) {
        //...
        try (BufferedReader bf = new BufferedReader(new FileReader(new BufferedReader(new InputStreamReader(System.in)).readLine()))) {
            String temp;
            StringBuilder stringBuilder = new StringBuilder();
            while ((temp = bf.readLine()) != null) {
                stringBuilder.append(temp + " ");
            }
            System.out.println(getLine(stringBuilder.toString().split(" ")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static StringBuilder getLine(String... words) {
        if(words == null || words.length == 0) return new StringBuilder("");
        if(!words[0].isEmpty() && words.length == 1) return  new StringBuilder(words[0]);

        ArrayList<String> list = new ArrayList<String>();
        Collections.addAll(list, words);
        Collections.sort(list);

        StringBuilder stringBuilder = new StringBuilder(list.get(0));
        list.remove(0);
        for (int i = 0; i < list.size(); i++) {
            if (stringBuilder.toString().toLowerCase().charAt(stringBuilder.length() - 1) == list.get(i).toLowerCase().charAt(0)) {
                stringBuilder.append(" " + list.get(i));
                list.remove(i);
                i = -1;
            }
        }
        return stringBuilder;
    }
}
