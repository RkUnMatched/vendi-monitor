public class AlternatingThreads {
    private static final int MAX_COUNT = 10;
    private static int currentNumber = 1;
    private static final Object lock = new Object();
    private static boolean isEvenTurn = false; // false表示奇数线程的回合，true表示偶数线程的回合

    public static void main(String[] args) {
        // 创建奇数线程
        Thread oddThread = new Thread(() -> {
            for (int i = 0; i < MAX_COUNT; i++) {
                synchronized (lock) {
                    // 等待轮到奇数线程
                    while (isEvenTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    
                    // 打印奇数
                    System.out.println("奇数线程: " + currentNumber);
                    currentNumber++;
                    
                    // 切换到偶数线程的回合
                    isEvenTurn = true;
                    lock.notify();
                }
            }
        }, "奇数线程");

        // 创建偶数线程
        Thread evenThread = new Thread(() -> {
            for (int i = 0; i < MAX_COUNT; i++) {
                synchronized (lock) {
                    // 等待轮到偶数线程
                    while (!isEvenTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    
                    // 打印偶数
                    System.out.println("偶数线程: " + currentNumber);
                    currentNumber++;
                    
                    // 切换到奇数线程的回合
                    isEvenTurn = false;
                    lock.notify();
                }
            }
        }, "偶数线程");

        // 启动线程
        System.out.println("开始交替打印...");
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