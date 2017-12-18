package com.my_project.calc;

/**
 * Created by 1 on 05.12.2016.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.*;

public class Visual extends JFrame
{
    JButton button1, button2;
    JLabel label1, label2, label3, labelResult, labelInfo;//как ярлык на стекло
    JTextField field1, field2;
    int i, k;
    String a, b;
    Aktivator aktivator = new Aktivator(); // "слушатель" активатор. Создаем объект который отвечает за действие
    // происходящее при нажатии на кнопку


    public Visual(String s){
        super(s);
        setLayout(new FlowLayout());//???
        // текстовые поля
        label1 = new JLabel("Введите первое число: ");//text
        labelInfo = new JLabel("Список операций:\n" +
                "1. Сложение\n" +
                "2. Вычитание\n" +
                "3. Умножение\n" +
                "4. Деление\n" +
                "5. Возведение в степень\n" +
                "6. Извлечение квадратноо корня\n" +
                "10. Нахождение совершенных чисел в интервале от а до b\n" +
                "11. Нахождение простых чисел в интервале от а до b\n");
        label2 = new JLabel("Введите номер операции: ");
        label3 = new JLabel("Введите второе число: ");//текст который не видно, это будет результатом
        labelResult = new JLabel("Результат: ");
        // поля ввода
        field1 = new JTextField(10);// поле ввода длинной 10 символов
        field2 = new JTextField(10);// поле для номера операции
        // нажимные кнопки
        button1 = new JButton("Выполнить");
        button2 = new JButton("Очистить");// кнопка нажимная

        //при помощи команды add добавляем кнопки поочередно в наше окно
        add(label1);
        add(field1);
        add(labelInfo);
        add(label2);
        add(field2);
        add(button1);
        add(button2);
        //привязка кнопок к объекту, теперь при нажатии будет что-то происходить
        //а что именно будет происходить написано в классе объекта Aktivator
        button1.addActionListener(aktivator);
        button2.addActionListener(aktivator);
    }

    public class Aktivator implements ActionListener{

        //этот  метод всегда вызывается при любом действии: движение курсора, нажатие кнопок мыши
        public void actionPerformed(ActionEvent e){
            try
            {
                if (e.getSource() == button1)// нажатие кнопки "Посчитать"
                {
                    int i = Integer.parseInt(field1.getText());
                    i++;

                    a = "Результат: " + i;
                    label3.setText(a);
                }
                if (e.getSource() == button2)//действия при нажатии другой кнопки
                {
                    field1.setText(null);
                    field2.setText(null);
                    label3.setText(null);
                }
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Неверный ввод! Введите число.");
            }
        }

    }
}
