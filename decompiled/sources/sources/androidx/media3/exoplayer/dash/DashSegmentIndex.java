package androidx.media3.exoplayer.dash;

import androidx.media3.exoplayer.dash.manifest.RangedUri;

/* loaded from: classes4.dex */
public interface DashSegmentIndex {
    long getAvailableSegmentCount(long r1, long r3);

    long getDurationUs(long r1, long r3);

    long getFirstAvailableSegmentNum(long r1, long r3);

    long getFirstSegmentNum();

    long getNextSegmentAvailableTimeUs(long r1, long r3);

    long getSegmentCount(long r1);

    long getSegmentNum(long r1, long r3);

    RangedUri getSegmentUrl(long r1);

    long getTimeUs(long r1);

    boolean isExplicit();
}
