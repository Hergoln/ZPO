package com.company.lab01pkg;

import java.util.HashMap;

public class SignsMap {
    HashMap<String, String> map;

    public SignsMap()
    {
        this.map = new HashMap<>();
        this.map.put("+", "plus");
        this.map.put("-", "minus");
        this.map.put("*", "razy");
    }

    public HashMap<String, String> getMap() {
        return map;
    }
}
