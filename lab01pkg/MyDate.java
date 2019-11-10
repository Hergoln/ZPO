package com.company.lab01pkg;

public class MyDate {
    @Override
    public String toString() {
        return day+"-"+month+"-"+year;
    }

    public MyDate(String day, String month, String year)
    {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    String day;
    String month;
    String year;
}
