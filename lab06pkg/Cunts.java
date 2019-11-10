package com.company.lab06pkg;

import java.util.HashMap;

public class Cunts
{
    public HashMap<String, Integer> cuntsDic;

    public Cunts()
    {
        cuntsDic = new HashMap<>();
    }

    public void containsKeyMerge(String key, Integer value)
    {
        if(cuntsDic.containsKey(key))
            cuntsDic.replace(key, cuntsDic.get(key) + value);
        else
            cuntsDic.put(key, value);
    }

    public void getWithNullMerge(String key, Integer value)
    {
        Integer oldVal;
        if(null == (oldVal = cuntsDic.get(key)))
            cuntsDic.put(key, value);
        else
            cuntsDic.replace(key, oldVal + value);
    }

    public void getOrDefaultMerge(String key, Integer value)
    {
        Integer oldVal;
        if(-2137 == (oldVal = cuntsDic.getOrDefault(key, -2137)))
            cuntsDic.put(key, value);
        else
            cuntsDic.replace(key, oldVal + value);
    }

    public void putIfAbsentMerge(String key, Integer value)
    {
        Integer oldVal;
        if( null != (oldVal = cuntsDic.putIfAbsent(key, value)))
            cuntsDic.replace(key, oldVal+value);
    }
}
