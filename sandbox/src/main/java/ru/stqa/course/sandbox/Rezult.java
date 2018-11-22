package ru.stqa.course.sandbox;

public class Rezult {

    public static void main(String[] args) {
    Point a = new Point ();
      a.x = 0;
      a.y = 0;
    Point b = new Point ();
      b.x = 30;
      b.y = 40;

      System.out.println ("Расстояние между точками"+"("+a.x+","+a.y+") и ("+b.x+","+b.y+")"+" = "+distanse (a, b));
    }

  public static double distanse (Point a, Point b) {

      double a1 = b.x - a.x;
      double b1 = b.y - a.x;
      return Math.sqrt(a1 * a1 + b1 * b1);

  }


}
