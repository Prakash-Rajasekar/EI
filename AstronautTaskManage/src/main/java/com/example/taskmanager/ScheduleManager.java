package com.example.taskmanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

public class ScheduleManager {
    private static ScheduleManager instance;
    private List<Task> tasks;

    private ScheduleManager() {
        tasks = new ArrayList<>();
    }

    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public void addTask(Task task) throws Exception {
        if (isConflicting(task)) {
            TaskObserver.notifyObservers("Error: Task conflicts with existing tasks.");
            throw new Exception("Task conflicts with existing tasks.");
        }
        tasks.add(task);
        TaskObserver.notifyObservers("Task added: " + task);
    }

    public void removeTaskByTitle(String title) throws Exception {
        Task taskToRemove = tasks.stream()
                                 .filter(task -> task.getTitle().equalsIgnoreCase(title))
                                 .findFirst()
                                 .orElseThrow(() -> new Exception("Task not found."));

        tasks.remove(taskToRemove);
        TaskObserver.notifyObservers("Task removed: " + title);
    }

    public void editTask(String oldTitle, Task newTask) throws Exception {
        Task taskToEdit = tasks.stream()
                               .filter(task -> task.getTitle().equalsIgnoreCase(oldTitle))
                               .findFirst()
                               .orElseThrow(() -> new Exception("Task not found."));

        if (isConflicting(newTask)) {
            TaskObserver.notifyObservers("Error: Task conflicts with existing tasks.");
            throw new Exception("Task conflicts with existing tasks.");
        }

        tasks.remove(taskToEdit);
        tasks.add(newTask);
        TaskObserver.notifyObservers("Task updated: " + newTask);
    }

    public void markTaskAsCompleted(String title) throws Exception {
        Task taskToMark = tasks.stream()
                               .filter(task -> task.getTitle().equalsIgnoreCase(title))
                               .findFirst()
                               .orElseThrow(() -> new Exception("Task not found."));

        taskToMark.setCompleted(true);
        TaskObserver.notifyObservers("Task marked as completed: " + title);
    }

    public List<Task> getTasksSortedByStartTime() {
        List<Task> sortedTasks = new ArrayList<>(tasks);
        sortedTasks.sort(Comparator.comparing(Task::getStartTime));
        return sortedTasks;
    }

    public List<Task> getTasksSortedByPriority() {
        List<Task> sortedTasks = new ArrayList<>(tasks);
        sortedTasks.sort(Comparator.comparingInt(Task::getPriority));
        return sortedTasks;
    }

    public List<Task> getTasksByStatus(boolean completed) {
        return tasks.stream()
                    .filter(task -> task.isCompleted() == completed)
                    .collect(Collectors.toList());
    }

    public List<Task> getTasksByPriority(int priority) {
        return tasks.stream()
                    .filter(task -> task.getPriority() == priority)
                    .collect(Collectors.toList());
    }

    private boolean isConflicting(Task newTask) {
        for (Task existingTask : tasks) {
            if (existingTask.getStartTime().compareTo(newTask.getEndTime()) < 0 &&
                existingTask.getEndTime().compareTo(newTask.getStartTime()) > 0) {
                return true;
            }
        }
        return false;
    }

    public void saveTasksToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tasks.dat"))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public void loadTasksFromFile() {
        File file = new File("tasks.dat");
        if (!file.exists()) {
            return; // No tasks to load
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tasks.dat"))) {
            tasks = (List<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }
}
