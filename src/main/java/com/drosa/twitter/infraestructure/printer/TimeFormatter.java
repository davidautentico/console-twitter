package com.drosa.twitter.infraestructure.printer;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class TimeFormatter {
    private static final long ONE_MINUTE = TimeUnit.MINUTES.toMillis(1);
    private static final long ONE_HOUR = TimeUnit.HOURS.toMillis(1);
    private static final long ONE_DAY = TimeUnit.DAYS.toMillis(1);

    public static String getTimeDifference(Instant createdAt) {
        long timeDifference = Instant.now().toEpochMilli() - createdAt.toEpochMilli();
        long timeDiff;
        String timeUnit;
        if (timeDifference < ONE_MINUTE) {
            timeDiff = TimeUnit.MILLISECONDS.toSeconds(timeDifference);
            timeUnit = "second";
        } else if (timeDifference < ONE_HOUR) {
            timeDiff = TimeUnit.MILLISECONDS.toMinutes(timeDifference);
            timeUnit = "minute";
        } else if (timeDifference < ONE_DAY) {
            timeDiff = TimeUnit.MILLISECONDS.toHours(timeDifference);
            timeUnit = "hour";
        } else {
            timeDiff = TimeUnit.MILLISECONDS.toDays(timeDifference);
            timeUnit = "day";
        }

        if (timeDiff > 1) {
            timeUnit = timeUnit + "s";
        }
        return String.format("(%d %s ago)", timeDiff, timeUnit);
    }
}
