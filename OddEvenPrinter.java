package Lesson17;
public class OddEvenPrinter {
    private static final int MAX_NUMBER = 10000;
    private static final Object lock = new Object();
    private static int number = 1;

    public static void main(String[] args) {
        Thread oddThread = new Thread(new OddPrinter(), "OddThread");
        Thread evenThread = new Thread(new EvenPrinter(), "EvenThread");

        oddThread.start();
        evenThread.start();
    }

    static class OddPrinter implements Runnable {
        @Override
        public void run() {
            while (number <= MAX_NUMBER) {
                synchronized (lock) {
                    if (number % 2 != 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + number);
                        number++;
                        lock.notify(); // Notify the waiting thread (EvenPrinter)
                    } else {
                        try {
                            lock.wait(); // Wait for the other thread to print
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    static class EvenPrinter implements Runnable {
        @Override
        public void run() {
            while (number <= MAX_NUMBER) {
                synchronized (lock) {
                    if (number % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + number);
                        number++;
                        lock.notify(); // Notify the waiting thread (OddPrinter)
                    } else {
                        try {
                            lock.wait(); // Wait for the other thread to print
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
