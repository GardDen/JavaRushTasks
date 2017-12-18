package com.my_project.calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JFrame;

import static java.lang.Math.*;

/**
 * Created by 1 on 12.10.2016.
 */
public class Calc
{
    public static void main(String[] args) throws IOException
    {
        int number1;

        System.out.println("Добро пожаловать в кулькулятор!");

        /*создание окна калькулятора и  его параметры
        Visual window = new com.my_project.calc.Visual("Калькулятор");
        window.setVisible(true);// отображение окна
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//stop and exit the program
        window.setSize(300, 200);
        window.setResizable(true);// если false  то размеры окна const
        window.setLocationRelativeTo(null);//если null то окно открывается по центру экрана
        */
        //select operatione and metod do all calculations
        //1 - 1 число, 2 - номер операции.
        //selectOperation(number1, v.reader(s2));

        /*    это  не нужно
        Visualisation message = new Visualisation();
        message.scanN();
        //Запускаем окно ввода - Input, введенное значение сохраняется как переменная объекта класса визуализации
        number1 = message.number;
        */
    }

    /*
    // метод считывания
    public static int reader() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int scan = Integer.parseInt(reader.readLine());
        return scan;
    }
    */

    //метод считывания второго числа, если это необходимо
    public static int reader2number() throws IOException{
        System.out.print("Введите второе число: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int reader2number = Integer.parseInt(reader.readLine());
        return reader2number;
    }

    //metod select operation
    public static void selectOperation(int number1, int operation) throws IOException
    {
        long result, a = number1, b;
        double result_d;
        ArrayList<Integer> list = new ArrayList<Integer>();

        switch (operation)
        {
            case 1:
                b = reader2number();
                result = a + b;
                System.out.println(a + " + " + b + " = " + result);
                break;
            case 2:
                b = reader2number();
                result = a - b;
                System.out.println(a + " - " + b + " = " + result);
                ;
                break;
            case 3:
                b = reader2number();
                result = a * b;
                System.out.println(a + " * " + b + " = " + result);
                ;
                break;
            case 4:
                b = reader2number();
                if (b == 0) System.out.println("Делить на ноль нельзя! На мамом деле можно, но лучше этого не знать)");
                else
                {
                    result = a / b;
                    System.out.println(a + " : " + b + " = " + result + " целых, остаток - " + a % b);
                }
                break;
            case 5:
                b = reader2number();
                result = a;
                if (b == 0) System.out.println(a + " в степени " + b + " = " + 1);
                else
                {
                    for (int i = 1; i < b; i++)
                    {
                        result = result * a;
                    }
                    System.out.println(a + " в степени " + b + " = " + result);
                }
                break;
            case 10: //Совершенные числа - это числа которые равны сумме их делителей, не считая себя.
                b = reader2number();
                for (int i = (int) a; i <= b; i++)
                {
                    int sum_divider = 0;
                    for (int j = 1; j < i; j++)
                    {
                        if (i % j == 0)
                        {
                            sum_divider = sum_divider + j;// если число делится нацело на какое-то число,
                            // то мы суммируем это число с другими делителями
                        }
                    }
                    if (i == sum_divider)
                    {// если наше число совершенно, то добавляем его в список совершенных чисел
                        list.add(i);
                    }
                }
                if (list.size() == 0) System.out.println("Совершенных чисел в интервале от " + a + " до " + b + " нету.");
                else
                {
                    System.out.print("Совершенные числа в интервале от " + a + " до " + b + ": ");
                    for (int i : list)
                        System.out.print(i + " ");
                }
                System.out.println();
                break;
            case 6:
                result_d = sqrt(a);
                System.out.println("Корень из числа " + a + " = " + result_d);
                break;
            case 11:// простые числа
                b = reader2number();
                for (int i = (int) a; i <= b; i++)
                {
                    int sum_divider = 1;
                    for (int j = 2; j < i; j++)
                    {
                        if (i % j == 0)
                        {
                            sum_divider = sum_divider + j;// если число делится нацело на какое-то число,
                            // то мы суммируем это число с другими делителями
                        }
                    }
                    if (sum_divider == 1)
                    {// если наше число совершенно, то добавляем его в список совершенных чисел
                        list.add(i);
                    }
                }
                if (list.size() == 0) System.out.println("Простых чисел в интервале от " + a + " до " + b + " нету.");
                else
                {
                    System.out.print("простые числа в интервале от " + a + " до " + b + ": ");
                    if (list.get(0) == 0) list.remove(0);
                    for (int i : list)
                        System.out.print(i + " ");
                }
                System.out.println();
                break;
            default:
                System.out.println("Неправильный выбор операции");
                break;
        }
    }

}


