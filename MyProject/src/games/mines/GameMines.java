package games.mines;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.Timer;

/**
 * Created by 1 on 26.10.2016. основной код игры + таймер написаны за 3 дня
 *
 * Модификация
 * 1. Убрать возможность ставить флаг после окончания игры
 * 2. Добавить надпись Вы победили / Вы проиграли
 * 3. Добавить кнопки: Новая игра, пауза, рекорды
 * 4. Добавить возможность изменять размер поля и количество бомб
 */

public class GameMines extends JFrame
{
    final String TITLE_OF_PROGRAM = "Сапер";
    final String SIGN_OF_FLAG = "f";
    final int BLOCK_SIZE = 30;
    final int FIELD_SIZE = 30;
    final int FIELD_DX = 6;
    final int FIELD_DY = 28 + 17;
    final int START_LOCATION = 200;
    final int MOUSE_BUTTON_LEFT = 1;
    final int MOUSE_BUTTON_RIGHT = 3;
    final int NUMBERS_OF_MINES = 80;
    final int[] COLOR_OF_NUMBERS = {0x0000FF, 0x008000, 0xFF0000, 0x800000, 0x0};
    Cell[][] field = new Cell[FIELD_SIZE][FIELD_SIZE];
    Random random = new Random();
    int countOpenedCells;// количество открытых ячеек
    boolean youWon, bangMine;
    int bangX, bangY;

    public static void main(String[] args){
        new GameMines();
    }

