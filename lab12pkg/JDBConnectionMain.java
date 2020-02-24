package com.company.lab12pkg;

import java.sql.*;
import java.util.Scanner;

public class JDBConnectionMain
{
//    do wrzucenia takie ludziki
//    Jan Kowalski PL 35000
//    Helmut Schnittke DE 45000
//    Jiri Prohazka CZ 28000
//    Anna Malinowska PL 52000
//    Józef Bšk PL 49999
//    Kleofas Ogiński PL 45000
//    John Bull US 74000
//    Łukasz Żółw PL 9400
//    Franz Beckenbauer DE 83000
//    Frantisek Kupka CZ 32000
//    Jakub Kowal PL 29500
//    Jim Hart US 57900
//    Tomas Svoboda CZ 48500
    public static void main(String[] argv) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int control = 0;
        Pracownik prac = new Pracownik();
        String field = "";

        while(true)
        {
            try
            {
                System.out.println("1. Dodal Pracownika\n" +
                                    "2. Wyswietl posortowana tablice\n" +
                                    "3. Srednia plac dla kraju\n" +
                                    "4. Koniec");
                control = Integer.parseInt(sc.nextLine());
                switch (control)
                {
                    case 1:
                        System.out.println("Imie: ");
                        prac.Imie = sc.nextLine();
                        System.out.println("Nazwisko: ");
                        prac.Nazwisko = sc.nextLine();
                        System.out.println("Kraj: ");
                        prac.Kraj = sc.nextLine();
                        System.out.println("Placa: ");
                        prac.Placa = Integer.parseInt(sc.nextLine());
                        dodajPracownika(prac);
                        break;
                    case 2:
                        System.out.println("Podaj pole");
                        field = sc.nextLine();
                        wyswietlPracownikow(field);
                        break;
                    case 3:
                        wyswietlSrednie("PL");
                        wyswietlSrednie("DE");
                        break;
                    case 4:
                        return;
                }
            }
            catch (SQLException exc)
            {
                System.out.println(exc);
            }
        }
    }

    public static boolean dodajPracownika(Pracownik pracownik) throws SQLException
    {
        boolean result = false;
        // connection string 1) DB server addres, 2) password
        try(Connection conn = DriverManager.getConnection("***********************************************", "root", "*****");
            Statement queryProvider = conn.createStatement();)
        {
            result = queryProvider.execute("INSERT INTO jahfa_kufa.pracownicy (imie, nazwisko, kraj, placa) VALUES(\"" +
                    pracownik.Imie + "\", \"" +
                    pracownik.Nazwisko + "\", \"" +
                    pracownik.Kraj + "\", " +
                    pracownik.Placa + ")");
        }

        return result;
    }

    public static void wyswietlPracownikow(String field) throws SQLException
    {
        // connection string 1) DB server addres, 2) password
        try(Connection conn = DriverManager.getConnection("***********************************************", "root", "*****";
            Statement queryProvider = conn.createStatement();)
        {
            ResultSet rs = queryProvider.executeQuery("SELECT * FROM jahfa_kufa.pracownicy ORDER BY " + field + " ASC");
            while(rs.next())
            {
                System.out.println( "Name: " + rs.getString("imie") + ", " +
                                    "Surname: " + rs.getString("nazwisko") + ", " +
                                    "Country: " + rs.getString("kraj") + ", " +
                                    "Pay: " + rs.getInt("placa")
                );
            }
        }
    }

    public static void wyswietlSrednie(String country) throws SQLException
    {
        // connection string 1) DB server addres, 2) password
        try(Connection conn = DriverManager.getConnection("***********************************************", "root", "*****");
            Statement queryProvider = conn.createStatement();)
        {
            ResultSet rs = queryProvider.executeQuery("SELECT AVG(placa) as srednia FROM jahfa_kufa.pracownicy WHERE kraj = \"" + country + "\"");
            if(rs.next())
            {
                System.out.println("srednia dla " + country + ": " + rs.getInt("srednia"));
            }
        }
    }
}
