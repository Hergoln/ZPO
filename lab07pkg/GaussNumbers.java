package com.company.lab07pkg;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class GaussNumbers
{
    public static void main(String[] args)
    {
        assert Double.parseDouble(args[2]) >= 0 : "deviation is negative";
        try
        {
            GaussNumbers.randSomeNumbers(Integer.parseInt(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]), "E:\\Studia\\Przedmioty_Lodz_\\ZPO\\lab01ZPO\\src\\com\\company\\lab07pkg\\GaussRandNumbers");
        }
        catch (IOException exc)
        {
            System.out.println(exc);
        }
    }

    public static void randSomeNumbers(int n, double average, double dev, String path) throws IOException
    {
        ArrayList<Double> numbers = new ArrayList<>();
        ArrayList<Double> readNumbers = new ArrayList<>();
        boolean EOF = false;
        Random r = new Random();
        for(int  i =0; i < n; ++i)
        {
            numbers.add((r.nextGaussian()*dev + average));
        }

        DataOutputStream binaryWrite = new DataOutputStream(new FileOutputStream(path+"Bin"));

        for(int i = 0; i < numbers.size(); ++i)
        {
            binaryWrite.writeDouble(numbers.get(i));
        }
        binaryWrite.close();

        DataInputStream binaryRead = new DataInputStream(new FileInputStream(path+"Bin"));
        FileWriter textWriter = new FileWriter(path+"Txt.txt");
        while(!EOF)
            try
            {
                textWriter.write(Double.toString(binaryRead.readDouble()).replace('.', ',') + "\n");
            }
            catch (IOException endOfFile)
            {
                EOF = true;
            }
        textWriter.flush();
        binaryRead.close();
        binaryWrite.close();
    }
}
