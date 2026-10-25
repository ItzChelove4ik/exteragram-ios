package androidx.media3.exoplayer.source;

import android.util.Pair;
import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.upstream.Allocator;
import java.util.Objects;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes4.dex */
public final class MaskingMediaSource extends WrappingMediaSource {
    private boolean hasRealTimeline;
    private boolean hasStartedPreparing;
    private boolean isPrepared;
    private final Timeline.Period period;
    private MaskingTimeline timeline;
    private MaskingMediaPeriod unpreparedMaskingMediaPeriod;
    private final boolean useLazyPreparation;
    private final Timeline.Window window;

    public static final class MaskingTimeline extends ForwardingTimeline {
        public static final Object MASKING_EXTERNAL_PERIOD_UID = null;
        private final Object replacedInternalPeriodUid;
        private final Object replacedInternalWindowUid;

        static {
            MASKING_EXTERNAL_PERIOD_UID = new Object();
        }

        private MaskingTimeline(Timeline r1, Object r2, Object r3) {
            super(r1);
            this.replacedInternalWindowUid = r2;
            this.replacedInternalPeriodUid = r3;
        }

        public static /* synthetic */ Object access$000(MaskingTimeline r0) {
            return r0.replacedInternalPeriodUid;
        }

        public static MaskingTimeline createWithPlaceholderTimeline(MediaItem r3) {
            return new MaskingTimeline(new PlaceholderTimeline(r3), Timeline.Window.SINGLE_WINDOW_UID, MASKING_EXTERNAL_PERIOD_UID);
        }

        public static MaskingTimeline createWithRealTimeline(Timeline r1, Object r2, Object r3) {
            return new MaskingTimeline(r1, r2, r3);
        }

