package com.javarush.task.task14.task1408;

/**
 * Created by 1 on 08.02.2017.
 */
public class RussianHen extends Hen {

    @Override
    public int getCountOfEggsPerMonth() {
        return 30;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " Моя страна - " + Country.RUSSIA + ". Я несу " + this.getCountOfEggsPerMonth() +
                " яиц в месяц.";
    }
}
