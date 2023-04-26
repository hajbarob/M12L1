public class ThreadSleepTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main thread Start");

        new Thread(() -> {
            System.out.println("In new thread");
            try {
                Thread.sleep(5000);
                Thread.currentThread().getName();
                //Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("End in new thread");
        }).start();

        Thread.sleep(1000);
        //Thread.sleep(1000);
        System.out.println("Main thread End");
    }
}
