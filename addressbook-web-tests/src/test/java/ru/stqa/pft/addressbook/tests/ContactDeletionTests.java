package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by a.oreshnikova on 04.11.17.
 */
public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().goToContactPage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().alert();
    }
}
