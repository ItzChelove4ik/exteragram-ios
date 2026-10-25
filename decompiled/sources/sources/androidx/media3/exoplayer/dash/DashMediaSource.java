package androidx.media3.exoplayer.dash;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.ParserException;
import androidx.media3.common.StreamKey;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.dash.DashChunkSource;
import androidx.media3.exoplayer.dash.DefaultDashChunkSource;
import androidx.media3.exoplayer.dash.PlayerEmsgHandler;
import androidx.media3.exoplayer.dash.manifest.AdaptationSet;
import androidx.media3.exoplayer.dash.manifest.DashManifest;
import androidx.media3.exoplayer.dash.manifest.DashManifestParser;
import androidx.media3.exoplayer.dash.manifest.Period;
import androidx.media3.exoplayer.dash.manifest.Representation;
import androidx.media3.exoplayer.dash.manifest.ServiceDescriptionElement;
import androidx.media3.exoplayer.dash.manifest.UtcTimingElement;
import androidx.media3.exoplayer.drm.DefaultDrmSessionManagerProvider;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.offline.FilteringManifestParser;
import androidx.media3.exoplayer.source.BaseMediaSource;
import androidx.media3.exoplayer.source.CompositeSequenceableLoaderFactory;
import androidx.media3.exoplayer.source.DefaultCompositeSequenceableLoaderFactory;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.source.MediaSourceFactory;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.exoplayer.upstream.DefaultLoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.Loader;
import androidx.media3.exoplayer.upstream.LoaderErrorThrower;
import androidx.media3.exoplayer.upstream.ParsingLoadable;
import androidx.media3.exoplayer.util.SntpClient;
import androidx.media3.extractor.text.SubtitleParser;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.common.math.LongMath;
import j$.util.DesugarTimeZone;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes4.dex */
public final class DashMediaSource extends BaseMediaSource {
    private final BaseUrlExclusionList baseUrlExclusionList;
    private final DashChunkSource.Factory chunkSourceFactory;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private DataSource dataSource;
    private final DrmSessionManager drmSessionManager;
    private long elapsedRealtimeOffsetMs;
    private long expiredManifestPublishTimeUs;
    private final long fallbackTargetLiveOffsetMs;
    private int firstPeriodId;
    private Handler handler;
    private Uri initialManifestUri;
    private MediaItem.LiveConfiguration liveConfiguration;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private Loader loader;
    private DashManifest manifest;
    private final ManifestCallback manifestCallback;
    private final DataSource.Factory manifestDataSourceFactory;
    private final MediaSourceEventListener.EventDispatcher manifestEventDispatcher;
    private IOException manifestFatalError;
    private long manifestLoadEndTimestampMs;
    private final LoaderErrorThrower manifestLoadErrorThrower;
    private boolean manifestLoadPending;
    private long manifestLoadStartTimestampMs;
    private final ParsingLoadable.Parser<? extends DashManifest> manifestParser;
    private Uri manifestUri;
    private final Object manifestUriLock;
    private MediaItem mediaItem;
    private TransferListener mediaTransferListener;
    private final long minLiveStartPositionUs;
    private final SparseArray<DashMediaPeriod> periodsById;
    private final PlayerEmsgHandler.PlayerEmsgCallback playerEmsgCallback;
    private final Runnable refreshManifestRunnable;
    private final boolean sideloadedManifest;
    private final Runnable simulateManifestRefreshRunnable;
    private int staleManifestReloadAttempt;

    /* renamed from: androidx.media3.exoplayer.dash.DashMediaSource$1, reason: invalid class name */
    public class AnonymousClass1 implements SntpClient.InitializationCallback {
        final /* synthetic */ DashMediaSource this$0;

        public AnonymousClass1(DashMediaSource r1) {
            this.this$0 = r1;
        }

        @Override // androidx.media3.exoplayer.util.SntpClient.InitializationCallback
        public void onInitializationFailed(IOException r1) {
            DashMediaSource.access$600(this.this$0, r1);
        }

        @Override // androidx.media3.exoplayer.util.SntpClient.InitializationCallback
        public void onInitialized() {
            DashMediaSource.access$500(this.this$0, SntpClient.getElapsedRealtimeOffsetMs());
        }
    }

    public static final class DashTimeline extends Timeline {
        private final long elapsedRealtimeEpochOffsetMs;
        private final int firstPeriodId;
        private final MediaItem.LiveConfiguration liveConfiguration;
        private final DashManifest manifest;
        private final MediaItem mediaItem;
        private final long offsetInFirstPeriodUs;
        private final long presentationStartTimeMs;
        private final long windowDefaultStartPositionUs;
        private final long windowDurationUs;
        private final long windowStartTimeMs;

        public DashTimeline(long r7, long r9, long r11, int r13, long r14, long r16, long r18, DashManifest r20, MediaItem r21, MediaItem.LiveConfiguration r22) {
            boolean r2 = r20.dynamic;
            boolean r3 = false;
            if (r22 == null) goto L5;
            boolean r5 = true;
        L6:
            if (r2 != r5) goto L8;
            r3 = true;
        L8:
            Assertions.checkState(r3);
            this.presentationStartTimeMs = r7;
            this.windowStartTimeMs = r9;
            this.elapsedRealtimeEpochOffsetMs = r11;
            this.firstPeriodId = r13;
            this.offsetInFirstPeriodUs = r14;
            this.windowDurationUs = r16;
            this.windowDefaultStartPositionUs = r18;
            this.manifest = r20;
            this.mediaItem = r21;
            this.liveConfiguration = r22;
            return;
        L5:
            r5 = false;
            goto L6
        }

        private long getAdjustedWindowDefaultStartPositionUs(long r10) {
            long r0 = this.windowDefaultStartPositionUs;
            if (isMovingLiveWindow(this.manifest) == true) goto L6;
            return r0;
        L6:
            if (r10 <= 0) goto L11;
            r0 = r0 + r10;
            if (r0 <= this.windowDurationUs) goto L11;
            return -9223372036854775807L;
        L11:
            long r102 = this.offsetInFirstPeriodUs + r0;
            long r6 = this.manifest.getPeriodDurationUs(0);
            int r4 = 0;
        L13:
            if (r4 >= (this.manifest.getPeriodCount() - 1)) goto L17;
            if (r102 < r6) goto L17;
            r102 = r102 - r6;
            r4 = r4 + 1;
            r6 = this.manifest.getPeriodDurationUs(r4);
        L17:
            Period r9 = this.manifest.getPeriod(r4);
            int r42 = r9.getAdaptationSetIndex(2);
            if (r42 == (-1)) goto L30;
            DashSegmentIndex r92 = r9.adaptationSets.get(r42).representations.get(0).getIndex();
            if (r92 != null) goto L23;
            return r0;
        L23:
            if (r92.getSegmentCount(r6) != 0) goto L26;
            return r0;
        L26:
            return (r0 + r92.getTimeUs(r92.getSegmentNum(r102, r6))) - r102;
        L30:
            return r0;
        }

