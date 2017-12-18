package com.javarush.task.task27.task2712.ad;

/**
 * Created by 1 on 22.11.2017.
 */
public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();
    public static StatisticAdvertisementManager getInstance() {
        return ourInstance;
    }
    private StatisticAdvertisementManager() {
    }

    static AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();
}
