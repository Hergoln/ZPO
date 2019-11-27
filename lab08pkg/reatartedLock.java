package com.company.lab08pkg;

import java.util.LinkedList;

public class reatartedLock
{
    private int threadsCount = 0;
    private int threadID = 0;
    private LinkedList<Integer> queue;
    private LinkedList<Integer> disposableQueue;
    private boolean isLocked = false;

    public reatartedLock()
    {
        queue = new LinkedList<>();
    }

    public synchronized void lock(int threadID) throws InterruptedException
    {
        if(queue.contains(threadID)) return;
        while (isLocked ) {
            wait();
        }
        isLocked = true;
    }

    public synchronized void unlock(int threadID) {
        isLocked = false;
        notify();
    }
}
