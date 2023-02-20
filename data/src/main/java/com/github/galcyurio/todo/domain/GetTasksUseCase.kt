package com.github.galcyurio.todo.domain

import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(): List<Task> {
        return taskRepository.findAll()
    }
}
