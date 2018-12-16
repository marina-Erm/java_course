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
            app.contact().create(new ContactData().withName("test3").withMiddlename("test2").withLastname("test3").withNickname("test4").withPhone("+79110001122").withEmail("test@test.com").withGroup("test1"), true);
        }
    }

  @Test (enabled = true)
  public void testContactDeletion() throws Exception {

      List<ContactData> before = app.contact().list();
      int index = before.size()-1;
      app.contact().delet(index);
      app.goTo().homePage();
      List<ContactData> after = app.contact().list();
      Assert.assertEquals(after.size(), before.size()-1);

      before.remove(index);
      Assert.assertEquals(before, after);

  }



}
