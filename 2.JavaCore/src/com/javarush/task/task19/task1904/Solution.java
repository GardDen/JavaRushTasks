package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) throws IOException, ParseException {

        /*Scanner scanner = new Scanner("Петров Петр Петрович 31 12 1957");
        PersonScanner personScanner= new PersonScannerAdapter(scanner);
        System.out.println(personScanner.read());*/

    }

    public static class PersonScannerAdapter implements PersonScanner {
        final private Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException, ParseException {
            String[] words = fileScanner.nextLine().split(" ");
            String joinDate = words[3] + " " + words[4] + " " + words[5];
            Date date = new SimpleDateFormat("d MM yyyy", Locale.ENGLISH).parse(joinDate);
            return new Person(words[1], words[2], words[0], date);
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
