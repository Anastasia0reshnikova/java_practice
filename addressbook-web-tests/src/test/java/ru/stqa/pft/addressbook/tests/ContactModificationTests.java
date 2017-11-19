package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by a.oreshnikova on 04.11.17.
 */

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHomePage();
        if(! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Аня", "Смирнова", "Яндекс",
                    "Санкт-Петербург", "8(812)7887878", "89110110101",
                    "test@mail.ru"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().modificationSelectedContact();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Кристина", "Петрова",
                null,null, null, null,null);
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        //Удалить последний контакт из списка before
        before.remove(before.size() - 1);
        //Добавить модифицированные контакт в список before
        before.add(contact);
        //Сортировка списов по id
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }
}
