package com.javarush.task.task14.task1411;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
User, Looser, Coder and Proger
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Person person = null;
        String key = null;
        boolean flag = true;
        ArrayList<Person> listPeron = new ArrayList<>();
        //тут цикл по чтению ключей, пункт 1
        do{
            key = reader.readLine();
            person = null;
            if (key.equals("user")){
                person = new Person.User();
            } else if (key.equals("loser")) {
                person = new Person.Loser();
            } else if (key.equals("coder")) {
                person = new Person.Coder();
            } else if (key.equals("proger")) {
                person = new Person.Proger();
            } else flag = false;
            if (person != null) {
                listPeron.add(person);
                //doWork(person);
            }
        }
        while (flag);


        {
            //создаем объект, пункт 2
            for (int i = 0; i < listPeron.size(); i++){
                doWork(listPeron.get(i));
            }
             //вызываем doWork

        }
    }

    public static void doWork(Person person) {
        // пункт 3
        if (person instanceof Person.User){
            person.live();
        } else if (person instanceof Person.Loser) {
            person.doNothing();
        } else if (person instanceof Person.Coder) {
            person.coding();
        } else  if (person instanceof Person.Proger) {
            person.enjoy();
        }
    }
}
