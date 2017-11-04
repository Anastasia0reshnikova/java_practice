package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by a.oreshnikova on 04.11.17.
 */
public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void goToGroupPage() {
        click(By.linkText("groups"));
    }

    public void goToNewContactPage() {
        click(By.linkText("add new"));
    }

    public void goToContactPage() {
        click(By.linkText("home"));
    }

    public void goToHomePage() {
        click(By.linkText("home page"));
    }
}
