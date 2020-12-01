package com.github.galcyurio.todo.addedittask

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import com.github.galcyurio.todo.DatabaseModule
import com.github.galcyurio.todo.MainActivity
import com.github.galcyurio.todo.R
import com.github.galcyurio.todo.tasks.TasksFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.hamcrest.Matchers.instanceOf
import org.junit.Rule
import org.junit.Test
import java.util.*

@UninstallModules(DatabaseModule::class)
@HiltAndroidTest
class AddEditTaskFragmentTest {
    @get:Rule val hiltRule = HiltAndroidRule(this)
    @get:Rule val activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test fun whenSaveButtonClicked_saveTask() {
        // given
        val expectedTitle = UUID.randomUUID().toString()
        val expectedDescription = UUID.randomUUID().toString()

        onView(withId(R.id.menu_add)).perform(click())
        onView(withId(R.id.tvTitle)).perform(typeText(expectedTitle))
        onView(withId(R.id.tvDescription)).perform(typeText(expectedDescription))

        // when - 저장 버튼을 누르면
        onView(withId(R.id.menu_save)).perform(click())

        // then - 업무 목록 화면으로 돌아와야 한다
        activityScenarioRule.scenario.onActivity {
            val currentFragment = it.supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                ?.childFragmentManager?.fragments?.get(0)
            assertThat(currentFragment, instanceOf(TasksFragment::class.java))
        }

        // and - 작성했던 제목이 보여야 한다
        onView(withText(expectedTitle)).check(matches(isDisplayed()))
    }
}