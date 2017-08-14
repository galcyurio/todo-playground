package com.github.galcyurio.dagger2todo.data;

import com.github.galcyurio.dagger2todo.data.source.LocalTaskRepository;

/**
 * @author galcyurio
 */
public class TaskRepository {

    LocalTaskRepository mLocalTaskRepository;

    public TaskRepository(LocalTaskRepository localTaskRepository) {
        this.mLocalTaskRepository = localTaskRepository;
    }
}
