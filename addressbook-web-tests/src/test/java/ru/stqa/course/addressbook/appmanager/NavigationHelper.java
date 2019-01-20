package ru.stqa.course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.course.addressbook.model.GroupData;

public class NavigationHelper extends HelperBase{


    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
              return;
        }
        click(By.linkText("groups"));
    }



    public void contactPage() {
        click(By.linkText("add new"));
    }

    public void listContInGroup(GroupData group) {
        new Select(wd.findElement(By.cssSelector("select[name=\"group\"]")))
                .selectByVisibleText(group.getName());
    }

    public void homePage() {
     if (isElementPresent(By.id("maintable"))) {
       return;
}
        click(By.linkText("home"));
    }
}
