package packagehw22;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainSmoke {
    static boolean[] table = new boolean[3];
    static boolean tobacco = table[0];
    static boolean paper = table[1];
    static boolean matches = table[2];

    static final Lock lock = new ReentrantLock();
    static final Condition barman = lock.newCondition();
    static final Condition smoker1 = lock.newCondition();
    static final Condition smoker2 = lock.newCondition();
    static final Condition smoker3 = lock.newCondition();

    public static void main(String[] args) {
        new Thread(new Smoker1()).start();
        new Thread(new Smoker2()).start();
        new Thread(new Smoker3()).start();
        new Thread(new Barman()).start();
    }
}
