package androidx.media3.exoplayer;

import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.exoplayer.source.ClippingMediaPeriod;
import androidx.media3.exoplayer.source.EmptySampleStream;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.SampleStream;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.trackselection.TrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelectorResult;
import androidx.media3.exoplayer.upstream.Allocator;
import java.io.IOException;

/* loaded from: classes4.dex */
final class MediaPeriodHolder {
    public boolean allRenderersInCorrectState;
    public boolean hasEnabledTracks;
    public MediaPeriodInfo info;
    private final boolean[] mayRetainStreamFlags;
    public final MediaPeriod mediaPeriod;
    private final MediaSourceList mediaSourceList;
    private MediaPeriodHolder next;
    public boolean prepareCalled;
    public boolean prepared;
    private final RendererCapabilities[] rendererCapabilities;
    private long rendererPositionOffsetUs;
    public final SampleStream[] sampleStreams;
    public final long targetPreloadBufferDurationUs;
    private TrackGroupArray trackGroups;
    private final TrackSelector trackSelector;
    private TrackSelectorResult trackSelectorResult;
    public final Object uid;

    public interface Factory {
        MediaPeriodHolder create(MediaPeriodInfo r1, long r2);
    }

    public MediaPeriodHolder(RendererCapabilities[] r1, long r2, TrackSelector r4, Allocator r5, MediaSourceList r6, MediaPeriodInfo r7, TrackSelectorResult r8, long r9) {
        this.rendererCapabilities = r1;
        this.rendererPositionOffsetUs = r2;
        this.trackSelector = r4;
        this.mediaSourceList = r6;
        MediaSource.MediaPeriodId r12 = r7.id;
        this.uid = r12.periodUid;
        this.info = r7;
        this.targetPreloadBufferDurationUs = r9;
        this.trackGroups = TrackGroupArray.EMPTY;
        this.trackSelectorResult = r8;
        this.sampleStreams = new SampleStream[r1.length];
        this.mayRetainStreamFlags = new boolean[r1.length];
        this.mediaPeriod = createMediaPeriod(r12, r6, r5, r7.startPositionUs, r7.endPositionUs, r7.isPrecededByTransitionFromSameStream);
    }

    private void associateNoSampleRenderersWithEmptySampleStream(SampleStream[] r4) {
        int r0 = 0;
    L3:
        RendererCapabilities[] r1 = this.rendererCapabilities;
        if (r0 >= r1.length) goto L11;
        if (r1[r0].getTrackType() != (-2)) goto L10;
        if (this.trackSelectorResult.isRendererEnabled(r0) == false) goto L10;
        r4[r0] = new EmptySampleStream();
    L10:
        r0 = r0 + 1;
        goto L3
    }

    private static MediaPeriod createMediaPeriod(MediaSource.MediaPeriodId r0, MediaSourceList r1, Allocator r2, long r3, long r5, boolean r7) {
        MediaPeriod r12 = r1.createPeriod(r0, r2, r3);
        if (r5 != (-9223372036854775807L)) goto L5;
        return r12;
    L5:
        return new ClippingMediaPeriod(r12, !r7, 0, r5);
    }

    private void disableTrackSelectionsInResult() {
        if (isLoadingMediaPeriod() == false) goto L13;
        int r0 = 0;
    L6:
        TrackSelectorResult r1 = this.trackSelectorResult;
        if (r0 >= r1.length) goto L18;
        boolean r12 = r1.isRendererEnabled(r0);
        ExoTrackSelection r2 = this.trackSelectorResult.selections[r0];
        if (r12 == false) goto L12;
        if (r2 == null) goto L12;
        r2.disable();
    L12:
        r0 = r0 + 1;
        goto L6
    L18:
        return;
    }

    private void disassociateNoSampleRenderersWithEmptySampleStream(SampleStream[] r4) {
        int r0 = 0;
    L3:
        RendererCapabilities[] r1 = this.rendererCapabilities;
        if (r0 >= r1.length) goto L9;
        if (r1[r0].getTrackType() != (-2)) goto L8;
        r4[r0] = null;
    L8:
        r0 = r0 + 1;
        goto L3
    }

    private void enableTrackSelectionsInResult() {
        if (isLoadingMediaPeriod() == false) goto L13;
        int r0 = 0;
    L6:
        TrackSelectorResult r1 = this.trackSelectorResult;
        if (r0 >= r1.length) goto L18;
        boolean r12 = r1.isRendererEnabled(r0);
        ExoTrackSelection r2 = this.trackSelectorResult.selections[r0];
        if (r12 == false) goto L12;
        if (r2 == null) goto L12;
        r2.enable();
    L12:
        r0 = r0 + 1;
        goto L6
    L18:
        return;
    }

