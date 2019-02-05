package ru.stqa.course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.Contacts;
import ru.stqa.course.addressbook.model.GroupData;

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
            } break;
        }
    }

    @Test
    public void testDeleteContactFromGroup() {

        for (GroupData group: app.db().groups()) {
            app.goTo().listContInGroup(group);
            Contacts contacts = app.db().contInGroup(group);

            if (contacts.size() != 0) {

            ContactData cont = contacts.iterator().next();
            Contacts before = app.db().contInGroup(group);
            app.contact().deleteContFromGroup(group, cont);
            Contacts after = app.db().contInGroup(group);

            assertThat(app.contact().contactCount(), equalTo(before.size() - 1));
            assertThat(after, equalTo(before.without(cont.inGroup(group))));

            verifyContactInGroupListInUI(group);
            return;
        }
    }
  }
}
