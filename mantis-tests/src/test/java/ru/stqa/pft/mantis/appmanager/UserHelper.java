package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.User;

/**
 * Created by a.oreshnikova on 24.12.17.
 */
public class UserHelper extends HelperBase {

    public UserHelper(ApplicationManager app) {
        super(app);
    }

    public String changePasswordByUser(String url, User user) {
        wd.get(url);
        String selectedUser = wd.findElement(By.cssSelector("[class='login-info-left'] .italic")).getText();
        type(By.name("password"), user.getNewUserPassword());
        type(By.name("password_confirm"), user.getNewUserPassword());
        click(By.cssSelector("input[value='Update User']"));
        return selectedUser;
    }
}
