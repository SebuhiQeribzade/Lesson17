package Lesson17Mentor;
class OddThread extends Thread {
    public void run() {
        for (int i = 1; i <= 10; i += 2) {
            System.out.println("Odd Thread: " + i);
            try {
                Thread.sleep(100); // Simulate some work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class EvenThread extends Thread {
    public void run() {
        for (int i = 2; i <= 10; i += 2) {
            System.out.println("Even Thread: " + i);
            try {
                Thread.sleep(100); // Simulate some work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Task I - asynchronous threads
        OddThread oddThread = new OddThread();
        EvenThread evenThread = new EvenThread();

        oddThread.start();
        evenThread.start();

        // Task II - print only odds then evens
        OddThread oddsFirst = new OddThread();
        EvenThread evensAfterOdds = new EvenThread();

        oddsFirst.start();
        try {
            oddsFirst.join(); // Wait for oddThread to finish before starting evenThread
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        evensAfterOdds.start();
    }
}

