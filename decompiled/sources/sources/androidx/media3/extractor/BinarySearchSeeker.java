package androidx.media3.extractor;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.SeekMap;
import com.google.android.gms.cast.MediaStatus;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public abstract class BinarySearchSeeker {
    private final int minimumSearchRange;
    protected final BinarySearchSeekMap seekMap;
    protected SeekOperationParams seekOperationParams;
    protected final TimestampSeeker timestampSeeker;

    public static class BinarySearchSeekMap implements SeekMap {
        private final long approxBytesPerFrame;
        private final long ceilingBytePosition;
        private final long ceilingTimePosition;
        private final long durationUs;
        private final long floorBytePosition;
        private final long floorTimePosition;
        private final SeekTimestampConverter seekTimestampConverter;

        public BinarySearchSeekMap(SeekTimestampConverter r1, long r2, long r4, long r6, long r8, long r10, long r12) {
            this.seekTimestampConverter = r1;
            this.durationUs = r2;
            this.floorTimePosition = r4;
            this.ceilingTimePosition = r6;
            this.floorBytePosition = r8;
            this.ceilingBytePosition = r10;
            this.approxBytesPerFrame = r12;
        }

        public static /* synthetic */ long access$1000(BinarySearchSeekMap r2) {
            return r2.floorTimePosition;
        }

        public static /* synthetic */ long access$1100(BinarySearchSeekMap r2) {
            return r2.ceilingTimePosition;
        }

        public static /* synthetic */ long access$1200(BinarySearchSeekMap r2) {
            return r2.floorBytePosition;
        }

        public static /* synthetic */ long access$1300(BinarySearchSeekMap r2) {
            return r2.ceilingBytePosition;
        }

        public static /* synthetic */ long access$1400(BinarySearchSeekMap r2) {
            return r2.approxBytesPerFrame;
        }

        @Override // androidx.media3.extractor.SeekMap
        public long getDurationUs() {
            return this.durationUs;
        }

        @Override // androidx.media3.extractor.SeekMap
        public SeekMap.SeekPoints getSeekPoints(long r14) {
            return new SeekMap.SeekPoints(new SeekPoint(r14, SeekOperationParams.calculateNextSearchBytePosition(this.seekTimestampConverter.timeUsToTargetTime(r14), this.floorTimePosition, this.ceilingTimePosition, this.floorBytePosition, this.ceilingBytePosition, this.approxBytesPerFrame)));
        }

        @Override // androidx.media3.extractor.SeekMap
        public boolean isSeekable() {
            return true;
        }

        public long timeUsToTargetTime(long r1) {
            return this.seekTimestampConverter.timeUsToTargetTime(r1);
        }
    }

    public static final class DefaultSeekTimestampConverter implements SeekTimestampConverter {
        public DefaultSeekTimestampConverter() {
        }

        @Override // androidx.media3.extractor.BinarySearchSeeker.SeekTimestampConverter
        public long timeUsToTargetTime(long r1) {
            return r1;
        }
    }

    public static class SeekOperationParams {
        private final long approxBytesPerFrame;
        private long ceilingBytePosition;
        private long ceilingTimePosition;
        private long floorBytePosition;
        private long floorTimePosition;
        private long nextSearchBytePosition;
        private final long seekTimeUs;
        private final long targetTimePosition;

        public SeekOperationParams(long r1, long r3, long r5, long r7, long r9, long r11, long r13) {
            this.seekTimeUs = r1;
            this.targetTimePosition = r3;
            this.floorTimePosition = r5;
            this.ceilingTimePosition = r7;
            this.floorBytePosition = r9;
            this.ceilingBytePosition = r11;
            this.approxBytesPerFrame = r13;
            this.nextSearchBytePosition = calculateNextSearchBytePosition(r3, r5, r7, r9, r11, r13);
        }

        public static /* synthetic */ long access$000(SeekOperationParams r2) {
            return r2.getSeekTimeUs();
        }

        public static /* synthetic */ long access$100(SeekOperationParams r2) {
            return r2.getFloorBytePosition();
        }

        public static /* synthetic */ long access$200(SeekOperationParams r2) {
            return r2.getCeilingBytePosition();
        }

        public static /* synthetic */ long access$300(SeekOperationParams r2) {
            return r2.getNextSearchBytePosition();
        }

        public static /* synthetic */ long access$400(SeekOperationParams r2) {
            return r2.getTargetTimePosition();
        }

        public static /* synthetic */ void access$800(SeekOperationParams r0, long r1, long r3) {
            r0.updateSeekCeiling(r1, r3);
        }

        public static /* synthetic */ void access$900(SeekOperationParams r0, long r1, long r3) {
            r0.updateSeekFloor(r1, r3);
        }

        public static long calculateNextSearchBytePosition(long r4, long r6, long r8, long r10, long r12, long r14) {
            if ((r10 + 1) < r12) goto L5;
        L9:
            return r10;
        L5:
            if ((r6 + 1) >= r8) goto L9;
            long r42 = (long) ((r4 - r6) * ((r12 - r10) / (r8 - r6)));
            return Util.constrainValue(((r42 + r10) - r14) - (r42 / 20), r10, r12 - 1);
        }

        private long getCeilingBytePosition() {
            return this.ceilingBytePosition;
        }

        private long getFloorBytePosition() {
            return this.floorBytePosition;
        }

        private long getNextSearchBytePosition() {
            return this.nextSearchBytePosition;
        }

        private long getSeekTimeUs() {
            return this.seekTimeUs;
        }

        private long getTargetTimePosition() {
            return this.targetTimePosition;
        }

        private void updateNextSearchBytePosition() {
            this.nextSearchBytePosition = calculateNextSearchBytePosition(this.targetTimePosition, this.floorTimePosition, this.ceilingTimePosition, this.floorBytePosition, this.ceilingBytePosition, this.approxBytesPerFrame);
        }

        private void updateSeekCeiling(long r1, long r3) {
            this.ceilingTimePosition = r1;
            this.ceilingBytePosition = r3;
            updateNextSearchBytePosition();
        }

        private void updateSeekFloor(long r1, long r3) {
            this.floorTimePosition = r1;
            this.floorBytePosition = r3;
            updateNextSearchBytePosition();
        }
    }

    public interface SeekTimestampConverter {
        long timeUsToTargetTime(long r1);
    }

    public static final class TimestampSearchResult {
        public static final TimestampSearchResult NO_TIMESTAMP_IN_RANGE_RESULT = null;
        private final long bytePositionToUpdate;
        private final long timestampToUpdate;
        private final int type;

        static {
            NO_TIMESTAMP_IN_RANGE_RESULT = new TimestampSearchResult(-3, -9223372036854775807L, -1);
        }

        private TimestampSearchResult(int r1, long r2, long r4) {
            this.type = r1;
            this.timestampToUpdate = r2;
            this.bytePositionToUpdate = r4;
        }

        public static /* synthetic */ int access$500(TimestampSearchResult r0) {
            return r0.type;
        }

        public static /* synthetic */ long access$600(TimestampSearchResult r2) {
            return r2.timestampToUpdate;
        }

        public static /* synthetic */ long access$700(TimestampSearchResult r2) {
            return r2.bytePositionToUpdate;
        }

        public static TimestampSearchResult overestimatedResult(long r6, long r8) {
            return new TimestampSearchResult(-1, r6, r8);
        }

        public static TimestampSearchResult targetFoundResult(long r6) {
            return new TimestampSearchResult(0, -9223372036854775807L, r6);
        }

        public static TimestampSearchResult underestimatedResult(long r6, long r8) {
            return new TimestampSearchResult(-2, r6, r8);
        }
    }

    public interface TimestampSeeker {
        default void onSeekFinished() {
        }

        TimestampSearchResult searchForTimestamp(ExtractorInput r1, long r2);
    }

    public BinarySearchSeeker(SeekTimestampConverter r15, TimestampSeeker r16, long r17, long r19, long r21, long r23, long r25, long r27, int r29) {
        this.timestampSeeker = r16;
        this.minimumSearchRange = r29;
        this.seekMap = new BinarySearchSeekMap(r15, r17, r19, r21, r23, r25, r27);
    }

    public SeekOperationParams createSeekParamsForTargetTimeUs(long r18) {
        return new SeekOperationParams(r18, this.seekMap.timeUsToTargetTime(r18), BinarySearchSeekMap.access$1000(this.seekMap), BinarySearchSeekMap.access$1100(this.seekMap), BinarySearchSeekMap.access$1200(this.seekMap), BinarySearchSeekMap.access$1300(this.seekMap), BinarySearchSeekMap.access$1400(this.seekMap));
    }

    public final SeekMap getSeekMap() {
        return this.seekMap;
    }

    public int handlePendingSeek(ExtractorInput r10, PositionHolder r11) {
    L2:
        SeekOperationParams r0 = (SeekOperationParams) Assertions.checkStateNotNull(this.seekOperationParams);
        long r1 = SeekOperationParams.access$100(r0);
        long r3 = SeekOperationParams.access$200(r0);
        long r5 = SeekOperationParams.access$300(r0);
        if ((r3 - r1) <= this.minimumSearchRange) goto L4;
        if (skipInputUntilPosition(r10, r5) == false) goto L9;
        r10.resetPeekPosition();
        TimestampSearchResult r12 = this.timestampSeeker.searchForTimestamp(r10, SeekOperationParams.access$400(r0));
        int r2 = TimestampSearchResult.access$500(r12);
        if (r2 == (-3)) goto L23;
        if (r2 != (-2)) goto L15;
        SeekOperationParams.access$900(r0, TimestampSearchResult.access$600(r12), TimestampSearchResult.access$700(r12));
        goto L2
    L15:
        if (r2 != (-1)) goto L16;
        SeekOperationParams.access$800(r0, TimestampSearchResult.access$600(r12), TimestampSearchResult.access$700(r12));
        goto L2
    L16:
        if (r2 != 0) goto L19;
        skipInputUntilPosition(r10, TimestampSearchResult.access$700(r12));
        markSeekOperationFinished(true, TimestampSearchResult.access$700(r12));
        return seekToPosition(r10, TimestampSearchResult.access$700(r12), r11);
    L19:
        Segment$$ExternalSyntheticBUOutline1.m("Invalid case");
        return 0;
    L23:
        markSeekOperationFinished(false, r5);
        return seekToPosition(r10, r5, r11);
    L9:
        return seekToPosition(r10, r5, r11);
    L4:
        markSeekOperationFinished(false, r1);
        return seekToPosition(r10, r1, r11);
    }

    public final boolean isSeeking() {
        if (this.seekOperationParams == null) goto L6;
        return true;
    L6:
        return false;
    }

    public final void markSeekOperationFinished(boolean r2, long r3) {
        this.seekOperationParams = null;
        this.timestampSeeker.onSeekFinished();
        onSeekOperationFinished(r2, r3);
    }

    public void onSeekOperationFinished(boolean r1, long r2) {
    }

    public final int seekToPosition(ExtractorInput r1, long r2, PositionHolder r4) {
        if (r2 != r1.getPosition()) goto L6;
        return 0;
    L6:
        r4.position = r2;
        return 1;
    }

    public final void setSeekTargetUs(long r3) {
        SeekOperationParams r0 = this.seekOperationParams;
        if (r0 != null) goto L5;
    L7:
        this.seekOperationParams = createSeekParamsForTargetTimeUs(r3);
        return;
    L5:
        if (SeekOperationParams.access$000(r0) != r3) goto L7;
    }

    public final boolean skipInputUntilPosition(ExtractorInput r3, long r4) {
        long r42 = r4 - r3.getPosition();
        if (r42 >= 0) goto L5;
        return false;
    L5:
        if (r42 > MediaStatus.COMMAND_STREAM_TRANSFER) goto L10;
        r3.skipFully((int) r42);
        return true;
    L10:
        return false;
    }
}
