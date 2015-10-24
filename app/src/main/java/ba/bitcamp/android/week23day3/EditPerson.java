package ba.bitcamp.android.week23day3;

import android.content.Intent;
import android.graphics.AvoidXfermode;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by Rasta on 24.10.2015.
 */
public class EditPerson extends AppCompatActivity {

    public PersonList personList = new PersonList();
    private Model person;
    private EditText name;
    private EditText surname;
    private Button editButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit_layout);

        name = (EditText) findViewById(R.id.editName);
        surname = (EditText) findViewById(R.id.editSurname);
        editButton = (Button) findViewById(R.id.editButton);

        String firstName = getIntent().getExtras().get(MainActivity.EXTRAS_NAME).toString();
        String lastName = getIntent().getExtras().get(MainActivity.EXTRAS_SURNAME).toString();
        final String id = getIntent().getExtras().get(MainActivity.EXTRAS_ID).toString();

        name.setText(firstName);
        surname.setText(lastName);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = name.getText().toString();
                String lastName = surname.getText().toString();

                personList.editPerson(id, firstName, lastName);
                
            }
        });

    }
}
