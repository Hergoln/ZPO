package com.company.lab10pkg;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.company.IncorrectDateException;

public class LottoLoco
{
    public static void doStuff()
    {
        try
        {
            Scanner scanner = new Scanner(System.in);
            int choice = 0;
            Document doc = Jsoup.connect("http://megalotto.pl/wyniki").get();
            Element resultsDiv = doc.getElementById("div_dla_tabeli_wyniki_gry");
            Element table = resultsDiv.select("table").get(0);

            Elements rows = table.select("tr");
            List<Element> ahrefs = new ArrayList<>();

            for (Element column: rows)
            {
                Element el = column.child(0);
                if(el.children().size() > 0) ahrefs.add(el.child(0));
            }

            choice = chooseGame(ahrefs);
            String choiceURL = ahrefs.get(choice).attr("abs:href");
            choice = chooseOption();

            switch (choice)
            {
                case 1: // from date
                {
                    System.out.println("Insert Date in format (dd-MM-yyyy):");
                    String date = scanner.nextLine();
                    LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    List<Integer> res = getResultFromDate(localDate, choiceURL);
                    System.out.print("Result: ");
                    if(res.size() <= 0) System.out.println("There is no date like this in Lotto results");
                    else
                        System.out.println(res);
                } break;
                case 2: // hist from year
                {
                    System.out.println("Insert year:");
                    int year = scanner.nextInt();
                    if(LocalDate.of(year, 1, 1).isAfter(LocalDate.now()))
                        throw new IncorrectDateException(String.valueOf(year));
                    Map<Integer, Integer> res = getHistogramFromYear(year, choiceURL);
                    System.out.println("Result:");
                    res.forEach((key, value) -> System.out.println(key + ": " +value));
                } break;
                case 3: // hist from span
                {
                    System.out.println("Insert span (dates in format dd-MM-yyyy):\nFirst date:");
                    String date = scanner.nextLine();
                    LocalDate startDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    System.out.println("Second date:");
                    date = scanner.nextLine();
                    LocalDate endDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                    if(startDate.isAfter(endDate) || endDate.isAfter(LocalDate.now()))
                        throw new IncorrectDateException("(start):" + startDate + " (end):" + endDate);

                    Map<Integer, Integer> res = getHistogramBetweenDates(startDate, endDate, choiceURL);
                    res.forEach((key, value) -> System.out.println(key + ": " +value));
                } break;
                default:
                    System.out.println("Incorrect choice");
                    break;
            }

        }
        catch (IOException exc)
        {
            System.out.println(exc);
        }
        catch (IncorrectDateException inData)
        {
            System.out.println("Not this time, your date (" + inData.getInfo() + ") is incorrect");
        }
    }

    private static int chooseGame(List<Element> table)
    {
        for(int i = 0; i < table.size(); ++i)
        {
            System.out.println(i + ": " + table.get(i).text());
        }

        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static int chooseOption()
    {
        System.out.println("1. Results at date");
        System.out.println("2. Histogram at specified year");
        System.out.println("3. Histogram at specified span");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static List<Integer> getResultFromDate(LocalDate date, String url) throws IOException
    {
//        Document doc = Jsoup.connect(url+fromToURLPart(date, date)).get();
//        List<Integer> toReturn = new ArrayList<>();
//        String numbers = doc.getElementsByClass("numbers_in_list ").text();
//        if(!numbers.equals(""))
//        {
//            for(String s : numbers.split(" "))
//            {
//                toReturn.add(Integer.parseInt(s));
//            }
//        }
//
//        return toReturn;
        // pytanie czy takie coś może być, są tutaj tylko liczby, w przypadku MultiMulti powinny być chyba dwie listy, albo ja już nawet nie wiem xD
        return new ArrayList<>(getHistogramBetweenDates(date, date, url).keySet());
    }

    private static Map<Integer, Integer> getHistogramFromYear(int year, String url) throws IOException
    {
        return getHistogramBetweenDates(LocalDate.of(year, 1, 1), LocalDate.of(year, 12, 31), url);
    }

    private static Map<Integer, Integer> getHistogramBetweenDates(LocalDate start, LocalDate end, String url) throws IOException
    {
        Document doc = Jsoup.connect(url+fromToURLPart(start, end)).get();
        Map<Integer, Integer> histogram = new HashMap<>();

        String numbers = doc.getElementsByClass("numbers_in_list ").text();
        if(!numbers.equals(""))
        {
            for(String s : numbers.split(" "))
            {
                Integer num = Integer.parseInt(s);
                if(histogram.putIfAbsent(num, 1) != null) histogram.replace(num, histogram.get(num)+1);
            }
        }
        return histogram;
    }

    private static String fromToURLPart(LocalDate start, LocalDate end)
    {
        return "/losowania-od-"+start.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))+"-do-"+end.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
