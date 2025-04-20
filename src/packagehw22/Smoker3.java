package packagehw22;

import java.util.Random;
import static packagehw22.MainSmoke.*;

public class Smoker3 implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                while (!tobacco && !paper) {
                    try {
                        smoker3.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                makingAndSmokingSigarette();
                tobacco = false;
                paper = false;
                matches = false;
                barman.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    private void makingAndSmokingSigarette() {
        int interval = new Random().nextInt(1000,3001);
        try {
            System.out.println("Курильщик 3 скручивает и курит сигарету (" + interval/1000 + " c)");
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
