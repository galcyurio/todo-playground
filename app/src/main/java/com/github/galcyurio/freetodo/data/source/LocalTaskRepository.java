package com.github.galcyurio.freetodo.data.source;

import com.github.galcyurio.freetodo.commons.FilterType;
import com.github.galcyurio.freetodo.data.model.Task;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author galcyurio
 */
public class LocalTaskRepository {

    private DbHelper mDbHelper;

    private String[] COLUMNS = {
            TaskPersistenceContract.TaskEntry._ID,
            TaskPersistenceContract.TaskEntry.COLUMN_UUID_NAME,
            TaskPersistenceContract.TaskEntry.COLUMN_TITLE_NAME,
            TaskPersistenceContract.TaskEntry.COLUMN_DESCRIPTION_NAME,
            TaskPersistenceContract.TaskEntry.COLUMN_COMPLETED_NAME
    };

    private final int FALSE = 0;
    private final int TRUE = 1;

    @Inject
    public LocalTaskRepository(DbHelper dbHelper) {
        mDbHelper = dbHelper;
    }

    /**
     * <pre>
     * This allows save one {@link Task}.
     * if save process is success, will set id to passed task using {@link Task#setId(Long)}.
     * </pre>
     *
     * @param task want to save.
     * @return if success positive unique id; if fail negative;
     */
    public long saveTask(@NonNull Task task) {
        checkNotNull(task);
        UUID.fromString(task.getUuid());

        ContentValues cv = taskToContentValues(task);

        try (SQLiteDatabase db = mDbHelper.getWritableDatabase()) {
            long result = db.insert(TaskPersistenceContract.TaskEntry.TABLE_NAME, null, cv);
            task.setId(result);
            return result;
        }
    }

    /**
     * This allows get specific task using uuid.
     *
     * @param taskUuid uuid of Task to find
     * @return if exists Task, otherwise null
     */
    public Task getTask(String taskUuid) {
        checkNotNull(taskUuid);

        String selection = TaskPersistenceContract.TaskEntry.COLUMN_UUID_NAME + " = ?";
        String[] selectionArgs = {taskUuid};

        try (
                SQLiteDatabase db = mDbHelper.getReadableDatabase();
                Cursor cursor = db.query(TaskPersistenceContract.TaskEntry.TABLE_NAME, COLUMNS, selection, selectionArgs, null, null, null)
        ) {
            List<Task> tasks = cursorToTasks(cursor);
            return tasks.size() != 0 ? tasks.get(0) : null;
        }
    }

    /**
     * This allows get all tasks by {@link FilterType}.
     *
     * @param filterType want to type <br/>
     *                   (e.g. {@link FilterType#ALL}, {@link FilterType#ACTIVE}, {@link FilterType#COMPLETED})
     * @return filtered tasks; if length equals 0, return empty ArrayList.
     */
    public List<Task> getTasks(FilterType filterType) {
        filterType = filterType == null ? FilterType.ALL : filterType;
        String whereClause;
        String[] whereArgs;

        switch (filterType) {
            case ALL:
                whereClause = null;
                whereArgs = null;
                break;
            case ACTIVE:
                whereClause = TaskPersistenceContract.TaskEntry.COLUMN_COMPLETED_NAME + " = ?";
                whereArgs = new String[]{String.valueOf(FALSE)};
                break;
            case COMPLETED:
                whereClause = TaskPersistenceContract.TaskEntry.COLUMN_COMPLETED_NAME + " = ?";
                whereArgs = new String[]{String.valueOf(TRUE)};
                break;
            default:
                whereClause = null;
                whereArgs = null;
        }

        try (
                SQLiteDatabase db = mDbHelper.getReadableDatabase();
                Cursor cursor = db.query(TaskPersistenceContract.TaskEntry.TABLE_NAME, COLUMNS, whereClause, whereArgs, null, null, null)
        ) {
            return cursorToTasks(cursor);
        }
    }

    /**
     * <pre>
     * This allows update certain Task.
     * The unique reference is {@link Task#uuid}
     * </pre>
     *
     * @param task want to update
     * @return true if success; false if fail.
     */
    public boolean updateTask(Task task) {
        checkNotNull(task);

        String whereClause = TaskPersistenceContract.TaskEntry.COLUMN_UUID_NAME + " = ?";
        String[] whereArgs = {task.getUuid()};
        ContentValues cv = taskToContentValues(task);

        try (SQLiteDatabase db = mDbHelper.getWritableDatabase()) {
            return db.update(TaskPersistenceContract.TaskEntry.TABLE_NAME, cv, whereClause, whereArgs) == TRUE;
        }
    }

