package ba.bitcamp.android.week23day3;

import android.text.Editable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by Rasta on 22.10.2015.
 */
public class PersonList {

    private List<Model> personList;

    public PersonList(){
        personList = new ArrayList<>();
    }

    public void addPerson(Editable name, Editable surname){
        personList.add(new Model(name.toString(), surname.toString()));
    }

    public void editPerson(String id, String name, String surname){
        for (int i = 0; i < personList.size(); i++){
            if (personList.get(i).getId().equals(id)){
                personList.get(i).setName(name);
                personList.get(i).setSurname(surname);
                return;
            }
        }
    }

    public void deletePerson(UUID id){
        Iterator<Model> person = personList.iterator();
        while (person.hasNext()){
            if(person.next().getId().equals(id)){
                person.remove();
                return;
            }
        }
    }

    public Model getPerson(int id){
        return personList.get(id);
    }

    public int getSize(){
        return personList.size();
    }
}
