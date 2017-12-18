package com.javarush.task.task30.task3008;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by 1 on 03.07.2017.
 */
public class Server {

    //String nameUser, Connection - соединение с пользователем nameUser
    private static Map<String, Connection> connectionMap = new java.util.concurrent.ConcurrentHashMap<>();

    private static class Handler extends Thread{
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        /**
         * Medod start thread new user
         */
        public void run() {
            Connection connection = null;
            String userName = null;
            ConsoleHelper.writeMessage("Установлено соединение с удаленным клиентом с адресом: " +
                    socket.getRemoteSocketAddress());
            try {
                connection = new Connection(socket);
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);
            } catch (ClassNotFoundException exc) {
                try {
                    ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом.");
                    socket.close();
                } catch (IOException exception) {
                    //ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом.");
                }
            } catch (IOException exc) {
                try {
                    ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом.");
                    socket.close();
                } catch (IOException exception) {
                    //ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом.");

                }
            }

            if (userName != null) {
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
            }
            ConsoleHelper.writeMessage("Cоединение с удаленным адресом закрыто.");
        }

        /**
         * Metod realise Handshake for user and server, save a username
         * @param connection
         * @return
         * @throws IOException
         * @throws ClassNotFoundException
         */
        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            String nameUser = null;
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message = connection.receive();
                if (message.getType() == MessageType.USER_NAME) {
                    nameUser = message.getData();
                    if (!nameUser.isEmpty() && connectionMap.get(nameUser) == null) {
                        connectionMap.put(nameUser, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        break;
                    }
                }
            }
            return nameUser;
        }

        /**
         * Metod осуществляет отправку клиенту (новому участнику) информации об
         остальных клиентах (участниках) чата.
         * @param connection соединение с участником
         * @param userName его имя
         * @throws IOException
         */
        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> item : connectionMap.entrySet()) {
                if (!item.getKey().equals(userName)) {
                    connection.send(new Message(MessageType.USER_ADDED, item.getKey()));
                }
            }
        }

        /**
         * Обработка сообщений отклиентов сервером
         * сервер принимает сообщение и конвертирует его в сообщение вида:
         * userName: "dataMessage"
         * @param connection
         * @param userName
         * @throws IOException
         * @throws ClassNotFoundException
         */
        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (!Thread.currentThread().isInterrupted()) {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    Message convertingMessage = new Message(MessageType.TEXT, userName + ": " + message.getData());
                    sendBroadcastMessage(convertingMessage);
                } else {
                    System.out.println("Error! Неверное сообщение!");
                }
            }
        }
    }

    /**
     * Metod to send a message for all user
     * @param message
     */
    public static void sendBroadcastMessage(Message message) {
        for (Map.Entry<String, Connection> item : connectionMap.entrySet()) {
            try {
                item.getValue().send(message);
            } catch (IOException e) {
                System.out.println("Ошибка отправки сообщения клиенту");
                //Клиент " + item.getValue().toString() + " не получил сообщение от сервера: \"" + message.toString() + ""
            }
        }
    }

    /**
     * Основное тело программы
     * @param args - входящие параметры консоли
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ConsoleHelper consoleHelper = new ConsoleHelper();//Create console helper - через него идет обращение к консоли
        int numberPort = consoleHelper.readInt();//Запрос порта сервера - request port server
        ServerSocket serverSocket = new ServerSocket(numberPort);
        System.out.println("Сервер запущен.");
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            } catch (IOException exc) {
                serverSocket.close();
                System.out.println(exc.getMessage());
                //System.out.println("Ошибка соединения.");
                break;
            }
        }

    }
}
