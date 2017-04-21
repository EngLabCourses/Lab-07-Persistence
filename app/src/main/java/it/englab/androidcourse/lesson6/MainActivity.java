package it.englab.androidcourse.lesson6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import it.englab.androidcourse.lesson6.file.SaveFileActivity;
import it.englab.androidcourse.lesson6.provider.ContentProviderActivity;
import it.englab.androidcourse.lesson6.sharedprefs.SharedPrefsActivity;
import it.englab.androidcourse.lesson6.sqlite.SQLiteActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.shared_prefs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SharedPrefsActivity.class));
            }
        });


        findViewById(R.id.saving_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SaveFileActivity.class));
            }
        });


        findViewById(R.id.sqlite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SQLiteActivity.class));
            }
        });

        findViewById(R.id.contentprovider).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ContentProviderActivity.class));
            }
        });
    }
}
