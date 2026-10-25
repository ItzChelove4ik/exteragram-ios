package androidx.media3.exoplayer.text;

import androidx.media3.common.text.Cue;
import androidx.media3.extractor.text.CuesWithTiming;
import com.google.common.collect.ImmutableList;

/* loaded from: classes4.dex */
interface CuesResolver {
    boolean addCues(CuesWithTiming r1, long r2);

    void clear();

    void discardCuesBeforeTimeUs(long r1);

    ImmutableList<Cue> getCuesAtTimeUs(long r1);

    long getNextCueChangeTimeUs(long r1);

    long getPreviousCueChangeTimeUs(long r1);
}
