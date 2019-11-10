package com.company.lab05pkg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test Lab 5 zad 1")
public class zad1Test {
    @Test
    public void testDecorator() {
        Publication k1 = new Book("Adam Mickiewicz", "Pan Tadeusz", 340);
        Publication k2 = new Book("Adam Mickiewicz", "Dziady", 130);
        Publication fakeBook;
        Assertions.assertThrows(IllegalWrappingException.class, () -> new BookWithWrapper(k1));
        Publication kk1 = new BookWithSoftCover(k1);
        Assertions.assertThrows(IllegalWrappingException.class, () -> new BookWithHardCover(kk1));
        Publication kk2 = new BookWithHardCover(k2);
        Assertions.assertThrows(IllegalWrappingException.class, () -> new BookWithSoftCover(kk2));
        Publication kkk2 = new BookWithWrapper(kk2);
        Assertions.assertThrows(IllegalWrappingException.class, () -> new BookWithWrapper(kkk2));
        Publication dziadyZAutografemWieszcza =
                new BookWithAutograph(kk2, "Drogiej Hani - Adam Mickiewicz");
        Assertions.assertEquals("| Adam Mickiewicz | Dziady\t| 130 | Hard Cover | Drogiej Hani - Adam Mickiewicz |", dziadyZAutografemWieszcza.toString());

        Publication dziadyZDwomaAutografami;
        Assertions.assertThrows(IllegalWrappingException.class, () -> new BookWithAutograph(dziadyZAutografemWieszcza, "Haniu, to nieprawda, Dziady napisałem ja, Julek Słowacki!"));
    }

    @Test
    public void testBooksFactories()
    {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> PublisherHouse.getInstance("Not in Storage"));

        PublisherHouse historicalPub = PublisherHouse.getInstance("Józef Ignacy Kraszewski");
        Assertions.assertTrue(historicalPub instanceof PublisherHouseHistoricalNovels);
        Book historical = historicalPub.createBook("Masław", 280);
        Assertions.assertTrue(historical instanceof HistoricalNovel);

        PublisherHouse thrillerPub = PublisherHouse.getInstance("Stephen King");
        Assertions.assertTrue(thrillerPub instanceof PublisherHouseThrillers);
        Book thriller = thrillerPub.createBook("It", 1_138);
        Assertions.assertTrue(thriller instanceof Thriller);

        PublisherHouse poemPub = PublisherHouse.getInstance("Juliusz Słowacki");
        Assertions.assertTrue(poemPub instanceof PublisherHousePoems);
        Book poem = poemPub.createBook("Wacław", 1_138);
        Assertions.assertTrue(poem instanceof Poem);
    }
}
