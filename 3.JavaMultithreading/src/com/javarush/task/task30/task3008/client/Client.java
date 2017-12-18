//12.1
package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import static com.javarush.task.task30.task3008.ConsoleHelper.readInt;
import static com.javarush.task.task30.task3008.ConsoleHelper.readString;
import static com.javarush.task.task30.task3008.ConsoleHelper.writeMessage;

/**
 * Created by 1 on 10.07.2017.
 */
//12.2
public class Client {

    //14,2
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.run();
    }

    //12.4
    protected Connection connection;
    //12.5 В дальнейшем оно будет устанавливаться в true, если клиент подсоединен к серверу или в false в противном случае.
    private volatile boolean clientConnected = false;

    //13.1
    protected String getServerAddress(){
        System.out.println("Введите адрес сервера:");
        return readString();
    }

    //13.2
    protected int getServerPort() {
        System.out.println("Введите номер порта:");
        try {
            return readInt();
        } catch (NumberFormatException exc) {
            System.out.println("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            return getServerPort();
        }
    }

    //13.3
    protected String getUserName() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите имя пользователя:");
        return readString();
    }

    //13.4
    protected boolean shouldSendTextFromConsole() {
        return true;
    }

    //13.5
    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    //13.6
    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException exc) {
            clientConnected = false;
        }
    }

    //14.1
    public void run() throws IOException {
        //14.1 a
        SocketThread socketThread = getSocketThread();

        //14.1 б Помечать созданный поток как daemon, это нужно для того, чтобы при выходе из программы вспомогательный поток прервался автоматически.
        socketThread.setDaemon(true);

        //14.1 в
        socketThread.start();

        //14.1 г

        try {
            synchronized (this) {
                this.wait();
            }
        } catch (InterruptedException exc) {
            System.out.println(exc.getMessage().toString());
            connection.close();
        }


        //14.1 д
        if (clientConnected == true) {
            writeMessage("Соединение установлено. Для выхода наберите команду ‘exit’.");
        } else {
            writeMessage("Произошла ошибка во время работы клиента.");
        }

        //14.1 е
        String data;
        while (clientConnected) {
            data = readString();
            if ("exit".equals(data)) {
                break;
            }

            //14.1 ж
            if (shouldSendTextFromConsole()) {
                sendTextMessage(data);
            }
        }
    }

    //12.3
    public class SocketThread extends Thread {
        protected void processIncomingMessage(String message) {
            writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName) {
            writeMessage(userName + " присоеденился к чату.");
        }

        protected void informAboutDeletingNewUser(String userName) {
            writeMessage(userName + " покинул чат.");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            //15.4 a
            Client.this.clientConnected = clientConnected;

            //15.4 б
            synchronized (Client.this) {
                Client.this.notify();
            }
        }

        /**
         * Этот метод будет представлять клиента серверу.
         * @throws IOException
         * @throws ClassNotFoundException
         */
        //16.1
        protected void clientHandshake() throws IOException, ClassNotFoundException {
            //16.1 a
            Message message;
            while (true) {
                message = connection.receive();
                //16.1 б
                if (message.getType() == MessageType.NAME_REQUEST) {
                    connection.send(new Message(MessageType.USER_NAME, getUserName()));
                } else
                //16.1 в
                if (message.getType() == MessageType.NAME_ACCEPTED) {
                    notifyConnectionStatusChanged(true);
                    break;
                } else {
                    //16.2 г
                    throw new IOException("Unexpected MessageType");
                }
            }

        }

        /**
         * главный цикл обработки сообщений сервера
         * @throws IOException
         * @throws ClassNotFoundException
         */
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            //16.2
            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) processIncomingMessage(message.getData());
                else if (message.getType() == MessageType.USER_ADDED) informAboutAddingNewUser(message.getData());
                else if (message.getType() == MessageType.USER_REMOVED) informAboutDeletingNewUser(message.getData());
                else throw new IOException("Unexpected MessageType");
            }

        }

        public void run() {

            try {
                Socket socket = new Socket(getServerAddress(), getServerPort());
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            } catch (IOException e) {
                notifyConnectionStatusChanged(false);
            } catch (ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }
        }
    }
}
