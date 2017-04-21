package it.englab.androidcourse.lesson6.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import it.englab.androidcourse.lesson6.BaseActivity;
import it.englab.androidcourse.lesson6.PeopleAdapter;
import it.englab.androidcourse.lesson6.R;
import it.englab.androidcourse.lesson6.db.PeopleContract;
import it.englab.androidcourse.lesson6.db.PeopleDBHelper;
import it.englab.androidcourse.lesson6.db.Person;
import it.englab.androidcourse.lesson6.dialog.AddPersonDialog;

public class SQLiteActivity extends BaseActivity implements AddPersonDialog.PersonListener, View.OnClickListener {

    private PeopleDBHelper dbHelper;
    protected RecyclerView list;
    protected EditText query;
    protected View search;

    private PeopleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        adapter = new PeopleAdapter();
        list = (RecyclerView) findViewById(R.id.list);
        query = (EditText) findViewById(R.id.query);
        search = findViewById(R.id.search);
        search.setOnClickListener(this);
        list.setLayoutManager(new LinearLayoutManager(list.getContext()));
        list.setAdapter(adapter);

        dbHelper = new PeopleDBHelper(this);


        refreshView();
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }

    @Override
    public void onPersonAdd(String name, String surname, String email, long telephone) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PeopleContract.PeopleEntry.COLUMN_NAME, name);
        values.put(PeopleContract.PeopleEntry.COLUMN_SURNAME, surname);
        values.put(PeopleContract.PeopleEntry.COLUMN_EMAIL, email);
        values.put(PeopleContract.PeopleEntry.COLUMN_TELEPHONE, telephone);

        long id = db.insert(PeopleContract.PeopleEntry.TABLE_NAME, null, values);

        Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT).show();

        refreshView();
    }


    @Override
    public void refreshView() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] selectedColumns = {
                PeopleContract.PeopleEntry._ID,
                PeopleContract.PeopleEntry.COLUMN_NAME,
                PeopleContract.PeopleEntry.COLUMN_SURNAME,
                PeopleContract.PeopleEntry.COLUMN_EMAIL,
                PeopleContract.PeopleEntry.COLUMN_TELEPHONE};

        Cursor cursor = db.query(
                PeopleContract.PeopleEntry.TABLE_NAME,
                selectedColumns,
                null,
                null,
                null,
                null,
                PeopleContract.PeopleEntry.COLUMN_SURNAME + " ASC");

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

    protected void updateList(List<Person> items) {
        adapter.setList(items);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search:
                if (TextUtils.isEmpty(query.getText())) {
                    refreshView();
                } else {
                    String name = query.getText().toString();
                    searchByName(name);
                }
                break;
        }
    }

    protected void searchByName(String name) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] selectedColumns = {
                PeopleContract.PeopleEntry._ID,
                PeopleContract.PeopleEntry.COLUMN_NAME,
                PeopleContract.PeopleEntry.COLUMN_SURNAME,
                PeopleContract.PeopleEntry.COLUMN_EMAIL,
                PeopleContract.PeopleEntry.COLUMN_TELEPHONE};

        String selection = PeopleContract.PeopleEntry.COLUMN_NAME + " = ?";
        String[] selectionArgs = {name};

        Cursor cursor = db.query(
                PeopleContract.PeopleEntry.TABLE_NAME,
                selectedColumns,
                selection,
                selectionArgs,
                null,
                null,
                PeopleContract.PeopleEntry.COLUMN_SURNAME + " ASC");

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
