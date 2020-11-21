package com.github.galcyurio.todo.tasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.galcyurio.todo.domain.GetTasksUseCase
import com.github.galcyurio.todo.domain.TaskEntity
import kotlinx.coroutines.launch

class TasksViewModel @ViewModelInject constructor(
    getTasks: GetTasksUseCase
) : ViewModel() {
    private val _tasks = MediatorLiveData<List<TaskEntity>>()
    val tasks: LiveData<List<TaskEntity>>
        get() = _tasks

    init {
        viewModelScope.launch {
            _tasks.addSource(getTasks()) {}
        }
    }
}