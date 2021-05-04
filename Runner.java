import java.util.*;
/**
 * Ron Cohen - 208401349
 * Noam Boni - 315586131
 */
public class Runner {
    public static void main(String[] args) {
        Random rand = new Random();
        int N = 3;
        int K = 3;
        int M = 10;
        CarWash wash = new CarWash(N, K, M);

        for (int i = 0; i < M; i++) {
            new Car(wash).start();
        }
    }
}
