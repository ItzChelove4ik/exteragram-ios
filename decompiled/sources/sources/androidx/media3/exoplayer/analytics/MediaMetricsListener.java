package androidx.media3.exoplayer.analytics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.DeniedByServerException;
import android.media.MediaCodec;
import android.media.MediaDrm;
import android.media.MediaDrmResetException;
import android.media.NotProvisionedException;
import android.media.metrics.LogSessionId;
import android.media.metrics.MediaMetricsManager;
import android.media.metrics.NetworkEvent;
import android.media.metrics.PlaybackErrorEvent;
import android.media.metrics.PlaybackMetrics;
import android.media.metrics.PlaybackSession;
import android.media.metrics.PlaybackStateEvent;
import android.media.metrics.TrackChangeEvent;
import android.os.SystemClock;
import android.system.ErrnoException;
import android.system.OsConstants;
import android.util.Pair;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.ParserException;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BackgroundExecutor;
import androidx.media3.common.util.NetworkTypeObserver;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.FileDataSource;
import androidx.media3.datasource.HttpDataSource$HttpDataSourceException;
import androidx.media3.datasource.HttpDataSource$InvalidContentTypeException;
import androidx.media3.datasource.HttpDataSource$InvalidResponseCodeException;
import androidx.media3.datasource.UdpDataSource;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.analytics.PlaybackSessionManager;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.drm.DefaultDrmSessionManager;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.drm.UnsupportedDrmException;
import androidx.media3.exoplayer.mediacodec.MediaCodecDecoderException;
import androidx.media3.exoplayer.mediacodec.MediaCodecRenderer;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaSource;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes4.dex */
public final class MediaMetricsListener implements AnalyticsListener, PlaybackSessionManager.Listener {
    private String activeSessionId;
    private int audioUnderruns;
    private final Executor backgroundExecutor;
    private final HashMap<String, Long> bandwidthBytes;
    private final HashMap<String, Long> bandwidthTimeMs;
    private final Context context;
    private Format currentAudioFormat;
    private int currentNetworkType;
    private int currentPlaybackState;
    private Format currentTextFormat;
    private Format currentVideoFormat;
    private int discontinuityReason;
    private int droppedFrames;
    private boolean hasFatalError;
    private int ioErrorType;
    private boolean isSeeking;
    private PlaybackMetrics.Builder metricsBuilder;
    private PendingFormatUpdate pendingAudioFormat;
    private PlaybackException pendingPlayerError;
    private PendingFormatUpdate pendingTextFormat;
    private PendingFormatUpdate pendingVideoFormat;
    private final Timeline.Period period;
    private final PlaybackSession playbackSession;
    private int playedFrames;
    private boolean reportedEventsForCurrentSession;
    private final PlaybackSessionManager sessionManager;
    private final long startTimeMs;
    private final Timeline.Window window;

    public static final class ErrorInfo {
        public final int errorCode;
        public final int subErrorCode;

        public ErrorInfo(int r1, int r2) {
            this.errorCode = r1;
            this.subErrorCode = r2;
        }
    }

    public static final class PendingFormatUpdate {
        public final Format format;
        public final int selectionReason;
        public final String sessionId;

        public PendingFormatUpdate(Format r1, int r2, String r3) {
            this.format = r1;
            this.selectionReason = r2;
            this.sessionId = r3;
        }
    }

    public static /* synthetic */ void $r8$lambda$IgQl2PoRf4O5l7uPuXmlRgNkm8s(MediaMetricsListener r0, PlaybackErrorEvent r1) {
        r0.playbackSession.reportPlaybackErrorEvent(r1);
    }

    /* renamed from: $r8$lambda$Ln4o32G-snHjxLveqPrb6nd4DFU, reason: not valid java name */
    public static /* synthetic */ void m819$r8$lambda$Ln4o32GsnHjxLveqPrb6nd4DFU(MediaMetricsListener r0, PlaybackMetrics r1) {
        r0.playbackSession.reportPlaybackMetrics(r1);
    }

    /* renamed from: $r8$lambda$Z90VT2CXYz5ZKj6hhHP5A-mdjkg, reason: not valid java name */
    public static /* synthetic */ void m820$r8$lambda$Z90VT2CXYz5ZKj6hhHP5Amdjkg(MediaMetricsListener r0, NetworkEvent r1) {
        r0.playbackSession.reportNetworkEvent(r1);
    }

