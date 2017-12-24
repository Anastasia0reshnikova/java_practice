package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by a.oreshnikova on 04.11.17.
 */

public class ApplicationManager {

    private final Properties properties;
    private WebDriver wd;

    private String browser;
    private RegistrationHelper registrationHelper;
    private FtpHelper ftpHelper;
    private MailHelper mailHelper;
    private JamesHelper jamesHelper;
    private AdminHelper adminHelper;
    private UserHelper userHelper;
    private SoapHelper soapHelper;

    public ApplicationManager(String browser){
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    //Помощники
    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null) {
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public FtpHelper ftp() {
        if (ftpHelper == null) {
            ftpHelper = new FtpHelper(this);
        }
        return ftpHelper;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public JamesHelper james() {
        if (jamesHelper == null) {
            jamesHelper = new JamesHelper(this);
        }
        return jamesHelper;
    }

    public AdminHelper admin() {
        if (adminHelper == null) {
            adminHelper = new AdminHelper(this);
        }
        return adminHelper;
    }

    public UserHelper user() {
        if (userHelper == null) {
            userHelper = new UserHelper(this);
        }
        return userHelper;
    }

    public SoapHelper soap() {
        if (soapHelper == null) {
            soapHelper = new SoapHelper(this);
        }
        return soapHelper;
    }

    //Инициализировать драйвер
    public WebDriver getDriver() {
        if (wd == null) {
            if (browser.equals(BrowserType.FIREFOX)) {
                System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
                wd = new FirefoxDriver();
            } else if (browser.equals(BrowserType.CHROME)) {
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.IE)) {
                wd = new InternetExplorerDriver();
            }
            wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }
}
