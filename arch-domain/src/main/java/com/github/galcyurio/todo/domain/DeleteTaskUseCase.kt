package com.github.galcyurio.todo.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteTaskUseCase(
        private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(id: Long) = withContext(Dispatchers.Default) {
        taskRepository.deleteById(id)
    }

    suspend operator fun invoke(task: TaskEntity) = invoke(task.id)
}