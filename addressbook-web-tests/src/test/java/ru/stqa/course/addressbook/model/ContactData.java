package ru.stqa.course.addressbook.model;

import java.io.File;
import java.util.Objects;

public class ContactData {
    private  int id = Integer.MAX_VALUE;
    private  String name;
    private  String middlename;
    private  String lastname;
    private  String nickname;
    private  String homePhone;
    private  String phone;
    private  String workPhone;
    private  String email;
    private  String email2;
    private  String email3;
    private  String allEmail;
    private String group;
    private String allPhones;
    private String address;
    private String allAddress;
    private File photo;

    public File getPhoto() {
        return photo;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public String getAllAddress() {
        return allAddress;
    }

    public ContactData withAllAddress(String allAddress) {
        this.allAddress = allAddress;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public String getEmail2() {
        return email2;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public String getEmail3() {
        return email3;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public String getAllEmail() {
        return allEmail;
    }

    public ContactData withAllEmail(String allEmail) {
        this.allEmail = allEmail;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withName(String name) {
        this.name = name;
        return this;
    }

    public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withhomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public ContactData withworkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }


    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(homePhone, that.homePhone) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(workPhone, that.workPhone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(email2, that.email2) &&
                Objects.equals(email3, that.email3) &&
                Objects.equals(allEmail, that.allEmail) &&
                Objects.equals(allPhones, that.allPhones) &&
                Objects.equals(address, that.address) &&
                Objects.equals(allAddress, that.allAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname, homePhone, phone, workPhone, email, email2, email3, allEmail, allPhones, address, allAddress);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", phone='" + phone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", allPhones='" + allPhones + '\'' +
                ", email='" + email + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                ", allEmail='" + allEmail + '\'' +
                ", address='" + address + '\'' +
                ", allAddress='" + allAddress + '\'' +
                ", group='" + group + '\'' +
                '}';
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

    public String getHomePhone() {
        return homePhone;
    }

    public String getPhone() {
        return phone;
    }

    public String getWorkPhone() {
        return workPhone;
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
