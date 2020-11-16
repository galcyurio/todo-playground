package com.github.galcyurio.todo.domain

import androidx.lifecycle.LiveData

interface TaskRepository {
    suspend fun save(task: TaskEntity)
    suspend fun delete(task: TaskEntity)
    suspend fun findById(id: Long): TaskEntity?
    suspend fun findAll(): LiveData<List<TaskEntity>>
    suspend fun update(task: TaskEntity)
}