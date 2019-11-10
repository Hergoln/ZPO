package com.company.lab04pkg;

import org.junit.jupiter.api.*;

import java.text.Collator;
import java.util.Locale;

public class lab04zad1test
{
    @DisplayName("Test Lab 4 zad 1")
    @Test
    public void stringTest()
    {
        Collator collator = Collator.getInstance(new Locale("pl", "PL"));
        String[] wordsSort = {"Łukasz", "Ścibor", "Stefania", "Darek", "Agnieszka",
            "Zyta", "Órszula", "Świętopełk"};
        String[] wordsSort2 = {"Łukasz", "Ścibor", "Stefania", "Darek", "Agnieszka",
                "Zyta", "Órszula", "Świętopełk"};
        String[] wordsFastSort = {"Łukasz", "Ścibor", "Stefania", "Darek", "Agnieszka",
                "Zyta", "Órszula", "Świętopełk"};

        Sorts.sortStrings(collator, wordsSort);
        Sorts.sortStrings2(collator, wordsSort2);
        Sorts.fastSortStrings(collator, wordsFastSort);

        for (int i = 0; i < wordsSort.length; ++i) {
            Assertions.assertEquals(wordsSort[i], wordsSort2[i]);
            Assertions.assertEquals(wordsSort[i], wordsFastSort[i]);
        }
    }

    @Test
    public void stringEfficiencyTest()
    {
        Collator collator = Collator.getInstance(new Locale("pl", "PL"));
        String[] words = {"Łukasz", "Ścibor", "Stefania", "Darek", "Agnieszka",
                "Zyta", "Órszula", "Świętopełk"};

        long timeStart = 0, timeStop = 0,
            timeSort = 0, timeSort2 = 0, timeFastSort = 0;

        timeStart = System.currentTimeMillis();
        for(int i = 0; i < 1000; ++i)
        {
            Sorts.sortStrings(collator, words);
            words = new String[] {"Łukasz", "Ścibor", "Stefania", "Darek", "Agnieszka",
                    "Zyta", "Órszula", "Świętopełk"};
        }
        timeStop = System.currentTimeMillis();
        timeSort = timeStop - timeStart;

        timeStart = System.currentTimeMillis();
        for(int i = 0; i < 1000; ++i)
        {
            Sorts.sortStrings2(collator, words);
            words = new String[] {"Łukasz", "Ścibor", "Stefania", "Darek", "Agnieszka",
                    "Zyta", "Órszula", "Świętopełk"};
        }
        timeStop = System.currentTimeMillis();
        timeSort2 = timeStop - timeStart;

        timeStart = System.currentTimeMillis();
        for(int i = 0; i < 1000; ++i)
        {
            Sorts.fastSortStrings(collator, words);
            words = new String[] {"Łukasz", "Ścibor", "Stefania", "Darek", "Agnieszka",
                    "Zyta", "Órszula", "Świętopełk"};
        }
        timeStop = System.currentTimeMillis();
        timeFastSort = timeStop - timeStart;

        System.out.println("sort: " +timeSort +
                            "\nsort2: " + timeSort2 +
                            "\nfastSort: " + timeFastSort);
    }
}
