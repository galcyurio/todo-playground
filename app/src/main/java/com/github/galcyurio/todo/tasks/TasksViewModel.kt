package com.github.galcyurio.todo.tasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.galcyurio.todo.domain.GetTasksUseCase
import com.github.galcyurio.todo.domain.TaskEntity
import kotlinx.coroutines.launch

class TasksViewModel @ViewModelInject constructor(
    private val getTasks: GetTasksUseCase
) : ViewModel() {
    private val _tasks = MutableLiveData<List<TaskEntity>>()
    val tasks: LiveData<List<TaskEntity>>
        get() = _tasks

    init {
        viewModelScope.launch {
            _tasks.value = getTasks()
        }
    }
}