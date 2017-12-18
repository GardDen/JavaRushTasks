package com.my_project.epam;
import java.util.*;
/**
 * Created by 1 on 01.11.2016.
 * ЗАДАЧА В ЕПАМ НА ОСЕНЬ 2015

 Условие задачи

 Имеется набор емкостей, в которые заливают жидкости. В одну емкость можно залить только одну жидкость, т.е. смешивать жидкости запрещается. Емкость заполняется на 95% от максимального объема.

 Описание емкостей:

 – стенки емкостей прямые или наклонные с одинаковым углом наклона во всех направлениях;

 – у наклонных емкостей основание меньшей площади всегда находится внизу;

 – в основании прямых емкостей могут быть следующие фигуры: квадрат, прямоугольник, равнобедренная трапеция, правильный шестиугольник, круг, овал (эллипс);

 – в основании наклонных емкостей могут быть следующие фигуры: квадрат, равнобедренная трапеция, правильный шестиугольник, круг;

 – высота у всех емкостей одинаковая.

 Список имеющихся жидкостей (в скобках указана плотность, кг/м3): бензин (740), керосин (820), машинное масло (910).

 Создать консольное приложение, в котором последовательно выполнить следующие задания:

 – залить произвольным образом жидкости в набор емкостей (не менее 10);

 – отсортировать емкости по убыванию массы залитой в них жидкости;

 – вывести на консоль в табличном виде (можно без границ) набор емкостей (полный состав атрибутов) и их содержимого;

 – найти наименьшую массу бензина, залитого в емкость.

 Требования:

 – Использовать объектно-ориентированный подход для описания сущностей предметной области.

 – Набор емкостей инициализировать в коде с помощью конструктора или метода. Как следствие, не использовать внешние источники данных: консоль (т.е. ввод с клавиатуры), файлы, СУБД и т.п.

 – Инициализацию выполнить без датчика случайных чисел. Передавать в конструктор константные значения. Например, залить керосин в прямую емкость, в основании которой прямоугольник со сторонами 45 см и 30 см.

 – Приложение должно быть консольным. Не использовать графический интерфейс! Таким образом, приложение ничего не должно вводить, а только выводить результаты на консоль.

 Предпочтения по выбору:

 – языка программирования: 1) Java; 2) C++; 3) другой ООП язык.

 – реализации сортировки и поиска: 1) интерфейс внешних библиотек (н-р, метод sort() подходящего класса); 2) собственный код.

 Формульный ликбез:

 mass = volume * density

 стенки емкостей
 volume =

 прямые
 baseSquare * height

 наклонные
 (baseSquare1 + sqrt(baseSquare1 * baseSquare2) + baseSquare2) * height / 3

 основание
 square =

 квадрат
 a * a

 прямоугольник
 a * b

 трапеция
 (a + b) * h / 2

 правильный шестиугольник
 a * a * sqrt(27) / 2

 круг
 PI * a * a

 овал (эллипс)
 PI * a * b
 Эта не сложная. Я её решил. Все работало но сказали решение слабенькое.
 */
public class Epam_2015
{
    final static double HEIGHT = 10.0;
    //final static double ANGLE_WALLS = 45;//in degree
    final static int WIDTH_COLOMN = 13;
    final static  double HEIGHT_TRAPECIA = 10;

