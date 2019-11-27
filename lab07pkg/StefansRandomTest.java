package com.company.lab07pkg;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class StefansRandomTest
{
    public static void testGenerators(List<String> testFilesPaths) throws IOException
    {
        for(String path : testFilesPaths)
        {
            boolean EOF = false;
            LinkedList<Integer> numbers = new LinkedList<>();
            int beg = 0, end = 0, removedCount = 0, read;
            DataInputStream binaryRead = new DataInputStream(new FileInputStream(path));
            while(!EOF)
                try
                {
                    read = binaryRead.readInt();
                    if(numbers.indexOf(read) != -1)
                    {
                        numbers.add(read);
                        beg = numbers.indexOf(read);
                        end = numbers.size();
                        break;
                    }
                    else
                    {
                        if(numbers.size() > 1000)
                        {
                            numbers.removeFirst();
                            ++removedCount;
                            numbers.addLast(read);
                        }
                        else
                            numbers.add(read);
                    }
                }
                catch (IOException endOfFile)
                {
                    EOF = true;
                }

            binaryRead.close();
            if(numbers.size() <= 1000 || beg != end)
                System.out.println("Bad Seq, " + (removedCount+beg) + ", " + (removedCount+end) + ", period length = "  + (end-beg));
            else
                System.out.println("GoodSeq");
        }
    }
}
