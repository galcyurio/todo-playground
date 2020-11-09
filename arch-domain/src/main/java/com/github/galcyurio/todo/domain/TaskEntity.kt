package com.github.galcyurio.todo.domain

data class TaskEntity(
    val title: String,
    val description: String,
    val isCompleted: Boolean
)
