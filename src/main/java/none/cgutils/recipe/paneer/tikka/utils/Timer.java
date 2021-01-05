package none.cgutils.recipe.paneer.tikka.utils;

import none.cgutils.recipe.paneer.tikka.parts.RecipePart;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public class Timer {

    public static final String DURATION_TO_READABLE_STRING_REGEX = "(\\d[HMS])(?!$)";

    private final String timerName;
    private Instant start;
    private Instant end;

    /**
     * Default Timer with a random UUID, initializing the start time to now.
     */
    public Timer() {
        this(UUID.randomUUID().toString(), Instant.now());
    }

    /**
     * Timer with a name, initializing the start time to now.
     */
    public Timer(String timerName) {
        this(timerName, Instant.now());
    }

    /**
     * Timer with a name, and a start time.
     */
    public Timer(String timerName, Instant start) {
        this.timerName = timerName;
        this.start = start;
    }

    public void start() {
        start = Instant.now();
    }

    public void start(Clock clock) {
        start = Instant.now(clock);
    }

    public void end() {
        end = Instant.now();
    }

    public void end(Clock clock) {
        end = Instant.now(clock);
    }

    public String getDuration() {

        return getDuration(RecipePart.MILLISECONDS_MAPPER);
    }

    public String getDuration(Long customMillisecondMapper) {

        if (end == null) {
            return "still running";
        }
        long millis = Duration.between(start, end)
                .multipliedBy(customMillisecondMapper)
                .toMillis();

        return Duration.ofMillis(millis)
                .toString()
                .substring(2)
                .replaceAll(DURATION_TO_READABLE_STRING_REGEX, "$1 ")
                .toLowerCase();
    }

    @Override
    public String toString() {
        return "Timer " + timerName + "status: " + getDuration();
    }
}
