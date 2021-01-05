package none.cgutils.recipe.paneer.tikka.parts;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RecipePartTest {

    @Test
    void delayMinutes() {
        Instant start = Instant.now();
        RecipePart.delayMinutes();
        Instant end = Instant.now();
        long timeElapsed = Duration.between(start, end).toMillis();
        long expected = RecipePart.MILLISECONDS_IN_A_MINUTE * RecipePart.DEFAULT_DELAY_MINUTES / RecipePart.MILLISECONDS_MAPPER;
        assertTrue(timeElapsed > expected);
    }

    @Test
    void testDelayMinutes() {
        Instant start = Instant.now();
        RecipePart.delayMinutes(30L, "30 min delay");
        Instant end = Instant.now();
        long timeElapsed = Duration.between(start, end).toMillis();
        long expected = RecipePart.MILLISECONDS_IN_A_MINUTE * 30 / RecipePart.MILLISECONDS_MAPPER;
        assertTrue(timeElapsed >= expected);
    }

    @Test
    void mapMinutesToMillisForDemo() {
        long mappedMilliseconds = RecipePart.mapMinutesToMillisForDemo(30L);
        long expected = Duration.ofMinutes(30).toMillis() / RecipePart.MILLISECONDS_MAPPER;
        assertEquals(mappedMilliseconds, expected);
    }
}