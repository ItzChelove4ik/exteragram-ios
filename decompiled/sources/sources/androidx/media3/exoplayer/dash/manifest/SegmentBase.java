package androidx.media3.exoplayer.dash.manifest;

import androidx.media3.common.Format;
import androidx.media3.common.util.Util;
import com.google.common.math.BigIntegerMath;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class SegmentBase {
    final RangedUri initialization;
    final long presentationTimeOffset;
    final long timescale;

    public static abstract class MultiSegmentBase extends SegmentBase {
        final long availabilityTimeOffsetUs;
        final long duration;
        private final long periodStartUnixTimeUs;
        final List<SegmentTimelineElement> segmentTimeline;
        final long startNumber;
        private final long timeShiftBufferDepthUs;

        public MultiSegmentBase(RangedUri r1, long r2, long r4, long r6, long r8, List<SegmentTimelineElement> r10, long r11, long r13, long r15) {
            super(r1, r2, r4);
            this.startNumber = r6;
            this.duration = r8;
            this.segmentTimeline = r10;
            this.availabilityTimeOffsetUs = r11;
            this.timeShiftBufferDepthUs = r13;
            this.periodStartUnixTimeUs = r15;
        }

        public long getAvailableSegmentCount(long r5, long r7) {
            long r0 = getSegmentCount(r5);
            if (r0 == (-1)) goto L6;
            return r0;
        L6:
            return (int) (getSegmentNum((r7 - this.periodStartUnixTimeUs) + this.availabilityTimeOffsetUs, r5) - getFirstAvailableSegmentNum(r5, r7));
        }

        public long getFirstAvailableSegmentNum(long r5, long r7) {
            if (getSegmentCount(r5) != (-1)) goto L10;
            long r0 = this.timeShiftBufferDepthUs;
            if (r0 == (-9223372036854775807L)) goto L10;
            long r52 = getSegmentNum((r7 - this.periodStartUnixTimeUs) - r0, r5);
            return Math.max(getFirstSegmentNum(), r52);
        L10:
            return getFirstSegmentNum();
        }

        public long getFirstSegmentNum() {
            return this.startNumber;
        }

        public long getNextSegmentAvailableTimeUs(long r3, long r5) {
            if (this.segmentTimeline == null) goto L6;
            return -9223372036854775807L;
        L6:
            long r0 = getFirstAvailableSegmentNum(r3, r5) + getAvailableSegmentCount(r3, r5);
            return (getSegmentTimeUs(r0) + getSegmentDurationUs(r0, r3)) - this.availabilityTimeOffsetUs;
        }

        public abstract long getSegmentCount(long r1);

        public final long getSegmentDurationUs(long r8, long r10) {
            List<SegmentTimelineElement> r0 = this.segmentTimeline;
            if (r0 != null) goto L5;
            long r3 = getSegmentCount(r10);
            if (r3 == (-1)) goto L13;
            if (r8 != ((getFirstSegmentNum() + r3) - 1)) goto L13;
            return r10 - getSegmentTimeUs(r8);
        L13:
            return (this.duration * 1000000) / this.timescale;
        L5:
            return (r0.get((int) (r8 - this.startNumber)).duration * 1000000) / this.timescale;
        }

        public long getSegmentNum(long r12, long r14) {
            long r0 = getFirstSegmentNum();
            long r142 = getSegmentCount(r14);
            if (r142 != 0) goto L6;
        L9:
            return r0;
        L6:
            if (this.segmentTimeline != null) goto L15;
            long r5 = (this.duration * 1000000) / this.timescale;
            long r7 = this.startNumber + (r12 / r5);
            if (r7 < r0) goto L9;
            if (r142 != (-1)) goto L14;
            return r7;
        L14:
            return Math.min(r7, (r0 + r142) - 1);
        L15:
            long r143 = (r142 + r0) - 1;
            long r52 = r0;
        L17:
            if (r52 > r143) goto L25;
            long r72 = ((r143 - r52) / 2) + r52;
            long r9 = getSegmentTimeUs(r72);
            if (r9 < r12) goto L20;
            if (r9 <= r12) goto L23;
            r143 = r72 - 1;
            goto L17
        L23:
            return r72;
        L20:
            r52 = r72 + 1;
            goto L17
        L25:
            if (r52 != r0) goto L27;
            return r52;
        L27:
            return r143;
        }

        public final long getSegmentTimeUs(long r7) {
            List<SegmentTimelineElement> r0 = this.segmentTimeline;
            long r1 = this.startNumber;
            if (r0 == null) goto L6;
            long r72 = r0.get((int) (r7 - r1)).startTime - this.presentationTimeOffset;
        L5:
            long r02 = r72;
            return Util.scaleLargeTimestamp(r02, 1000000, this.timescale);
        L6:
            r72 = (r7 - r1) * this.duration;
            goto L5
        }

        public abstract RangedUri getSegmentUrl(Representation r1, long r2);

        public boolean isExplicit() {
            if (this.segmentTimeline == null) goto L6;
            return true;
        L6:
            return false;
        }
    }

    public static final class SegmentList extends MultiSegmentBase {
        final List<RangedUri> mediaSegments;

        public SegmentList(RangedUri r18, long r19, long r21, long r23, long r25, List<SegmentTimelineElement> r27, long r28, List<RangedUri> r30, long r31, long r33) {
            super(r18, r19, r21, r23, r25, r27, r28, r31, r33);
            this.mediaSegments = r30;
        }

        @Override // androidx.media3.exoplayer.dash.manifest.SegmentBase.MultiSegmentBase
        public long getSegmentCount(long r1) {
            return this.mediaSegments.size();
        }

        @Override // androidx.media3.exoplayer.dash.manifest.SegmentBase.MultiSegmentBase
        public RangedUri getSegmentUrl(Representation r3, long r4) {
            return this.mediaSegments.get((int) (r4 - this.startNumber));
        }

        @Override // androidx.media3.exoplayer.dash.manifest.SegmentBase.MultiSegmentBase
        public boolean isExplicit() {
            return true;
        }
    }

    public static final class SegmentTemplate extends MultiSegmentBase {
        final long endNumber;
        final UrlTemplate initializationTemplate;
        final UrlTemplate mediaTemplate;

        public SegmentTemplate(RangedUri r18, long r19, long r21, long r23, long r25, long r27, List<SegmentTimelineElement> r29, long r30, UrlTemplate r32, UrlTemplate r33, long r34, long r36) {
            super(r18, r19, r21, r23, r27, r29, r30, r34, r36);
            this.initializationTemplate = r32;
            this.mediaTemplate = r33;
            this.endNumber = r25;
        }

        @Override // androidx.media3.exoplayer.dash.manifest.SegmentBase
        public RangedUri getInitialization(Representation r14) {
            UrlTemplate r0 = this.initializationTemplate;
            if (r0 == null) goto L7;
            Format r13 = r14.format;
            return new RangedUri(r0.buildUri(r13.id, 0, r13.bitrate, 0), 0, -1);
        L7:
            return super.getInitialization(r14);
        }

        @Override // androidx.media3.exoplayer.dash.manifest.SegmentBase.MultiSegmentBase
        public long getSegmentCount(long r6) {
            if (this.segmentTimeline != null) goto L5;
            long r0 = this.endNumber;
            if (r0 == (-1)) goto L11;
            return (r0 - this.startNumber) + 1;
        L11:
            if (r6 != (-9223372036854775807L)) goto L13;
            return -1;
        L13:
            return BigIntegerMath.divide(BigInteger.valueOf(r6).multiply(BigInteger.valueOf(this.timescale)), BigInteger.valueOf(this.duration).multiply(BigInteger.valueOf(1000000)), RoundingMode.CEILING).longValue();
        L5:
            return r0.size();
        }

        @Override // androidx.media3.exoplayer.dash.manifest.SegmentBase.MultiSegmentBase
        public RangedUri getSegmentUrl(Representation r17, long r18) {
            List<SegmentTimelineElement> r1 = this.segmentTimeline;
            long r2 = this.startNumber;
            if (r1 == null) goto L6;
            long r12 = r1.get((int) (r18 - r2)).startTime;
        L5:
            long r8 = r12;
            UrlTemplate r3 = this.mediaTemplate;
            Format r0 = r17.format;
            return new RangedUri(r3.buildUri(r0.id, r18, r0.bitrate, r8), 0, -1);
        L6:
            r12 = (r18 - r2) * this.duration;
            goto L5
        }
    }

    public static final class SegmentTimelineElement {
        final long duration;
        final long startTime;

        public SegmentTimelineElement(long r1, long r3) {
            this.startTime = r1;
            this.duration = r3;
        }

        public boolean equals(Object r7) {
            if (this != r7) goto L6;
            return true;
        L6:
            if (r7 != null) goto L8;
        L15:
            return false;
        L8:
            if (SegmentTimelineElement.class != r7.getClass()) goto L15;
            SegmentTimelineElement r72 = (SegmentTimelineElement) r7;
            if (this.startTime != r72.startTime) goto L15;
            if (this.duration != r72.duration) goto L15;
            return true;
        }

        public int hashCode() {
            return (((int) this.startTime) * 31) + ((int) this.duration);
        }
    }

    public static class SingleSegmentBase extends SegmentBase {
        final long indexLength;
        final long indexStart;

        public SingleSegmentBase() {
            this(null, 1, 0, 0, 0);
        }

        public SingleSegmentBase(RangedUri r1, long r2, long r4, long r6, long r8) {
            super(r1, r2, r4);
            this.indexStart = r6;
            this.indexLength = r8;
        }

        public RangedUri getIndex() {
            long r4 = this.indexLength;
            if (r4 > 0) goto L7;
            return null;
        L7:
            return new RangedUri(null, this.indexStart, r4);
        }
    }

    public SegmentBase(RangedUri r1, long r2, long r4) {
        this.initialization = r1;
        this.timescale = r2;
        this.presentationTimeOffset = r4;
    }

    public RangedUri getInitialization(Representation r1) {
        return this.initialization;
    }

    public long getPresentationTimeOffsetUs() {
        return Util.scaleLargeTimestamp(this.presentationTimeOffset, 1000000, this.timescale);
    }
}
