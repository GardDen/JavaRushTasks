package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;

import static com.javarush.task.task27.task2712.ConsoleHelper.writeMessage;


/**
 * Created by 1 on 15.11.2017.
 */
public class Cook extends Observable implements Observer {
    private String name;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * — observable — объект, который отправил нам значение
     * — arg — само значение, в нашем случае — это объект Order
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        Tablet tablet = (Tablet)o;
        Order order = (Order) arg;
        writeMessage(String.format("Start cooking — %s, cooking time %dmin", order, order.getTotalCookingTime()));
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(tablet.toString(), this.name, order.getTotalCookingTime()*60, order.getDishes()));
        setChanged();
        notifyObservers(order);
    }
}
