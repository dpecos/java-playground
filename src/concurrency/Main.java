package concurrency;

public class Main {
    // https://howtodoinjava.com/java/multi-threading/structured-concurrency/
    public static void main(String[] args) {
        System.out.println("Shutdown on success (early exit)");
        System.out.println("------------------");
        ShutdownOnSuccess.getClientDetails(1000L);
        System.out.println("------------------\n\n");

        System.out.println("Shutdown on failure (exit if failure)");
        System.out.println("------------------");
        ShutdownOnFailure.getClientDetails(1000L);
        System.out.println("------------------");
    }
}
