package androidx.media3.exoplayer.source;

import android.net.Uri;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;

/* loaded from: classes4.dex */
public final class SinglePeriodTimeline extends Timeline {
    private static final MediaItem MEDIA_ITEM = null;
    private static final Object UID = null;
    private final long elapsedRealtimeEpochOffsetMs;
    private final boolean isDynamic;
    private final boolean isSeekable;
    private final MediaItem.LiveConfiguration liveConfiguration;
    private final Object manifest;
    private final MediaItem mediaItem;
    private final long periodDurationUs;
    private final long presentationStartTimeMs;
    private final boolean suppressPositionProjection;
    private final long windowDefaultStartPositionUs;
    private final long windowDurationUs;
    private final long windowPositionInPeriodUs;
    private final long windowStartTimeMs;

    static {
        UID = new Object();
        MEDIA_ITEM = new MediaItem.Builder().setMediaId("SinglePeriodTimeline").setUri(Uri.EMPTY).build();
    }

    public SinglePeriodTimeline(long r1, long r3, long r5, long r7, long r9, long r11, long r13, boolean r15, boolean r16, boolean r17, Object r18, MediaItem r19, MediaItem.LiveConfiguration r20) {
        this.presentationStartTimeMs = r1;
        this.windowStartTimeMs = r3;
        this.elapsedRealtimeEpochOffsetMs = r5;
        this.periodDurationUs = r7;
        this.windowDurationUs = r9;
        this.windowPositionInPeriodUs = r11;
        this.windowDefaultStartPositionUs = r13;
        this.isSeekable = r15;
        this.isDynamic = r16;
        this.suppressPositionProjection = r17;
        this.manifest = r18;
        this.mediaItem = (MediaItem) Assertions.checkNotNull(r19);
        this.liveConfiguration = r20;
    }

    public SinglePeriodTimeline(long r22, long r24, long r26, long r28, boolean r30, boolean r31, boolean r32, Object r33, MediaItem r34) {
        if (r32 == false) goto L6;
        MediaItem.LiveConfiguration r1 = r34.liveConfiguration;
    L7:
        this(-9223372036854775807L, -9223372036854775807L, -9223372036854775807L, r22, r24, r26, r28, r30, r31, false, r33, r34, r1);
        return;
    L6:
        r1 = null;
        goto L7
    }

    public SinglePeriodTimeline(long r15, boolean r17, boolean r18, boolean r19, Object r20, MediaItem r21) {
        this(r15, r15, 0, 0, r17, r18, r19, r20, r21);
    }

    @Override // androidx.media3.common.Timeline
    public int getIndexOfPeriod(Object r1) {
        if (UID.equals(r1) == false) goto L6;
        return 0;
    L6:
        return -1;
    }

    @Override // androidx.media3.common.Timeline
    public Timeline.Period getPeriod(int r9, Timeline.Period r10, boolean r11) {
        Assertions.checkIndex(r9, 0, 1);
        if (r11 == false) goto L6;
        Object r92 = UID;
    L5:
        Object r2 = r92;
        return r10.set(null, r2, 0, this.periodDurationUs, -this.windowPositionInPeriodUs);
    L6:
        r92 = null;
        goto L5
    }

    @Override // androidx.media3.common.Timeline
    public int getPeriodCount() {
        return 1;
    }

    @Override // androidx.media3.common.Timeline
    public Object getUidOfPeriod(int r2) {
        Assertions.checkIndex(r2, 0, 1);
        return UID;
    }

    @Override // androidx.media3.common.Timeline
    public Timeline.Window getWindow(int r25, Timeline.Window r26, long r27) {
        Assertions.checkIndex(r25, 0, 1);
        long r1 = this.windowDefaultStartPositionUs;
        boolean r14 = this.isDynamic;
        if (r14 == true) goto L5;
    L14:
        long r16 = r1;
    L16:
        return r26.set(Timeline.Window.SINGLE_WINDOW_UID, this.mediaItem, this.manifest, this.presentationStartTimeMs, this.windowStartTimeMs, this.elapsedRealtimeEpochOffsetMs, this.isSeekable, r14, this.liveConfiguration, r16, this.windowDurationUs, 0, 0, this.windowPositionInPeriodUs);
    L5:
        if (this.suppressPositionProjection == true) goto L14;
        if (r27 == 0) goto L14;
        long r3 = this.windowDurationUs;
        if (r3 != (-9223372036854775807L)) goto L11;
    L10:
        r16 = -9223372036854775807L;
        goto L16
    L11:
        r1 = r1 + r27;
        if (r1 <= r3) goto L14;
        goto L14
    }

    @Override // androidx.media3.common.Timeline
    public int getWindowCount() {
        return 1;
    }
}