        private static boolean isMovingLiveWindow(DashManifest r4) {
            if (r4.dynamic == true) goto L5;
            return false;
        L5:
            if (r4.minUpdatePeriodMs != (-9223372036854775807L)) goto L7;
            return false;
        L7:
            if (r4.durationMs != (-9223372036854775807L)) goto L13;
            return true;
        L13:
            return false;
        }

        @Override // androidx.media3.common.Timeline
        public int getIndexOfPeriod(Object r3) {
            if ((r3 instanceof Integer) == true) goto L5;
            return -1;
        L5:
            int r32 = ((Integer) r3).intValue() - this.firstPeriodId;
            if (r32 >= 0) goto L8;
        L11:
            return -1;
        L8:
            if (r32 >= getPeriodCount()) goto L11;
            return r32;
        }

        @Override // androidx.media3.common.Timeline
        public Timeline.Period getPeriod(int r12, Timeline.Period r13, boolean r14) {
            Assertions.checkIndex(r12, 0, getPeriodCount());
            Integer r0 = null;
            if (r14 == false) goto L5;
            String r4 = this.manifest.getPeriod(r12).id;
        L6:
            if (r14 == false) goto L9;
            r0 = Integer.valueOf(this.firstPeriodId + r12);
        L9:
            return r13.set(r4, r0, 0, this.manifest.getPeriodDurationUs(r12), Util.msToUs(this.manifest.getPeriod(r12).startMs - this.manifest.getPeriod(0).startMs) - this.offsetInFirstPeriodUs);
        L5:
            r4 = null;
            goto L6
        }

        @Override // androidx.media3.common.Timeline
        public int getPeriodCount() {
            return this.manifest.getPeriodCount();
        }

        @Override // androidx.media3.common.Timeline
        public Object getUidOfPeriod(int r3) {
            Assertions.checkIndex(r3, 0, getPeriodCount());
            return Integer.valueOf(this.firstPeriodId + r3);
        }

        @Override // androidx.media3.common.Timeline
        public Timeline.Window getWindow(int r25, Timeline.Window r26, long r27) {
            Assertions.checkIndex(r25, 0, 1);
            long r16 = getAdjustedWindowDefaultStartPositionUs(r27);
            Object r4 = Timeline.Window.SINGLE_WINDOW_UID;
            MediaItem r5 = this.mediaItem;
            DashManifest r6 = this.manifest;
            return r26.set(r4, r5, r6, this.presentationStartTimeMs, this.windowStartTimeMs, this.elapsedRealtimeEpochOffsetMs, true, isMovingLiveWindow(r6), this.liveConfiguration, r16, this.windowDurationUs, 0, getPeriodCount() - 1, this.offsetInFirstPeriodUs);
        }

        @Override // androidx.media3.common.Timeline
        public int getWindowCount() {
            return 1;
        }
    }

    public final class DefaultPlayerEmsgCallback implements PlayerEmsgHandler.PlayerEmsgCallback {
        final /* synthetic */ DashMediaSource this$0;

