package com.company.lab05pkg;

public class PublisherHouseThrillers extends PublisherHouse
{
    private String author;

    public PublisherHouseThrillers(String author)
    {
        this.author = author;
    }

    public String getAuthor()
    {
        return author;
    }

    public Thriller createBook(String title, Integer pageCounter)
    {
        return new Thriller(new Book(author, title, pageCounter));
    }
}
