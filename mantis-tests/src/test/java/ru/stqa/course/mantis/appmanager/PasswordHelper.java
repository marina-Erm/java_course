package ru.stqa.course.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.course.mantis.model.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PasswordHelper extends HelperBase {
    public PasswordHelper(ApplicationManager app) {
        super(app);
    }

     public void login () {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");

        type(By.name("username"), app.getProperty("web.adminLogin"));
        click(By.cssSelector("input[value='Войти']"));

        type(By.name("password"), app.getProperty("web.adminPassword"));
        click(By.cssSelector("input[value='Войти']"));
    }

    public void goToManagePage() {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
    }

    public DataBase selectUser() {
        Connection connect = null;
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password=&serverTimezone=UTC");
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery("select id, username, email from mantis_user_table where username <> administrator and enabled = 1");
            List<DataBase> users = new ArrayList<DataBase>();
            while (rs.next()) {
                users.add(new DataBase().withId(rs.getInt("id"))
                        .withUsername(rs.getString("username")).withEmail(rs.getString("email")));
            }
            rs.close();
            st.close();
            connect.close();
            System.out.println(users);
            return users.iterator().next();

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }

    public void goToSelectUser(DataBase username) {
        click(By.linkText(username.getUsername()));
    }

    public void passwordChange() {
        click(By.cssSelector("input[value='Сбросить пароль']"));
    }

    public void verificationPassword(String confirmationLink, DataBase username, String changedPassword) {
        wd.get(confirmationLink);
        type(By.name("realname"), username.getUsername());
        type(By.name("password"), changedPassword);
        type(By.name("password_confirm"), changedPassword);
        click(By.cssSelector("button[type='Submit']"));
    }
}