    GameMines(){
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_LOCATION, START_LOCATION, FIELD_SIZE*BLOCK_SIZE + FIELD_DX, FIELD_SIZE*BLOCK_SIZE + FIELD_DY);
        setResizable(false);
        final TimerLabel timeLabel = new TimerLabel();//создание метки с таймером
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);// выравнивание по горизонтали
        final Canvas canvas = new Canvas();
        canvas.setBackground(Color.green);
        canvas.addMouseListener(new MouseAdapter()
        {
            @Override// переопределяем метот маус релейз
            public void mouseReleased(MouseEvent e)
            {
                super.mouseReleased(e);
                int x = e.getX() / BLOCK_SIZE;
                int y = e.getY() / BLOCK_SIZE;
                if (e.getButton() == MOUSE_BUTTON_LEFT && !bangMine && !youWon)//ЛКМ - мышь
                    if (field[y][x].isNotOpen())
                    {
                        openCells(x, y);
                        field[y][x].open();
                        youWon = countOpenedCells == FIELD_SIZE * FIELD_SIZE - NUMBERS_OF_MINES;// победа
                        if (bangMine){
                            bangX = x;
                            bangY = y;
                        }
                    }
                if (e.getButton() == MOUSE_BUTTON_RIGHT) field[y][x].inverseFlag();//ПКМ
                if(bangMine || youWon) timeLabel.stopTimer();// конец игры - остановка таймера
                canvas.repaint();

            };
        });
        add(BorderLayout.CENTER, canvas);//
        add(BorderLayout.SOUTH, timeLabel);// paint timer
        setVisible(true);//делает окно видимым
        initField();//инициализация поля
    };
    //рекурсивный метод - метод который вызывает сам себя, выполняет циклическое открытие пустых клеток
    void openCells(int x, int y){
        if (x < 0 || x > FIELD_SIZE - 1 || y < 0 || y > FIELD_SIZE - 1) return; //wrong coordinates
        if (!field[y][x].isNotOpen()) return;//the cell is already open
        field[y][x].open();
        if(field[y][x].getCountBomb() > 0 || bangMine) return;// если ячейка не пуста
        //если все проверки успешно пройдены, то ячейка пустая и выполняется открытие других пустых ячеек вокруг
        else{
            for (int dx = -1; dx < 2; dx++)
                for (int dy = -1; dy < 2; dy++) openCells(x + dx, x + dy);
        }
    }

    void initField(){// инициализация игрового поля
        int x, y, countMines = 0;
        for (x = 0; x < FIELD_SIZE; x++)//заполняем поле объектами - клетками
            for (y = 0; y < FIELD_SIZE; y++)
                field[y][x] = new Cell();
        while (countMines < NUMBERS_OF_MINES){//ставим мины на поле
            do{
                x = random.nextInt(FIELD_SIZE);
                y = random.nextInt(FIELD_SIZE);
            } while (field[y][x].isMined());
            field[y][x].mine();
            countMines++;
        }

        for(x = 0; x < FIELD_SIZE; x++)//  счетчик мин вокруг ячейки,
            for (y = 0; y < FIELD_SIZE;y++ )
                if(!field[y][x].isMined()){
                    int count = 0;
                    for(int dx = -1; dx < 2; dx++)
                        for (int dy = -1; dy < 2; dy++){
                            int nX = x + dx;
                            int nY = y + dy;
                            if (nX < 0 || nY < 0 || nX > FIELD_SIZE - 1 || nY > FIELD_SIZE - 1){
                                nX = x;
                                nY = y;
                            }
                            count+= (field[nY][nX].isMined() ? 1 : 0);
                        }
                    field[y][x].setCountBomb(count);
                }
    }

    class Cell{
        private boolean isOpen, isMine, isFlag;
        private int countBombNear;
        void open(){
            isOpen = true;
            bangMine = isMine; //
            if(!isMine) countOpenedCells++;
        }
        void mine(){isMine = true;}

        void setCountBomb(int count) {countBombNear = count; }// устанавливает количество бомб

        int getCountBomb() { return countBombNear; }

        boolean isNotOpen() { return !isOpen; }

        boolean isMined(){ return isMine; }

        void inverseFlag(){ isFlag = !isFlag; }

        void paintBomp(Graphics g, int x, int y, Color color){//рисует бомбу
            g.setColor(color);
            g.fillRect(x*BLOCK_SIZE + 7, y*BLOCK_SIZE + 10, 18, 10);
            g.fillRect(x*BLOCK_SIZE + 11, y*BLOCK_SIZE + 6, 10, 18);
            g.fillRect(x*BLOCK_SIZE + 9, y*BLOCK_SIZE + 8, 14, 14);
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
                if ((bangMine || youWon)&& isMine) paintBomp(g, x, y, Color.black);//прорисовывает все бомбы в конце игры
                else {
                    g.setColor(Color.lightGray);// начальный цвет полей
                    g.fill3DRect(x*BLOCK_SIZE, y*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, true);
                    if(isFlag) paintString(g, SIGN_OF_FLAG, x, y, Color.red);// рисует флаг
                }
            } else
                if (isMine) paintBomp(g, x, y, bangMine? Color.red : Color.black);// рисует бомбу при взрыве
                else
                    if (countBombNear > 0)
                        paintString(g, Integer.toString(countBombNear), x, y, new Color(COLOR_OF_NUMBERS[
                                countBombNear -1]));// рисует количество бомб вокруг, определенного цвета.
        }
    }

    class TimerLabel extends JLabel{
        Timer timer = new Timer();

        TimerLabel(){
            timer.scheduleAtFixedRate(timerTask, 0, 1000);
        }

        TimerTask timerTask = new TimerTask(){
            volatile int time;
            Runnable refresher = new Runnable()
            {
                public  void run(){
                    TimerLabel.this.setText(String.format("%02d:%02d", time / 60, time % 60 ));
                }
            };
            public void run(){
                time++;
                SwingUtilities.invokeLater(refresher);
            }
        };
        void stopTimer(){
            timer.cancel();
        }
    }

    class Canvas extends JPanel
    {
        //переопределяем метод paint он теперь будет изменять field
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (int x = 0; x < FIELD_SIZE; x++){
                for (int y = 0; y < FIELD_SIZE; y++) field[y][x].paint(g, x, y);
            }
        }
    }
}
