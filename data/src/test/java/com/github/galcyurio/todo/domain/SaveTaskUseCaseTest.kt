package com.github.galcyurio.todo.domain

import com.google.common.truth.Truth.assertThat
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

    @Before fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test fun `모든 입력이 유효하면 업무를 저장할 수 있어야 한다`() = runBlocking {
        // given
        val title = "foo"

        // when
        val actual = saveTask.invoke(title = title)

        // then
        assertThat(actual).isEqualTo(SaveTaskUseCase.Result.Success)
        coVerify { taskRepository.save(any()) }
    }

    @Test fun `제목이 비어있으면 업무를 저장할 수 없어야 한다`() = runBlocking {
        // given
        val title = ""

        // when
        val actual = saveTask.invoke(title)

        // then
        assertThat(actual).isEqualTo(SaveTaskUseCase.Result.EmptyTitle)
        coVerify(exactly = 0) { taskRepository.save(any()) }
    }
}
