package androidx.media3.extractor.text;

import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Ordering;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes4.dex */
final class CuesWithTimingSubtitle implements Subtitle {
    private static final Ordering<CuesWithTiming> CUES_BY_START_TIME_ASCENDING = null;
    private final ImmutableList<ImmutableList<Cue>> eventCues;
    private final long[] eventTimesUs;

    public static /* synthetic */ Comparable $r8$lambda$Fjs177jCbMY6aKo7pC06dBgJNPk(CuesWithTiming r2) {
        return Long.valueOf(normalizeUnsetStartTimeToZero(r2.startTimeUs));
    }

    static {
        CUES_BY_START_TIME_ASCENDING = Ordering.natural().onResultOf(new CuesWithTimingSubtitle$$ExternalSyntheticLambda0());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CuesWithTimingSubtitle(List<CuesWithTiming> r15) {
        int r2 = 0;
        if (r15.size() != 1) goto L10;
        CuesWithTiming r152 = (CuesWithTiming) Iterables.getOnlyElement(r15);
        long r6 = normalizeUnsetStartTimeToZero(r152.startTimeUs);
        long r8 = r152.durationUs;
        ImmutableList<Cue> r3 = r152.cues;
        if (r8 != (-9223372036854775807L)) goto L8;
        this.eventCues = ImmutableList.of(r3);
        this.eventTimesUs = new long[]{r6};
        return;
    L8:
        this.eventCues = ImmutableList.of(r3, ImmutableList.of());
        this.eventTimesUs = new long[]{r6, r152.durationUs + r6};
        return;
    L10:
        long[] r0 = new long[r15.size() * 2];
        this.eventTimesUs = r0;
        Arrays.fill(r0, LongCompanionObject.MAX_VALUE);
        ArrayList r02 = new ArrayList();
        ImmutableList r153 = ImmutableList.sortedCopyOf(CUES_BY_START_TIME_ASCENDING, r15);
        int r1 = 0;
    L12:
        if (r2 >= r153.size()) goto L29;
        CuesWithTiming r5 = (CuesWithTiming) r153.get(r2);
        long r62 = normalizeUnsetStartTimeToZero(r5.startTimeUs);
        long r82 = r5.durationUs + r62;
        if (r1 == 0) goto L24;
        int r11 = r1 - 1;
        long r12 = this.eventTimesUs[r11];
        if (r12 < r62) goto L24;
        if (r12 == r62) goto L21;
    L23:
        Log.w("CuesWithTimingSubtitle", "Truncating unsupported overlapping cues.");
        this.eventTimesUs[r11] = r62;
        r02.set(r11, r5.cues);
    L26:
        if (r5.durationUs == (-9223372036854775807L)) goto L28;
        this.eventTimesUs[r1] = r82;
        r02.add(ImmutableList.of());
        r1 = r1 + 1;
    L28:
        r2 = r2 + 1;
        goto L12
    L21:
        if (((ImmutableList) r02.get(r11)).isEmpty() == false) goto L23;
        r02.set(r11, r5.cues);
    L24:
        this.eventTimesUs[r1] = r62;
        r02.add(r5.cues);
        r1 = r1 + 1;
        goto L26
    L29:
        this.eventCues = ImmutableList.copyOf(r02);
    }

    private static long normalizeUnsetStartTimeToZero(long r2) {
        if (r2 != (-9223372036854775807L)) goto L6;
        return 0;
    L6:
        return r2;
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public ImmutableList<Cue> getCues(long r4) {
        int r42 = Util.binarySearchFloor(this.eventTimesUs, r4, true, false);
        if (r42 != (-1)) goto L7;
        return ImmutableList.of();
    L7:
        return this.eventCues.get(r42);
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public /* bridge */ /* synthetic */ List getCues(long r1) {
        return getCues(r1);
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public long getEventTime(int r3) {
        if (r3 >= this.eventCues.size()) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        return this.eventTimesUs[r3];
    L5:
        r0 = false;
        goto L6
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public int getEventTimeCount() {
        return this.eventCues.size();
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public int getNextEventTimeIndex(long r3) {
        int r32 = Util.binarySearchCeil(this.eventTimesUs, r3, false, false);
        if (r32 >= this.eventCues.size()) goto L5;
        return r32;
    L5:
        return -1;
    }
}
