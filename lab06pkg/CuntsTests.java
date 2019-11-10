package com.company.lab06pkg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Counts test")
public class CuntsTests
{
    public Cunts dictionary;

    @Test
    public void containsKeyTest()
    {
        dictionary = new Cunts();
        dictionary.containsKeyMerge("Jan", 1);
        Assertions.assertTrue(dictionary.cuntsDic.containsKey("Jan"));
        Assertions.assertEquals(1, dictionary.cuntsDic.get("Jan"));

        dictionary.containsKeyMerge("Jan", 1);
        Assertions.assertTrue(dictionary.cuntsDic.containsKey("Jan"));
        Assertions.assertEquals(2, dictionary.cuntsDic.get("Jan"));

        dictionary.containsKeyMerge("Kazik", 1);
        Assertions.assertTrue(dictionary.cuntsDic.containsKey("Kazik"));
        Assertions.assertEquals(1, dictionary.cuntsDic.get("Kazik"));

        dictionary.containsKeyMerge("Kazik", 1);
        Assertions.assertTrue(dictionary.cuntsDic.containsKey("Kazik"));
        Assertions.assertEquals(2, dictionary.cuntsDic.get("Kazik"));
    }

    @Test
    public void getWithNullTest()
    {
        dictionary = new Cunts();
        dictionary.getWithNullMerge("Halyna", 2);
        Assertions.assertTrue(dictionary.cuntsDic.containsKey("Halyna"));
        Assertions.assertEquals(2, dictionary.cuntsDic.get("Halyna"));

        dictionary.getWithNullMerge("Halyna", 1);
        Assertions.assertTrue(dictionary.cuntsDic.containsKey("Halyna"));
        Assertions.assertEquals(3, dictionary.cuntsDic.get("Halyna"));

        dictionary.getWithNullMerge("Kazik", 1);
        Assertions.assertTrue(dictionary.cuntsDic.containsKey("Kazik"));
        Assertions.assertEquals(1, dictionary.cuntsDic.get("Kazik"));

        dictionary.containsKeyMerge("Kazik", 4);
        Assertions.assertTrue(dictionary.cuntsDic.containsKey("Kazik"));
        Assertions.assertEquals(5, dictionary.cuntsDic.get("Kazik"));
    }

    @Test
    public void getOrDefaultMerge()
    {
        dictionary = new Cunts();
        dictionary.getOrDefaultMerge("Zenek", 3);
        Assertions.assertTrue(dictionary.cuntsDic.containsKey("Zenek"));
        Assertions.assertEquals(3, dictionary.cuntsDic.get("Zenek"));

        dictionary.getOrDefaultMerge("Zenek", 1);
        Assertions.assertTrue(dictionary.cuntsDic.containsKey("Zenek"));
        Assertions.assertEquals(4, dictionary.cuntsDic.get("Zenek"));

        dictionary.getOrDefaultMerge("Kazik", 1);
        Assertions.assertTrue(dictionary.cuntsDic.containsKey("Kazik"));
        Assertions.assertEquals(1, dictionary.cuntsDic.get("Kazik"));

        dictionary.getOrDefaultMerge("Kazik", 1);
        Assertions.assertTrue(dictionary.cuntsDic.containsKey("Kazik"));
        Assertions.assertEquals(2, dictionary.cuntsDic.get("Kazik"));
    }

    @Test
    public void putIfAbsentTest()
    {
        dictionary = new Cunts();
        dictionary.putIfAbsentMerge("Antek", 4);
        Assertions.assertTrue(dictionary.cuntsDic.containsKey("Antek"));
        Assertions.assertEquals(4, dictionary.cuntsDic.get("Antek"));

        dictionary.putIfAbsentMerge("Antek", 1);
        Assertions.assertTrue(dictionary.cuntsDic.containsKey("Antek"));
        Assertions.assertEquals(5, dictionary.cuntsDic.get("Antek"));

        dictionary.putIfAbsentMerge("Kazik", 1);
        Assertions.assertTrue(dictionary.cuntsDic.containsKey("Kazik"));
        Assertions.assertEquals(1, dictionary.cuntsDic.get("Kazik"));

        dictionary.putIfAbsentMerge("Kazik", 1);
        Assertions.assertTrue(dictionary.cuntsDic.containsKey("Kazik"));
        Assertions.assertEquals(2, dictionary.cuntsDic.get("Kazik"));
    }
}
