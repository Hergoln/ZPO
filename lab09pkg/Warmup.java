package com.company.lab09pkg;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Warmup
{
    public void doStuff()
    {
//        ExecutorService exec = Executors.newSingleThreadExecutor();
//
//        for(int i = 9; i > 0; --i)
//        {
//            exec.submit(new RetThread(i));
//        }
//        exec.shutdown();

        Thredo[] tArr = new Thredo[9];
        tArr[8] = new Thredo(tArr, 9);
        tArr[7] = new Thredo(tArr, 8);
        tArr[6] = new Thredo(tArr, 7);
        tArr[5] = new Thredo(tArr, 6);
        tArr[4] = new Thredo(tArr, 5);
        tArr[3] = new Thredo(tArr, 4);
        tArr[2] = new Thredo(tArr, 3);
        tArr[1] = new Thredo(tArr, 2);
        tArr[0] = new Thredo(tArr, 1);

        for (int i = 0; i < 9; ++i) {
            tArr[i].start();
        }


    }

    public class RetThread implements Runnable
    {
        int ID;
        public RetThread(int ID)
        {
            this.ID = ID;
        }

        public void run()
        {
            System.out.println("Hello from task " + ID);
        }
    }

    public class Thredo extends Thread
    {
        Thredo[] t;
        int ID;
        public Thredo(Thredo[] t, int ID)
        {
            this.t = t;
            this.ID = ID;
        }
        public void run()
        {
            try
            {
                if(ID != 9) t[ID].join();
                System.out.println("Hello from task " + ID);
            }
            catch (InterruptedException exc)
            {
                System.out.println(exc);
            }
        }
    }
}
