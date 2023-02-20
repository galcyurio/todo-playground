package com.github.galcyurio.todo.data

import com.github.galcyurio.todo.domain.Task

interface TaskDataSource {
    suspend fun save(task: Task)
    suspend fun delete(task: Task)
    suspend fun findById(id: Long): Task?
    suspend fun findAll(): List<Task>
    suspend fun update(task: Task)
}
