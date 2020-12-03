package com.github.galcyurio.todo.domain

data class TaskEntity(
    val id: Long = 0,
    val title: String,
    val description: String,
    val isCompleted: Boolean
) {
    companion object {
        fun newDefault(
            title: String,
            description: String = ""
        ): TaskEntity = TaskEntity(
            title = title,
            description = description,
            isCompleted = false
        )
    }
}
