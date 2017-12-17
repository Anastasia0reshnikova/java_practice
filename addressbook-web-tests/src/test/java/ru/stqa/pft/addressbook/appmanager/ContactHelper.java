package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a.oreshnikova on 04.11.17.
 */
public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    private void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("email"), contactData.getEmail());
        attach(By.name("photo"), contactData.getPhoto());
        if (creation) {
          if(contactData.getGroups().size() > 0) {
              Assert.assertTrue(contactData.getGroups().size() == 1);
              new Select(wd.findElement(By.name("new_group")))
                      .selectByVisibleText(contactData.getGroups().iterator().next().getName());
          } else {
              Assert.assertFalse(isElementPresent(By.name("new_group")));
          }
        }
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    public void selectContact(int index) {
        wd.findElements(By.cssSelector("[title='Edit']")).get(index).click();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedContact() {
        click(By.cssSelector("[value='Delete']"));
    }

    public void modificationSelectedContact(int index) {
        wd.findElements(By.cssSelector("[title='Edit']")).get(index).click();
    }

    public void initModificationContactById(int id) {
        wd.findElement(By.cssSelector(String.format(("a[href='edit.php?id=%s']"), id))).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    private void goToNewContactPage() {
        if(isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")) {
            return;
        }
        click(By.linkText("add new"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void create(ContactData contact) {
        goToNewContactPage();
        fillContactForm(contact, false);
        submitContactCreation();
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        initModificationContactById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        alert();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("[name='entry']"));
        for (WebElement element: elements) {
            int id = Integer.parseInt(element.findElement(By.cssSelector("td:nth-child(1) input")).getAttribute("value"));
            String lastname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String firstname = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname);
            contacts.add(contact);
        }
        return contacts;
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("[name='entry']"));
        for (WebElement element: elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.cssSelector("td:nth-child(1) input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText().replaceAll("\n", " ");
            String allPhones = cells.get(5).getText();
//            String[] phones = cells.get(5).getText().split("\n"); //порезать строчку
//            ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
//                    .withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]);
            ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAddress(address).withAllEmails(allEmails).withAllPhones(allPhones);
            contacts.add(contact);
        }
        return contacts;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initModificationContactById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withFirstname(firstName).withLastname(lastName).withAddress(address)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withEmail(email).withEmail_2(email2).withEmail_3(email3);
    }

    //____________________ Работа с группами и контактами _________________

    public void addToGroup(ContactData contact, GroupData group) {
        selectContactById(contact.getId());
        selectGroup(group.getName());
        add();
        System.out.println("Контакт добавлен в группу с именем " + group.getName());
    }

    public void removeFromGroup(ContactData contact, GroupData group) {
        selectDeletedGroup(group.getName());
        selectContactById(contact.getId());
        remove();
        System.out.println("Контакт удален из группы с именем " + group.getName());
    }

    private void selectGroup(String groupName) {
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(groupName);
    }

    private void add() {
        click(By.cssSelector("[value='Add to']"));
    }

    private void selectDeletedGroup(String groupName) {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(groupName);
    }

    private void remove() {
        clickWithTimeOut(By.name("remove"));
    }

}
