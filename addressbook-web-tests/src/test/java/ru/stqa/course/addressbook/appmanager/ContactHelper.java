package ru.stqa.course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.course.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        clickCont(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillFormContact(ContactData contactData) {
        typeCont(By.name("firstname"), contactData.getName());
        typeCont(By.name("middlename"), contactData.getMiddlename());
        typeCont(By.name("lastname"), contactData.getLastname());
        typeCont(By.name("nickname"), contactData.getNickname());
        typeCont(By.name("mobile"), contactData.getPhone());
        typeCont(By.name("email"), contactData.getEmail());
    }

    public void selectContact() {
        clickCont(By.id("14"));
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

}
