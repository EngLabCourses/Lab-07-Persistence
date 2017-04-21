package it.englab.androidcourse.lesson6;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import it.englab.androidcourse.lesson6.dialog.AddPersonDialog;

/**
 * Created by Peppe on 18/04/2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements AddPersonDialog.PersonListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(this.getClass().getSimpleName());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_person_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_person_add:
                AddPersonDialog dialog = AddPersonDialog.newInstance(this);
                dialog.show(getFragmentManager(), AddPersonDialog.TAG);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public abstract void onPersonAdd(String name, String surname, String email, long telephone);

    public abstract void refreshView();
}
