package androidx.media3.common;

import android.os.Bundle;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Booleans;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes3.dex */
public final class Tracks {
    public static final Tracks EMPTY = null;
    private static final String FIELD_TRACK_GROUPS = null;
    private final ImmutableList<Group> groups;

    public static final class Group {
        private static final String FIELD_ADAPTIVE_SUPPORTED = null;
        private static final String FIELD_TRACK_GROUP = null;
        private static final String FIELD_TRACK_SELECTED = null;
        private static final String FIELD_TRACK_SUPPORT = null;
        private final boolean adaptiveSupported;
        public final int length;
        private final TrackGroup mediaTrackGroup;
        private final boolean[] trackSelected;
        private final int[] trackSupport;

        static {
            FIELD_TRACK_GROUP = Util.intToStringMaxRadix(0);
            FIELD_TRACK_SUPPORT = Util.intToStringMaxRadix(1);
            FIELD_TRACK_SELECTED = Util.intToStringMaxRadix(3);
            FIELD_ADAPTIVE_SUPPORTED = Util.intToStringMaxRadix(4);
        }

        public Group(TrackGroup r5, boolean r6, int[] r7, boolean[] r8) {
            int r0 = r5.length;
            this.length = r0;
            boolean r2 = false;
            if (r0 == r7.length) goto L5;
        L7:
            boolean r1 = false;
        L8:
            Assertions.checkArgument(r1);
            this.mediaTrackGroup = r5;
            if (r6 == false) goto L12;
            if (r0 <= 1) goto L12;
            r2 = true;
        L12:
            this.adaptiveSupported = r2;
            this.trackSupport = (int[]) r7.clone();
            this.trackSelected = (boolean[]) r8.clone();
            return;
        L5:
            if (r0 != r8.length) goto L7;
            r1 = true;
            goto L8
        }

        public Group copyWithId(String r4) {
            return new Group(this.mediaTrackGroup.copyWithId(r4), this.adaptiveSupported, this.trackSupport, this.trackSelected);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L19:
            return false;
        L8:
            if (Group.class != r5.getClass()) goto L19;
            Group r52 = (Group) r5;
            if (this.adaptiveSupported != r52.adaptiveSupported) goto L19;
            if (this.mediaTrackGroup.equals(r52.mediaTrackGroup) == false) goto L19;
            if (Arrays.equals(this.trackSupport, r52.trackSupport) == false) goto L19;
            if (Arrays.equals(this.trackSelected, r52.trackSelected) == false) goto L19;
            return true;
        }

        public TrackGroup getMediaTrackGroup() {
            return this.mediaTrackGroup;
        }

        public Format getTrackFormat(int r1) {
            return this.mediaTrackGroup.getFormat(r1);
        }

        public int getType() {
            return this.mediaTrackGroup.type;
        }

        public int hashCode() {
            return (((((this.mediaTrackGroup.hashCode() * 31) + (this.adaptiveSupported ? 1 : 0)) * 31) + Arrays.hashCode(this.trackSupport)) * 31) + Arrays.hashCode(this.trackSelected);
        }

        public boolean isSelected() {
            return Booleans.contains(this.trackSelected, true);
        }

        public boolean isTrackSelected(int r1) {
            return this.trackSelected[r1];
        }

        public Bundle toBundle() {
            Bundle r0 = new Bundle();
            r0.putBundle(FIELD_TRACK_GROUP, this.mediaTrackGroup.toBundle());
            r0.putIntArray(FIELD_TRACK_SUPPORT, this.trackSupport);
            r0.putBooleanArray(FIELD_TRACK_SELECTED, this.trackSelected);
            r0.putBoolean(FIELD_ADAPTIVE_SUPPORTED, this.adaptiveSupported);
            return r0;
        }
    }

    static {
        EMPTY = new Tracks(ImmutableList.of());
        FIELD_TRACK_GROUPS = Util.intToStringMaxRadix(0);
    }

    public Tracks(List<Group> r1) {
        this.groups = ImmutableList.copyOf(r1);
    }

    public boolean equals(Object r3) {
        if (this != r3) goto L5;
        return true;
    L5:
        if (r3 != null) goto L7;
        return false;
    L7:
        if (Tracks.class == r3.getClass()) goto L10;
        return false;
    L10:
        return this.groups.equals(((Tracks) r3).groups);
    }

    public ImmutableList<Group> getGroups() {
        return this.groups;
    }

    public int hashCode() {
        return this.groups.hashCode();
    }

    public boolean isTypeSelected(int r5) {
        int r1 = 0;
    L4:
        if (r1 >= this.groups.size()) goto L12;
        Group r2 = this.groups.get(r1);
        if (r2.isSelected() == false) goto L11;
        if (r2.getType() != r5) goto L11;
        return true;
    L11:
        r1 = r1 + 1;
        goto L4
    L12:
        return false;
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        r0.putParcelableArrayList(FIELD_TRACK_GROUPS, BundleCollectionUtil.toBundleArrayList(this.groups, new Tracks$$ExternalSyntheticLambda0()));
        return r0;
    }
}
