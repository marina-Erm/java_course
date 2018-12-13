package ru.stqa.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification () {
        app.getNavigationHelper().gotoHomePage();

        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().gotoContactPage();
            app.getContactHelper().createContact(new ContactData("test1", "test2", "test3", "test4", "+79110001122", "test@test.com", "test1"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size()-before.size());
        app.getContactHelper().initContactModification();
        ContactData contact = new ContactData(before.get(before.size()-before.size()).getId(),"test1", "test2", "test3", "test4", "+79110001122", "test@test.com", null);
        app.getContactHelper().fillFormContact(contact, false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().gotoHome();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size()-before.size());
        before.add(contact);
        Comparator<? super ContactData> byId = (c1,c2) -> Integer.compare(c1.getId(),c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }
}
