package it.englab.androidcourse.lesson6.provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import it.englab.androidcourse.lesson6.R;
import it.englab.androidcourse.lesson6.db.PeopleContract;
import it.englab.androidcourse.lesson6.db.Person;
import it.englab.androidcourse.lesson6.sqlite.SQLiteActivity;

public class ContentProviderActivity extends SQLiteActivity {

    @Override
    public void onPersonAdd(String name, String surname, String email, long telephone) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PeopleContract.PeopleEntry.COLUMN_NAME, name);
        contentValues.put(PeopleContract.PeopleEntry.COLUMN_SURNAME, surname);
        contentValues.put(PeopleContract.PeopleEntry.COLUMN_EMAIL, email);
        contentValues.put(PeopleContract.PeopleEntry.COLUMN_TELEPHONE, telephone);

        getContentResolver().insert(Uri.parse("content://" + PeopleContentProvider.AUTHORITY), contentValues);

        Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT).show();
        refreshView();
    }

    @Override
    public void refreshView() {
        Cursor cursor = getContentResolver().query(Uri.parse("content://" + PeopleContentProvider.AUTHORITY), null, null, null, null);

        List<Person> items = new ArrayList<>();

        while (cursor.moveToNext()) {
            Person p = new Person();
            p.setId(cursor.getLong(0));
            p.setName(cursor.getString(1));
            p.setSurname(cursor.getString(2));
            p.setEmail(cursor.getString(3));
            p.setTelephone(cursor.getLong(4));

            items.add(p);
        }

        cursor.close();

        updateList(items);
    }

    @Override
    protected void searchByName(String name) {
        String selection = PeopleContract.PeopleEntry.COLUMN_NAME + " = ?";
        String[] selectionArgs = {name};
        Cursor cursor = getContentResolver().query(Uri.parse("content://" + PeopleContentProvider.AUTHORITY), null, selection, selectionArgs, null);

        List<Person> items = new ArrayList<>();

        while (cursor.moveToNext()) {
            Person p = new Person();
            p.setId(cursor.getLong(0));
            p.setName(cursor.getString(1));
            p.setSurname(cursor.getString(2));
            p.setEmail(cursor.getString(3));
            p.setTelephone(cursor.getLong(4));

            items.add(p);
        }

        cursor.close();

        updateList(items);
    }
}
