package ru.stqa.course.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.course.mantis.appmanager.ApplicationManager;

public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.IE));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }


    @AfterSuite (alwaysRun = true )
    public void tearDown() throws Exception {
        app.stop();

    }

  }
