package com.javarush.task.task27.task2712.ad;

/**
 * Created by 1 on 16.11.2017.
 */
public class Advertisement {
    public Object content;// — видео
    public String name;// — имя/название
    private long initialAmount;// — начальная сумма, стоимость рекламы в копейках. Используем long, чтобы избежать проблем с округлением
    private int hits;// — количество оплаченных показов
    public int duration;// — продолжительность в секундах
    private long amountPerOneDisplaying;//стоимость одного показа рекламного объявления в копейках

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        amountPerOneDisplaying = this.initialAmount / hits;
    }

    public void revalidate() {
        if (hits <= 0) {
            throw new UnsupportedOperationException();
        }
        hits--;
    }

    public int getHits() {
        return hits;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    @Override
    public String toString() {
        return name + " is displaying... " + amountPerOneDisplaying + ", " + (amountPerOneDisplaying * 1000/duration);
    }
}
