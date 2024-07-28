package com.example.taskmanager;

import java.util.ArrayList;
import java.util.List;

public class TaskObserver {
    private static final List<String> notifications = new ArrayList<>();

    public static void notifyObservers(String message) {
        notifications.add(message);
        // In a real application, you might notify registered observers here.
        System.out.println("Notification: " + message);
    }

    public static List<String> getNotifications() {
        return new ArrayList<>(notifications);
    }
}
