package none.cgutils.recipe.paneer.tikka.parts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

public class RecipePart {

    public static final Long DEFAULT_DELAY_MINUTES = 2L;
    public static final Long MILLISECONDS_IN_A_MINUTE = 60000L;
    public static final Long MILLISECONDS_MAPPER = 6000L;
    public static final String DELAY_REASON = "delay.reason";
    public static final String RECIPE_PART = "recipe.part";
    private static final Logger LOGGER = LogManager.getLogger(RecipePart.class);

    /**
     * Introduces a simulated delay mapping to the default delay time
     */
    public static void delayMinutes() {

        delayMinutes(DEFAULT_DELAY_MINUTES, "Default delay");
    }

    /**
     * Introduces a simulated delay mapping to suggested delay minutes, with a reason for the delay.
     *
     * @param minutes - Preferred delay in minutes (will map to some milliseconds)
     * @param reason  - A log-worthy reason explaining the rationale for the delay.
     */
    public static void delayMinutes(Long minutes, String reason) {

        ThreadContext.put(DELAY_REASON, reason);

        LOGGER.info("Waiting for {} minutes", minutes);

        long delayTime = mapMinutesToMillisForDemo(minutes);
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LOGGER.info("Done !");

        ThreadContext.remove(DELAY_REASON);
    }

    /**
     * Maps the provided minutes to a calculated number of milliseconds.
     *
     * @param minutes - Actual time in minutes that the delay is intended for
     * @return - a long representing a mapped amount of milliseconds, to simulate a delay.
     */
    static long mapMinutesToMillisForDemo(Long minutes) {

        long millis = minutes * MILLISECONDS_IN_A_MINUTE;
        return millis / MILLISECONDS_MAPPER;
    }
}
