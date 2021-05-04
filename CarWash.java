// note that sometimes the output is not by order, that's because some cars got washed for less time than the others!

/**
 * Ron Cohen - 208401349 Noam Boni - 315586131
 */
 
public class CarWash {
    private int M;// car number
    private int counterThirdStation = 0;// we're using this to release all the cars together from the last station
    private MySemaphore semN;
    private MySemaphore semK;
    private int counter = 0;
    private Object lock1 = new Object();// the locks are used to protect the critical sections at the stations
    private Object lock2 = new Object();

    public CarWash(int N, int K, int M) {
        this.M = M;
        this.semN = new MySemaphore(N);// number of machines on every station
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

    public void enterNewCar(Car c) {// this is how we keep the order of the cars, only if the wash's counter is
                                    // equal to the turn number of the car it will get in
        while (c.getTurnNumber() != counter) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
        counter++;
        try {
            notifyAll();
        } catch (Exception e) {}
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
