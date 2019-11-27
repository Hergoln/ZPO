package com.company.lab08pkg;

import java.util.concurrent.*;

public class RetardedThread
{
    public void doStuff()
    {
        String[] strings = new String[]{"aaaa", "bb", "ccccccccccccc", "dddddd"};
        MyLock lock = new MyLock();
        ExecutorService pool = Executors.newFixedThreadPool(strings.length);

        for(int i = 0; i < strings.length; ++i)
        {
            Runnable r = new Retard(strings[i], lock, i);
            pool.submit(r);
        }
        pool.shutdown();

    }

    public class Retard implements Runnable
    {
        private String retardsText;
        private final MyLock lock;
        private int ID;

        public Retard(String text, MyLock lock, int ID)
        {
            retardsText = text;
            this.lock  = lock;
            this.ID = ID;
        }

        public void run()
        {
            try {
                synchronized (lock)
                {
                    lock.RegisterID(ID);
                    lock.notifyAll();
                    lock.wait();
                }

                for(int i = 0; i < retardsText.length();++i)
                {
                    synchronized (lock) {
                        lock.notifyAll();
                        while (ID != lock.GetCurrent()) {  lock.wait(); }
                        System.out.print(retardsText.charAt(i));
                        lock.Increment();
                    }
                }

                synchronized (lock)
                {
                    lock.notifyAll();
                    while (ID != lock.GetCurrent()) {  lock.wait(); }
                    lock.DeleteID(ID);
                    lock.notifyAll();
                }
            }
            catch (InterruptedException exc)
            {
                System.out.println("It failed :)");
                System.out.println(exc);
            }
        }
    }

    public class MyLock
    {
        private int currentIDIndex = 0;
        private CopyOnWriteArrayList<Integer> queue;

        public MyLock()
        {
            queue = new CopyOnWriteArrayList<>();
        }

        public void Increment()
        {
            currentIDIndex = currentIDIndex < queue.size()-1 ? currentIDIndex+1 : 0;
        }

        public int GetCurrent()
        {
            currentIDIndex = currentIDIndex >= queue.size()-1? queue.size()-1 : currentIDIndex;
            return queue.get(currentIDIndex);
        }

        public void RegisterID(int ID)
        {
            queue.add(ID);
        }

        public void DeleteID(int ID)
        {
            queue.remove(Integer.valueOf(ID));
        }
    }
}
