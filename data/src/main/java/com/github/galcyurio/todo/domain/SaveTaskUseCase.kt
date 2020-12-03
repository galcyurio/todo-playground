package com.github.galcyurio.todo.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(
        title: String,
        description: String = ""
    ): Result = withContext(Dispatchers.Default) {
        if (title.isEmpty()) return@withContext Result.EmptyTitle

        val task = TaskEntity.newDefault(
            title = title,
            description = description
        )
        taskRepository.save(task)
        return@withContext Result.Success
    }

    enum class Result {
        Success, EmptyTitle
    }
}