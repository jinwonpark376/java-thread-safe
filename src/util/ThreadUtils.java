package util;

import static util.CustomLogger.log;

public abstract class ThreadUtils {
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log("Interrupted while sleeping. message: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
