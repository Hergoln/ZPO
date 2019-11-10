package com.company.lab05pkg;

public class BookWithAutograph implements Publication
{
    private String autograph;
    private Publication publication;
    public BookWithAutograph(Publication pub, String autograph) throws IllegalWrappingException
    {
        if(pub instanceof BookWithAutograph) throw new IllegalWrappingException("Book can have only one autograph");
        this.publication = pub;
        this.autograph = autograph;
    }

    @Override
    public String getAuthor() {
        return this.publication.getAuthor();
    }

    @Override
    public String getTitle()
    {
        return this.publication.getTitle();
    }

    @Override
    public Integer getPagesCount()
    {
        return this.publication.getPagesCount();
    }

    public String getAutograph()
    {
        return autograph;
    }

    @Override
    public String toString()
    {
        return this.publication.toString() + " " + autograph + " |";
    }
}
