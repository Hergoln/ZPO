package com.company.lab04pkg;

import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;

public class Sorts
{
    public static void sortStrings(Comparator collator, String[] words)
    {
        String key;
        for(int i = 1; i < words.length; ++i)
        {
            key = words[i];
            int j;
            for (j = i-1; j >= 0  && collator.compare(key, words[j]) < 0; --j)
                words[j+1] = words[j];
            words[j+1] = key;
        }
    }

    public static void fastSortStrings(Collator collator, String[] words)
    {
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return collator.compare(o1, o2);
            }
        });
    }

    public static void sortStrings2(Collator collator, String[] words)
    {
        Arrays.sort(words, (s1, s2)-> collator.compare(s1, s2));
    }
}
