package com.github.galcyurio.todo.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(): List<TaskEntity> = withContext(Dispatchers.Default) {
        return@withContext taskRepository.findAll()
    }
}