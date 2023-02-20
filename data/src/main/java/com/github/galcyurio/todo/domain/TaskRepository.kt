package com.github.galcyurio.todo.domain

import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun save(task: Task)
    suspend fun delete(task: Task)
    suspend fun findById(id: Long): Task?
    fun findAll(): Flow<List<Task>>
    suspend fun update(task: Task)
}
