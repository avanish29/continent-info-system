package net.avanishkpandey.universum.continentservice.util;

import java.util.concurrent.TimeUnit;

/**
 * An object that measures elapsed time in nanoseconds.
 */
public final class Chronometer {
    private final long startTime;

    private Chronometer() { this.startTime = System.nanoTime(); }

    /**
     * Creates (and starts) a new chronometer using System.nanoTime() as its time source.
     * @return instance of Chronometer.
     */
    public static Chronometer start() {
        return new Chronometer();
    }

    /**
     * Stops the chronometer and return the total time elapsed in MilliSeconds.
     * @return return the total time elapsed in MilliSeconds
     */
    public long stop() {
        return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - this.startTime);
    }
}
