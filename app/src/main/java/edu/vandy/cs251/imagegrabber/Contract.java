package edu.vandy.cs251.imagegrabber;

import android.provider.BaseColumns;

/**
 * This is the class that defines the schema of the database
 *
 */
public class Contract {

    public Contract() {}

    /* Inner class that defines the table contents */
    public static abstract class ImageEntry implements BaseColumns {
        public static final String TABLE_NAME = "images";
        public static final String COLUMN_NAME_ENTRY_ID = "image_id";
        public static final String COLUMN_NAME_TITLE = "image_title";
        public static final String COLUMN_NAME_DOWNLOAD_TIME = "download_time";
        public static final String COLUMN_NAME_URI = "image_uri";
    }
}
