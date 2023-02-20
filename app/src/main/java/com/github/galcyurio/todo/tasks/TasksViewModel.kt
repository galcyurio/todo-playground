package com.github.galcyurio.todo.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.github.galcyurio.todo.domain.GetTasksUseCase
import com.github.galcyurio.todo.domain.TaskEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    getTasks: GetTasksUseCase
) : ViewModel() {
    val tasks: LiveData<List<TaskEntity>> = liveData { emitSource(getTasks()) }
}
