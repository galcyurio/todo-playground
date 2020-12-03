package com.github.galcyurio.todo.tasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.github.galcyurio.todo.domain.GetTasksUseCase
import com.github.galcyurio.todo.domain.TaskEntity

class TasksViewModel @ViewModelInject constructor(
    getTasks: GetTasksUseCase
) : ViewModel() {
    val tasks: LiveData<List<TaskEntity>> = liveData { emitSource(getTasks()) }
}