package com.github.galcyurio.todo.addedittask

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.galcyurio.todo.domain.SaveTaskUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AddEditTaskViewModel @ViewModelInject constructor(
    private val saveTask: SaveTaskUseCase
) : ViewModel() {
    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    private val _saveTaskResult = MutableLiveData<SaveTaskUseCase.Result>()
    val saveTaskResult: LiveData<SaveTaskUseCase.Result>
        get() = _saveTaskResult

    fun saveTask(): Job = viewModelScope.launch {
        val result = saveTask.invoke(
            title = title.value ?: "",
            description = description.value ?: ""
        )
        _saveTaskResult.value = result
    }
}