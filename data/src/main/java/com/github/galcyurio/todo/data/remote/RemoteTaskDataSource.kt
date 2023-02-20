package com.github.galcyurio.todo.data.remote

import com.github.galcyurio.todo.data.TaskDataSource
import com.github.galcyurio.todo.domain.Task
import javax.inject.Inject

class RemoteTaskDataSource @Inject constructor(
    private val apiService: ApiService
) : TaskDataSource {
    override suspend fun save(task: Task) {
        apiService.saveTask(task.toRemote())
    }

    override suspend fun delete(task: Task) {
        apiService.deleteTask(task.toRemote())
    }

    override suspend fun findById(id: Long): Task {
        return apiService.getTask(id).toDomain()
    }

    override suspend fun findAll(): List<Task> {
        return apiService.getTasks().map(TaskRemote::toDomain)
    }

    override suspend fun update(task: Task) {
        apiService.update(task.toRemote())
    }
}
