package com.github.galcyurio.todo.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SaveTaskUseCase(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(task: TaskEntity) = withContext(Dispatchers.Default){
        taskRepository.save(task)
    }
}