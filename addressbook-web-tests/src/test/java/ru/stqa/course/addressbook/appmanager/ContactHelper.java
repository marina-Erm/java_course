package ru.stqa.course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.course.addressbook.model.ContactData;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        typeCont(By.name("mobile"), contactData.getPhone());
        typeCont(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
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

       // wd.findElements(By.xpath("//img[@alt='Edit']")).get(id).click();
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
        gotoHome();
    }

    public void modify(ContactData contact) {
       selectContactById(contact.getId());
       initContactModification(contact.getId());
       fillFormContact(contact, false);
       submitContactModification();
       gotoHome();
    }


    public void delet(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        initDeletedContacts();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }


    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element: elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String name = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            String phone = cells.get(5).getText();
            String email = cells.get(4).getText();
            contacts.add(new ContactData().withId(id).withName(name).withLastname(lastname).withPhone(phone).withEmail(email));
        }
        return contacts;
    }


}
