package ru.stqa.course.addressbook.tests;



import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;;import java.util.List;


public class ContactDeletionTests  extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.contact().list().size()==0){
            app.goTo().contactPage();
            app.contact().create(new ContactData("test1", "test2", "test3", "test4", "+79110001122", "test@test.com", "test1"), true);
        }
    }

  @Test (enabled = false)
  public void testContactDeletion() throws Exception {

      List<ContactData> before = app.contact().list();
      int index = before.size()-1;
      app.contact().delet(index);
      List<ContactData> after = app.contact().list();
      Assert.assertEquals(after.size(), before.size()-1);

      before.remove(index);
      Assert.assertEquals(before, after);

  }



}
