package com.javarush.task.task14.task1417;

/**
 * Created by 1 on 21.02.2017.
 */
class USD extends Money{
    public USD(double amount) {
        super(amount);
    }

    @Override
    public String getCurrencyName() {
        return "USD";
    }
}
