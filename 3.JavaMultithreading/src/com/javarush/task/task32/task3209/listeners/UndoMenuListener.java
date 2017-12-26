package com.javarush.task.task32.task3209.listeners;

import com.javarush.task.task32.task3209.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 * Created by 1 on 19.12.2017.
 */
public class UndoMenuListener implements MenuListener {
    private View view;
    private JMenuItem undoMenuItem;//отменить
    private JMenuItem redoMenuItem;//вернуть

    public UndoMenuListener(View view, JMenuItem undoMenuItem, JMenuItem redoMenuItem) {
        this.view = view;
        this.undoMenuItem = undoMenuItem;
        this.redoMenuItem = redoMenuItem;
    }

    @Override
    public void menuSelected(MenuEvent e) {
        boolean isAvailabelUndo = view.canUndo();
        undoMenuItem.setEnabled(isAvailabelUndo);
        boolean isAvailabelRedo = view.canRedo();
        redoMenuItem.setEnabled(isAvailabelRedo);
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
