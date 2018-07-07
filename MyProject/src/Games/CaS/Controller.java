package Games.CaS;

import Games.CaS.players.Bot;
import Games.CaS.players.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Games.CaS.ConcoleHelper.*;
import static Games.CaS.Model.listPlayers;
import static Games.CaS.Model.saveList;

/**
 * Created by 1 on 12.11.2017.
 */
public class Controller {
    private static boolean isExit = false;
    private Player player1 = new Bot("bot#1");
    private Player player2 = new Bot("bot#2");

    private static String language = "english";


    private Model model;
    private View view;

    public Controller(Model model) {
        this.model = model;
        view = new View(this);
    }

    public void menu() {
        while (!isExit) {
            printMenu(language);
            //Выбор пункта меню
            switch (readString("Введите порядковый номер строки:")) {
                case "1":
                    newGame();
                    break;
                case "2":
                    leaderBoards();
                    break;
                case "3":
                    profiles();
                    break;
                case "4":
                    options();
                    break;
                case "5":
                    isExit = true;
                    saveList();
                    writeMessage("Выход из программы.");
                    System.exit(1);
                    break;
                default:
                    writeMessage("Неверный выбор");
            }
        }
    }

    /**
     * 0.
     * @param language
     */
    public void printMenu(String language) {
        writeMessage("\n.-=***\\ Crosses and Zeros /***=-.\n" +
                "X O X O X O X O X O X O X O X O X\n");
        for (Command command : Command.values()) {
            writeMessage("\t\t" + command.toSelectTheLanguage(language).toUpperCase());
        }
        writeMessage(" ");
    }

    /**
     * 1.
     * The method implements next action:
     * - pick who move first
     * - implents all move
     */
    public void newGame() {
        String answer = readString("Желаете поменять профиль? Y/N");
        List<String> listNo = new ArrayList<>(Arrays.asList("нет", "не", "н", "n", "no", "net", "not"));
        List<String> listYes = new ArrayList<>(Arrays.asList("да", "д", "y", "yes"));
        if (listNo.contains(answer.toLowerCase())) {
            writeMessage(player1.toString() + " против " + player2.toString());
            printField();
            //Рандомный вобор кто ходит первый
            Player playerTemp;
            int random = 0 + (int) (Math.random() * 2);
            if (random == 0) {
                playerTemp = player1;
                player1 = player2;
                player2 = playerTemp;
            }

            //Игровой процесс
            do {
                //view.repaint();
                player1.move(this, "X");
                if (!model.isGameOver(player1, player2)) {
                    player2.move(this, "O");
                } else break;
            } while (!model.isGameOver(player2, player1));
            writeMessage("Конец игры.");
            model.resetModel();
            saveList();
        }
        if (listYes.contains(answer.toLowerCase())) {
            profiles();
            newGame();
        } else if (!(player1.getType().equals("bot") && player2.getType().equals("bot"))){
            ConcoleHelper.writeMessage("Введен неверный ответ.");
        }
    }

    public void leaderBoards() {
        writeMessage(".-=***Список лидеров***=-.");
        if (!listPlayers.isEmpty()) {
            String s = String.format("%-10s  %s  %4s  %4s  %4s", "Игрок", "     ", "Loss", "Win", "%Win");
            writeMessage(s);
            for (Player player : listPlayers) {
                writeMessage(player.toString());
            }
        } else {
            writeMessage("Список пуст.");
        }
    }

    public void profiles() {
        writeMessage("Выбор профиля");
        player1 = model.selectPlayer();
        writeMessage("Профиль " + player1.getName() + " активирован.");
        player2 = model.selectSecondPlayer(player1);
        writeMessage("Профиль " + player2.getName() + " активирован.");
    }

    public void options() {
        writeMessage("Options:");
        writeMessage("Выбор языка:\n" +
                "1 русский\n" +
                "2 english\n" +
                "Выберите язык:");
        setLanguage();
        writeMessage("Выбран язык - " + language);
    }


    /**
     * 4.
     */
    public void setLanguage() {
        int numberItem = readNumber();
        switch (numberItem) {
            case 1:
                language = "русский";
                break;
            case 2:
                language = "english";
                break;
            default:
                writeMessage("Выбран неверный пункт. Повторите выбор языка");
                setLanguage();
                break;
        }
    }

    /**
     * Выводит в консоль поле - матрицу 3х3
     */
    public void printField() {
        for (int i = 0; i < model.getFieldWidth(); i++) {
            for (int j = 0; j < model.getFieldHeight(); j++) {
                System.out.print(" " + model.getField()[i][j] + " ");
            }
            writeMessage(" ");
        }
    }


    public Cell[][] getField() {
        return model.getField();
    }

    public Model getModel() {
        return model;
    }


}
