package com.github.galcyurio.todo.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.galcyurio.todo.domain.GetTasksUseCase
import com.github.galcyurio.todo.domain.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val getTasks: GetTasksUseCase
) : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks = _tasks.asStateFlow()

    fun fetchTasks(): Job = viewModelScope.launch {
        _tasks.value = getTasks()
    }
}
