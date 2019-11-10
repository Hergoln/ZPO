package com.company.lab01pkg;

public class Person {
    String name;
    String surname;
    FromPesel fromPesel;

    public Person(String name, String surname, String PESEL) throws Exception
    {
        this.name = name;
        this.surname = surname;
        if(PESEL == null || PESEL.length() != 11)
        {
            throw new Exception();
        }
        this.fromPesel = new FromPesel(PESEL);
    }
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", fromPesel=" + fromPesel +
                '}';
    }
}
