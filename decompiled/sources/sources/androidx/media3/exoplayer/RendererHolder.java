package androidx.media3.exoplayer;

import androidx.media3.common.Format;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.metadata.MetadataRenderer;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.SampleStream;
import androidx.media3.exoplayer.text.TextRenderer;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.trackselection.TrackSelectorResult;
import androidx.media3.exoplayer.video.VideoFrameMetadataListener;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes4.dex */
class RendererHolder {
    private final int index;
    private int prewarmingState;
    private final Renderer primaryRenderer;
    private boolean primaryRequiresReset;
    private final Renderer secondaryRenderer;
    private boolean secondaryRequiresReset;

    public RendererHolder(Renderer r1, Renderer r2, int r3) {
        this.primaryRenderer = r1;
        this.index = r3;
        this.secondaryRenderer = r2;
        this.prewarmingState = 0;
        this.primaryRequiresReset = false;
        this.secondaryRequiresReset = false;
    }

    private void disableRenderer(Renderer r2, DefaultMediaClock r3) {
        if (this.primaryRenderer != r2) goto L5;
    L8:
        boolean r0 = true;
    L9:
        Assertions.checkState(r0);
        if (isRendererEnabled(r2) == true) goto L12;
        return;
    L12:
        r3.onRendererDisabled(r2);
        ensureStopped(r2);
        r2.disable();
        return;
    L5:
        if (this.secondaryRenderer == r2) goto L8;
        r0 = false;
        goto L9
    }

    private void ensureStopped(Renderer r2) {
        if (r2.getState() != 2) goto L6;
        r2.stop();
        return;
    }

    private static Format[] getFormats(ExoTrackSelection r4) {
        int r0 = 0;
        if (r4 == null) goto L5;
        int r1 = r4.length();
    L6:
        Format[] r2 = new Format[r1];
    L7:
        if (r0 >= r1) goto L9;
        r2[r0] = ((ExoTrackSelection) Assertions.checkNotNull(r4)).getFormat(r0);
        r0 = r0 + 1;
        goto L7
    L9:
        return r2;
    L5:
        r1 = 0;
        goto L6
    }

    private Renderer getRendererReadingFromPeriod(MediaPeriodHolder r5) {
        if (r5 != null) goto L5;
    L17:
        return null;
    L5:
        if (r5.sampleStreams[this.index] == null) goto L17;
        if (this.primaryRenderer.getStream() == r5.sampleStreams[this.index]) goto L10;
        Renderer r1 = this.secondaryRenderer;
        if (r1 == null) goto L17;
        if (r1.getStream() != r5.sampleStreams[this.index]) goto L17;
        return this.secondaryRenderer;
    L10:
        return this.primaryRenderer;
    }

    private boolean hasFinishedReadingFromPeriodInternal(MediaPeriodHolder r4, Renderer r5) {
        if (r5 != null) goto L5;
        return true;
    L5:
        SampleStream r1 = r4.sampleStreams[this.index];
        if (r5.getStream() != null) goto L8;
    L21:
        return true;
    L8:
        if (r5.getStream() != r1) goto L14;
        if (r1 == null) goto L21;
        if (r5.hasReadStreamToEnd() == true) goto L21;
        if (hasReachedServerSideInsertedAdsTransition(r5, r4) == true) goto L21;
    L14:
        MediaPeriodHolder r42 = r4.getNext();
        if (r42 != null) goto L17;
        return false;
    L17:
        if (r42.sampleStreams[this.index] != r5.getStream()) goto L22;
        return true;
    L22:
        return false;
    }

    private boolean hasReachedServerSideInsertedAdsTransition(Renderer r3, MediaPeriodHolder r4) {
        MediaPeriodHolder r2 = r4.getNext();
        if (r4.info.isFollowedByTransitionToSameStream == false) goto L15;
        if (r2 != null) goto L6;
        return false;
    L6:
        if (r2.prepared == true) goto L8;
        return false;
    L8:
        if ((r3 instanceof TextRenderer) == false) goto L10;
        return true;
    L10:
        if ((r3 instanceof MetadataRenderer) == false) goto L12;
        return true;
    L12:
        if (r3.getReadingPositionUs() < r2.getStartPositionRendererTime()) goto L21;
        return true;
    L21:
        return false;
    L15:
        return false;
    }

