package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

import static com.javarush.task.task27.task2712.ConsoleHelper.getAllDishesForOrder;

/**
 * Created by 1 on 11.11.2017.
 */
public class Order {
    private final Tablet tablet;

    public Tablet getTablet() {
        return tablet;
    }

    protected List<Dish> dishes;

    public List<Dish> getDishes() {
        return dishes;
    }

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        dishes = getAllDishesForOrder();
    }

    /**
     * Cчетчик времени готовки заказа в минутах.
     * @return
     */
    public int getTotalCookingTime() {
        int result = 0;
        for (Dish dish : dishes) {
            result += dish.getDuration();
        }
        return result;
    }

    public boolean isEmpty() {
        if (dishes.size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if (dishes.isEmpty()) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder(dishes.get(0).toString());
            for (int i = 1 ; i < dishes.size(); i++) {
                builder.append(", " + dishes.get(i));
            }
            return "Your order: ["  + builder.toString() + "] of " + tablet.toString() ;
        }
    }
}

