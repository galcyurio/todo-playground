package com.github.galcyurio.todo.tasks

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import com.github.galcyurio.todo.MainActivity
import com.github.galcyurio.todo.R
import com.github.galcyurio.todo.addedittask.AddEditTaskFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.instanceOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@UninstallModules(DatabaseModule::class)
@HiltAndroidTest
class TasksFragmentTest {
    @get:Rule val hiltRule = HiltAndroidRule(this)
    @get:Rule val activityScenarioRule = activityScenarioRule<MainActivity>()

    private lateinit var navController: TestNavHostController

    @Before fun setUp() {
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.nav_graph)
        activityScenarioRule.scenario.onActivity {
            Navigation.setViewNavController(it.requireViewById(R.id.nav_host_fragment), navController)
        }
    }

    @Test fun showTasksUi() {
        // when - 화면이 뜨면
        // then - 업무 목록 화면이 보여야 한다
        assertThat(navController.currentDestination?.id, equalTo(R.id.tasksFragment))
    }

    @Test fun whenAddButtonClicked_showAddEditTaskUi() {
        // when - 추가 버튼을 누르면
        onView(withId(R.id.menu_add)).perform(click())

        // then - 업무 추가, 수정 화면이 보여야 한다
        activityScenarioRule.scenario.onActivity {
            val navHostFragment = it.supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
            val currentFragment = navHostFragment?.childFragmentManager?.fragments?.get(0)
            assertThat(currentFragment, instanceOf(AddEditTaskFragment::class.java))
        }
//        알 수 없는 원인으로 아래 코드에서는 tasksFragment id를 가지고 있다.
//        assertThat(navController.currentDestination?.id, equalTo(R.id.addEditTaskFragment))
    }
}
