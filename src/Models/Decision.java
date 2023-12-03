package Models;

import java.time.LocalDate;

public class Decision implements java.io.Serializable{
    private int id;
    private String component;
    private String Person;
    private int importance;
    private String comment;
    private LocalDate date;

    public Decision(int id, LocalDate date, String component, String Person, int importance, String comment){
        this.setId(id);
        this.setDate(date);
        this.setComponent(component);
        this.setPerson(Person);
        this.setImportance(importance);
        this.setComment(comment);
    }

    @Override
    public String toString() {
        return getId() + " | " + getDate() + " | " + getComponent() + " | " + getPerson() + " | " + getImportance() + " | " + getComment();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getPerson() {
        return Person;
    }

    public void setPerson(String person) {
        Person = person;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
