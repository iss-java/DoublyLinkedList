package iss.java;

import iss.java.list.Node;

/**
 * Updated by Wen Ke on 2016/10/12
 * Requirement A consists of 15 points.
 */
public class Main_A {
    public static void main(String[] args) throws InterruptedException {

        Object lock = new Object();
        Node node = new Node();
        node.setData(50);

        Thread plus = new Thread() {
            @Override
            public void run() {
                for (int i=0; i<1e6; ++i){
                    synchronized (lock) {
                        node.setData(node.getData()+10);
                    }
                }
            }
        };

        Thread minus = new Thread() {
            @Override
            public void run() {
                for (int i=0; i<1e6; ++i) {
                    synchronized (lock) {
                        node.setData(node.getData()-10);
                    }
                }
            }
        };

        /**
         * start() the (at least) two threads.
         * Or submit() to a thread pool.
         */
        plus.start();
        minus.start();

        /**
         * Sleep to wait for the two worker threads.
         * Or you can use CyclicBarrier to wait for worker threads.
         */
        Thread.sleep(10000);

        System.out.println(node.getData());
    }
}
