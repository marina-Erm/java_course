package ru.stqa.course.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.Contacts;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

  @DataProvider
   public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
      BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
       String line = reader.readLine();
       while (line != null) {
           String[] split = line.split(";");
    list.add(new Object[] {new ContactData().withName(split[0]).withMiddlename(split[1]).withLastname(split[2]).withNickname(split[3]).withPhoto(new File(split[4])).withAddress(split[5]).withhomePhone(split[6]).withPhone(split[7]).withworkPhone(split[8]).withEmail(split[9]).withEmail2(split[10]).withEmail3(split[11]).withGroup("test1")});
         line = reader.readLine();
       }

    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) throws Exception {
    Contacts before = app.contact().all();
    app.goTo().contactPage();
   // File photo = new File("src/test/resources/stru.png");
  //  ContactData contact = new ContactData().withName("test3").withMiddlename("test2").withLastname("test3").withNickname("test4").withPhone("+79110001122").withEmail("test@test.com").withGroup("test1").withPhoto(photo);
    app.contact().create(contact, true);
    assertThat(app.contact().contactCount(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();


    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/stru.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}
