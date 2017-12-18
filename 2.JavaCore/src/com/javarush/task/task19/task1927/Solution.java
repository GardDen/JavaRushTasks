package com.javarush.task.task19.task1927;

/* 
Контекстная реклама
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        //Запоминаем настоящий PrintStream
        PrintStream saveOut = System.out;

        //Создаем динамичный массив для хранения данных
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        //Создаем адаптер на основе массива и сразу
        //Устанавливаем адаптер
        System.setOut(new PrintStream(byteArrayOutputStream));

        //Вызываем функцию, данные которой на самом деле не выведутся, а запишутся в ложны поток - в массив
        testString.printSomething();

        //Преобразовываем данные
        String[] xz = byteArrayOutputStream.toString().split("\\n");
        String result = "";
        for (int i = 0; i < xz.length ; i++) {
            result += xz[i] + "\r\n";
            if ((i + 1) % 2 == 0) {
                result += "JavaRush - курсы Java онлайн\r\n";
            }
        }

        //Возвращаем первоначальное значение переменной System.out
        System.setOut(saveOut);

        System.out.println(result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
