/**
 * Ron Cohen - 208401349
 * Noam Boni - 315586131
 */
public class Car extends Thread {
    private CarWash wash;
    private static int counter = 0;
    private int turnNumber;

    public Car(CarWash wash) {
        this.wash = wash;
        turnNumber = counter;
        counter++;
    }

    public void run() {
        System.out.println("Car number " + turnNumber + " entered the wash");
        wash.enterNewCar(this);
    }

    public int getTurnNumber() {
        return turnNumber;
    }
}
