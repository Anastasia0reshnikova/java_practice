package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by a.oreshnikova on 18.12.17.
 */

public class RegistrationHelper extends HelperBase{

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    //Создание нового пользователя
    public void start(String userName, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), userName);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Signup']"));
    }

    //Подтверждение пароля
    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User']"));
    }
}
