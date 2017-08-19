package com.github.galcyurio.freetodo.data.source;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import timber.log.Timber;

/**
 * @author galcyurio
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "task.db";
    private static final String COMMA = ",";

    private static final String V1_SQL_TASK_TABLE =
            "CREATE TABLE " + TaskPersistenceContract.TaskEntry.TABLE_NAME + " (" +
                    TaskPersistenceContract.TaskEntry._ID + TaskPersistenceContract.TaskEntry._ID_TYPE + COMMA +
                    TaskPersistenceContract.TaskEntry.COLUMN_UUID_NAME + TaskPersistenceContract.TaskEntry.COLUMN_UUID_TYPE + COMMA +
                    TaskPersistenceContract.TaskEntry.COLUMN_TITLE_NAME + TaskPersistenceContract.TaskEntry.COLUMN_TITLE_TYPE + COMMA +
                    TaskPersistenceContract.TaskEntry.COLUMN_DESCRIPTION_NAME + TaskPersistenceContract.TaskEntry.COLUMN_DESCRIPTION_TYPE + COMMA +
                    TaskPersistenceContract.TaskEntry.COLUMN_COMPLETED_NAME + TaskPersistenceContract.TaskEntry.COLUMN_COMPLETED_TYPE +
                    ")";

    private static final String SQL_DROP_TASK_TABLE =
            "DROP TABLE IF EXISTS " + TaskPersistenceContract.TaskEntry.TABLE_NAME;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        int upgradeVersion = oldVersion;
        while(++upgradeVersion <= newVersion) {
            switch (upgradeVersion) {
                case 2:
                    break;
            }
        }
    }

    public boolean createTables(SQLiteDatabase db) {
        try {
            db.execSQL(V1_SQL_TASK_TABLE);
            Timber.i("Tables created");
            return true;
        } catch (SQLException e) {
            Timber.e(e, "Failed create tables");
            return false;
        }
    }

    public boolean clearTables(SQLiteDatabase db) {
        try {
            db.execSQL(SQL_DROP_TASK_TABLE);
            Timber.i("Tables cleared");
            return true;
        } catch (SQLException e) {
            Timber.e(e, "Failed clear tables");
            return false;
        }
    }
}
