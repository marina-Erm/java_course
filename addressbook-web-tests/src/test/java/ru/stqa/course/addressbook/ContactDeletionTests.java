package ru.stqa.course.addressbook;


import org.openqa.selenium.Alert;
import org.testng.annotations.Test;;
import static org.testng.Assert.*;


public class ContactDeletionTests  extends TestBase{

  @Test
  public void testContactDeletion() throws Exception {
    gotoHomePage();
    selectContact();
    acceptNextAlert = true;
    deleteSelectedContacts();
   assertTrue(  closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));

  }

}
