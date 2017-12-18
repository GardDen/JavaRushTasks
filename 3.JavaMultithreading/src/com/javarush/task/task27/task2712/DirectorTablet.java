package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 1 on 21.11.2017.
 */
public class DirectorTablet {
    StatisticManager statisticManager = StatisticManager.getInstance();
    DateFormat format = new SimpleDateFormat("d-MMM-yyyy", Locale.ENGLISH);

    /**
     * 1. какую сумму заработали на рекламе, сгруппировать по дням;
     */
    public void printAdvertisementProfit() {

        TreeMap<Date, Long> advertisementRevenueAgregatedByDay = StatisticManager.getInstance().getAdvertisementRevenueAgregatedByDay();
        if (advertisementRevenueAgregatedByDay.isEmpty()) return;
        NavigableSet<Date> datesRow = advertisementRevenueAgregatedByDay.descendingKeySet();
        Long totalAmmout = Long.valueOf(0);
        for (Date date : datesRow) {
            Long dayAmount = advertisementRevenueAgregatedByDay.get(date);
            totalAmmout += dayAmount;
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %.2f",
                    new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(date),
                    0.01d * dayAmount)
            );
        }
        ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Total - %.2f", 0.01d * totalAmmout));


        /*SortedMap<Date, Long> sortedMap = new TreeMap<>(new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                return (int)(o1.getTime() - o2.getTime());
            }
        });
        long totalAmount = 0;
        sortedMap.putAll(statisticManager.calculateProfitForDay());
        for (Map.Entry<Date, Long> item : sortedMap.entrySet()) {
            writeMessage(format.format(item.getKey().getDay()) + " - " + item.getValue()/60);
            totalAmount += item.getValue();
        }
        writeMessage("Total - " + totalAmount/60);*/
    }

    /**
     * 2. загрузка (рабочее время) повара, сгруппировать по дням;
     */
    public void printCookWorkloading() {
        TreeMap<Date, HashMap<String, Integer>> cookWorkloadingAgregatedByDay = StatisticManager.getInstance().getCookWorkloadingAgregatedByDay();
        if (cookWorkloadingAgregatedByDay.isEmpty()) return;
        NavigableSet<Date> datesRow = cookWorkloadingAgregatedByDay.descendingKeySet();
        for (Date date: datesRow) {
            ConsoleHelper.writeMessage(new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(date));
            List<Map.Entry<String, Integer>> cooksNameWorkDuration = new ArrayList(cookWorkloadingAgregatedByDay.get(date).entrySet());
            Collections.sort(cooksNameWorkDuration, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                    return b.getValue() - a.getValue();
                }
            });
            for (Map.Entry<String, Integer> cookNameWorkDuration: cooksNameWorkDuration) {
                ConsoleHelper.writeMessage(String.format("%s - %d min",
                        cookNameWorkDuration.getKey(),
                        (int)Math.ceil(cookNameWorkDuration.getValue() / 60.0))
                );
            }
        }



        /*SortedMap<Date, Map<String, Integer>> sortedMap = new TreeMap<>(new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                return (int)(o1.getTime() - o2.getTime());
            }
        });
        sortedMap.putAll(statisticManager.calculateTimeWorkCookDay());

        //отсортировали по дате теперь необходимо отсортировать по имени в алфавитном порядке
        for (Map.Entry<Date, Map<String, Integer>> dateMapEntry : sortedMap.entrySet()) {
            SortedMap<String, Integer> sortedMapName = new TreeMap<>(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            sortedMapName.putAll(dateMapEntry.getValue());
            writeMessage(format.format(dateMapEntry.getKey()));
            for (Map.Entry<String, Integer> itemName : sortedMapName.entrySet()) {
                writeMessage(itemName.getKey() + " - " + (int)(itemName.getValue()/60) + " min");
            }
        }*/
    }

    /**
     3. список активных роликов и оставшееся количество показов по каждому;
     */
    public void printActiveVideoSet() {

    }

    /**
     4. список неактивных роликов (с оставшемся количеством показов равным нулю).
     */
    public void printArchivedVideoSet() {

    }


}
