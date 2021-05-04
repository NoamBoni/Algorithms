import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class MySemaphore extends Semaphore {
    private int counter;
    private int permits;
    private ArrayList<Car> queue;

    public MySemaphore(int permits) {
        super(permits, true);
        this.permits = permits;
        counter = 0;
        queue = new ArrayList<Car>();
    }

    public void queueSemaphore() throws InterruptedException {
        while (queue.indexOf(Thread.currentThread()) < permits + counter) 
                try {
                    wait();
                } catch (Exception e) {
                    //TODO: handle exception
                }
    }

    @Override
    public void acquire() throws InterruptedException {
        queue.add((Car) Thread.currentThread());
        queueSemaphore();
        super.acquire();
    }

    @Override
    public void release() {
        counter++;
        super.release();
    }

}
