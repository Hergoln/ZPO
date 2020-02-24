package com.company.lab12pkg.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Person
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String pay;
    private Family family;
    private String nonsenseField = "";
    private List<Job> jobList = new ArrayList<Job>();

    @Override
    public String toString()
    {
        return "Hey, my name is " + firstName + ", hey, my surname is " + lastName + " chicki chicki, they pay me " + pay;
    }

    public Long getId()                                 {return id; }
    public void setId(Long id)                          {this.id = id;}
    public String getFirstName()                        {return firstName;}
    public void setFirstName(String firstName)          {this.firstName = firstName;}
    public String getSecondName()                       {return lastName;}
    public void setLastName(String secondName)        {this.lastName = secondName;}
    public String getPay()                              {return pay;}
    public void setPay(String pay)                      {this.pay = pay;}
    @ManyToOne
    public Family getFamily()                           {return family;}
    public void setFamily(Family family)                {this.family = family;}

    @Transient
    public String getNonsenseField()                    {return nonsenseField;}
    public void setNonsenseField(String nonsenseField)  {this.nonsenseField = nonsenseField;}

    @OneToMany
    public List<Job> getJobList()                       {return jobList;}
    public void setJobList(List<Job> jobList)           {this.jobList = jobList;}
}
