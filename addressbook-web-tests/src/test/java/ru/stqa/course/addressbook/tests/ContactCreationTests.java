package ru.stqa.course.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.Contacts;


import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {


  @Test (enabled = true)
  public void testContactCreation() throws Exception {
    Contacts before = app.contact().all();
    app.goTo().contactPage();
    File photo = new File("src/test/resources/stru.png");
    ContactData contact = new ContactData().withName("test3").withMiddlename("test2").withLastname("test3").withNickname("test4").withPhone("+79110001122").withEmail("test@test.com").withGroup("test1").withPhoto(photo);
    app.contact().create(contact, true);
    assertThat(app.contact().contactCount(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();


    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
  }

  @Test
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/stru.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}
