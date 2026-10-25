package androidx.media3.extractor.metadata.mp4;

import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.google.common.collect.ComparisonChain;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class SlowMotionData implements Metadata.Entry {
    public final List<Segment> segments;

    public static final class Segment {
        public static final Comparator<Segment> BY_START_THEN_END_THEN_DIVISOR = null;
        public final long endTimeMs;
        public final int speedDivisor;
        public final long startTimeMs;

        public static /* synthetic */ int $r8$lambda$cDyB_U3UxHiO2xAuH5ZOgxRUiGQ(Segment r5, Segment r6) {
            return ComparisonChain.start().compare(r5.startTimeMs, r6.startTimeMs).compare(r5.endTimeMs, r6.endTimeMs).compare(r5.speedDivisor, r6.speedDivisor).result();
        }

        static {
            BY_START_THEN_END_THEN_DIVISOR = new SlowMotionData$Segment$$ExternalSyntheticLambda0();
        }

        public Segment(long r2, long r4, int r6) {
            if (r2 >= r4) goto L5;
            boolean r0 = true;
        L6:
            Assertions.checkArgument(r0);
            this.startTimeMs = r2;
            this.endTimeMs = r4;
            this.speedDivisor = r6;
            return;
        L5:
            r0 = false;
            goto L6
        }

        public boolean equals(Object r7) {
            if (this != r7) goto L6;
            return true;
        L6:
            if (r7 != null) goto L8;
        L17:
            return false;
        L8:
            if (Segment.class != r7.getClass()) goto L17;
            Segment r72 = (Segment) r7;
            if (this.startTimeMs != r72.startTimeMs) goto L17;
            if (this.endTimeMs != r72.endTimeMs) goto L17;
            if (this.speedDivisor != r72.speedDivisor) goto L17;
            return true;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{Long.valueOf(this.startTimeMs), Long.valueOf(this.endTimeMs), Integer.valueOf(this.speedDivisor)});
        }

        public String toString() {
            return Util.formatInvariant("Segment: startTimeMs=%d, endTimeMs=%d, speedDivisor=%d", new Object[]{Long.valueOf(this.startTimeMs), Long.valueOf(this.endTimeMs), Integer.valueOf(this.speedDivisor)});
        }
    }

    public SlowMotionData(List<Segment> r1) {
        this.segments = r1;
        Assertions.checkArgument(!doSegmentsOverlap(r1));
    }

    private static boolean doSegmentsOverlap(List<Segment> r7) {
        if (r7.isEmpty() == false) goto L5;
        return false;
    L5:
        long r2 = r7.get(0).endTimeMs;
        int r4 = 1;
    L7:
        if (r4 >= r7.size()) goto L12;
        if (r7.get(r4).startTimeMs < r2) goto L10;
        r2 = r7.get(r4).endTimeMs;
        r4 = r4 + 1;
        goto L7
    L10:
        return true;
    L12:
        return false;
    }

    public boolean equals(Object r3) {
        if (this != r3) goto L5;
        return true;
    L5:
        if (r3 != null) goto L7;
        return false;
    L7:
        if (SlowMotionData.class == r3.getClass()) goto L10;
        return false;
    L10:
        return this.segments.equals(((SlowMotionData) r3).segments);
    }

    public int hashCode() {
        return this.segments.hashCode();
    }

    public String toString() {
        return "SlowMotion: segments=" + this.segments;
    }
}
