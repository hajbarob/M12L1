public class EndlessLoopTest {

    public static void main(String[] args) {

        int a = 9;
        a +=8;
        printThreadName();

        Thread thread = new Thread(EndlessLoopTest::printThreadName);
        thread.setName("first thread");
        thread.setPriority(10);

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable realization");
                System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            }
        });
        thread2.setName("second thread");

        CustomMultiplyThread customThread = new CustomMultiplyThread(9, 9);
        customThread.setName("CustomMultiplyThread");

        thread.start();
        thread2.start();
        customThread.start();

    }

    public static class CustomMultiplyThread extends Thread{

        private int a, b;

        public CustomMultiplyThread(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            System.out.println("I just multiply numbers in thread = " + a * b);
            System.out.println("in custom thread class");
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        }
    }

    private static void printThreadName() {
        System.out.println(
                "Thread.currentThread().getName() = " +
                        Thread.currentThread().getName()
        );
    }
}
