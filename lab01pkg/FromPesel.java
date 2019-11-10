package com.company.lab01pkg;

public class FromPesel {
    String PESEL;
    MyDate bdate;
    boolean sex; // 0 - male, 1 - female

    FromPesel(String PESEL) throws Exception
    {
        this.bdate = new MyDate(PESEL.substring(4,6), PESEL.substring(2, 4), PESEL.substring(0, 2));
        if(PESEL.charAt(10) == '1') sex = false;
        else if(PESEL.charAt(10) == '0') sex = true;
        else throw new Exception();
        this.PESEL = PESEL;
    }
    @Override
    public String toString() {
        return "FromPesel{" +
                "PESEL='" + PESEL + '\'' +
                ", bdate=" + bdate +
                ", sex=" + sex +
                '}';
    }

}