    public static /* synthetic */ void $r8$lambda$bTOys6EHiq4ZkLBqYbkbW1ptmaY(MediaMetricsListener r0, TrackChangeEvent r1) {
        r0.playbackSession.reportTrackChangeEvent(r1);
    }

    public static /* synthetic */ void $r8$lambda$btN0ToBG2PTYruOL2GpGKQdslu8(MediaMetricsListener r0, PlaybackStateEvent r1) {
        r0.playbackSession.reportPlaybackStateEvent(r1);
    }

    private MediaMetricsListener(Context r1, PlaybackSession r2) {
        this.context = r1.getApplicationContext();
        this.playbackSession = r2;
        this.backgroundExecutor = BackgroundExecutor.get();
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        this.bandwidthBytes = new HashMap();
        this.bandwidthTimeMs = new HashMap();
        this.startTimeMs = SystemClock.elapsedRealtime();
        this.currentPlaybackState = 0;
        this.currentNetworkType = 0;
        DefaultPlaybackSessionManager r12 = new DefaultPlaybackSessionManager();
        this.sessionManager = r12;
        r12.setListener(this);
    }

    @EnsuresNonNullIf(expression = {"#1"}, result = true)
    private boolean canReportPendingFormatUpdate(PendingFormatUpdate r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.sessionId.equals(this.sessionManager.getActiveSessionId()) == false) goto L9;
        return true;
    L9:
        return false;
    }

    public static MediaMetricsListener create(Context r2) {
        MediaMetricsManager r0 = MediaMetricsListener$$ExternalSyntheticApiModelOutline6.m(r2.getSystemService("media_metrics"));
        if (r0 != null) goto L7;
        return null;
    L7:
        return new MediaMetricsListener(r2, r0.createPlaybackSession());
    }

    private void finishCurrentSession() {
        PlaybackMetrics.Builder r0 = this.metricsBuilder;
        if (r0 != null) goto L5;
    L21:
        this.metricsBuilder = null;
        this.activeSessionId = null;
        this.audioUnderruns = 0;
        this.droppedFrames = 0;
        this.playedFrames = 0;
        this.currentVideoFormat = null;
        this.currentAudioFormat = null;
        this.currentTextFormat = null;
        this.reportedEventsForCurrentSession = false;
        return;
    L5:
        if (this.reportedEventsForCurrentSession == false) goto L21;
        r0.setAudioUnderrunCount(this.audioUnderruns);
        this.metricsBuilder.setVideoFramesDropped(this.droppedFrames);
        this.metricsBuilder.setVideoFramesPlayed(this.playedFrames);
        Long r02 = this.bandwidthTimeMs.get(this.activeSessionId);
        PlaybackMetrics.Builder r2 = this.metricsBuilder;
        if (r02 != null) goto L9;
        long r5 = 0;
    L10:
        r2.setNetworkTransferDurationMillis(r5);
        Long r03 = this.bandwidthBytes.get(this.activeSessionId);
        PlaybackMetrics.Builder r22 = this.metricsBuilder;
        if (r03 != null) goto L13;
        long r52 = 0;
    L14:
        r22.setNetworkBytesRead(r52);
        PlaybackMetrics.Builder r23 = this.metricsBuilder;
        if (r03 != null) goto L17;
    L19:
        int r04 = 0;
    L20:
        r23.setStreamSource(r04);
        this.backgroundExecutor.execute(new MediaMetricsListener$$ExternalSyntheticLambda10(this, this.metricsBuilder.build()));
        goto L21
    L17:
        if (r03.longValue() <= 0) goto L19;
        r04 = 1;
        goto L20
    L13:
        r52 = r03.longValue();
        goto L14
    L9:
        r5 = r02.longValue();
        goto L10
    }

    @SuppressLint({"SwitchIntDef"})
    private static int getDrmErrorCode(int r0) {
        switch(Util.getErrorCodeForMediaDrmErrorCode(r0)) {
            case 6002: goto L12;
            case 6003: goto L10;
            case 6004: goto L8;
            case 6005: goto L6;
            default: goto L4;
        };
    L4:
        return 27;
    L6:
        return 26;
    L8:
        return 25;
    L10:
        return 28;
    L12:
        return 24;
    }

    private static DrmInitData getDrmInitData(ImmutableList<Tracks.Group> r3) {
        UnmodifiableIterator<Tracks.Group> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L14;
        Tracks.Group r0 = r32.next();
        int r1 = 0;
    L7:
        if (r1 >= r0.length) goto L4;
        if (r0.isTrackSelected(r1) == false) goto L13;
        DrmInitData r2 = r0.getTrackFormat(r1).drmInitData;
        if (r2 == null) goto L13;
        return r2;
    L13:
        r1 = r1 + 1;
        goto L7
    L14:
        return null;
    }

    private static int getDrmType(DrmInitData r3) {
        int r0 = 0;
    L4:
        if (r0 >= r3.schemeDataCount) goto L18;
        UUID r1 = r3.get(r0).uuid;
        if (r1.equals(C.WIDEVINE_UUID) == true) goto L7;
        if (r1.equals(C.PLAYREADY_UUID) == true) goto L11;
        if (r1.equals(C.CLEARKEY_UUID) == true) goto L15;
        r0 = r0 + 1;
        goto L4
    L15:
        return 6;
    L11:
        return 2;
    L7:
        return 3;
    L18:
        return 1;
    }

    private static ErrorInfo getErrorInfo(PlaybackException r8, Context r9, boolean r10) {
        if (r8.errorCode != 1001) goto L7;
        return new ErrorInfo(20, 0);
    L7:
        if ((r8 instanceof ExoPlaybackException) == false) goto L13;
        ExoPlaybackException r0 = (ExoPlaybackException) r8;
        if (r0.type != 1) goto L11;
        boolean r3 = true;
    L12:
        int r02 = r0.rendererFormatSupport;
    L14:
        Throwable r4 = (Throwable) Assertions.checkNotNull(r8.getCause());
        if ((r4 instanceof IOException) == true) goto L17;
        if (r3 == false) goto L106;
        if (r02 == 0) goto L105;
        if (r02 != 1) goto L106;
    L105:
        return new ErrorInfo(35, 0);
    L106:
        if (r3 == false) goto L110;
        if (r02 != 3) goto L110;
        return new ErrorInfo(15, 0);
    L110:
        if (r3 == false) goto L116;
        if (r02 != 2) goto L116;
        return new ErrorInfo(23, 0);
    L116:
        if ((r4 instanceof MediaCodecRenderer.DecoderInitializationException) == false) goto L120;
        return new ErrorInfo(13, Util.getErrorCodeFromPlatformDiagnosticsInfo(((MediaCodecRenderer.DecoderInitializationException) r4).diagnosticInfo));
    L120:
        if ((r4 instanceof MediaCodecDecoderException) == false) goto L124;
        return new ErrorInfo(14, ((MediaCodecDecoderException) r4).errorCode);
    L124:
        if ((r4 instanceof OutOfMemoryError) == false) goto L128;
        return new ErrorInfo(14, 0);
    L128:
        if ((r4 instanceof AudioSink.InitializationException) == false) goto L132;
        return new ErrorInfo(17, ((AudioSink.InitializationException) r4).audioTrackState);
    L132:
        if ((r4 instanceof AudioSink.WriteException) == false) goto L136;
        return new ErrorInfo(18, ((AudioSink.WriteException) r4).errorCode);
    L136:
        if ((r4 instanceof MediaCodec.CryptoException) == false) goto L140;
        int r82 = ((MediaCodec.CryptoException) r4).getErrorCode();
        return new ErrorInfo(getDrmErrorCode(r82), r82);
    L140:
        return new ErrorInfo(22, 0);
    L17:
        if ((r4 instanceof HttpDataSource$InvalidResponseCodeException) == false) goto L21;
        return new ErrorInfo(5, ((HttpDataSource$InvalidResponseCodeException) r4).responseCode);
    L21:
        if ((r4 instanceof HttpDataSource$InvalidContentTypeException) == true) goto L96;
        if ((r4 instanceof ParserException) == true) goto L96;
        boolean r102 = r4 instanceof HttpDataSource$HttpDataSourceException;
        if (r102 == true) goto L77;
        if ((r4 instanceof UdpDataSource.UdpDataSourceException) == true) goto L77;
        if (r8.errorCode != 1002) goto L35;
        return new ErrorInfo(21, 0);
    L35:
        if ((r4 instanceof DrmSession.DrmSessionException) == false) goto L63;
        Throwable r83 = (Throwable) Assertions.checkNotNull(r4.getCause());
        if ((r83 instanceof MediaDrm.MediaDrmStateException) == false) goto L41;
        int r84 = Util.getErrorCodeFromPlatformDiagnosticsInfo(((MediaDrm.MediaDrmStateException) r83).getDiagnosticInfo());
        return new ErrorInfo(getDrmErrorCode(r84), r84);
    L41:
        if ((r83 instanceof MediaDrmResetException) == false) goto L45;
        return new ErrorInfo(27, 0);
    L45:
        if ((r83 instanceof NotProvisionedException) == false) goto L49;
        return new ErrorInfo(24, 0);
    L49:
        if ((r83 instanceof DeniedByServerException) == false) goto L53;
        return new ErrorInfo(29, 0);
    L53:
        if ((r83 instanceof UnsupportedDrmException) == false) goto L57;
        return new ErrorInfo(23, 0);
    L57:
        if ((r83 instanceof DefaultDrmSessionManager.MissingSchemeDataException) == false) goto L61;
        return new ErrorInfo(28, 0);
    L61:
        return new ErrorInfo(30, 0);
    L63:
        if ((r4 instanceof FileDataSource.FileDataSourceException) == false) goto L75;
        if ((r4.getCause() instanceof FileNotFoundException) == false) goto L75;
        Throwable r85 = ((Throwable) Assertions.checkNotNull(r4.getCause())).getCause();
        if ((r85 instanceof ErrnoException) == false) goto L73;
        if (((ErrnoException) r85).errno != OsConstants.EACCES) goto L73;
        return new ErrorInfo(32, 0);
    L73:
        return new ErrorInfo(31, 0);
    L75:
        return new ErrorInfo(9, 0);
    L77:
        if (NetworkTypeObserver.getInstance(r9).getNetworkType() == 1) goto L79;
        Throwable r86 = r4.getCause();
        if ((r86 instanceof UnknownHostException) == false) goto L85;
        return new ErrorInfo(6, 0);
    L85:
        if ((r86 instanceof SocketTimeoutException) == true) goto L87;
        if (r102 == false) goto L94;
        if (((HttpDataSource$HttpDataSourceException) r4).type != 1) goto L94;
        return new ErrorInfo(4, 0);
    L94:
        return new ErrorInfo(8, 0);
    L87:
        return new ErrorInfo(7, 0);
    L79:
        return new ErrorInfo(3, 0);
    L96:
        if (r10 == false) goto L98;
        int r92 = 10;
    L100:
        return new ErrorInfo(r92, 0);
    L98:
        r92 = 11;
        goto L100
    L11:
        r3 = false;
        goto L12
    L13:
        r02 = 0;
        r3 = false;
        goto L14
    }

    private static Pair<String, String> getLanguageAndRegion(String r3) {
        String[] r32 = Util.split(r3, "-");
        String r0 = r32[0];
        if (r32.length < 2) goto L5;
        String r33 = r32[1];
    L7:
        return Pair.create(r0, r33);
    L5:
        r33 = null;
        goto L7
    }

    private static int getNetworkType(Context r0) {
        switch(NetworkTypeObserver.getInstance(r0).getNetworkType()) {
            case 0: goto L22;
            case 1: goto L20;
            case 2: goto L18;
            case 3: goto L16;
            case 4: goto L14;
            case 5: goto L12;
            case 6: goto L4;
            case 7: goto L10;
            case 8: goto L4;
            case 9: goto L8;
            case 10: goto L6;
            default: goto L4;
        };
    L4:
        return 1;
    L6:
        return 7;
    L8:
        return 8;
    L10:
        return 3;
    L12:
        return 6;
    L14:
        return 5;
    L16:
        return 4;
    L18:
        return 2;
    L20:
        return 9;
    L22:
        return 0;
    }

    private static int getStreamType(MediaItem r2) {
        MediaItem.LocalConfiguration r22 = r2.localConfiguration;
        if (r22 != null) goto L6;
        return 0;
    L6:
        int r23 = Util.inferContentTypeForUriAndMimeType(r22.uri, r22.mimeType);
        if (r23 != 0) goto L9;
        return 3;
    L9:
        if (r23 != 1) goto L11;
        return 5;
    L11:
        if (r23 == 2) goto L13;
        return 1;
    L13:
        return 4;
    }

    private static int getTrackChangeReason(int r3) {
        if (r3 != 1) goto L5;
        return 2;
    L5:
        if (r3 == 2) goto L10;
        if (r3 == 3) goto L8;
        return 1;
    L8:
        return 4;
    L10:
        return 3;
    }

    private void maybeAddSessions(AnalyticsListener.Events r6) {
        int r0 = 0;
    L4:
        if (r0 >= r6.size()) goto L13;
        int r1 = r6.get(r0);
        AnalyticsListener.EventTime r2 = r6.getEventTime(r1);
        if (r1 != 0) goto L8;
        this.sessionManager.updateSessionsWithTimelineChange(r2);
    L12:
        r0 = r0 + 1;
        goto L4
    L8:
        PlaybackSessionManager r3 = this.sessionManager;
        if (r1 != 11) goto L11;
        r3.updateSessionsWithDiscontinuity(r2, this.discontinuityReason);
        goto L12
    L11:
        r3.updateSessions(r2);
        goto L12
    }

    private void maybeReportNetworkChange(long r4) {
        int r0 = getNetworkType(this.context);
        if (r0 == this.currentNetworkType) goto L6;
        this.currentNetworkType = r0;
        NetworkEvent r42 = MediaMetricsListener$$ExternalSyntheticApiModelOutline1.m().setNetworkType(r0).setTimeSinceCreatedMillis(r4 - this.startTimeMs).build();
        this.backgroundExecutor.execute(new MediaMetricsListener$$ExternalSyntheticLambda8(this, r42));
        return;
    }

    private void maybeReportPlaybackError(long r8) {
        PlaybackException r0 = this.pendingPlayerError;
        if (r0 != null) goto L5;
        return;
    L5:
        Context r1 = this.context;
        if (this.ioErrorType != 4) goto L8;
        boolean r2 = true;
    L9:
        ErrorInfo r12 = getErrorInfo(r0, r1, r2);
        this.backgroundExecutor.execute(new MediaMetricsListener$$ExternalSyntheticLambda9(this, MediaMetricsListener$$ExternalSyntheticApiModelOutline3.m().setTimeSinceCreatedMillis(r8 - this.startTimeMs).setErrorCode(r12.errorCode).setSubErrorCode(r12.subErrorCode).setException(r0).build()));
        this.reportedEventsForCurrentSession = true;
        this.pendingPlayerError = null;
        return;
    L8:
        r2 = false;
        goto L9
    }

    private void maybeReportPlaybackStateChange(Player r4, AnalyticsListener.Events r5, long r6) {
        if (r4.getPlaybackState() == 2) goto L6;
        this.isSeeking = false;
    L6:
        if (r4.getPlayerError() != null) goto L9;
        this.hasFatalError = false;
    L11:
        int r42 = resolveNewPlaybackState(r4);
        if (this.currentPlaybackState == r42) goto L15;
        this.currentPlaybackState = r42;
        this.reportedEventsForCurrentSession = true;
        PlaybackStateEvent r43 = MediaMetricsListener$$ExternalSyntheticApiModelOutline4.m().setState(this.currentPlaybackState).setTimeSinceCreatedMillis(r6 - this.startTimeMs).build();
        this.backgroundExecutor.execute(new MediaMetricsListener$$ExternalSyntheticLambda11(this, r43));
        return;
    L15:
        return;
    L9:
        if (r5.contains(10) == false) goto L11;
        this.hasFatalError = true;
        goto L11
    }

    private void maybeReportTrackChanges(Player r4, AnalyticsListener.Events r5, long r6) {
        if (r5.contains(2) == false) goto L16;
        Tracks r42 = r4.getCurrentTracks();
        boolean r52 = r42.isTypeSelected(2);
        boolean r0 = r42.isTypeSelected(1);
        boolean r43 = r42.isTypeSelected(3);
        if (r52 == true) goto L9;
        if (r0 == true) goto L9;
        if (r43 == false) goto L16;
    L9:
        if (r52 == true) goto L11;
        maybeUpdateVideoFormat(r6, null, 0);
    L11:
        if (r0 == true) goto L13;
        maybeUpdateAudioFormat(r6, null, 0);
    L13:
        if (r43 == true) goto L16;
        maybeUpdateTextFormat(r6, null, 0);
    L16:
        if (canReportPendingFormatUpdate(this.pendingVideoFormat) == false) goto L21;
        PendingFormatUpdate r44 = this.pendingVideoFormat;
        Format r53 = r44.format;
        if (r53.height == (-1)) goto L21;
        maybeUpdateVideoFormat(r6, r53, r44.selectionReason);
        this.pendingVideoFormat = null;
    L21:
        if (canReportPendingFormatUpdate(this.pendingAudioFormat) == false) goto L24;
        PendingFormatUpdate r45 = this.pendingAudioFormat;
        maybeUpdateAudioFormat(r6, r45.format, r45.selectionReason);
        this.pendingAudioFormat = null;
    L24:
        if (canReportPendingFormatUpdate(this.pendingTextFormat) == false) goto L27;
        PendingFormatUpdate r46 = this.pendingTextFormat;
        maybeUpdateTextFormat(r6, r46.format, r46.selectionReason);
        this.pendingTextFormat = null;
        return;
    }

    private void maybeUpdateAudioFormat(long r7, Format r9, int r10) {
        if (Objects.equals(this.currentAudioFormat, r9) == false) goto L6;
        return;
    L6:
        if (this.currentAudioFormat != null) goto L9;
        if (r10 != 0) goto L9;
        r10 = 1;
    L9:
        this.currentAudioFormat = r9;
        reportTrackChangeEvent(0, r7, r9, r10);
    }

    private void maybeUpdateMetricsBuilderValues(Player r3, AnalyticsListener.Events r4) {
        if (r4.contains(0) == false) goto L8;
        AnalyticsListener.EventTime r0 = r4.getEventTime(0);
        if (this.metricsBuilder == null) goto L8;
        maybeUpdateTimelineMetadata(r0.timeline, r0.mediaPeriodId);
    L8:
        if (r4.contains(2) == false) goto L15;
        if (this.metricsBuilder == null) goto L15;
        DrmInitData r32 = getDrmInitData(r3.getCurrentTracks().getGroups());
        if (r32 == null) goto L15;
        MediaMetricsListener$$ExternalSyntheticApiModelOutline5.m(Util.castNonNull(this.metricsBuilder)).setDrmType(getDrmType(r32));
    L15:
        if (r4.contains(1011) == false) goto L18;
        this.audioUnderruns++;
        return;
    }

    private void maybeUpdateTextFormat(long r7, Format r9, int r10) {
        if (Objects.equals(this.currentTextFormat, r9) == false) goto L6;
        return;
    L6:
        if (this.currentTextFormat != null) goto L9;
        if (r10 != 0) goto L9;
        r10 = 1;
    L9:
        this.currentTextFormat = r9;
        reportTrackChangeEvent(2, r7, r9, r10);
    }

    @RequiresNonNull({"metricsBuilder"})
    private void maybeUpdateTimelineMetadata(Timeline r6, MediaSource.MediaPeriodId r7) {
        PlaybackMetrics.Builder r0 = this.metricsBuilder;
        if (r7 == null) goto L23;
        int r72 = r6.getIndexOfPeriod(r7.periodUid);
        if (r72 != (-1)) goto L8;
        return;
    L8:
        r6.getPeriod(r72, this.period);
        r6.getWindow(this.period.windowIndex, this.window);
        r0.setStreamType(getStreamType(this.window.mediaItem));
        Timeline.Window r62 = this.window;
        if (r62.durationUs == (-9223372036854775807L)) goto L18;
        if (r62.isPlaceholder == true) goto L18;
        if (r62.isDynamic == true) goto L18;
        if (r62.isLive() == true) goto L18;
        r0.setMediaDurationMillis(this.window.getDurationMs());
    L18:
        if (this.window.isLive() == false) goto L20;
        int r63 = 2;
    L21:
        r0.setPlaybackType(r63);
        this.reportedEventsForCurrentSession = true;
        return;
    L20:
        r63 = 1;
        goto L21
    }

    private void maybeUpdateVideoFormat(long r7, Format r9, int r10) {
        if (Objects.equals(this.currentVideoFormat, r9) == false) goto L6;
        return;
    L6:
        if (this.currentVideoFormat != null) goto L9;
        if (r10 != 0) goto L9;
        r10 = 1;
    L9:
        this.currentVideoFormat = r9;
        reportTrackChangeEvent(1, r7, r9, r10);
    }

    private void reportTrackChangeEvent(int r3, long r4, Format r6, int r7) {
        TrackChangeEvent.Builder r32 = MediaMetricsListener$$ExternalSyntheticApiModelOutline0.m(r3).setTimeSinceCreatedMillis(r4 - this.startTimeMs);
        if (r6 == null) goto L36;
        r32.setTrackState(1);
        r32.setTrackChangeReason(getTrackChangeReason(r7));
        String r5 = r6.containerMimeType;
        if (r5 == null) goto L7;
        r32.setContainerMimeType(r5);
    L7:
        String r52 = r6.sampleMimeType;
        if (r52 == null) goto L10;
        r32.setSampleMimeType(r52);
    L10:
        String r53 = r6.codecs;
        if (r53 == null) goto L13;
        r32.setCodecName(r53);
    L13:
        int r54 = r6.bitrate;
        if (r54 == (-1)) goto L16;
        r32.setBitrate(r54);
    L16:
        int r55 = r6.width;
        if (r55 == (-1)) goto L19;
        r32.setWidth(r55);
    L19:
        int r56 = r6.height;
        if (r56 == (-1)) goto L22;
        r32.setHeight(r56);
    L22:
        int r57 = r6.channelCount;
        if (r57 == (-1)) goto L25;
        r32.setChannelCount(r57);
    L25:
        int r58 = r6.sampleRate;
        if (r58 == (-1)) goto L28;
        r32.setAudioSampleRate(r58);
    L28:
        String r59 = r6.language;
        if (r59 == null) goto L33;
        Pair<String, String> r510 = getLanguageAndRegion(r59);
        r32.setLanguage((String) r510.first);
        Object r511 = r510.second;
        if (r511 == null) goto L33;
        r32.setLanguageRegion((String) r511);
    L33:
        float r512 = r6.frameRate;
        if (r512 == (-1.0f)) goto L37;
        r32.setVideoFrameRate(r512);
    L37:
        this.reportedEventsForCurrentSession = true;
        TrackChangeEvent r33 = r32.build();
        this.backgroundExecutor.execute(new MediaMetricsListener$$ExternalSyntheticLambda7(this, r33));
        return;
    L36:
        r32.setTrackState(0);
        goto L37
    }

    private int resolveNewPlaybackState(Player r5) {
        int r0 = r5.getPlaybackState();
        if (this.isSeeking == false) goto L7;
        return 5;
    L7:
        if (this.hasFatalError == false) goto L11;
        return 13;
    L11:
        if (r0 != 4) goto L15;
        return 11;
    L15:
        if (r0 != 2) goto L33;
        int r4 = this.currentPlaybackState;
        if (r4 == 0) goto L31;
        if (r4 == 2) goto L31;
        if (r4 == 12) goto L31;
        if (r5.getPlayWhenReady() == true) goto L26;
        return 7;
    L26:
        if (r5.getPlaybackSuppressionReason() == 0) goto L29;
        return 10;
    L29:
        return 6;
    L31:
        return 2;
    L33:
        if (r0 != 3) goto L43;
        if (r5.getPlayWhenReady() == true) goto L38;
        return 4;
    L38:
        if (r5.getPlaybackSuppressionReason() == 0) goto L41;
        return 9;
    L41:
        return 3;
    L43:
        if (r0 != 1) goto L48;
        if (this.currentPlaybackState == 0) goto L48;
        return 12;
    L48:
        return this.currentPlaybackState;
    }

    public LogSessionId getLogSessionId() {
        return this.playbackSession.getSessionId();
    }

    @Override // androidx.media3.exoplayer.analytics.PlaybackSessionManager.Listener
    public void onAdPlaybackStarted(AnalyticsListener.EventTime r1, String r2, String r3) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onBandwidthEstimate(AnalyticsListener.EventTime r6, int r7, long r8, long r10) {
        MediaSource.MediaPeriodId r102 = r6.mediaPeriodId;
        if (r102 == null) goto L14;
        String r62 = this.sessionManager.getSessionForMediaPeriodId(r6.timeline, (MediaSource.MediaPeriodId) Assertions.checkNotNull(r102));
        Long r103 = this.bandwidthBytes.get(r62);
        Long r11 = this.bandwidthTimeMs.get(r62);
        HashMap<String, Long> r0 = this.bandwidthBytes;
        long r1 = 0;
        if (r103 != null) goto L7;
        long r3 = 0;
    L8:
        r0.put(r62, Long.valueOf(r3 + r8));
        HashMap<String, Long> r5 = this.bandwidthTimeMs;
        if (r11 == null) goto L12;
        r1 = r11.longValue();
    L12:
        r5.put(r62, Long.valueOf(r1 + r7));
        return;
    L7:
        r3 = r103.longValue();
        goto L8
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onDownstreamFormatChanged(AnalyticsListener.EventTime r6, MediaLoadData r7) {
        if (r6.mediaPeriodId == null) goto L20;
        PendingFormatUpdate r0 = new PendingFormatUpdate((Format) Assertions.checkNotNull(r7.trackFormat), r7.trackSelectionReason, this.sessionManager.getSessionForMediaPeriodId(r6.timeline, (MediaSource.MediaPeriodId) Assertions.checkNotNull(r6.mediaPeriodId)));
        int r62 = r7.trackType;
        if (r62 != 0) goto L8;
    L18:
        this.pendingVideoFormat = r0;
        return;
    L8:
        if (r62 != 1) goto L10;
        this.pendingAudioFormat = r0;
        return;
    L10:
        if (r62 == 2) goto L18;
        if (r62 == 3) goto L14;
        return;
    L14:
        this.pendingTextFormat = r0;
        return;
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onEvents(Player r3, AnalyticsListener.Events r4) {
        if (r4.size() == 0) goto L10;
        maybeAddSessions(r4);
        long r0 = SystemClock.elapsedRealtime();
        maybeUpdateMetricsBuilderValues(r3, r4);
        maybeReportPlaybackError(r0);
        maybeReportTrackChanges(r3, r4, r0);
        maybeReportNetworkChange(r0);
        maybeReportPlaybackStateChange(r3, r4, r0);
        if (r4.contains(1028) == false) goto L9;
        this.sessionManager.finishAllSessions(r4.getEventTime(1028));
        return;
    L9:
        return;
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onLoadError(AnalyticsListener.EventTime r1, LoadEventInfo r2, MediaLoadData r3, IOException r4, boolean r5) {
        this.ioErrorType = r3.dataType;
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onPlayerError(AnalyticsListener.EventTime r1, PlaybackException r2) {
        this.pendingPlayerError = r2;
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onPositionDiscontinuity(AnalyticsListener.EventTime r1, Player.PositionInfo r2, Player.PositionInfo r3, int r4) {
        if (r4 != 1) goto L5;
        this.isSeeking = true;
    L5:
        this.discontinuityReason = r4;
    }

    @Override // androidx.media3.exoplayer.analytics.PlaybackSessionManager.Listener
    public void onSessionActive(AnalyticsListener.EventTime r2, String r3) {
        MediaSource.MediaPeriodId r0 = r2.mediaPeriodId;
        if (r0 != null) goto L5;
    L7:
        finishCurrentSession();
        this.activeSessionId = r3;
        this.metricsBuilder = MediaMetricsListener$$ExternalSyntheticApiModelOutline2.m().setPlayerName("AndroidXMedia3").setPlayerVersion("1.8.1");
        maybeUpdateTimelineMetadata(r2.timeline, r2.mediaPeriodId);
        return;
    L5:
        if (r0.isAd() == false) goto L7;
    }

    @Override // androidx.media3.exoplayer.analytics.PlaybackSessionManager.Listener
    public void onSessionCreated(AnalyticsListener.EventTime r1, String r2) {
    }

    @Override // androidx.media3.exoplayer.analytics.PlaybackSessionManager.Listener
    public void onSessionFinished(AnalyticsListener.EventTime r1, String r2, boolean r3) {
        MediaSource.MediaPeriodId r12 = r1.mediaPeriodId;
        if (r12 == null) goto L7;
        if (r12.isAd() == false) goto L7;
    L10:
        this.bandwidthTimeMs.remove(r2);
        this.bandwidthBytes.remove(r2);
        return;
    L7:
        if (r2.equals(this.activeSessionId) == false) goto L10;
        finishCurrentSession();
        goto L10
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onVideoDisabled(AnalyticsListener.EventTime r2, DecoderCounters r3) {
        this.droppedFrames += r3.droppedBufferCount;
        this.playedFrames += r3.renderedOutputBufferCount;
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onVideoSizeChanged(AnalyticsListener.EventTime r4, VideoSize r5) {
        PendingFormatUpdate r42 = this.pendingVideoFormat;
        if (r42 == null) goto L8;
        Format r0 = r42.format;
        if (r0.height != (-1)) goto L9;
        this.pendingVideoFormat = new PendingFormatUpdate(r0.buildUpon().setWidth(r5.width).setHeight(r5.height).build(), r42.selectionReason, r42.sessionId);
        return;
    L9:
        return;
    }
}
