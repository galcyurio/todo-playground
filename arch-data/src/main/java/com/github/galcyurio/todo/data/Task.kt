package com.github.galcyurio.todo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
internal data class Task(
    val title: String,
    val description: String,
    val isCompleted: Boolean,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)