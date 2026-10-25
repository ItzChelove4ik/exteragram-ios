package androidx.media3.exoplayer.source;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocator;
import java.io.IOException;

/* loaded from: classes4.dex */
public final class MaskingMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    private final Allocator allocator;
    private MediaPeriod.Callback callback;
    public final MediaSource.MediaPeriodId id;
    private PrepareListener listener;
    private MediaPeriod mediaPeriod;
    private MediaSource mediaSource;
    private boolean notifiedPrepareError;
    private long preparePositionOverrideUs;
    private final long preparePositionUs;

    public interface PrepareListener {
        void onPrepareComplete(MediaSource.MediaPeriodId r1);

        void onPrepareError(MediaSource.MediaPeriodId r1, IOException r2);
    }

    public MaskingMediaPeriod(MediaSource.MediaPeriodId r1, Allocator r2, long r3) {
        this.id = r1;
        this.allocator = r2;
        this.preparePositionUs = r3;
        this.preparePositionOverrideUs = -9223372036854775807L;
    }

    private long getPreparePositionWithOverride(long r5) {
        long r0 = this.preparePositionOverrideUs;
        if (r0 == (-9223372036854775807L)) goto L5;
        return r0;
    L5:
        return r5;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(LoadingInfo r1) {
        MediaPeriod r0 = this.mediaPeriod;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.continueLoading(r1) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public void createPeriod(MediaSource.MediaPeriodId r5) {
        long r0 = getPreparePositionWithOverride(this.preparePositionUs);
        MediaPeriod r52 = ((MediaSource) Assertions.checkNotNull(this.mediaSource)).createPeriod(r5, this.allocator, r0);
        this.mediaPeriod = r52;
        if (this.callback == null) goto L6;
        r52.prepare(this, r0);
        return;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void discardBuffer(long r1, boolean r3) {
        ((MediaPeriod) Util.castNonNull(this.mediaPeriod)).discardBuffer(r1, r3);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long r1, SeekParameters r3) {
        return ((MediaPeriod) Util.castNonNull(this.mediaPeriod)).getAdjustedSeekPositionUs(r1, r3);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getBufferedPositionUs() {
        return ((MediaPeriod) Util.castNonNull(this.mediaPeriod)).getBufferedPositionUs();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        return ((MediaPeriod) Util.castNonNull(this.mediaPeriod)).getNextLoadPositionUs();
    }

    public long getPreparePositionOverrideUs() {
        return this.preparePositionOverrideUs;
    }

    public long getPreparePositionUs() {
        return this.preparePositionUs;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return ((MediaPeriod) Util.castNonNull(this.mediaPeriod)).getTrackGroups();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean isLoading() {
        MediaPeriod r0 = this.mediaPeriod;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isLoading() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void maybeThrowPrepareError() {
        MediaPeriod r0 = this.mediaPeriod;     // Catch: IOException -> L6
        if (r0 == null) goto L8;
        r0.maybeThrowPrepareError();     // Catch: IOException -> L6
        return;
    L8:
        MediaSource r02 = this.mediaSource;     // Catch: IOException -> L6
        if (r02 == null) goto L20;
        r02.maybeThrowSourceInfoRefreshError();     // Catch: IOException -> L6
        return;
    L20:
        return;
    L6:
        e = move-exception;
        PrepareListener r1 = this.listener;
        if (r1 != null) goto L15;
        throw e;
    L15:
        if (this.notifiedPrepareError == true) goto L21;
        this.notifiedPrepareError = true;
        r1.onPrepareError(this.id, e);
        return;
    }

    public void onContinueLoadingRequested(MediaPeriod r1) {
        ((MediaPeriod.Callback) Util.castNonNull(this.callback)).onContinueLoadingRequested(this);
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
    public /* bridge */ /* synthetic */ void onContinueLoadingRequested(SequenceableLoader r1) {
        onContinueLoadingRequested((MediaPeriod) r1);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod.Callback
    public void onPrepared(MediaPeriod r1) {
        ((MediaPeriod.Callback) Util.castNonNull(this.callback)).onPrepared(this);
        PrepareListener r12 = this.listener;
        if (r12 == null) goto L6;
        r12.onPrepareComplete(this.id);
        return;
    }

    public void overridePreparePositionUs(long r1) {
        this.preparePositionOverrideUs = r1;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void prepare(MediaPeriod.Callback r1, long r2) {
        this.callback = r1;
        MediaPeriod r12 = this.mediaPeriod;
        if (r12 == null) goto L6;
        r12.prepare(this, getPreparePositionWithOverride(this.preparePositionUs));
        return;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long readDiscontinuity() {
        return ((MediaPeriod) Util.castNonNull(this.mediaPeriod)).readDiscontinuity();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long r1) {
        ((MediaPeriod) Util.castNonNull(this.mediaPeriod)).reevaluateBuffer(r1);
    }

    public void releasePeriod() {
        if (this.mediaPeriod == null) goto L6;
        ((MediaSource) Assertions.checkNotNull(this.mediaSource)).releasePeriod(this.mediaPeriod);
        return;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long seekToUs(long r1) {
        return ((MediaPeriod) Util.castNonNull(this.mediaPeriod)).seekToUs(r1);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long selectTracks(ExoTrackSelection[] r7, boolean[] r8, SampleStream[] r9, boolean[] r10, long r11) {
        long r0 = this.preparePositionOverrideUs;
        if (r0 != (-9223372036854775807L)) goto L5;
    L7:
        this.preparePositionOverrideUs = -9223372036854775807L;
        return ((MediaPeriod) Util.castNonNull(this.mediaPeriod)).selectTracks(r7, r8, r9, r10, r11);
    L5:
        if (r11 != this.preparePositionUs) goto L7;
        r11 = r0;
        goto L7
    }

    public void setMediaSource(MediaSource r2) {
        if (this.mediaSource != null) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        this.mediaSource = r2;
        return;
    L5:
        r0 = false;
        goto L6
    }
}
