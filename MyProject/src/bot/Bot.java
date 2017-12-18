package bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by 1 on 19.03.2017.
 * Данная программа представляет собой искуственный интелект. Ну что ж попробуем его создать!)
 */
class Bot {
    final static int RATIO = 1000;
    /** Основная часть программы (клиентская часть).*/
    public static void main(String[] args) {
        startBot();
    }

    public static void startBot(){
        II ii = II.getII();
        delay(1);
        if (ii.name == null) {
            print("Привет, Человек!");
            delay(2);
            print("Я - искуственный интелект.");
            delay(1);
            print("Я нахожусь в спящем режиме, для моего запуска задайте мне имя.");
            ii.name = read();
        } else {
            System.out.printf("\nОтлично придумано! Теперь моё имя %s.\n",ii.name);
            delay(1);
            print("С тобой так интересно. Расскажи ещё что-нибудь.");
            delay(1);
            read();
        }
        startBot();
    }

    public static String read() {
        System.out.print("\n" + "Введите данные: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Неверный ввод данных, попробуйте ещё раз.");
            return read();
        }
    }

    public static void delay(double second){
        try {
            Thread.sleep((long) (second * Bot.RATIO));
        } catch (InterruptedException e) {
            System.out.println("Error. Metod sleep.");
        }
    }

    public static void print(String text){
        char listSimbols[] = text.toCharArray();
        for (int i = 0; i < text.length(); i++){
            System.out.print(listSimbols[i]);
            delay(0.1);
        }
        System.out.println();
    }

}
