package com.github.galcyurio.todo.data

import com.github.galcyurio.todo.domain.Task
import com.github.galcyurio.todo.domain.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDataSource: TaskDataSource,
) : TaskRepository {
    override suspend fun save(task: Task) {
        taskDataSource.save(task)
    }

    override suspend fun delete(task: Task) {
        taskDataSource.delete(task)
    }

    override suspend fun findById(id: Long): Task? {
        return taskDataSource.findById(id)
    }

    override suspend fun findAll(): List<Task> {
        return taskDataSource.findAll()
    }

    override suspend fun update(task: Task) {
        taskDataSource.update(task)
    }
}
