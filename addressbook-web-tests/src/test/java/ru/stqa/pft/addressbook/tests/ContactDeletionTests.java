package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by a.oreshnikova on 04.11.17.
 */
public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().goToHomePage();
        if(! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Аня", "Смирнова", "Яндекс",
                    "Санкт-Петербург", "8(812)7887878", "89110110101",
                    "test@mail.ru"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();  //список контактов ДО
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().alert();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList(); //список контактов ПОСЛЕ
        Assert.assertEquals(after.size(), before.size() - 1);

        //Удалить из списка before удаленный контак
        before.remove(before.size() - 1);
        //Проверить, что списки совпадают
        Assert.assertEquals(before, after);
    }
}
