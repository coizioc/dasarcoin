package com.coizioc.dasarcoin.util;

import lombok.experimental.UtilityClass;
import org.assertj.core.util.VisibleForTesting;

import java.time.Clock;
import java.time.Instant;

@UtilityClass
public class Timestamp {
    @VisibleForTesting
    public static Clock clock = Clock.systemUTC();

    public static long now() {
        return Instant.now(clock).getEpochSecond();
    }
}
