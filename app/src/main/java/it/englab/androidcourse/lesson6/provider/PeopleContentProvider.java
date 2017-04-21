package it.englab.androidcourse.lesson6.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.annotation.NonNull;

import it.englab.androidcourse.lesson6.db.PeopleContract;
import it.englab.androidcourse.lesson6.db.PeopleDBHelper;

public class PeopleContentProvider extends ContentProvider {
    public static final String AUTHORITY = "it.englab.androidcourse.lesson6";

    private PeopleDBHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new PeopleDBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        try {
            cursor = db.query(PeopleContract.PeopleEntry.TABLE_NAME,null,null,null,null,null,null);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }

        return cursor;
    }


    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int res = -1;

        try {
            res = db.delete(PeopleContract.PeopleEntry.TABLE_NAME, selection, selectionArgs);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
            return res;
    }


    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long id = -1;
        try {
            id = db.insert(PeopleContract.PeopleEntry.TABLE_NAME,null,values);
        } catch (SQLiteException e) {
            e.printStackTrace();
            return null;
        }
        return Uri.withAppendedPath(uri, Long.toString(id));
    }


    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return 0;
    }


    @Override
    public String getType(@NonNull Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
