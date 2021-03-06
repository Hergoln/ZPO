package com.company.lab06pkg;
import com.google.common.collect.Lists;

import java.util.*;

public class BiBaby
{
    // (x+5)^{2}-6
    public static void doStuff(double start, double end, double step, double epsilon)
    {
        ArrayList<Double> xAxis = new ArrayList<>();
        int x0; // index
        boolean rising = fn(start) < fn(end);
        while(start <= end)
        {
            xAxis.add(start);
            start += step;
        }

        if(rising)
            x0 = Collections.binarySearch(
                    Lists.transform(xAxis, x -> ((x + 5.) * (x + 5.) - 6)),
                    0.,
                    (Double a, Double b) -> Math.abs(a - b) <= Math.abs(epsilon) ? 0 : a - b < -Math.abs(epsilon) ? -1 : 1);
        else
            x0 = Collections.binarySearch(
                    Lists.transform(xAxis, x -> ((x + 5.) * (x + 5.) - 6)),
                    0.,
                    (Double a, Double b) -> Math.abs(a - b) <= Math.abs(epsilon) ? 0 : a - b < -Math.abs(epsilon) ? 1 : -1);

        if(x0 >= 0 && x0 < xAxis.size())
        {
            System.out.println("x0 = " + xAxis.get(x0));
            System.out.println((xAxis.get(x0) + 5.) * (xAxis.get(x0) + 5.) - 6); // powinno być 0 albo bardzo blisko 0
        }
        else
            System.out.println("Zero points not found");
    }

    private static Double fn(Double x)
    {
        return (x + 5.) * (x + 5.) - 6;
    }
}
