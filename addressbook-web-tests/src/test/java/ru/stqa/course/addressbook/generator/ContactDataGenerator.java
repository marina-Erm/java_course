package ru.stqa.course.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.course.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    public static void main(String args[]) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();

    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        save(contacts, new File(file));
    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getName(), contact.getMiddlename(), contact.getLastname(), contact.getNickname(), contact.getPhoto(), contact.getAddress(), contact.getHomePhone(), contact.getPhone(), contact.getWorkPhone(), contact.getEmail(), contact.getEmail2(), contact.getEmail3()));
        }
        writer.close();
    }


    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withName(String.format("test %s", i)).withMiddlename(String.format("middlename %s", i))
                    .withLastname(String.format("lastname %s", i)).withNickname(String.format("nickname %s", i))
                    .withPhoto(new File("src/test/resources/stru.png")).withAddress(String.format("address %s", i))
                    .withhomePhone(String.format("123456 %s", i)).withPhone(String.format("456789 %s", i))
                    .withworkPhone(String.format("789123 %s", i)).withEmail(String.format("test1@test.com %s", i))
                    .withEmail2(String.format("test2@test.com %s", i)).withEmail3(String.format("test3@test.com %s", i)));
        }
        return contacts;
    }
}
