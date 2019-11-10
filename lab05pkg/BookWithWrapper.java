package com.company.lab05pkg;

public class BookWithWrapper implements Publication
{
    private Publication publication;
    public BookWithWrapper(Publication pub) throws IllegalWrappingException
    {
        if(!(pub instanceof BookWithSoftCover) && !(pub instanceof BookWithHardCover)) throw new IllegalWrappingException("Book without cover can't have wrapper");
        this.publication = pub;
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

    @Override
    public String toString()
    {
        return this.publication.toString() + " With Wrapper |";
    }
}
