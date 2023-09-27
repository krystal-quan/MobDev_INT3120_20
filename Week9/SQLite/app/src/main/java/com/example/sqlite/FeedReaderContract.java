package com.example.sqlite;

import android.provider.BaseColumns;

public final class FeedReaderContract {
    private FeedReaderContract() {};

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "data";
        public static final String COLUMN_NAME_TITLE = "name";
        public static final String COLUMN_NAME_SUBTITLE = "number";
    }
}
