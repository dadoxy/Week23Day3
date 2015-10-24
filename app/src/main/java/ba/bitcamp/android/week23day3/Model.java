package ba.bitcamp.android.week23day3;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Rasta on 21.10.2015.
 */
public class Model {

    private String name;
    private String surname;
    private UUID id;
    private Date date;

    public Model(String name, String surname){
        id = UUID.randomUUID();
        this.name = name;
        this.surname = surname;
        date = Calendar.getInstance().getTime();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
