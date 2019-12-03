package com.company.lab09pkg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Cinema
{
    int peopleMetre;
    int delay;
    int eagerAudienceCounter = 0;
    double p1;
    double p2;

    public Cinema(int peopleMetre, int delay, double p1, double p2)
    {
        this.peopleMetre = peopleMetre;
        this.delay = delay;
        this.p1 = p1;
        this.p2 = p2;
    }

    public void doStuff()
    {
        try
        {
            ExecutorService exec = Executors.newFixedThreadPool(peopleMetre+1);
            StapoGe kapo;
            List<Person> audience = new ArrayList<>();
            List<Future<Boolean>> ticketsList;
            CyclicBarrier barrier;

            for(int i = 0; i < peopleMetre; ++i)
            {
                audience.add(new Person(delay, p1));
            }

            ticketsList = exec.invokeAll(audience);
            ticketsList.forEach(ticket ->
            {
                try{if(ticket.get()) ++eagerAudienceCounter;} // Future::get jest metodą oczekującą na wynik zatem po przejściu całej listy nie musimy już czekać dalej
                catch (InterruptedException | ExecutionException concurrentExc)
                { System.out.println("no nie działa"); return;}
            });

            if(eagerAudienceCounter < 5)
            {
                System.out.println("Hello FRIENDS, cinema is closed today");
                exec.shutdown();
                return;
            }

            System.out.println("Welcome to our Cinema");

            kapo = new StapoGe(eagerAudienceCounter);

            exec.submit(() ->
            {
                try
                {
                    Thread.sleep(2_000);
                    synchronized (kapo)
                    {
                        kapo.notifyAll();
                        while(!kapo.signal())
                        {
                            System.out.println("waiting for leavers");
                            kapo.wait();
                        }

                        if(!kapo.enough()) System.out.println("You little rascals money will not be refunded");
                    }

                    Thread.sleep(2_000);
                    System.out.println("THE END");
                }
                catch (InterruptedException intExc)
                {
                    System.out.println("huhu something is no yes");
                }
            });

            for(int i = 0; i < eagerAudienceCounter; ++i)
            {
                exec.submit(new Viewer(kapo));
            }

            exec.shutdown();
        }
        catch (InterruptedException concurrentExc)
        {
            System.out.println(concurrentExc);
        }

    }

    public class Person implements Callable<Boolean>
    {
        int delay;
        double probability;

        public Person(int delay, double probability)
        {
            this.delay = delay;
            this.probability = probability;
        }

        public Boolean call()
        {
            Random r = new Random();
            try
            {
                Thread.sleep(r.nextInt(delay)*1000);
                return r.nextDouble() < probability;
            }
            catch (InterruptedException intExc)
            {
                System.out.println("huhu something is no yes");
            }
            return false;
        }
    }

    public class Viewer implements Runnable
    {
        StapoGe kapo;

        public Viewer(StapoGe kapo)
        {
            this.kapo = kapo;
        }

        public void run()
        {
            try
            {
                synchronized (kapo)
                {
                    kapo.wait();
                    Random r = new Random();
                    kapo.Decrement(r.nextDouble() < 0.3);
                    kapo.notifyAll();
                }
            }
            catch (InterruptedException intExc)
            {
                System.out.println("huhu something is no yes");
            }
        }
    }

    public class StapoGe
    {
        AtomicInteger people;
        AtomicInteger eagerViewers;

        public StapoGe(int people)
        {
            this.people = new AtomicInteger(people);
            this.eagerViewers = new AtomicInteger();
        }

        public synchronized int Decrement(boolean leave)
        {
            if(leave) eagerViewers.decrementAndGet();
            return people.decrementAndGet();
        }

        public boolean signal()
        {
            return people.intValue() <= 0;
        }

        public boolean enough()
        {
            return eagerViewers.intValue() >= 5;
        }
    }
}
