package leetcode;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 三笠阿克曼
 * @date 2022/8/19
 * Leetcode 1114: 按序打印
 *
 *  1、synchronized + state
 *
 *      1、synchronized(lock)
 *      2、判断state不对，则lock.wait();
 *      3、对则打印，更新state，唤醒所有的：lock.notifyAll();
 *
 *  2、lock + condition + state
 *      - 三个Condition
 *      1、lock()
 *      2、判断状态state，不对则firstCondition.await();
 *      3、对则打印，唤醒下一个secondCondition.signal();
 *      4、unlock()
 *
 *  3、semaphore信号量
 *
 *     - release(): 【释放】一个信号量
 *     - acquire(): 【获取】一个信号量，没有则阻塞
 *     1、first打印完，semaphore2.release()，second会被【阻塞】在semaphore2.acquire()
 *     2、second打印完，semaphore3.release()，third会被【阻塞】在semaphore3.acquire()
 *
 *  4、CountDownLatch
 *
 *    - await(): 【等待减到0】
 *    1、first打印完，countDownLatch12.countDown()，second会被【阻塞】在countDownLatch12.await()
 *    2、second打印完，countDownLatch23.countDown()，third会被【阻塞】在countDownLatch23.await()
 *
 *  5、阻塞队列
 *
 *      - take(): 队列为null时，会【阻塞】
 *     1、first打印完，blockingQueue2.put(2), second会被【阻塞】在blockingQueue2.take()
 *     2、second打印完，blockingQueue3.put(3)，third会被【阻塞】在blockingQueue3.take()
 *
 *  6、yield + state
 *
 */
public class Leetcode_1114_Foo {

    static int state = 1;
    // 1
    static Object lock = new Object();

    // 2
    static ReentrantLock lock2 = new ReentrantLock();
    static Condition firstCondition = lock2.newCondition();
    static Condition secondCondition = lock2.newCondition();
    static Condition thirdCondition = lock2.newCondition();
    // 3
    Semaphore semaphore2 = new Semaphore(0);
    Semaphore semaphore3 = new Semaphore(0);

    // 4
    CountDownLatch countDownLatch12 = new CountDownLatch(1);
    CountDownLatch countDownLatch23 = new CountDownLatch(1);

    // 5
    BlockingQueue<Integer> blockingQueue2 = new LinkedBlockingQueue<>();
    BlockingQueue<Integer> blockingQueue3 = new LinkedBlockingQueue<>();

    public Leetcode_1114_Foo() {
    }

    // 1、synchronized
    public void first1(Runnable printFirst) throws InterruptedException {
        synchronized(lock){
            while(state != 1){
                lock.wait();
            }
            printFirst.run();
            state = 2;
            lock.notifyAll();
        }
    }
    public void second1(Runnable printSecond) throws InterruptedException {
        synchronized(lock){
            while(state != 2){
                lock.wait();
            }
            printSecond.run();
            state = 3;
            lock.notifyAll();
        }
    }
    public void third1(Runnable printThird) throws InterruptedException {
        synchronized(lock){
            while(state != 3){
                lock.wait();
            }
            printThird.run();
            state = 1;
            lock.notifyAll();
        }
    }

    // 2、lock + condition
    public void first2(Runnable printFirst) throws InterruptedException {

        lock2.lock();
        try {
            if(state != 1){
                firstCondition.await();
            }

            printFirst.run();
            state = 2;
            secondCondition.signal();
        } finally {
            lock2.unlock();
        }
    }
    public void second2(Runnable printSecond) throws InterruptedException {

        lock2.lock();
        try {
            while(state != 2){
                secondCondition.await();
            }

            printSecond.run();
            state = 3;
            thirdCondition.signal();
        } finally {
            lock2.unlock();
        }
    }
    public void third2(Runnable printThird) throws InterruptedException {

        lock2.lock();
        try {
            while(state != 3){
                thirdCondition.await();
            }

            printThird.run();
            state = 1;
            firstCondition.signal();
        } finally {
            lock2.unlock();
        }
    }

    // 3、semaphore信号量
    public void first3(Runnable printFirst) throws InterruptedException {

        printFirst.run();
        state = 2;
        //释放一个12的信号量
        semaphore2.release();
    }
    public void second3(Runnable printSecond) throws InterruptedException {

        //获取一个2的信号量，没有则阻塞
        semaphore2.acquire();
        printSecond.run();
        semaphore3.release();
    }
    public void third3(Runnable printThird) throws InterruptedException {
        semaphore3.acquire();
        printThird.run();
    }

    // 4、CountDownLatch
    public void first4(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        countDownLatch12.countDown();
    }
    public void second4(Runnable printSecond) throws InterruptedException {
        // 等待减到0
        countDownLatch12.await();

        printSecond.run();
        countDownLatch23.countDown();
    }
    public void third4(Runnable printThird) throws InterruptedException {

        countDownLatch23.await();
        printThird.run();
    }

    // 5、阻塞队列
    public void first5(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        blockingQueue2.put(2);
    }
    public void second5(Runnable printSecond) throws InterruptedException {

        // 队列为null时，会阻塞
        blockingQueue2.take();
        printSecond.run();

        blockingQueue3.put(3);
    }
    public void third5(Runnable printThird) throws InterruptedException {

        blockingQueue3.take();
        printThird.run();
    }

    // 6、yield + state
    public void first6(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        state = 2;
    }
    public void second6(Runnable printSecond) throws InterruptedException {

        while (state != 2){
            Thread.yield();
        }
        printSecond.run();
        state = 3;
    }

    public void third6(Runnable printThird) throws InterruptedException {

        while (state != 3){
            Thread.yield();
        }
        printThird.run();
    }

}
