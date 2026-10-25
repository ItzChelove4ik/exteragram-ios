package androidx.media3.exoplayer.dash.manifest;

import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public class Period {
    public final List<AdaptationSet> adaptationSets;
    public final Descriptor assetIdentifier;
    public final List<EventStream> eventStreams;
    public final String id;
    public final long startMs;

    public Period(String r8, long r9, List<AdaptationSet> r11, List<EventStream> r12) {
        this(r8, r9, r11, r12, null);
    }

    public Period(String r1, long r2, List<AdaptationSet> r4, List<EventStream> r5, Descriptor r6) {
        this.id = r1;
        this.startMs = r2;
        this.adaptationSets = Collections.unmodifiableList(r4);
        this.eventStreams = Collections.unmodifiableList(r5);
        this.assetIdentifier = r6;
    }

    public int getAdaptationSetIndex(int r4) {
        int r0 = this.adaptationSets.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        if (this.adaptationSets.get(r1).type == r4) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return r1;
    L8:
        return -1;
    }
}
