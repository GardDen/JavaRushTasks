package com.javarush.task.task36.task3608.view;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.controller.Controller;
import com.javarush.task.task36.task3608.model.ModelData;

/**
 * Created by 1 on 14.10.2017.
 */
public class EditUserView implements View {
    private Controller controller;

    @Override
    public void refresh(ModelData modelData) {
        System.out.println("User to be edited:");
        User user = modelData.getActiveUser();
        System.out.println("\t" + user.toString());
        System.out.println("===================================================");
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * эмулировать событие клиента.
     */
    public void fireEventShowAllUsers() {
        controller.onShowAllUsers();
    }

    /**
     * Эмулирует событие удаленных клиентов
     */
    public void fireEventShowDeletedUsers() {
        controller.onShowAllDeletedUsers();
    }

    public void fireEventUserDeleted(long id) {
        controller.onUserDelete(id);
    }

    public void fireEventUserChanged(String name, long id, int level){
        controller.onUserChange(name, id, level);
    }
}

