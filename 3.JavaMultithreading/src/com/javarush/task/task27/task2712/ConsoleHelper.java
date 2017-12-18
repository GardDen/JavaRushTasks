package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 11.11.2017.
 */
public class ConsoleHelper {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> listDish= new ArrayList<>();
        String temp;
        writeMessage(Dish.allDishesToString());
        do {
            writeMessage("Выберите блюдо:");
            temp = readString();
            try {
                listDish.add(Dish.valueOf(temp));
            } catch (IllegalArgumentException exception) {
                if (temp.equals("exit")) {
                    break;
                } else {
                    writeMessage("Такого блюда нет.");
                }
            }
        } while (true);
        return listDish;
    }

}
