package com.company.lab10pkg;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class LottoLoco
{
    public static void doStuff()
    {
        try
        {
            Document doc = Jsoup.connect("http://megalotto.pl/wyniki").get();
            Element resultsDiv = doc.getElementById("div_dla_tabeli_wyniki_gry");
            Element table = resultsDiv.select("table").get(0);
            Elements rows = table.select("tr");

            for (Element column: rows)
            {
                
            }
            
            System.out.println(doc);
        }
        catch (IOException exc)
        {
            System.out.println(exc);
        }
    }
}
