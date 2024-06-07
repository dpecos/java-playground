package threads;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executors;

import static java.time.temporal.ChronoUnit.SECONDS;

// https://howtodoinjava.com/java/multi-threading/virtual-threads/
public class Main {
    public static void main(String[] args) {
        Runnable task = () -> {
            try {
                Thread.sleep(Duration.of(1, SECONDS));
            } catch (Exception e) {
                System.err.println(e);
            }

            System.out.println(Thread.currentThread() + " done");
        };

        // platform threads
        Instant init = Instant.now();
        try (var executor = Executors.newFixedThreadPool(100)) {
            for (var i = 0; i < 10_000; i++) {
                executor.submit(task);
            }
        }
        Duration ellapsedPlatformThreads = Duration.between(init, Instant.now());

        // virtual threads
        init = Instant.now();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (var i = 0; i < 10_000; i++) {
                executor.submit(task);
            }
        }
        Duration ellapsedVirtualThreads  = Duration.between(init, Instant.now());

        System.out.println("\nDone");
        System.out.println("Platform threads took " + ellapsedPlatformThreads.toMillis() + " ms");
        System.out.println("Virtual threads took " + ellapsedVirtualThreads.toMillis() + " ms");
    }
}
