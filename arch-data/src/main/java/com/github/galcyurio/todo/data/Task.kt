package com.github.galcyurio.todo.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.galcyurio.todo.domain.TaskEntity

@Entity(tableName = "tasks")
data class Task(
    val title: String,
    val description: String,
    val isCompleted: Boolean,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
) {
    fun toEntity(): TaskEntity = TaskEntity(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted
    )
}

internal fun TaskEntity.toDto(): Task = Task(
    title = title,
    description = description,
    isCompleted = isCompleted,
    id = id
)
