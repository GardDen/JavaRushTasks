package com.javarush.task.task36.task3608.model;

/**
 * Created by 1 on 13.10.2017.
 */
public interface Model {
    ModelData getModelData();

    void loadUsers();

    void loadDeletedUsers();

    public void loadUserById(long userId);

    void deleteUserById(long id);

    void changeUserData(String name, long id, int level);
}
