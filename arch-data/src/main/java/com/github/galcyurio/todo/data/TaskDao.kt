package com.github.galcyurio.todo.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Insert
    suspend fun insertAll(tasks: List<Task>)

    @Query("SELECT * FROM tasks")
    fun getTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM tasks WHERE id == :id")
    fun getTask(id: Long): LiveData<Task>

    @Delete
    fun delete(task: Task)

    @Update
    fun update(task: Task)
}