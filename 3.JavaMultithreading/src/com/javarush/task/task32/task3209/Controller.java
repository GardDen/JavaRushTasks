package com.javarush.task.task32.task3209;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

/**
 * Created by 1 on 19.12.2017.
 */
public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    /**
     * THe method is responsible for initializing a object
     * Контроллер обновляется при создании нового документа.
     */
    public void init() {
        this.createNewDocument();
    }

    public void exit() {
        System.exit(0);
    }

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }

    /**
     *  будет сбрасывать текущий документ.
     */
    public void resetDocument() {
        if (document != null) {
            document.removeUndoableEditListener(view.getUndoListener());
        }
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        document = (HTMLDocument)htmlEditorKit.createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }

    /**
     * Метод записывает переданный текст с html тегами в документ document.
     * @param text текст с html тегами
     */
    public void setPlainText(String text) {
        //16.1. Сбрось документ.
        this.resetDocument();
        //16.2. Создай новый реадер StringReader на базе переданного текста.
        StringReader stringReader = new StringReader(text);
        //16.3. Вызови метод read() из класса HTMLEditorKit, который вычитает данные из реадера в document.
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        //16.4. Проследи, чтобы метод не кидал исключения. Их необходимо просто логировать.
        try {
            htmlEditorKit.read(stringReader, document, 0);
        } catch (BadLocationException exc) {
            ExceptionHandler.log(exc);
        } catch (IOException exc) {
            ExceptionHandler.log(exc);
        }
    }

    /**
     * Он должен получать текст из документа со всеми html тегами.
     * @return
     */
    public String getPlainText() {
        //17.1. Создай объект StringWriter.
        StringWriter stringWriter = new StringWriter();
        //17.2. Перепиши все содержимое из документа document в созданный объект с помощью write класса HTMLEditorKit.
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        //17.3. Как обычно, метод не должен кидать исключений.
        try {
            htmlEditorKit.write(stringWriter, document, 0, document.getLength());
        } catch (BadLocationException exc) {
            ExceptionHandler.log(exc);
        } catch (IOException exc) {
            ExceptionHandler.log(exc);
        }
        return stringWriter.toString();
    }

    /**
     * Plug
     */
    public void createNewDocument() {
        //20.1.1. Выбирать html вкладку у представления.
        view.selectHtmlTab();
        //20.1.2. Сбрасывать текущий документ.
        this.resetDocument();
        //20.1.3. Устанавливать новый заголовок окна, например: "HTML редактор". Воспользуйся методом setTitle(),
        // который унаследован в нашем представлении.
        view.setTitle("HTML редактор");
        //20.1.4. Сбрасывать правки в Undo менеджере. Используй метод resetUndo представления.
        view.resetUndo();
        //20.1.5. Обнулить переменную currentFile.
        currentFile = null;
    }

    public HTMLDocument getDocument() {
        return document;
    }

    public void openDocument() {
        //23.2. Метод должен работать аналогично методу saveDocumentAs(), в той части, которая отвечает за выбор файла.
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        //22.3. Устанавливать ему в качестве фильтра объект HTMLFileFilter.
        jFileChooser.setFileFilter(new HTMLFileFilter());
        //Подсказка: Обрати внимание на имя метода для показа диалогового окна.
        int returnVal = jFileChooser.showOpenDialog(view);
        //Когда файл выбран, необходимо:
        //23.2.1. Установить новое значение currentFile.
        if (returnVal == jFileChooser.APPROVE_OPTION) {
            currentFile = jFileChooser.getSelectedFile();
            //23.2.2. Сбросить документ.
            resetDocument();
            //23.2.3. Установить имя файла в заголовок у представления.
            view.setTitle(currentFile.getName());
            //23.2.4. Создать FileReader, используя currentFile.
            try {
                FileReader fileReader = new FileReader(currentFile);
                //23.2.5. Вычитать данные из FileReader-а в документ document с помощью объекта класса HTMLEditorKit.
                new HTMLEditorKit().read(fileReader, document, 0);
                //23.2.6. Сбросить правки (вызвать метод resetUndo представления).
                view.resetUndo();
                //23.2.7. Если возникнут исключения - залогируй их и не пробрасывай наружу.
            } catch (FileNotFoundException e) {
                ExceptionHandler.log(e);
            } catch (BadLocationException e) {
                ExceptionHandler.log(e);
            } catch (IOException e) {
                ExceptionHandler.log(e);
            }
        }


    }

    /**
     * 23.1. Напишем метод для сохранения открытого файла saveDocument().
     * Метод должен работать также, как saveDocumentAs(), но не запрашивать файл у пользователя,
     * а использовать currentFile. Если currentFile равен null, то вызывать метод saveDocumentAs().
     */
    public void saveDocument() {
        if (currentFile == null) saveDocumentAs();
        else {
            view.selectHtmlTab();
            view.setTitle(currentFile.getName());
            try {
                FileWriter fileWriter = new FileWriter(currentFile);
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocumentAs() {
            //22.1. Переключать представление на html вкладку.
            view.selectHtmlTab();
            //22.2. Создавать новый объект для выбора файла JFileChooser.
            JFileChooser jFileChooser = new JFileChooser();
            //22.3. Устанавливать ему в качестве фильтра объект HTMLFileFilter.
            jFileChooser.setFileFilter(new HTMLFileFilter());
            //22.4. Показывать диалоговое окно "Save File" для выбора файла.
            int returnVal = jFileChooser.showSaveDialog(view);
            //22.5. Если пользователь подтвердит выбор файла:
            //22.5.1. Сохранять выбранный файл в поле currentFile.
            if (returnVal == jFileChooser.APPROVE_OPTION) {
                currentFile = jFileChooser.getSelectedFile();
                //22.5.2. Устанавливать имя файла в качестве заголовка окна представления.
                view.setTitle(currentFile.getName());
                try {
                    //22.5.3. Создавать FileWriter на базе currentFile.
                    FileWriter fileWriter = new FileWriter(currentFile);
                    //22.5.4. Переписывать данные из документа document в объекта FileWriter-а аналогично тому,
                    // как мы это делали в методе getPlainText().
                    new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
                    //22.6. Метод не должен кидать исключения.
                    //Проверь работу пункта меню Сохранить как...
                } catch (IOException e) {
                    ExceptionHandler.log(e);
                } catch (BadLocationException e) {
                    ExceptionHandler.log(e);
                }
            }
    }

}
