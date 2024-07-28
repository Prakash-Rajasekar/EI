package com.example.taskmanager;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L; // Recommended for Serializable classes

    private String title;
    private String description;
    private Date startTime;  // Time in HH:mm format
    private Date endTime;    // Time in HH:mm format
    private boolean completed;
    private int priority;

    // Constructor
    public Task(String title, String description, String startTimeStr, String endTimeStr, int priority) {
        this.title = title;
        this.description = description;
        this.startTime = parseTime(startTimeStr); // Parse start time
        this.endTime = parseTime(endTimeStr);     // Parse end time
        this.completed = false;
        this.priority = priority;
    }

    // Helper method to parse the time string
    private Date parseTime(String timeStr) {
        try {
            return new SimpleDateFormat("HH:mm").parse(timeStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid time format. Please use HH:mm.");
        }
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return String.format("Title: %s\nDescription: %s\nStart Time: %s\nEnd Time: %s\nPriority: %d\nStatus: %s",
                title, description, sdf.format(startTime), sdf.format(endTime), priority, completed ? "Completed" : "Pending");
    }
}
