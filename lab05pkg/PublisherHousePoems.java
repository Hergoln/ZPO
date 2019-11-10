package com.company.lab05pkg;

public class PublisherHousePoems extends PublisherHouse
{
    private String author;

    public PublisherHousePoems(String author)
    {
        this.author = author;
    }

    public String getAuthor()
    {
        return author;
    }

    public Poem createBook(String title, Integer pageCounter)
    {
        return new Poem(new Book(author, title, pageCounter));
    }
}
