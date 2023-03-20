package com.example._06_fassbender_state_task;

public class TaskStartState implements TaskState{

    private final String description = "Started";
    @Override
    public String assign() {
        return "Task wurde schon zugeteilt!";
    }

    @Override
    public String start() {
        return "Task wurde schon gestartet!";
    }

    @Override
    public String finish() {
        Task.setState(new TaskFinishState());
        return "Task wird beendet...";
    }

    @Override
    public String decline() {
        Task.setState(new TaskDeclineState());
        return "Task wird abgebrochen...";
    }

    @Override
    public String toString() {
        return description;
    }
}
