package ru.stqa.course.addressbook.tests;



import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;;



public class ContactDeletionTests  extends TestBase{

  @Test
  public void testContactDeletion() throws Exception {
       app.getNavigationHelper().gotoHomePage();
      int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAContact()){
        app.getNavigationHelper().gotoContactPage();
app.getContactHelper().createContact(new ContactData("test1", "test2", "test3", "test4", "+79110001122", "test@test.com", "test1"), true);
    }
    app.getContactHelper().selectContact(before-1);
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().initDeletedContacts();
    app.getNavigationHelper().gotoHomePage();
      int after = app.getContactHelper().getContactCount();
      Assert.assertEquals(after, before - 1);

  }

}
