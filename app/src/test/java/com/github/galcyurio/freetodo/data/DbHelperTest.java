package com.github.galcyurio.freetodo.data;

import com.github.galcyurio.freetodo.data.source.DbHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import android.database.sqlite.SQLiteDatabase;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author galcyurio
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class DbHelperTest {

    private DbHelper mDbHelper;

    @Before
    public void setup() {
        mDbHelper = new DbHelper(RuntimeEnvironment.application);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        mDbHelper.clearTables(db);
        db.close();
    }

    @Test
    public void WHEN__create_tables__THEN__should_return_true() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        boolean actual = mDbHelper.createTables(db);
        assertThat(actual).isTrue();
    }

    @Test
    public void WHEN__clear_tables__THEN__should_return_true() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        boolean result = mDbHelper.clearTables(db);
        assertThat(result).isTrue();
    }
}
