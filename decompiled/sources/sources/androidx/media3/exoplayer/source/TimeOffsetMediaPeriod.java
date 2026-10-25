package androidx.media3.exoplayer.source;

import androidx.media3.common.util.Assertions;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;

/* loaded from: classes4.dex */
final class TimeOffsetMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    private MediaPeriod.Callback callback;
    private final MediaPeriod mediaPeriod;
    private final long timeOffsetUs;

    public static final class TimeOffsetSampleStream implements SampleStream {
        private final SampleStream sampleStream;
        private final long timeOffsetUs;

        public TimeOffsetSampleStream(SampleStream r1, long r2) {
            this.sampleStream = r1;
            this.timeOffsetUs = r2;
        }

        public SampleStream getChildStream() {
            return this.sampleStream;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public boolean isReady() {
            return this.sampleStream.isReady();
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public void maybeThrowError() {
            this.sampleStream.maybeThrowError();
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int readData(FormatHolder r5, DecoderInputBuffer r6, int r7) {
            int r52 = this.sampleStream.readData(r5, r6, r7);
            if (r52 != (-4)) goto L5;
            r6.timeUs += this.timeOffsetUs;
        L5:
            return r52;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int skipData(long r4) {
            return this.sampleStream.skipData(r4 - this.timeOffsetUs);
        }
    }

    public TimeOffsetMediaPeriod(MediaPeriod r1, long r2) {
        this.mediaPeriod = r1;
        this.timeOffsetUs = r2;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(LoadingInfo r5) {
        return this.mediaPeriod.continueLoading(r5.buildUpon().setPlaybackPositionUs(r5.playbackPositionUs - this.timeOffsetUs).build());
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void discardBuffer(long r4, boolean r6) {
        this.mediaPeriod.discardBuffer(r4 - this.timeOffsetUs, r6);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long r4, SeekParameters r6) {
        return this.mediaPeriod.getAdjustedSeekPositionUs(r4 - this.timeOffsetUs, r6) + this.timeOffsetUs;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getBufferedPositionUs() {
        long r0 = this.mediaPeriod.getBufferedPositionUs();
        if (r0 != Long.MIN_VALUE) goto L6;
        return Long.MIN_VALUE;
    L6:
        return r0 + this.timeOffsetUs;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        long r0 = this.mediaPeriod.getNextLoadPositionUs();
        if (r0 != Long.MIN_VALUE) goto L6;
        return Long.MIN_VALUE;
    L6:
        return r0 + this.timeOffsetUs;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return this.mediaPeriod.getTrackGroups();
    }

    public MediaPeriod getWrappedMediaPeriod() {
        return this.mediaPeriod;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean isLoading() {
        return this.mediaPeriod.isLoading();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void maybeThrowPrepareError() {
        this.mediaPeriod.maybeThrowPrepareError();
    }

    public void onContinueLoadingRequested(MediaPeriod r1) {
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
    public /* bridge */ /* synthetic */ void onContinueLoadingRequested(SequenceableLoader r1) {
        onContinueLoadingRequested((MediaPeriod) r1);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod.Callback
    public void onPrepared(MediaPeriod r1) {
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onPrepared(this);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void prepare(MediaPeriod.Callback r3, long r4) {
        this.callback = r3;
        this.mediaPeriod.prepare(this, r4 - this.timeOffsetUs);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long readDiscontinuity() {
        long r0 = this.mediaPeriod.readDiscontinuity();
        if (r0 != (-9223372036854775807L)) goto L6;
        return -9223372036854775807L;
    L6:
        return r0 + this.timeOffsetUs;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long r4) {
        this.mediaPeriod.reevaluateBuffer(r4 - this.timeOffsetUs);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long seekToUs(long r4) {
        return this.mediaPeriod.seekToUs(r4 - this.timeOffsetUs) + this.timeOffsetUs;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long selectTracks(ExoTrackSelection[] r10, boolean[] r11, SampleStream[] r12, boolean[] r13, long r14) {
        SampleStream[] r4 = new SampleStream[r12.length];
        int r0 = 0;
        int r1 = 0;
    L3:
        SampleStream r8 = null;
        if (r1 >= r12.length) goto L9;
        TimeOffsetSampleStream r2 = (TimeOffsetSampleStream) r12[r1];
        if (r2 == null) goto L8;
        r8 = r2.getChildStream();
    L8:
        r4[r1] = r8;
        r1 = r1 + 1;
        goto L3
    L9:
        long r102 = this.mediaPeriod.selectTracks(r10, r11, r4, r13, r14 - this.timeOffsetUs);
    L11:
        if (r0 >= r12.length) goto L22;
        SampleStream r132 = r4[r0];
        if (r132 != null) goto L15;
        r12[r0] = null;
    L20:
        r0 = r0 + 1;
        goto L11
    L15:
        SampleStream r142 = r12[r0];
        if (r142 != null) goto L18;
    L19:
        r12[r0] = new TimeOffsetSampleStream(r132, this.timeOffsetUs);
        goto L20
    L18:
        if (((TimeOffsetSampleStream) r142).getChildStream() == r132) goto L20;
    L22:
        return r102 + this.timeOffsetUs;
    }
}
