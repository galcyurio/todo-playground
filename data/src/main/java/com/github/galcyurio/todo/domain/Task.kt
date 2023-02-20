package com.github.galcyurio.todo.domain

data class Task(
    val id: Long = 0,
    val title: String,
    val description: String,
    val isCompleted: Boolean
) {
    companion object {
        fun newDefault(
            title: String,
            description: String = ""
        ): Task = Task(
            title = title,
            description = description,
            isCompleted = false
        )
    }
}
