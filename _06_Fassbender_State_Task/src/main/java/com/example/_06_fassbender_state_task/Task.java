package com.example._06_fassbender_state_task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private static TaskState state;
    private String identifier;
    private String description;
    private LocalDate dueDate;

    public Task(String identifier, String description, LocalDate dueDate) {
        this.identifier = identifier;
        this.description = description;
        this.dueDate = dueDate;

        state = new TaskAssignState();
    }

    public static void setState(TaskState newState) {
        state = newState;
    }

    public void assign() {
        state.assign();
    }

    @Override
    public String toString() {
        String formattedDate = dueDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String s = "Kennung: " + identifier + ", Beschreibung: " + description + ", Abgabedatum: " + formattedDate + ", Status: " + state;
        return s;
    }

    public String start() {
        return state.start();
    }

    public String finish() {
        return state.finish();
    }

    public TaskState getState() {
        return state;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String decline() {
        return state.decline();
    }
}
