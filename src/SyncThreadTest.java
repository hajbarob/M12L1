import java.util.ArrayList;
import java.util.List;

public class SyncThreadTest {

    private volatile static int counter = 0;

    public static void increment() {
        System.out.println("aaa");
        System.out.println("aaa");
        System.out.println("aaa");
        System.out.println("aaa");
        System.out.println("aaa");
        System.out.println("aaa");
        System.out.println("aaa");
        synchronized (SyncThreadTest.class) {
            counter = counter + 1;
        }
    }

    public static synchronized void increment2() {
        counter = counter + 1;
    }

    public static void main(String[] args) throws InterruptedException {
        testThree();
    }

    public static void testOne() throws InterruptedException {
        Thread thread = new Thread(() -> {
            counter++;
        });
        thread.start();

        thread.join();

        System.out.println("counter = " + counter);
    }

    public static void testTwo() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    counter = counter + 1;
                }
            });
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("counter = " + counter);
    }

    public static void testThree() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    increment();
                }
            });
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("counter = " + counter);

    }
}
