package com.company.lab11pkg;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Serber
{
    public static void main(String[] args) throws Exception {
        try (ServerSocket listener = new ServerSocket(2137)) {
            Referee referee = new Referee();
            CyclicBarrier barrier = new CyclicBarrier(3);
            System.out.println("Waiting for cyclists to appear...");
            ExecutorService threadPool = Executors.newFixedThreadPool(4);

            while (referee.participantsCounter.get() < 3) {
                threadPool.execute(new CyclyBoi(listener.accept(), referee, barrier));
                referee.participantsCounter.incrementAndGet();
            }
            threadPool.shutdown();
        }
    }

    public static class CyclyBoi implements Runnable
    {
        private Socket sock;
        private Referee ref;
        private OutputStreamWriter output;
        private InputStreamReader input;
        private String name;
        private CyclicBarrier barr;

        public CyclyBoi(Socket socket, Referee ref, CyclicBarrier barr)
        {
            this.sock = socket;
            this.ref = ref;
            this.barr = barr;
        }

        public void run()
        {
            try
            {
                char[] inputSeq = new char[32];
                this.output = new OutputStreamWriter(sock.getOutputStream(), StandardCharsets.UTF_8);
                this.input = new InputStreamReader(sock.getInputStream(), StandardCharsets.UTF_8);
                this.input.read(inputSeq);
                this.name = new String(inputSeq);
                System.out.println(this.name + " is waiting for other cyclists...");
                barr.await();

                this.output.write("Start!");
                this.output.flush();
                for(int i = 0; i < 6; ++i) // waiting for mid ends
                {
                    this.input.read(inputSeq);
                    if(!ref.ends[i].get())
                    {
                        if(i < 5)
                        {
                            System.out.println(this.name.trim() + " wins mid end nr." + i);
                            this.ref.ends[i].getAndSet(true);
                        }
                        else
                        {
                            int place = this.ref.place.incrementAndGet();
                            System.out.println(this.name.trim() + "is " + place + " on Finish!");
                            switch (place)
                            {
                                case 1: this.output.write("first"); break;
                                case 2: this.output.write("second"); break;
                                case 3: this.output.write("third"); break;
                            }
                            this.output.flush();
                        }
                    }
                }
            }
            catch (BrokenBarrierException | InterruptedException exc)
            {
                System.out.println(exc);
                this.ref.participantsCounter.decrementAndGet();
            }
            catch (IOException streamExc)
            {
                System.out.println("Cyclist dropped from race");
                this.ref.participantsCounter.decrementAndGet();
            }
        }
    }

    public static class Referee
    {
        public AtomicInteger participantsCounter;
        public AtomicInteger place;
        public AtomicBoolean[] ends;

        public Referee()
        {
            participantsCounter = new AtomicInteger(0);
            place = new AtomicInteger(0);
            ends = new AtomicBoolean[6];
            for (int i = 0; i < 6; ++i)
                ends[i] = new AtomicBoolean(false);
        }
    }
}
