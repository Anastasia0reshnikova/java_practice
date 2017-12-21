package ru.stqa.pft.mantis.model;

/**
 * Created by a.oreshnikova on 21.12.17.
 */
public class MailMessage {

    public String to;
    public String text;

    public MailMessage(String to, String text){
        this.to = to;
        this.text = text;
    }

}
