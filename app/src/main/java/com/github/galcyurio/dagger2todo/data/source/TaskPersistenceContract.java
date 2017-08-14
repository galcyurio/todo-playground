package com.github.galcyurio.dagger2todo.data.source;

import android.provider.BaseColumns;

/**
 * @author galcyurio
 */
public class TaskPersistenceContract {

    private TaskPersistenceContract() {}

    public static class TaskEntry implements BaseColumns {
        public static final String TABLE_NAME = " task ";

        public static final String COLUMN_TITLE_NAME = " title ";
        public static final String COLUMN_DESCRIPTION_NAME = " description ";
        public static final String COLUMN_COMPLETED_NAME = " completed ";

        public static final String _ID_TYPE = " integer primary key ";
        public static final String COLUMN_TITLE_TYPE = " text ";
        public static final String COLUMN_DESCRIPTION_TYPE = " text ";
        public static final String COLUMN_COMPLETED_TYPE = " integer ";
    }

}
