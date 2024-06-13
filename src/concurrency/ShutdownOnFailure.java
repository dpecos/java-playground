package concurrency;

import java.util.concurrent.StructuredTaskScope;

import static concurrency.Tasks.fetchUserDetails;
import static concurrency.Tasks.fetchUserDetailsNew;

public class ShutdownOnFailure {
    public static void getClientDetails(Long id) {
        System.out.println("Forking new threads...");
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            scope.fork(() -> fetchUserDetails(id));
            scope.fork(() -> fetchUserDetailsNew(id));
            scope.join();
            System.out.println("All responses received...");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
