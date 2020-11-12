package com.github.galcyurio.todo.domain

data class TaskEntity(
        val id: Long = 0,
        val title: String,
        val description: String,
        val isCompleted: Boolean
)
