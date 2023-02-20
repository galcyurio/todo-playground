package com.github.galcyurio.todo.data

import com.github.galcyurio.todo.domain.Task
import kotlinx.coroutines.flow.Flow

interface TaskDataSource {
    suspend fun save(task: Task)
    suspend fun delete(task: Task)
    suspend fun findById(id: Long): Task?
    fun findAll(): Flow<List<Task>>
    suspend fun update(task: Task)
}
