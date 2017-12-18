package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by a.oreshnikova on 18.12.17.
 */

public class ContactDeleteFromGroupTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstname("Елена").withLastname("Николаевна")
                    .withCompany("ООО Привет").withHomePhone("+7(915)8009090").withEmail("privet@mail.ru"));
            app.goTo().homePage();
        }
        if (app.db().groups().size() == 0) {
            Date date = new Date();
            SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
            GroupData newGroup = new GroupData().withName("Группа" + formatDate.format(date)).
                    withFooter("footer").withHeader("header");
            createGroup(newGroup);
        }
    }

    @Test
    public void testDeleteGroupFromContact() {

        ContactData contact = app.db().contacts().iterator().next();
        Groups beforeRemove = contact.getGroups(); //контакты в группах до добавления

        Groups groups = app.db().groups(); //группы в БД
        GroupData group = groups.iterator().next();

        //контакт не состоит ни в какой группе
        if(contact.getGroups().size() == 0) {
            app.contact().addToGroup(contact,group);
            app.contact().removeFromGroup(contact, group);
        } else { //контакт состоит в группе
            GroupData groupForRemove = contact.getGroups().iterator().next();
            app.contact().removeFromGroup(contact, groupForRemove);
        }

        Groups afterRemove = app.db().contacts().iterator().next().getGroups();
        assertThat(beforeRemove.size() - 1, equalTo(afterRemove.size()));
        assertThat(afterRemove, equalTo(beforeRemove.without(group)));
    }

    private void createGroup(GroupData group) {
        app.goTo().groupPage();
        app.group().create(group);
        app.goTo().homePage();
        System.out.println("Создана новая группа с именем " + group.getName());
    }
}
