package ru.stqa.course.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification () {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillFormContact(new ContactData("test1", "test2", "test3", "test4", "+79110001122", "test@test.com"));
        app.getContactHelper().submitContactModification();
        app.getContactHelper().gotoHome();
    }
}
