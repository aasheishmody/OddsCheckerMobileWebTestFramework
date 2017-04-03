package util;

import java.util.Optional;
import java.util.function.BooleanSupplier;

public class Retrier extends WebConnector{
    public static long defaultSleepTimeInMillis = 20l;

    public static void retry(String name, Runnable function, int numberOfTimes, long sleepTimeInMillis) {
        retryRec(name, function, Optional.empty(), 0, numberOfTimes, sleepTimeInMillis);
    }

    public static void retry(String name, Runnable function, int numberOfTimes) {
        retryRec(name, function, Optional.empty(), 0, numberOfTimes, defaultSleepTimeInMillis);
    }

    public static void retry(String name, Runnable function, BooleanSupplier until, int numberOfTimes, long sleepTimeInMillis) {
        retryRec(name, function, Optional.of(until), 0, numberOfTimes, sleepTimeInMillis);
    }

    private static void retryRec(String name, Runnable function, Optional<BooleanSupplier> until, int current, int total, long sleepTimeInMillis) {
        if (current == total) {
            throw new RuntimeException("[" + name + "]" + "Maximum number of retries (" + total + ")");
        } else {
            try {
                function.run();
            } catch (Throwable exception) {
                if (until.map(Retrier::requiresRetry).orElse(true)) {
                    getLogger().severe("Retrying (" + current + ") " + name + " because exception:" + exception.getMessage());
                    Sleeper.sleep(sleepTimeInMillis);
                    retryRec(name, function, until, current + 1, total, sleepTimeInMillis);
                } else {
                    getLogger().info("Successfully excuted: (" + current + ") " + name);
                }
            }
            Sleeper.sleep(sleepTimeInMillis);
            if (until.map(Retrier::requiresRetry).orElse(false)) {
                getLogger().severe("Retrying " + current+ " " + name + " because condition is false");
                retryRec(name, function, until, current + 1, total, sleepTimeInMillis);
            } else {
                getLogger().info("Successfully excuted: (" + current + ") " + name);
            }
        }
    }

    // Until function can contain size effects, in case of exception we shoul retry
    private static boolean requiresRetry(BooleanSupplier until) {
        try {
            return until != null && !until.getAsBoolean();
        } catch (Exception exception) {
            return false;
        }
    }
}
