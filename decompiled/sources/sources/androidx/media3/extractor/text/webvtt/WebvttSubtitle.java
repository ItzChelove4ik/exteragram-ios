package androidx.media3.extractor.text.webvtt;

import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.text.Subtitle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
final class WebvttSubtitle implements Subtitle {
    private final List<WebvttCueInfo> cueInfos;
    private final long[] cueTimesUs;
    private final long[] sortedCueTimesUs;

    public static /* synthetic */ int $r8$lambda$tbPyxXap1_N3HtHEIrTXOvP8690(WebvttCueInfo r2, WebvttCueInfo r3) {
        return Long.compare(r2.startTimeUs, r3.startTimeUs);
    }

    public WebvttSubtitle(List<WebvttCueInfo> r7) {
        this.cueInfos = Collections.unmodifiableList(new ArrayList(r7));
        this.cueTimesUs = new long[r7.size() * 2];
        int r0 = 0;
    L4:
        if (r0 >= r7.size()) goto L6;
        WebvttCueInfo r1 = r7.get(r0);
        int r2 = r0 * 2;
        long[] r3 = this.cueTimesUs;
        r3[r2] = r1.startTimeUs;
        r3[r2 + 1] = r1.endTimeUs;
        r0 = r0 + 1;
        goto L4
    L6:
        long[] r72 = this.cueTimesUs;
        long[] r73 = Arrays.copyOf(r72, r72.length);
        this.sortedCueTimesUs = r73;
        Arrays.sort(r73);
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public List<Cue> getCues(long r9) {
        ArrayList r0 = new ArrayList();
        ArrayList r1 = new ArrayList();
        int r2 = 0;
        int r3 = 0;
    L4:
        if (r3 >= this.cueInfos.size()) goto L14;
        long[] r4 = this.cueTimesUs;
        int r5 = r3 * 2;
        if (r4[r5] > r9) goto L13;
        if (r9 >= r4[r5 + 1]) goto L13;
        WebvttCueInfo r42 = this.cueInfos.get(r3);
        Cue r52 = r42.cue;
        if (r52.line != (-3.4028235E38f)) goto L12;
        r1.add(r42);
        goto L13
    L12:
        r0.add(r52);
    L13:
        r3 = r3 + 1;
        goto L4
    L14:
        Collections.sort(r1, new WebvttSubtitle$$ExternalSyntheticLambda0());
    L16:
        if (r2 >= r1.size()) goto L18;
        r0.add(((WebvttCueInfo) r1.get(r2)).cue.buildUpon().setLine((-1) - r2, 1).build());
        r2 = r2 + 1;
        goto L16
    L18:
        return r0;
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public long getEventTime(int r4) {
        boolean r0 = false;
        if (r4 < 0) goto L5;
        boolean r2 = true;
    L6:
        Assertions.checkArgument(r2);
        if (r4 >= this.sortedCueTimesUs.length) goto L9;
        r0 = true;
    L9:
        Assertions.checkArgument(r0);
        return this.sortedCueTimesUs[r4];
    L5:
        r2 = false;
        goto L6
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public int getEventTimeCount() {
        return this.sortedCueTimesUs.length;
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public int getNextEventTimeIndex(long r3) {
        int r32 = Util.binarySearchCeil(this.sortedCueTimesUs, r3, false, false);
        if (r32 >= this.sortedCueTimesUs.length) goto L5;
        return r32;
    L5:
        return -1;
    }
}
