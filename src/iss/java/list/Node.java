package iss.java.list;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Updated by wenke on 2016/10/12.
 * TODO: Modify this class to meet requirement A and C.
 */
public class Node {

    /*
     * volatile means "prone to modification".
     * A volatile variable is prevented from the compiler's optimization.
     */
    private volatile int data;
    private volatile Node prev;
    private volatile Node next;

    /**
     * You must store some information (e.g. the hash code of the host list;
        or the address of the host);
     * Otherwise, requirement C -15.
     */
    private MyList host;

    private Lock lock = new ReentrantLock();

    public Node(){};

    public Node(MyList host) {
        this.host = host;
    }

    public int getData() {
        return data;
    }

    public Node setData(int data) {
        this.data = data;
        return this;
    }

    public Node getPrev() {
        return prev;
    }

    Node setPrev(Node prev) {
        this.prev = prev;
        return this;
    }

    public Node getNext() {
        return next;
    }

    Node setNext(Node next) {
        this.next = next;
        return this;
    }

    MyList getHost(){
        return host;
    }

    public void lock(){
        lock.lock();
    }

    public void unlock(){
        lock.unlock();
    }
}
