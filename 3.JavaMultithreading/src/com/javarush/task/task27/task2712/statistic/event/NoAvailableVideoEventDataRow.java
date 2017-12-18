package com.javarush.task.task27.task2712.statistic.event;

import java.util.Date;

/**
 * Created by 1 on 20.11.2017.
 */
public class NoAvailableVideoEventDataRow implements EventDataRow{
    int totalDuration;
    Date currentDate;

    public NoAvailableVideoEventDataRow(int totalDuration) {
        this.totalDuration = totalDuration;
        currentDate = new Date();
    }

    @Override
    public EventType getType() {
        return EventType.NO_AVAILABLE_VIDEO;
    }

    @Override
    public Date getDate() {
        return currentDate;
    }

    @Override
    public int getTime() {
        return totalDuration;
    }
}
