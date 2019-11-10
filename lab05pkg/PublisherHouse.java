package com.company.lab05pkg;

import java.util.HashMap;
import java.util.Map;

public abstract class PublisherHouse
{
    private static Map<String, Class> publishersMap;

    static {
        publishersMap = new HashMap<>();
        publishersMap.put("Józef Ignacy Kraszewski",    PublisherHouseHistoricalNovels.class);
        publishersMap.put("Stephen King",               PublisherHouseThrillers.class);
        publishersMap.put("Juliusz Słowacki",           PublisherHousePoems.class);
    }

    public static PublisherHouse getInstance(String author)
    {
        try
        {
            Object toReturn = publishersMap.get(author).getConstructor(String.class).newInstance(author);
            return (PublisherHouse)toReturn;
        }
        catch (Exception exc)
        {
            System.out.println(exc);
            throw new IndexOutOfBoundsException("No such auhtor in publishers storage");
        }
    }

    public abstract Book createBook(String title, Integer pageCounter);

}
