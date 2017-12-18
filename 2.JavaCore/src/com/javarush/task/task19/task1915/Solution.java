package com.javarush.task.task19.task1915;

/* 
Дублируем текст
*/

import javax.xml.bind.SchemaOutputResolver;
import java.io.*;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        PrintStream printStream = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream1 = new PrintStream(byteArrayOutputStream);

        System.setOut(printStream1);
        testString.printSomething();
        System.setOut(printStream);

        FileOutputStream fileWriter = null;
        try {
            fileWriter = new FileOutputStream(fileName);
            fileWriter.write(byteArrayOutputStream.toByteArray());
        } catch (NumberFormatException exc) {
            System.out.println("Wrong name file!");
        } finally {
            fileWriter.close();
        }

        System.out.println(byteArrayOutputStream.toString());

        reader.close();
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

