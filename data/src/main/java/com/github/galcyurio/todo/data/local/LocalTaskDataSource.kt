package com.github.galcyurio.todo.data.local

import com.github.galcyurio.todo.data.TaskDataSource
import com.github.galcyurio.todo.data.TaskLocal
import com.github.galcyurio.todo.data.toLocal
import com.github.galcyurio.todo.domain.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    override fun findAll(): Flow<List<Task>> {
        return taskDao.getTasks()
            .map { tasks -> tasks.map(TaskLocal::toDomain) }
    }

    override suspend fun update(task: Task) {
        taskDao.update(task.toLocal())
    }
}
