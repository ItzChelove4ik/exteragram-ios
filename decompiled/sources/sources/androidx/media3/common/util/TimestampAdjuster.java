package androidx.media3.common.util;

import java.util.concurrent.TimeoutException;
import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes3.dex */
public final class TimestampAdjuster {
    private long firstSampleTimestampUs;
    private long lastUnadjustedTimestampUs;
    private final ThreadLocal<Long> nextSampleTimestampUs;
    private long timestampOffsetUs;

    public TimestampAdjuster(long r2) {
        this.nextSampleTimestampUs = new ThreadLocal();
        reset(r2);
    }

    public static long ptsToUs(long r6) {
        return Util.scaleLargeTimestamp(r6, 1000000, 90000);
    }

    public static long usToNonWrappedPts(long r6) {
        return Util.scaleLargeTimestamp(r6, 90000, 1000000);
    }

    public static long usToWrappedPts(long r2) {
        return usToNonWrappedPts(r2) % 8589934592L;
    }

    public synchronized long adjustSampleTimestamp(long r5) {
        monitor-enter(this);
        if (r5 != (-9223372036854775807L)) goto L21;
        monitor-exit(this);
        return -9223372036854775807L;
    L21:
    L12:
        th = move-exception;
        throw th;
    L8:
        if (isInitialized() == true) goto L15;
        long r0 = this.firstSampleTimestampUs;     // Catch: Throwable -> L12
        if (r0 != 9223372036854775806L) goto L14;
        r0 = ((Long) Assertions.checkNotNull(this.nextSampleTimestampUs.get())).longValue();     // Catch: Throwable -> L12
    L14:
        this.timestampOffsetUs = r0 - r5;     // Catch: Throwable -> L12
        notifyAll();     // Catch: Throwable -> L12
    L15:
        this.lastUnadjustedTimestampUs = r5;     // Catch: Throwable -> L12
        long r52 = r5 + this.timestampOffsetUs;
        monitor-exit(this);
        return r52;
    }

    public synchronized long adjustTsTimestamp(long r9) {
        monitor-enter(this);
        if (r9 != (-9223372036854775807L)) goto L19;
        monitor-exit(this);
        return -9223372036854775807L;
    L19:
        long r2 = this.lastUnadjustedTimestampUs;     // Catch: Throwable -> L12
        if (r2 == (-9223372036854775807L)) goto L14;
        long r0 = usToNonWrappedPts(r2);     // Catch: Throwable -> L12
        long r22 = (4294967296L + r0) / 8589934592L;     // Catch: Throwable -> L12
        long r6 = ((r22 - 1) * 8589934592L) + r9;     // Catch: Throwable -> L12
        r9 = r9 + (r22 * 8589934592L);     // Catch: Throwable -> L12
        if (Math.abs(r6 - r0) >= Math.abs(r9 - r0)) goto L14;
        r9 = r6;
    L14:
        long r92 = adjustSampleTimestamp(ptsToUs(r9));     // Catch: Throwable -> L12
        monitor-exit(this);
        return r92;
    L12:
        th = move-exception;
        throw th;
    }

    public synchronized long adjustTsTimestampGreaterThanPreviousTimestamp(long r11) {
        monitor-enter(this);
        if (r11 != (-9223372036854775807L)) goto L19;
        monitor-exit(this);
        return -9223372036854775807L;
    L19:
        long r2 = this.lastUnadjustedTimestampUs;     // Catch: Throwable -> L12
        if (r2 == (-9223372036854775807L)) goto L14;
        long r0 = usToNonWrappedPts(r2);     // Catch: Throwable -> L12
        long r4 = r0 / 8589934592L;     // Catch: Throwable -> L12
        long r6 = (r4 * 8589934592L) + r11;     // Catch: Throwable -> L12
        r11 = r11 + ((r4 + 1) * 8589934592L);     // Catch: Throwable -> L12
        if (r6 < r0) goto L14;
        r11 = r6;
    L14:
        long r112 = adjustSampleTimestamp(ptsToUs(r11));     // Catch: Throwable -> L12
        monitor-exit(this);
        return r112;
    L12:
        th = move-exception;
        throw th;
    }

