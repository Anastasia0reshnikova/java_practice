package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.mantis.model.User;
import ru.stqa.pft.mantis.model.Users;

import java.util.List;

/**
 * Created by a.oreshnikova on 24.12.17.
 */

public class AdminHelper extends HelperBase{


    public AdminHelper(ApplicationManager app) {
        super(app);
    }

    //Авторизация в система под администратором
    public void loginByAdmin() {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), app.getProperty("web.adminLogin"));
        type(By.name("password"), app.getProperty("web.adminPassword"));
        click(By.cssSelector("input[value='Login']"));
    }

    //Переход на страницу с Пользователями
    public void goToManageUserPage() {
        click(By.xpath("//span[.='[ Manage Users ]']/a"));
    }

    //Все юзеры на странице
    public Users allUsers() {
        Users users = new Users();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[contains(@class,'row-')]"));
        elements.remove(0);
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String userName = cells.get(0).findElement(By.tagName("a")).getText();
            if (userName.equals(app.getProperty("web.adminLogin"))) {
                continue;
            }
            String email = cells.get(2).getText();
            User user = new User().withUserName(userName).withUserEmail(email);
            users.add(user);
        }
        return users;
    }

    public void changePassword(User user) {
        click(By.linkText(user.getUserName()));
        click(By.cssSelector("input[value='Reset Password']"));
        click(By.linkText("Proceed"));
    }
}
