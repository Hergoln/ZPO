package com.company.lab01pkg;

import java.util.HashMap;

public class IntegersMap {
    HashMap<Integer, String> map;

    public IntegersMap()
    {
        this.map = new HashMap<>();
        this.map.put(0, "zero");
        this.map.put(1, "jeden");
        this.map.put(2, "dwa");
        this.map.put(3, "trzy");
        this.map.put(4, "cztery");
        this.map.put(5, "piec");
        this.map.put(6, "szesc");
        this.map.put(7, "siedem");
        this.map.put(8, "osiem");
        this.map.put(9, "dziewiec");
        this.map.put(10, "dziesiec");
        this.map.put(11, "jedenascie");
        this.map.put(12, "dwanascie");
        this.map.put(13, "trzynascie");
        this.map.put(14, "czternascie");
        this.map.put(15, "pietnascie");
        this.map.put(16, "szesnascie");
        this.map.put(17, "siedemnascie");
        this.map.put(18, "osiemnascie");
        this.map.put(19, "dziewietnascie");
        this.map.put(20, "dwadziescia");
        this.map.put(30, "trzydziesci");
        this.map.put(40, "czterdziesci");
        this.map.put(50, "piecdziesiat");
        this.map.put(60, "szescdziesiat");
        this.map.put(70, "siedemdziesiat");
        this.map.put(80, "osiemdziesiat");
        this.map.put(90, "dziewiecdziesiat");
    }

    public HashMap<Integer, String> getMap()
    {
        return map;
    }

    public String getFromString(String s) throws IndexOutOfBoundsException
    {
        Integer value = Integer.parseInt(s);
        if(value > 99) throw new IndexOutOfBoundsException();
        if(value < 0)
            return "minus " + this.map.get(value*(-1));
        else if(value <= 20)
            return this.map.get(value);
        else
            return this.map.get((value - (value%10))) + " " + this.map.get(value%10);
    }
}
