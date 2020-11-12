package com.github.galcyurio.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Task::class],
    version = 1
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}