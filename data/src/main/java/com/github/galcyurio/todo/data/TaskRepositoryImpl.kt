package com.github.galcyurio.todo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.github.galcyurio.todo.domain.TaskEntity
import com.github.galcyurio.todo.domain.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {
    override suspend fun save(task: TaskEntity) {
        taskDao.insertAll(listOf(task.toDto()))
    }

    override suspend fun delete(task: TaskEntity) {
        taskDao.delete(task.toDto())
    }

    override suspend fun findById(id: Long): TaskEntity? {
        return taskDao.getTask(id)?.toEntity()
    }

    override suspend fun findAll(): LiveData<List<TaskEntity>> = withContext(Dispatchers.Default) {
        return@withContext taskDao.getTasks().switchMap { tasks ->
            liveData { emit(tasks.map(Task::toEntity)) }
        }
    }

    override suspend fun update(task: TaskEntity) {
        taskDao.update(task.toDto())
    }
}