import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AlternatingThreadsWithLock {
    private static final int MAX_COUNT = 10;
    private static int currentNumber = 1;
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition oddCondition = lock.newCondition();
    private static final Condition evenCondition = lock.newCondition();
    private static boolean isOddTurn = true; // true表示奇数线程的回合

    public static void main(String[] args) {
        // 创建奇数线程
        Thread oddThread = new Thread(() -> {
            for (int i = 0; i < MAX_COUNT; i++) {
                lock.lock();
                try {
                    // 等待轮到奇数线程
                    while (!isOddTurn) {
                        oddCondition.await();
                    }
                    
                    // 打印奇数
                    System.out.println("奇数线程: " + currentNumber);
                    currentNumber++;
                    
                    // 切换到偶数线程的回合
                    isOddTurn = false;
                    evenCondition.signal();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                } finally {
                    lock.unlock();
                }
            }
        }, "奇数线程");

        // 创建偶数线程
        Thread evenThread = new Thread(() -> {
            for (int i = 0; i < MAX_COUNT; i++) {
                lock.lock();
                try {
                    // 等待轮到偶数线程
                    while (isOddTurn) {
                        evenCondition.await();
                    }
                    
                    // 打印偶数
                    System.out.println("偶数线程: " + currentNumber);
                    currentNumber++;
                    
                    // 切换到奇数线程的回合
                    isOddTurn = true;
                    oddCondition.signal();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                } finally {
                    lock.unlock();
                }
            }
        }, "偶数线程");

        // 启动线程
        System.out.println("开始交替打印（使用ReentrantLock）...");
        oddThread.start();
        evenThread.start();

        // 等待两个线程完成
        try {
            oddThread.join();
            evenThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("交替打印完成！");
    }
}