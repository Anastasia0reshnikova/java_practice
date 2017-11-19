package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by a.oreshnikova on 19.11.17.
 */

public class Collections {

    public static void main (String[] args) {
        String[] langs_1 = new String[4];
        langs_1[0] = "Java";
        langs_1[1] = "C#";
        langs_1[2] = "Python";
        langs_1[3] = "PHP";

        String[] langs = {"Java", "C#", "Python", "PHP"};

        List<String> languages =new ArrayList<>();
        languages.add("Java");
        languages.add("C#");
        languages.add("Python");
        languages.add("PHP");

        List<String> languages_1 = Arrays.asList("Java", "C#", "Python", "PHP");

        for (int i = 0; i < langs.length; i++) {
            System.out.println("Я хочу выучить " + langs[i]);
        }

        for (String l: langs) {
            System.out.println("Я хочу выучить " + l);
        }

        for(String l: languages){
            System.out.println("Я хочу выучить " + l);
        }

        for (int i = 0; i < languages.size(); i++) {
            System.out.println("Я хочу выучить " + languages.get(i));
        }
    }
}
