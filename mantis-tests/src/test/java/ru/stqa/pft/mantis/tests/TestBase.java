package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

/**
 * Created by a.oreshnikova on 04.11.17.
 */

public class TestBase {

    protected static final ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws IOException {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"),
                "mantisbt/config_inc.php", "mantisbt/config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("mantisbt/config_inc.php.bak", "mantisbt/config_inc.php");
        app.stop();
    }

    //Функция, которая должна через Remote API получаеи из баг-трекера информацию о баг-репорте с заданным идентификатором,
    // и возвращет значение false или true в зависимости от того, помечен он как исправленный или нет.
    public boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        return !app.soap().getIssueStatus(issueId).equals("fixed");
    }

    //Вызывать её в начале нужного теста, чтобы он пропускался, если баг ещё не исправлен
    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}
