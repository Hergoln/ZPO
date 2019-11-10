package com.company.lab05pkg;

public class PublisherHouseHistoricalNovels extends PublisherHouse
{
    private String author;

    public PublisherHouseHistoricalNovels(String author)
    {
        this.author = author;
    }

    public String getAuthor()
    {
        return author;
    }

    public HistoricalNovel createBook(String title, Integer pageCounter)
    {
        return new HistoricalNovel(new Book(author, title, pageCounter));
    }
}
