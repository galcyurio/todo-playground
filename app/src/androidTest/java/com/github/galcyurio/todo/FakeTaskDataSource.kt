package com.github.galcyurio.todo

import com.github.galcyurio.todo.data.TaskDataSource
import com.github.galcyurio.todo.domain.Task
import javax.inject.Inject

class FakeTaskDataSource @Inject constructor() : TaskDataSource {
    private val tasks = mutableMapOf<Long, Task>()
    private var id = 1L

    override suspend fun save(task: Task) {
        tasks[id++] = task
    }

    override suspend fun delete(task: Task) {
        tasks.remove(task.id)
    }

    override suspend fun findById(id: Long): Task? {
        return tasks[id]
    }

    override suspend fun findAll(): List<Task> {
        return tasks.values.toList()
    }

    override suspend fun update(task: Task) {
        tasks[task.id] = task
    }
}
