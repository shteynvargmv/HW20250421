package packagehw22;

import java.util.Random;
import java.util.concurrent.locks.Lock;

class Philosopher implements Runnable {
    private final int id;
    private final Lock leftFork;
    private final Lock rightFork;

    public Philosopher(int id, Lock leftFork, Lock rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void think() throws InterruptedException {
        Long interval = new Random().nextLong(1000,5001);
        System.out.println("Философ " + id + " размышляет (" + interval/1000 + " c)");
        Thread.sleep(interval); // Размышляет от 1 до 5 сек
    }

    private void eat() throws InterruptedException {
        Long interval = new Random().nextLong(1000,10001);
        System.out.println("Философ " + id + " ест (" + interval/1000 + " c)");
        Thread.sleep(interval); // Ест от 1 до 10 сек
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 3; i++) {
                think();
                leftFork.lock();
                System.out.println("Философ " + id + " взял левую вилку");
                try {
                    rightFork.lock();
                    System.out.println("Философ " + id + " взял правую вилку");
                    try {
                        eat();
                    } finally {
                        rightFork.unlock();
                    }
                } finally {
                    leftFork.unlock();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
