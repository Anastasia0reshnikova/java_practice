package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by a.oreshnikova on 04.11.17.
 */

public class GroupDeletionTests  extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
    }

}
