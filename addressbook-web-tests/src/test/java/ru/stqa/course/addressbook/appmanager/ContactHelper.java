package ru.stqa.course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.course.addressbook.model.ContactData;

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

    public void selectContact(int ind) {
        wd.findElements(By.name("selected[]")).get(ind).click();

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

    public void initContactModification() {
        clickCont(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification() {
        clickCont(By.xpath("(//input[@name='update'])[2]"));
    }

    public void gotoHome() {
        clickCont(By.linkText("home page"));
    }

    public void createContact(ContactData contact, boolean create) {
        fillFormContact(contact, create);
        submitContactCreation();
        gotoHome();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }
}
