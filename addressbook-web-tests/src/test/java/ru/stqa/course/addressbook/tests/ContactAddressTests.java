package ru.stqa.course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.contact().all().size()==0){
            app.goTo().contactPage();
            app.contact().create(new ContactData().withName("test3").withMiddlename("test2").withLastname("test3").withNickname("test4").withPhone("+79110001122").withEmail("test@test.com"), true);
        }
    }

    @Test
    public void testContactAddress () {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditFormAddress = app.contact().infoFromEditFormAddress(contact);
        assertThat(contact.getAllAddress(), equalTo(mergeAddress(contactInfoFromEditFormAddress)));
    }

    private String mergeAddress (ContactData contact){
        String collect = Arrays.asList(contact.getAddress())
                .stream().filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
        return collect;

    }
}
