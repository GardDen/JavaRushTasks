package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.javarush.task.task27.task2712.ConsoleHelper.writeMessage;

/**
 * Created by 1 on 11.11.2017.
 */
public class Tablet extends Observable {
    final int number;
    public Order order;

    public static Logger logger = Logger.getLogger(Tablet.class.getName());

    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder() {
        try {
            order = new Order(this);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }

        writeMessage(order.toString());
        setChanged();
        if (!order.isEmpty()) {
            new Thread(() -> {
                AdvertisementManager manager = new AdvertisementManager(order.getTotalCookingTime() * 60);
                try {
                    manager.processVideos();
                } catch (NoVideoAvailableException e) {
                    logger.log(Level.INFO, "No video is available for the order " + order.toString());
                }
            }).run();
            notifyObservers(order);
        }
        return order;
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
