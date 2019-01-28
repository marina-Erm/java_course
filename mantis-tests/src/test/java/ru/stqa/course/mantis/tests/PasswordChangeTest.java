package ru.stqa.course.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.course.mantis.model.DataBase;
import ru.stqa.course.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PasswordChangeTest extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }


    @Test
    public void testChangeUserPassword() throws IOException {
        app.ph().login();
        app.ph().goToManagePage();
        DataBase selectedUser = app.ph().selectUser();
        app.ph().goToSelectUser(selectedUser);
        app.ph().passwordChange();

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 20000);
        String confirmationLink = findConfirmationLink(mailMessages, selectedUser.getEmail());
        System.out.println(confirmationLink);
        String changedPassword = "password";
        app.ph().verificationPassword(confirmationLink, selectedUser, changedPassword);

        assertTrue(app.newSession().login(selectedUser.getUsername(), changedPassword));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
