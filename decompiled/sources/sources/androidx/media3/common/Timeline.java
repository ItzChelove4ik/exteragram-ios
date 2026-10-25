package androidx.media3.common;

import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.android.dx.io.Opcodes;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Objects;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public abstract class Timeline {
    public static final Timeline EMPTY = null;
    private static final String FIELD_PERIODS = null;
    private static final String FIELD_SHUFFLED_WINDOW_INDICES = null;
    private static final String FIELD_WINDOWS = null;

    /* renamed from: androidx.media3.common.Timeline$1, reason: invalid class name */
    public class AnonymousClass1 extends Timeline {
        public AnonymousClass1() {
        }

        @Override // androidx.media3.common.Timeline
        public int getIndexOfPeriod(Object r1) {
            return -1;
        }

        @Override // androidx.media3.common.Timeline
        public Period getPeriod(int r1, Period r2, boolean r3) {
            throw new IndexOutOfBoundsException();
        }

        @Override // androidx.media3.common.Timeline
        public int getPeriodCount() {
            return 0;
        }

        @Override // androidx.media3.common.Timeline
        public Object getUidOfPeriod(int r1) {
            throw new IndexOutOfBoundsException();
        }

        @Override // androidx.media3.common.Timeline
        public Window getWindow(int r1, Window r2, long r3) {
            throw new IndexOutOfBoundsException();
        }

        @Override // androidx.media3.common.Timeline
        public int getWindowCount() {
            return 0;
        }
    }

    public static final class Period {
        private static final String FIELD_AD_PLAYBACK_STATE = null;
        private static final String FIELD_DURATION_US = null;
        private static final String FIELD_PLACEHOLDER = null;
        private static final String FIELD_POSITION_IN_WINDOW_US = null;
        private static final String FIELD_WINDOW_INDEX = null;
        public AdPlaybackState adPlaybackState;
        public long durationUs;
        public Object id;
        public boolean isPlaceholder;
        public long positionInWindowUs;
        public Object uid;
        public int windowIndex;

        static {
            FIELD_WINDOW_INDEX = Util.intToStringMaxRadix(0);
            FIELD_DURATION_US = Util.intToStringMaxRadix(1);
            FIELD_POSITION_IN_WINDOW_US = Util.intToStringMaxRadix(2);
            FIELD_PLACEHOLDER = Util.intToStringMaxRadix(3);
            FIELD_AD_PLAYBACK_STATE = Util.intToStringMaxRadix(4);
        }

        public Period() {
            this.adPlaybackState = AdPlaybackState.NONE;
        }

        public boolean equals(Object r7) {
            if (this != r7) goto L6;
            return true;
        L6:
            if (r7 != null) goto L8;
        L25:
            return false;
        L8:
            if (Period.class.equals(r7.getClass()) == false) goto L25;
            Period r72 = (Period) r7;
            if (Objects.equals(this.id, r72.id) == false) goto L25;
            if (Objects.equals(this.uid, r72.uid) == false) goto L25;
            if (this.windowIndex != r72.windowIndex) goto L25;
            if (this.durationUs != r72.durationUs) goto L25;
            if (this.positionInWindowUs != r72.positionInWindowUs) goto L25;
            if (this.isPlaceholder != r72.isPlaceholder) goto L25;
            if (Objects.equals(this.adPlaybackState, r72.adPlaybackState) == false) goto L25;
            return true;
        }

        public int getAdCountInAdGroup(int r1) {
            return this.adPlaybackState.getAdGroup(r1).count;
        }

        public long getAdDurationUs(int r2, int r3) {
            AdPlaybackState.AdGroup r1 = this.adPlaybackState.getAdGroup(r2);
            if (r1.count != (-1)) goto L5;
            return -9223372036854775807L;
        L5:
            return r1.durationsUs[r3];
        }

        public int getAdGroupCount() {
            return this.adPlaybackState.adGroupCount;
        }

        public int getAdGroupIndexAfterPositionUs(long r4) {
            return this.adPlaybackState.getAdGroupIndexAfterPositionUs(r4, this.durationUs);
        }

        public int getAdGroupIndexForPositionUs(long r4) {
            return this.adPlaybackState.getAdGroupIndexForPositionUs(r4, this.durationUs);
        }

        public long getAdGroupTimeUs(int r1) {
            return this.adPlaybackState.getAdGroup(r1).timeUs;
        }

        public long getAdResumePositionUs() {
            return this.adPlaybackState.adResumePositionUs;
        }

        public int getAdState(int r2, int r3) {
            AdPlaybackState.AdGroup r1 = this.adPlaybackState.getAdGroup(r2);
            if (r1.count != (-1)) goto L5;
            return 0;
        L5:
            return r1.states[r3];
        }

        public long getContentResumeOffsetUs(int r1) {
            return this.adPlaybackState.getAdGroup(r1).contentResumeOffsetUs;
        }

        public long getDurationUs() {
            return this.durationUs;
        }

        public int getFirstAdIndexToPlay(int r1) {
            return this.adPlaybackState.getAdGroup(r1).getFirstAdIndexToPlay();
        }

        public int getNextAdIndexToPlay(int r1, int r2) {
            return this.adPlaybackState.getAdGroup(r1).getNextAdIndexToPlay(r2);
        }

        public long getPositionInWindowMs() {
            return Util.usToMs(this.positionInWindowUs);
        }

        public long getPositionInWindowUs() {
            return this.positionInWindowUs;
        }

        public int getRemovedAdGroupCount() {
            return this.adPlaybackState.removedAdGroupCount;
        }

        public boolean hasPlayedAdGroup(int r1) {
            return !this.adPlaybackState.getAdGroup(r1).hasUnplayedAds();
        }

        public int hashCode() {
            Object r0 = this.id;
            int r1 = 0;
            if (r0 != null) goto L5;
            int r02 = 0;
        L6:
            int r2 = (Opcodes.RSUB_INT_LIT8 + r02) * 31;
            Object r03 = this.uid;
            if (r03 == null) goto L10;
            r1 = r03.hashCode();
        L10:
            int r22 = (((r2 + r1) * 31) + this.windowIndex) * 31;
            long r04 = this.durationUs;
            int r23 = (r22 + ((int) (r04 ^ (r04 >>> 32)))) * 31;
            long r05 = this.positionInWindowUs;
            return ((((r23 + ((int) (r05 ^ (r05 >>> 32)))) * 31) + (this.isPlaceholder ? 1 : 0)) * 31) + this.adPlaybackState.hashCode();
        L5:
            r02 = r0.hashCode();
            goto L6
        }

        public boolean isLivePostrollPlaceholder(int r3) {
            if (r3 == (getAdGroupCount() - 1)) goto L5;
            return false;
        L5:
            if (this.adPlaybackState.isLivePostrollPlaceholder(r3) == false) goto L9;
            return true;
        L9:
            return false;
        }

        public boolean isServerSideInsertedAdGroup(int r1) {
            return this.adPlaybackState.getAdGroup(r1).isServerSideInserted;
        }

        public Period set(Object r11, Object r12, int r13, long r14, long r16) {
            return set(r11, r12, r13, r14, r16, AdPlaybackState.NONE, false);
        }

        public Period set(Object r1, Object r2, int r3, long r4, long r6, AdPlaybackState r8, boolean r9) {
            this.id = r1;
            this.uid = r2;
            this.windowIndex = r3;
            this.durationUs = r4;
            this.positionInWindowUs = r6;
            this.adPlaybackState = r8;
            this.isPlaceholder = r9;
            return this;
        }

        public Bundle toBundle() {
            Bundle r0 = new Bundle();
            int r1 = this.windowIndex;
            if (r1 == 0) goto L5;
            r0.putInt(FIELD_WINDOW_INDEX, r1);
        L5:
            long r12 = this.durationUs;
            if (r12 == (-9223372036854775807L)) goto L8;
            r0.putLong(FIELD_DURATION_US, r12);
        L8:
            long r13 = this.positionInWindowUs;
            if (r13 == 0) goto L11;
            r0.putLong(FIELD_POSITION_IN_WINDOW_US, r13);
        L11:
            boolean r14 = this.isPlaceholder;
            if (r14 == false) goto L15;
            r0.putBoolean(FIELD_PLACEHOLDER, r14);
        L15:
            if (this.adPlaybackState.equals(AdPlaybackState.NONE) == true) goto L17;
            r0.putBundle(FIELD_AD_PLAYBACK_STATE, this.adPlaybackState.toBundle());
        L17:
            return r0;
        }
    }

    public static final class RemotableTimeline extends Timeline {
        private final ImmutableList<Period> periods;
        private final int[] shuffledWindowIndices;
        private final int[] windowIndicesInShuffled;
        private final ImmutableList<Window> windows;

        public RemotableTimeline(ImmutableList<Window> r4, ImmutableList<Period> r5, int[] r6) {
            int r2 = 0;
            if (r4.size() != r6.length) goto L5;
            boolean r0 = true;
        L6:
            Assertions.checkArgument(r0);
            this.windows = r4;
            this.periods = r5;
            this.shuffledWindowIndices = r6;
            this.windowIndicesInShuffled = new int[r6.length];
        L8:
            if (r2 >= r6.length) goto L10;
            this.windowIndicesInShuffled[r6[r2]] = r2;
            r2 = r2 + 1;
            goto L8
        L10:
            return;
        L5:
            r0 = false;
            goto L6
        }

        @Override // androidx.media3.common.Timeline
        public int getFirstWindowIndex(boolean r2) {
            if (isEmpty() == false) goto L7;
            return -1;
        L7:
            if (r2 == true) goto L9;
            return 0;
        L9:
            return this.shuffledWindowIndices[0];
        }

        @Override // androidx.media3.common.Timeline
        public int getIndexOfPeriod(Object r1) {
            throw new UnsupportedOperationException();
        }

        @Override // androidx.media3.common.Timeline
        public int getLastWindowIndex(boolean r2) {
            if (isEmpty() == false) goto L6;
            return -1;
        L6:
            if (r2 == false) goto L10;
            return this.shuffledWindowIndices[getWindowCount() - 1];
        L10:
            return getWindowCount() - 1;
        }

        @Override // androidx.media3.common.Timeline
        public int getNextWindowIndex(int r3, int r4, boolean r5) {
            if (r4 != 1) goto L6;
            return r3;
        L6:
            if (r3 == getLastWindowIndex(r5)) goto L8;
            if (r5 == false) goto L17;
            return this.shuffledWindowIndices[this.windowIndicesInShuffled[r3] + 1];
        L17:
            return r3 + 1;
        L8:
            if (r4 == 2) goto L10;
            return -1;
        L10:
            return getFirstWindowIndex(r5);
        }

        @Override // androidx.media3.common.Timeline
        public Period getPeriod(int r11, Period r12, boolean r13) {
            Period r10 = this.periods.get(r11);
            r12.set(r10.id, r10.uid, r10.windowIndex, r10.durationUs, r10.positionInWindowUs, r10.adPlaybackState, r10.isPlaceholder);
            return r12;
        }

        @Override // androidx.media3.common.Timeline
        public int getPeriodCount() {
            return this.periods.size();
        }

        @Override // androidx.media3.common.Timeline
        public int getPreviousWindowIndex(int r3, int r4, boolean r5) {
            if (r4 != 1) goto L6;
            return r3;
        L6:
            if (r3 == getFirstWindowIndex(r5)) goto L8;
            if (r5 == false) goto L17;
            return this.shuffledWindowIndices[this.windowIndicesInShuffled[r3] - 1];
        L17:
            return r3 - 1;
        L8:
            if (r4 == 2) goto L10;
            return -1;
        L10:
            return getLastWindowIndex(r5);
        }

        @Override // androidx.media3.common.Timeline
        public Object getUidOfPeriod(int r1) {
            throw new UnsupportedOperationException();
        }

        @Override // androidx.media3.common.Timeline
        public Window getWindow(int r25, Window r26, long r27) {
            Window r0 = this.windows.get(r25);
            r26.set(r0.uid, r0.mediaItem, r0.manifest, r0.presentationStartTimeMs, r0.windowStartTimeMs, r0.elapsedRealtimeEpochOffsetMs, r0.isSeekable, r0.isDynamic, r0.liveConfiguration, r0.defaultPositionUs, r0.durationUs, r0.firstPeriodIndex, r0.lastPeriodIndex, r0.positionInFirstPeriodUs);
            r26.isPlaceholder = r0.isPlaceholder;
            return r26;
        }

        @Override // androidx.media3.common.Timeline
        public int getWindowCount() {
            return this.windows.size();
        }
    }

    public static final class Window {
        private static final Object FAKE_WINDOW_UID = null;
        private static final String FIELD_DEFAULT_POSITION_US = null;
        private static final String FIELD_DURATION_US = null;
        private static final String FIELD_ELAPSED_REALTIME_EPOCH_OFFSET_MS = null;
        private static final String FIELD_FIRST_PERIOD_INDEX = null;
        private static final String FIELD_IS_DYNAMIC = null;
        private static final String FIELD_IS_PLACEHOLDER = null;
        private static final String FIELD_IS_SEEKABLE = null;
        private static final String FIELD_LAST_PERIOD_INDEX = null;
        private static final String FIELD_LIVE_CONFIGURATION = null;
        private static final String FIELD_MEDIA_ITEM = null;
        private static final String FIELD_POSITION_IN_FIRST_PERIOD_US = null;
        private static final String FIELD_PRESENTATION_START_TIME_MS = null;
        private static final String FIELD_WINDOW_START_TIME_MS = null;
        private static final MediaItem PLACEHOLDER_MEDIA_ITEM = null;
        public static final Object SINGLE_WINDOW_UID = null;
        public long defaultPositionUs;
        public long durationUs;
        public long elapsedRealtimeEpochOffsetMs;
        public int firstPeriodIndex;
        public boolean isDynamic;
        public boolean isPlaceholder;
        public boolean isSeekable;
        public int lastPeriodIndex;
        public MediaItem.LiveConfiguration liveConfiguration;
        public Object manifest;
        public MediaItem mediaItem;
        public long positionInFirstPeriodUs;
        public long presentationStartTimeMs;

        @Deprecated
        public Object tag;
        public Object uid;
        public long windowStartTimeMs;

        static {
            SINGLE_WINDOW_UID = new Object();
            FAKE_WINDOW_UID = new Object();
            PLACEHOLDER_MEDIA_ITEM = new MediaItem.Builder().setMediaId("androidx.media3.common.Timeline").setUri(Uri.EMPTY).build();
            FIELD_MEDIA_ITEM = Util.intToStringMaxRadix(1);
            FIELD_PRESENTATION_START_TIME_MS = Util.intToStringMaxRadix(2);
            FIELD_WINDOW_START_TIME_MS = Util.intToStringMaxRadix(3);
            FIELD_ELAPSED_REALTIME_EPOCH_OFFSET_MS = Util.intToStringMaxRadix(4);
            FIELD_IS_SEEKABLE = Util.intToStringMaxRadix(5);
            FIELD_IS_DYNAMIC = Util.intToStringMaxRadix(6);
            FIELD_LIVE_CONFIGURATION = Util.intToStringMaxRadix(7);
            FIELD_IS_PLACEHOLDER = Util.intToStringMaxRadix(8);
            FIELD_DEFAULT_POSITION_US = Util.intToStringMaxRadix(9);
            FIELD_DURATION_US = Util.intToStringMaxRadix(10);
            FIELD_FIRST_PERIOD_INDEX = Util.intToStringMaxRadix(11);
            FIELD_LAST_PERIOD_INDEX = Util.intToStringMaxRadix(12);
            FIELD_POSITION_IN_FIRST_PERIOD_US = Util.intToStringMaxRadix(13);
        }

        public Window() {
            this.uid = SINGLE_WINDOW_UID;
            this.mediaItem = PLACEHOLDER_MEDIA_ITEM;
        }

        public boolean equals(Object r7) {
            if (this != r7) goto L6;
            return true;
        L6:
            if (r7 != null) goto L8;
        L41:
            return false;
        L8:
            if (Window.class.equals(r7.getClass()) == false) goto L41;
            Window r72 = (Window) r7;
            if (Objects.equals(this.uid, r72.uid) == false) goto L41;
            if (Objects.equals(this.mediaItem, r72.mediaItem) == false) goto L41;
            if (Objects.equals(this.manifest, r72.manifest) == false) goto L41;
            if (Objects.equals(this.liveConfiguration, r72.liveConfiguration) == false) goto L41;
            if (this.presentationStartTimeMs != r72.presentationStartTimeMs) goto L41;
            if (this.windowStartTimeMs != r72.windowStartTimeMs) goto L41;
            if (this.elapsedRealtimeEpochOffsetMs != r72.elapsedRealtimeEpochOffsetMs) goto L41;
            if (this.isSeekable != r72.isSeekable) goto L41;
            if (this.isDynamic != r72.isDynamic) goto L41;
            if (this.isPlaceholder != r72.isPlaceholder) goto L41;
            if (this.defaultPositionUs != r72.defaultPositionUs) goto L41;
            if (this.durationUs != r72.durationUs) goto L41;
            if (this.firstPeriodIndex != r72.firstPeriodIndex) goto L41;
            if (this.lastPeriodIndex != r72.lastPeriodIndex) goto L41;
            if (this.positionInFirstPeriodUs != r72.positionInFirstPeriodUs) goto L41;
            return true;
        }

        public long getCurrentUnixTimeMs() {
            return Util.getNowUnixTimeMs(this.elapsedRealtimeEpochOffsetMs);
        }

        public long getDefaultPositionMs() {
            return Util.usToMs(this.defaultPositionUs);
        }

        public long getDefaultPositionUs() {
            return this.defaultPositionUs;
        }

        public long getDurationMs() {
            return Util.usToMs(this.durationUs);
        }

        public long getPositionInFirstPeriodUs() {
            return this.positionInFirstPeriodUs;
        }

        public int hashCode() {
            int r1 = (((Opcodes.RSUB_INT_LIT8 + this.uid.hashCode()) * 31) + this.mediaItem.hashCode()) * 31;
            Object r0 = this.manifest;
            int r2 = 0;
            if (r0 != null) goto L5;
            int r02 = 0;
        L6:
            int r12 = (r1 + r02) * 31;
            MediaItem.LiveConfiguration r03 = this.liveConfiguration;
            if (r03 == null) goto L10;
            r2 = r03.hashCode();
        L10:
            int r13 = (r12 + r2) * 31;
            long r22 = this.presentationStartTimeMs;
            int r14 = (r13 + ((int) (r22 ^ (r22 >>> 32)))) * 31;
            long r23 = this.windowStartTimeMs;
            int r15 = (r14 + ((int) (r23 ^ (r23 >>> 32)))) * 31;
            long r24 = this.elapsedRealtimeEpochOffsetMs;
            int r16 = (((((((r15 + ((int) (r24 ^ (r24 >>> 32)))) * 31) + (this.isSeekable ? 1 : 0)) * 31) + (this.isDynamic ? 1 : 0)) * 31) + (this.isPlaceholder ? 1 : 0)) * 31;
            long r25 = this.defaultPositionUs;
            int r17 = (r16 + ((int) (r25 ^ (r25 >>> 32)))) * 31;
            long r26 = this.durationUs;
            int r18 = (((((r17 + ((int) (r26 ^ (r26 >>> 32)))) * 31) + this.firstPeriodIndex) * 31) + this.lastPeriodIndex) * 31;
            long r27 = this.positionInFirstPeriodUs;
            return r18 + ((int) (r27 ^ (r27 >>> 32)));
        L5:
            r02 = r0.hashCode();
            goto L6
        }

        public boolean isLive() {
            if (this.liveConfiguration == null) goto L6;
            return true;
        L6:
            return false;
        }

        public Window set(Object r1, MediaItem r2, Object r3, long r4, long r6, long r8, boolean r10, boolean r11, MediaItem.LiveConfiguration r12, long r13, long r15, int r17, int r18, long r19) {
            this.uid = r1;
            if (r2 == null) goto L5;
            MediaItem r14 = r2;
        L6:
            this.mediaItem = r14;
            if (r2 == null) goto L11;
            MediaItem.LocalConfiguration r16 = r2.localConfiguration;
            if (r16 == null) goto L11;
            Object r110 = r16.tag;
        L12:
            this.tag = r110;
            this.manifest = r3;
            this.presentationStartTimeMs = r4;
            this.windowStartTimeMs = r6;
            this.elapsedRealtimeEpochOffsetMs = r8;
            this.isSeekable = r10;
            this.isDynamic = r11;
            this.liveConfiguration = r12;
            this.defaultPositionUs = r13;
            this.durationUs = r15;
            this.firstPeriodIndex = r17;
            this.lastPeriodIndex = r18;
            this.positionInFirstPeriodUs = r19;
            this.isPlaceholder = false;
            return this;
        L11:
            r110 = null;
            goto L12
        L5:
            r14 = PLACEHOLDER_MEDIA_ITEM;
            goto L6
        }

        public Bundle toBundle() {
            Bundle r0 = new Bundle();
            if (MediaItem.EMPTY.equals(this.mediaItem) == true) goto L5;
            r0.putBundle(FIELD_MEDIA_ITEM, this.mediaItem.toBundle());
        L5:
            long r1 = this.presentationStartTimeMs;
            if (r1 == (-9223372036854775807L)) goto L8;
            r0.putLong(FIELD_PRESENTATION_START_TIME_MS, r1);
        L8:
            long r12 = this.windowStartTimeMs;
            if (r12 == (-9223372036854775807L)) goto L11;
            r0.putLong(FIELD_WINDOW_START_TIME_MS, r12);
        L11:
            long r13 = this.elapsedRealtimeEpochOffsetMs;
            if (r13 == (-9223372036854775807L)) goto L14;
            r0.putLong(FIELD_ELAPSED_REALTIME_EPOCH_OFFSET_MS, r13);
        L14:
            boolean r14 = this.isSeekable;
            if (r14 == false) goto L17;
            r0.putBoolean(FIELD_IS_SEEKABLE, r14);
        L17:
            boolean r15 = this.isDynamic;
            if (r15 == false) goto L20;
            r0.putBoolean(FIELD_IS_DYNAMIC, r15);
        L20:
            MediaItem.LiveConfiguration r16 = this.liveConfiguration;
            if (r16 == null) goto L23;
            r0.putBundle(FIELD_LIVE_CONFIGURATION, r16.toBundle());
        L23:
            boolean r17 = this.isPlaceholder;
            if (r17 == false) goto L26;
            r0.putBoolean(FIELD_IS_PLACEHOLDER, r17);
        L26:
            long r18 = this.defaultPositionUs;
            if (r18 == 0) goto L29;
            r0.putLong(FIELD_DEFAULT_POSITION_US, r18);
        L29:
            long r19 = this.durationUs;
            if (r19 == (-9223372036854775807L)) goto L32;
            r0.putLong(FIELD_DURATION_US, r19);
        L32:
            int r110 = this.firstPeriodIndex;
            if (r110 == 0) goto L35;
            r0.putInt(FIELD_FIRST_PERIOD_INDEX, r110);
        L35:
            int r111 = this.lastPeriodIndex;
            if (r111 == 0) goto L38;
            r0.putInt(FIELD_LAST_PERIOD_INDEX, r111);
        L38:
            long r112 = this.positionInFirstPeriodUs;
            if (r112 == 0) goto L41;
            r0.putLong(FIELD_POSITION_IN_FIRST_PERIOD_US, r112);
        L41:
            return r0;
        }
    }

    static {
        EMPTY = new AnonymousClass1();
        FIELD_WINDOWS = Util.intToStringMaxRadix(0);
        FIELD_PERIODS = Util.intToStringMaxRadix(1);
        FIELD_SHUFFLED_WINDOW_INDICES = Util.intToStringMaxRadix(2);
    }

    public Timeline() {
    }

    public final Timeline copyWithSingleWindow(int r6) {
        if (getWindowCount() != 1) goto L5;
        return this;
    L5:
        Window r62 = getWindow(r6, new Window(), 0);
        ImmutableList.Builder r0 = ImmutableList.builder();
        int r2 = r62.firstPeriodIndex;
    L6:
        int r3 = r62.lastPeriodIndex;
        if (r2 > r3) goto L9;
        Period r32 = getPeriod(r2, new Period(), true);
        r32.windowIndex = 0;
        r0.add(r32);
        r2 = r2 + 1;
        goto L6
    L9:
        r62.lastPeriodIndex = r3 - r62.firstPeriodIndex;
        r62.firstPeriodIndex = 0;
        return new RemotableTimeline(ImmutableList.of(r62), r0.build(), new int[]{0});
    }

    public boolean equals(Object r10) {
        if (this != r10) goto L6;
        return true;
    L6:
        if ((r10 instanceof Timeline) == true) goto L8;
        return false;
    L8:
        Timeline r102 = (Timeline) r10;
        if (r102.getWindowCount() == getWindowCount()) goto L11;
    L39:
        return false;
    L11:
        if (r102.getPeriodCount() != getPeriodCount()) goto L39;
        Window r1 = new Window();
        Period r3 = new Period();
        Window r4 = new Window();
        Period r5 = new Period();
        int r6 = 0;
    L15:
        if (r6 >= getWindowCount()) goto L20;
        if (getWindow(r6, r1).equals(r102.getWindow(r6, r4)) == false) goto L18;
        r6 = r6 + 1;
        goto L15
    L18:
        return false;
    L20:
        int r12 = 0;
    L22:
        if (r12 >= getPeriodCount()) goto L27;
        if (getPeriod(r12, r3, true).equals(r102.getPeriod(r12, r5, true)) == false) goto L25;
        r12 = r12 + 1;
        goto L22
    L25:
        return false;
    L27:
        int r13 = getFirstWindowIndex(true);
        if (r13 == r102.getFirstWindowIndex(true)) goto L30;
        return false;
    L30:
        int r32 = getLastWindowIndex(true);
        if (r32 == r102.getLastWindowIndex(true)) goto L33;
        return false;
    L33:
        if (r13 == r32) goto L38;
        int r42 = getNextWindowIndex(r13, 0, true);
        if (r42 != r102.getNextWindowIndex(r13, 0, true)) goto L36;
        r13 = r42;
        goto L33
    L36:
        return false;
    L38:
        return true;
    }

    public int getFirstWindowIndex(boolean r1) {
        if (isEmpty() == false) goto L6;
        return -1;
    L6:
        return 0;
    }

    public abstract int getIndexOfPeriod(Object r1);

    public int getLastWindowIndex(boolean r1) {
        if (isEmpty() == false) goto L7;
        return -1;
    L7:
        return getWindowCount() - 1;
    }

    public final int getNextPeriodIndex(int r2, Period r3, Window r4, int r5, boolean r6) {
        int r32 = getPeriod(r2, r3).windowIndex;
        if (getWindow(r32, r4).lastPeriodIndex != r2) goto L10;
        int r22 = getNextWindowIndex(r32, r5, r6);
        if (r22 != (-1)) goto L8;
        return -1;
    L8:
        return getWindow(r22, r4).firstPeriodIndex;
    L10:
        return r2 + 1;
    }

    public int getNextWindowIndex(int r3, int r4, boolean r5) {
        if (r4 == 0) goto L17;
        if (r4 != 1) goto L6;
        return r3;
    L6:
        if (r4 == 2) goto L8;
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return 0;
    L8:
        if (r3 != getLastWindowIndex(r5)) goto L12;
        return getFirstWindowIndex(r5);
    L12:
        return r3 + 1;
    L17:
        if (r3 != getLastWindowIndex(r5)) goto L21;
        return -1;
    L21:
        return r3 + 1;
    }

    public final Period getPeriod(int r2, Period r3) {
        return getPeriod(r2, r3, false);
    }

    public abstract Period getPeriod(int r1, Period r2, boolean r3);

    public Period getPeriodByUid(Object r2, Period r3) {
        return getPeriod(getIndexOfPeriod(r2), r3, true);
    }

    public abstract int getPeriodCount();

    public final Pair<Object, Long> getPeriodPositionUs(Window r9, Period r10, int r11, long r12) {
        return (Pair) Assertions.checkNotNull(getPeriodPositionUs(r9, r10, r11, r12, 0));
    }

    public final Pair<Object, Long> getPeriodPositionUs(Window r4, Period r5, int r6, long r7, long r9) {
        Assertions.checkIndex(r6, 0, getWindowCount());
        getWindow(r6, r4, r9);
        if (r7 != (-9223372036854775807L)) goto L8;
        r7 = r4.getDefaultPositionUs();
        if (r7 != (-9223372036854775807L)) goto L8;
        return null;
    L8:
        int r62 = r4.firstPeriodIndex;
        getPeriod(r62, r5);
    L10:
        if (r62 >= r4.lastPeriodIndex) goto L16;
        if (r5.positionInWindowUs == r7) goto L16;
        int r0 = r62 + 1;
        if (getPeriod(r0, r5).positionInWindowUs > r7) goto L16;
        r62 = r0;
    L16:
        getPeriod(r62, r5, true);
        long r72 = r7 - r5.positionInWindowUs;
        long r3 = r5.durationUs;
        if (r3 == (-9223372036854775807L)) goto L19;
        r72 = Math.min(r72, r3 - 1);
    L19:
        long r32 = Math.max(0, r72);
        return Pair.create(Assertions.checkNotNull(r5.uid), Long.valueOf(r32));
    }

    public int getPreviousWindowIndex(int r3, int r4, boolean r5) {
        if (r4 == 0) goto L17;
        if (r4 != 1) goto L6;
        return r3;
    L6:
        if (r4 == 2) goto L8;
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return 0;
    L8:
        if (r3 != getFirstWindowIndex(r5)) goto L12;
        return getLastWindowIndex(r5);
    L12:
        return r3 - 1;
    L17:
        if (r3 != getFirstWindowIndex(r5)) goto L21;
        return -1;
    L21:
        return r3 - 1;
    }

    public abstract Object getUidOfPeriod(int r1);

    public final Window getWindow(int r3, Window r4) {
        return getWindow(r3, r4, 0);
    }

    public abstract Window getWindow(int r1, Window r2, long r3);

    public abstract int getWindowCount();

    public int hashCode() {
        Window r0 = new Window();
        Period r1 = new Period();
        int r2 = Opcodes.RSUB_INT_LIT8 + getWindowCount();
        int r4 = 0;
    L4:
        if (r4 >= getWindowCount()) goto L6;
        r2 = (r2 * 31) + getWindow(r4, r0).hashCode();
        r4 = r4 + 1;
        goto L4
    L6:
        int r22 = (r2 * 31) + getPeriodCount();
        int r02 = 0;
    L8:
        if (r02 >= getPeriodCount()) goto L10;
        r22 = (r22 * 31) + getPeriod(r02, r1, true).hashCode();
        r02 = r02 + 1;
        goto L8
    L10:
        int r03 = getFirstWindowIndex(true);
    L12:
        if (r03 == (-1)) goto L14;
        r22 = (r22 * 31) + r03;
        r03 = getNextWindowIndex(r03, 0, true);
        goto L12
    L14:
        return r22;
    }

    public final boolean isEmpty() {
        if (getWindowCount() != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean isLastPeriod(int r1, Period r2, Window r3, int r4, boolean r5) {
        if (getNextPeriodIndex(r1, r2, r3, r4, r5) != (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    public final Bundle toBundle() {
        ArrayList r0 = new ArrayList();
        int r1 = getWindowCount();
        Window r2 = new Window();
        int r4 = 0;
    L3:
        if (r4 >= r1) goto L5;
        r0.add(getWindow(r4, r2, 0).toBundle());
        r4 = r4 + 1;
        goto L3
    L5:
        ArrayList r22 = new ArrayList();
        int r42 = getPeriodCount();
        Period r5 = new Period();
        int r6 = 0;
    L6:
        if (r6 >= r42) goto L8;
        r22.add(getPeriod(r6, r5, false).toBundle());
        r6 = r6 + 1;
        goto L6
    L8:
        int[] r43 = new int[r1];
        if (r1 <= 0) goto L11;
        r43[0] = getFirstWindowIndex(true);
    L11:
        int r62 = 1;
    L12:
        if (r62 >= r1) goto L14;
        r43[r62] = getNextWindowIndex(r43[r62 - 1], 0, true);
        r62 = r62 + 1;
        goto L12
    L14:
        Bundle r8 = new Bundle();
        r8.putBinder(FIELD_WINDOWS, new BundleListRetriever(r0));
        r8.putBinder(FIELD_PERIODS, new BundleListRetriever(r22));
        r8.putIntArray(FIELD_SHUFFLED_WINDOW_INDICES, r43);
        return r8;
    }
}
