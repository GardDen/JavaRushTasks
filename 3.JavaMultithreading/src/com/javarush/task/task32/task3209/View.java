package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.javarush.task.task32.task3209.ExceptionHandler.log;

/**
 * Created by 1 on 19.12.2017.
 */
public class View extends JFrame implements ActionListener {
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();//панель с двумя вкладками
    private JTextPane htmlTextPane = new JTextPane();//компонент для визуального редактирования html - первая вкладка
    /*компонент для редактирования html в виде текста, он будет
    отображать код html (теги и их содержимое)*/
    private JEditorPane plainTextPane = new JEditorPane();

    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            log(e);
        }
    }

    /**
     * будет вызваться при выборе пунктов меню, у которых наше
     * представление указано в виде слушателя событий.
     * @param e event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //19.1. Получи из события команду с помощью метода getActionCommand(). Это будет обычная строка.
        // По этой строке ты можешь понять какой пункт меню создал данное событие.
        String command = e.getActionCommand();
        //19.2. Если это команда "Новый", вызови у контроллера метод createNewDocument().
        // В этом пункте и далее, если необходимого метода в контроллере еще нет - создай заглушки.
        //19.3. Если это команда "Открыть", вызови метод openDocument().
        //19.4. Если "Сохранить", то вызови saveDocument().
        //19.5. Если "Сохранить как..." - saveDocumentAs().
        //19.6. Если "Выход" - exit().
        //19.7. Если "О программе", то вызови метод showAbout() у представления.
        //Проверь, что заработали пункты меню Выход и О программе.
        switch (command) {
            case "Новый":
                controller.createNewDocument();
                break;
            case "Открыть":
                controller.openDocument();
                break;
            case "Сохранить":
                controller.saveDocument();
                break;
            case "Сохранить как...":
                controller.saveDocumentAs();
                break;
            case "Выход":
                controller.exit();
                break;
            case "О программе":
                showAbout();
                break;
        }
    }

    /**
     * The method is responsible for initializing a GUI.
     */
    public void initGui() {
        initMenuBar();
        initEditor();
        pack();
    }

    /**
     * The method is responsible for initializing a menu.
     */
    public void initMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();//menu pane
        MenuHelper.initFileMenu(this, jMenuBar);
        MenuHelper.initEditMenu(this, jMenuBar);
        MenuHelper.initStyleMenu(this, jMenuBar);
        MenuHelper.initAlignMenu(this, jMenuBar);
        MenuHelper.initColorMenu(this, jMenuBar);
        MenuHelper.initFontMenu(this, jMenuBar);
        MenuHelper.initHelpMenu(this, jMenuBar);
        getContentPane().add(jMenuBar, BorderLayout.NORTH);
    }

    /**
     * The method is responsible for initializing a editor pane.
     */
    public void initEditor() {
        htmlTextPane.setContentType("text/html");
        JScrollPane paneHTML = new JScrollPane(htmlTextPane);
        tabbedPane.addTab("HTML", paneHTML);
        JScrollPane paneText = new JScrollPane(plainTextPane);
        tabbedPane.addTab("Текст", paneText);
        tabbedPane.setPreferredSize(null);
        TabbedPaneChangeListener tabbedPaneChangeListener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(tabbedPaneChangeListener);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    /**
     * Этот метод вызывается, когда произошла смена выбранной вкладки.
     */
    public void selectedTabChanged() {
        //18.1. Метод должен проверить, какая вкладка сейчас оказалась выбранной.
        if (tabbedPane.getSelectedIndex() == 0) {
            //18.2. Если выбрана вкладка с индексом 0 (html вкладка), значит нам нужно получить текст из plainTextPane
            // и установить его в контроллер с помощью метода setPlainText.
            controller.setPlainText(plainTextPane.getText());
        } else {
            //18.3. Если выбрана вкладка с индексом 1 (вкладка с html текстом), то необходимо получить текст у
            // контроллера с помощью метода getPlainText() и установить его в панель plainTextPane.
            plainTextPane.setText(controller.getPlainText());
        }
        //18.4. Сбросить правки (вызвать метод resetUndo представления).
        this.resetUndo();
    }

    /**
     * Выбирает вкладку html и сбрасывает все правки.
     */
    public void selectHtmlTab(){
        //this.getContentPane();
        tabbedPane.setSelectedIndex(0);
        this.resetUndo();
    }

    /**
     * Проверяет выбрана ли html вкладка.
     * @return  true, если выбрана вкладка, отображающая html в панели вкладок
     */
    public boolean isHtmlTabSelected(){
        return tabbedPane.getSelectedIndex() == 0;
    }

    public void update() {
        HTMLDocument document = controller.getDocument();
        htmlTextPane.setDocument(document);
    }

    public void showAbout() {
        JOptionPane.showMessageDialog(tabbedPane, "dfgdfg", "О программе", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * THe method is responsible for initializing a object
     */
    public void init() {
        initGui();
        FrameListener frameListener = new FrameListener(this);
        this.addWindowListener(frameListener);
        this.setVisible(true);
    }

    public boolean canUndo() {
        return undoManager.canUndo();
    }

    public boolean canRedo() {
        return undoManager.canRedo();
    }

    /**
     * отменяет последнее действие.
     */
    public void undo() {
        try {
            undoManager.undo();
        } catch (CannotUndoException e) {
            log(e);
        }
    }

    /**
     * возвращает ранее отмененное действие
     */
    public void redo() {
        try {
            undoManager.redo();
        } catch (CannotRedoException e) {
            log(e);
        }
    }

    /**
     * должен сбрасывать все правки в менеджере undoManager
     */
    public void resetUndo() {
        undoManager.discardAllEdits();
    }

    public void exit() {
        controller.exit();
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

}
