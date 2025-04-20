package packagehw22;

import java.util.Random;
import static packagehw22.MainSmoke.*;

public class Smoker1 implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                while (!paper && !matches) {
                    try {
                        smoker1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                paper = false;
                matches = false;
                tobacco = false;
                makingAndSmokingSigarette();
                barman.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    private void makingAndSmokingSigarette() {
        int interval = new Random().nextInt(1000,3001);
        try {
            System.out.println("Курильщик 1 скручивает и курит сигарету (" + interval/1000 + " c)");
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
