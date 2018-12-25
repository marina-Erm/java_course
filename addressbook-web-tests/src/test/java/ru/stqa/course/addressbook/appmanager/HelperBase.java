package ru.stqa.course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

public class HelperBase {
    protected WebDriver wd;


    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (! text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }

        }

    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
    public boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    protected void clickCont(By loc) {
        wd.findElement(loc).click();
    }

    protected void typeCont(By loc, String textCont) {
        clickCont(loc);
        wd.findElement(loc).clear();
        wd.findElement(loc).sendKeys(textCont);
    }
    protected void attach (By loc, File file) {
      if (file != null) {
        wd.findElement(loc).sendKeys(file.getAbsolutePath());
      }
    }
}
