package com.github.galcyurio.todo.domain

interface TaskRepository {
    fun save(task: TaskEntity)
    fun deleteById(id: Long)
    fun findById(id: Long): TaskEntity?
}