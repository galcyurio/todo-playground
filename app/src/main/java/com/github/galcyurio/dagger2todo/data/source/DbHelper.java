package com.github.galcyurio.dagger2todo.data.source;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author galcyurio
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "task.db";
    private static final String SQL_TASK_TABLE =
            "CREATE TABLE " + TaskPersistenceContract.TaskEntry.TABLE_NAME + " (" +
                    TaskPersistenceContract.TaskEntry._ID + TaskPersistenceContract.TaskEntry._ID_TYPE + "" +
                    TaskPersistenceContract.TaskEntry.COLUMN_TITLE_NAME + TaskPersistenceContract.TaskEntry.COLUMN_TITLE_TYPE +
                    TaskPersistenceContract.TaskEntry.COLUMN_DESCRIPTION_NAME + TaskPersistenceContract.TaskEntry.COLUMN_DESCRIPTION_TYPE +
                    ")";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_TASK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
