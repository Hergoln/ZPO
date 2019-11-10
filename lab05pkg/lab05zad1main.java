package com.company.lab05pkg;

import java.io.IOException;

public class lab05zad1main
{
    public static void main(String[] args)
    {
        System.out.println("Books with patterns");
        System.out.println("Jan study");

        try
        {
            JanStudy jan = new JanStudy("E:\\Studia\\Przedmioty_Lodz_\\ZPO\\lab01ZPO\\src\\com\\company\\lab05pkg\\1500.txt",
                                        2, 2, 3, .5, null);
            jan.simulate(10);
        }
        catch (IOException exc)
        {
            System.out.println(exc);
        }
    }
}
