package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by a.oreshnikova on 02.11.17.
 */
public class ContactCreationTests extends  TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("Елена").withLastname("Николаевна")
                .withCompany("Яндекс").withAddress("Санкт-Петербург").withHomePhone("8(812)7887878")
                .withMobilePhone("89110110101").withEmail("test@mail.ru");
        app.contact().create(contact);
        app.goTo().homePage();
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size() + 1);
        assertThat(after, equalTo(before.withAdded(contact)));
    }

    @Test
    public void testContactCreationWithPhoto() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        File photo = new File("src/resources/ava.png");
        ContactData contact = new ContactData().withFirstname("Елена").withLastname("Николаевна")
                .withPhoto(photo);
        app.contact().create(contact);
        app.goTo().homePage();
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size() + 1);
        assertThat(after, equalTo(before.withAdded(contact)));
    }

}
