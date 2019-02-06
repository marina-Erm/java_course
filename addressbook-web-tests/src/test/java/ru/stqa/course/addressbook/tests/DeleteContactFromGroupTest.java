package ru.stqa.course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.Contacts;
import ru.stqa.course.addressbook.model.GroupData;
import ru.stqa.course.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }

        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withName("test3").withMiddlename("test2").withLastname("test3").withNickname("test4").withPhone("+79110001122").withEmail("test@test.com"), true);
        }

        for (GroupData group: app.db().groups()) {
            Contacts contacts = app.db().contInGroup(group);
            if (contacts.size() == 0) {
                app.goTo().homePage();
                app.contact().addContactInGroup(app.db().contacts().iterator().next(), group);
                break;
            }
        }
    }

    @Test
    public void testDeleteContactFromGroup() {

        Groups group = app.db().groups();
        GroupData group1 = group.iterator().next();
            app.goTo().listContInGroup(group1);
            Contacts contacts = app.db().contInGroup(group1);

            if (contacts.size() != 0) {

            ContactData cont = contacts.iterator().next();
            Contacts before = app.db().contInGroup(group1);
            app.contact().deleteContFromGroup(group1, cont);
            Contacts after = app.db().contInGroup(group1);

            assertThat(app.contact().contactCount(), equalTo(before.size() - 1));
            assertThat(after, equalTo(before.without(cont.inGroup(group1))));

            verifyContactInGroupListInUI(group1);
            //return;
        }
    }
  }

