package com.github.galcyurio.dagger2todo.data.source;

import android.content.Context;

/**
 * @author galcyurio
 */
public class LocalTaskRepository {

    private DbHelper mDbHelper;

    public LocalTaskRepository(Context context) {
        this.mDbHelper = new DbHelper(context);
    }


}
