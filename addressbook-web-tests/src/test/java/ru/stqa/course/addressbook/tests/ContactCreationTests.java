package ru.stqa.course.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.course.addressbook.model.ContactData;



import java.util.Set;


public class ContactCreationTests extends TestBase {


  @Test (enabled = true)
  public void testContactCreation() throws Exception {
    Set<ContactData> before = app.contact().all();
    app.goTo().contactPage();
    ContactData contact = new ContactData().withName("test3").withMiddlename("test2").withLastname("test3").withNickname("test4").withPhone("+79110001122").withEmail("test@test.com").withGroup("test1");
    app.contact().create(contact, true);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before,after);
  }


}
