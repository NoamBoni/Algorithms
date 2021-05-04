/**
 * Ron Cohen - 208401349 Noam Boni - 315586131
 */

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
        while (tickets <= 0 || !(tickets > 0 && checkFair(index))) {// only if there are tickets remaining and it's the
                                                                    // car turn it'll get in
            try {
                wait();
            } catch (Exception e) {
            }
        }
        tickets--;
    }

    public synchronized boolean checkFair(int index) {// checks if it's the car turn
        if (index == counter) {
            counter++;
            return true;
        }
        return false;
    }

}
