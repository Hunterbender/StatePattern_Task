package com.example._06_fassbender_state_task;

public class TaskFinishState implements TaskState{

    private final String description = "Finished";
    @Override
    public String assign() {
        return "Aufgabe wurde beendet, kann nicht zuteilen!";
    }

    @Override
    public String start() {
        return "Aufgabe wurde schon beendet, kann nicht starten!";
    }

    @Override
    public String finish() {
        return "Aufgabe wurde schon beendet, kann nicht noch einmal beenden!";
    }

    @Override
    public String decline() {
        return "Aufgabe wurde beendet, kann nicht abrechen!";
    }

    @Override
    public String toString() {
        return description;
    }
}
