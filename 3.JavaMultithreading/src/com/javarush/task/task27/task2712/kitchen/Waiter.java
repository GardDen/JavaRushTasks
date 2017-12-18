package com.javarush.task.task27.task2712.kitchen;

import java.util.Observable;
import java.util.Observer;

import static com.javarush.task.task27.task2712.ConsoleHelper.writeMessage;

/**
 * Created by 1 on 15.11.2017.
 */
public class Waiter implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        writeMessage(arg.toString() + " was cooked by " + o.toString());
    }
}
