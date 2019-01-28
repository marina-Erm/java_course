package ru.stqa.course.mantis.model;

public class DataBase {

    public int id;
    public String username;
    public String email;

    public int getId() {
        return id;
    }

    public DataBase withId(int id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public DataBase withUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public DataBase withEmail(String email) {
        this.email = email;
        return this;
    }
}
