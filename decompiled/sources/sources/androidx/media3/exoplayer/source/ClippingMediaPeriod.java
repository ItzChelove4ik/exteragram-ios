package androidx.media3.exoplayer.source;

import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.ClippingMediaSource;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes4.dex */
public final class ClippingMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    private MediaPeriod.Callback callback;
    private ClippingMediaSource.IllegalClippingException clippingError;
    long endUs;
    public final MediaPeriod mediaPeriod;
    private long pendingInitialDiscontinuityPositionUs;
    private ClippingSampleStream[] sampleStreams;
    long startUs;

    public final class ClippingSampleStream implements SampleStream {
        public final SampleStream childStream;
        private boolean sentEos;
        final /* synthetic */ ClippingMediaPeriod this$0;

        public ClippingSampleStream(ClippingMediaPeriod r1, SampleStream r2) {
            this.this$0 = r1;
            this.childStream = r2;
        }

        public void clearSentEos() {
            this.sentEos = false;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public boolean isReady() {
            if (this.this$0.isPendingInitialDiscontinuity() == false) goto L5;
            return false;
        L5:
            if (this.childStream.isReady() == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public void maybeThrowError() {
            this.childStream.maybeThrowError();
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int readData(FormatHolder r13, DecoderInputBuffer r14, int r15) {
            if (this.this$0.isPendingInitialDiscontinuity() == false) goto L6;
            return -3;
        L6:
            if (this.sentEos == false) goto L9;
            r14.setFlags(4);
            return -4;
        L9:
            long r4 = this.this$0.getBufferedPositionUs();
            int r152 = this.childStream.readData(r13, r14, r15);
            if (r152 != (-5)) goto L24;
            Format r142 = (Format) Assertions.checkNotNull(r13.format);
            int r153 = r142.encoderDelay;
            if (r153 == 0) goto L14;
        L15:
            ClippingMediaPeriod r12 = this.this$0;
            int r2 = 0;
            if (r12.startUs == 0) goto L19;
            r153 = 0;
        L19:
            if (r12.endUs != Long.MIN_VALUE) goto L22;
            r2 = r142.encoderPadding;
        L22:
            r13.format = r142.buildUpon().setEncoderDelay(r153).setEncoderPadding(r2).build();
        L23:
            return -5;
        L14:
            if (r142.encoderPadding == 0) goto L23;
        L24:
            long r8 = this.this$0.endUs;
            if (r8 == Long.MIN_VALUE) goto L36;
            if (r152 == (-4)) goto L28;
        L29:
            if (r152 != (-3)) goto L36;
            if (r4 != Long.MIN_VALUE) goto L36;
            if (r14.waitingForKeys == true) goto L36;
        L34:
            r14.clear();
            r14.setFlags(4);
            this.sentEos = true;
            return -4;
        L28:
            if (r14.timeUs >= r8) goto L34;
        L36:
            return r152;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int skipData(long r2) {
            if (this.this$0.isPendingInitialDiscontinuity() == false) goto L7;
            return -3;
        L7:
            return this.childStream.skipData(r2);
        }
    }

    public ClippingMediaPeriod(MediaPeriod r1, boolean r2, long r3, long r5) {
        this.mediaPeriod = r1;
        this.sampleStreams = new ClippingSampleStream[0];
        if (r2 == false) goto L5;
        long r12 = r3;
    L6:
        this.pendingInitialDiscontinuityPositionUs = r12;
        this.startUs = r3;
        this.endUs = r5;
        return;
    L5:
        r12 = -9223372036854775807L;
        goto L6
    }

    private SeekParameters clipSeekParameters(long r9, SeekParameters r11) {
        long r0 = Util.constrainValue(r11.toleranceBeforeUs, 0, r9 - this.startUs);
        long r2 = r11.toleranceAfterUs;
        long r4 = this.endUs;
        if (r4 != Long.MIN_VALUE) goto L6;
        long r8 = LongCompanionObject.MAX_VALUE;
    L7:
        long r82 = Util.constrainValue(r2, 0, r8);
        if (r0 != r11.toleranceBeforeUs) goto L13;
        if (r82 != r11.toleranceAfterUs) goto L13;
        return r11;
    L13:
        return new SeekParameters(r0, r82);
    L6:
        r8 = r4 - r9;
        goto L7
    }

    private static long enforceClippingRange(long r0, long r2, long r4) {
        long r02 = Math.max(r0, r2);
        if (r4 != Long.MIN_VALUE) goto L5;
        return r02;
    L5:
        return Math.min(r02, r4);
    }

    private static boolean shouldKeepInitialDiscontinuity(long r2, long r4, ExoTrackSelection[] r6) {
        if (r2 >= r4) goto L6;
        return true;
    L6:
        if (r2 == 0) goto L15;
        int r22 = r6.length;
        int r42 = 0;
    L8:
        if (r42 >= r22) goto L15;
        ExoTrackSelection r0 = r6[r42];
        if (r0 == null) goto L14;
        Format r02 = r0.getSelectedFormat();
        if (MimeTypes.allSamplesAreSyncSamples(r02.sampleMimeType, r02.codecs) == true) goto L14;
        return true;
    L14:
        r42 = r42 + 1;
    L15:
        return false;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(LoadingInfo r1) {
        return this.mediaPeriod.continueLoading(r1);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void discardBuffer(long r1, boolean r3) {
        this.mediaPeriod.discardBuffer(r1, r3);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long r4, SeekParameters r6) {
        long r0 = this.startUs;
        if (r4 != r0) goto L5;
        return r0;
    L5:
        SeekParameters r62 = clipSeekParameters(r4, r6);
        return this.mediaPeriod.getAdjustedSeekPositionUs(r4, r62);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getBufferedPositionUs() {
        long r0 = this.mediaPeriod.getBufferedPositionUs();
        if (r0 == Long.MIN_VALUE) goto L10;
        long r4 = this.endUs;
        if (r4 != Long.MIN_VALUE) goto L7;
    L9:
        return r0;
    L7:
        if (r0 < r4) goto L9;
    L10:
        return Long.MIN_VALUE;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        long r0 = this.mediaPeriod.getNextLoadPositionUs();
        if (r0 == Long.MIN_VALUE) goto L10;
        long r4 = this.endUs;
        if (r4 != Long.MIN_VALUE) goto L7;
    L9:
        return r0;
    L7:
        if (r0 < r4) goto L9;
    L10:
        return Long.MIN_VALUE;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return this.mediaPeriod.getTrackGroups();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean isLoading() {
        return this.mediaPeriod.isLoading();
    }

    public boolean isPendingInitialDiscontinuity() {
        if (this.pendingInitialDiscontinuityPositionUs == (-9223372036854775807L)) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void maybeThrowPrepareError() {
        ClippingMediaSource.IllegalClippingException r0 = this.clippingError;
        if (r0 != null) goto L6;
        this.mediaPeriod.maybeThrowPrepareError();
        return;
    L6:
        throw r0;
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
        if (this.clippingError == null) goto L5;
        return;
    L5:
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onPrepared(this);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void prepare(MediaPeriod.Callback r1, long r2) {
        this.callback = r1;
        this.mediaPeriod.prepare(this, r2);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long readDiscontinuity() {
        if (isPendingInitialDiscontinuity() == false) goto L8;
        long r3 = this.pendingInitialDiscontinuityPositionUs;
        this.pendingInitialDiscontinuityPositionUs = -9223372036854775807L;
        long r5 = readDiscontinuity();
        if (r5 == (-9223372036854775807L)) goto L7;
        return r5;
    L7:
        return r3;
    L8:
        long r32 = this.mediaPeriod.readDiscontinuity();
        if (r32 != (-9223372036854775807L)) goto L12;
        return -9223372036854775807L;
    L12:
        return enforceClippingRange(r32, this.startUs, this.endUs);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long r1) {
        this.mediaPeriod.reevaluateBuffer(r1);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long seekToUs(long r8) {
        this.pendingInitialDiscontinuityPositionUs = -9223372036854775807L;
        ClippingSampleStream[] r0 = this.sampleStreams;
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L9;
        ClippingSampleStream r3 = r0[r2];
        if (r3 == null) goto L7;
        r3.clearSentEos();
    L7:
        r2 = r2 + 1;
        goto L3
    L9:
        return enforceClippingRange(this.mediaPeriod.seekToUs(r8), this.startUs, this.endUs);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long selectTracks(ExoTrackSelection[] r18, boolean[] r19, SampleStream[] r20, boolean[] r21, long r22) {
        this.sampleStreams = new ClippingSampleStream[r20.length];
        SampleStream[] r6 = new SampleStream[r20.length];
        int r2 = 0;
        int r3 = 0;
    L3:
        SampleStream r10 = null;
        if (r3 >= r20.length) goto L9;
        ClippingSampleStream[] r4 = this.sampleStreams;
        ClippingSampleStream r5 = (ClippingSampleStream) r20[r3];
        r4[r3] = r5;
        if (r5 == null) goto L8;
        r10 = r5.childStream;
    L8:
        r6[r3] = r10;
        r3 = r3 + 1;
        goto L3
    L9:
        long r11 = this.mediaPeriod.selectTracks(r18, r19, r6, r21, r22);
        long r32 = enforceClippingRange(r11, r22, this.endUs);
        if (isPendingInitialDiscontinuity() == true) goto L12;
    L14:
        long r7 = -9223372036854775807L;
    L15:
        this.pendingInitialDiscontinuityPositionUs = r7;
    L17:
        if (r2 >= r20.length) goto L27;
        SampleStream r52 = r6[r2];
        ClippingSampleStream[] r72 = this.sampleStreams;
        if (r52 != null) goto L21;
        r72[r2] = null;
    L26:
        r20[r2] = this.sampleStreams[r2];
        r2 = r2 + 1;
        goto L17
    L21:
        ClippingSampleStream r8 = r72[r2];
        if (r8 != null) goto L24;
    L25:
        r72[r2] = new ClippingSampleStream(this, r52);
        goto L26
    L24:
        if (r8.childStream == r52) goto L26;
    L27:
        return r32;
    L12:
        if (shouldKeepInitialDiscontinuity(r11, r22, r18) == false) goto L14;
        r7 = r32;
        goto L15
    }

    public void setClippingError(ClippingMediaSource.IllegalClippingException r1) {
        this.clippingError = r1;
    }

    public void updateClipping(long r1, long r3) {
        this.startUs = r1;
        this.endUs = r3;
    }
}