    public static void main(String[] args)
    {
        //1 create Capacity(String numberCapacity, String densityType, String wallType, String baseType, double a, double b)
        Capacity capacity1 = new Capacity("1", "бензин", "наклонные", "квадрат", 0.1, 0);
        Capacity capacity2 = new Capacity("2", "керосин", "наклонные", "круг", 15.0, 0);
        Capacity capacity3 = new Capacity("3", "машин. масло", "прямые", "прямоугольник", 50.0, 15.0);
        Capacity capacity4 = new Capacity("4", "бензин", "наклонные", "круг", 15.0, 0);
        Capacity capacity5 = new Capacity("5", "бензин", "прямые", "овал", 1.0, 2.0);
        Capacity capacity6 = new Capacity("6", "бензин", "наклонные", "трапеция", 5.0, 15.0);
        Capacity capacity7 = new Capacity("7", "машин. масло", "наклонные", "шестиугольник", 15.0, 0);
        Capacity capacity8 = new Capacity("8", "керосин", "прямые", "круг", 15.0, 0);
        Capacity capacity9 = new Capacity("9", "машин. масло", "наклонные", "трапеция", 15.0, 15.0);
        Capacity capacity10 = new Capacity("10", "бензин", "наклонные", "прямоугольник", 13.564, 1.303);

        Capacity[] listCapacity = new Capacity[]{capacity1, capacity2, capacity3, capacity4, capacity5, capacity6,
                capacity7, capacity8, capacity9, capacity10};

        printTable(listCapacity);
        sortCapacity(listCapacity);
        printTable(listCapacity);
        System.out.println("Наименьшая масса бензина, залитого в емкость: " +
                listCapacity[listCapacity.length - 1].getMass());// неправильно работает
    }

    public static class Capacity{
        String numberCapacity;
        double mass, volume, a, b, square;
        int density;
        String densityType, baseType, wallType;//тип основания
        double baseSquareBot;//основание
        double baseSquareTop;

        Capacity(String nomberCapacity, String densityType, String wallType, String baseType, double a, double b)
        {
            this.numberCapacity = nomberCapacity;
            this.densityType = densityType;
            this.wallType = wallType;
            this.baseType = baseType;
            this.a = a;
            this.b = b;

            Map<String, Integer> mapDensity =  new HashMap<String, Integer>();
            mapDensity.put("бензин", 740);
            mapDensity.put("керосин", 820);
            mapDensity.put("машин. масло", 910);

            square = getSquare();
            baseSquareBot = square;
            baseSquareTop = square/4;
            volume = getVolume();
            density = mapDensity.get(densityType);
            mass = 0.95 * volume * density;
        }

        double getSquare(){
            switch (baseType){
                case "квадрат":
                    square = a * a;
                    break;
                case "прямоугольник":
                    square = a * b;
                    break;
                case "трапеция":
                    square = (a + b) * HEIGHT_TRAPECIA / 2;
                    break;
                case "шестиугольник":
                    square = a * a * Math.sqrt(27) / 2;
                    break;
                case "круг":
                    square = Math.PI * a * a;
                    break;
                case "овал":
                    square = Math.PI * a * b;
                    break;
            }
            return square;
        }

        double getVolume(){
            //wall is direct;
            if (wallType.equals("прямые")) volume = baseSquareBot * HEIGHT;
            // wall is inclined = is notDirect
            else volume = (baseSquareBot + Math.sqrt(baseSquareBot * baseSquareTop) + baseSquareTop) * HEIGHT / 3;
            return volume;
        }

        double getMass(){
            return mass;
        }

        String alignColomn(String addText){
            if (addText.length() <  WIDTH_COLOMN)
                for(int i = 0; addText.length() < WIDTH_COLOMN; i++) addText+=" ";
            // else - обрезание чисел
            return  addText;
        }

        public String toString(){
            String[] list = new String[]{this.numberCapacity, this.densityType, this.wallType, "" + HEIGHT, baseType, "" + this.a, "" + this.b};
            String text = "";
            for (int i = 0; i < list.length; i++)
                text += alignColomn(list[i]) + "|";
            text += this.mass;
            return text;
        }
    }

    public static void printTable(Capacity[] listCapacity){
        System.out.println("Номер емкости|Тип жидкости |Тип стенок   |Высота       |Тип основания|a            |b            |Масса");
        for (int i = 0; i < listCapacity.length; i++) System.out.println(listCapacity[i]);
    }

    public static void sortCapacity(Capacity[] listCapacity){
        System.out.println("Sorting!");
        Arrays.sort(listCapacity, new Comparator<Capacity>() {
            public int compare(Capacity e1, Capacity e2) {
                return Double.compare(e2.getMass(), e1.getMass());
            }
        });
    }
}