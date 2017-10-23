package ru.stqa.pft.sandbox;

/**
 * Created by a.oreshnikova on 22.10.17.
 */
public class Rectangle {

    public double a;
    public double b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double area ()  {
        return this.a * this.b;
    }
}
