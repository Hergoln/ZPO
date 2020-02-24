package com.company.lab09pkg;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Sitalke
{
    public void doStuff(int upperLimit)
    {
        boolean[] numbers = new boolean[upperLimit];
        boolean[] smallPrimes = findSmallPrimes(upperLimit);
        List<Integer> list = new ArrayList<>();

        for (int i =2; i < smallPrimes.length; ++i)
        {
            if(!smallPrimes[i]) list.add(i);
        }

        FirstVariant first = new FirstVariant(numbers, list);
        first.doYourThing();
        for(int i =0; i < numbers.length; ++i) numbers[i] = false;

        SecondVariant second = new SecondVariant(numbers, list);
        second.doYourThing();
        for(int i =0; i < numbers.length; ++i) numbers[i] = false;

        ThirdVariant third = new ThirdVariant(numbers, list);
        third.doYourThing();
        for(int i =0; i < numbers.length; ++i) numbers[i] = false;
    }

    public void howManyNumbers(boolean[] numbers)
    {
        int howManyPrimes = 0;
        for(int i = 2; i < numbers.length; ++i)
        {
            if(i == 1_000_000)
                System.out.println("Primes(1_000_000) : " + howManyPrimes);
            else if(i == 10_000_000)
                System.out.println("Primes(10_000_000) : " + howManyPrimes);
            else if(i == 100_000_000)
                System.out.println("Primes(100_000_000) : " + howManyPrimes);
            if(!numbers[i])
            {
                ++howManyPrimes;
            }
        }

        System.out.println("Primes(1_000_000_000) : " + howManyPrimes);
    }

    public static boolean[] findSmallPrimes(int upperLimit)
    {
        int upperLimitSquare = (int)Math.sqrt(upperLimit);
        int iIterations = (int)Math.sqrt(upperLimitSquare);

        boolean[] values = new boolean[upperLimitSquare];

        for(int i = 2; i < iIterations; ++i)
            if(!values[i])
                for(int j = i+i; j < upperLimitSquare; j += i)
                    values[j] = true;

        return values;
    }

    public class FirstVariant
    {
        boolean[] numbers;
        Kapo kapo;

        public FirstVariant(boolean[] numbers, List<Integer> smallPrimes)
        {
            this.numbers = numbers;
            this.kapo = new Kapo(smallPrimes);
        }

        public void doYourThing()
        {
            System.out.println("First variant, 2 threads version: " + countPrimes(2) + "(time)");
            System.out.println("First variant, 4 threads version: " + countPrimes(4) + "(time)");
        }

        public double countPrimes(int threads)
        {
            ExecutorService exec = Executors.newFixedThreadPool(threads);
            kapo.restart();
            long start = System.nanoTime();
            while (kapo.peek() != -1)
            {
                Integer sPrime = kapo.getNextPrime();
                exec.submit(() ->
                {
                    for(int i = sPrime+sPrime; i < numbers.length; i+=sPrime)
                    {
                        numbers[i] = true;
                    }
                });
            }

            exec.shutdown();
            try
            {
                exec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
            }
            catch (InterruptedException exc)
            {
                System.out.println(exc);
            }
            return (System.nanoTime() - start)/1_000_000_000.0;
        }

        public class Kapo
        {
            AtomicInteger index;
            List<Integer> smallPrimes;

            public Kapo(List<Integer> smallPrimes)
            {
                this.smallPrimes = smallPrimes;
                this.index = new AtomicInteger(0);
            }

            public synchronized int getNextPrime()
            {
                return index.intValue() < smallPrimes.size() ? smallPrimes.get(index.getAndIncrement()) : -1;
            }

            public synchronized int peek()
            {
                return index.intValue() < smallPrimes.size() ? index.intValue() : -1;
            }

            public void restart()
            {
                index.set(0);
            }
        }
    }

    public class SecondVariant
    {
        boolean[] numbers;
        Kapo kapo;

        public SecondVariant(boolean[] numbers, List<Integer> smallPrimes)
        {
            this.numbers = numbers;
            this.kapo = new Kapo(smallPrimes);
        }

        public void doYourThing()
        {
            System.out.println("Second variant, 2 threads version: " + countPrimes(2) + "(time)");
            howManyNumbers(numbers);
            for(int i =0; i < numbers.length; ++i) numbers[i] = false;
            System.out.println("Second variant, 3 threads version: " + countPrimes(3) + "(time)");
            howManyNumbers(numbers);
            for(int i =0; i < numbers.length; ++i) numbers[i] = false;
            System.out.println("Second variant, 4 threads version: " + countPrimes(4) + "(time)");
            howManyNumbers(numbers);
            for(int i =0; i < numbers.length; ++i) numbers[i] = false;
        }

        public double countPrimes(int threads)
        {
            try
            {
                ExecutorService exec = Executors.newFixedThreadPool(threads);
                kapo.restart();
                long start = System.nanoTime();

                while (kapo.peek() != -1)
                {
                    Integer sPrime = kapo.getNextPrime();
                    for(int j = 0; j < threads; ++j)
                    {
                        List<Callable<Boolean>> calls = new ArrayList<>();
                        calls.add(new CallingMan(j, threads, sPrime, numbers));
                        List<Future<Boolean>> tasks = exec.invokeAll(calls);

                        for (Future<Boolean> t: tasks) t.get();
                    }
                }

                exec.shutdown();
                try
                {
                    exec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
                }
                catch (InterruptedException exc)
                {
                    System.out.println(exc);
                }
                return (System.nanoTime() - start)/1_000_000_000.0;
            }
            catch (InterruptedException | ExecutionException exc)
            {
                return -1;
            }

        }

        public class Kapo
        {
            AtomicInteger index;
            List<Integer> smallPrimes;

            public Kapo(List<Integer> smallPrimes)
            {
                this.smallPrimes = smallPrimes;
                this.index = new AtomicInteger(0);
            }

            public synchronized int getNextPrime()
            {
                return index.intValue() < smallPrimes.size() ? smallPrimes.get(index.getAndIncrement()) : -1;
            }

            public synchronized int peek()
            {
                return index.intValue() < smallPrimes.size() ? index.intValue() : -1;
            }

            public void restart()
            {
                index.set(0);
            }
        }

        public class CallingMan implements Callable<Boolean>
        {
            int threadID;
            int t;
            int s;
            boolean[] numbers;

            public CallingMan(int threadID, int t, int smallPrime, boolean[] numbers)
            {
                this.threadID = threadID;
                this.t = t;
                this.s = smallPrime;
                this.numbers = numbers;
            }
            // Pierwszy wątek wykreśla: s^2,       s^2 + 2ts,       s^2 + 4ts,       s^2 + 6ts, ...
            // Drugi wątek wykreśla: s^2 + 2s,  s^2 + 2ts + 2s, s^2 + 4ts + 2s, s^2 + 6ts + 2s, ...
            // Trzeci wątek wykreśla: s^2 + 4s, s^2 + 2ts + 4s, s^2 + 4ts + 4s, s^2 + 6ts + 4s, ...
            public Boolean call()
            {
                if(s == 2) {
                    for (int i = s * s + threadID * s; i < numbers.length; i += s * t) {
                        numbers[i] = true;
                    }
                }
                else {
                    for (int i = s * s + threadID * 2 * s; i < numbers.length; i += 2 * t * s) {
                        numbers[i] = true;
                    }
                }
                return false;
            }
        }
    }

    public class ThirdVariant
    {
        boolean[] numbers;
        List<Integer> smallPrimes;

        public ThirdVariant(boolean[] numbers, List<Integer> smallPrimes)
        {
            this.numbers = numbers;
            this.smallPrimes = smallPrimes;
        }

        public void doYourThing()
        {
            System.out.println("Fourth variant, 2 threads version: " + countPrimes(2) + "(time)");
            System.out.println("Fourth variant, 4 threads version: " + countPrimes(4) + "(time)");
        }

        public double countPrimes(int threads)
        {
            ExecutorService exec = Executors.newFixedThreadPool(threads);
            long start = System.nanoTime();
            for(int i = 1; i <= threads; ++i)
                exec.submit(new RuinedMan(numbers, smallPrimes, (i-1)*(numbers.length/threads), i*(numbers.length/threads)));

            exec.shutdown();
            try
            {
                exec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
            }
            catch (InterruptedException exc)
            {
                System.out.println(exc);
            }
            return (System.nanoTime() - start)/1_000_000_000.0;
        }

        public class RuinedMan implements Runnable
        {
            boolean[] numbers;
            List<Integer> smallPrimes;
            int beg, end;

            public RuinedMan(boolean[] numbers, List<Integer> smallPrimes, int beg, int end)
            {
                this.numbers = numbers;
                this.smallPrimes = smallPrimes;
                this.beg = beg;
                this.end = end;
            }

            public void run()
            {
                for(int i =0; i < smallPrimes.size(); ++i)
                {
                    int multS = smallPrimes.get(i)*2;
                    while (multS < beg)
                    {
                        multS += smallPrimes.get(i);
                    }

                    for (; multS < end; multS += smallPrimes.get(i))
                        numbers[multS] = true;
                }
            }
        }
    }
}