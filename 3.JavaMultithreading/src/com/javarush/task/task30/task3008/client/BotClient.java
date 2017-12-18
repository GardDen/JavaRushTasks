package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by 1 on 12.07.2017.
 */
public class BotClient extends Client {

    public static void main(String[] args) throws IOException {
        BotClient botClient = new BotClient();
        botClient.run();
    }

    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    protected String getUserName() {
       /* int n = ;
        String number;
        if (n < 10){
            number = "0" + n;
        } else number = "" + n;*/
        return "date_bot_" + (int)(Math.random()*100) ;
    }



    public class BotSocketThread extends SocketThread {
        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (message.contains(": ")) {
                int n = message.indexOf(": ");
                String name = message.substring(0, n);
                String text = message.substring(n + 2);
                Date date = new GregorianCalendar().getTime();
                SimpleDateFormat simpleDateFormat;
                String answer = "";
                switch (text) {
                    case "дата":
                        simpleDateFormat = new SimpleDateFormat("d.MM.YYYY");
                        answer = simpleDateFormat.format(date);
                        break;
                    case "день":
                        simpleDateFormat = new SimpleDateFormat("d");
                        answer = simpleDateFormat.format(date);
                        break;
                    case "месяц":
                        simpleDateFormat = new SimpleDateFormat("MMMM");
                        answer = simpleDateFormat.format(date);
                        break;
                    case "год":
                        simpleDateFormat = new SimpleDateFormat("YYYY");
                        answer = simpleDateFormat.format(date);
                        break;
                    case "время":
                        simpleDateFormat = new SimpleDateFormat("H:mm:ss");
                        answer = simpleDateFormat.format(date);
                        break;
                    case "час":
                        simpleDateFormat = new SimpleDateFormat("H");
                        answer = simpleDateFormat.format(date);
                        break;
                    case "минуты":
                        simpleDateFormat = new SimpleDateFormat("m");
                        answer = simpleDateFormat.format(date);
                        break;
                    case "секунды":
                        simpleDateFormat = new SimpleDateFormat("s");
                        answer = simpleDateFormat.format(date);
                        break;
                }

                if (!answer.isEmpty()) {
                    sendTextMessage("Информация для " + name + ": " + answer);
                }
            }
        }


        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }
    }
}
