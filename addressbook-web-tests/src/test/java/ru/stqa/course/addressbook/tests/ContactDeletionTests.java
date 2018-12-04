package ru.stqa.course.addressbook.tests;



import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;;



public class ContactDeletionTests  extends TestBase{

  @Test
  public void testContactDeletion() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()){
        app.getNavigationHelper().gotoContactPage();
app.getContactHelper().createContact(new ContactData("test1", "test2", "test3", "test4", "+79110001122", "test@test.com", "test1"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().initDeletedContacts();
    app.getNavigationHelper().gotoHomePage();

  }

}
