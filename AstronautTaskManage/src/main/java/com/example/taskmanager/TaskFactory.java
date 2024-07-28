package com.example.taskmanager;

public class TaskFactory {
    public static Task createTask(String title, String description, String startTimeStr, String endTimeStr, int priority) {
        return new Task(title, description, startTimeStr, endTimeStr, priority);
    }
}
