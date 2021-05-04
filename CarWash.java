// note that sometimes the output is not by order, that's because some cars got washed for less time than the others!

/**
 * Ron Cohen - 208401349
 * Noam Boni - 315586131
 */

public class CarWash {
    private int K;
    private int N;
    private int M;
    private int counterThirdStation = 0;
    private MySemaphore semN;
    private MySemaphore semK;
    private int counter = 0;
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public CarWash(int N, int K, int M) {
        this.N = N;
        this.K = K;
        this.M = M;
        this.semN = new MySemaphore(N);
        this.semK = new MySemaphore(K);
    }

    public void firstStation(Car c) {
        semN.acquire(c.getTurnNumber());
        synchronized (lock1) {
            float waitTime = carWait();
            System.out.println("Car number " + c.getTurnNumber() + " arrived to the first station, being washed for "
                    + waitTime + " seconds");
        }
        semN.release();
        secondStation(c);
    }

    public void secondStation(Car c) {
        semK.acquire(c.getTurnNumber());
        synchronized (lock2) {
            float waitTime = carWait();
            System.out.println("Car number " + c.getTurnNumber() + " arrived to the second station, being washed for "
                    + waitTime + " seconds");
        }
        semK.release();
        thirdStation(c);
    }

    public void thirdStation(Car c) {
        if (++counterThirdStation == M)
            try {
                notifyAll();
            } catch (Exception e) {
            }
        while (counterThirdStation < M) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
        System.out.println("Car number " + c.getTurnNumber() + " left thr car wash :)");
    }

    public void enterNewCar(Car c) {
        while (c.getTurnNumber() != counter) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
        counter++;
        try {
            notifyAll();
        } catch (Exception e) {
        }
        firstStation(c);
    }

    public float carWait() {
        float waitTime = (float) ((-1 * Math.log(Math.random()) / 1.5));
        try {
            Thread.sleep((int) waitTime * 1000);
        } catch (Exception e) {
        }
        return waitTime;
    }

}
