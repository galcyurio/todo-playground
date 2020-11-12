package com.github.galcyurio.todo.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetTasksUseCase(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(): List<TaskEntity> = withContext(Dispatchers.Default) {
        return@withContext taskRepository.findAll()
    }
}