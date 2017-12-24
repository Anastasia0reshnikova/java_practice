package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.User;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by a.oreshnikova on 24.12.17.
 */

public class ChangePasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangeUserPasswordByAdmin() throws IOException, MessagingException {
        String newPassword = "12345qwe";
        //Администратор отправляет запрос на изменение пароля юзеру
        app.admin().loginByAdmin();
        app.admin().goToManageUserPage();
        User user = app.admin().allUsers().iterator().next().withNewUserPassword(newPassword);
        app.admin().changePassword(user);
        //Извлекается ссылка
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, user.getUserEmail());
        String selectedUser = app.user().changePasswordByUser(confirmationLink, user);
        // Проверки 1.Выбранный юзер для смены пароля верный 2.Юзер может войти в систему
        assertEquals(user.getUserName(), selectedUser);
        assertTrue(app.newSession().login(user.getUserName(), newPassword));
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
