package androidx.media3.common;

import android.os.Bundle;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Ints;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class TrackSelectionOverride {
    private static final String FIELD_TRACKS = null;
    private static final String FIELD_TRACK_GROUP = null;
    public final TrackGroup mediaTrackGroup;
    public final ImmutableList<Integer> trackIndices;

    static {
        FIELD_TRACK_GROUP = Util.intToStringMaxRadix(0);
        FIELD_TRACKS = Util.intToStringMaxRadix(1);
    }

    public TrackSelectionOverride(TrackGroup r1, int r2) {
        this(r1, ImmutableList.of(Integer.valueOf(r2)));
    }

    public TrackSelectionOverride(TrackGroup r3, List<Integer> r4) {
        if (r4.isEmpty() == false) goto L5;
    L11:
        this.mediaTrackGroup = r3;
        this.trackIndices = ImmutableList.copyOf(r4);
        return;
    L5:
        if (((Integer) Collections.min(r4)).intValue() < 0) goto L10;
        if (((Integer) Collections.max(r4)).intValue() < r3.length) goto L11;
    L10:
        throw new IndexOutOfBoundsException();
    }

    public static TrackSelectionOverride fromBundle(Bundle r2) {
        return new TrackSelectionOverride(TrackGroup.fromBundle((Bundle) Assertions.checkNotNull(r2.getBundle(FIELD_TRACK_GROUP))), Ints.asList((int[]) Assertions.checkNotNull(r2.getIntArray(FIELD_TRACKS))));
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L15:
        return false;
    L8:
        if (TrackSelectionOverride.class != r5.getClass()) goto L15;
        TrackSelectionOverride r52 = (TrackSelectionOverride) r5;
        if (this.mediaTrackGroup.equals(r52.mediaTrackGroup) == false) goto L15;
        if (this.trackIndices.equals(r52.trackIndices) == false) goto L15;
        return true;
    }

    public int getType() {
        return this.mediaTrackGroup.type;
    }

    public int hashCode() {
        return this.mediaTrackGroup.hashCode() + (this.trackIndices.hashCode() * 31);
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        r0.putBundle(FIELD_TRACK_GROUP, this.mediaTrackGroup.toBundle());
        r0.putIntArray(FIELD_TRACKS, Ints.toArray(this.trackIndices));
        return r0;
    }
}
