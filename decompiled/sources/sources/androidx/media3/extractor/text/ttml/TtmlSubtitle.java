package androidx.media3.extractor.text.ttml;

import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.text.Subtitle;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
final class TtmlSubtitle implements Subtitle {
    private final long[] eventTimesUs;
    private final Map<String, TtmlStyle> globalStyles;
    private final Map<String, String> imageMap;
    private final Map<String, TtmlRegion> regionMap;
    private final TtmlNode root;

    public TtmlSubtitle(TtmlNode r1, Map<String, TtmlStyle> r2, Map<String, TtmlRegion> r3, Map<String, String> r4) {
        this.root = r1;
        this.regionMap = r3;
        this.imageMap = r4;
        if (r2 == null) goto L5;
        Map<String, TtmlStyle> r22 = Collections.unmodifiableMap(r2);
    L6:
        this.globalStyles = r22;
        this.eventTimesUs = r1.getEventTimesUs();
        return;
    L5:
        r22 = Collections.EMPTY_MAP;
        goto L6
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public List<Cue> getCues(long r7) {
        return this.root.getCues(r7, this.globalStyles, this.regionMap, this.imageMap);
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public long getEventTime(int r3) {
        return this.eventTimesUs[r3];
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public int getEventTimeCount() {
        return this.eventTimesUs.length;
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public int getNextEventTimeIndex(long r3) {
        int r32 = Util.binarySearchCeil(this.eventTimesUs, r3, false, false);
        if (r32 >= this.eventTimesUs.length) goto L5;
        return r32;
    L5:
        return -1;
    }
}
