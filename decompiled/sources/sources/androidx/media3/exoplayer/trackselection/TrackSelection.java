package androidx.media3.exoplayer.trackselection;

import androidx.media3.common.Format;
import androidx.media3.common.TrackGroup;

/* loaded from: classes4.dex */
public interface TrackSelection {
    Format getFormat(int r1);

    int getIndexInTrackGroup(int r1);

    TrackGroup getTrackGroup();

    int indexOf(int r1);

    int indexOf(Format r1);

    int length();
}
