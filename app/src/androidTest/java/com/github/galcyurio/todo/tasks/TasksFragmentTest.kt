package com.github.galcyurio.todo.tasks

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.galcyurio.todo.MainActivity
import com.github.galcyurio.todo.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class TasksFragmentTest {
    @get:Rule val hiltRule = HiltAndroidRule(this)

    @Test fun showTasksUi() {
        // given
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.nav_graph)

        // when - 화면이 뜨면
        launchActivity<MainActivity>().onActivity {
            Navigation.setViewNavController(it.requireViewById(R.id.nav_host_fragment), navController)
        }

        // then - 업무 목록 화면이 보여야 한다
        assertThat(navController.currentDestination?.id, equalTo(R.id.tasksFragment))
    }
}