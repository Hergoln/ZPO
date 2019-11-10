package com.company.lab03pkg;

public class Cyclist implements Comparable<Cyclist>
{
    private Integer baseTime;
    private Integer currentTime;

    private String surname;

    public Cyclist(String surname, Integer time)
    {
        this.baseTime = time;
        this.surname = surname;
        this.currentTime = time;
    }

    public Boolean reduce()
    {
        if(--currentTime <= 0)
        {
            currentTime = 0;
            return false;
        }
        else
            return true;
    }

    public Integer getBaseTime()
    {
        return baseTime;
    }

    public String getSurname()
    {
        return surname;
    }


    @Override
    public String toString() {
        return surname + ": " + baseTime;
    }

    @Override
    public int compareTo(Cyclist c) {
        if(this.baseTime > c.baseTime)
            return 1;
        else if(this.baseTime < c.baseTime)
            return -1;
        return 0;
    }
}