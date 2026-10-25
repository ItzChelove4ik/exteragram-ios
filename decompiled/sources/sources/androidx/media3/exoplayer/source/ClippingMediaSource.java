package androidx.media3.exoplayer.source;

import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.upstream.Allocator;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class ClippingMediaSource extends WrappingMediaSource {
    private final boolean allowDynamicClippingUpdates;
    private final boolean allowUnseekableMedia;
    private IllegalClippingException clippingError;
    private ClippingTimeline clippingTimeline;
    private final boolean enableInitialDiscontinuity;
    private final long endUs;
    private final ArrayList<ClippingMediaPeriod> mediaPeriods;
    private long periodEndUs;
    private long periodStartUs;
    private final boolean relativeToDefaultPosition;
    private final long startUs;
    private final Timeline.Window window;

    /* renamed from: androidx.media3.exoplayer.source.ClippingMediaSource$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder {
        private boolean allowDynamicClippingUpdates;
        private boolean allowUnseekableMedia;
        private boolean buildCalled;
        private boolean enableInitialDiscontinuity;
        private long endPositionUs;
        private final MediaSource mediaSource;
        private boolean relativeToDefaultPosition;
        private long startPositionUs;

        public Builder(MediaSource r3) {
            this.mediaSource = (MediaSource) Assertions.checkNotNull(r3);
            this.enableInitialDiscontinuity = true;
            this.endPositionUs = Long.MIN_VALUE;
        }

        public static /* synthetic */ MediaSource access$100(Builder r0) {
            return r0.mediaSource;
        }

        public static /* synthetic */ long access$200(Builder r2) {
            return r2.startPositionUs;
        }

        public static /* synthetic */ long access$300(Builder r2) {
            return r2.endPositionUs;
        }

        public static /* synthetic */ boolean access$400(Builder r0) {
            return r0.enableInitialDiscontinuity;
        }

        public static /* synthetic */ boolean access$500(Builder r0) {
            return r0.allowDynamicClippingUpdates;
        }

        public static /* synthetic */ boolean access$600(Builder r0) {
            return r0.relativeToDefaultPosition;
        }

        public static /* synthetic */ boolean access$700(Builder r0) {
            return r0.allowUnseekableMedia;
        }

        public ClippingMediaSource build() {
            this.buildCalled = true;
            return new ClippingMediaSource(this, null);
        }

        public Builder setAllowDynamicClippingUpdates(boolean r2) {
            Assertions.checkState(!this.buildCalled);
            this.allowDynamicClippingUpdates = r2;
            return this;
        }

        public Builder setAllowUnseekableMedia(boolean r2) {
            Assertions.checkState(!this.buildCalled);
            this.allowUnseekableMedia = r2;
            return this;
        }

        public Builder setEnableInitialDiscontinuity(boolean r2) {
            Assertions.checkState(!this.buildCalled);
            this.enableInitialDiscontinuity = r2;
            return this;
        }

        public Builder setEndPositionUs(long r2) {
            Assertions.checkState(!this.buildCalled);
            this.endPositionUs = r2;
            return this;
        }

        public Builder setRelativeToDefaultPosition(boolean r2) {
            Assertions.checkState(!this.buildCalled);
            this.relativeToDefaultPosition = r2;
            return this;
        }

        public Builder setStartPositionUs(long r3) {
            if (r3 < 0) goto L5;
            boolean r0 = true;
        L6:
            Assertions.checkArgument(r0);
            Assertions.checkState(!this.buildCalled);
            this.startPositionUs = r3;
            return this;
        L5:
            r0 = false;
            goto L6
        }
    }

    public static final class ClippingTimeline extends ForwardingTimeline {
        private final long durationUs;
        private final long endUs;
        private final boolean isDynamic;
        private final long startUs;

        public ClippingTimeline(Timeline r7, long r8, long r10, boolean r12) {
            super(r7);
            if (r10 != Long.MIN_VALUE) goto L5;
        L9:
            boolean r2 = false;
            if (r7.getPeriodCount() != 1) goto L48;
            Timeline.Window r72 = r7.getWindow(0, new Timeline.Window());
            long r82 = Math.max(0, r8);
            if (r12 == false) goto L14;
        L22:
            if (r10 != Long.MIN_VALUE) goto L24;
            long r102 = r72.durationUs;
        L25:
            long r0 = r72.durationUs;
            if (r0 != (-9223372036854775807L)) goto L28;
        L33:
            this.startUs = r82;
            this.endUs = r102;
            if (r102 != (-9223372036854775807L)) goto L36;
            long r83 = -9223372036854775807L;
        L37:
            this.durationUs = r83;
            if (r72.isDynamic == false) goto L45;
            if (r102 != (-9223372036854775807L)) goto L41;
        L44:
            r2 = true;
            goto L45
        L41:
            if (r0 == (-9223372036854775807L)) goto L45;
            if (r102 == r0) goto L44;
        L45:
            this.isDynamic = r2;
            return;
        L36:
            r83 = r102 - r82;
            goto L37
        L28:
            if (r102 <= r0) goto L31;
            r102 = r0;
        L31:
            if (r82 <= r102) goto L33;
            r82 = r102;
            goto L33
        L24:
            r102 = Math.max(0, r10);
            goto L25
        L14:
            if (r72.isPlaceholder == true) goto L22;
            if (r82 == 0) goto L22;
            if (r72.isSeekable == true) goto L22;
            throw new IllegalClippingException(1);
        L48:
            throw new IllegalClippingException(0);
        L5:
            if (r10 >= r8) goto L9;
            throw new IllegalClippingException(2, r8, r10);
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public Timeline.Period getPeriod(int r13, Timeline.Period r14, boolean r15) {
            this.timeline.getPeriod(0, r14, r15);
            long r10 = r14.getPositionInWindowUs() - this.startUs;
            long r12 = this.durationUs;
            long r0 = -9223372036854775807L;
            if (r12 != (-9223372036854775807L)) goto L5;
        L4:
            long r8 = r0;
            return r14.set(r14.id, r14.uid, 0, r8, r10);
        L5:
            r0 = r12 - r10;
            goto L4
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public Timeline.Window getWindow(int r5, Timeline.Window r6, long r7) {
            this.timeline.getWindow(0, r6, 0);
            long r72 = r6.positionInFirstPeriodUs;
            long r0 = this.startUs;
            r6.positionInFirstPeriodUs = r72 + r0;
            r6.durationUs = this.durationUs;
            r6.isDynamic = this.isDynamic;
            long r73 = r6.defaultPositionUs;
            if (r73 == (-9223372036854775807L)) goto L9;
            long r74 = Math.max(r73, r0);
            r6.defaultPositionUs = r74;
            long r02 = this.endUs;
            if (r02 == (-9223372036854775807L)) goto L8;
            r74 = Math.min(r74, r02);
        L8:
            r6.defaultPositionUs = r74 - this.startUs;
        L9:
            long r4 = Util.usToMs(this.startUs);
            long r75 = r6.presentationStartTimeMs;
            if (r75 == (-9223372036854775807L)) goto L12;
            r6.presentationStartTimeMs = r75 + r4;
        L12:
            long r76 = r6.windowStartTimeMs;
            if (r76 == (-9223372036854775807L)) goto L15;
            r6.windowStartTimeMs = r76 + r4;
        L15:
            return r6;
        }
    }

    public static final class IllegalClippingException extends IOException {
        public final int reason;

        public IllegalClippingException(int r7) {
            this(r7, -9223372036854775807L, -9223372036854775807L);
        }

        public IllegalClippingException(int r3, long r4, long r6) {
            super("Illegal clipping: " + getReasonDescription(r3, r4, r6));
            this.reason = r3;
        }

        private static String getReasonDescription(int r3, long r4, long r6) {
            if (r3 == 0) goto L19;
            boolean r0 = true;
            if (r3 != 1) goto L6;
            return "not seekable to start";
        L6:
            if (r3 == 2) goto L10;
            return "unknown";
        L10:
            if (r4 != (-9223372036854775807L)) goto L12;
        L14:
            r0 = false;
        L15:
            Assertions.checkState(r0);
            return "start exceeds end. Start time: " + r4 + ", End time: " + r6;
        L12:
            if (r6 == (-9223372036854775807L)) goto L14;
        L19:
            return "invalid period count";
        }
    }

    private ClippingMediaSource(Builder r3) {
        super(Builder.access$100(r3));
        this.startUs = Builder.access$200(r3);
        this.endUs = Builder.access$300(r3);
        this.enableInitialDiscontinuity = Builder.access$400(r3);
        this.allowDynamicClippingUpdates = Builder.access$500(r3);
        this.relativeToDefaultPosition = Builder.access$600(r3);
        this.allowUnseekableMedia = Builder.access$700(r3);
        this.mediaPeriods = new ArrayList();
        this.window = new Timeline.Window();
    }

    public /* synthetic */ ClippingMediaSource(Builder r1, AnonymousClass1 r2) {
        this(r1);
    }

    private void refreshClippedTimeline(Timeline r15) {
        int r1 = 0;
        r15.getWindow(0, this.window);
        long r4 = this.window.getPositionInFirstPeriodUs();
        long r6 = Long.MIN_VALUE;
        if (this.clippingTimeline != null) goto L5;
    L14:
        long r8 = this.startUs;
        long r10 = this.endUs;
        if (this.relativeToDefaultPosition == false) goto L17;
        long r12 = this.window.getDefaultPositionUs();
        r8 = r8 + r12;
        r10 = r10 + r12;
    L17:
        this.periodStartUs = r4 + r8;
        if (this.endUs == Long.MIN_VALUE) goto L21;
        r6 = r4 + r10;
    L21:
        this.periodEndUs = r6;
        int r0 = this.mediaPeriods.size();
        int r2 = 0;
    L22:
        if (r2 >= r0) goto L24;
        this.mediaPeriods.get(r2).updateClipping(this.periodStartUs, this.periodEndUs);
        r2 = r2 + 1;
        goto L22
    L24:
        r6 = r10;
    L34:
        ClippingTimeline r22 = new ClippingTimeline(r15, r8, r6, this.allowUnseekableMedia);     // Catch: IllegalClippingException -> L28
        this.clippingTimeline = r22;     // Catch: IllegalClippingException -> L28
        refreshSourceInfo(r22);
        return;
    L28:
        e = move-exception;
        this.clippingError = e;
    L31:
        if (r1 >= this.mediaPeriods.size()) goto L33;
        this.mediaPeriods.get(r1).setClippingError(this.clippingError);
        r1 = r1 + 1;
        goto L31
    L33:
        return;
    L5:
        if (this.mediaPeriods.isEmpty() == true) goto L14;
        if (this.allowDynamicClippingUpdates == true) goto L14;
        r8 = this.periodStartUs - r4;
        if (this.endUs == Long.MIN_VALUE) goto L34;
        r6 = this.periodEndUs - r4;
        goto L34
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource, androidx.media3.exoplayer.source.MediaSource
    public boolean canUpdateMediaItem(MediaItem r3) {
        if (getMediaItem().clippingConfiguration.equals(r3.clippingConfiguration) == true) goto L5;
        return false;
    L5:
        if (this.mediaSource.canUpdateMediaItem(r3) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId r8, Allocator r9, long r10) {
        ClippingMediaPeriod r0 = new ClippingMediaPeriod(this.mediaSource.createPeriod(r8, r9, r10), this.enableInitialDiscontinuity, this.periodStartUs, this.periodEndUs);
        this.mediaPeriods.add(r0);
        return r0;
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() {
        IllegalClippingException r0 = this.clippingError;
        if (r0 != null) goto L6;
        super.maybeThrowSourceInfoRefreshError();
        return;
    L6:
        throw r0;
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource
    public void onChildSourceInfoRefreshed(Timeline r2) {
        if (this.clippingError == null) goto L5;
        return;
    L5:
        refreshClippedTimeline(r2);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void releasePeriod(MediaPeriod r2) {
        Assertions.checkState(this.mediaPeriods.remove(r2));
        this.mediaSource.releasePeriod(((ClippingMediaPeriod) r2).mediaPeriod);
        if (this.mediaPeriods.isEmpty() == true) goto L5;
        return;
    L5:
        if (this.allowDynamicClippingUpdates == true) goto L9;
        refreshClippedTimeline(((ClippingTimeline) Assertions.checkNotNull(this.clippingTimeline)).timeline);
        return;
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.BaseMediaSource
    public void releaseSourceInternal() {
        super.releaseSourceInternal();
        this.clippingError = null;
        this.clippingTimeline = null;
    }
}
