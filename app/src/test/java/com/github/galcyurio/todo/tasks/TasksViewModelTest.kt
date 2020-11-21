package com.github.galcyurio.todo.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.galcyurio.todo.domain.GetTasksUseCase
import com.github.galcyurio.todo.domain.TaskEntity
import com.github.galcyurio.todo.test.MainCoroutineRule
import com.github.galcyurio.todo.test.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TasksViewModelTest {
    @get:Rule val instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule val coroutineRule = MainCoroutineRule()

    private lateinit var tasksViewModel: TasksViewModel
    private lateinit var getTasks: GetTasksUseCase

    private val task1 = TaskEntity.newDefault("foo1")
    private val task2 = TaskEntity.newDefault("foo2")

    @Before
    fun setUp() {
        getTasks = mockk()
        coEvery { getTasks() } returns listOf(task1, task2)
        tasksViewModel = TasksViewModel(getTasks)
    }

    @Test
    fun `생성되면 업무 목록을 불러와야 한다`() {
        val actual = tasksViewModel.tasks.getOrAwaitValue()
        assertThat(actual).contains(task1, task2)
    }
}