    /**
     * This allows {@link Task} to set completed.
     *
     * @param task want to set completed.
     * @return true if success; false if fail.
     */
    public boolean completeTask(Task task) {
        checkNotNull(task);
        UUID.fromString(task.getUuid());

        String whereClause = TaskPersistenceContract.TaskEntry.COLUMN_UUID_NAME + " = ?";
        String[] whereArgs = {task.getUuid()};

        // just turn completed field
        ContentValues cv = new ContentValues();
        cv.put(TaskPersistenceContract.TaskEntry.COLUMN_COMPLETED_NAME, true);

        try (SQLiteDatabase db = mDbHelper.getWritableDatabase()) {
            return db.update(TaskPersistenceContract.TaskEntry.TABLE_NAME, cv, whereClause, whereArgs) == TRUE;
        }
    }

    /**
     * This allows activate one {@link Task}.
     *
     * @param task want to activate
     * @return true if success; false if fail.
     */
    public boolean activateTask(Task task) {
        String whereClause = TaskPersistenceContract.TaskEntry.COLUMN_UUID_NAME + " = ?";
        String[] whereArgs = {task.getUuid()};

        ContentValues cv = new ContentValues();
        cv.put(TaskPersistenceContract.TaskEntry.COLUMN_COMPLETED_NAME, false);

        try(SQLiteDatabase db = mDbHelper.getWritableDatabase()) {
            return db.update(TaskPersistenceContract.TaskEntry.TABLE_NAME, cv, whereClause, whereArgs) == TRUE;
        }
    }

    /**
     * This allows delete one task.
     *
     * @param task want to delete {@link Task}.
     * @return true if success; false if fail.
     */
    public boolean deleteTask(Task task) {
        checkNotNull(task);
        UUID.fromString(task.getUuid());

        String whereClause = TaskPersistenceContract.TaskEntry.COLUMN_UUID_NAME + " = ?";
        String[] whereArgs = {task.getUuid()};

        try (SQLiteDatabase db = mDbHelper.getWritableDatabase()) {
            return db.delete(TaskPersistenceContract.TaskEntry.TABLE_NAME, whereClause, whereArgs) == TRUE;
        }
    }

    // ~ Commons

    /**
     * <pre>
     * Cursot 에 있는 값들을 Task 로 맵핑해준다.
     * 넘어온 cursor 는 자동으로 close 되지 않는다.
     * </pre>
     */
    private List<Task> cursorToTasks(Cursor cursor) {
        List<Task> tasks = Lists.newArrayList();

        if (cursor == null || cursor.getCount() <= 0) {
            return tasks;
        }

        while (cursor.moveToNext()) {
            Long id = cursor.getLong(cursor.getColumnIndex(TaskPersistenceContract.TaskEntry._ID));
            String uuid = cursor.getString(cursor.getColumnIndexOrThrow(TaskPersistenceContract.TaskEntry.COLUMN_UUID_NAME));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(TaskPersistenceContract.TaskEntry.COLUMN_TITLE_NAME));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(TaskPersistenceContract.TaskEntry.COLUMN_DESCRIPTION_NAME));
            boolean completed = cursor.getInt(cursor.getColumnIndex(TaskPersistenceContract.TaskEntry.COLUMN_COMPLETED_NAME)) == TRUE;
            tasks.add(new Task(title, description, completed, uuid, id));
        }
        return tasks;
    }

    /**
     * <pre>
     * This allows convert {@link Task} to {@link ContentValues}.
     * Doesn't include {@link Task#id} field.
     * </pre>
     *
     * @param task want to convert to {@link ContentValues}
     * @return converted {@link ContentValues}
     */
    private ContentValues taskToContentValues(Task task) {
        ContentValues cv = new ContentValues();
        cv.put(TaskPersistenceContract.TaskEntry.COLUMN_UUID_NAME, task.getUuid());
        cv.put(TaskPersistenceContract.TaskEntry.COLUMN_TITLE_NAME, task.getTitle());
        cv.put(TaskPersistenceContract.TaskEntry.COLUMN_DESCRIPTION_NAME, task.getDescription());
        cv.put(TaskPersistenceContract.TaskEntry.COLUMN_COMPLETED_NAME, task.isCompleted());
        return cv;
    }
}
