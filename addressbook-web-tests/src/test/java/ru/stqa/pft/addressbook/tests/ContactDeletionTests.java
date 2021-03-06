package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by a.oreshnikova on 04.11.17.
 */
public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstname("Аня").withLastname("Смирнова")
                    .withCompany("Яндекс").withAddress("Санкт-Петербург").withHomePhone("8(812)7887878")
                    .withMobilePhone("89110110101").withEmail("test@mail.ru"));
        }
    }

    @Test
    public void testContactDeletion() {
        Contacts before = app.db().contacts();  //список контактов ДО
        ContactData deletedContact = before.iterator().next();
        app.goTo().homePage();
        app.contact().delete(deletedContact);
        app.goTo().homePage();
        Contacts after = app.db().contacts(); //список контактов ПОСЛЕ
        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
