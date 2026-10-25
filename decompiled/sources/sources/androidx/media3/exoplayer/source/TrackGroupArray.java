package androidx.media3.exoplayer.source;

import android.os.Bundle;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class TrackGroupArray {
    public static final TrackGroupArray EMPTY = null;
    private static final String FIELD_TRACK_GROUPS = null;
    private int hashCode;
    public final int length;
    private final ImmutableList<TrackGroup> trackGroups;

    /* renamed from: $r8$lambda$f8FYboB6iZsjJlZ-DQcijUsRv2o, reason: not valid java name */
    public static /* synthetic */ Integer m842$r8$lambda$f8FYboB6iZsjJlZDQcijUsRv2o(TrackGroup r0) {
        return Integer.valueOf(r0.type);
    }

    static {
        EMPTY = new TrackGroupArray(new TrackGroup[0]);
        FIELD_TRACK_GROUPS = Util.intToStringMaxRadix(0);
    }

    public TrackGroupArray(TrackGroup... r2) {
        this.trackGroups = ImmutableList.copyOf(r2);
        this.length = r2.length;
        verifyCorrectness();
    }

    private void verifyCorrectness() {
        int r0 = 0;
    L4:
        if (r0 >= this.trackGroups.size()) goto L13;
        int r1 = r0 + 1;
        int r2 = r1;
    L7:
        if (r2 >= this.trackGroups.size()) goto L12;
        if (this.trackGroups.get(r0).equals(this.trackGroups.get(r2)) == false) goto L11;
        Log.e("TrackGroupArray", _UrlKt.FRAGMENT_ENCODE_SET, new IllegalArgumentException("Multiple identical TrackGroups added to one TrackGroupArray."));
    L11:
        r2 = r2 + 1;
        goto L7
    L12:
        r0 = r1;
        goto L4
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L15:
        return false;
    L8:
        if (TrackGroupArray.class != r5.getClass()) goto L15;
        TrackGroupArray r52 = (TrackGroupArray) r5;
        if (this.length != r52.length) goto L15;
        if (this.trackGroups.equals(r52.trackGroups) == false) goto L15;
        return true;
    }

    public TrackGroup get(int r1) {
        return this.trackGroups.get(r1);
    }

    public ImmutableList<Integer> getTrackTypes() {
        return ImmutableList.copyOf(Lists.transform(this.trackGroups, new TrackGroupArray$$ExternalSyntheticLambda0()));
    }

    public int hashCode() {
        if (this.hashCode != 0) goto L6;
        this.hashCode = this.trackGroups.hashCode();
    L6:
        return this.hashCode;
    }

    public int indexOf(TrackGroup r1) {
        int r0 = this.trackGroups.indexOf(r1);
        if (r0 < 0) goto L5;
        return r0;
    L5:
        return -1;
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        r0.putParcelableArrayList(FIELD_TRACK_GROUPS, BundleCollectionUtil.toBundleArrayList(this.trackGroups, new TrackGroupArray$$ExternalSyntheticLambda1()));
        return r0;
    }

    public String toString() {
        return this.trackGroups.toString();
    }
}