    private boolean isLoadingMediaPeriod() {
        if (this.next != null) goto L6;
        return true;
    L6:
        return false;
    }

    private static void releaseMediaPeriod(MediaSourceList r1, MediaPeriod r2) {
    L8:
        e = move-exception;
        Log.e("MediaPeriodHolder", "Period release failed.", e);
        return;
    L3:
        if ((r2 instanceof ClippingMediaPeriod) == false) goto L6;
        r1.releasePeriod(((ClippingMediaPeriod) r2).mediaPeriod);     // Catch: RuntimeException -> L8
        return;
    L6:
        r1.releasePeriod(r2);     // Catch: RuntimeException -> L8
    }

    public long applyTrackSelection(TrackSelectorResult r8, long r9, boolean r11) {
        return applyTrackSelection(r8, r9, r11, new boolean[this.rendererCapabilities.length]);
    }

    public long applyTrackSelection(TrackSelectorResult r12, long r13, boolean r15, boolean[] r16) {
        int r1 = 0;
    L3:
        boolean r3 = true;
        if (r1 >= r12.length) goto L12;
        boolean[] r2 = this.mayRetainStreamFlags;
        if (r15 == false) goto L8;
    L10:
        r3 = false;
    L11:
        r2[r1] = r3;
        r1 = r1 + 1;
        goto L3
    L8:
        if (r12.isEquivalent(this.trackSelectorResult, r1) == false) goto L10;
    L12:
        disassociateNoSampleRenderersWithEmptySampleStream(this.sampleStreams);
        disableTrackSelectionsInResult();
        this.trackSelectorResult = r12;
        enableTrackSelectionsInResult();
        long r132 = this.mediaPeriod.selectTracks(r12.selections, this.mayRetainStreamFlags, this.sampleStreams, r16, r13);
        associateNoSampleRenderersWithEmptySampleStream(this.sampleStreams);
        this.hasEnabledTracks = false;
        int r152 = 0;
    L13:
        SampleStream[] r14 = this.sampleStreams;
        if (r152 >= r14.length) goto L26;
        if (r14[r152] == null) goto L21;
        Assertions.checkState(r12.isRendererEnabled(r152));
        if (this.rendererCapabilities[r152].getTrackType() == (-2)) goto L25;
        this.hasEnabledTracks = true;
    L25:
        r152 = r152 + 1;
        goto L13
    L21:
        if (r12.selections[r152] != null) goto L23;
        boolean r17 = true;
    L24:
        Assertions.checkState(r17);
        goto L25
    L23:
        r17 = false;
        goto L24
    L26:
        return r132;
    }

