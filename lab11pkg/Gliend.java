package com.company.lab11pkg;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Gliend
{
    public static void main(String[] args) throws IOException
    {
        try(Socket socket = new Socket("localhost", 2137))
        {
            InputStreamReader input = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);
            OutputStreamWriter output = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);
            Random rand = new Random();
            char[] inputSeq = new char[32];
            String name = args[0];
            //             -2 0                   20      28        38               55          67     74
            String route = "  S                   |       |         |                |           |      M";
            StringBuilder bike = new StringBuilder("o&o\r");

            output.write(name + "\0");
            output.flush();
            input.read(inputSeq);
            System.out.println(inputSeq);

            System.out.println(route);
            for(int i = 0; i < route.length()-3; ++i)
            {
                Thread.sleep(rand.nextInt(121) + 30);
                bike.insert(0, ".");
                System.out.print(bike);
                if(i == 20 || i == 28 || i == 38 || i == 55 || i ==67 || i == 73)
                {
                    output.write(name + "\0");
                    output.flush();
                }

            }

            input.read(inputSeq);
            System.out.println("\n" + name + " is on " + inputSeq.toString().trim() + " place on Finish");

        }
        catch (InterruptedException exc)
        {
            System.out.println("Should never happen");
        }
    }
}
