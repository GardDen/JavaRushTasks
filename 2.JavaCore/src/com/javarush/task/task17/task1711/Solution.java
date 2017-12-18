package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();
    public static String[] args;


    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        switch (args[0]){
            case "-c":
                synchronized (allPeople){}
                break;
        }
        Solution.args = args;
        CrUDBatch();
    }

    /**
     * Множественный вызов программы CrUD
     * метод должен:
     * 1. анализировать входящий аргумент: определть вид первого аргумента и в соответствии с ним определять количество
     * 2. Зная количество должен в цикле вызывать CrUD и стирать использованные аргументы кроме первого.
     */
    public static void CrUDBatch(){
        int redix;
        if ("-c".equals(args[0])  ) {
            redix = 3;
        } else if ("-u".equals(args[0])){
            redix = 4;
        } else redix = 1;
        CrUD();
        for (int i = 0; i < (args.length - 1)/redix - 1; i++) {
            for (int n = 1; n <= redix; n++) {
                args[n] = args[n + redix*(1 + i)];
            }
            CrUD();
        }
    }

    public static void CrUD(){
        switch (args[0]){
            case "-c":
                c();
                break;
            case "-u":
                u();
                break;
            case "-d":
                d();
                break;
            case "-i":
                i();
                break;
            default:
                System.out.println("Неверные параметры!");
                break;
        }
    }

    /**
     * Метод преобразовывает входящий строковый параметр args в дату
     * @return Date
     */
    public static Date parsingData(String stringDate){
        String stringDateFormat = "dd/MM/yyyy";
        SimpleDateFormat format = new SimpleDateFormat(stringDateFormat, Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(stringDate);
        } catch (NullPointerException exc) {
            return null;
        } catch (ParseException e) {
            System.out.println("Проблема с преобразованием даты рождения!");
        }
        return date;
    }

    /**
     * Vtnjl
     * @param stringSex
     * @return
     */
    public static Sex parsingSex(String stringSex) {
        if ("м".equals(stringSex)) {
            return Sex.MALE;
        } else if ("ж".equals(stringSex)) {
            return Sex.FEMALE;
        } else return null;
    }

    public static void c(){
        if (args[2].equals("м")) {
            allPeople.add(Person.createMale(args[1], parsingData(args[3])));
        } else allPeople.add(Person.createFemale(args[1], parsingData(args[3])));
        System.out.println(allPeople.size() - 1);
    }

    public static void u(){
        int id = Integer.parseInt(args[1]);
        allPeople.get(id).setName(args[2]);
        allPeople.get(id).setSex(parsingSex(args[3]));
        allPeople.get(id).setBirthDay(parsingData(args[4]));
    }

    public static void d(){
        int id = Integer.parseInt(args[1]);
        allPeople.get(id).setName(null);
        allPeople.get(id).setSex(parsingSex(null));
        allPeople.get(id).setBirthDay(parsingData(null));
    }

    public static void i(){
        int i = Integer.parseInt(args[1]);
        String sex;
        if (allPeople.get(i).getSex() == Sex.MALE) {
            sex = "м";
        } else if (allPeople.get(i).getSex() == Sex.FEMALE) {
            sex = "ж";
        } else sex = null;
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        System.out.print(allPeople.get(i).getName() + " " + sex + " ");
        if (allPeople.get(i).getBirthDay() == null) {
            System.out.print(allPeople.get(i).getBirthDay());
        } else {
            System.out.print(format.format(allPeople.get(i).getBirthDay()));
        }
        System.out.println();
    }

/*    public static void printAll() {
        for (int i = 0; i < allPeople.size(); i++) {
            String sex;
            if (allPeople.get(i).getSex() == Sex.MALE) {
                sex = "м";
            } else if (allPeople.get(i).getSex() == Sex.FEMALE) {
                sex = "ж";
            } else sex = null;
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            System.out.print(allPeople.get(i).getName() + " " + sex + " ");
            if (allPeople.get(i).getBirthDay() == null) {
                System.out.print(allPeople.get(i).getBirthDay());
            } else {
                System.out.print(format.format(allPeople.get(i).getBirthDay()));
            }
            System.out.println();
        }
        System.out.println();
    }*/
}