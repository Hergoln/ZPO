package com.company.lab06pkg;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DataAndTime
{
    public static void doStuff()
    {
        LocalDate startOfWW = LocalDate.of(1939, 9, 1);
        LocalDate endOfWW   = LocalDate.of(1945, 5, 8);

        System.out.println(endOfWW.toEpochDay() - startOfWW.toEpochDay() + 1);

        LocalDate ofYear = LocalDate.ofYearDay(2016, 68);
        System.out.println(ofYear);

        System.out.println(countTimesWithSum(15, LocalTime.of(11, 45), LocalTime.of(22, 30)));
        System.out.println(computeLongerFebsInMaLyv(LocalDate.of(1997, 9, 24)));
    }

    private static int countTimesWithSum(int sum, LocalTime start, LocalTime end)
    {
        int out = 0;
        Integer current, count;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmm");
        while(start.isBefore(end))
        {
            count = 0;
            for(String s : start.format(dtf).split(""))
            {
                current = Integer.parseInt(s);
                count += current;
            }
            if(count == sum) ++out;
            start = start.plusMinutes(1);
        }

        return out;
    }

    private static int computeLongerFebsInMaLyv(LocalDate birthYear)
    {
        int out = 0;

        LocalDate now = LocalDate.now();
        LocalDate begin = LocalDate.of(birthYear.getYear(), birthYear.getMonth(), birthYear.getDayOfMonth());
        while(begin.isBefore(now))
        {
            if(begin.isLeapYear()) ++out;
            begin = begin.plusYears(1);
        }
        if(birthYear.getMonthValue() >= 3 && birthYear.isLeapYear()) --out;
        if(now.getMonthValue() > 3  && now.isLeapYear()) ++out;

        return out < 0 ? 0 : out;
    }
}
