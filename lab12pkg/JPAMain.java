package com.company.lab12pkg;
import com.company.lab12pkg.Models.*;
import javax.persistence.*;

public class JPAMain {
    private static EntityManagerFactory factory;

    public static void main(String[] argv)
    {
        try
        {
            setUp();
        }
        catch (Exception exc)
        {
            System.out.println(exc);
        }
    }

    public static void setUp() throws Exception {
        factory = Persistence.createEntityManagerFactory("people");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();  //nowa transakcja
        Query q = em.createQuery("select m from Person m"); //lista rekordów
        boolean createNewEntries = (q.getResultList().size() == 0);
        if (createNewEntries) {// No, so lets create new entries
            Family family = new Family();
            family.setDescription("Family for the Knopfs");
            em.persist(family);
            for (int i = 0; i < 40; i++) {
                Person person = new Person();
                person.setFirstName("Jim_" + i);
                person.setLastName("Knopf_" + i);
                em.persist(person);
                family.getMembers().add(person);
                em.persist(person);
                em.persist(family);
            }
        }
        em.getTransaction().commit();
        em.close();

    }
}
