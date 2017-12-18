package com.javarush.task.task19.task1913;

/* 
Выводим только цифры
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream printStream = System.out;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();//динамическй массив
        PrintStream printStream1 = new PrintStream(byteArrayOutputStream);//адаптер

        System.setOut(printStream1);
        testString.printSomething();

        String data = "";
        char[] buf = byteArrayOutputStream.toString().toCharArray();
        for (int i = 0; i < buf.length; i++) {
            if (buf[i] >= 48 && buf[i] <= 57 ) {
                data += buf[i];
            }
        }
        System.setOut(printStream);
        System.out.println(data);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's 1 a 23 text 4 f5-6or7 tes8ting");
        }
    }
}
