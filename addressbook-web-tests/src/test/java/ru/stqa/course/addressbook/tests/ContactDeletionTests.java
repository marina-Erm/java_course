package ru.stqa.course.addressbook.tests;



import org.testng.annotations.Test;;



public class ContactDeletionTests  extends TestBase{

  @Test
  public void testContactDeletion() throws Exception {
    app.gotoHomePage();
    app.selectContact();
    app.deleteSelectedContacts();
    app.initDeletedContacts();

  }

}
