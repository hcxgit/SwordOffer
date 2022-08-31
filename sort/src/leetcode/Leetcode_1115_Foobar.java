package leetcode;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 三笠阿克曼
 * @date 2022/8/30
 *
 * Leetcode 1115: 交替打印FooBar
 *
 *  1、synchronized + flag
 *
 *  2、lock + condition + flag
 *
 *  3、两个Semaphore
 *
 *  4、CyclicBarrier + flag
 *
 *  5、2个阻塞队列
 *
 *  6、yield + flag
 */
public class Leetcode_1115_Foobar {
    private int n;
    private volatile boolean fooFlag = true;

    // 1、synchronized + flag
    private Object lock = new Object();

    // 2、lock + condition+ flag
    private ReentrantLock lock2 = new ReentrantLock();
    Condition cFoo = lock2.newCondition();
    Condition cBar = lock2.newCondition();

    // 3、两个Semaphore
    Semaphore sFoo = new Semaphore(1);
    Semaphore sBar = new Semaphore(0);

    // 4、CyclicBarrier + flag
    CyclicBarrier cyclic =  new CyclicBarrier(2);

    // 5、2个阻塞队列
    BlockingQueue<Integer> queueFoo = new ArrayBlockingQueue<>(1);
    BlockingQueue<Integer> queueBar = new ArrayBlockingQueue<>(1);

    public Leetcode_1115_Foobar(int n) {
        this.n = n;
    }


    // 1、synchronized + flag
    public void foo1(Runnable printFoo) throws InterruptedException {

        synchronized(lock){
            for (int i = 0; i < n; i++) {
                while(!fooFlag){
                    lock.wait();
                }
                printFoo.run();
                fooFlag = false;
                lock.notify();
            }
        }
    }

    public void bar1(Runnable printBar) throws InterruptedException {

        synchronized(lock) {
            for (int i = 0; i < n; i++) {

                while(fooFlag){
                    lock.wait();
                }
                printBar.run();
                fooFlag = true;
                lock.notify();
            }
        }
    }

    // 2、lock + condition + flag
    public void foo2(Runnable printFoo) throws InterruptedException {
        lock2.lock();

        try {
            for (int i = 0; i < n; i++) {
                while(!fooFlag){
                    cFoo.await();
                }
                printFoo.run();
                fooFlag = false;
                cBar.signal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock2.unlock();
        }
    }

    public void bar2(Runnable printBar) throws InterruptedException {

        lock2.lock();

        try {
            for (int i = 0; i < n; i++) {
                while(fooFlag){
                    cBar.await();
                }
                printBar.run();
                fooFlag = true;
                cFoo.signal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock2.unlock();
        }
    }

    // 3、两个Semaphore
    public void foo3(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            sFoo.acquire();
            printFoo.run();
            sBar.release();
        }
    }

    public void bar3(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            sBar.acquire();
            printBar.run();
            sFoo.release();
        }
    }

    // 4、CyclicBarrier + flag
    public void foo4(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while(!fooFlag) {
                ;
            }

            printFoo.run();
            fooFlag = false;
            try {
                cyclic.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public void bar4(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while(fooFlag) {
                ;
            }

            printBar.run();
            fooFlag = true;
            try {
                cyclic.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
    }

    // 5、阻塞队列
    public void foo5(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            printFoo.run();
            queueBar.put(i);
            queueFoo.take();
        }
    }

    public void bar5(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            queueBar.take();
            printBar.run();
            queueFoo.put(i);
        }
    }

    // 6、yield + flag
    public void foo6(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; ) {
            if(fooFlag){
                printFoo.run();
                i++;  // 注意这里
                fooFlag = false;
            }else{
                Thread.yield();
            }
        }
    }

    public void bar6(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; ) {
            if(!fooFlag){
                printBar.run();
                i++;
                fooFlag = true;
            }else{
                Thread.yield();
            }
        }
    }
}
