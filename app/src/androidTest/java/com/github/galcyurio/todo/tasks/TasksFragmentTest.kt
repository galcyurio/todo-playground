package com.github.galcyurio.todo.tasks

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.galcyurio.todo.MainActivity
import com.github.galcyurio.todo.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class TasksFragmentTest {
    @get:Rule val hiltRule = HiltAndroidRule(this)

    @Test fun showTasksUi() {
        // when - 화면이 뜨면
        launchActivity<MainActivity>()

        // then - 업무 목록 화면이 보여야 한다
        onView(withId(R.id.tasksFragment)).check(matches(isDisplayed()))
    }
}