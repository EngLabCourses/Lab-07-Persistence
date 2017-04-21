package it.englab.androidcourse.lesson6.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import it.englab.androidcourse.lesson6.R;

/**
 * Created by Peppe on 18/04/2017.
 */

public class AddPersonDialog extends DialogFragment {
    public static final String TAG = AddPersonDialog.class.getName();

    private PersonListener listener;
    public static AddPersonDialog newInstance(PersonListener listener) {
        Bundle args = new Bundle();

        AddPersonDialog fragment = new AddPersonDialog();
        fragment.setArguments(args);
        fragment.setListener(listener);
        return fragment;
    }

    private void setListener(PersonListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.activity_person_card, null);

        final EditText name = (EditText) view.findViewById(R.id.name);
        final EditText surname = (EditText) view.findViewById(R.id.surname);
        final EditText email = (EditText) view.findViewById(R.id.email);
        final EditText telephone = (EditText) view.findViewById(R.id.telephone);
        final Button save = (Button) view.findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(name.getText())||TextUtils.isEmpty(surname.getText())||TextUtils.isEmpty(email.getText())||TextUtils.isEmpty(telephone.getText())) {
                    Toast.makeText(getActivity(), R.string.empty_field, Toast.LENGTH_SHORT).show();
                    return;
                }

                if (listener!= null) {
                    listener.onPersonAdd(name.getText().toString(), surname.getText().toString(), email.getText().toString(), Long.parseLong(telephone.getText().toString()));
                    dismiss();
                }
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);

        return builder.create();
    }

    public interface PersonListener {
        void onPersonAdd(String name, String surname, String email, long telephone);
    }

}
