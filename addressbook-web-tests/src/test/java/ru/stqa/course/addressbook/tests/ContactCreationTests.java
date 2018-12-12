package ru.stqa.course.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.course.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().gotoContactPage();
    ContactData contact = new ContactData("test1", "test2", "test3", "test4", "+79110001122", "test@test.com", "test1");
    app.getContactHelper().createContact(contact, true);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);



    int max = 0;
    for (ContactData c: after) {
      if (c.getId() > max) {
        max = c.getId();
      }
    }
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
  }


}
