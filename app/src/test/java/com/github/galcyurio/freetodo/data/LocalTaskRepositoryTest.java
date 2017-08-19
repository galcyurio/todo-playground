package com.github.galcyurio.freetodo.data;

import com.github.galcyurio.freetodo.commons.FilterType;
import com.github.galcyurio.freetodo.data.model.Task;
import com.github.galcyurio.freetodo.data.source.DbHelper;
import com.github.galcyurio.freetodo.data.source.LocalTaskRepository;
import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author galcyurio
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class LocalTaskRepositoryTest {

    private LocalTaskRepository mLocalTaskRepository;

    @Before
    public void setup() {
        DbHelper dbHelper = new DbHelper(RuntimeEnvironment.application);
        mLocalTaskRepository = new LocalTaskRepository(dbHelper);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.clearTables(db);
        dbHelper.createTables(db);
        db.close();
    }

    @Test
    public void WHEN__save_task__THEN__should_return_valid_value() {
        Task task = new Task("dummy title", "dummy desc");
        long taskId = mLocalTaskRepository.saveTask(task);
        System.out.println("task = " + task);

        assertThat(task.getId()).isPositive();
        assertThat(taskId).isPositive();
    }

    @Test
    public void WHEN__get_task__THEN__should_return_saved_task() {
        Task task = new Task("dummy title", "dummy desc");
        long taskId = mLocalTaskRepository.saveTask(task);
        assertThat(taskId).isPositive();

        String taskUuid = task.getUuid();
        Task afterTask = mLocalTaskRepository.getTask(taskUuid);

        assertThat(task.equals(afterTask)).isTrue();
    }

    @Test
    public void WHEN__get_tasks__THEN__should_return_saved_tasks() {
        List<Task> tasks = Lists.newArrayList(
                new Task("dummy title1", "dummy desc1"),
                new Task("dummy title2", "dummy desc2"),
                new Task("dummy title3", "dummy desc3")
        );

        for (Task task : tasks) {
            long id = mLocalTaskRepository.saveTask(task);
            assertThat(id).isPositive();

            System.out.println("id = " + id);
        }

        List<Task> fetchedTasks = mLocalTaskRepository.getTasks(FilterType.ALL);
        System.out.println("fetchedTasks = " + fetchedTasks);

        assertThat(fetchedTasks.size() == tasks.size()).isTrue();
        assertThat(tasks.equals(fetchedTasks));
    }

    @Test
    public void WHEN__save_after_update_task__THEN__should_return_true() {
        // save one task
        Task originalTask = new Task("originalTask title", "originalTask desc");
        mLocalTaskRepository.saveTask(originalTask);

        // update the task using uuid
        originalTask.setTitle("updated title");
        originalTask.setDescription("updated desc");
        boolean result = mLocalTaskRepository.updateTask(originalTask);
        assertThat(result).isTrue();
    }

    @Test
    public void WHEN__save_after_complete_task__THEN__should_return_true() {
        Task task = new Task("dummy title", "dummy desc");
        task.setCompleted(true);
        long taskId = mLocalTaskRepository.saveTask(task);
        assertThat(taskId).isPositive();

        boolean actual = mLocalTaskRepository.completeTask(task);
        assertThat(actual).isTrue();
    }

    @Test
    public void WHEN__save_after_delete_task__THEN__should_return_true() {
        Task task1 = new Task("title", "desc");
        Task task2 = new Task("asdf", "zxcv");

        long save1 = mLocalTaskRepository.saveTask(task1);
        long save2 = mLocalTaskRepository.saveTask(task2);

        assertThat(save1).isPositive();
        assertThat(save2).isPositive();

        boolean delete = mLocalTaskRepository.deleteTask(task2);

        assertThat(delete).isTrue();

        List<Task> tasks = mLocalTaskRepository.getTasks(FilterType.ALL);
        assertThat(tasks.size()).isEqualTo(1);
        assertThat(tasks.get(0)).isEqualToComparingFieldByFieldRecursively(task1);
    }

    @Test
    public void WHEN__save_after_get_by_filter__THEN__should_return_filtered_tasks() {
        List<Task> newTasks = Lists.newArrayList(
                new Task("completed", "completed desc", true),
                new Task("active", "active desc", false),
                new Task("completed2", "completed2 desc", true),
                new Task("active2", "active2 desc", false));

        for(Task task : newTasks) {
            mLocalTaskRepository.saveTask(task);
        }

        List<Task> tasks = mLocalTaskRepository.getTasks(FilterType.ALL);
        System.out.println(tasks.toString());
        assertThat(tasks.size()).isEqualTo(newTasks.size());

        List<Task> activeTasks = mLocalTaskRepository.getTasks(FilterType.ACTIVE);
        for (Task task : activeTasks) {
            System.out.println(task.toString());
            assertThat(task.isCompleted()).isFalse();
        }

        List<Task> completedTasks = mLocalTaskRepository.getTasks(FilterType.COMPLETED);
        for (Task task : completedTasks) {
            System.out.println(task.toString());
            assertThat(task.isCompleted()).isTrue();
        }
    }

    @Test
    public void WHEN__activate_task__THEN__should_return_true() {
        Task task = new Task("title", "desc", true);
        mLocalTaskRepository.saveTask(task);

        boolean actual = mLocalTaskRepository.activateTask(task);
        assertThat(actual).isTrue();
    }

    // ~ Invalid =================================================================

    @Test
    public void WHEN__complete_unknown_task__THEN__should_return_zero() {
        Task task = new Task();
        boolean actual = mLocalTaskRepository.completeTask(task);
        assertThat(actual).isFalse();
    }

    @Test
    public void WHEN__get_unknown_task__THEN__should_return_null() {
        Task task = mLocalTaskRepository.getTask("asdf");
        assertThat(task).isNull();
    }

    @Test
    public void WHEN__get_tasks_from_empty_db__THEN__should_return_empty_list() {
        List<Task> tasks = mLocalTaskRepository.getTasks(FilterType.ALL);
        assertThat(tasks).isNotNull();
        assertThat(tasks.size()).isZero();
    }

    @Test(expected = IllegalArgumentException.class)
    public void WHEN__save_task_using_invalid_uuid__THEN__should_throw_exception() {
        Task task = new Task();
        task.setUuid("asdf");

        mLocalTaskRepository.saveTask(task);
    }

    @Test(expected = IllegalArgumentException.class)
    public void WHEN__complete_task_using_invalid_uuid__THEN__should_throw_exception() {
        Task task = new Task("title", "desc");
        mLocalTaskRepository.saveTask(task);

        task.setUuid("invalid uuid");
        mLocalTaskRepository.completeTask(task);
    }
}
