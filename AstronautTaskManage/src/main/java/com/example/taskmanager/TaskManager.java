package com.example.taskmanager;

import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private static final ScheduleManager scheduleManager = ScheduleManager.getInstance();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeTasks();
        while (true) {
            System.out.println("\nTask Manager Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. Edit Task");
            System.out.println("4. Mark Task as Completed");
            System.out.println("5. View All Tasks");
            System.out.println("6. View Completed Tasks");
            System.out.println("7. View Pending Tasks");
            System.out.println("8. View Tasks by Priority");
            System.out.println("9. View Tasks Sorted by Start Time");
            System.out.println("10. View Tasks Sorted by Priority");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    removeTask();
                    break;
                case 3:
                    editTask();
                    break;
                case 4:
                    markTaskAsCompleted();
                    break;
                case 5:
                    viewAllTasks();
                    break;
                case 6:
                    viewCompletedTasks();
                    break;
                case 7:
                    viewPendingTasks();
                    break;
                case 8:
                    viewTasksByPriority();
                    break;
                case 9:
                    viewTasksSortedByStartTime();
                    break;
                case 10:
                    viewTasksSortedByPriority();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    saveTasksOnExit();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addTask() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter start time (HH:mm): ");
        String startTime = scanner.nextLine();

        System.out.print("Enter end time (HH:mm): ");
        String endTime = scanner.nextLine();

        System.out.print("Enter priority (1-High, 2-Medium, 3-Low): ");
        int priority = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            Task task = new Task(title, description, startTime, endTime, priority);
            scheduleManager.addTask(task);
            System.out.println("Task added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding task: " + e.getMessage());
        }
    }

    private static void removeTask() {
        System.out.print("Enter the title of the task to remove: ");
        String title = scanner.nextLine();

        try {
            scheduleManager.removeTaskByTitle(title);
            System.out.println("Task removed successfully.");
        } catch (Exception e) {
            System.out.println("Error removing task: " + e.getMessage());
        }
    }

    private static void editTask() {
        System.out.print("Enter the title of the task to edit: ");
        String oldTitle = scanner.nextLine();

        System.out.print("Enter new title: ");
        String newTitle = scanner.nextLine();

        System.out.print("Enter new description: ");
        String newDescription = scanner.nextLine();

        System.out.print("Enter new start time (HH:mm): ");
        String newStartTime = scanner.nextLine();

        System.out.print("Enter new end time (HH:mm): ");
        String newEndTime = scanner.nextLine();

        System.out.print("Enter new priority (1-High, 2-Medium, 3-Low): ");
        int newPriority = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            Task newTask = new Task(newTitle, newDescription, newStartTime, newEndTime, newPriority);
            scheduleManager.editTask(oldTitle, newTask);
            System.out.println("Task updated successfully.");
        } catch (Exception e) {
            System.out.println("Error updating task: " + e.getMessage());
        }
    }

    private static void markTaskAsCompleted() {
        System.out.print("Enter the title of the task to mark as completed: ");
        String title = scanner.nextLine();

        try {
            scheduleManager.markTaskAsCompleted(title);
            System.out.println("Task marked as completed.");
        } catch (Exception e) {
            System.out.println("Error marking task as completed: " + e.getMessage());
        }
    }

    private static void viewAllTasks() {
        List<Task> tasks = scheduleManager.getTasksSortedByStartTime();
        tasks.forEach(System.out::println);
    }

    private static void viewCompletedTasks() {
        List<Task> tasks = scheduleManager.getTasksByStatus(true);
        tasks.forEach(System.out::println);
    }

    private static void viewPendingTasks() {
        List<Task> tasks = scheduleManager.getTasksByStatus(false);
        tasks.forEach(System.out::println);
    }

    private static void viewTasksByPriority() {
        System.out.print("Enter priority (1-High, 2-Medium, 3-Low): ");
        int priority = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<Task> tasks = scheduleManager.getTasksByPriority(priority);
        tasks.forEach(System.out::println);
    }

    private static void viewTasksSortedByStartTime() {
        List<Task> tasks = scheduleManager.getTasksSortedByStartTime();
        tasks.forEach(System.out::println);
    }

    private static void viewTasksSortedByPriority() {
        List<Task> tasks = scheduleManager.getTasksSortedByPriority();
        tasks.forEach(System.out::println);
    }

    // Add this method in TaskManager class
    private static void initializeTasks() {
        ScheduleManager scheduleManager = ScheduleManager.getInstance();
        scheduleManager.loadTasksFromFile(); 
    }

    // Add this method in TaskManager class
    private static void saveTasksOnExit() {
        ScheduleManager scheduleManager = ScheduleManager.getInstance();
        scheduleManager.saveTasksToFile(); // Save tasks from ScheduleManager to file
    }
}
