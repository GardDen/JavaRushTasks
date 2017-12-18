package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream printStream = System.out;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream1 = new PrintStream(byteArrayOutputStream);

        System.setOut(printStream1);
        testString.printSomething();
        System.setOut(printStream);

        String[] buffer = byteArrayOutputStream.toString().split(" ");
        String data = "";
        int result = calculation(Integer.parseInt(buffer[0]), buffer[1], Integer.parseInt(buffer[2]));

        for (String x : buffer) {
            System.out.print(x + " ");
        }
        System.out.println(result);
    }

    public static int calculation(int a, String sign, int b) {
        switch (sign) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
        }
        return 0;
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

