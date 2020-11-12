package com.github.galcyurio.todo.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.galcyurio.todo.data.util.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.*
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TaskDaoTest {
    private lateinit var taskDao: TaskDao
    private lateinit var db: AppDatabase

    private val task1 = Task(id = 1, title = "foo1", description = "bar1", isCompleted = false)
    private val task2 = Task(id = 2, title = "foo2", description = "bar2", isCompleted = true)
    private val task3 = Task(id = 3, title = "foo3", description = "bar3", isCompleted = false)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        taskDao = db.taskDao()

        taskDao.insertAll(listOf(task1, task2, task3))
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun getTasks() {
        // when
        val actual = taskDao.getTasks().getOrAwaitValue()

        // then
        assertThat(actual, contains(task1, task2, task3))
    }

    @Test
    fun deleteTask() {
        // when
        taskDao.delete(task1)
        val actual = taskDao.getTasks().getOrAwaitValue()

        // then
        assertThat(actual, not(contains(task1)))
        assertThat(actual, contains(task2, task3))
    }

    @Test
    fun updateTask() {
        // given
        val completedTask = task1.copy(isCompleted = true)

        // when
        taskDao.update(completedTask)

        // then
        val tasks = taskDao.getTasks().getOrAwaitValue()
        assertThat(tasks, hasItem(equalTo(completedTask)))
    }
}