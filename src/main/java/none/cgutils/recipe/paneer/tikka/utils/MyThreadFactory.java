package none.cgutils.recipe.paneer.tikka.utils;

import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public class MyThreadFactory implements ThreadFactory {

    final AtomicLong count;
    String nameFormat;

    public MyThreadFactory() {
        this("RecipePart-%d");
    }

    public MyThreadFactory(String nameFormat) {
        this.nameFormat = nameFormat;
        count = (nameFormat != null) ? new AtomicLong(0) : null;
    }

    private static String format(String format, Object... args) {
        return String.format(Locale.ROOT, format, args);
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = Executors.defaultThreadFactory().newThread(runnable);
        if (nameFormat != null) {
            thread.setName(format(nameFormat, count.getAndIncrement()));
        }
        return thread;
    }
}
