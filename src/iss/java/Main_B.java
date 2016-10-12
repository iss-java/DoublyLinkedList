package iss.java;

import iss.java.list.MyList;

import java.util.Random;

/**
 * Created by wenke on 2016/9/16.
 */
public class Main_B {
    public static void main(String[] args) throws InterruptedException {
        MyList list = new MyList();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i=0; i<5; ++i) {
                    list.insert(list.getHead(), i);
                }
            }
        };

        t1.start();
        Thread.sleep(2000);
        System.out.println(list.validate());
        Thread.sleep(200);

        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (int i=5; i<1e4+5; ++i) {
                    list.insert(list.getHead(), i);
                }
            }
        };

        Thread t3 = new Thread() {
            @Override
            public void run() {
                for (int i=0; i<1e4; ++i) {
                    list.remove(list.getHead().getNext());
                }
            }
        };

        t2.start();
        t3.start();

        Thread.sleep(5000);
        System.out.println(list.validate());

    }
}
