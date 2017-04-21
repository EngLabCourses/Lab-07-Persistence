package it.englab.androidcourse.lesson6.file;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

import it.englab.androidcourse.lesson6.BaseActivity;
import it.englab.androidcourse.lesson6.R;
import it.englab.androidcourse.lesson6.dialog.AddPersonDialog;

public class SaveFileActivity extends BaseActivity implements AddPersonDialog.PersonListener {
    private static final String FILENAME = "myfile";

    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_prefs);

        result = (TextView) findViewById(R.id.result);

        refreshView();
    }

    @Override
    public void onPersonAdd(String name, String surname, String email, long telephone) {
        String person = String.format("Name: %s\nSurname: %s\nEmail: %s\nTelephone: %s", name,surname,email,String.valueOf(telephone));

        File file = new File(getFilesDir(), FILENAME);
        FileOutputStream outputStream;

        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(person.getBytes());
            outputStream.close();
        } catch (Exception e) {
            Toast.makeText(this, R.string.error_writing_file, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return;
        }

        Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT).show();

        refreshView();
    }

    @Override
    public void refreshView() {
        File file = new File(getFilesDir(), FILENAME);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            Toast.makeText(this, R.string.error_reading_file, Toast.LENGTH_SHORT).show();
            return;
        }

        result.setText("");
        StringBuilder content = new StringBuilder();

        while(sc.hasNextLine()){
            content.append(sc.nextLine()+"\n");
        }

        result.setText(content.toString());
    }
}
