package com.github.galcyurio.todo.data.local

import com.github.galcyurio.todo.data.TaskDataSource
import com.github.galcyurio.todo.data.TaskLocal
import com.github.galcyurio.todo.data.toLocal
import com.github.galcyurio.todo.domain.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalTaskDataSource @Inject constructor(
    private val taskDao: TaskDao
) : TaskDataSource {
    override suspend fun save(task: Task) {
        taskDao.insertAll(listOf(task.toLocal()))
    }

    override suspend fun delete(task: Task) {
        taskDao.delete(task.toLocal())
    }

    override suspend fun findById(id: Long): Task? {
        return taskDao.getTask(id)?.toDomain()
    }

    override suspend fun findAll(): List<Task> = withContext(Dispatchers.Default) {
        taskDao.getTasks().map(TaskLocal::toDomain)
    }

    override suspend fun update(task: Task) {
        taskDao.update(task.toLocal())
    }
}
