package com.javarush.task.task32.task3209.listeners;

import com.javarush.task.task32.task3209.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

/**
 * класс будет работать аналогично классу UndoMenuListener только для других пунктов меню. Пункты меню, отвечающие за
 * стиль, шрифт, цвет и т.д. должны быть доступны только тогда, когда в нашем редакторе выбрана первая вкладка.
 * Created by 1 on 19.12.2017.
 */
public class TextEditMenuListener implements MenuListener {
    private View view;




    public TextEditMenuListener(View view) {
        this.view = view;
    }

    /**
     * Это метод делает недоступными пункты меню, отвечающие за стиль, шрифт, цвет и т.д. если не выбрана вкладка html.
     * @param menuEvent MenuEvent используется для уведомления заинтересованных сторон о том, что меню,
     * которое является источником события, было опубликовано, выбрано или отменено.
     * getSource() - возвращает Объект, с которого произошло Событие.
     */
    @Override
    public void menuSelected(MenuEvent menuEvent) {
        //Из переданного параметра получать объект, над которым было совершено действие
        JMenu jMenu = (JMenu) menuEvent.getSource();
        //У полученного меню получать список компонентов (пунктов меню)
        Component[] components = jMenu.getMenuComponents();
        //Для каждого пункта меню вызывать метод setEnabled, передав в качестве параметра
        //результат вызова метода isHtmlTabSelected() из представления.
        for (Component component : components) {
            component.setEnabled(view.isHtmlTabSelected());
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