        private DefaultPlayerEmsgCallback(DashMediaSource r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ DefaultPlayerEmsgCallback(DashMediaSource r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // androidx.media3.exoplayer.dash.PlayerEmsgHandler.PlayerEmsgCallback
        public void onDashManifestPublishTimeExpired(long r1) {
            this.this$0.onDashManifestPublishTimeExpired(r1);
        }

        @Override // androidx.media3.exoplayer.dash.PlayerEmsgHandler.PlayerEmsgCallback
        public void onDashManifestRefreshRequested() {
            this.this$0.onDashManifestRefreshRequested();
        }
    }

    public static final class Factory implements MediaSourceFactory {
        public static final /* synthetic */ int $r8$clinit = 0;
        private final DashChunkSource.Factory chunkSourceFactory;
        private CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
        private DrmSessionManagerProvider drmSessionManagerProvider;
        private long fallbackTargetLiveOffsetMs;
        private LoadErrorHandlingPolicy loadErrorHandlingPolicy;
        private final DataSource.Factory manifestDataSourceFactory;
        private ParsingLoadable.Parser<? extends DashManifest> manifestParser;
        private long minLiveStartPositionUs;

        public Factory(DataSource.Factory r2) {
            this(new DefaultDashChunkSource.Factory(r2), r2);
        }

        public Factory(DashChunkSource.Factory r1, DataSource.Factory r2) {
            this.chunkSourceFactory = (DashChunkSource.Factory) Assertions.checkNotNull(r1);
            this.manifestDataSourceFactory = r2;
            this.drmSessionManagerProvider = new DefaultDrmSessionManagerProvider();
            this.loadErrorHandlingPolicy = new DefaultLoadErrorHandlingPolicy();
            this.fallbackTargetLiveOffsetMs = NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS;
            this.minLiveStartPositionUs = 5000000;
            this.compositeSequenceableLoaderFactory = new DefaultCompositeSequenceableLoaderFactory();
            experimentalParseSubtitlesDuringExtraction(true);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public DashMediaSource createMediaSource(MediaItem r16) {
            Assertions.checkNotNull(r16.localConfiguration);
            ParsingLoadable.Parser r0 = this.manifestParser;
            if (r0 != null) goto L5;
            r0 = new DashManifestParser();
        L5:
            List<StreamKey> r2 = r16.localConfiguration.streamKeys;
            if (r2.isEmpty() == true) goto L8;
            ParsingLoadable.Parser r4 = new FilteringManifestParser(r0, r2);
        L10:
            return new DashMediaSource(r16, null, this.manifestDataSourceFactory, r4, this.chunkSourceFactory, this.compositeSequenceableLoaderFactory, null, this.drmSessionManagerProvider.get(r16), this.loadErrorHandlingPolicy, this.fallbackTargetLiveOffsetMs, this.minLiveStartPositionUs, null);
        L8:
            r4 = r0;
            goto L10
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public /* bridge */ /* synthetic */ MediaSource createMediaSource(MediaItem r1) {
            return createMediaSource(r1);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        @Deprecated
        public Factory experimentalParseSubtitlesDuringExtraction(boolean r2) {
            this.chunkSourceFactory.experimentalParseSubtitlesDuringExtraction(r2);
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        @Deprecated
        public /* bridge */ /* synthetic */ MediaSource.Factory experimentalParseSubtitlesDuringExtraction(boolean r1) {
            return experimentalParseSubtitlesDuringExtraction(r1);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public Factory experimentalSetCodecsToParseWithinGopSampleDependencies(int r2) {
            this.chunkSourceFactory.experimentalSetCodecsToParseWithinGopSampleDependencies(r2);
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public /* bridge */ /* synthetic */ MediaSource.Factory experimentalSetCodecsToParseWithinGopSampleDependencies(int r1) {
            return experimentalSetCodecsToParseWithinGopSampleDependencies(r1);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public Factory setDrmSessionManagerProvider(DrmSessionManagerProvider r2) {
            this.drmSessionManagerProvider = (DrmSessionManagerProvider) Assertions.checkNotNull(r2, "MediaSource.Factory#setDrmSessionManagerProvider no longer handles null by instantiating a new DefaultDrmSessionManagerProvider. Explicitly construct and pass an instance in order to retain the old behavior.");
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public /* bridge */ /* synthetic */ MediaSource.Factory setDrmSessionManagerProvider(DrmSessionManagerProvider r1) {
            return setDrmSessionManagerProvider(r1);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy r2) {
            this.loadErrorHandlingPolicy = (LoadErrorHandlingPolicy) Assertions.checkNotNull(r2, "MediaSource.Factory#setLoadErrorHandlingPolicy no longer handles null by instantiating a new DefaultLoadErrorHandlingPolicy. Explicitly construct and pass an instance in order to retain the old behavior.");
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public /* bridge */ /* synthetic */ MediaSource.Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy r1) {
            return setLoadErrorHandlingPolicy(r1);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public Factory setSubtitleParserFactory(SubtitleParser.Factory r2) {
            this.chunkSourceFactory.setSubtitleParserFactory((SubtitleParser.Factory) Assertions.checkNotNull(r2));
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public /* bridge */ /* synthetic */ MediaSource.Factory setSubtitleParserFactory(SubtitleParser.Factory r1) {
            return setSubtitleParserFactory(r1);
        }
    }

    public static final class Iso8601Parser implements ParsingLoadable.Parser<Long> {
        private static final Pattern TIMESTAMP_WITH_TIMEZONE_PATTERN = null;

        static {
            TIMESTAMP_WITH_TIMEZONE_PATTERN = Pattern.compile("(.+?)(Z|((\\+|-|−)(\\d\\d)(:?(\\d\\d))?))");
        }

        public Iso8601Parser() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.media3.exoplayer.upstream.ParsingLoadable.Parser
        public Long parse(Uri r10, InputStream r11) {
            String r102 = new BufferedReader(new InputStreamReader(r11, StandardCharsets.UTF_8)).readLine();
            Matcher r0 = TIMESTAMP_WITH_TIMEZONE_PATTERN.matcher(r102);     // Catch: ParseException -> L19
            if (r0.matches() == false) goto L22;
            String r9 = r0.group(1);     // Catch: ParseException -> L19
            SimpleDateFormat r103 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);     // Catch: ParseException -> L19
            r103.setTimeZone(DesugarTimeZone.getTimeZone("UTC"));     // Catch: ParseException -> L19
            long r92 = r103.parse(r9).getTime();     // Catch: ParseException -> L19
            if ("Z".equals(r0.group(2)) == false) goto L9;
        L18:
            return Long.valueOf(r92);
        L9:
            if ("+".equals(r0.group(4)) == false) goto L11;
            long r1 = 1;
        L12:
            long r3 = Long.parseLong(r0.group(5));     // Catch: ParseException -> L19
            String r02 = r0.group(7);     // Catch: ParseException -> L19
            if (TextUtils.isEmpty(r02) == false) goto L15;
            long r5 = 0;
        L16:
            r92 = r92 - (r1 * (((r3 * 60) + r5) * 60000));     // Catch: ParseException -> L19
            goto L18
        L15:
            r5 = Long.parseLong(r02);     // Catch: ParseException -> L19
            goto L16
        L11:
            r1 = -1;
            goto L12
        L22:
            throw ParserException.createForMalformedManifest("Couldn't parse timestamp: " + r102, null);     // Catch: ParseException -> L19
        L19:
            e = move-exception;
            throw ParserException.createForMalformedManifest(null, e);
        }

        @Override // androidx.media3.exoplayer.upstream.ParsingLoadable.Parser
        public /* bridge */ /* synthetic */ Long parse(Uri r1, InputStream r2) {
            return parse(r1, r2);
        }
    }

    public final class ManifestCallback implements Loader.Callback<ParsingLoadable<DashManifest>> {
        final /* synthetic */ DashMediaSource this$0;

        private ManifestCallback(DashMediaSource r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ ManifestCallback(DashMediaSource r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Callback
        public /* bridge */ /* synthetic */ void onLoadCanceled(Loader.Loadable r1, long r2, long r4, boolean r6) {
            onLoadCanceled((ParsingLoadable) r1, r2, r4, r6);
        }

        public void onLoadCanceled(ParsingLoadable<DashManifest> r1, long r2, long r4, boolean r6) {
            this.this$0.onLoadCanceled(r1, r2, r4);
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Callback
        public /* bridge */ /* synthetic */ void onLoadCompleted(Loader.Loadable r1, long r2, long r4) {
            onLoadCompleted((ParsingLoadable) r1, r2, r4);
        }

        public void onLoadCompleted(ParsingLoadable<DashManifest> r1, long r2, long r4) {
            this.this$0.onManifestLoadCompleted(r1, r2, r4);
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Callback
        public /* bridge */ /* synthetic */ Loader.LoadErrorAction onLoadError(Loader.Loadable r1, long r2, long r4, IOException r6, int r7) {
            return onLoadError((ParsingLoadable) r1, r2, r4, r6, r7);
        }

        public Loader.LoadErrorAction onLoadError(ParsingLoadable<DashManifest> r1, long r2, long r4, IOException r6, int r7) {
            return this.this$0.onManifestLoadError(r1, r2, r4, r6, r7);
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Callback
        public /* bridge */ /* synthetic */ void onLoadStarted(Loader.Loadable r1, long r2, long r4, int r6) {
            onLoadStarted((ParsingLoadable) r1, r2, r4, r6);
        }

        public void onLoadStarted(ParsingLoadable<DashManifest> r1, long r2, long r4, int r6) {
            this.this$0.onManifestLoadStarted(r1, r2, r4, r6);
        }
    }

    public final class ManifestLoadErrorThrower implements LoaderErrorThrower {
        final /* synthetic */ DashMediaSource this$0;

        public ManifestLoadErrorThrower(DashMediaSource r1) {
            this.this$0 = r1;
        }

        private void maybeThrowManifestError() {
            if (DashMediaSource.access$800(this.this$0) != null) goto L6;
            return;
        L6:
            throw DashMediaSource.access$800(this.this$0);
        }

        @Override // androidx.media3.exoplayer.upstream.LoaderErrorThrower
        public void maybeThrowError() {
            DashMediaSource.access$700(this.this$0).maybeThrowError();
            maybeThrowManifestError();
        }
    }

    public final class UtcTimestampCallback implements Loader.Callback<ParsingLoadable<Long>> {
        final /* synthetic */ DashMediaSource this$0;

        private UtcTimestampCallback(DashMediaSource r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ UtcTimestampCallback(DashMediaSource r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Callback
        public /* bridge */ /* synthetic */ void onLoadCanceled(Loader.Loadable r1, long r2, long r4, boolean r6) {
            onLoadCanceled((ParsingLoadable) r1, r2, r4, r6);
        }

        public void onLoadCanceled(ParsingLoadable<Long> r1, long r2, long r4, boolean r6) {
            this.this$0.onLoadCanceled(r1, r2, r4);
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Callback
        public /* bridge */ /* synthetic */ void onLoadCompleted(Loader.Loadable r1, long r2, long r4) {
            onLoadCompleted((ParsingLoadable) r1, r2, r4);
        }

        public void onLoadCompleted(ParsingLoadable<Long> r1, long r2, long r4) {
            this.this$0.onUtcTimestampLoadCompleted(r1, r2, r4);
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Callback
        public /* bridge */ /* synthetic */ Loader.LoadErrorAction onLoadError(Loader.Loadable r1, long r2, long r4, IOException r6, int r7) {
            return onLoadError((ParsingLoadable) r1, r2, r4, r6, r7);
        }

        public Loader.LoadErrorAction onLoadError(ParsingLoadable<Long> r1, long r2, long r4, IOException r6, int r7) {
            return this.this$0.onUtcTimestampLoadError(r1, r2, r4, r6);
        }
    }

    public static final class XsDateTimeParser implements ParsingLoadable.Parser<Long> {
        private XsDateTimeParser() {
        }

        public /* synthetic */ XsDateTimeParser(AnonymousClass1 r1) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.media3.exoplayer.upstream.ParsingLoadable.Parser
        public Long parse(Uri r1, InputStream r2) {
            return Long.valueOf(Util.parseXsDateTime(new BufferedReader(new InputStreamReader(r2)).readLine()));
        }

        @Override // androidx.media3.exoplayer.upstream.ParsingLoadable.Parser
        public /* bridge */ /* synthetic */ Long parse(Uri r1, InputStream r2) {
            return parse(r1, r2);
        }
    }

    public static /* synthetic */ void $r8$lambda$VdFcoBzVieqv9jwwmV6lqyHjYNo(DashMediaSource r1) {
        r1.processManifest(false);
    }

    public static /* synthetic */ void $r8$lambda$iFt22VLWMPRWv6j8zcIpQ90WqWU(DashMediaSource r0) {
        r0.startLoadingManifest();
    }

    static {
        MediaLibraryInfo.registerModule("media3.exoplayer.dash");
    }

    private DashMediaSource(MediaItem r1, DashManifest r2, DataSource.Factory r3, ParsingLoadable.Parser<? extends DashManifest> r4, DashChunkSource.Factory r5, CompositeSequenceableLoaderFactory r6, CmcdConfiguration r7, DrmSessionManager r8, LoadErrorHandlingPolicy r9, long r10, long r12) {
        this.mediaItem = r1;
        this.liveConfiguration = r1.liveConfiguration;
        this.manifestUri = ((MediaItem.LocalConfiguration) Assertions.checkNotNull(r1.localConfiguration)).uri;
        this.initialManifestUri = r1.localConfiguration.uri;
        this.manifest = r2;
        this.manifestDataSourceFactory = r3;
        this.manifestParser = r4;
        this.chunkSourceFactory = r5;
        this.drmSessionManager = r8;
        this.loadErrorHandlingPolicy = r9;
        this.fallbackTargetLiveOffsetMs = r10;
        this.minLiveStartPositionUs = r12;
        this.compositeSequenceableLoaderFactory = r6;
        this.baseUrlExclusionList = new BaseUrlExclusionList();
        if (r2 == null) goto L5;
        boolean r32 = true;
    L6:
        this.sideloadedManifest = r32;
        this.manifestEventDispatcher = createEventDispatcher(null);
        this.manifestUriLock = new Object();
        this.periodsById = new SparseArray();
        this.playerEmsgCallback = new DefaultPlayerEmsgCallback(this, null);
        this.expiredManifestPublishTimeUs = -9223372036854775807L;
        this.elapsedRealtimeOffsetMs = -9223372036854775807L;
        if (r32 == false) goto L10;
        Assertions.checkState(true ^ r2.dynamic);
        this.manifestCallback = null;
        this.refreshManifestRunnable = null;
        this.simulateManifestRefreshRunnable = null;
        this.manifestLoadErrorThrower = new LoaderErrorThrower.Placeholder();
        return;
    L10:
        this.manifestCallback = new ManifestCallback(this, null);
        this.manifestLoadErrorThrower = new ManifestLoadErrorThrower(this);
        this.refreshManifestRunnable = new DashMediaSource$$ExternalSyntheticLambda0(this);
        this.simulateManifestRefreshRunnable = new DashMediaSource$$ExternalSyntheticLambda1(this);
        return;
    L5:
        r32 = false;
        goto L6
    }

    public /* synthetic */ DashMediaSource(MediaItem r1, DashManifest r2, DataSource.Factory r3, ParsingLoadable.Parser r4, DashChunkSource.Factory r5, CompositeSequenceableLoaderFactory r6, CmcdConfiguration r7, DrmSessionManager r8, LoadErrorHandlingPolicy r9, long r10, long r12, AnonymousClass1 r14) {
        this(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12);
    }

    public static /* synthetic */ void access$500(DashMediaSource r0, long r1) {
        r0.onUtcTimestampResolved(r1);
    }

    public static /* synthetic */ void access$600(DashMediaSource r0, IOException r1) {
        r0.onUtcTimestampResolutionError(r1);
    }

    public static /* synthetic */ Loader access$700(DashMediaSource r0) {
        return r0.loader;
    }

    public static /* synthetic */ IOException access$800(DashMediaSource r0) {
        return r0.manifestFatalError;
    }

    private static long getAvailableEndTimeInManifestUs(Period r17, long r18, long r20) {
        long r5 = Util.msToUs(r17.startMs);
        boolean r7 = hasVideoOrAudioAdaptationSets(r17);
        long r8 = LongCompanionObject.MAX_VALUE;
        int r11 = 0;
    L4:
        if (r11 >= r17.adaptationSets.size()) goto L25;
        AdaptationSet r12 = r17.adaptationSets.get(r11);
        List<Representation> r13 = r12.representations;
        int r122 = r12.type;
        boolean r14 = true;
        if (r122 != 1) goto L8;
    L10:
        r14 = false;
    L11:
        if (r7 == false) goto L14;
        if (r14 == false) goto L14;
    L24:
        r11 = r11 + 1;
    L14:
        if (r13.isEmpty() == true) goto L24;
        DashSegmentIndex r123 = r13.get(0).getIndex();
        if (r123 == null) goto L19;
        long r132 = r123.getAvailableSegmentCount(r18, r20);
        if (r132 == 0) goto L22;
        long r133 = (r123.getFirstAvailableSegmentNum(r18, r20) + r132) - 1;
        r8 = Math.min(r8, r123.getDurationUs(r133, r18) + (r123.getTimeUs(r133) + r5));
        goto L24
    L22:
        return r5;
    L19:
        return r5 + r18;
    L8:
        if (r122 == 2) goto L10;
    L25:
        return r8;
    }

    private static long getAvailableStartTimeInManifestUs(Period r17, long r18, long r20) {
        long r5 = Util.msToUs(r17.startMs);
        boolean r7 = hasVideoOrAudioAdaptationSets(r17);
        long r10 = r5;
        int r9 = 0;
    L4:
        if (r9 >= r17.adaptationSets.size()) goto L24;
        AdaptationSet r12 = r17.adaptationSets.get(r9);
        List<Representation> r13 = r12.representations;
        int r122 = r12.type;
        boolean r14 = true;
        if (r122 != 1) goto L8;
    L10:
        r14 = false;
    L11:
        if (r7 == false) goto L14;
        if (r14 == false) goto L14;
    L23:
        r9 = r9 + 1;
    L14:
        if (r13.isEmpty() == true) goto L23;
        DashSegmentIndex r123 = r13.get(0).getIndex();
        if (r123 == null) goto L21;
        if (r123.getAvailableSegmentCount(r18, r20) == 0) goto L21;
        r10 = Math.max(r10, r123.getTimeUs(r123.getFirstAvailableSegmentNum(r18, r20)) + r5);
    L21:
        return r5;
    L8:
        if (r122 == 2) goto L10;
    L24:
        return r10;
    }

    private static long getIntervalUntilNextManifestRefreshMs(DashManifest r20, long r21) {
        int r1 = r20.getPeriodCount() - 1;
        Period r2 = r20.getPeriod(r1);
        long r3 = Util.msToUs(r2.startMs);
        long r5 = r20.getPeriodDurationUs(r1);
        long r7 = Util.msToUs(r21);
        long r9 = Util.msToUs(r20.availabilityStartTimeMs);
        long r0 = Util.msToUs(r20.minUpdatePeriodMs);
        if (r0 != (-9223372036854775807L)) goto L5;
    L7:
        r0 = 5000000;
    L8:
        int r12 = 0;
    L10:
        if (r12 >= r2.adaptationSets.size()) goto L28;
        List<Representation> r13 = r2.adaptationSets.get(r12).representations;
        if (r13.isEmpty() == true) goto L26;
        DashSegmentIndex r132 = r13.get(0).getIndex();
        if (r132 == null) goto L26;
        long r14 = ((r9 + r3) + r132.getNextSegmentAvailableTimeUs(r5, r7)) - r7;
        if (r14 <= 0) goto L26;
        if (r14 >= (r0 - 100000)) goto L22;
    L25:
        r0 = r14;
        goto L26
    L22:
        if (r14 <= r0) goto L26;
        if (r14 < (r0 + 100000)) goto L25;
    L26:
        r12 = r12 + 1;
        goto L10
    L28:
        return LongMath.divide(r0, 1000, RoundingMode.CEILING);
    L5:
        if (r0 >= 5000000) goto L7;
        goto L7
    }

    private long getManifestLoadRetryDelayMillis() {
        return Math.min((this.staleManifestReloadAttempt - 1) * 1000, 5000);
    }

    private static boolean hasVideoOrAudioAdaptationSets(Period r5) {
        int r1 = 0;
    L4:
        if (r1 >= r5.adaptationSets.size()) goto L12;
        int r2 = r5.adaptationSets.get(r1).type;
        if (r2 == 1) goto L11;
        if (r2 == 2) goto L11;
        r1 = r1 + 1;
    L11:
        return true;
    L12:
        return false;
    }

    private static boolean isIndexExplicit(Period r3) {
        int r1 = 0;
    L4:
        if (r1 >= r3.adaptationSets.size()) goto L13;
        DashSegmentIndex r2 = r3.adaptationSets.get(r1).representations.get(0).getIndex();
        if (r2 == null) goto L11;
        if (r2.isExplicit() == true) goto L17;
        r1 = r1 + 1;
        goto L4
    L17:
        return true;
    L11:
        return true;
    L13:
        return false;
    }

    private void loadNtpTimeOffset() {
        SntpClient.initialize(this.loader, new AnonymousClass1(this));
    }

    private void onUtcTimestampResolutionError(IOException r5) {
        Log.e("DashMediaSource", "Failed to resolve time offset.", r5);
        this.elapsedRealtimeOffsetMs = System.currentTimeMillis() - SystemClock.elapsedRealtime();
        processManifest(true);
    }

    private void onUtcTimestampResolved(long r1) {
        this.elapsedRealtimeOffsetMs = r1;
        processManifest(true);
    }

    private void processManifest(boolean r36) {
        boolean r1 = false;
        int r2 = 0;
    L4:
        if (r2 >= this.periodsById.size()) goto L9;
        int r3 = this.periodsById.keyAt(r2);
        if (r3 < this.firstPeriodId) goto L8;
        this.periodsById.valueAt(r2).updateManifest(this.manifest, r3 - this.firstPeriodId);
    L8:
        r2 = r2 + 1;
        goto L4
    L9:
        Period r22 = this.manifest.getPeriod(0);
        int r32 = this.manifest.getPeriodCount() - 1;
        Period r5 = this.manifest.getPeriod(r32);
        long r6 = this.manifest.getPeriodDurationUs(r32);
        long r8 = Util.msToUs(Util.getNowUnixTimeMs(this.elapsedRealtimeOffsetMs));
        long r10 = getAvailableStartTimeInManifestUs(r22, this.manifest.getPeriodDurationUs(0), r8);
        long r62 = getAvailableEndTimeInManifestUs(r5, r6, r8);
        if (this.manifest.dynamic == true) goto L12;
    L14:
        boolean r33 = false;
    L16:
        if (r33 == false) goto L20;
        long r14 = this.manifest.timeShiftBufferDepthMs;
        if (r14 == (-9223372036854775807L)) goto L20;
        r10 = Math.max(r10, r62 - Util.msToUs(r14));
    L20:
        long r63 = r62 - r10;
        DashManifest r52 = this.manifest;
        if (r52.dynamic == true) goto L23;
        long r332 = 0;
        long r17 = -9223372036854775807L;
        long r26 = 0;
    L30:
        long r222 = r10 - Util.msToUs(r22.startMs);
        DashManifest r12 = this.manifest;
        long r4 = r12.availabilityStartTimeMs;
        long r82 = this.elapsedRealtimeOffsetMs;
        int r23 = this.firstPeriodId;
        MediaItem r29 = getMediaItem();
        if (this.manifest.dynamic == false) goto L34;
        MediaItem.LiveConfiguration r102 = this.liveConfiguration;
    L35:
        refreshSourceInfo(new DashTimeline(r4, r17, r82, r23, r222, r63, r26, r12, r29, r102));
        if (this.sideloadedManifest == true) goto L57;
        this.handler.removeCallbacks(this.simulateManifestRefreshRunnable);
        if (r33 == false) goto L41;
        this.handler.postDelayed(this.simulateManifestRefreshRunnable, getIntervalUntilNextManifestRefreshMs(this.manifest, Util.getNowUnixTimeMs(this.elapsedRealtimeOffsetMs)));
    L41:
        if (this.manifestLoadPending == false) goto L44;
        startLoadingManifest();
        return;
    L44:
        if (r36 == false) goto L58;
        DashManifest r13 = this.manifest;
        if (r13.dynamic == false) goto L59;
        long r15 = r13.minUpdatePeriodMs;
        if (r15 != (-9223372036854775807L)) goto L50;
        return;
    L50:
        if (r15 != r332) goto L52;
        r15 = 5000;
    L52:
        scheduleManifestRefresh(Math.max(r332, (this.manifestLoadStartTimestampMs + r15) - SystemClock.elapsedRealtime()));
        return;
    L59:
        return;
    L58:
        return;
    L57:
        return;
    L34:
        r102 = null;
        goto L35
    L23:
        if (r52.availabilityStartTimeMs == (-9223372036854775807L)) goto L25;
        r1 = true;
    L25:
        Assertions.checkState(r1);
        long r83 = (r8 - Util.msToUs(this.manifest.availabilityStartTimeMs)) - r10;
        updateLiveConfiguration(r83, r63);
        long r42 = this.manifest.availabilityStartTimeMs + Util.usToMs(r10);
        long r84 = r83 - Util.msToUs(this.liveConfiguration.targetOffsetMs);
        r332 = 0;
        long r122 = Math.min(this.minLiveStartPositionUs, r63 / 2);
        r17 = r42;
        if (r84 >= r122) goto L28;
        r26 = r122;
        goto L30
    L28:
        r26 = r84;
        goto L30
    L12:
        if (isIndexExplicit(r5) == true) goto L14;
        r33 = true;
        goto L16
    }

    private void resolveUtcTimingElement(UtcTimingElement r3) {
        String r0 = r3.schemeIdUri;
        if (Objects.equals(r0, "urn:mpeg:dash:utc:direct:2014") == false) goto L5;
    L30:
        resolveUtcTimingElementDirect(r3);
        return;
    L5:
        if (Objects.equals(r0, "urn:mpeg:dash:utc:direct:2012") == true) goto L30;
        if (Objects.equals(r0, "urn:mpeg:dash:utc:http-iso:2014") == false) goto L10;
    L28:
        resolveUtcTimingElementHttp(r3, new Iso8601Parser());
        return;
    L10:
        if (Objects.equals(r0, "urn:mpeg:dash:utc:http-iso:2012") == true) goto L28;
        if (Objects.equals(r0, "urn:mpeg:dash:utc:http-xsdate:2014") == false) goto L15;
    L26:
        resolveUtcTimingElementHttp(r3, new XsDateTimeParser(null));
        return;
    L15:
        if (Objects.equals(r0, "urn:mpeg:dash:utc:http-xsdate:2012") == true) goto L26;
        if (Objects.equals(r0, "urn:mpeg:dash:utc:ntp:2014") == false) goto L20;
    L24:
        loadNtpTimeOffset();
        return;
    L20:
        if (Objects.equals(r0, "urn:mpeg:dash:utc:ntp:2012") == true) goto L24;
        onUtcTimestampResolutionError(new IOException("Unsupported UTC timing scheme"));
    }

    private void resolveUtcTimingElementDirect(UtcTimingElement r5) {
        onUtcTimestampResolved(Util.parseXsDateTime(r5.value) - this.manifestLoadEndTimestampMs);     // Catch: ParserException -> L4
        return;
    L4:
        e = move-exception;
        onUtcTimestampResolutionError(e);
    }

    private void resolveUtcTimingElementHttp(UtcTimingElement r4, ParsingLoadable.Parser<Long> r5) {
        startLoading(new ParsingLoadable(this.dataSource, Uri.parse(r4.value), 5, r5), new UtcTimestampCallback(this, null), 1);
    }

    private void scheduleManifestRefresh(long r2) {
        this.handler.postDelayed(this.refreshManifestRunnable, r2);
    }

    private <T> void startLoading(ParsingLoadable<T> r1, Loader.Callback<ParsingLoadable<T>> r2, int r3) {
        this.loader.startLoading(r1, r2, r3);
    }

    private void startLoadingManifest() {
        this.handler.removeCallbacks(this.refreshManifestRunnable);
        if (this.loader.hasFatalError() == false) goto L6;
        return;
    L6:
        if (this.loader.isLoading() == false) goto L9;
        this.manifestLoadPending = true;
        return;
    L9:
        Object r0 = this.manifestUriLock;
        monitor-enter(r0);
        Uri r2 = this.manifestUri;     // Catch: Throwable -> L15
        monitor-exit(r0);     // Catch: Throwable -> L15
        this.manifestLoadPending = false;
        DataSpec r02 = new DataSpec.Builder().setUri(r2).setFlags(1).build();
        startLoading(new ParsingLoadable(this.dataSource, r02, 4, this.manifestParser), this.manifestCallback, this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(4));
        return;
    L15:
        th = move-exception;
        throw th;
    }

    private void updateLiveConfiguration(long r19, long r21) {
        MediaItem.LiveConfiguration r1 = getMediaItem().liveConfiguration;
        long r6 = Util.usToMs(r19);
        long r2 = r1.maxOffsetMs;
        if (r2 == (-9223372036854775807L)) goto L6;
        long r22 = Math.min(r6, r2);
    L5:
        long r10 = r22;
    L12:
        long r23 = Util.usToMs(r19 - r21);
        if (r23 < 0) goto L15;
    L17:
        long r4 = this.manifest.minBufferTimeMs;
        if (r4 == (-9223372036854775807L)) goto L20;
        r23 = Math.min(r23 + r4, r6);
    L20:
        long r42 = r23;
        long r24 = r1.minOffsetMs;
        if (r24 == (-9223372036854775807L)) goto L24;
        r42 = Util.constrainValue(r24, r42, r6);
    L23:
        long r14 = r42;
        if (r14 <= r10) goto L32;
        long r16 = r14;
    L33:
        long r25 = this.liveConfiguration.targetOffsetMs;
        if (r25 != (-9223372036854775807L)) goto L46;
        DashManifest r26 = this.manifest;
        ServiceDescriptionElement r3 = r26.serviceDescription;
        if (r3 == null) goto L41;
        long r32 = r3.targetOffsetMs;
        if (r32 == (-9223372036854775807L)) goto L41;
        r25 = r32;
    L41:
        r25 = r26.suggestedPresentationDelayMs;
        if (r25 != (-9223372036854775807L)) goto L46;
        r25 = this.fallbackTargetLiveOffsetMs;
    L46:
        if (r25 >= r14) goto L49;
        r25 = r14;
    L49:
        if (r25 <= r16) goto L51;
        r25 = Util.constrainValue(Util.usToMs(r19 - Math.min(this.minLiveStartPositionUs, r21 / 2)), r14, r16);
    L51:
        long r102 = r16;
        float r43 = r1.minPlaybackSpeed;
        if (r43 != (-3.4028235E38f)) goto L58;
        ServiceDescriptionElement r44 = this.manifest.serviceDescription;
        if (r44 == null) goto L57;
        r43 = r44.minPlaybackSpeed;
        goto L58
    L57:
        r43 = -3.4028235E38f;
    L58:
        float r12 = r1.maxPlaybackSpeed;
        if (r12 != (-3.4028235E38f)) goto L66;
        ServiceDescriptionElement r13 = this.manifest.serviceDescription;
        if (r13 == null) goto L64;
        r12 = r13.maxPlaybackSpeed;
        goto L66
    L64:
        r12 = -3.4028235E38f;
    L66:
        if (r43 == (-3.4028235E38f)) goto L68;
    L74:
        this.liveConfiguration = new MediaItem.LiveConfiguration.Builder().setTargetOffsetMs(r25).setMinOffsetMs(r14).setMaxOffsetMs(r102).setMinPlaybackSpeed(r43).setMaxPlaybackSpeed(r12).build();
        return;
    L68:
        if (r12 != (-3.4028235E38f)) goto L74;
        ServiceDescriptionElement r5 = this.manifest.serviceDescription;
        if (r5 != null) goto L72;
    L73:
        r43 = 1.0f;
        r12 = 1.0f;
        goto L74
    L72:
        if (r5.targetOffsetMs != (-9223372036854775807L)) goto L74;
    L32:
        r16 = r10;
        goto L33
    L24:
        ServiceDescriptionElement r27 = this.manifest.serviceDescription;
        if (r27 == null) goto L23;
        long r28 = r27.minOffsetMs;
        if (r28 == (-9223372036854775807L)) goto L23;
        r42 = Util.constrainValue(r28, r42, r6);
        goto L23
    L15:
        if (r10 <= 0) goto L17;
        r23 = 0;
        goto L17
    L6:
        ServiceDescriptionElement r29 = this.manifest.serviceDescription;
        if (r29 == null) goto L11;
        long r210 = r29.maxOffsetMs;
        if (r210 == (-9223372036854775807L)) goto L11;
        r22 = Math.min(r6, r210);
    L11:
        r10 = r6;
        goto L12
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public boolean canUpdateMediaItem(MediaItem r5) {
        MediaItem r4 = getMediaItem();
        MediaItem.LocalConfiguration r0 = (MediaItem.LocalConfiguration) Assertions.checkNotNull(r4.localConfiguration);
        MediaItem.LocalConfiguration r1 = r5.localConfiguration;
        if (r1 != null) goto L5;
        return false;
    L5:
        if (r1.uri.equals(r0.uri) == true) goto L7;
        return false;
    L7:
        if (r1.streamKeys.equals(r0.streamKeys) == true) goto L9;
        return false;
    L9:
        if (Objects.equals(r1.drmConfiguration, r0.drmConfiguration) == true) goto L11;
        return false;
    L11:
        if (r4.liveConfiguration.equals(r5.liveConfiguration) == false) goto L19;
        return true;
    L19:
        return false;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId r24, Allocator r25, long r26) {
        int r8 = ((Integer) r24.periodUid).intValue() - this.firstPeriodId;
        MediaSourceEventListener.EventDispatcher r15 = createEventDispatcher(r24);
        DrmSessionEventListener.EventDispatcher r13 = createDrmEventDispatcher(r24);
        DashMediaPeriod r4 = new DashMediaPeriod(this.firstPeriodId + r8, this.manifest, this.baseUrlExclusionList, r8, this.chunkSourceFactory, this.mediaTransferListener, null, this.drmSessionManager, r13, this.loadErrorHandlingPolicy, r15, this.elapsedRealtimeOffsetMs, this.manifestLoadErrorThrower, r25, this.compositeSequenceableLoaderFactory, this.playerEmsgCallback, getPlayerId());
        this.periodsById.put(r4.id, r4);
        return r4;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public synchronized MediaItem getMediaItem() {
        monitor-enter(this);
        MediaItem r0 = this.mediaItem;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() {
        this.manifestLoadErrorThrower.maybeThrowError();
    }

    public void onDashManifestPublishTimeExpired(long r5) {
        long r0 = this.expiredManifestPublishTimeUs;
        if (r0 != (-9223372036854775807L)) goto L5;
    L8:
        this.expiredManifestPublishTimeUs = r5;
        return;
    L5:
        if (r0 < r5) goto L8;
    }

    public void onDashManifestRefreshRequested() {
        this.handler.removeCallbacks(this.simulateManifestRefreshRunnable);
        startLoadingManifest();
    }

    public void onLoadCanceled(ParsingLoadable<?> r13, long r14, long r16) {
        LoadEventInfo r0 = new LoadEventInfo(r13.loadTaskId, r13.dataSpec, r13.getUri(), r13.getResponseHeaders(), r14, r16, r13.bytesLoaded());
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(r13.loadTaskId);
        this.manifestEventDispatcher.loadCanceled(r0, r13.type);
    }

    public void onManifestLoadCompleted(ParsingLoadable<DashManifest> r17, long r18, long r20) {
        LoadEventInfo r2 = new LoadEventInfo(r17.loadTaskId, r17.dataSpec, r17.getUri(), r17.getResponseHeaders(), r18, r20, r17.bytesLoaded());
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(r17.loadTaskId);
        this.manifestEventDispatcher.loadCompleted(r2, r17.type);
        DashManifest r22 = r17.getResult();
        DashManifest r3 = this.manifest;
        if (r3 != null) goto L5;
        int r32 = 0;
    L6:
        long r5 = r22.getPeriod(0).startMs;
        int r7 = 0;
    L7:
        if (r7 >= r32) goto L12;
        if (this.manifest.getPeriod(r7).startMs >= r5) goto L12;
        r7 = r7 + 1;
    L12:
        if (r22.dynamic == true) goto L14;
    L28:
        this.manifest = r22;
        this.manifestLoadPending = r22.dynamic & this.manifestLoadPending;
        this.manifestLoadStartTimestampMs = r18 - r20;
        this.manifestLoadEndTimestampMs = r18;
        this.firstPeriodId += r7;
        Object r23 = this.manifestUriLock;
        monitor-enter(r23);
    L51:
        th = move-exception;
        throw th;
    L31:
        if (r17.dataSpec.uri.equals(this.manifestUri) == false) goto L38;
        Uri r33 = this.manifest.location;     // Catch: Throwable -> L51
        if (r33 != null) goto L37;
        r33 = CmcdData.removeFromUri(r17.getUri());     // Catch: Throwable -> L51
    L37:
        this.manifestUri = r33;     // Catch: Throwable -> L51
    L38:
        monitor-exit(r23);     // Catch: Throwable -> L51
        DashManifest r1 = this.manifest;
        if (r1.dynamic == true) goto L42;
    L49:
        processManifest(true);
        return;
    L42:
        if (this.elapsedRealtimeOffsetMs != (-9223372036854775807L)) goto L49;
        UtcTimingElement r12 = r1.utcTiming;
        if (r12 == null) goto L47;
        resolveUtcTimingElement(r12);
        return;
    L47:
        loadNtpTimeOffset();
        return;
    L14:
        if ((r32 - r7) <= r22.getPeriodCount()) goto L16;
        Log.w("DashMediaSource", "Loaded out of sync manifest");
    L21:
        int r24 = this.staleManifestReloadAttempt;
        this.staleManifestReloadAttempt = r24 + 1;
        if (r24 >= this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(r17.type)) goto L25;
        scheduleManifestRefresh(getManifestLoadRetryDelayMillis());
        return;
    L25:
        this.manifestFatalError = new DashManifestStaleException();
        return;
    L16:
        long r52 = this.expiredManifestPublishTimeUs;
        if (r52 != (-9223372036854775807L)) goto L19;
    L27:
        this.staleManifestReloadAttempt = 0;
        goto L28
    L19:
        if ((r22.publishTimeMs * 1000) > r52) goto L27;
        Log.w("DashMediaSource", "Loaded stale dynamic manifest: " + r22.publishTimeMs + ", " + this.expiredManifestPublishTimeUs);
        goto L21
    L5:
        r32 = r3.getPeriodCount();
        goto L6
    }

    public Loader.LoadErrorAction onManifestLoadError(ParsingLoadable<DashManifest> r15, long r16, long r18, IOException r20, int r21) {
        LoadEventInfo r2 = new LoadEventInfo(r15.loadTaskId, r15.dataSpec, r15.getUri(), r15.getResponseHeaders(), r16, r18, r15.bytesLoaded());
        long r3 = this.loadErrorHandlingPolicy.getRetryDelayMsFor(new LoadErrorHandlingPolicy.LoadErrorInfo(r2, new MediaLoadData(r15.type), r20, r21));
        if (r3 != (-9223372036854775807L)) goto L5;
        Loader.LoadErrorAction r32 = Loader.DONT_RETRY_FATAL;
    L6:
        boolean r4 = r32.isRetry();
        this.manifestEventDispatcher.loadError(r2, r15.type, r20, !r4);
        if (r4 == true) goto L9;
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(r15.loadTaskId);
    L9:
        return r32;
    L5:
        r32 = Loader.createRetryAction(false, r3);
        goto L6
    }

    public void onManifestLoadStarted(ParsingLoadable<DashManifest> r16, long r17, long r19, int r21) {
        if (r21 != 0) goto L5;
        LoadEventInfo r2 = new LoadEventInfo(r16.loadTaskId, r16.dataSpec, r17);
    L6:
        this.manifestEventDispatcher.loadStarted(r2, r16.type, r21);
        return;
    L5:
        r2 = new LoadEventInfo(r16.loadTaskId, r16.dataSpec, r16.getUri(), r16.getResponseHeaders(), r17, r19, r16.bytesLoaded());
        goto L6
    }

    public void onUtcTimestampLoadCompleted(ParsingLoadable<Long> r13, long r14, long r16) {
        LoadEventInfo r0 = new LoadEventInfo(r13.loadTaskId, r13.dataSpec, r13.getUri(), r13.getResponseHeaders(), r14, r16, r13.bytesLoaded());
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(r13.loadTaskId);
        this.manifestEventDispatcher.loadCompleted(r0, r13.type);
        onUtcTimestampResolved(r13.getResult().longValue() - r14);
    }

    public Loader.LoadErrorAction onUtcTimestampLoadError(ParsingLoadable<Long> r17, long r18, long r20, IOException r22) {
        this.manifestEventDispatcher.loadError(new LoadEventInfo(r17.loadTaskId, r17.dataSpec, r17.getUri(), r17.getResponseHeaders(), r18, r20, r17.bytesLoaded()), r17.type, r22, true);
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(r17.loadTaskId);
        onUtcTimestampResolutionError(r22);
        return Loader.DONT_RETRY;
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void prepareSourceInternal(TransferListener r3) {
        this.mediaTransferListener = r3;
        this.drmSessionManager.setPlayer(Looper.myLooper(), getPlayerId());
        this.drmSessionManager.prepare();
        if (this.sideloadedManifest == false) goto L6;
        processManifest(false);
        return;
    L6:
        this.dataSource = this.manifestDataSourceFactory.createDataSource();
        this.loader = new Loader("DashMediaSource");
        this.handler = Util.createHandlerForCurrentLooper();
        startLoadingManifest();
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void releasePeriod(MediaPeriod r1) {
        DashMediaPeriod r12 = (DashMediaPeriod) r1;
        r12.release();
        this.periodsById.remove(r12.id);
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void releaseSourceInternal() {
        this.manifestLoadPending = false;
        this.dataSource = null;
        Loader r2 = this.loader;
        if (r2 == null) goto L5;
        r2.release();
        this.loader = null;
    L5:
        this.manifestLoadStartTimestampMs = 0;
        this.manifestLoadEndTimestampMs = 0;
        this.manifestUri = this.initialManifestUri;
        this.manifestFatalError = null;
        Handler r22 = this.handler;
        if (r22 == null) goto L8;
        r22.removeCallbacksAndMessages(null);
        this.handler = null;
    L8:
        this.elapsedRealtimeOffsetMs = -9223372036854775807L;
        this.staleManifestReloadAttempt = 0;
        this.expiredManifestPublishTimeUs = -9223372036854775807L;
        this.periodsById.clear();
        this.baseUrlExclusionList.reset();
        this.drmSessionManager.release();
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public synchronized void updateMediaItem(MediaItem r1) {
        monitor-enter(this);
        this.mediaItem = r1;     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }
}
