package com.example._06_fassbender_state_task.dal;

import java.net.URISyntaxException;
import java.util.List;

public interface Dao<T> {
    List<T> getAll() throws URISyntaxException;
    T getById(int id);
    void insert( T e) throws URISyntaxException;
    void update(T e) throws URISyntaxException;
    void delete( T e );
}
