package com.company.lab08pkg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

@DisplayName("zad2 test")
public class zad2test
{
    @Test
    public void doesItWorkTest()
    {
        zad2Class first = new zad2Class(), second = new zad2Class();

        first.setFirst("first");
        first.setSecond(1);
        first.setThird(true);
        first.setFourth(Arrays.asList(1, 2));

        second.setFirst("first");
        second.setSecond(1);
        second.setThird(true);
        second.setFourth(Arrays.asList(1, 2));

        Assertions.assertTrue(first.equals(second));

        second.setFourth(Arrays.asList(1, 2, 3));
        Assertions.assertTrue(first.equals(second));

        second.setFirst("not first");
        Assertions.assertFalse(first.equals(second));
    }
}
