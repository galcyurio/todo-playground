package com.github.galcyurio.todo.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {
    @Insert
    suspend fun insertAll(tasks: List<Task>)

    @Query("SELECT * FROM tasks")
    fun getTasks(): LiveData<List<Task>>
}