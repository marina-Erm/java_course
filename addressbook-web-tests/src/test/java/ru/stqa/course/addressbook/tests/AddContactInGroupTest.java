package ru.stqa.course.addressbook.tests;

import org.hibernate.SessionFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.Contacts;
import ru.stqa.course.addressbook.model.GroupData;
import ru.stqa.course.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactInGroupTest extends TestBase {

    private SessionFactory sessionFactory;

    @BeforeMethod
    public void ensurePreconditions() {
        long n = System.currentTimeMillis();
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withName("test3").withMiddlename("test2").withLastname("test3").withNickname("test4").withPhone("+79110001122").withEmail("test@test.com"), true);
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }

        for (ContactData cont1 : app.db().contacts()) {
            Groups groups1 = app.db().groups();

            if (cont1.getGroups().size() == groups1.size()) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withName(String.format("test%s", n)));
                break;
            }
        }
    }

    @Test
    public void testAddContactInGroup () {
       Contacts cont = app.db().contacts();
    Groups groups = app.db().groups();

    ContactData c1 = cont.iterator().next();
           if (c1.getGroups().size() != groups.size()) {

             groups.removeAll(c1.getGroups());
             GroupData group = groups.iterator().next();
             Contacts before = app.db().contInGroup(group);

               app.goTo().homePage();
               app.contact().addContactInGroup(c1, group);

               Contacts after = app.db().contInGroup(group);

               assertThat(app.contact().contactCount(), equalTo(before.size() + 1));
               assertThat(after, equalTo(before.withAdded(c1.inGroup(group))));

             verifyContactInGroupListInUI(group);
            // return;
            }

        }
    }

