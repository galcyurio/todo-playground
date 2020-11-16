package com.github.galcyurio.todo.domain

interface TaskRepository {
    suspend fun save(task: TaskEntity)
    suspend fun delete(task: TaskEntity)
    suspend fun findById(id: Long): TaskEntity?
    suspend fun findAll(): List<TaskEntity>
    suspend fun update(task: TaskEntity)
}