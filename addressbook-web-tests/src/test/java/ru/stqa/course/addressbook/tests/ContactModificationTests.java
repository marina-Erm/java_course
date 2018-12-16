package ru.stqa.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;


import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.contact().all().size()==0){
        app.goTo().contactPage();
        app.contact().create(new ContactData().withName("test3").withMiddlename("test2").withLastname("test3").withNickname("test4").withPhone("+79110001122").withEmail("test@test.com").withGroup("test1"), true);
    }
}

    @Test (enabled = true)
    public void testContactModification () {

        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withName("test3").withMiddlename("test2").withLastname("test3").withNickname("test4").withPhone("+79110001122").withEmail("test@test.com");
        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before,after);
    }


}
