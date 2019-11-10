package com.company.lab02pkg;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

public class Lab02Test
{
    static LevQWERTY lev;
    double val;

    @BeforeAll
    public static void beforeMethod()
    {
        lev = new LevQWERTY();
    }

    @Test
    public void stringNullTest()
    {
        val = LevQWERTY.compute(null, null);
        Assertions.assertEquals(-1, val, .001);

        val = LevQWERTY.compute(null, "klawiatura");
        Assertions.assertEquals(-1, val, .001);

        val = LevQWERTY.compute("klawiatura", null);
        Assertions.assertEquals(-1, val, .001);
    }

    @Test
    public void identicalStringsTest()
    {
        val = LevQWERTY.compute("pies", "pies");
        Assertions.assertEquals(0., val, .001);

        val = LevQWERTY.compute("klawiatura", "klawiatura");
        Assertions.assertEquals(0., val, .001);
    }

    @Test
    public void correctDifferenceTest()
    {
        val = LevQWERTY.compute("kot", "kita");
        Assertions.assertEquals(1.5, val, .001);

        val = LevQWERTY.compute("kwiat", "kwist");
        Assertions.assertEquals(.5, val, .001);

        val = LevQWERTY.compute("drab", "dal");
        Assertions.assertEquals(2., val, .001);
    }
}
