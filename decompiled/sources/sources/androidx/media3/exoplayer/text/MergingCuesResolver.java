package androidx.media3.exoplayer.text;

import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.extractor.text.CuesWithTiming;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
final class MergingCuesResolver implements CuesResolver {
    private static final Ordering<CuesWithTiming> CUES_DISPLAY_PRIORITY_COMPARATOR = null;
    private final List<CuesWithTiming> cuesWithTimingList;

    public static /* synthetic */ Long $r8$lambda$IYQ6NFPgGvIp5yy_PJ2dwIRwFAk(CuesWithTiming r2) {
        return Long.valueOf(r2.durationUs);
    }

    /* renamed from: $r8$lambda$x4QKFLq1ovJ7UCAN-UhatKa9UT4, reason: not valid java name */
    public static /* synthetic */ Long m843$r8$lambda$x4QKFLq1ovJ7UCANUhatKa9UT4(CuesWithTiming r2) {
        return Long.valueOf(r2.startTimeUs);
    }

    static {
        CUES_DISPLAY_PRIORITY_COMPARATOR = Ordering.natural().onResultOf(new MergingCuesResolver$$ExternalSyntheticLambda0()).compound(Ordering.natural().reverse().onResultOf(new MergingCuesResolver$$ExternalSyntheticLambda1()));
    }

    public MergingCuesResolver() {
        this.cuesWithTimingList = new ArrayList();
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public boolean addCues(CuesWithTiming r8, long r9) {
        if (r8.startTimeUs == (-9223372036854775807L)) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        if (r8.durationUs == (-9223372036854775807L)) goto L9;
        boolean r02 = true;
    L10:
        Assertions.checkArgument(r02);
        if (r8.startTimeUs <= r9) goto L13;
    L15:
        boolean r92 = false;
    L16:
        int r10 = this.cuesWithTimingList.size() - 1;
    L17:
        if (r10 < 0) goto L23;
        if (r8.startTimeUs >= this.cuesWithTimingList.get(r10).startTimeUs) goto L20;
        r10 = r10 - 1;
        goto L17
    L20:
        this.cuesWithTimingList.add(r10 + 1, r8);
        return r92;
    L23:
        this.cuesWithTimingList.add(0, r8);
        return r92;
    L13:
        if (r9 >= r8.endTimeUs) goto L15;
        r92 = true;
        goto L16
    L9:
        r02 = false;
        goto L10
    L5:
        r0 = false;
        goto L6
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public void clear() {
        this.cuesWithTimingList.clear();
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public void discardCuesBeforeTimeUs(long r5) {
        int r0 = 0;
    L4:
        if (r0 >= this.cuesWithTimingList.size()) goto L13;
        long r1 = this.cuesWithTimingList.get(r0).startTimeUs;
        if (r5 > r1) goto L8;
    L10:
        if (r5 >= r1) goto L12;
        return;
    L12:
        r0 = r0 + 1;
        goto L4
    L8:
        if (r5 <= this.cuesWithTimingList.get(r0).endTimeUs) goto L10;
        this.cuesWithTimingList.remove(r0);
        r0 = r0 - 1;
        goto L12
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.media3.exoplayer.text.CuesResolver
    public ImmutableList<Cue> getCuesAtTimeUs(long r7) {
        if (this.cuesWithTimingList.isEmpty() == true) goto L26;
        int r1 = 0;
        if (r7 < this.cuesWithTimingList.get(0).startTimeUs) goto L26;
        ArrayList r0 = new ArrayList();
        int r2 = 0;
    L9:
        if (r2 >= this.cuesWithTimingList.size()) goto L19;
        CuesWithTiming r3 = this.cuesWithTimingList.get(r2);
        if (r7 < r3.startTimeUs) goto L16;
        if (r7 >= r3.endTimeUs) goto L16;
        r0.add(r3);
    L16:
        if (r7 < r3.startTimeUs) goto L19;
        r2 = r2 + 1;
    L19:
        ImmutableList r6 = ImmutableList.sortedCopyOf(CUES_DISPLAY_PRIORITY_COMPARATOR, r0);
        ImmutableList.Builder r72 = ImmutableList.builder();
    L21:
        if (r1 >= r6.size()) goto L24;
        r72.addAll(((CuesWithTiming) r6.get(r1)).cues);
        r1 = r1 + 1;
        goto L21
    L24:
        return r72.build();
    L26:
        return ImmutableList.of();
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public long getNextCueChangeTimeUs(long r11) {
        int r2 = 0;
        long r3 = -9223372036854775807L;
    L4:
        if (r2 >= this.cuesWithTimingList.size()) goto L19;
        long r5 = this.cuesWithTimingList.get(r2).startTimeUs;
        long r7 = this.cuesWithTimingList.get(r2).endTimeUs;
        if (r11 < r5) goto L8;
        if (r11 >= r7) goto L17;
        if (r3 != (-9223372036854775807L)) goto L16;
        r3 = r7;
        goto L17
    L16:
        r3 = Math.min(r3, r7);
    L17:
        r2 = r2 + 1;
        goto L4
    L8:
        if (r3 != (-9223372036854775807L)) goto L10;
        r3 = r5;
        goto L19
    L10:
        r3 = Math.min(r3, r5);
    L19:
        if (r3 == (-9223372036854775807L)) goto L21;
        return r3;
    L21:
        return Long.MIN_VALUE;
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public long getPreviousCueChangeTimeUs(long r9) {
        if (this.cuesWithTimingList.isEmpty() == true) goto L18;
        int r1 = 0;
        if (r9 < this.cuesWithTimingList.get(0).startTimeUs) goto L24;
        long r2 = this.cuesWithTimingList.get(0).startTimeUs;
    L9:
        if (r1 >= this.cuesWithTimingList.size()) goto L17;
        long r4 = this.cuesWithTimingList.get(r1).startTimeUs;
        long r6 = this.cuesWithTimingList.get(r1).endTimeUs;
        if (r6 > r9) goto L14;
        r2 = Math.max(r2, r6);
    L16:
        r1 = r1 + 1;
        goto L9
    L14:
        if (r4 > r9) goto L17;
        r2 = Math.max(r2, r4);
    L17:
        return r2;
    L24:
        return -9223372036854775807L;
    L18:
        return -9223372036854775807L;
    }
}
