package ru.stqa.course.addressbook.tests;



import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;;import java.util.List;


public class ContactDeletionTests  extends TestBase{

  @Test
  public void testContactDeletion() throws Exception {
       app.getNavigationHelper().gotoHomePage();

    if (! app.getContactHelper().isThereAContact()){
        app.getNavigationHelper().gotoContactPage();
app.getContactHelper().createContact(new ContactData("test1", "test2", "test3", "test4", "+79110001122", "test@test.com", "test1"), true);
    }
      List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().initDeletedContacts();
      app.getNavigationHelper().gotoHomePage();
      List<ContactData> after = app.getContactHelper().getContactList();
      Assert.assertEquals(after.size(), before.size()-1);

      before.remove(before.size()-1);
      Assert.assertEquals(before, after);

  }

}
