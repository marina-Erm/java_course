package ru.stqa.course.addressbook.appmanager;


import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    FirefoxDriver wd;
    private NavigationHelper navigationHelper;
    private ContactHelper contactHelper ;
    private SessionHelper sessionHelper;
    private GroupHelper groupHelper ;
   // private SessionComplited sessionComplited;

    public void init() {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/group.php");
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("admin", "secret");
        contactHelper = new ContactHelper(wd);
      //  sessionComplited = new SessionComplited(wd);
       // sessionComplited.logOut();
    }






    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }


}
