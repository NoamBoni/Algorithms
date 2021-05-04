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
        wash.firstStation(this);
    }

    public int getTurnNumber() {
        return turnNumber;
    }
}
