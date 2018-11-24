package ru.stqa.course.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void testDistanse () {
        Point a = new Point (10,10);
        Point b = new Point (40,50);
        Assert.assertEquals( b.distanse(a), 50.0);
        Point c = new Point (5,10);
        Point d = new Point (35,10);
        Assert.assertEquals( d.distanse(c), 30.0);
    }

     @Test
    public void testDistanseB () {
        Point a = new Point (-40,-50);
        Point b = new Point (-10,-10);
        Assert.assertEquals( b.distanse(a), 50.0);}
}