    public synchronized long getFirstSampleTimestampUs() {
        monitor-enter(this);
        long r0 = this.firstSampleTimestampUs;     // Catch: Throwable -> L11
        if (r0 != LongCompanionObject.MAX_VALUE) goto L7;
    L8:
        r0 = -9223372036854775807L;
    L9:
        monitor-exit(this);
        return r0;
    L7:
        if (r0 != 9223372036854775806L) goto L9;
    L11:
        th = move-exception;
        throw th;
    }

    public synchronized long getLastAdjustedTimestampUs() {
        monitor-enter(this);
        long r0 = this.lastUnadjustedTimestampUs;     // Catch: Throwable -> L6
        if (r0 == (-9223372036854775807L)) goto L8;
        long r02 = r0 + this.timestampOffsetUs;     // Catch: Throwable -> L6
    L9:
        monitor-exit(this);
        return r02;
    L8:
        r02 = getFirstSampleTimestampUs();     // Catch: Throwable -> L6
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized long getTimestampOffsetUs() {
        monitor-enter(this);
        long r0 = this.timestampOffsetUs;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized boolean isInitialized() {
        monitor-enter(this);
        if (this.timestampOffsetUs == (-9223372036854775807L)) goto L7;
        boolean r0 = true;
    L8:
        monitor-exit(this);
        return r0;
    L7:
        r0 = false;
    L10:
        th = move-exception;
        throw th;
    }

    public synchronized void reset(long r3) {
        monitor-enter(this);
        this.firstSampleTimestampUs = r3;     // Catch: Throwable -> L10
        if (r3 != LongCompanionObject.MAX_VALUE) goto L6;
        long r32 = 0;
    L7:
        this.timestampOffsetUs = r32;     // Catch: Throwable -> L10
        this.lastUnadjustedTimestampUs = -9223372036854775807L;     // Catch: Throwable -> L10
        monitor-exit(this);
        return;
    L6:
        r32 = -9223372036854775807L;
    L10:
        th = move-exception;
        throw th;
    }

    public synchronized void sharedInitializeOrWait(boolean r10, long r11, long r13) {
        monitor-enter(this);
    L13:
        th = move-exception;
        throw th;
    L4:
        if (this.firstSampleTimestampUs != 9223372036854775806L) goto L6;
        boolean r0 = true;
    L7:
        Assertions.checkState(r0);     // Catch: Throwable -> L13
        if (isInitialized() == false) goto L11;
        monitor-exit(this);
        return;
    L11:
        if (r10 == false) goto L15;
        this.nextSampleTimestampUs.set(Long.valueOf(r11));     // Catch: Throwable -> L13
    L33:
        monitor-exit(this);
        return;
    L15:
        long r5 = 0;
        long r3 = r13;
    L17:
        if (isInitialized() == true) goto L33;
        if (r13 == 0) goto L20;
        if (r3 <= 0) goto L24;
        boolean r12 = true;
    L25:
        Assertions.checkState(r12);     // Catch: Throwable -> L13
        long r7 = android.os.SystemClock.elapsedRealtime();     // Catch: Throwable -> L13
        wait(r3);     // Catch: Throwable -> L13
        r5 = r5 + (android.os.SystemClock.elapsedRealtime() - r7);     // Catch: Throwable -> L13
        if (r5 >= r13) goto L28;
    L32:
        r3 = r13 - r5;
        goto L17
    L28:
        if (isInitialized() == true) goto L32;
        throw new TimeoutException("TimestampAdjuster failed to initialize in " + r13 + " milliseconds");     // Catch: Throwable -> L13
    L24:
        r12 = false;
        goto L25
    L20:
        wait();     // Catch: Throwable -> L13
        goto L17
    L6:
        r0 = false;
        goto L7
    }
}
