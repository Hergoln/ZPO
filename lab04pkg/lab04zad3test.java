package com.company.lab04pkg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@DisplayName("Test Lab 4 zad 3")
public class lab04zad3test {
    public static List<Double> li;

    @BeforeEach
    public void prep()
    {
        li = new ArrayList<Double>();
        li.add((double)-5100);
        li.add(43.257);
        li.add((double)200000);
        li.add(2000000.5);
    }

    @Test
    public void requiredTest1()
    {
        List<String> fn = FormattNumbers.formattedNumbers(li, 2, ',', 2, true);
        Assertions.assertTrue(fn.size() == 4);
        Assertions.assertTrue(fn.get(0).equals("    -51,00.00"));
        Assertions.assertTrue(fn.get(1).equals("        43.26"));
        Assertions.assertTrue(fn.get(2).equals("  20,00,00.00"));
        Assertions.assertTrue(fn.get(3).equals("2,00,00,00.50"));
    }

    @Test
    public void requiredTest2()
    {
        List<String> fn2 = FormattNumbers.formattedNumbers(li, 3, '|', 2, false);
        Assertions.assertTrue(fn2.size() == 4);
        Assertions.assertTrue(fn2.get(0).equals("   -5|100"));
        Assertions.assertTrue(fn2.get(1).equals("       43.26"));
        Assertions.assertTrue(fn2.get(2).equals("  200|000"));
        Assertions.assertTrue(fn2.get(3).equals("2|000|000.5"));
    }
}
