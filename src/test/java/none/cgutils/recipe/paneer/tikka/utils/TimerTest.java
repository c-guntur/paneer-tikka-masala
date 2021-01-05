package none.cgutils.recipe.paneer.tikka.utils;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TimerTest {

    @Test
    void thirtyMinutes() {
        Timer timer = new Timer();
        Clock.system(ZoneId.systemDefault());
        timer.start();
        try {
            Thread.sleep(300L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.end();
        assertTrue(timer.getDuration().startsWith("30m"));
    }

    @Test
    void hourAndThirteenMinutes() {
        Timer timer = new Timer();
        Clock.system(ZoneId.systemDefault());
        timer.start();
        try {
            Thread.sleep(730L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.end();
        assertTrue(timer.getDuration().startsWith("1h 13m"));
    }
}