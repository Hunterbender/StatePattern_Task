package com.example._06_fassbender_state_task;

public class TaskDeclineState implements TaskState{

    private final String description = "Declined";
    @Override
    public String assign() {
        return "Aufgabe wurde abgebrochen, kann nicht zuteilen!";
    }

    @Override
    public String start() {
        return "Aufgabe wurde abgebrochen, kann nicht starten!";
    }

    @Override
    public String finish() {
        return "Aufgabe wurde abgebrochen, kann nicht beenden!";
    }

    @Override
    public String decline() {
        return "Aufgabe wurde schon beendet!";
    }

    @Override
    public String toString() {
        return description;
    }
}
