package com.github.galcyurio.todo.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.github.galcyurio.todo.domain.GetTasksUseCase
import com.github.galcyurio.todo.domain.Task
import com.github.galcyurio.todo.test.MainCoroutineRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TasksViewModelTest {
    @get:Rule val instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule val coroutineRule = MainCoroutineRule()

    private lateinit var tasksViewModel: TasksViewModel
    private lateinit var getTasks: GetTasksUseCase

    private val task1 = Task.newDefault("foo1")
    private val task2 = Task.newDefault("foo2")

    @Before
    fun setUp() {
        getTasks = mockk()
        coEvery { getTasks() } returns MutableLiveData(listOf(task1, task2))
        tasksViewModel = TasksViewModel(getTasks)
    }

    @Test
    fun `생성되면 업무 목록을 불러와야 한다`() {
        tasksViewModel.tasks.observeForever {}
        coVerify { getTasks() }
    }
}
