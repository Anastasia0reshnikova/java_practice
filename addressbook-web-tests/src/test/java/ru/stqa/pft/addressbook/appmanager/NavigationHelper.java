package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
        wd.findElement(By.linkText("add new")).click();
    }

    public void goToHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }
}
