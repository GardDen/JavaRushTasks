package com.javarush.task.task32.task3209.listeners;

import com.javarush.task.task32.task3209.View;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by 1 on 19.12.2017.
 * Этот класс будет слушать и обрабатывать изменения состояния панели вкладок.
 */
public class TabbedPaneChangeListener implements ChangeListener {
    private View view;

    /**
     *
     * @param view представление.
     */
    public TabbedPaneChangeListener(View view) {
        this.view = view;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        view.selectedTabChanged();
    }


}
