package ru.stqa.course.addressbook;



import org.testng.annotations.Test;;



public class ContactDeletionTests  extends TestBase{

  @Test
  public void testContactDeletion() throws Exception {
    gotoHomePage();
    selectContact();
    deleteSelectedContacts();
    initDeletedContacts();

  }

}
