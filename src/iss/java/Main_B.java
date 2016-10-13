package iss.java;

import iss.java.list.MyList;
import iss.java.list.Node;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by wenke on 2016/9/16.
 */
public class Main_B {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        MyList list = new MyList();
        Random rand = new Random();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i=0; i<5; ++i) {
                    list.insert(list.getHead(), i);
                }
            }
        };

        t1.start();
        Thread.sleep(1000);
        System.out.println(list.validate());

        CyclicBarrier barrier = new CyclicBarrier(3);
        final int N = 10000;

        // push_front
        Thread t2 = new Thread() {
            @Override
            public void run() {
                Node _prev = list.getHead();
                for (int i=0; i<N; ++i) {
                    /**
                     * Acquire lock on "head" BEFORE fetching its successor.
                     */
                    _prev.lock();
                    Node _next = _prev.getNext();
                    _next.lock();
                    list.insert(_prev, rand.nextInt());
                    /**
                     * Release locks in the REVERSE order.
                     */
                    _next.unlock();
                    _prev.unlock();
                }
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        };

        // pop_front
        Thread t3 = new Thread() {
            @Override
            public void run() {
                Node _prev = list.getHead();
                for (int i=0; i<N; ++i) {
                    _prev.lock();
                    Node target = _prev.getNext();
                    target.lock();
                    list.remove(target);
                    target.unlock();
                    _prev.unlock();
                }
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        };

        t2.start();
        t3.start();

        /**
         * We must use barrier instead of sleep()
         * Otherwise we cannot detect deadlock
         */
        barrier.await();

        /**
         * Usually, this testcase will result in a list whose length is larger than 5.
         * This is because the remove() method meets a tail node and performs a dummy remove;
         */
        System.out.println(list.validate());

    }
}
