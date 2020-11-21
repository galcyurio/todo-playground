package com.github.galcyurio.todo.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(): LiveData<List<TaskEntity>> {
        return taskRepository.findAll()
    }
}