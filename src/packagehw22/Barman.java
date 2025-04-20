package packagehw22;
import static packagehw22.MainSmoke.*;
import java.util.Random;

public class Barman implements Runnable {
    private Random rnd = new Random();

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                while ((tobacco && paper) || (tobacco && matches) || (paper && matches)) {
                    try {
                        barman.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int temp = rnd.nextInt(3);
                if (temp == 0) {
                    tobacco = false;
                    paper = true;
                    matches = true;
                    System.out.println("Бармен кладет бумагу и спички");
                    pushing();
                    smoker1.signal();
                }
                if (temp == 1) {
                    tobacco = true;
                    paper = false;
                    matches = true;
                    System.out.println("Бармен кладет табак и спички");
                    pushing();
                    smoker2.signal();
                }
                if (temp == 2) {
                    tobacco = true;
                    paper = true;
                    matches = false;
                    System.out.println("Бармен кладет табак и бумагу");
                    pushing();
                    smoker3.signal();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    private void pushing() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
