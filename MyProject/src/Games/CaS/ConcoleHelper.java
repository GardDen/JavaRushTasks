package Games.CaS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



/**
 * Created by 1 on 13.11.2017.
 */
public class ConcoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            writeMessage("Неверный ввод строки");
            return readString();
        }
    }

    public static String readString(String text) {
        writeMessage(text);
        return readString();
    }

    public static int readNumber(String message) {
        writeMessage(message);
        return readNumber();
    }

    public static int readNumber() {
        int number;
        try {
            number = Integer.parseInt(readString());
        } catch (NumberFormatException exc) {
            writeMessage("Вы ввели не число, введите число:");
            return readNumber();
        }
        return number;
    }

    /**
     * Считывает число - позицию
     * @return
     */
    public static int readNumberTile() {
        int numberTile = readNumber();
        if (numberTile > 0 & numberTile < 10) {
            return numberTile;
        } else {
            writeMessage("Неверный код ячейки.\n" +
                    "Ячейки обозначены цифрами, как в правой части клавиатуры.\n" +
                    "Повторите выбор позиции.");
            return readNumberTile();
        }
    }




}
