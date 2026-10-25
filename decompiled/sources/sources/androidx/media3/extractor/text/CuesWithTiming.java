package androidx.media3.extractor.text;

import androidx.media3.common.text.Cue;
import com.google.common.collect.ImmutableList;
import java.util.List;

/* loaded from: classes4.dex */
public class CuesWithTiming {
    public final ImmutableList<Cue> cues;
    public final long durationUs;
    public final long endTimeUs;
    public final long startTimeUs;

    public CuesWithTiming(List<Cue> r3, long r4, long r6) {
        this.cues = ImmutableList.copyOf(r3);
        this.startTimeUs = r4;
        this.durationUs = r6;
        long r0 = -9223372036854775807L;
        if (r4 != (-9223372036854775807L)) goto L5;
    L8:
        this.endTimeUs = r0;
        return;
    L5:
        if (r6 == (-9223372036854775807L)) goto L8;
        r0 = r4 + r6;
        goto L8
    }
}
