package ru.stqa.course.addressbook.model;

import java.util.Objects;

public class ContactData {
    private  int id;
    private final String name;
    private final String middlename;
    private final String lastname;
    private final String nickname;
    private final String phone;
    private final String email;
    private String group;

    public ContactData( String name, String middlename, String lastname, String nickname, String phone, String email, String group) {
        this.id = Integer.MAX_VALUE;
        this.name = name;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.phone = phone;
        this.email = email;
        this.group = group;
    }

    public ContactData(int id, String name, String middlename, String lastname, String nickname, String phone, String email, String group) {
        this.id = id;
        this.name = name;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.phone = phone;
        this.email = email;
        this.group = group;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastname, phone, email);
    }

    public String getName() {
        return name;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }

    public int getId() {
        return id;
    }
}
