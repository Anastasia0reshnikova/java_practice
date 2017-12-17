package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

/**
 * Created by a.oreshnikova on 17.12.17.
 */

public class ContactAddToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstname("Елена").withLastname("Николаевна")
                    .withCompany("ООО Привет").withHomePhone("+7(915)8009090").withEmail("privet@mail.ru"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testAddContactToGroup() {
        ContactData contact = app.db().contacts().iterator().next();
        Groups beforeAdd = contact.getGroups(); //контакты в группах до добавления

        Groups groups = app.db().groups(); //группы в БД
        GroupData group = groups.iterator().next();

        if(contact.getGroups().size() == 0 || !contact.getGroups().contains(group)) {
            app.goTo().homePage();
            app.contact().addToGroup(contact, group);
            beforeAdd.add(group);
        }


    }

}
