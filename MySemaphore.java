
public class MySemaphore {
    private int tickets;
    private int counter;

    public MySemaphore(int tickets) {
        this.tickets = tickets;
        counter = 0;
    }

    public synchronized void release() {
        tickets++;
        notifyAll();
    }

    public synchronized void acquire(int index) {
        while (tickets <= 0 || !(tickets > 0 && checkFair(index))) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
        tickets--;
        // try {
        //     Thread.sleep(1500);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
    }

    public synchronized boolean checkFair(int index) {
        if (index == counter) {
            counter++;
            return true;
        }
        return false;
    }

}
