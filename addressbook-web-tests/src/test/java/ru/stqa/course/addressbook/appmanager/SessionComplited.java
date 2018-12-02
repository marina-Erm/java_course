package ru.stqa.course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SessionComplited {
    private WebDriver wd;

    public SessionComplited(WebDriver wd) {

        this.wd = wd;
    }
    public void logOut() {
        wd.findElement(By.linkText("Logout")).click();
    }
}
