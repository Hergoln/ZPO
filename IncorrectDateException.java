package com.company;

public class IncorrectDateException extends Exception
{
    private String info;
    public IncorrectDateException(String info)
    {
        this.info = info;
    }

    public String getInfo()
    {
        return info;
    }
}
