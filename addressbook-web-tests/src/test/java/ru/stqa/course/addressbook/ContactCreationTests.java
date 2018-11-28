package ru.stqa.course.addressbook;


import org.testng.annotations.*;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    gotoContactPage();
    fillFormContact(new ContactData("test1", "test2", "test3", "test4", "+79110001122", "test@test.com"));
    submitContactCreation();
    logOut();
  }


}
