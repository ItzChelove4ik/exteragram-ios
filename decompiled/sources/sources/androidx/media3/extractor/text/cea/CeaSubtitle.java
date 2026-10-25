package androidx.media3.extractor.text.cea;

import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.extractor.text.Subtitle;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
final class CeaSubtitle implements Subtitle {
    private final List<Cue> cues;

    public CeaSubtitle(List<Cue> r1) {
        this.cues = r1;
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public List<Cue> getCues(long r3) {
        if (r3 < 0) goto L7;
        return this.cues;
    L7:
        return Collections.EMPTY_LIST;
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public long getEventTime(int r1) {
        if (r1 != 0) goto L4;
        boolean r0 = true;
    L5:
        Assertions.checkArgument(r0);
        return 0;
    L4:
        r0 = false;
        goto L5
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public int getEventTimeCount() {
        return 1;
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public int getNextEventTimeIndex(long r3) {
        if (r3 >= 0) goto L6;
        return 0;
    L6:
        return -1;
    }
}
