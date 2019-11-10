package com.company.lab02pkg;

public class Lab02Main
{
    public static void main(String[] args)
    {
        Meter meter = new Meter(2, 5);
        System.out.println(meter);
        LevQWERTY lev = new LevQWERTY();

        System.out.println(LevQWERTY.compute("onomatopeja", "onomatopeiczny"));
    }
}
