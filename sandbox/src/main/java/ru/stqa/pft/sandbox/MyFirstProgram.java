package ru.stqa.pft.sandbox;

/**
 * Created by a.oreshnikova on 22.10.17.
 */
public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");
        hello("user");
        hello("Anastasia");

        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(4, 6);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
    }

    public static void hello(final String somebody) {
        System.out.println("Hello, " + somebody + "!");
    }
}
