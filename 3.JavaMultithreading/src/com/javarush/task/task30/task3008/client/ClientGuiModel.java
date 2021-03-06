package com.javarush.task.task30.task3008.client;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 1 on 14.07.2017.
 */
public class ClientGuiModel {
    private final Set<String> allUserNames = new HashSet<>();
    private String newMessage;

    public Set<String> getAllUserNames() {
        Set<String> set = Collections.unmodifiableSet(allUserNames);
        return set;
    }

    public String getNewMessage() {
        return newMessage;
    }

    public void setNewMessage(String newMessage) {
        this.newMessage = newMessage;
    }

    public void addUser(String userNme) {
        allUserNames.add(userNme);
    }

    public void deleteUser(String userNme) {
        allUserNames.remove(userNme);
    }
}
