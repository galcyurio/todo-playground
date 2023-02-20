package com.github.galcyurio.todo.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.galcyurio.todo.domain.Task

@Entity(tableName = "tasks")
data class TaskLocal(
    val title: String,
    val description: String,
    val isCompleted: Boolean,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
) {
    fun toDomain(): Task = Task(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted
    )
}

internal fun Task.toLocal(): TaskLocal = TaskLocal(
    title = title,
    description = description,
    isCompleted = isCompleted,
    id = id
)
