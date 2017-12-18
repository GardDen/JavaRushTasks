package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

/**
 * Created by 1 on 16.11.2017.
 */
public class AdvertisementManager {
    public final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;
    public Set<List<Advertisement>> setCombo = new HashSet<>();
    public Comparator<List<Advertisement>> comparatorCombo = new Comparator<List<Advertisement>>() {
        @Override
        public int compare(List<Advertisement> o1, List<Advertisement> o2) {
            int flag = Long.compare(getMoneyOfOrder(o2), getMoneyOfOrder(o1));
            if (flag == 0) {
                flag = Integer.compare(getTimeCombo(o2), getTimeCombo(o1));
            }
            if (flag == 0) {
                flag = Integer.compare(o1.size(), o2.size());
            }
            return flag;
        }
    };
    public Comparator<Advertisement> comparator = new Comparator<Advertisement>() {
        @Override
        public int compare(Advertisement o1, Advertisement o2) {
            return Long.compare(o2.getAmountPerOneDisplaying(), o1.getAmountPerOneDisplaying());
        }
    };

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }


    /**
     * Подобрать список видео из доступных, просмотр которых обеспечивает максимальную выгоду.
     * +1. max money
     *      +2. timevideo < timeOfCooking
     *      +3. each video play once
     * +4. if (combo1 == combo2) {return combo max time}
     * +5. if max time =={ return combo min count video}
     *      6. +hits > 0
     * +сперва составить список комбо влазящих по времени
     * а затем выбрать самую денежную
     * 7 list.sort (video from max to min, second sort min money/sek of video to max)
     * @throws NoVideoAvailableException
     */
    public void processVideos() throws NoVideoAvailableException {
        if (storage.list().isEmpty()) {
            throw new NoVideoAvailableException();
        }
        getAllCombo(getValidAdvertisementList(), 0);
        List<List<Advertisement>> listCombo = new ArrayList<>(setCombo);
        Collections.sort(listCombo, comparatorCombo);
        /*for (int i = 0; i < listCombo.size(); i++) {
            System.out.println("money = " + getMoneyOfOrder(listCombo.get(i)) + "time = " + getTimeCombo(listCombo.get(i)) + " combo: " + listCombo.get(i));
        }*/
        Collections.sort(listCombo.get(0), comparator);
        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(listCombo.get(0),
                getMoneyOfOrder(listCombo.get(0)), getTimeCombo(listCombo.get(0))));
        for (int i = 0; i < listCombo.get(0).size(); i++) {
            ConsoleHelper.writeMessage(listCombo.get(0).get(i).toString());
            listCombo.get(0).get(i).revalidate();
        }

    }

    /**
     * берем только доступные к показу и не больше времени готовки видео.
     * @return
     */
    private List<Advertisement> getValidAdvertisementList() {
        List<Advertisement> listAvailableVideo = new ArrayList<>();
        for (Advertisement advertisement : storage.list()) {
            if (advertisement.getHits() > 0 && advertisement.duration <= timeSeconds  ) {
                listAvailableVideo.add(advertisement);
            }
        }
        return listAvailableVideo;
    }

    /**
     * На основе подходящих видео, формируем список всех возможных комбинаций.
     * И на ходу этот список обрезаем по времени и исключаем из него совпадения из-за обрезки
     * @param arr
     * @param k
     */
    private void getAllCombo(List<Advertisement> arr, int k) {
        for (int i = k; i < arr.size(); i++) {
            Collections.swap(arr, i, k);
            getAllCombo(arr, k + 1);
            Collections.swap(arr, k, i);
        }
        if (k == arr.size() - 1) {
            setCombo.add(trimCombo(arr));
        }
    }

    private List<Advertisement> trimCombo(List<Advertisement> list) {
        List<Advertisement> trimList = new ArrayList<>();
        int timeCombo = 0; // in sek
        for (Advertisement advertisement : list) {
            timeCombo += advertisement.getDuration();
            if (timeCombo <= timeSeconds) {
                trimList.add(advertisement);
            } else break;
        }
        return trimList;
    }

    private int getTimeCombo(List<Advertisement> list) {
        int timeCombo = 0;
        for (Advertisement advertisement : list) {
            timeCombo += advertisement.getDuration();
        }
        return timeCombo;
    }

    private long getMoneyOfOrder(List<Advertisement> list) {
        long moneyOfOrder = 0;
        for (Advertisement advertisement : list ) {
            moneyOfOrder += advertisement.getAmountPerOneDisplaying();
        }
        return moneyOfOrder;
    }
}
