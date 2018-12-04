package ru.stqa.course.addressbook.model;

public class ContactData {
    private final String name;
    private final String middlename;
    private final String lastname;
    private final String nickname;
    private final String phone;
    private final String email;
    private String group;

    public ContactData(String name, String middlename, String lastname, String nickname, String phone, String email, String group) {
        this.name = name;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.phone = phone;
        this.email = email;
        this.group = group;
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
}