        public MaskingTimeline cloneWithUpdatedTimeline(Timeline r3) {
            return new MaskingTimeline(r3, this.replacedInternalWindowUid, this.replacedInternalPeriodUid);
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public int getIndexOfPeriod(Object r3) {
            Timeline r0 = this.timeline;
            if (MASKING_EXTERNAL_PERIOD_UID.equals(r3) == false) goto L8;
            Object r2 = this.replacedInternalPeriodUid;
            if (r2 == null) goto L8;
            r3 = r2;
        L8:
            return r0.getIndexOfPeriod(r3);
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public Timeline.Period getPeriod(int r2, Timeline.Period r3, boolean r4) {
            this.timeline.getPeriod(r2, r3, r4);
            if (Objects.equals(r3.uid, this.replacedInternalPeriodUid) == false) goto L6;
            if (r4 == false) goto L6;
            r3.uid = MASKING_EXTERNAL_PERIOD_UID;
        L6:
            return r3;
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public Object getUidOfPeriod(int r2) {
            Object r22 = this.timeline.getUidOfPeriod(r2);
            if (Objects.equals(r22, this.replacedInternalPeriodUid) == true) goto L5;
            return r22;
        L5:
            return MASKING_EXTERNAL_PERIOD_UID;
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public Timeline.Window getWindow(int r2, Timeline.Window r3, long r4) {
            this.timeline.getWindow(r2, r3, r4);
            if (Objects.equals(r3.uid, this.replacedInternalWindowUid) == false) goto L5;
            r3.uid = Timeline.Window.SINGLE_WINDOW_UID;
        L5:
            return r3;
        }
    }

    public static final class PlaceholderTimeline extends Timeline {
        private final MediaItem mediaItem;

        public PlaceholderTimeline(MediaItem r1) {
            this.mediaItem = r1;
        }

        @Override // androidx.media3.common.Timeline
        public int getIndexOfPeriod(Object r1) {
            if (r1 != MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID) goto L6;
            return 0;
        L6:
            return -1;
        }

        @Override // androidx.media3.common.Timeline
        public Timeline.Period getPeriod(int r11, Timeline.Period r12, boolean r13) {
            Object r10 = null;
            if (r13 == false) goto L5;
            Integer r1 = 0;
        L6:
            if (r13 == false) goto L8;
            r10 = MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID;
        L8:
            r12.set(r1, r10, 0, -9223372036854775807L, 0, AdPlaybackState.NONE, true);
            return r12;
        L5:
            r1 = null;
            goto L6
        }

        @Override // androidx.media3.common.Timeline
        public int getPeriodCount() {
            return 1;
        }

        @Override // androidx.media3.common.Timeline
        public Object getUidOfPeriod(int r1) {
            return MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID;
        }

        @Override // androidx.media3.common.Timeline
        public Timeline.Window getWindow(int r22, Timeline.Window r23, long r24) {
            r23.set(Timeline.Window.SINGLE_WINDOW_UID, this.mediaItem, null, -9223372036854775807L, -9223372036854775807L, -9223372036854775807L, false, true, null, 0, -9223372036854775807L, 0, 0, 0);
            r23.isPlaceholder = true;
            return r23;
        }

        @Override // androidx.media3.common.Timeline
        public int getWindowCount() {
            return 1;
        }
    }

    public MaskingMediaSource(MediaSource r2, boolean r3) {
        super(r2);
        if (r3 == true) goto L5;
    L7:
        boolean r32 = false;
    L8:
        this.useLazyPreparation = r32;
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        Timeline r33 = r2.getInitialTimeline();
        if (r33 == null) goto L12;
        this.timeline = MaskingTimeline.createWithRealTimeline(r33, null, null);
        this.hasRealTimeline = true;
        return;
    L12:
        this.timeline = MaskingTimeline.createWithPlaceholderTimeline(r2.getMediaItem());
        return;
    L5:
        if (r2.isSingleWindow() == false) goto L7;
        r32 = true;
        goto L8
    }

    private Object getExternalPeriodUid(Object r2) {
        if (MaskingTimeline.access$000(this.timeline) != null) goto L5;
    L8:
        return r2;
    L5:
        if (MaskingTimeline.access$000(this.timeline).equals(r2) == false) goto L8;
        return MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID;
    }

    private Object getInternalPeriodUid(Object r2) {
        if (MaskingTimeline.access$000(this.timeline) != null) goto L5;
    L8:
        return r2;
    L5:
        if (r2.equals(MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID) == false) goto L8;
        return MaskingTimeline.access$000(this.timeline);
    }

    @RequiresNonNull({"unpreparedMaskingMediaPeriod"})
    private boolean setPreparePositionOverrideToUnpreparedMaskingPeriod(long r6) {
        MaskingMediaPeriod r0 = this.unpreparedMaskingMediaPeriod;
        int r1 = this.timeline.getIndexOfPeriod(r0.id.periodUid);
        if (r1 != (-1)) goto L6;
        return false;
    L6:
        long r12 = this.timeline.getPeriod(r1, this.period).durationUs;
        if (r12 != (-9223372036854775807L)) goto L9;
    L11:
        r0.overridePreparePositionUs(r6);
        return true;
    L9:
        if (r6 < r12) goto L11;
        r6 = Math.max(0, r12 - 1);
        goto L11
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource, androidx.media3.exoplayer.source.MediaSource
    public boolean canUpdateMediaItem(MediaItem r1) {
        return this.mediaSource.canUpdateMediaItem(r1);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MaskingMediaPeriod createPeriod(MediaSource.MediaPeriodId r2, Allocator r3, long r4) {
        MaskingMediaPeriod r0 = new MaskingMediaPeriod(r2, r3, r4);
        r0.setMediaSource(this.mediaSource);
        if (this.isPrepared == false) goto L6;
        r0.createPeriod(r2.copyWithPeriodUid(getInternalPeriodUid(r2.periodUid)));
        return r0;
    L6:
        this.unpreparedMaskingMediaPeriod = r0;
        if (this.hasStartedPreparing == true) goto L9;
        this.hasStartedPreparing = true;
        prepareChildSource();
    L9:
        return r0;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public /* bridge */ /* synthetic */ MediaPeriod createPeriod(MediaSource.MediaPeriodId r1, Allocator r2, long r3) {
        return createPeriod(r1, r2, r3);
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSource.MediaPeriodId r2) {
        return r2.copyWithPeriodUid(getExternalPeriodUid(r2.periodUid));
    }

    public Timeline getTimeline() {
        return this.timeline;
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource
    public void onChildSourceInfoRefreshed(Timeline r15) {
        if (this.isPrepared == false) goto L8;
        this.timeline = this.timeline.cloneWithUpdatedTimeline(r15);
        MaskingMediaPeriod r152 = this.unpreparedMaskingMediaPeriod;
        if (r152 == null) goto L29;
        setPreparePositionOverrideToUnpreparedMaskingPeriod(r152.getPreparePositionOverrideUs());
    L29:
        MediaSource.MediaPeriodId r153 = null;
    L30:
        this.hasRealTimeline = true;
        this.isPrepared = true;
        refreshSourceInfo(this.timeline);
        if (r153 == null) goto L34;
        ((MaskingMediaPeriod) Assertions.checkNotNull(this.unpreparedMaskingMediaPeriod)).createPeriod(r153);
        return;
    L34:
        return;
    L8:
        if (r15.isEmpty() == true) goto L10;
        r15.getWindow(0, this.window);
        long r2 = this.window.getDefaultPositionUs();
        Object r0 = this.window.uid;
        MaskingMediaPeriod r4 = this.unpreparedMaskingMediaPeriod;
        if (r4 == null) goto L19;
        long r42 = r4.getPreparePositionUs();
        this.timeline.getPeriodByUid(this.unpreparedMaskingMediaPeriod.id.periodUid, this.period);
        long r6 = this.period.getPositionInWindowUs() + r42;
        if (r6 == this.timeline.getWindow(0, this.window).getDefaultPositionUs()) goto L19;
        long r12 = r6;
    L20:
        Pair<Object, Long> r154 = r15.getPeriodPositionUs(this.window, this.period, 0, r12);
        Object r1 = r154.first;
        long r22 = ((Long) r154.second).longValue();
        if (this.hasRealTimeline == false) goto L23;
        MaskingTimeline r155 = this.timeline.cloneWithUpdatedTimeline(r15);
    L24:
        this.timeline = r155;
        MaskingMediaPeriod r156 = this.unpreparedMaskingMediaPeriod;
        if (r156 == null) goto L29;
        if (setPreparePositionOverrideToUnpreparedMaskingPeriod(r22) == false) goto L29;
        MediaSource.MediaPeriodId r157 = r156.id;
        r153 = r157.copyWithPeriodUid(getInternalPeriodUid(r157.periodUid));
        goto L30
    L23:
        r155 = MaskingTimeline.createWithRealTimeline(r15, r0, r1);
    L19:
        r12 = r2;
        goto L20
    L10:
        if (this.hasRealTimeline == false) goto L12;
        MaskingTimeline r158 = this.timeline.cloneWithUpdatedTimeline(r15);
    L13:
        this.timeline = r158;
        goto L29
    L12:
        r158 = MaskingTimeline.createWithRealTimeline(r15, Timeline.Window.SINGLE_WINDOW_UID, MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID);
        goto L13
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource
    public void prepareSourceInternal() {
        if (this.useLazyPreparation == true) goto L6;
        this.hasStartedPreparing = true;
        prepareChildSource();
        return;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void releasePeriod(MediaPeriod r2) {
        ((MaskingMediaPeriod) r2).releasePeriod();
        if (r2 != this.unpreparedMaskingMediaPeriod) goto L6;
        this.unpreparedMaskingMediaPeriod = null;
        return;
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.BaseMediaSource
    public void releaseSourceInternal() {
        this.isPrepared = false;
        this.hasStartedPreparing = false;
        super.releaseSourceInternal();
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource, androidx.media3.exoplayer.source.MediaSource
    public void updateMediaItem(MediaItem r4) {
        if (this.hasRealTimeline == false) goto L5;
        this.timeline = this.timeline.cloneWithUpdatedTimeline(new TimelineWithUpdatedMediaItem(this.timeline.timeline, r4));
    L6:
        this.mediaSource.updateMediaItem(r4);
        return;
    L5:
        this.timeline = MaskingTimeline.createWithPlaceholderTimeline(r4);
        goto L6
    }
}
