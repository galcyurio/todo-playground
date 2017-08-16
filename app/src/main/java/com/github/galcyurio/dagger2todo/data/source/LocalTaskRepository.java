package com.github.galcyurio.dagger2todo.data.source;

import javax.inject.Inject;

/**
 * @author galcyurio
 */
public class LocalTaskRepository {

    private DbHelper mDbHelper;

    @Inject
    public LocalTaskRepository(DbHelper dbHelper) {
        mDbHelper = dbHelper;
    }

}
