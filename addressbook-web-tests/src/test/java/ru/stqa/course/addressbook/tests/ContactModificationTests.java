package ru.stqa.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification () {
        app.getNavigationHelper().gotoHomePage();
        int before = app.getContactHelper().getContactCount();
        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().gotoContactPage();
            app.getContactHelper().createContact(new ContactData("test1", "test2", "test3", "test4", "+79110001122", "test@test.com", "test1"), true);
        }
        app.getContactHelper().selectContact(before-1);
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillFormContact(new ContactData("test1", "test2", "test3", "test4", "+79110001122", "test@test.com", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().gotoHome();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);
    }
}
