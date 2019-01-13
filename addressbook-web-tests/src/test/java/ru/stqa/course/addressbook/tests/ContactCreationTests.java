package ru.stqa.course.addressbook.tests;


import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.Contacts;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

  @DataProvider
   public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>)  xstream.fromXML(xml);
      return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
    }

  }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) throws Exception {
    Contacts before = app.db().contacts();
    app.goTo().contactPage();
   // File photo = new File("src/test/resources/stru.png");
  //  ContactData contact = new ContactData().withName("test3").withMiddlename("test2").withLastname("test3").withNickname("test4").withPhone("+79110001122").withEmail("test@test.com").withGroup("test1").withPhoto(photo);
    app.contact().create(contact.withGroup("test1"), true);
    assertThat(app.contact().contactCount(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();


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
