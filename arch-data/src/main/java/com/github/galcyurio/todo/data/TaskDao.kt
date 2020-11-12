package com.github.galcyurio.todo.data

import androidx.room.*

@Dao
internal interface TaskDao {
    @Insert
    suspend fun insertAll(tasks: List<Task>)

    @Query("SELECT * FROM tasks")
    suspend fun getTasks(): List<Task>

    @Query("SELECT * FROM tasks WHERE id == :id")
    suspend fun getTask(id: Long): Task?

    @Delete
    suspend fun delete(task: Task)

    @Update
    suspend fun update(task: Task)
}