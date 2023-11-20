package AppLogic;

import java.time.LocalDate;

public class Decision implements java.io.Serializable{
    int id;
    String component;
    String Person;
    int importance;
    String comment;
    LocalDate date;

    Decision(int id, LocalDate date, String component, String Person, int importance, String comment){
        this.id = id;
        this.date = date;
        this.component = component;
        this.Person = Person;
        this.importance = importance;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return id + " | " +  date + " | " + component + " | " + Person + " | " + importance + " | " + comment;
    }
}
