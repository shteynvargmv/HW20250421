package packagehw22;

import java.util.Random;
import static packagehw22.MainSmoke.*;

public class Smoker2 implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                while (!tobacco && !matches) {
                    try {
                        smoker2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                tobacco = false;
                matches = false;
                paper = false;
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
            System.out.println("Курильщик 2 скручивает и курит сигарету (" + interval/1000 + " c)");
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
