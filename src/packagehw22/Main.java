package packagehw22;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Lock[] forks = new Lock[5];
        for (int i = 0; i < 5; i++) {
            forks[i] = new ReentrantLock();
        }

        Thread[] philosophers = new Thread[5];
        for (int i = 0; i < 5; i++) {
            philosophers[i] = new Thread(new Philosopher(i, forks[i], forks[(i == 4) ? 0 : (i + 1)]));
            philosophers[i].start();
        }

        // Ждем завершения всех философов
        try {
            for (Thread philosopher : philosophers) {
                philosopher.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
