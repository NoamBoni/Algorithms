import java.util.ArrayList;

public class CarWash {
    private int K;
    private int N;
    private int M;
    private int counterThirdStation = 0;
    private MySemaphore semN;
    private MySemaphore semK;

    public CarWash(int N, int K, int M) {
        this.N = N;
        this.K = K;
        this.M = M;
        this.semN = new MySemaphore(N);
        this.semK = new MySemaphore(K);
    }

    // public void init(Car c) {

    // }

    public void firstStation(Car c) {
        synchronized(this){
            try {
                semN.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        float waitTime = carWait();
        System.out.println("Car number " + c.getTurnNumber() + " arrived to the first station, being washed for "
                + waitTime + " seconds");
        semN.release();
        secondStation(c);
    }

    public void secondStation(Car c) {
        synchronized(this){
            try {
                semK.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        float waitTime = carWait();
        System.out.println("Car number " + c.getTurnNumber() + " arrived to the second station, being washed for "
                + waitTime + " seconds");
        semK.release();
        thirdStation(c);
    }

    public void thirdStation(Car c) {
        if (++counterThirdStation == M)
            notifyAll();
        while (counterThirdStation < M) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
        System.out.println("Car number " + c.getTurnNumber() + " left thr car wash :)");
    }

    public synchronized float carWait() {
        float waitTime = (float) ((-1 * Math.log(Math.random()) / 1.5));
        try {
            Thread.sleep((int) waitTime * 1000);
        } catch (Exception e) {
        }
        return waitTime;
    }

}