    public boolean canBeUsedForMediaPeriodInfo(MediaPeriodInfo r5) {
        if (MediaPeriodQueue.areDurationsCompatible(this.info.durationUs, r5.durationUs) == false) goto L10;
        MediaPeriodInfo r4 = this.info;
        if (r4.startPositionUs == r5.startPositionUs) goto L7;
        return false;
    L7:
        if (r4.id.equals(r5.id) == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public void continueLoading(LoadingInfo r2) {
        Assertions.checkState(isLoadingMediaPeriod());
        this.mediaPeriod.continueLoading(r2);
    }

    public long getBufferedPositionUs() {
        if (this.prepared == true) goto L7;
        return this.info.startPositionUs;
    L7:
        if (this.hasEnabledTracks == false) goto L9;
        long r3 = this.mediaPeriod.getBufferedPositionUs();
    L11:
        if (r3 == Long.MIN_VALUE) goto L13;
        return r3;
    L13:
        return this.info.durationUs;
    L9:
        r3 = Long.MIN_VALUE;
        goto L11
    }

    public MediaPeriodHolder getNext() {
        return this.next;
    }

    public long getNextLoadPositionUs() {
        if (this.prepared == true) goto L7;
        return 0;
    L7:
        return this.mediaPeriod.getNextLoadPositionUs();
    }

    public long getRendererOffset() {
        return this.rendererPositionOffsetUs;
    }

    public long getStartPositionRendererTime() {
        return this.info.startPositionUs + this.rendererPositionOffsetUs;
    }

    public TrackGroupArray getTrackGroups() {
        return this.trackGroups;
    }

    public TrackSelectorResult getTrackSelectorResult() {
        return this.trackSelectorResult;
    }

    public void handlePrepared(float r5, Timeline r6, boolean r7) {
        this.prepared = true;
        this.trackGroups = this.mediaPeriod.getTrackGroups();
        TrackSelectorResult r52 = selectTracks(r5, r6, r7);
        MediaPeriodInfo r62 = this.info;
        long r0 = r62.startPositionUs;
        long r63 = r62.durationUs;
        if (r63 != (-9223372036854775807L)) goto L5;
    L7:
        long r53 = applyTrackSelection(r52, r0, false);
        long r02 = this.rendererPositionOffsetUs;
        MediaPeriodInfo r72 = this.info;
        this.rendererPositionOffsetUs = r02 + (r72.startPositionUs - r53);
        this.info = r72.copyWithStartPositionUs(r53);
        return;
    L5:
        if (r0 < r63) goto L7;
        r0 = Math.max(0, r63 - 1);
        goto L7
    }

    public boolean hasLoadingError() {
    L12:
        return true;
    L3:
        if (this.prepared == true) goto L5;
        this.mediaPeriod.maybeThrowPrepareError();     // Catch: IOException -> L12
    L11:
        return false;
    L5:
        SampleStream[] r4 = this.sampleStreams;     // Catch: IOException -> L12
        int r0 = r4.length;     // Catch: IOException -> L12
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L11;
        SampleStream r3 = r4[r2];     // Catch: IOException -> L12
        if (r3 == null) goto L10;
        r3.maybeThrowError();     // Catch: IOException -> L12
    L10:
        r2 = r2 + 1;
        goto L6
    }

    public boolean isFullyBuffered() {
        if (this.prepared == true) goto L5;
        return false;
    L5:
        if (this.hasEnabledTracks == true) goto L7;
        return true;
    L7:
        if (this.mediaPeriod.getBufferedPositionUs() != Long.MIN_VALUE) goto L13;
        return true;
    L13:
        return false;
    }

    public boolean isFullyPreloaded() {
        if (this.prepared == true) goto L5;
        return false;
    L5:
        if (isFullyBuffered() == false) goto L7;
        return true;
    L7:
        if ((getBufferedPositionUs() - this.info.startPositionUs) < this.targetPreloadBufferDurationUs) goto L13;
        return true;
    L13:
        return false;
    }

    public void prepare(MediaPeriod.Callback r2, long r3) {
        this.prepareCalled = true;
        this.mediaPeriod.prepare(r2, r3);
    }

    public void reevaluateBuffer(long r2) {
        Assertions.checkState(isLoadingMediaPeriod());
        if (this.prepared == false) goto L6;
        this.mediaPeriod.reevaluateBuffer(toPeriodTime(r2));
        return;
    }

    public void release() {
        disableTrackSelectionsInResult();
        releaseMediaPeriod(this.mediaSourceList, this.mediaPeriod);
    }

    public TrackSelectorResult selectTracks(float r6, Timeline r7, boolean r8) {
        TrackSelectorResult r72 = this.trackSelector.selectTracks(this.rendererCapabilities, getTrackGroups(), this.info.id, r7);
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r1 >= r72.length) goto L20;
        boolean r2 = r72.isRendererEnabled(r1);
        ExoTrackSelection[] r3 = r72.selections;
        boolean r4 = true;
        if (r2 == false) goto L15;
        if (r3[r1] == null) goto L10;
    L13:
        Assertions.checkState(r4);
    L19:
        r1 = r1 + 1;
        goto L4
    L10:
        if (this.rendererCapabilities[r1].getTrackType() == (-2)) goto L13;
        r4 = false;
        goto L13
    L15:
        if (r3[r1] == null) goto L18;
        r4 = false;
    L18:
        Assertions.checkState(r4);
        goto L19
    L20:
        ExoTrackSelection[] r5 = r72.selections;
        int r12 = r5.length;
    L21:
        if (r0 >= r12) goto L26;
        ExoTrackSelection r22 = r5[r0];
        if (r22 == null) goto L25;
        r22.onPlaybackSpeed(r6);
        r22.onPlayWhenReadyChanged(r8);
    L25:
        r0 = r0 + 1;
        goto L21
    L26:
        return r72;
    }

    public void setNext(MediaPeriodHolder r2) {
        if (r2 != this.next) goto L5;
        return;
    L5:
        disableTrackSelectionsInResult();
        this.next = r2;
        enableTrackSelectionsInResult();
    }

    public void setRendererOffset(long r1) {
        this.rendererPositionOffsetUs = r1;
    }

    public long toPeriodTime(long r3) {
        return r3 - getRendererOffset();
    }

    public long toRendererTime(long r3) {
        return r3 + getRendererOffset();
    }

    public void updateClipping() {
        MediaPeriod r0 = this.mediaPeriod;
        if ((r0 instanceof ClippingMediaPeriod) == false) goto L9;
        long r1 = this.info.endPositionUs;
        if (r1 != (-9223372036854775807L)) goto L7;
        r1 = Long.MIN_VALUE;
    L7:
        ((ClippingMediaPeriod) r0).updateClipping(0, r1);
        return;
    }
}
