package com.studenttaskmanager.models;

import java.time.LocalDate;

public class Task {
    private int id;
    private String name;
    private LocalDate dueDate;
    private boolean completed;

    // Constructor
    public Task(int id, String name, LocalDate dueDate, boolean completed) {
        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    @Override
    public String toString() {
        return "Task{id=" + id + ", name='" + name + "', dueDate=" + dueDate + ", completed=" + completed + "}";
    }
}
