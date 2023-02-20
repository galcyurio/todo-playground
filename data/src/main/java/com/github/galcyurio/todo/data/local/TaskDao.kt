package com.github.galcyurio.todo.data.local

import androidx.room.*
import com.github.galcyurio.todo.data.TaskLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert
    suspend fun insertAll(tasks: List<TaskLocal>)

    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getTasks(): List<TaskLocal>

    @Query("SELECT * FROM tasks WHERE id == :id")
    suspend fun getTask(id: Long): TaskLocal?

    @Delete
    suspend fun delete(task: TaskLocal)

    @Update
    suspend fun update(task: TaskLocal)
}