    private boolean isPrimaryRendererPrewarming() {
        int r1 = this.prewarmingState;
        if (r1 != 2) goto L5;
        return true;
    L5:
        if (r1 == 4) goto L11;
        return false;
    L11:
        return true;
    }

    private static boolean isRendererEnabled(Renderer r0) {
        if (r0.getState() == 0) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isSecondaryRendererPrewarming() {
        if (this.prewarmingState != 3) goto L6;
        return true;
    L6:
        return false;
    }

    private void maybeDisableOrResetPositionInternal(Renderer r2, SampleStream r3, DefaultMediaClock r4, long r5, boolean r7) {
        if (isRendererEnabled(r2) == true) goto L5;
        return;
    L5:
        if (r3 == r2.getStream()) goto L8;
        disableRenderer(r2, r4);
        return;
    L8:
        if (r7 == false) goto L12;
        r2.resetPosition(r5);
        return;
    }

    private void maybeResetRenderer(boolean r2) {
        if (r2 == false) goto L9;
        if (this.primaryRequiresReset == false) goto L12;
        this.primaryRenderer.reset();
        this.primaryRequiresReset = false;
        return;
    L12:
        return;
    L9:
        if (this.secondaryRequiresReset == false) goto L13;
        ((Renderer) Assertions.checkNotNull(this.secondaryRenderer)).reset();
        this.secondaryRequiresReset = false;
        return;
    }

    private int replaceStreamsOrDisableRendererForTransitionInternal(Renderer r9, MediaPeriodHolder r10, TrackSelectorResult r11, DefaultMediaClock r12) {
        if (r9 != null) goto L5;
    L39:
        return 1;
    L5:
        if (isRendererEnabled(r9) == false) goto L39;
        if (r9 != this.primaryRenderer) goto L11;
        if (isPrimaryRendererPrewarming() == true) goto L39;
    L11:
        if (r9 == this.secondaryRenderer) goto L13;
    L15:
        SampleStream r1 = r9.getStream();
        SampleStream[] r2 = r10.sampleStreams;
        int r3 = this.index;
        boolean r4 = false;
        if (r1 == r2[r3]) goto L18;
        boolean r13 = true;
    L19:
        boolean r22 = r11.isRendererEnabled(r3);
        if (r22 == false) goto L24;
        if (r13 == true) goto L24;
        return 1;
    L24:
        if (r9.isCurrentStreamFinal() == true) goto L28;
        r9.replaceStream(getFormats(r11.selections[this.index]), (SampleStream) Assertions.checkNotNull(r10.sampleStreams[this.index]), r10.getStartPositionRendererTime(), r10.getRendererOffset(), r10.info.id);
        return 3;
    L28:
        if (r9.isEnded() == false) goto L38;
        disableRenderer(r9, r12);
        if (r22 == false) goto L34;
        if (isPrewarming() == true) goto L34;
    L37:
        return 1;
    L34:
        if (r9 != this.primaryRenderer) goto L36;
        r4 = true;
    L36:
        maybeResetRenderer(r4);
        goto L37
    L38:
        return 0;
    L18:
        r13 = false;
        goto L19
    L13:
        if (isSecondaryRendererPrewarming() == false) goto L15;
        goto L15
    }

    private void setCurrentStreamFinalInternal(Renderer r1, long r2) {
        r1.setCurrentStreamFinal();
        if ((r1 instanceof TextRenderer) == false) goto L6;
        ((TextRenderer) r1).setFinalStreamEndPositionUs(r2);
        return;
    }

    private void transferResources(boolean r2) {
        if (r2 == false) goto L6;
        ((Renderer) Assertions.checkNotNull(this.secondaryRenderer)).handleMessage(17, this.primaryRenderer);
        return;
    L6:
        this.primaryRenderer.handleMessage(17, Assertions.checkNotNull(this.secondaryRenderer));
    }

    public boolean allowsPlayback(MediaPeriodHolder r1) {
        Renderer r0 = getRendererReadingFromPeriod(r1);
        if (r0 != null) goto L5;
        return true;
    L5:
        if (r0.hasReadStreamToEnd() == false) goto L7;
        return true;
    L7:
        if (r0.isReady() == false) goto L9;
        return true;
    L9:
        if (r0.isEnded() == true) goto L17;
        return false;
    L17:
        return true;
    }

    public void disable(DefaultMediaClock r5) {
        disableRenderer(this.primaryRenderer, r5);
        Renderer r0 = this.secondaryRenderer;
        if (r0 != null) goto L5;
    L13:
        this.prewarmingState = 0;
        return;
    L5:
        if (isRendererEnabled(r0) == true) goto L7;
    L9:
        boolean r02 = false;
    L10:
        disableRenderer(this.secondaryRenderer, r5);
        maybeResetRenderer(false);
        if (r02 == false) goto L13;
        transferResources(true);
        goto L13
    L7:
        if (this.prewarmingState == 3) goto L9;
        r02 = true;
        goto L10
    }

    public void disablePrewarming(DefaultMediaClock r6) {
        if (isPrewarming() == true) goto L5;
        return;
    L5:
        int r0 = this.prewarmingState;
        int r1 = 1;
        if (r0 != 4) goto L8;
    L11:
        boolean r4 = true;
    L12:
        if (r0 == 4) goto L15;
        r1 = 0;
    L15:
        if (r4 == false) goto L17;
        Renderer r02 = this.primaryRenderer;
    L18:
        disableRenderer(r02, r6);
        maybeResetRenderer(r4);
        this.prewarmingState = r1;
        return;
    L17:
        r02 = (Renderer) Assertions.checkNotNull(this.secondaryRenderer);
        goto L18
    L8:
        if (r0 == 2) goto L11;
        r4 = false;
        goto L12
    }

    public void enable(RendererConfiguration r15, ExoTrackSelection r16, SampleStream r17, long r18, boolean r20, boolean r21, long r22, long r24, MediaSource.MediaPeriodId r26, DefaultMediaClock r27) {
        Format[] r3 = getFormats(r16);
        int r1 = this.prewarmingState;
        if (r1 != 0) goto L5;
    L11:
        this.primaryRequiresReset = true;
        this.primaryRenderer.enable(r15, r3, r17, r18, r20, r21, r22, r24, r26);
        r27.onRendererEnabled(this.primaryRenderer);
        return;
    L5:
        if (r1 == 2) goto L11;
        if (r1 == 4) goto L11;
        this.secondaryRequiresReset = true;
        ((Renderer) Assertions.checkNotNull(this.secondaryRenderer)).enable(r15, r3, r17, r18, r20, r21, r22, r24, r26);
        r27.onRendererEnabled(this.secondaryRenderer);
    }

    public void enableMayRenderStartOfStream() {
        if (isRendererEnabled(this.primaryRenderer) == false) goto L6;
        this.primaryRenderer.enableMayRenderStartOfStream();
        return;
    L6:
        Renderer r0 = this.secondaryRenderer;
        if (r0 != null) goto L9;
        return;
    L9:
        if (isRendererEnabled(r0) == false) goto L13;
        this.secondaryRenderer.enableMayRenderStartOfStream();
        return;
    }

    public int getEnabledRendererCount() {
        boolean r0 = isRendererEnabled(this.primaryRenderer);
        Renderer r1 = this.secondaryRenderer;
        if (r1 != null) goto L5;
    L7:
        int r12 = 0;
    L9:
        return (r0 ? 1 : 0) + r12;
    L5:
        if (isRendererEnabled(r1) == false) goto L7;
        r12 = 1;
        goto L9
    }

    public long getMinDurationToProgressUs(long r4, long r6) {
        if (isRendererEnabled(this.primaryRenderer) == false) goto L5;
        long r0 = this.primaryRenderer.getDurationToProgressUs(r4, r6);
    L6:
        Renderer r2 = this.secondaryRenderer;
        if (r2 != null) goto L9;
    L12:
        return r0;
    L9:
        if (isRendererEnabled(r2) == false) goto L12;
        return Math.min(r0, this.secondaryRenderer.getDurationToProgressUs(r4, r6));
    L5:
        r0 = LongCompanionObject.MAX_VALUE;
        goto L6
    }

    public long getReadingPositionUs(MediaPeriodHolder r1) {
        Renderer r0 = getRendererReadingFromPeriod(r1);
        Objects.requireNonNull(r0);
        return r0.getReadingPositionUs();
    }

    public int getTrackType() {
        return this.primaryRenderer.getTrackType();
    }

    public void handleMessage(int r1, Object r2, MediaPeriodHolder r3) {
        ((Renderer) Assertions.checkNotNull(getRendererReadingFromPeriod(r3))).handleMessage(r1, r2);
    }

    public boolean hasFinishedReadingFromPeriod(MediaPeriodHolder r2) {
        if (hasFinishedReadingFromPeriodInternal(r2, this.primaryRenderer) == true) goto L5;
        return false;
    L5:
        if (hasFinishedReadingFromPeriodInternal(r2, this.secondaryRenderer) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean hasReadPeriodToEnd(MediaPeriodHolder r1) {
        return ((Renderer) Assertions.checkNotNull(getRendererReadingFromPeriod(r1))).hasReadStreamToEnd();
    }

    public boolean hasSecondary() {
        if (this.secondaryRenderer == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isEnded() {
        if (isRendererEnabled(this.primaryRenderer) == false) goto L5;
        boolean r0 = this.primaryRenderer.isEnded();
    L6:
        Renderer r1 = this.secondaryRenderer;
        if (r1 != null) goto L9;
    L12:
        return r0;
    L9:
        if (isRendererEnabled(r1) == false) goto L12;
        return this.secondaryRenderer.isEnded() & r0;
    L5:
        r0 = true;
        goto L6
    }

    public boolean isPrewarming() {
        if (isPrimaryRendererPrewarming() == false) goto L5;
        return true;
    L5:
        if (isSecondaryRendererPrewarming() == true) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean isPrewarmingPeriod(MediaPeriodHolder r5) {
        if (isPrimaryRendererPrewarming() == true) goto L5;
    L7:
        boolean r0 = false;
    L9:
        if (isSecondaryRendererPrewarming() == true) goto L11;
    L13:
        boolean r4 = false;
    L14:
        if (r0 == true) goto L18;
        if (r4 == true) goto L18;
        return false;
    L18:
        return true;
    L11:
        if (getRendererReadingFromPeriod(r5) != this.secondaryRenderer) goto L13;
        r4 = true;
        goto L14
    L5:
        if (getRendererReadingFromPeriod(r5) != this.primaryRenderer) goto L7;
        r0 = true;
        goto L9
    }

    public boolean isReadingFromPeriod(MediaPeriodHolder r1) {
        if (getRendererReadingFromPeriod(r1) == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isRendererEnabled() {
        int r0 = this.prewarmingState;
        if (r0 == 0) goto L12;
        if (r0 == 2) goto L12;
        if (r0 == 4) goto L12;
        return isRendererEnabled((Renderer) Assertions.checkNotNull(this.secondaryRenderer));
    L12:
        return isRendererEnabled(this.primaryRenderer);
    }

    public void maybeDisableOrResetPosition(SampleStream r10, DefaultMediaClock r11, long r12, boolean r14) {
        maybeDisableOrResetPositionInternal(this.primaryRenderer, r10, r11, r12, r14);
        Renderer r9 = this.secondaryRenderer;
        if (r9 == null) goto L6;
        maybeDisableOrResetPositionInternal(r9, r10, r11, r12, r14);
        return;
    }

    public void maybeHandlePrewarmingTransition() {
        int r0 = this.prewarmingState;
        int r2 = 0;
        if (r0 == 3) goto L11;
        if (r0 == 4) goto L11;
        if (r0 != 2) goto L20;
        this.prewarmingState = 0;
        return;
    L20:
        return;
    L11:
        if (r0 != 4) goto L13;
        boolean r02 = true;
    L14:
        transferResources(r02);
        if (this.prewarmingState == 4) goto L18;
        r2 = 1;
    L18:
        this.prewarmingState = r2;
        return;
    L13:
        r02 = false;
        goto L14
    }

    public void maybeSetOldStreamToFinal(TrackSelectorResult r5, TrackSelectorResult r6, long r7) {
        boolean r0 = r5.isRendererEnabled(this.index);
        boolean r1 = r6.isRendererEnabled(this.index);
        if (this.secondaryRenderer == null) goto L11;
        int r2 = this.prewarmingState;
        if (r2 == 3) goto L11;
        if (r2 == 0) goto L8;
    L10:
        Renderer r22 = (Renderer) Assertions.checkNotNull(this.secondaryRenderer);
    L12:
        if (r0 == true) goto L14;
        return;
    L14:
        if (r22.isCurrentStreamFinal() == false) goto L16;
        return;
    L16:
        if (getTrackType() != (-2)) goto L18;
        boolean r02 = true;
    L19:
        RendererConfiguration[] r52 = r5.rendererConfigurations;
        int r3 = this.index;
        RendererConfiguration r53 = r52[r3];
        RendererConfiguration r62 = r6.rendererConfigurations[r3];
        if (r1 == true) goto L22;
    L26:
        setCurrentStreamFinalInternal(r22, r7);
        return;
    L22:
        if (Objects.equals(r62, r53) == false) goto L26;
        if (r02 == true) goto L26;
        if (isPrewarming() == true) goto L26;
        return;
    L18:
        r02 = false;
        goto L19
    L8:
        if (isRendererEnabled(this.primaryRenderer) == false) goto L10;
    L11:
        r22 = this.primaryRenderer;
        goto L12
    }

    public void maybeThrowStreamError(MediaPeriodHolder r1) {
        ((Renderer) Assertions.checkNotNull(getRendererReadingFromPeriod(r1))).maybeThrowStreamError();
    }

    public void release() {
        this.primaryRenderer.release();
        this.primaryRequiresReset = false;
        Renderer r1 = this.secondaryRenderer;
        if (r1 == null) goto L6;
        r1.release();
        this.secondaryRequiresReset = false;
        return;
    }

    public void render(long r2, long r4) {
        if (isRendererEnabled(this.primaryRenderer) == false) goto L5;
        this.primaryRenderer.render(r2, r4);
    L5:
        Renderer r0 = this.secondaryRenderer;
        if (r0 != null) goto L8;
        return;
    L8:
        if (isRendererEnabled(r0) == false) goto L12;
        this.secondaryRenderer.render(r2, r4);
        return;
    }

    public int replaceStreamsOrDisableRendererForTransition(MediaPeriodHolder r3, TrackSelectorResult r4, DefaultMediaClock r5) {
        int r0 = replaceStreamsOrDisableRendererForTransitionInternal(this.primaryRenderer, r3, r4, r5);
        int r2 = replaceStreamsOrDisableRendererForTransitionInternal(this.secondaryRenderer, r3, r4, r5);
        if (r0 != 1) goto L5;
        return r2;
    L5:
        return r0;
    }

    public void reset() {
        if (isRendererEnabled(this.primaryRenderer) == true) goto L5;
        maybeResetRenderer(true);
    L5:
        Renderer r0 = this.secondaryRenderer;
        if (r0 != null) goto L8;
        return;
    L8:
        if (isRendererEnabled(r0) == true) goto L12;
        maybeResetRenderer(false);
        return;
    }

    public void resetPosition(MediaPeriodHolder r1, long r2) {
        Renderer r0 = getRendererReadingFromPeriod(r1);
        if (r0 == null) goto L6;
        r0.resetPosition(r2);
        return;
    }

    public void setAllNonPrewarmingRendererStreamsFinal(long r3) {
        if (isRendererEnabled(this.primaryRenderer) == false) goto L9;
        int r0 = this.prewarmingState;
        if (r0 == 4) goto L9;
        if (r0 == 2) goto L9;
        setCurrentStreamFinalInternal(this.primaryRenderer, r3);
    L9:
        Renderer r02 = this.secondaryRenderer;
        if (r02 != null) goto L12;
        return;
    L12:
        if (isRendererEnabled(r02) == true) goto L14;
        return;
    L14:
        if (this.prewarmingState == 3) goto L19;
        setCurrentStreamFinalInternal(this.secondaryRenderer, r3);
        return;
    }

    public void setCurrentStreamFinal(MediaPeriodHolder r1, long r2) {
        setCurrentStreamFinalInternal((Renderer) Assertions.checkNotNull(getRendererReadingFromPeriod(r1)), r2);
    }

    public void setPlaybackSpeed(float r2, float r3) {
        this.primaryRenderer.setPlaybackSpeed(r2, r3);
        Renderer r1 = this.secondaryRenderer;
        if (r1 == null) goto L6;
        r1.setPlaybackSpeed(r2, r3);
        return;
    }

    public void setScrubbingMode(ScrubbingModeParameters r3) {
        this.primaryRenderer.handleMessage(18, r3);
        Renderer r2 = this.secondaryRenderer;
        if (r2 == null) goto L6;
        r2.handleMessage(18, r3);
        return;
    }

    public void setTimeline(Timeline r2) {
        this.primaryRenderer.setTimeline(r2);
        Renderer r1 = this.secondaryRenderer;
        if (r1 == null) goto L6;
        r1.setTimeline(r2);
        return;
    }

    public void setVideoFrameMetadataListener(VideoFrameMetadataListener r3) {
        if (getTrackType() != 2) goto L10;
        this.primaryRenderer.handleMessage(7, r3);
        Renderer r2 = this.secondaryRenderer;
        if (r2 == null) goto L9;
        r2.handleMessage(7, r3);
        return;
    L9:
        return;
    }

    public void setVideoOutput(Object r4) {
        if (getTrackType() == 2) goto L5;
        return;
    L5:
        int r0 = this.prewarmingState;
        if (r0 == 4) goto L11;
        if (r0 == 1) goto L11;
        this.primaryRenderer.handleMessage(1, r4);
        return;
    L11:
        ((Renderer) Assertions.checkNotNull(this.secondaryRenderer)).handleMessage(1, r4);
    }

    public void setVolume(float r4) {
        if (getTrackType() != 1) goto L10;
        this.primaryRenderer.handleMessage(2, Float.valueOf(r4));
        Renderer r3 = this.secondaryRenderer;
        if (r3 == null) goto L9;
        r3.handleMessage(2, Float.valueOf(r4));
        return;
    L9:
        return;
    }

    public void start() {
        if (this.primaryRenderer.getState() == 1) goto L5;
    L8:
        Renderer r0 = this.secondaryRenderer;
        if (r0 != null) goto L11;
        return;
    L11:
        if (r0.getState() == 1) goto L13;
        return;
    L13:
        if (this.prewarmingState == 3) goto L18;
        this.secondaryRenderer.start();
        return;
    L18:
        return;
    L5:
        if (this.prewarmingState == 4) goto L8;
        this.primaryRenderer.start();
    }

    public void startPrewarming() {
        Assertions.checkState(!isPrewarming());
        if (isRendererEnabled(this.primaryRenderer) == false) goto L5;
        int r0 = 3;
    L11:
        this.prewarmingState = r0;
        return;
    L5:
        Renderer r02 = this.secondaryRenderer;
        if (r02 != null) goto L8;
    L10:
        r0 = 2;
        goto L11
    L8:
        if (isRendererEnabled(r02) == false) goto L10;
        r0 = 4;
        goto L11
    }

    public void stop() {
        if (isRendererEnabled(this.primaryRenderer) == false) goto L5;
        ensureStopped(this.primaryRenderer);
    L5:
        Renderer r0 = this.secondaryRenderer;
        if (r0 != null) goto L8;
        return;
    L8:
        if (isRendererEnabled(r0) == false) goto L12;
        ensureStopped(this.secondaryRenderer);
        return;
    }
}
