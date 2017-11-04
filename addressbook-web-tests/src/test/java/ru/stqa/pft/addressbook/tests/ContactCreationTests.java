package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by a.oreshnikova on 02.11.17.
 */
public class ContactCreationTests extends  TestBase{

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToNewContactPage();
        app.getContactHelper().fillContactForm(new ContactData("Аня", "Смирнова", "Яндекс",
                "Санкт-Петербург", "8(812)7887878", "89110110101",
                "test@mail.ru"));
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().goToHomePage();
    }

}
