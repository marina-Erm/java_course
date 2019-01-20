package ru.stqa.course.addressbook.tests;



import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactDeletionTests  extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withName("test3").withMiddlename("test2").withLastname("test3").withNickname("test4").withPhone("+79110001122").withEmail("test@test.com"), true);
        }
    }

  @Test (enabled = true)
  public void testContactDeletion() throws Exception {

      Contacts before = app.db().contacts();
      ContactData deletedContact = before.iterator().next();
      app.contact().delet(deletedContact);
      app.goTo().homePage();
      assertEquals(app.contact().contactCount(), before.size()-1);
      Contacts after = app.db().contacts();
      assertThat(after, equalTo(before.without(deletedContact)));
      verifyContactListInUI();
    }

}
