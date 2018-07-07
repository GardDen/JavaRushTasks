package Archiver;

import Archiver.exception.WrongZipFileException;

import java.io.IOException;

/**
 * Доработка:
 * 1. Графический интерфейс
 * 2. Добавление не файлов, а целой папки
 * 3. Удаление не файла а целой папки(возможно уже работает)
 */
public class Archiver {
    /**
     * База.
     * @param args пока пусто
     * @throws IOException ошибка ввода-вывода
     */
    public static void main(String[] args) throws IOException {

        Operation operation = null;
        do {
            try {
                operation = askOperation();
                CommandExecutor.execute(operation);
            } catch (WrongZipFileException exc) {
                ConsoleHelper.writeMessage("Вы не выбрали файл архива или выбрали неверный файл.");
            } catch (Exception exc) {
                ConsoleHelper.writeMessage("Произошла ошибка. Проверьте введенные данные.");
                exc.printStackTrace();
            }

        }
        while (operation != Operation.EXIT);
    }

    /**
     * Метож заапрашивает номер операции и возращает значение.
     * @return Operation
     * @throws IOException ошибка ввода вывода
     */
    public static Operation askOperation() throws IOException {
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("Выберите операцию:");
        ConsoleHelper.writeMessage(String.format("\t %d - упаковать файлы в архив",
                Operation.CREATE.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - добавить файл в архив",
                Operation.ADD.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - удалить файл из архива",
                Operation.REMOVE.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - распаковать архив",
                Operation.EXTRACT.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - просмотреть содержимое архива",
                Operation.CONTENT.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - выход",
                Operation.EXIT.ordinal()));

        return Operation.values()[ConsoleHelper.readInt()];
    }
}