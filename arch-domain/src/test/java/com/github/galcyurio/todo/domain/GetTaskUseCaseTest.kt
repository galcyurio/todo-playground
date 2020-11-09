package com.github.galcyurio.todo.domain

import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class GetTaskUseCaseTest {
    @InjectMockKs lateinit var getTask: GetTaskUseCase
    @MockK(relaxUnitFun = true) lateinit var taskRepository: TaskRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `업무 하나를 가져올 수 있어야 한다`() = runBlocking {
        // given
        val expected = TaskEntity(
                id = 10L,
                title = "foo",
                description = "bar",
                isCompleted = false
        )
        every { taskRepository.findById(expected.id) } returns expected

        // when
        val actual = getTask(expected.id)

        // then
        assertThat(actual).isEqualTo(expected)
        coVerify { taskRepository.findById(expected.id) }
    }
}
