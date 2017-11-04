package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by a.oreshnikova on 04.11.17.
 */

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToContactPage();
        app.getContactHelper().selectContact();
        app.getContactHelper().modificationSelectedContact();
        app.getContactHelper().fillContactForm(new ContactData("Татьяна", "Петрова", "Майл",
                "Санкт-Петербург", "8(812)7887878", "89110110101",
                "test@mail.ru"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
    }
}
