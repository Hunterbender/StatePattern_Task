package com.example._06_fassbender_state_task.service;


import com.example._06_fassbender_state_task.Task;
import com.example._06_fassbender_state_task.dal.Dao;
import com.example._06_fassbender_state_task.dal.DatabaseManager;

import java.net.URISyntaxException;
import java.util.List;

public class TaskService {

    private Dao<Task> taskDao = null;
    private DatabaseManager databaseManager = DatabaseManager.getInstance();

    public TaskService(){

    }

    public List<Task> getTasks() throws URISyntaxException {
        return taskDao.getAll();
    }

    public boolean updateTask(Task t){
        boolean result = true;
        try {
            if (isTaskValid(t)) {
                taskDao.update(t);
            }
        }catch( Exception ex ){
            result = false;
        }
        return result;
    }

    public boolean deleteTask(Task t) {
        boolean result = true;
        try {
            if (isTaskValid(t)) {
                taskDao.delete(t);
            }
        }catch( Exception ex ){
            result = false;
        }
        return result;
    }
    public boolean insertTask( Task t ){
        boolean result = true;
        try {
            databaseManager.insertTask(t);
        }catch( Exception ex ){
            result = false;
        }
        return result;

    }
    private boolean isTaskValid( Task t){
        return !t.getIdentifier().isBlank()&& !t.getDescription().isBlank();
    }

}
