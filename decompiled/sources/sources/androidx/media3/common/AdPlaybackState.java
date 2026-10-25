package androidx.media3.common;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class AdPlaybackState {
    private static final String FIELD_AD_GROUPS = null;
    private static final String FIELD_AD_RESUME_POSITION_US = null;
    private static final String FIELD_CONTENT_DURATION_US = null;
    private static final String FIELD_REMOVED_AD_GROUP_COUNT = null;
    public static final AdPlaybackState NONE = null;
    private static final AdGroup REMOVED_AD_GROUP = null;
    public final int adGroupCount;
    private final AdGroup[] adGroups;
    public final long adResumePositionUs;
    public final Object adsId;
    public final long contentDurationUs;
    public final int removedAdGroupCount;

    public static final class AdGroup {
        private static final String FIELD_CONTENT_RESUME_OFFSET_US = null;
        private static final String FIELD_COUNT = null;
        private static final String FIELD_DURATIONS_US = null;
        static final String FIELD_IDS = null;
        static final String FIELD_IS_PLACEHOLDER = null;
        private static final String FIELD_IS_SERVER_SIDE_INSERTED = null;
        static final String FIELD_MEDIA_ITEMS = null;
        private static final String FIELD_ORIGINAL_COUNT = null;
        private static final String FIELD_STATES = null;
        private static final String FIELD_TIME_US = null;
        private static final String FIELD_URIS = null;
        public final long contentResumeOffsetUs;
        public final int count;
        public final long[] durationsUs;
        public final String[] ids;
        public final boolean isPlaceholder;
        public final boolean isServerSideInserted;
        public final MediaItem[] mediaItems;
        public final int originalCount;
        public final int[] states;
        public final long timeUs;

        @Deprecated
        public final Uri[] uris;

        static {
            FIELD_TIME_US = Util.intToStringMaxRadix(0);
            FIELD_COUNT = Util.intToStringMaxRadix(1);
            FIELD_URIS = Util.intToStringMaxRadix(2);
            FIELD_STATES = Util.intToStringMaxRadix(3);
            FIELD_DURATIONS_US = Util.intToStringMaxRadix(4);
            FIELD_CONTENT_RESUME_OFFSET_US = Util.intToStringMaxRadix(5);
            FIELD_IS_SERVER_SIDE_INSERTED = Util.intToStringMaxRadix(6);
            FIELD_ORIGINAL_COUNT = Util.intToStringMaxRadix(7);
            FIELD_MEDIA_ITEMS = Util.intToStringMaxRadix(8);
            FIELD_IDS = Util.intToStringMaxRadix(9);
            FIELD_IS_PLACEHOLDER = Util.intToStringMaxRadix(10);
        }

        public AdGroup(long r15) {
            this(r15, -1, -1, new int[0], new MediaItem[0], new long[0], 0, false, new String[0], false);
        }

        private AdGroup(long r4, int r6, int r7, int[] r8, MediaItem[] r9, long[] r10, long r11, boolean r13, String[] r14, boolean r15) {
            int r2 = 0;
            if (r8.length != r9.length) goto L5;
            boolean r0 = true;
        L6:
            Assertions.checkArgument(r0);
            this.timeUs = r4;
            this.count = r6;
            this.originalCount = r7;
            this.states = r8;
            this.mediaItems = r9;
            this.durationsUs = r10;
            this.contentResumeOffsetUs = r11;
            this.isServerSideInserted = r13;
            this.uris = new Uri[r9.length];
        L7:
            Uri[] r42 = this.uris;
            if (r2 >= r42.length) goto L14;
            MediaItem r5 = r9[r2];
            if (r5 != null) goto L12;
            Uri r52 = null;
        L13:
            r42[r2] = r52;
            r2 = r2 + 1;
            goto L7
        L12:
            r52 = ((MediaItem.LocalConfiguration) Assertions.checkNotNull(r5.localConfiguration)).uri;
            goto L13
        L14:
            this.ids = r14;
            this.isPlaceholder = r15;
            return;
        L5:
            r0 = false;
            goto L6
        }

        private static long[] copyDurationsUsWithSpaceForAdCount(long[] r3, int r4) {
            int r0 = r3.length;
            int r42 = Math.max(r4, r0);
            long[] r32 = Arrays.copyOf(r3, r42);
            Arrays.fill(r32, r0, r42, -9223372036854775807L);
            return r32;
        }

        private static int[] copyStatesWithSpaceForAdCount(int[] r2, int r3) {
            int r0 = r2.length;
            int r32 = Math.max(r3, r0);
            int[] r22 = Arrays.copyOf(r2, r32);
            Arrays.fill(r22, r0, r32, 0);
            return r22;
        }

        private ArrayList<Bundle> getMediaItemsArrayBundles() {
            ArrayList<Bundle> r0 = new ArrayList();
            MediaItem[] r4 = this.mediaItems;
            int r1 = r4.length;
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L9;
            MediaItem r3 = r4[r2];
            if (r3 != null) goto L7;
            Bundle r32 = null;
        L8:
            r0.add(r32);
            r2 = r2 + 1;
            goto L3
        L7:
            r32 = r3.toBundleIncludeLocalConfiguration();
            goto L8
        L9:
            return r0;
        }

        public boolean equals(Object r7) {
            if (this != r7) goto L6;
            return true;
        L6:
            if (r7 != null) goto L8;
        L31:
            return false;
        L8:
            if (AdGroup.class != r7.getClass()) goto L31;
            AdGroup r72 = (AdGroup) r7;
            if (this.timeUs != r72.timeUs) goto L31;
            if (this.count != r72.count) goto L31;
            if (this.originalCount != r72.originalCount) goto L31;
            if (Arrays.equals(this.mediaItems, r72.mediaItems) == false) goto L31;
            if (Arrays.equals(this.states, r72.states) == false) goto L31;
            if (Arrays.equals(this.durationsUs, r72.durationsUs) == false) goto L31;
            if (this.contentResumeOffsetUs != r72.contentResumeOffsetUs) goto L31;
            if (this.isServerSideInserted != r72.isServerSideInserted) goto L31;
            if (Arrays.equals(this.ids, r72.ids) == false) goto L31;
            if (this.isPlaceholder != r72.isPlaceholder) goto L31;
            return true;
        }

        public int getFirstAdIndexToPlay() {
            return getNextAdIndexToPlay(-1);
        }

        public int getNextAdIndexToPlay(int r4) {
            int r42 = r4 + 1;
        L3:
            int[] r1 = this.states;
            if (r42 >= r1.length) goto L12;
            if (this.isServerSideInserted == true) goto L12;
            int r12 = r1[r42];
            if (r12 == 0) goto L12;
            if (r12 == 1) goto L12;
            r42 = r42 + 1;
        L12:
            return r42;
        }

        public boolean hasUnplayedAds() {
            if (this.count != (-1)) goto L5;
            return true;
        L5:
            int r1 = 0;
        L7:
            if (r1 >= this.count) goto L14;
            int r3 = this.states[r1];
            if (r3 == 0) goto L13;
            if (r3 == 1) goto L13;
            r1 = r1 + 1;
        L13:
            return true;
        L14:
            return false;
        }

        public int hashCode() {
            int r0 = ((this.count * 31) + this.originalCount) * 31;
            long r1 = this.timeUs;
            int r02 = (((((((r0 + ((int) (r1 ^ (r1 >>> 32)))) * 31) + Arrays.hashCode(this.mediaItems)) * 31) + Arrays.hashCode(this.states)) * 31) + Arrays.hashCode(this.durationsUs)) * 31;
            long r12 = this.contentResumeOffsetUs;
            return ((((((r02 + ((int) (r12 ^ (r12 >>> 32)))) * 31) + (this.isServerSideInserted ? 1 : 0)) * 31) + Arrays.hashCode(this.ids)) * 31) + (this.isPlaceholder ? 1 : 0);
        }

        public boolean isLivePostrollPlaceholder() {
            if (this.isPlaceholder == true) goto L5;
            return false;
        L5:
            if (this.timeUs == Long.MIN_VALUE) goto L7;
            return false;
        L7:
            if (this.count != (-1)) goto L13;
            return true;
        L13:
            return false;
        }

        public boolean shouldPlayAdGroup() {
            if (this.count != (-1)) goto L5;
            return true;
        L5:
            if (getFirstAdIndexToPlay() < this.count) goto L11;
            return false;
        L11:
            return true;
        }

        public Bundle toBundle() {
            Bundle r0 = new Bundle();
            r0.putLong(FIELD_TIME_US, this.timeUs);
            r0.putInt(FIELD_COUNT, this.count);
            r0.putInt(FIELD_ORIGINAL_COUNT, this.originalCount);
            r0.putParcelableArrayList(FIELD_URIS, new ArrayList(Arrays.asList(this.uris)));
            r0.putParcelableArrayList(FIELD_MEDIA_ITEMS, getMediaItemsArrayBundles());
            r0.putIntArray(FIELD_STATES, this.states);
            r0.putLongArray(FIELD_DURATIONS_US, this.durationsUs);
            r0.putLong(FIELD_CONTENT_RESUME_OFFSET_US, this.contentResumeOffsetUs);
            r0.putBoolean(FIELD_IS_SERVER_SIDE_INSERTED, this.isServerSideInserted);
            r0.putStringArrayList(FIELD_IDS, new ArrayList(Arrays.asList(this.ids)));
            r0.putBoolean(FIELD_IS_PLACEHOLDER, this.isPlaceholder);
            return r0;
        }

        public AdGroup withAdCount(int r15) {
            int[] r6 = copyStatesWithSpaceForAdCount(this.states, r15);
            long[] r8 = copyDurationsUsWithSpaceForAdCount(this.durationsUs, r15);
            MediaItem[] r7 = (MediaItem[]) Arrays.copyOf(this.mediaItems, r15);
            String[] r12 = (String[]) Arrays.copyOf(this.ids, r15);
            return new AdGroup(this.timeUs, r15, this.originalCount, r6, r7, r8, this.contentResumeOffsetUs, this.isServerSideInserted, r12, this.isPlaceholder);
        }
    }

    static {
        NONE = new AdPlaybackState(null, new AdGroup[0], 0, -9223372036854775807L, 0);
        REMOVED_AD_GROUP = new AdGroup(0).withAdCount(0);
        FIELD_AD_GROUPS = Util.intToStringMaxRadix(1);
        FIELD_AD_RESUME_POSITION_US = Util.intToStringMaxRadix(2);
        FIELD_CONTENT_DURATION_US = Util.intToStringMaxRadix(3);
        FIELD_REMOVED_AD_GROUP_COUNT = Util.intToStringMaxRadix(4);
    }

    private AdPlaybackState(Object r1, AdGroup[] r2, long r3, long r5, int r7) {
        this.adsId = r1;
        this.adResumePositionUs = r3;
        this.contentDurationUs = r5;
        this.adGroupCount = r2.length + r7;
        this.adGroups = r2;
        this.removedAdGroupCount = r7;
    }

    private boolean isPositionBeforeAdGroup(long r7, long r9, int r11) {
        if (r7 != Long.MIN_VALUE) goto L5;
        return false;
    L5:
        AdGroup r6 = getAdGroup(r11);
        long r4 = r6.timeUs;
        if (r4 != Long.MIN_VALUE) goto L17;
        if (r9 != (-9223372036854775807L)) goto L10;
    L15:
        return true;
    L10:
        if (r6.isLivePostrollPlaceholder() == true) goto L15;
        if (r7 < r9) goto L15;
        return false;
    L17:
        if (r7 >= r4) goto L19;
        return true;
    L19:
        return false;
    }

    public boolean equals(Object r7) {
        if (this != r7) goto L6;
        return true;
    L6:
        if (r7 != null) goto L8;
    L23:
        return false;
    L8:
        if (AdPlaybackState.class != r7.getClass()) goto L23;
        AdPlaybackState r72 = (AdPlaybackState) r7;
        if (Objects.equals(this.adsId, r72.adsId) == false) goto L23;
        if (this.adGroupCount != r72.adGroupCount) goto L23;
        if (this.adResumePositionUs != r72.adResumePositionUs) goto L23;
        if (this.contentDurationUs != r72.contentDurationUs) goto L23;
        if (this.removedAdGroupCount != r72.removedAdGroupCount) goto L23;
        if (Arrays.equals(this.adGroups, r72.adGroups) == false) goto L23;
        return true;
    }

    public AdGroup getAdGroup(int r2) {
        int r0 = this.removedAdGroupCount;
        if (r2 >= r0) goto L7;
        return REMOVED_AD_GROUP;
    L7:
        return this.adGroups[r2 - r0];
    }

    public int getAdGroupIndexAfterPositionUs(long r8, long r10) {
        if (r8 != Long.MIN_VALUE) goto L5;
    L25:
        return -1;
    L5:
        if (r10 != (-9223372036854775807L)) goto L7;
    L9:
        int r4 = this.removedAdGroupCount;
    L11:
        if (r4 >= this.adGroupCount) goto L20;
        if (getAdGroup(r4).timeUs == Long.MIN_VALUE) goto L17;
        if (getAdGroup(r4).timeUs > r8) goto L17;
    L18:
        r4 = r4 + 1;
    L17:
        if (getAdGroup(r4).shouldPlayAdGroup() == false) goto L18;
    L20:
        if (r4 >= this.adGroupCount) goto L25;
        if (r10 != (-9223372036854775807L)) goto L23;
    L24:
        return r4;
    L23:
        if (getAdGroup(r4).timeUs > r10) goto L25;
    L7:
        if (r8 < r10) goto L9;
        goto L9
    }

    public int getAdGroupIndexForPositionUs(long r8, long r10) {
        int r0 = this.adGroupCount - 1;
        int r6 = r0 - (isLivePostrollPlaceholder(r0) ? 1 : 0);
    L3:
        AdPlaybackState r1 = this;
        if (r6 < 0) goto L8;
        long r2 = r8;
        long r4 = r10;
        if (r1.isPositionBeforeAdGroup(r2, r4, r6) == false) goto L8;
        r6 = r6 - 1;
        AdPlaybackState this = r1;
        r8 = r2;
        r10 = r4;
    L8:
        if (r6 >= 0) goto L10;
        return -1;
    L10:
        if (r1.getAdGroup(r6).hasUnplayedAds() == false) goto L16;
        return r6;
    L16:
        return -1;
    }

    public int hashCode() {
        int r0 = this.adGroupCount * 31;
        Object r1 = this.adsId;
        if (r1 != null) goto L5;
        int r12 = 0;
    L7:
        return ((((((((r0 + r12) * 31) + ((int) this.adResumePositionUs)) * 31) + ((int) this.contentDurationUs)) * 31) + this.removedAdGroupCount) * 31) + Arrays.hashCode(this.adGroups);
    L5:
        r12 = r1.hashCode();
        goto L7
    }

    public boolean isLivePostrollPlaceholder(int r3) {
        if (r3 == (this.adGroupCount - 1)) goto L5;
        return false;
    L5:
        if (getAdGroup(r3).isLivePostrollPlaceholder() == false) goto L9;
        return true;
    L9:
        return false;
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        ArrayList<? extends Parcelable> r1 = new ArrayList();
        AdGroup[] r2 = this.adGroups;
        int r3 = r2.length;
        int r4 = 0;
    L3:
        if (r4 >= r3) goto L6;
        r1.add(r2[r4].toBundle());
        r4 = r4 + 1;
        goto L3
    L6:
        if (r1.isEmpty() == true) goto L8;
        r0.putParcelableArrayList(FIELD_AD_GROUPS, r1);
    L8:
        long r12 = this.adResumePositionUs;
        AdPlaybackState r32 = NONE;
        if (r12 == r32.adResumePositionUs) goto L11;
        r0.putLong(FIELD_AD_RESUME_POSITION_US, r12);
    L11:
        long r13 = this.contentDurationUs;
        if (r13 == r32.contentDurationUs) goto L14;
        r0.putLong(FIELD_CONTENT_DURATION_US, r13);
    L14:
        int r6 = this.removedAdGroupCount;
        if (r6 == r32.removedAdGroupCount) goto L17;
        r0.putInt(FIELD_REMOVED_AD_GROUP_COUNT, r6);
    L17:
        return r0;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("AdPlaybackState(adsId=");
        r0.append(this.adsId);
        r0.append(", adResumePositionUs=");
        r0.append(this.adResumePositionUs);
        r0.append(", adGroups=[");
        int r2 = 0;
    L4:
        if (r2 >= this.adGroups.length) goto L31;
        r0.append("adGroup(timeUs=");
        r0.append(this.adGroups[r2].timeUs);
        r0.append(", ads=[");
        int r3 = 0;
    L7:
        if (r3 >= this.adGroups[r2].states.length) goto L27;
        r0.append("ad(state=");
        int r5 = this.adGroups[r2].states[r3];
        if (r5 == 0) goto L22;
        if (r5 != 1) goto L12;
        r0.append('R');
    L23:
        r0.append(", durationUs=");
        r0.append(this.adGroups[r2].durationsUs[r3]);
        r0.append(')');
        if (r3 >= (this.adGroups[r2].states.length - 1)) goto L26;
        r0.append(", ");
    L26:
        r3 = r3 + 1;
        goto L7
    L12:
        if (r5 != 2) goto L14;
        r0.append('S');
        goto L23
    L14:
        if (r5 != 3) goto L16;
        r0.append('P');
        goto L23
    L16:
        if (r5 == 4) goto L18;
        r0.append('?');
        goto L23
    L18:
        r0.append('!');
        goto L23
    L22:
        r0.append('_');
        goto L23
    L27:
        r0.append("])");
        if (r2 >= (this.adGroups.length - 1)) goto L30;
        r0.append(", ");
    L30:
        r2 = r2 + 1;
        goto L4
    L31:
        r0.append("])");
        return r0.toString();
    }
}
