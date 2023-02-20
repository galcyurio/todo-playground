package com.github.galcyurio.todo.data.remote

import com.github.galcyurio.todo.domain.Task
import com.google.gson.annotations.SerializedName

class TaskRemote(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("is_completed")
    val isCompleted: Boolean,
) {
    fun toDomain(): Task = Task(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted,
    )
}

fun Task.toRemote(): TaskRemote = TaskRemote(
    id = id,
    title = title,
    description = description,
    isCompleted = isCompleted,
)
