package com.github.galcyurio.todo.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Insert
    suspend fun insertAll(tasks: List<Task>)

    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM tasks WHERE id == :id")
    suspend fun getTask(id: Long): Task?

    @Delete
    suspend fun delete(task: Task)

    @Update
    suspend fun update(task: Task)
}