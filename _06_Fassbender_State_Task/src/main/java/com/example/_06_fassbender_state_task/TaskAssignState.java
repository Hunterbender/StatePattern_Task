package com.example._06_fassbender_state_task;

public class TaskAssignState implements TaskState {
    private final String description = "Assigned";
    @Override
    public String assign() {
        return "Aufgabe wurde schon zugeteilt, kann nicht noch einmal zuteilen!";
        //Task.setState(new TaskAssignState());
    }

    @Override
    public String start() {
        Task.setState(new TaskStartState());
        return "Aufgabe wurde zugeteilt, wird gestartet...";
    }

    @Override
    public String finish() {
        return "Aufgabe wurde erst zugeteilt, kann nicht beendet!";
        //Task.setState(new TaskFinishState());
    }

    @Override
    public String decline() {
        Task.setState(new TaskDeclineState());
        return "Aufgabe wird abgebrochen...";
    }

    @Override
    public String toString() {
        return description;
    }
}
