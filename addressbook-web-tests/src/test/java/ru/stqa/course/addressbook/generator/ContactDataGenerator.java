package ru.stqa.course.addressbook.generator;

import ru.stqa.course.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    public static void main (String args[]) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        List<ContactData> contacts = generateContacts(count);
        save(contacts,file);
}

    private static void save(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact: contacts){
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getName(),contact.getMiddlename(),contact.getLastname(),contact.getNickname(),contact.getPhoto(),contact.getAddress(),contact.getHomePhone(),contact.getPhone(),contact.getWorkPhone(),contact.getEmail(),contact.getEmail2(),contact.getEmail3(),contact.getGroup()));
        }
        writer.close();
    }


    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i<count; i++){
            contacts.add(new ContactData().withName(String.format("test %s", i)).withMiddlename(String.format("middlename %s", i))
             .withLastname(String.format("lastname %s", i)).withNickname(String.format("nickname %s", i))
             .withPhoto(new File("src/test/resources/stru.png")).withAddress(String.format("address %s", i))
             .withhomePhone(String.format("123456 %s", i)).withPhone(String.format("456789 %s", i))
             .withworkPhone(String.format("789123 %s", i)).withEmail(String.format("test1@test.com %s", i))
             .withEmail2(String.format("test2@test.com %s", i)).withEmail3(String.format("test3@test.com %s", i)).withGroup(String.format("test1")));
        }
        return contacts;
    }
    }
