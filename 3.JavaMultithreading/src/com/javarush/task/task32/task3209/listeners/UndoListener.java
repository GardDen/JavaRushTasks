package com.javarush.task.task32.task3209.listeners;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

/**
 * Created by 1 on 20.12.2017.
 * UndoManager:
 * UndoManagerподдерживает упорядоченный список изменений и индекс следующего редактирования в этом списке
 * Индекс следующего редактирования - это либо размер текущего списка изменений, либо если undoон был вызван,
 * он соответствует индексу последнего значимого редактирования, которое было отменено.
 * UndoManagerуправляет списком UndoableEdits, предоставляя способ отменить или повторить соответствующие изменения.
 * Есть два способа добавить изменения в UndoManager. Добавьте редактирование напрямую с помощью addEditметода или
 * добавьте в UndoManager bean-компонент, который поддерживает UndoableEditListener.
 *
 */
public class UndoListener implements UndoableEditListener {
    private UndoManager undoManager;

    public UndoListener(UndoManager undoManager) {
        this.undoManager = undoManager;
    }

    @Override
    public void undoableEditHappened(UndoableEditEvent e) {
        undoManager.addEdit(e.getEdit());
    }
}
