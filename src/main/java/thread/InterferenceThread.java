package thread;

import java.util.concurrent.atomic.AtomicInteger;

import static thread.InterferenceExample.HUNDRED_MILLION;

public class InterferenceThread extends Thread {

    private final InterferenceExample checker;
    private static AtomicInteger counter = new AtomicInteger();

    public InterferenceThread(InterferenceExample checker) {
        this.checker = checker;
    }

    @Override
    public void run() {
        while (!checker.stop()) {
            increment();
        }
    }

    private void increment() {
        if (counter.get() < HUNDRED_MILLION) {
            counter.incrementAndGet();
        }
    }

    public int getCounter() {
        return counter.get();
    }
}

