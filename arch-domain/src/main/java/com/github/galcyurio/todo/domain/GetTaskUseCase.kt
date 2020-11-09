package com.github.galcyurio.todo.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetTaskUseCase(
        private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(id: Long): TaskEntity? = withContext(Dispatchers.Default) {
        return@withContext taskRepository.findById(id)
    }
}