package com.my_project.calc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 1 on 14.11.2016.
 */
public class CalcApp extends JFrame
{
    //final variable
    final String TITLE_OF_PROGRAM = "Калькулятор";
    final int START_LOCATION_X = 1000; //point-coordinate begin start app
    final int START_LOCATION_Y = 200;
    final int FIELD_SIZE = 5;
    final int BLOCK_SIZE = 50;
    final int FIELD_DX = 0;// !!!это видимо отступы непонятно для чего это..
    final int FIELD_DY = 0;
    final int MOUSE_BUTTON_LEFT = 1;
    final int MOUSE_BUTTON_RIGHT = 3;

    Button[][] field = new Button[FIELD_SIZE][FIELD_SIZE];
    final String SIGN_OF_FLAG = "f";

    public static void main(String[] args)
    {
        new CalcApp();
    }
    //.exe
    CalcApp(){
        setTitle(TITLE_OF_PROGRAM);//!этот сетор находится в библиотеке JFrame, нужно от него унаследоваться чтобы заработало
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_LOCATION_X, START_LOCATION_Y, FIELD_SIZE*BLOCK_SIZE + FIELD_DX, FIELD_SIZE*BLOCK_SIZE + FIELD_DY);
        setResizable(false);
        final Canvas canvas = new Canvas();
        canvas.setBackground(Color.lightGray);//задний фон, наверное не пригодится..., хотя наверное им можно отрабатывать нажатие кнопок.
        canvas.addMouseListener(new MouseAdapter()
        {
            @Override// переопределяем метот маус релейз
            /** При нажатии ЛКМ на кнопку цифр нажимаются цифры
             * при нажатии кнопок опереций запоминается операция
             * потом опять цифра или операция если это возможно
             * и так пока не будет нажато равно.
             */
            public void mouseReleased(MouseEvent e)
            {
                super.mouseReleased(e);
                int x = e.getX() / BLOCK_SIZE;
                int y = e.getY() / BLOCK_SIZE;
                if (e.getButton() == MOUSE_BUTTON_LEFT)//ЛКМ - мышь
                {
                    pressButtons(x, y);// !!!переименовать в пресс селс

                    field[y][x].press();
                }
                if (e.getButton() == MOUSE_BUTTON_RIGHT) field[y][x].inverseFlag();//ПКМ
                canvas.repaint();
            };
        });
        add(BorderLayout.CENTER, canvas);//
        setVisible(true);//делает окно видимым
        //initField();//инициализация поля
    };

    void pressButtons(int x, int y){
        if (x < 0 || x > FIELD_SIZE - 1 || y < 0 || y > FIELD_SIZE - 1) return; //wrong coordinates
        field[y][x].press();
    }

    void initField(){// инициализация игрового поля
        int x, y;
        for (x = 0; x < FIELD_SIZE; x++)//заполняем поле объектами - клетками
            for (y = 0; y < FIELD_SIZE; y++)
                field[y][x] = new Button();
    }

    class Button{
       //метод действия что происходит после нажатия кнопки
        private boolean isOpen, isFlag;
        void press(){
            isOpen = true;
        }

        void inverseFlag(){ isFlag = !isFlag; }

        void paintBomp(Graphics g, int x, int y, Color color){//рисует бомбу
            g.setColor(color);
            g.fillRect(x*BLOCK_SIZE + 7, y*BLOCK_SIZE + 10, 18, 10);
            g.setColor(Color.white);
            g.fillRect(x*BLOCK_SIZE + 11, y*BLOCK_SIZE + 10, 4, 4);
        }

        //рисует строку в ячейке, чтобы в ней можно было ставить флаг
        void paintString(Graphics g, String str, int x, int y, Color color)
        {
            g.setColor(color);
            g.setFont(new Font("", Font.BOLD, BLOCK_SIZE));
            g.drawString(str, x * BLOCK_SIZE + 8, y * BLOCK_SIZE + 26);
        }

        void paint(Graphics g, int x, int y){
            g.setColor(Color.lightGray);
            g.drawRect(x*BLOCK_SIZE, y*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);// рисует сетку
            if (!isOpen){
                paintBomp(g, x, y, Color.black);//прорисовывает все бомбы в конце игры
                g.setColor(Color.lightGray);// начальный цвет полей
                g.fill3DRect(x*BLOCK_SIZE, y*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, true);
                if(isFlag) paintString(g, SIGN_OF_FLAG, x, y, Color.red);// рисует флаг

            }
        }
    }

    class Canvas extends JPanel
    {
        //переопределяем метод paint он теперь будет изменять field
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            //for (int x = 0; x < FIELD_SIZE; x++){
               // for (int y = 0; y < FIELD_SIZE; y++) field[y][x].paint(g, x, y);
            //}
        }
    }

}
