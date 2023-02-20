package com.github.galcyurio.todo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.galcyurio.todo.data.TaskLocal

@Database(
    entities = [TaskLocal::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
