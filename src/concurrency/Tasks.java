package concurrency;

public class Tasks {
    public static DemographicData fetchUserDetails(Long id) throws InterruptedException {
        Thread.sleep(2000L);
        System.out.println("Retrieved DemographicData.");
        return new DemographicData();
    }

    public static DemographicData fetchUserDetailsNew(Long id) throws InterruptedException {
        Thread.sleep(1000L);
        System.out.println("Retrieved DemographicData from fetchUserDetailsNew.");
        return new DemographicData();
    }
}
