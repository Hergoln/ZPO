package com.company.lab05pkg;

public class Book implements Publication
{
    private String  author;
    private String  title;
    private Integer pagesCount;

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getTitle()
    {
        return title;
    }

    @Override
    public Integer getPagesCount()
    {
        return pagesCount;
    }

    @Override
    public String toString()
    {
        return "| " + author + " | " + title + "\t| " + pagesCount + " |";
    }

    public Book(String author, String title, Integer pagesCount)
    {
        this.author = author;
        this.title = title;
        this.pagesCount = pagesCount;
    }

    public Book(Book old)
    {
        this(old.author, old.title, old.pagesCount);
    }
}
