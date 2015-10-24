package ba.bitcamp.android.week23day3;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRAS_NAME = "name";
    public static final String EXTRAS_SURNAME = "surname";
    public static final String EXTRAS_ID = "id";

    public PersonList personList = new PersonList();
    private EditText name;
    private EditText surname;
    private Button addButton;
    private RadioButton sortButton;
    private RecyclerView recyclerView;
    private PersonAdapter personAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.input_name);
        surname = (EditText) findViewById(R.id.input_surname);
        addButton = (Button) findViewById(R.id.add_button);
        sortButton = (RadioButton) findViewById(R.id.sort_button);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        personAdapter = new PersonAdapter(personList);
        recyclerView.setAdapter(personAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable Fname = name.getText();
                Editable Sname = surname.getText();

                personList.addPerson(Fname, Sname);
                personAdapter.notifyDataSetChanged();
            }
        });

        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private class PersonView extends RecyclerView.ViewHolder{

        private TextView nameText;
        private TextView surnameText;
        private TextView timeText;
        private Button deleteButton;
        private Button editButton;
        private Model person;

        public PersonView(final View view){
            super(view);
            nameText = (TextView) view.findViewById(R.id.name_text);
            surnameText = (TextView) view.findViewById(R.id.surname_text);
            timeText = (TextView) view.findViewById(R.id.time_text);
            deleteButton = (Button) view.findViewById(R.id.delete_button);
            editButton = (Button) view.findViewById(R.id.edit_button);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    personList.deletePerson(person.getId());
                    personAdapter.notifyDataSetChanged();
                }
            });
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, EditPerson.class);
                    intent.putExtra(EXTRAS_NAME, person.getName());
                    intent.putExtra(EXTRAS_SURNAME, person.getSurname());
                    intent.putExtra(EXTRAS_ID, person.getId().toString());
                    startActivity(intent);
                }
            });
        }

    }

    private class PersonAdapter extends RecyclerView.Adapter<PersonView>{

        private PersonList personList;

        public PersonAdapter(PersonList list){
            personList = list;
        }


        @Override
        public PersonView onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            View view = inflater.inflate(R.layout.list_view, parent, false);
            return new PersonView(view);
        }

        @Override
        public void onBindViewHolder(PersonView holder, int position) {
            Model person = personList.getPerson(position);

            holder.nameText.setText(person.getName());
            holder.surnameText.setText(person.getSurname());
            holder.timeText.setText(person.getDate().toString());
            holder.person = person;
        }

        @Override
        public int getItemCount() {
            return personList.getSize();
        }
    }
}
