package androidx.media3.exoplayer;

import androidx.media3.common.Format;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.SampleStream;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes4.dex */
public abstract class BaseRenderer implements Renderer, RendererCapabilities {
    private Clock clock;
    private RendererConfiguration configuration;
    private final FormatHolder formatHolder;
    private int index;
    private long lastResetPositionUs;
    private final Object lock;
    private MediaSource.MediaPeriodId mediaPeriodId;
    private PlayerId playerId;
    private long readingPositionUs;
    private RendererCapabilities.Listener rendererCapabilitiesListener;
    private int state;
    private SampleStream stream;
    private Format[] streamFormats;
    private boolean streamIsFinal;
    private long streamOffsetUs;
    private boolean throwRendererExceptionIsExecuting;
    private Timeline timeline;
    private final int trackType;

    public BaseRenderer(int r3) {
        this.lock = new Object();
        this.trackType = r3;
        this.formatHolder = new FormatHolder();
        this.readingPositionUs = Long.MIN_VALUE;
        this.timeline = Timeline.EMPTY;
    }

    private void resetPosition(long r2, boolean r4) {
        this.streamIsFinal = false;
        this.lastResetPositionUs = r2;
        this.readingPositionUs = r2;
        onPositionReset(r2, r4);
    }

    @Override // androidx.media3.exoplayer.RendererCapabilities
    public final void clearListener() {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.rendererCapabilitiesListener = null;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public final ExoPlaybackException createRendererException(Throwable r2, Format r3, int r4) {
        return createRendererException(r2, r3, false, r4);
    }

    public final ExoPlaybackException createRendererException(Throwable r10, Format r11, boolean r12, int r13) {
        if (r11 != null) goto L4;
    L13:
        int r0 = 4;
    L8:
        int r5 = r0;
        return ExoPlaybackException.createForRenderer(r10, getName(), getIndex(), r11, r5, this.mediaPeriodId, r12, r13);
    L4:
        if (this.throwRendererExceptionIsExecuting == true) goto L13;
        this.throwRendererExceptionIsExecuting = true;
        r0 = RendererCapabilities.getFormatSupport(supportsFormat(r11));     // Catch: Throwable -> L9 ExoPlaybackException -> L12
        this.throwRendererExceptionIsExecuting = false;
        goto L8
    L12:
        this.throwRendererExceptionIsExecuting = false;
    L9:
        th = move-exception;
        this.throwRendererExceptionIsExecuting = false;
        throw th;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void disable() {
        boolean r2 = true;
        if (this.state == 1) goto L6;
        r2 = false;
    L6:
        Assertions.checkState(r2);
        this.formatHolder.clear();
        this.state = 0;
        this.stream = null;
        this.streamFormats = null;
        this.streamIsFinal = false;
        onDisabled();
        this.mediaPeriodId = null;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void enable(RendererConfiguration r9, Format[] r10, SampleStream r11, long r12, boolean r14, boolean r15, long r16, long r18, MediaSource.MediaPeriodId r20) {
        if (this.state != 0) goto L5;
        boolean r122 = true;
    L6:
        Assertions.checkState(r122);
        this.configuration = r9;
        this.mediaPeriodId = r20;
        this.state = 1;
        onEnabled(r14, r15);
        replaceStream(r10, r11, r16, r18, r20);
        resetPosition(r16, r14);
        return;
    L5:
        r122 = false;
        goto L6
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final RendererCapabilities getCapabilities() {
        return this;
    }

    public final Clock getClock() {
        return (Clock) Assertions.checkNotNull(this.clock);
    }

    public final RendererConfiguration getConfiguration() {
        return (RendererConfiguration) Assertions.checkNotNull(this.configuration);
    }

    public final FormatHolder getFormatHolder() {
        this.formatHolder.clear();
        return this.formatHolder;
    }

    public final int getIndex() {
        return this.index;
    }

    public final long getLastResetPositionUs() {
        return this.lastResetPositionUs;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public MediaClock getMediaClock() {
        return null;
    }

    public final PlayerId getPlayerId() {
        return (PlayerId) Assertions.checkNotNull(this.playerId);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final long getReadingPositionUs() {
        return this.readingPositionUs;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final int getState() {
        return this.state;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final SampleStream getStream() {
        return this.stream;
    }

    public final Format[] getStreamFormats() {
        return (Format[]) Assertions.checkNotNull(this.streamFormats);
    }

    public final long getStreamOffsetUs() {
        return this.streamOffsetUs;
    }

    public final Timeline getTimeline() {
        return this.timeline;
    }

    @Override // androidx.media3.exoplayer.Renderer, androidx.media3.exoplayer.RendererCapabilities
    public final int getTrackType() {
        return this.trackType;
    }

    @Override // androidx.media3.exoplayer.PlayerMessage.Target
    public void handleMessage(int r1, Object r2) {
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final boolean hasReadStreamToEnd() {
        if (this.readingPositionUs != Long.MIN_VALUE) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void init(int r1, PlayerId r2, Clock r3) {
        this.index = r1;
        this.playerId = r2;
        this.clock = r3;
        onInit();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final boolean isCurrentStreamFinal() {
        return this.streamIsFinal;
    }

    public final boolean isSourceReady() {
        if (hasReadStreamToEnd() == false) goto L7;
        return this.streamIsFinal;
    L7:
        return ((SampleStream) Assertions.checkNotNull(this.stream)).isReady();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void maybeThrowStreamError() {
        ((SampleStream) Assertions.checkNotNull(this.stream)).maybeThrowError();
    }

    public abstract void onDisabled();

    public void onEnabled(boolean r1, boolean r2) {
    }

    public void onInit() {
    }

    public abstract void onPositionReset(long r1, boolean r3);

    public void onRelease() {
    }

    public final void onRendererCapabilitiesChanged() {
        Object r0 = this.lock;
        monitor-enter(r0);
        RendererCapabilities.Listener r1 = this.rendererCapabilitiesListener;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        if (r1 == null) goto L14;
        r1.onRendererCapabilitiesChanged(this);
        return;
    L14:
        return;
    L9:
        th = move-exception;
        throw th;
    }

    public void onReset() {
    }

    public void onStarted() {
    }

    public void onStopped() {
    }

    public void onStreamChanged(Format[] r1, long r2, long r4, MediaSource.MediaPeriodId r6) {
    }

    public void onTimelineChanged(Timeline r1) {
    }

    public final int readSource(FormatHolder r6, DecoderInputBuffer r7, int r8) {
        int r82 = ((SampleStream) Assertions.checkNotNull(this.stream)).readData(r6, r7, r8);
        if (r82 != (-4)) goto L14;
        if (r7.isEndOfStream() == false) goto L11;
        this.readingPositionUs = Long.MIN_VALUE;
        if (this.streamIsFinal == false) goto L9;
        return -4;
    L9:
        return -3;
    L11:
        long r0 = r7.timeUs + this.streamOffsetUs;
        r7.timeUs = r0;
        this.readingPositionUs = Math.max(this.readingPositionUs, r0);
        return r82;
    L14:
        if (r82 != (-5)) goto L18;
        Format r72 = (Format) Assertions.checkNotNull(r6.format);
        if (r72.subsampleOffsetUs == LongCompanionObject.MAX_VALUE) goto L18;
        r6.format = r72.buildUpon().setSubsampleOffsetUs(r72.subsampleOffsetUs + this.streamOffsetUs).build();
    L18:
        return r82;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void release() {
        if (this.state != 0) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        onRelease();
        return;
    L5:
        r0 = false;
        goto L6
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void replaceStream(Format[] r8, SampleStream r9, long r10, long r12, MediaSource.MediaPeriodId r14) {
        Assertions.checkState(!this.streamIsFinal);
        this.stream = r9;
        this.mediaPeriodId = r14;
        if (this.readingPositionUs != Long.MIN_VALUE) goto L5;
        this.readingPositionUs = r10;
    L5:
        this.streamFormats = r8;
        this.streamOffsetUs = r12;
        onStreamChanged(r8, r10, r12, r14);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void reset() {
        if (this.state != 0) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        this.formatHolder.clear();
        onReset();
        return;
    L5:
        r0 = false;
        goto L6
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void resetPosition(long r2) {
        resetPosition(r2, false);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void setCurrentStreamFinal() {
        this.streamIsFinal = true;
    }

    @Override // androidx.media3.exoplayer.RendererCapabilities
    public final void setListener(RendererCapabilities.Listener r2) {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.rendererCapabilitiesListener = r2;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void setTimeline(Timeline r2) {
        if (Objects.equals(this.timeline, r2) == true) goto L6;
        this.timeline = r2;
        onTimelineChanged(r2);
        return;
    }

    public int skipSource(long r4) {
        return ((SampleStream) Assertions.checkNotNull(this.stream)).skipData(r4 - this.streamOffsetUs);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void start() {
        boolean r1 = true;
        if (this.state == 1) goto L6;
        r1 = false;
    L6:
        Assertions.checkState(r1);
        this.state = 2;
        onStarted();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void stop() {
        if (this.state != 2) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        this.state = 1;
        onStopped();
        return;
    L5:
        r0 = false;
        goto L6
    }

    public int supportsMixedMimeTypeAdaptation() {
        return 0;
    }
}
