package concurrency;

import java.util.concurrent.StructuredTaskScope;

import static concurrency.Tasks.fetchUserDetails;
import static concurrency.Tasks.fetchUserDetailsNew;

public class ShutdownOnSuccess {
    public static void getClientDetails(Long id) {
        System.out.println("Forking new threads...");
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<>()) {
            scope.fork(() -> fetchUserDetails(id));
            scope.fork(() -> fetchUserDetailsNew(id));
            scope.join();
            System.out.println("Response is received from a worker...");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
