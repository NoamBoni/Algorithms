/**
 * Ron Cohen - 208401349
 * Noam Boni - 315586131
 */
public class Runner {
    public static void main(String[] args) {
        int N = 2;
        int K = 3;
        int M = 8;
        CarWash wash = new CarWash(N, K, M);

        for (int i = 0; i < M; i++) {
            new Car(wash).start();
        }
    }
}
