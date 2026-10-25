package androidx.media3.exoplayer.text;

import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.extractor.text.CuesWithTiming;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import java.util.ArrayList;

/* loaded from: classes4.dex */
final class ReplacingCuesResolver implements CuesResolver {
    private final ArrayList<CuesWithTiming> cuesWithTimingList;

    public ReplacingCuesResolver() {
        this.cuesWithTimingList = new ArrayList();
    }

    private int getIndexOfCuesStartingAfter(long r4) {
        int r0 = 0;
    L3:
        int r1 = this.cuesWithTimingList.size();
        ArrayList<CuesWithTiming> r2 = this.cuesWithTimingList;
        if (r0 >= r1) goto L10;
        if (r4 < r2.get(r0).startTimeUs) goto L7;
        r0 = r0 + 1;
        goto L3
    L7:
        return r0;
    L10:
        return r2.size();
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public boolean addCues(CuesWithTiming r10, long r11) {
        if (r10.startTimeUs == (-9223372036854775807L)) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        if (r10.startTimeUs > r11) goto L13;
        long r5 = r10.endTimeUs;
        if (r5 != (-9223372036854775807L)) goto L11;
    L12:
        boolean r02 = true;
    L14:
        int r2 = this.cuesWithTimingList.size() - 1;
    L15:
        if (r2 < 0) goto L24;
        long r52 = r10.startTimeUs;
        long r7 = this.cuesWithTimingList.get(r2).startTimeUs;
        ArrayList<CuesWithTiming> r53 = this.cuesWithTimingList;
        if (r52 >= r7) goto L18;
        if (r53.get(r2).startTimeUs > r11) goto L23;
        r02 = false;
    L23:
        r2 = r2 - 1;
        goto L15
    L18:
        r53.add(r2 + 1, r10);
        return r02;
    L24:
        this.cuesWithTimingList.add(0, r10);
        return r02;
    L11:
        if (r11 < r5) goto L12;
    L13:
        r02 = false;
        goto L14
    L5:
        r0 = false;
        goto L6
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public void clear() {
        this.cuesWithTimingList.clear();
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public void discardCuesBeforeTimeUs(long r6) {
        int r0 = getIndexOfCuesStartingAfter(r6);
        if (r0 != 0) goto L5;
        return;
    L5:
        long r1 = this.cuesWithTimingList.get(r0 - 1).endTimeUs;
        if (r1 != (-9223372036854775807L)) goto L8;
    L9:
        r0 = r0 - 1;
    L10:
        this.cuesWithTimingList.subList(0, r0).clear();
        return;
    L8:
        if (r1 < r6) goto L10;
        goto L9
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public ImmutableList<Cue> getCuesAtTimeUs(long r5) {
        int r0 = getIndexOfCuesStartingAfter(r5);
        if (r0 == 0) goto L5;
        CuesWithTiming r4 = this.cuesWithTimingList.get(r0 - 1);
        long r02 = r4.endTimeUs;
        if (r02 == (-9223372036854775807L)) goto L14;
        if (r5 < r02) goto L14;
        return ImmutableList.of();
    L14:
        return r4.cues;
    L5:
        return ImmutableList.of();
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public long getNextCueChangeTimeUs(long r11) {
        if (this.cuesWithTimingList.isEmpty() == false) goto L6;
        return Long.MIN_VALUE;
    L6:
        if (r11 < this.cuesWithTimingList.get(0).startTimeUs) goto L8;
        int r3 = 1;
    L10:
        int r4 = this.cuesWithTimingList.size();
        ArrayList<CuesWithTiming> r5 = this.cuesWithTimingList;
        if (r3 >= r4) goto L24;
        CuesWithTiming r42 = r5.get(r3);
        if (r11 < r42.startTimeUs) goto L14;
        r3 = r3 + 1;
        goto L10
    L14:
        long r0 = this.cuesWithTimingList.get(r3 - 1).endTimeUs;
        if (r0 == (-9223372036854775807L)) goto L22;
        if (r0 <= r11) goto L22;
        if (r0 >= r42.startTimeUs) goto L22;
        return r0;
    L22:
        return r42.startTimeUs;
    L24:
        long r32 = ((CuesWithTiming) Iterables.getLast(r5)).endTimeUs;
        if (r32 != (-9223372036854775807L)) goto L27;
    L29:
        return Long.MIN_VALUE;
    L27:
        if (r11 >= r32) goto L29;
        return r32;
    L8:
        return this.cuesWithTimingList.get(0).startTimeUs;
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public long getPreviousCueChangeTimeUs(long r8) {
        if (this.cuesWithTimingList.isEmpty() == false) goto L5;
    L30:
        return -9223372036854775807L;
    L5:
        if (r8 < this.cuesWithTimingList.get(0).startTimeUs) goto L30;
        int r3 = 1;
    L8:
        int r4 = this.cuesWithTimingList.size();
        ArrayList<CuesWithTiming> r5 = this.cuesWithTimingList;
        if (r3 >= r4) goto L22;
        long r42 = r5.get(r3).startTimeUs;
        if (r8 == r42) goto L12;
        if (r8 < r42) goto L14;
        r3 = r3 + 1;
        goto L8
    L14:
        CuesWithTiming r7 = this.cuesWithTimingList.get(r3 - 1);
        long r32 = r7.endTimeUs;
        if (r32 == (-9223372036854775807L)) goto L20;
        if (r32 > r8) goto L20;
        return r32;
    L20:
        return r7.startTimeUs;
    L12:
        return r42;
    L22:
        CuesWithTiming r72 = (CuesWithTiming) Iterables.getLast(r5);
        long r33 = r72.endTimeUs;
        if (r33 == (-9223372036854775807L)) goto L29;
        if (r8 < r33) goto L29;
        return r33;
    L29:
        return r72.startTimeUs;
    }
}
