package ru.stqa.pft.sandbox;

/**
 * Created by a.oreshnikova on 22.10.17.
 */
public class Square {

    public double l;

    public Square(double l) {
       this.l = l;
    }

    public double area ()  {
        return this.l * this.l;
    }
}
