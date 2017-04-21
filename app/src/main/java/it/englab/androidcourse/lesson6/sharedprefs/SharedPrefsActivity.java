package it.englab.androidcourse.lesson6.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import it.englab.androidcourse.lesson6.BaseActivity;
import it.englab.androidcourse.lesson6.R;
import it.englab.androidcourse.lesson6.dialog.AddPersonDialog;

public class SharedPrefsActivity extends BaseActivity implements AddPersonDialog.PersonListener {
    private static final String PREFERENCE_FILE_KEY = "default_prefs";

    private static final String PREFERENCE_NAME_KEY = "person_name";
    private static final String PREFERENCE_SURNAME_KEY = "person_surname";
    private static final String PREFERENCE_EMAIL_KEY = "person_email";
    private static final String PREFERENCE_TELEPHONE_KEY = "person_telephone";

    private TextView result;
    private SharedPreferences sharedpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_prefs);

        result = (TextView) findViewById(R.id.result);
        sharedpref = getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        refreshView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor = sharedpref.edit();
        editor.clear();
        editor.apply();
    }

    @Override
    public void onPersonAdd(String name, String surname, String email, long telephone) {
        SharedPreferences.Editor editor = sharedpref.edit();
        editor.putString(PREFERENCE_NAME_KEY, name);
        editor.putString(PREFERENCE_SURNAME_KEY, surname);
        editor.putString(PREFERENCE_EMAIL_KEY, email);
        editor.putLong(PREFERENCE_TELEPHONE_KEY, telephone);
        editor.apply();

        Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT).show();

        refreshView();
    }

    @Override
    public void refreshView() {
        String defaultValue = "No info";
        long defaultLong = 0;
        String name = sharedpref.getString(PREFERENCE_NAME_KEY, defaultValue);
        String surname = sharedpref.getString(PREFERENCE_SURNAME_KEY, defaultValue);
        String email = sharedpref.getString(PREFERENCE_EMAIL_KEY, defaultValue);
        long telephone = sharedpref.getLong(PREFERENCE_TELEPHONE_KEY, defaultLong);

        result.setText(
                String.format("Name: %s\nSurname: %s\nEmail: %s\nTelephone: %s", name,surname,email,String.valueOf(telephone)));
    }
}
