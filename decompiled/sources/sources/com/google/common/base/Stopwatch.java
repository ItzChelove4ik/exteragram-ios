package com.google.common.base;

import java.util.concurrent.TimeUnit;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class Stopwatch {
    private long elapsedNanos;
    private boolean isRunning;
    private long startTick;
    private final Ticker ticker;

    /* renamed from: com.google.common.base.Stopwatch$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$util$concurrent$TimeUnit = null;

        static {
            int[] r0 = new int[TimeUnit.values().length];
            $SwitchMap$java$util$concurrent$TimeUnit = r0;
            r0[TimeUnit.NANOSECONDS.ordinal()] = 1;     // Catch: NoSuchFieldError -> L11
        L18:
            $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MICROSECONDS.ordinal()] = 2;     // Catch: NoSuchFieldError -> L12
        L22:
            $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MILLISECONDS.ordinal()] = 3;     // Catch: NoSuchFieldError -> L13
        L30:
            $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.SECONDS.ordinal()] = 4;     // Catch: NoSuchFieldError -> L14
        L20:
            $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MINUTES.ordinal()] = 5;     // Catch: NoSuchFieldError -> L15
        L24:
            $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.HOURS.ordinal()] = 6;     // Catch: NoSuchFieldError -> L16
        L26:
            $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.DAYS.ordinal()] = 7;     // Catch: NoSuchFieldError -> L17
            return;
        }
    }

    public Stopwatch(Ticker r2) {
        this.ticker = (Ticker) Preconditions.checkNotNull(r2, "ticker");
    }

    private static String abbreviate(TimeUnit r1) {
        switch(AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit[r1.ordinal()]) {
            case 1: goto L18;
            case 2: goto L16;
            case 3: goto L14;
            case 4: goto L12;
            case 5: goto L10;
            case 6: goto L8;
            case 7: goto L6;
            default: goto L4;
        };
    L4:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return null;
    L6:
        return "d";
    L8:
        return "h";
    L10:
        return "min";
    L12:
        return "s";
    L14:
        return "ms";
    L16:
        return "μs";
    L18:
        return "ns";
    }

    private static TimeUnit chooseUnit(long r4) {
        if ((r4 / 86400000000000L) <= 0) goto L7;
        return TimeUnit.DAYS;
    L7:
        if ((r4 / 3600000000000L) <= 0) goto L11;
        return TimeUnit.HOURS;
    L11:
        if ((r4 / 60000000000L) <= 0) goto L15;
        return TimeUnit.MINUTES;
    L15:
        if ((r4 / 1000000000) <= 0) goto L19;
        return TimeUnit.SECONDS;
    L19:
        if ((r4 / 1000000) <= 0) goto L23;
        return TimeUnit.MILLISECONDS;
    L23:
        if ((r4 / 1000) <= 0) goto L27;
        return TimeUnit.MICROSECONDS;
    L27:
        return TimeUnit.NANOSECONDS;
    }

    public static Stopwatch createStarted(Ticker r1) {
        return new Stopwatch(r1).start();
    }

    private long elapsedNanos() {
        if (this.isRunning == false) goto L7;
        return (this.ticker.read() - this.startTick) + this.elapsedNanos;
    L7:
        return this.elapsedNanos;
    }

    public Stopwatch start() {
        Preconditions.checkState(!this.isRunning, "This stopwatch is already running.");
        this.isRunning = true;
        this.startTick = this.ticker.read();
        return this;
    }

    public String toString() {
        long r0 = elapsedNanos();
        TimeUnit r5 = chooseUnit(r0);
        return Platform.formatCompact4Digits(r0 / TimeUnit.NANOSECONDS.convert(1, r5)) + " " + abbreviate(r5);
    }
}
