package ru.stqa.course.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.course.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().createContact(new ContactData("test1", "test2", "test3", "test4", "+79110001122", "test@test.com", "test1"), true);

  }


}
