package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 1 on 20.11.2017.
 */
public class StatisticManager {
    private static StatisticManager statisticManager = new StatisticManager();
    private StatisticStorage storage = new StatisticStorage();
    private Set<Cook> cooks = new HashSet<>();
    DateFormat format = new SimpleDateFormat("d-MMM-yyyy", Locale.ENGLISH);



    private StatisticManager() {

    }

    public static StatisticManager getInstance() {
        return statisticManager;
    }

    public void register(EventDataRow data) {
        storage.put(data);
    }

    public void register(Cook cook) {
        cooks.add(cook);
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> eventMapStorage = new HashMap<>();

        public StatisticStorage() {
            for (EventType item : EventType.values()) {
                eventMapStorage.put(item, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data) {
            eventMapStorage.get(data.getType()).add(data);
        }

        private List<EventDataRow> getEvents(EventType eventType) {
            return eventMapStorage.get(eventType);
        }

    }

    /**
     * Метод обращается к хранилищу статистики, достает оттуда статистику по показам видео.
     * Соглассно этой статистике формирует отображение по дате и профиту в эту дату.
     * @return
     */
    /*public Map<Date, Long> calculateProfitForDay() {
        Map<Date, Long> mapProfit = new HashMap<>();
        List<EventDataRow> listVideo = eventMapStorage.eventMapStorage.get(EventType.SELECTED_VIDEOS);
        for (EventDataRow eventDataRow : listVideo) {
            VideoSelectedEventDataRow tempEvent = (VideoSelectedEventDataRow)eventDataRow;

            if (mapProfit.containsKey(eventDataRow.getDate())) {
                long summa = mapProfit.get(eventDataRow.getDate()) + tempEvent.getAmount();
                mapProfit.put(eventDataRow.getDate(), summa);
            } else {
                mapProfit.put(eventDataRow.getDate(), tempEvent.getAmount());
            }
        }
        return mapProfit;
    }*/

    /**
     * Метод собирает статистику по готовке
     * По каждому дню
     * по кажому повару
     * и суммирует время занятости каждого повара
     * @return
     */
    /*public Map<Date, Map<String, Integer>> calculateTimeWorkCookDay() {
        Map<Date, Map<String, Integer >> mapAllDate = new HashMap<>();
        List<EventDataRow> listEvent = eventMapStorage.eventMapStorage.get(EventType.COOKED_ORDER);
        for (EventDataRow eventItem : listEvent) {
            CookedOrderEventDataRow event = (CookedOrderEventDataRow)eventItem;
            //если ест дата, то:
            if (mapAllDate.containsKey(event.getDate())) {
                //если есть да и имя, то увеличиваем время для этого имени
                if (mapAllDate.get(event.getDate()).containsKey(event.getCookName())) {
                    int summa = mapAllDate.get(event.getDate()).get(event.getCookName()) + event.getTime();
                    mapAllDate.get(event.getDate()).put(event.getCookName(), summa);
                //если нет такого имени то добавляем данные
                } else {
                    mapAllDate.get(event.getDate()).put(event.getCookName(), event.getTime());
                }

            //если нет такой даты то добавляем эту дату и эти данные
            } else {
                mapAllDate.put(event.getDate(), new HashMap<>());
                mapAllDate.get(event.getDate()).put(event.getCookName(), event.getTime());
            }
        }
        return mapAllDate;
    }*/

    public TreeMap<Date, Long> getAdvertisementRevenueAgregatedByDay() {
        TreeMap<Date, Long> result = new TreeMap<>();
        for (EventDataRow eventDataRow : storage.getEvents(EventType.SELECTED_VIDEOS)) {
            VideoSelectedEventDataRow vEventDataRow = (VideoSelectedEventDataRow) eventDataRow;
            GregorianCalendar gDate = new GregorianCalendar();
            gDate.setTime(vEventDataRow.getDate());
            for(int i : TIME_FIELDS)
                gDate.clear(i);
            Date date = gDate.getTime();
            Long dayRevenue = result.get(date) ;
            if (dayRevenue == null) dayRevenue = Long.valueOf(0);
            result.put(date, dayRevenue + vEventDataRow.getAmount());
        }
        return result;
    }

    public TreeMap<Date, HashMap<String, Integer>> getCookWorkloadingAgregatedByDay() {
        TreeMap<Date, HashMap<String, Integer>> result = new TreeMap<>();
        for (EventDataRow eventDataRow : storage.getEvents(EventType.COOKED_ORDER)) {
            CookedOrderEventDataRow cookDataRow = (CookedOrderEventDataRow) eventDataRow;
            GregorianCalendar gDate = new GregorianCalendar();
            gDate.setTime(cookDataRow.getDate());
            for(int i : TIME_FIELDS)
                gDate.clear(i);
            Date date = gDate.getTime();
            HashMap<String, Integer> cooksNameWorkDuration = result.get(date);
            if (cooksNameWorkDuration == null) {
                cooksNameWorkDuration = new HashMap<>();
                result.put(date, cooksNameWorkDuration);
            }
            String cookName = cookDataRow.getCookName();
            Integer cookWorkDuration = cooksNameWorkDuration.get(cookName);
            if (cookWorkDuration == null) cookWorkDuration = Integer.valueOf(0);
            cooksNameWorkDuration.put(cookName, cookWorkDuration + cookDataRow.getTime());
        }
        return result;
    }

    private static final int[] TIME_FIELDS =
            {
                    Calendar.HOUR_OF_DAY,
                    Calendar.HOUR,
                    Calendar.AM_PM,
                    Calendar.MINUTE,
                    Calendar.SECOND,
                    Calendar.MILLISECOND
            };



}
