package com.github.galcyurio.todo.domain

import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SaveTaskUseCaseTest {
    @InjectMockKs lateinit var saveTask: SaveTaskUseCase
    @MockK(relaxUnitFun = true) lateinit var taskRepository: TaskRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `업무를 저장할 수 있어야 한다`() = runBlocking {
        // given
        val task = TaskEntity("foo", "bar", false)

        // when
        saveTask(task)

        // then
        coVerify { taskRepository.save(task) }
    }
}