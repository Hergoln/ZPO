package com.company.lab07pkg;

import com.company.lab08pkg.MaxSearchAlgorithmsInvoke;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class lab07Main
{
    public static void main(String[] args)
    {
        try
        {
            ColorGraph.drawStuff();
            StefansRandomTest.testGenerators(List.of("E:\\Studia\\Przedmioty_Lodz_\\ZPO\\lab01ZPO\\src\\com\\company\\lab07pkg\\gen1.dat",
                                                    "E:\\Studia\\Przedmioty_Lodz_\\ZPO\\lab01ZPO\\src\\com\\company\\lab07pkg\\gen2.dat",
                                                    "E:\\Studia\\Przedmioty_Lodz_\\ZPO\\lab01ZPO\\src\\com\\company\\lab07pkg\\gen3.dat"));
        }
        catch (IOException ioexc)
        {
            System.out.println(ioexc);
            System.out.println("Probably files are not found");
        }
    }
}
