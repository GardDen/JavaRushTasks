package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.io.IOException;

/**
 * Created by 1 on 11.11.2017.
 */
public class Restaurant {
    public static void main(String[] args) throws IOException {
        Cook cook = new Cook("Cooker");
        Waiter waiter = new Waiter();
        cook.addObserver(waiter);
        Tablet tablet = new Tablet(5);
        tablet.addObserver(cook);
        tablet.createOrder();
        tablet.createOrder();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();


    }
}
