package com.github.galcyurio.todo.domain

import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DeleteTaskUseCaseTest {
    @InjectMockKs lateinit var deleteTask: DeleteTaskUseCase
    @MockK(relaxUnitFun = true) lateinit var taskRepository: TaskRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `업무를 삭제할 수 있어야 한다`() = runBlocking {
        // given
        val task = TaskEntity(
                id = 100L,
                title = "foo",
                description = "bar",
                isCompleted = false
        )

        // when
        deleteTask(task)

        // then
        coVerify { taskRepository.deleteById(task.id) }
    }
}