package thread;

import java.util.concurrent.atomic.AtomicInteger;

public class InterferenceExample {

    static final int HUNDRED_MILLION = 100_000_000;
    private AtomicInteger counter = new AtomicInteger();

    boolean stop() {
        return counter.incrementAndGet() > HUNDRED_MILLION;
    }

    private void example() throws InterruptedException {
        InterferenceThread thread1 =  new InterferenceThread(this);
        InterferenceThread thread2 = new InterferenceThread(this);
        InterferenceThread thread3 = new InterferenceThread(this);
        InterferenceThread thread4 = new InterferenceThread(this);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread1.join();
        System.out.println("Expected: " + HUNDRED_MILLION);
        System.out.println("Result: " + thread1.getCounter());
    }

    public static void main(String[] args) throws InterruptedException {
        new InterferenceExample().example();
    }

}
