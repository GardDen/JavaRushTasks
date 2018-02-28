package Games.CaS;

import Games.CaS.players.Bot;
import Games.CaS.players.Human;
import Games.CaS.players.Player;

import java.io.*;
import java.util.LinkedHashSet;

import static Games.CaS.ConcoleHelper.readNumber;
import static Games.CaS.ConcoleHelper.readString;
import static Games.CaS.ConcoleHelper.writeMessage;
import static Games.CaS.players.Player.getPlayersByType;
import static Games.CaS.players.Player.printListPlayersByType;

/**
 * Created by 1 on 01.09.2017.
 */
public class Model {
    private static final String VALUE_INITIAL = ".";
    private static final int FIELD_WIDTH = 3;
    private static final int FIELD_HEIGHT = 3;
    private static final String FILE_NAME =
            "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\MyProject\\src\\Games\\CaS\\players\\ListPlayer.txt";
    public static LinkedHashSet<Player> listPlayers;
    static {
        listPlayers = loadList();
    }

    private Cell[][] field = new Cell[FIELD_WIDTH][FIELD_HEIGHT];

    public Model() {
        resetModel();
    }

    /**
     * The method reset field
     */
    public void resetModel() {
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_HEIGHT; j++) {
                field[i][j] = new Cell(i, j, VALUE_INITIAL);
            }
        }
    }

    /**
     * Метод проверяет закончена ли игра.
     * А также, если игра закончена, то изменяет данные игроков
     * @return true если игра окончена
     */
    public boolean isGameOver(Player playerWin, Player playerLoser) {
        boolean gameOver = false;
        int countNotEmpty = 0;//количество занятых клеток
        //цикл считает сколько клеток занято
        for (int i = 0; i < Model.FIELD_WIDTH; i++) {
            for (int j = 0; j < Model.FIELD_HEIGHT; j++) {
                if (field[i][j].notIsEmpty()) {
                    countNotEmpty++;
                }
            }
        }

        //если один из игроков выстроил линию, то чья - то победа
        if (isVictory()) {
            gameOver = true;
            System.out.println("Победил " + playerWin.getName() + ".");
            playerWin.setCountWin();
            playerLoser.setCountLoss();
        } else //Если победы нет, но и пустых клеток нету, то игра закончена в ничью, поражение обоим
            if (countNotEmpty == 9) {
                gameOver = true;
                System.out.println("Ничья");
                playerWin.setCountLoss();
                playerLoser.setCountLoss();
            }
        return gameOver;
    }

    /**
     * Проверяет выйграл ли кто нибудь - соблюдено ли условие "Три в ряд"
     * @return
     */
    public boolean isVictory() {
        for (int i = 0; i < Model.FIELD_WIDTH; i++) {
            if (field[i][0].equals(field[i][1])
                    & field[i][0].equals(field[i][2])
                    & !(field[i][0].getValue().equals(VALUE_INITIAL))) {
                return true;
            }
            if (field[0][i].equals(field[1][i])
                    & field[0][i].equals(field[2][i])
                    & !(field[0][i].getValue().equals(VALUE_INITIAL))) {
                return true;
            }
        }
        if (field[0][0].equals(field[1][1])
                & field[0][0].equals(field[2][2])
                & !(field[0][0].getValue().equals(VALUE_INITIAL))) {
            return true;
        }
        if (field[2][0].equals(field[1][1])
                & field[2][0].equals(field[0][2])
                & !(field[2][0].getValue().equals(VALUE_INITIAL))) {
            return true;
        }
        return false;
    }

    /**
     * The method implements select type player.
     * @return type players as text.
     */
    public String selectType() {
        String type;
        int numberType = readNumber("Выберите тип игрока:\n" +
                "1 Человек\n" +
                "2 Бот\n" +
                "Введите число:");
        switch (numberType) {
            case 1:
                type = "human";
                break;
            case 2:
                type = " bot ";
                break;
            default:
                writeMessage("Неверный быбор, попробуйте ещё раз.");
                type = selectType();
                break;
        }
        return type;
    }

    /**
     * Метод выбирает уже существующий профиль игрока или создаёт новый профиль.
     * @return
     */
    public Player selectPlayer(){
        String type = selectType();
        printListPlayersByType(type);
        String name = readString("Введите имя:");
        Player player;
        switch (type) {
            case " bot ":
                player = new Bot(name);
                break;
            case "human":
                player = new Human(name);
                break;
            default:
                player = new Bot(name);
                break;
        }
        if (listPlayers.add(player)) {
            writeMessage("Профиль создан.");
        } else {
            for(Player item : getPlayersByType(type)) {
                if (item.getName().equals(name)){
                    player = item;
                    break;
                }
            }
        }
        return player;
    }

    public Player selectSecondPlayer(Player firstPlayer) {
        Player secondPlayer = selectPlayer();
        if (secondPlayer.equals(firstPlayer)) {
            writeMessage("Профиль " + firstPlayer.getName()
                    + " уже задействован. Пожалуйста, выберите другой профиль.");
            secondPlayer = selectSecondPlayer(firstPlayer);
        }
        return secondPlayer;
    }

    /**
     * The method save list players.
     */
    public synchronized static void saveList() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME)))
        {
            oos.writeObject(listPlayers);
            oos.flush();
            writeMessage("Сохранение.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method load list players from file.
     * If file is empty method create new empty list.
     * @return list players.
     */
    public synchronized static LinkedHashSet loadList() {
        File file = new File(FILE_NAME);
        if (file.exists() && !(file.length() == 0)) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                return (LinkedHashSet<Player>) ois.readObject();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return new LinkedHashSet();
    }

    public Cell[][] getField() {
        return field;
    }

    public int getFieldWidth() {
        return FIELD_WIDTH;
    }

    public int getFieldHeight() {
        return FIELD_HEIGHT;
    }

    public static String getValueInitial() {
        return VALUE_INITIAL;
    }
}
