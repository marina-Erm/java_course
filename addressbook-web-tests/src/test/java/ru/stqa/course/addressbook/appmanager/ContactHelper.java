package ru.stqa.course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.Contacts;
import ru.stqa.course.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        clickCont(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillFormContact(ContactData contactData, boolean creation) {
        typeCont(By.name("firstname"), contactData.getName());
        typeCont(By.name("middlename"), contactData.getMiddlename());
        typeCont(By.name("lastname"), contactData.getLastname());
        typeCont(By.name("nickname"), contactData.getNickname());
        typeCont(By.name("address"), contactData.getAddress());
        typeCont(By.name("home"), contactData.getHomePhone());
        typeCont(By.name("mobile"), contactData.getPhone());
        typeCont(By.name("work"), contactData.getWorkPhone());
        typeCont(By.name("email"), contactData.getEmail());
        typeCont(By.name("email2"), contactData.getEmail2());
        typeCont(By.name("email3"), contactData.getEmail3());
       // attach(By.name("photo"), contactData.getPhoto());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        }
        else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }



    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
    }

    public void deleteSelectedContacts() {
        clickCont(By.xpath("//input[@value='Delete']"));
    }

    public void stop() {
        wd.quit();
    }

    public void initDeletedContacts() {
      wd.switchTo().alert().accept();
    }

    public void initContactModification(int id) {

              wd.findElement(By.cssSelector("a[href*='edit.php?id="+id+"']")).click();

    }


    public void submitContactModification() {
        clickCont(By.xpath("(//input[@name='update'])[2]"));
    }

    public void gotoHome() {
        clickCont(By.linkText("home page"));
    }

    public void create(ContactData contact, boolean create) {
        fillFormContact(contact, create);
        submitContactCreation();
        contactCache = null;
        gotoHome();
    }

    public void modify(ContactData contact) {
       selectContactById(contact.getId());
       initContactModification(contact.getId());
       fillFormContact(contact, false);
       submitContactModification();
       contactCache = null;
       gotoHome();
    }


    public void delet(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        initDeletedContacts();
        contactCache = null;
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int contactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element: elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String name = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            String allPhones = cells.get(5).getText();
            String allEmail = cells.get(4).getText();
            String allAddress = cells.get(3).getText();
            contactCache.add(new ContactData().withId(id)
                    .withName(name).withLastname(lastname).withAllPhones(allPhones).withAllEmail(allEmail).withAllAddress(allAddress));
        }
        return new Contacts(contactCache);
    }


    public ContactData infoFromEditForm(ContactData contact) {
        initContactModification(contact.getId());
        String name = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String phone = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withName(name)
                .withLastname(lastname).withhomePhone(home).withPhone(phone).withworkPhone(work);
    }

    public ContactData infoFromEditFormEmail(ContactData contact) {
        initContactModification(contact.getId());
        String name = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withName(name)
                .withLastname(lastname).withEmail(email).withEmail2(email2).withEmail3(email3);
    }

        public ContactData infoFromEditFormAddress(ContactData contact) {
            initContactModification(contact.getId());
            String name = wd.findElement(By.name("firstname")).getAttribute("value");
            String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
            String address = wd.findElement(By.name("address")).getAttribute("value");

            wd.navigate().back();
            return new ContactData().withId(contact.getId()).withName(name)
                    .withLastname(lastname).withAddress(address);
    }



    public void selectGroup(String group) {
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group);
    }

    public void addInGroup() {
        click(By.xpath("//input[@name='add']"));

    }

    public void goToGroupContacts(GroupData group) {

        click(By.linkText("group page \"" + group.getName() + "\""));
    }

    public void addContactInGroup(ContactData cont, GroupData group) {
        selectContactById(cont.getId());
        selectGroup(group.getName());
        addInGroup();
        goToGroupContacts(group);
    }


    public void deleteContFromGroup(GroupData group, ContactData cont) {
        selectContactById(cont.getId());
        initDeleteContFromGroup();
        goToGroupContacts(group);

    }

    public void initDeleteContFromGroup() {
        click(By.cssSelector("input[name='remove']"));
    }


}
