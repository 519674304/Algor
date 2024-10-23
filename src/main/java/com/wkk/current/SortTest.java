package com.wkk.current;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SortTest {
    private static volatile int i = 0;
    private static ReentrantLock lock = new ReentrantLock();

    @Test
    public void test5() throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (i < 10) {
                    if (i % 3 == 0) {
                        lock.lock();
                        System.out.println("1," + (i++));
                        lock.unlock();
                    }

                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (i < 10) {
                    if (i % 3 == 1) {
                        lock.lock();
                        System.out.println("1," + (i++));
                        lock.unlock();
                    }

                }
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (i < 10) {
                    if (i % 3 == 2) {
                        lock.lock();
                        System.out.println("1," + (i++));
                        lock.unlock();
                    }

                }
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        TimeUnit.SECONDS.sleep(20);

    }
}
