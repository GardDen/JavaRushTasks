package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Парсер реквестов
*/

public class Solution {
    static ArrayList<String> listParameters = new ArrayList<>();
    static ArrayList<String> listValueObjects = new ArrayList<>();

    /**.**/
    public static void main(String[] args) throws IOException {
        //add your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String url = reader.readLine();
        parsing(url);
        print();
    }

    /**.
     * Method parses url:
     * finds parameters and add they in listParameters
     * if parameters is obj, that method add value parameters in listValueObjects
      */
    public static void parsing(String url) {
        char[] chars = url.toCharArray();
        /* for (char ch : chars) {
            System.out.print(ch + " ");
        }
        System.out.println();*/
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '?' || chars[i] == '&') {
                while (chars[i] =='?' || chars[i] =='&') i++;//miss repit siimbol
                int countj = i;
                while (chars[countj] != '=' && chars[countj] != '&' && countj < chars.length ) {
                    countj++; //determine length parameter
                }
                listParameters.add(url.substring(i, countj));
                if (listParameters.size() > 0 && listParameters.get(listParameters.size() - 1).equals("obj")) {
                    i = countj;
                    while (countj < chars.length && chars[countj] != '&') {
                        countj++;//determine length value
                    }
                    if (i == countj) {
                        listValueObjects.add("");//check case "&obj&", obj = null
                    } else {
                        listValueObjects.add(url.substring(i + 1, countj));
                    }
                }
                i = countj - 1;
            }
        }
    }

    /**Print all parameters url.**/
    public static void print() {
        for (int i = 0; i < listParameters.size(); i++) {
            System.out.print(listParameters.get(i));
            if (i != listParameters.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
        if (listValueObjects.size() > 0) {
            callAlert();
        }
    }

    /**Call metod alert for all parameters obj.**/
    public static void callAlert() {
        for (int i = 0; i < listValueObjects.size(); i++) {
            if (isInteger(listValueObjects.get(i))) {
                alert(listValueObjects.get(i));
            } else if (isDooble(listValueObjects.get(i))){
                alert(Double.parseDouble(listValueObjects.get(i)));
            } else {
                alert(listValueObjects.get(i));
            }
        }
    }

    /**Check string is Integer?.**/
    private static boolean isInteger(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    /**Check string is Doooble?.**/
    private static boolean isDooble(String text) {
        try {
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
