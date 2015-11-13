package edu.vandy.cs251.imagegrabber;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * This is the class that creates database table used to
 * store the downloaded images locally.
 *
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;           // Database version is changed when the schema is changed
    public static final String DATABASE_NAME = "ImageReader.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String LONG_TYPE =" LONG";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Contract.ImageEntry.TABLE_NAME + " (" +
                    Contract.ImageEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Contract.ImageEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    Contract.ImageEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    Contract.ImageEntry.COLUMN_NAME_DOWNLOAD_TIME + LONG_TYPE + COMMA_SEP +
                    Contract.ImageEntry.COLUMN_NAME_URI + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Contract.ImageEntry.TABLE_NAME;
    private static final String SQL_SELECT_ALL = "SELECT * FROM " + Contract.ImageEntry.TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    /**
     * When the database is reversed to the previous version, this method is
     * called
     * @param db The database whose version was changed
     * @param oldVersion The interger value that represents the the old version
     * @param newVersion Integer that represents the version after the downgrade
     */
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /**
     * This method inserts the image into the database
     * @param image The Image object to be inserted into the database
     * @return Returns true if insertion was successful, false otherwise
     */
    public boolean insertImage(Image image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.ImageEntry.COLUMN_NAME_TITLE, image.getUri().getLastPathSegment());
        contentValues.put(Contract.ImageEntry.COLUMN_NAME_DOWNLOAD_TIME, image.downloadTime);
        contentValues.put(Contract.ImageEntry.COLUMN_NAME_URI, image.getUri().toString());
        long result = db.insert(Contract.ImageEntry.TABLE_NAME, null, contentValues);
        return  (result == -1) ? false : true;
    }

    /**
     * This method returns all the images in the database
     * @return Cursor Object that gives all the images in the database
     */
    public Cursor getAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery(SQL_SELECT_ALL, null);
    }
}
