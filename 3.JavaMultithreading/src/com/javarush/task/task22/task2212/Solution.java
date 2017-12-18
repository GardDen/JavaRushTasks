package com.javarush.task.task22.task2212;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        return false;
    }
/*
+380501234567 - true
+38(050)1234567 - true
+38050123-45-67 - true
050123-4567 - true
+38)050(1234567 - false
+38(050)1-23-45-6-7 - false
050ххх4567 - false
050123456 - false
(0)501234567 - false
 */
    public static void main(String[] args) {
        String begin = "+380501234567 - true\n" +
                "+38(050)1234567 - true\n" +
                "+38050123-45-67 - true\n" +
                "050123-4567 - true\n" +
                "+38)050(1234567 - false\n" +
                "+38(050)1-23-45-6-7 - false\n" +
                "050ххх4567 - false\n" +
                "050123456 - false\n" +
                "(0)501234567 - false";

        String[] massiv = begin.replace(" - true", " ").replace(" - false", " ").replace("\\s", " ").split(" ");
        for (String item: massiv
             ) {
            //System.out.println(item);
        };
        System.out.println("Проверка:");
        for (String item: massiv
                ) {
            //Pattern pattern = Pattern.compile("\\+?[0-9]{12}");
            Pattern pattern = Pattern.compile("(\\+[0-9]{2})?(\\({0,1}[0-9]{3}\\){0,1})");

            Matcher matcher = pattern.matcher(item);
            System.out.println(item + " " + matcher.find());
        }


    }
}
