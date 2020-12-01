package com.github.galcyurio.todo.addedittask

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.galcyurio.todo.domain.SaveTaskUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.async

class AddEditTaskViewModel @ViewModelInject constructor(
    private val saveTask: SaveTaskUseCase
) : ViewModel() {
    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    fun saveTask(): Job = viewModelScope.async {
        saveTask.invoke(
            title = title.value ?: "",
            description = description.value ?: ""
        )
        Result.success(Unit)
    }
}