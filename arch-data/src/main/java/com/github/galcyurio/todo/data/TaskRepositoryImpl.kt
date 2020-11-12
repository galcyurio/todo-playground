package com.github.galcyurio.todo.data

import com.github.galcyurio.todo.domain.TaskEntity
import com.github.galcyurio.todo.domain.TaskRepository

internal class TaskRepositoryImpl(
    private val taskDao: TaskDao
) : TaskRepository {
    override suspend fun save(task: TaskEntity) {
        taskDao.insertAll(listOf(task.toDto()))
    }

    override suspend fun delete(task: TaskEntity) {
        taskDao.delete(task.toDto())
    }

    override suspend fun findById(id: Long): TaskEntity? {
        return taskDao.getTask(id)?.toEntity()
    }

    override suspend fun findAll(): List<TaskEntity> {
        return taskDao.getTasks().map(Task::toEntity)
    }

    override suspend fun update(task: TaskEntity) {
        taskDao.update(task.toDto())
    }
}