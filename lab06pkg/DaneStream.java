package com.company.lab06pkg;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.stream.Collectors;

public class DaneStream
{
    public static void doStuff(String path) throws IOException
    {
        System.out.println(Files.lines(Paths.get(path), StandardCharsets.ISO_8859_1)
                .map(s -> s.split(" "))
                .filter(s -> s[2].equals("PL"))
                .mapToInt(s -> Integer.parseInt(s[3]))
                .sorted()
                .limit(2)
                .sum());

        System.out.println(Files.lines(Paths.get(path), StandardCharsets.ISO_8859_1)
                .map(s -> s.split(" "))
                .filter(s -> s[2].equals("PL"))
                .mapToInt(s -> Integer.parseInt(s[3]))
                .boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(s -> s)
                .limit(2)
                .sum());

        Files.lines(Paths.get(path), StandardCharsets.ISO_8859_1)
                .map(s -> s.split(" "))
                .collect(Collectors.groupingBy(s -> s[2]))
                .forEach((country, people) -> System.out.println(country + " : " + people.size()));
    }
}
