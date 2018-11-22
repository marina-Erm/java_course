package ru.stqa.course.sandbox;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;

    }

    public double distanse(double x, double y) {

        double a1 = this.x - x;
        double b1 = this.y - y;
        return Math.sqrt(a1 * a1 + b1 * b1);
    }

    public double distanse(Point z) {
        return distanse(z.x, z.y);
    }
}