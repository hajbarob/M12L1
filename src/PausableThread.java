public class PausableThread extends Thread {

    private volatile boolean running = false;

    private volatile boolean isAlive = true;

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        while (isAlive) {
            if (running) {
                System.out.println("running");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PausableThread pt = new PausableThread();
        pt.start();

        System.out.println("main sleep for 5000");
        Thread.sleep(5000);

        System.out.println("pt.setRunning(true);");
        pt.setRunning(true);

        System.out.println("main sleep for 5000");
        Thread.sleep(5000);

        System.out.println("pt.setRunning(false);");
        pt.setRunning(false);
        pt.setAlive(false);
    }
}
