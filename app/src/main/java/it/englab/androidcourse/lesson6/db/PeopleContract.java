package it.englab.androidcourse.lesson6.db;

import android.provider.BaseColumns;

/**
 * Created by Peppe on 17/04/2017.
 */

public final class PeopleContract {
    private PeopleContract(){}

    public static final String SQL_CREATE_TABLE =
            String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s BIGINT)",
                    PeopleEntry.TABLE_NAME,
                    PeopleEntry._ID,
                    PeopleEntry.COLUMN_NAME,
                    PeopleEntry.COLUMN_SURNAME,
                    PeopleEntry.COLUMN_EMAIL,
                    PeopleEntry.COLUMN_TELEPHONE
            );

    public static final String SQL_DELETE_TABLE =
            String.format("DROP TABLE IF EXISTS %s",
                    PeopleEntry.TABLE_NAME);

    public static class PeopleEntry implements BaseColumns {
        public static final String TABLE_NAME = "people";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SURNAME = "surname";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_TELEPHONE = "telephone";
    }
}
