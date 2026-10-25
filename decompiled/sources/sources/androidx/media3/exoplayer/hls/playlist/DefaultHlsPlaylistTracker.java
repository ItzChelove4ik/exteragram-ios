package androidx.media3.exoplayer.hls.playlist;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.HttpDataSource$InvalidResponseCodeException;
import androidx.media3.exoplayer.hls.HlsDataSourceFactory;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import androidx.media3.exoplayer.hls.playlist.HlsMultivariantPlaylist;
import androidx.media3.exoplayer.hls.playlist.HlsPlaylistParser;
import androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.Loader;
import androidx.media3.exoplayer.upstream.ParsingLoadable;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.common.collect.Iterables;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public final class DefaultHlsPlaylistTracker implements HlsPlaylistTracker, Loader.Callback<ParsingLoadable<HlsPlaylist>> {
    public static final HlsPlaylistTracker.Factory FACTORY = null;
    private final HlsDataSourceFactory dataSourceFactory;
    private MediaSourceEventListener.EventDispatcher eventDispatcher;
    private Loader initialPlaylistLoader;
    private long initialStartTimeUs;
    private boolean isLive;
    private final CopyOnWriteArrayList<HlsPlaylistTracker.PlaylistEventListener> listeners;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private HlsMultivariantPlaylist multivariantPlaylist;
    private final HashMap<Uri, MediaPlaylistBundle> playlistBundles;
    private final HlsPlaylistParserFactory playlistParserFactory;
    private Handler playlistRefreshHandler;
    private final double playlistStuckTargetDurationCoefficient;
    private HlsMediaPlaylist primaryMediaPlaylistSnapshot;
    private Uri primaryMediaPlaylistUrl;
    private HlsPlaylistTracker.PrimaryPlaylistListener primaryPlaylistListener;

    /* renamed from: androidx.media3.exoplayer.hls.playlist.DefaultHlsPlaylistTracker$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public class FirstPrimaryMediaPlaylistListener implements HlsPlaylistTracker.PlaylistEventListener {
        final /* synthetic */ DefaultHlsPlaylistTracker this$0;

        private FirstPrimaryMediaPlaylistListener(DefaultHlsPlaylistTracker r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ FirstPrimaryMediaPlaylistListener(DefaultHlsPlaylistTracker r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker.PlaylistEventListener
        public void onPlaylistChanged() {
            DefaultHlsPlaylistTracker.access$2100(this.this$0).remove(this);
        }

        @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker.PlaylistEventListener
        public boolean onPlaylistError(Uri r8, LoadErrorHandlingPolicy.LoadErrorInfo r9, boolean r10) {
            if (DefaultHlsPlaylistTracker.access$1500(this.this$0) != null) goto L20;
            long r1 = SystemClock.elapsedRealtime();
            List<HlsMultivariantPlaylist.Variant> r102 = ((HlsMultivariantPlaylist) Util.castNonNull(DefaultHlsPlaylistTracker.access$1200(this.this$0))).variants;
            int r3 = 0;
            int r4 = 0;
        L5:
            int r5 = r102.size();
            DefaultHlsPlaylistTracker r6 = this.this$0;
            if (r3 >= r5) goto L13;
            MediaPlaylistBundle r52 = (MediaPlaylistBundle) DefaultHlsPlaylistTracker.access$2200(r6).get(r102.get(r3).url);
            if (r52 == null) goto L12;
            if (r1 >= MediaPlaylistBundle.access$300(r52)) goto L12;
            r4 = r4 + 1;
        L12:
            r3 = r3 + 1;
            goto L5
        L13:
            LoadErrorHandlingPolicy.FallbackSelection r92 = DefaultHlsPlaylistTracker.access$900(this.this$0).getFallbackSelectionFor(new LoadErrorHandlingPolicy.FallbackOptions(1, 0, DefaultHlsPlaylistTracker.access$1200(r6).variants.size(), r4), r9);
            if (r92 == null) goto L20;
            if (r92.type != 2) goto L20;
            MediaPlaylistBundle r7 = (MediaPlaylistBundle) DefaultHlsPlaylistTracker.access$2200(this.this$0).get(r8);
            if (r7 == null) goto L20;
            MediaPlaylistBundle.access$000(r7, r92.exclusionDurationMs);
        L20:
            return false;
        }
    }

    public final class MediaPlaylistBundle implements Loader.Callback<ParsingLoadable<HlsPlaylist>> {
        private boolean activeForPlayback;
        private long earliestNextLoadTimeMs;
        private long excludeUntilMs;
        private long lastSnapshotChangeMs;
        private long lastSnapshotLoadMs;
        private boolean loadPending;
        private final DataSource mediaPlaylistDataSource;
        private final Loader mediaPlaylistLoader;
        private IOException playlistError;
        private HlsMediaPlaylist playlistSnapshot;
        private final Uri playlistUrl;
        final /* synthetic */ DefaultHlsPlaylistTracker this$0;

        public static /* synthetic */ void $r8$lambda$tODoFoTL93tNogJolJloDdCGWgE(MediaPlaylistBundle r1, Uri r2) {
            r1.loadPending = false;
            r1.loadPlaylistImmediately(r2);
        }

        public MediaPlaylistBundle(DefaultHlsPlaylistTracker r2, Uri r3) {
            this.this$0 = r2;
            this.playlistUrl = r3;
            this.mediaPlaylistLoader = new Loader("DefaultHlsPlaylistTracker:MediaPlaylist");
            this.mediaPlaylistDataSource = DefaultHlsPlaylistTracker.access$700(r2).createDataSource(4);
        }

        public static /* synthetic */ boolean access$000(MediaPlaylistBundle r0, long r1) {
            return r0.excludePlaylist(r1);
        }

        public static /* synthetic */ void access$200(MediaPlaylistBundle r0, HlsMediaPlaylist r1, LoadEventInfo r2) {
            r0.processLoadedPlaylist(r1, r2);
        }

        public static /* synthetic */ long access$300(MediaPlaylistBundle r2) {
            return r2.excludeUntilMs;
        }

        public static /* synthetic */ Uri access$400(MediaPlaylistBundle r0) {
            return r0.playlistUrl;
        }

        public static /* synthetic */ void access$500(MediaPlaylistBundle r0, Uri r1) {
            r0.loadPlaylistInternal(r1);
        }

        public static /* synthetic */ HlsMediaPlaylist access$600(MediaPlaylistBundle r0) {
            return r0.playlistSnapshot;
        }

        private boolean excludePlaylist(long r3) {
            this.excludeUntilMs = SystemClock.elapsedRealtime() + r3;
            if (this.playlistUrl.equals(DefaultHlsPlaylistTracker.access$1900(this.this$0)) == true) goto L5;
            return false;
        L5:
            if (DefaultHlsPlaylistTracker.access$2000(this.this$0) == true) goto L10;
            return true;
        L10:
            return false;
        }

        private Uri getMediaPlaylistUriForReload() {
            HlsMediaPlaylist r0 = this.playlistSnapshot;
            if (r0 == null) goto L29;
            HlsMediaPlaylist.ServerControl r02 = r0.serverControl;
            if (r02.skipUntilUs == (-9223372036854775807L)) goto L7;
        L9:
            Uri.Builder r03 = this.playlistUrl.buildUpon();
            HlsMediaPlaylist r1 = this.playlistSnapshot;
            if (r1.serverControl.canBlockReload == false) goto L19;
            r03.appendQueryParameter("_HLS_msn", String.valueOf(r1.mediaSequence + r1.segments.size()));
            HlsMediaPlaylist r12 = this.playlistSnapshot;
            if (r12.partTargetDurationUs == (-9223372036854775807L)) goto L19;
            List<HlsMediaPlaylist.Part> r13 = r12.trailingParts;
            int r2 = r13.size();
            if (r13.isEmpty() == false) goto L16;
        L18:
            r03.appendQueryParameter("_HLS_part", String.valueOf(r2));
            goto L19
        L16:
            if (((HlsMediaPlaylist.Part) Iterables.getLast(r13)).isPreload == false) goto L18;
            r2 = r2 - 1;
        L19:
            HlsMediaPlaylist.ServerControl r7 = this.playlistSnapshot.serverControl;
            if (r7.skipUntilUs == (-9223372036854775807L)) goto L27;
            if (r7.canSkipDateRanges == false) goto L24;
            String r72 = "v2";
        L25:
            r03.appendQueryParameter("_HLS_skip", r72);
            goto L27
        L24:
            r72 = "YES";
        L27:
            return r03.build();
        L7:
            if (r02.canBlockReload == true) goto L9;
        L29:
            return this.playlistUrl;
        }

        private void loadPlaylistImmediately(Uri r5) {
            ParsingLoadable.Parser<HlsPlaylist> r0 = DefaultHlsPlaylistTracker.access$1300(this.this$0).createPlaylistParser(DefaultHlsPlaylistTracker.access$1200(this.this$0), this.playlistSnapshot);
            DataSpec r52 = new DataSpec.Builder().setUri(r5).setFlags(1).build();
            DefaultHlsPlaylistTracker.access$1400(this.this$0);
            ParsingLoadable r1 = new ParsingLoadable(this.mediaPlaylistDataSource, r52, 4, r0);
            this.mediaPlaylistLoader.startLoading(r1, this, DefaultHlsPlaylistTracker.access$900(this.this$0).getMinimumLoadableRetryCount(r1.type));
        }

        private void loadPlaylistInternal(Uri r5) {
            this.excludeUntilMs = 0;
            if (this.loadPending == false) goto L5;
            return;
        L5:
            if (this.mediaPlaylistLoader.isLoading() == false) goto L7;
            return;
        L7:
            if (this.mediaPlaylistLoader.hasFatalError() == true) goto L17;
            long r0 = SystemClock.elapsedRealtime();
            if (r0 >= this.earliestNextLoadTimeMs) goto L13;
            this.loadPending = true;
            DefaultHlsPlaylistTracker.access$1100(this.this$0).postDelayed(new DefaultHlsPlaylistTracker$MediaPlaylistBundle$$ExternalSyntheticLambda0(this, r5), this.earliestNextLoadTimeMs - r0);
            return;
        L13:
            loadPlaylistImmediately(r5);
            return;
        }

        private void processLoadedPlaylist(HlsMediaPlaylist r13, LoadEventInfo r14) {
            HlsMediaPlaylist r0 = this.playlistSnapshot;
            long r1 = SystemClock.elapsedRealtime();
            this.lastSnapshotLoadMs = r1;
            HlsMediaPlaylist r3 = DefaultHlsPlaylistTracker.access$1600(this.this$0, r0, r13);
            this.playlistSnapshot = r3;
            IOException r4 = null;
            if (r3 == r0) goto L6;
            this.playlistError = null;
            this.lastSnapshotChangeMs = r1;
            DefaultHlsPlaylistTracker.access$1700(this.this$0, this.playlistUrl, r3);
        L15:
            HlsMediaPlaylist r132 = this.playlistSnapshot;
            if (r132.serverControl.canBlockReload == true) goto L20;
            if (r132 == r0) goto L19;
            long r32 = r132.targetDurationUs;
        L27:
            this.earliestNextLoadTimeMs = (r1 + Util.usToMs(r32)) - r14.loadDurationMs;
            if (this.playlistSnapshot.hasEndTag == false) goto L30;
            return;
        L30:
            if (this.playlistUrl.equals(DefaultHlsPlaylistTracker.access$1900(this.this$0)) == false) goto L32;
        L33:
            loadPlaylistInternal(getMediaPlaylistUriForReload());
            return;
        L32:
            if (this.activeForPlayback == true) goto L33;
            return;
        L19:
            r32 = r132.targetDurationUs / 2;
            goto L27
        L20:
            if (r132 != r0) goto L26;
            long r6 = r132.partTargetDurationUs;
            if (r6 == (-9223372036854775807L)) goto L25;
            long r62 = r6 / 2;
        L24:
            r32 = r62;
            goto L27
        L25:
            r62 = r132.targetDurationUs / 2;
            goto L24
        L26:
            r32 = 0;
            goto L27
        L6:
            if (r3.hasEndTag == true) goto L15;
            if ((r13.mediaSequence + r13.segments.size()) >= this.playlistSnapshot.mediaSequence) goto L10;
            r4 = new HlsPlaylistTracker.PlaylistResetException(this.playlistUrl);
            boolean r33 = true;
        L13:
            if (r4 == null) goto L15;
            this.playlistError = r4;
            DefaultHlsPlaylistTracker.access$1000(this.this$0, this.playlistUrl, new LoadErrorHandlingPolicy.LoadErrorInfo(r14, new MediaLoadData(4), r4, 1), r33);
            goto L15
        L10:
            r33 = false;
            if ((r1 - this.lastSnapshotChangeMs) <= (Util.usToMs(r13.targetDurationUs) * DefaultHlsPlaylistTracker.access$1800(this.this$0))) goto L13;
            r4 = new HlsPlaylistTracker.PlaylistStuckException(this.playlistUrl);
            goto L13
        }

        public HlsMediaPlaylist getPlaylistSnapshot() {
            return this.playlistSnapshot;
        }

        public boolean isActiveForPlayback() {
            return this.activeForPlayback;
        }

        public boolean isSnapshotValid() {
            if (this.playlistSnapshot != null) goto L5;
            return false;
        L5:
            long r2 = SystemClock.elapsedRealtime();
            long r4 = Math.max(NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS, Util.usToMs(this.playlistSnapshot.durationUs));
            HlsMediaPlaylist r0 = this.playlistSnapshot;
            if (r0.hasEndTag == true) goto L14;
            int r02 = r0.playlistType;
            if (r02 == 2) goto L14;
            if (r02 == 1) goto L14;
            if ((this.lastSnapshotLoadMs + r4) > r2) goto L14;
            return false;
        L14:
            return true;
        }

        public void loadPlaylist(boolean r1) {
            if (r1 == false) goto L4;
            Uri r12 = getMediaPlaylistUriForReload();
        L5:
            loadPlaylistInternal(r12);
            return;
        L4:
            r12 = this.playlistUrl;
            goto L5
        }

        public void maybeThrowPlaylistRefreshError() {
            this.mediaPlaylistLoader.maybeThrowError();
            IOException r1 = this.playlistError;
            if (r1 != null) goto L5;
            return;
        L5:
            throw r1;
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Callback
        public /* bridge */ /* synthetic */ void onLoadCanceled(Loader.Loadable r1, long r2, long r4, boolean r6) {
            onLoadCanceled((ParsingLoadable) r1, r2, r4, r6);
        }

        public void onLoadCanceled(ParsingLoadable<HlsPlaylist> r13, long r14, long r16, boolean r18) {
            LoadEventInfo r0 = new LoadEventInfo(r13.loadTaskId, r13.dataSpec, r13.getUri(), r13.getResponseHeaders(), r14, r16, r13.bytesLoaded());
            DefaultHlsPlaylistTracker.access$900(this.this$0).onLoadTaskConcluded(r13.loadTaskId);
            DefaultHlsPlaylistTracker.access$800(this.this$0).loadCanceled(r0, 4);
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Callback
        public /* bridge */ /* synthetic */ void onLoadCompleted(Loader.Loadable r1, long r2, long r4) {
            onLoadCompleted((ParsingLoadable) r1, r2, r4);
        }

        public void onLoadCompleted(ParsingLoadable<HlsPlaylist> r15, long r16, long r18) {
            HlsPlaylist r1 = r15.getResult();
            LoadEventInfo r2 = new LoadEventInfo(r15.loadTaskId, r15.dataSpec, r15.getUri(), r15.getResponseHeaders(), r16, r18, r15.bytesLoaded());
            if ((r1 instanceof HlsMediaPlaylist) == false) goto L5;
            processLoadedPlaylist((HlsMediaPlaylist) r1, r2);
            DefaultHlsPlaylistTracker.access$800(this.this$0).loadCompleted(r2, 4);
        L6:
            DefaultHlsPlaylistTracker.access$900(this.this$0).onLoadTaskConcluded(r15.loadTaskId);
            return;
        L5:
            this.playlistError = ParserException.createForMalformedManifest("Loaded playlist has unexpected type.", null);
            DefaultHlsPlaylistTracker.access$800(this.this$0).loadError(r2, 4, this.playlistError, true);
            goto L6
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Callback
        public /* bridge */ /* synthetic */ Loader.LoadErrorAction onLoadError(Loader.Loadable r1, long r2, long r4, IOException r6, int r7) {
            return onLoadError((ParsingLoadable) r1, r2, r4, r6, r7);
        }

        public Loader.LoadErrorAction onLoadError(ParsingLoadable<HlsPlaylist> r15, long r16, long r18, IOException r20, int r21) {
            LoadEventInfo r2 = new LoadEventInfo(r15.loadTaskId, r15.dataSpec, r15.getUri(), r15.getResponseHeaders(), r16, r18, r15.bytesLoaded());
            if (r15.getUri().getQueryParameter("_HLS_msn") == null) goto L5;
            boolean r3 = true;
        L6:
            boolean r6 = r20 instanceof HlsPlaylistParser.DeltaUpdateException;
            if (r3 == true) goto L10;
            if (r6 == true) goto L10;
        L19:
            LoadErrorHandlingPolicy.LoadErrorInfo r4 = new LoadErrorHandlingPolicy.LoadErrorInfo(r2, new MediaLoadData(r15.type), r20, r21);
            if (DefaultHlsPlaylistTracker.access$1000(this.this$0, this.playlistUrl, r4, false) == false) goto L25;
            long r32 = DefaultHlsPlaylistTracker.access$900(this.this$0).getRetryDelayMsFor(r4);
            if (r32 == (-9223372036854775807L)) goto L24;
            Loader.LoadErrorAction r33 = Loader.createRetryAction(false, r32);
        L26:
            boolean r42 = r33.isRetry();
            DefaultHlsPlaylistTracker.access$800(this.this$0).loadError(r2, r15.type, r20, !r42);
            if (r42 == true) goto L29;
            DefaultHlsPlaylistTracker.access$900(this.this$0).onLoadTaskConcluded(r15.loadTaskId);
        L29:
            return r33;
        L24:
            r33 = Loader.DONT_RETRY_FATAL;
            goto L26
        L25:
            r33 = Loader.DONT_RETRY;
        L10:
            if ((r20 instanceof HttpDataSource$InvalidResponseCodeException) == false) goto L12;
            int r34 = ((HttpDataSource$InvalidResponseCodeException) r20).responseCode;
        L13:
            if (r6 == false) goto L15;
        L30:
            this.earliestNextLoadTimeMs = SystemClock.elapsedRealtime();
            loadPlaylist(false);
            ((MediaSourceEventListener.EventDispatcher) Util.castNonNull(DefaultHlsPlaylistTracker.access$800(this.this$0))).loadError(r2, r15.type, r20, true);
            return Loader.DONT_RETRY;
        L15:
            if (r34 == 400) goto L30;
            if (r34 != 503) goto L19;
        L12:
            r34 = Integer.MAX_VALUE;
            goto L13
        L5:
            r3 = false;
            goto L6
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Callback
        public /* bridge */ /* synthetic */ void onLoadStarted(Loader.Loadable r1, long r2, long r4, int r6) {
            onLoadStarted((ParsingLoadable) r1, r2, r4, r6);
        }

        public void onLoadStarted(ParsingLoadable<HlsPlaylist> r16, long r17, long r19, int r21) {
            if (r21 != 0) goto L5;
            LoadEventInfo r2 = new LoadEventInfo(r16.loadTaskId, r16.dataSpec, r17);
        L6:
            DefaultHlsPlaylistTracker.access$800(this.this$0).loadStarted(r2, r16.type, r21);
            return;
        L5:
            r2 = new LoadEventInfo(r16.loadTaskId, r16.dataSpec, r16.getUri(), r16.getResponseHeaders(), r17, r19, r16.bytesLoaded());
            goto L6
        }

        public void release() {
            this.mediaPlaylistLoader.release();
        }

        public void setActiveForPlayback(boolean r1) {
            this.activeForPlayback = r1;
        }
    }

    static {
        FACTORY = new DefaultHlsPlaylistTracker$$ExternalSyntheticLambda0();
    }

    public DefaultHlsPlaylistTracker(HlsDataSourceFactory r8, LoadErrorHandlingPolicy r9, HlsPlaylistParserFactory r10, CmcdConfiguration r11) {
        this(r8, r9, r10, r11, 3.5d);
    }

    public DefaultHlsPlaylistTracker(HlsDataSourceFactory r1, LoadErrorHandlingPolicy r2, HlsPlaylistParserFactory r3, CmcdConfiguration r4, double r5) {
        this.dataSourceFactory = r1;
        this.playlistParserFactory = r3;
        this.loadErrorHandlingPolicy = r2;
        this.playlistStuckTargetDurationCoefficient = r5;
        this.listeners = new CopyOnWriteArrayList();
        this.playlistBundles = new HashMap();
        this.initialStartTimeUs = -9223372036854775807L;
    }

    public static /* synthetic */ boolean access$1000(DefaultHlsPlaylistTracker r0, Uri r1, LoadErrorHandlingPolicy.LoadErrorInfo r2, boolean r3) {
        return r0.notifyPlaylistError(r1, r2, r3);
    }

    public static /* synthetic */ Handler access$1100(DefaultHlsPlaylistTracker r0) {
        return r0.playlistRefreshHandler;
    }

    public static /* synthetic */ HlsMultivariantPlaylist access$1200(DefaultHlsPlaylistTracker r0) {
        return r0.multivariantPlaylist;
    }

    public static /* synthetic */ HlsPlaylistParserFactory access$1300(DefaultHlsPlaylistTracker r0) {
        return r0.playlistParserFactory;
    }

    public static /* synthetic */ CmcdConfiguration access$1400(DefaultHlsPlaylistTracker r0) {
        r0.getClass();
        return null;
    }

    public static /* synthetic */ HlsMediaPlaylist access$1500(DefaultHlsPlaylistTracker r0) {
        return r0.primaryMediaPlaylistSnapshot;
    }

    public static /* synthetic */ HlsMediaPlaylist access$1600(DefaultHlsPlaylistTracker r0, HlsMediaPlaylist r1, HlsMediaPlaylist r2) {
        return r0.getLatestPlaylistSnapshot(r1, r2);
    }

    public static /* synthetic */ void access$1700(DefaultHlsPlaylistTracker r0, Uri r1, HlsMediaPlaylist r2) {
        r0.onPlaylistUpdated(r1, r2);
    }

    public static /* synthetic */ double access$1800(DefaultHlsPlaylistTracker r2) {
        return r2.playlistStuckTargetDurationCoefficient;
    }

    public static /* synthetic */ Uri access$1900(DefaultHlsPlaylistTracker r0) {
        return r0.primaryMediaPlaylistUrl;
    }

    public static /* synthetic */ boolean access$2000(DefaultHlsPlaylistTracker r0) {
        return r0.maybeSelectNewPrimaryUrl();
    }

    public static /* synthetic */ CopyOnWriteArrayList access$2100(DefaultHlsPlaylistTracker r0) {
        return r0.listeners;
    }

    public static /* synthetic */ HashMap access$2200(DefaultHlsPlaylistTracker r0) {
        return r0.playlistBundles;
    }

    public static /* synthetic */ HlsDataSourceFactory access$700(DefaultHlsPlaylistTracker r0) {
        return r0.dataSourceFactory;
    }

    public static /* synthetic */ MediaSourceEventListener.EventDispatcher access$800(DefaultHlsPlaylistTracker r0) {
        return r0.eventDispatcher;
    }

    public static /* synthetic */ LoadErrorHandlingPolicy access$900(DefaultHlsPlaylistTracker r0) {
        return r0.loadErrorHandlingPolicy;
    }

    private void createBundles(List<Uri> r6) {
        int r0 = r6.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        Uri r2 = r6.get(r1);
        MediaPlaylistBundle r3 = new MediaPlaylistBundle(this, r2);
        this.playlistBundles.put(r2, r3);
        r1 = r1 + 1;
        goto L3
    }

    private static HlsMediaPlaylist.Segment getFirstOldOverlappingSegment(HlsMediaPlaylist r4, HlsMediaPlaylist r5) {
        int r52 = (int) (r5.mediaSequence - r4.mediaSequence);
        List<HlsMediaPlaylist.Segment> r42 = r4.segments;
        if (r52 < r42.size()) goto L5;
        return null;
    L5:
        return r42.get(r52);
    }

    private HlsMediaPlaylist getLatestPlaylistSnapshot(HlsMediaPlaylist r3, HlsMediaPlaylist r4) {
        if (r4.isNewerThan(r3) == true) goto L10;
        if (r4.hasEndTag == true) goto L7;
        return r3;
    L7:
        return r3.copyWithEndTag();
    L10:
        return r4.copyWith(getLoadedPlaylistStartTimeUs(r3, r4), getLoadedPlaylistDiscontinuitySequence(r3, r4));
    }

    private int getLoadedPlaylistDiscontinuitySequence(HlsMediaPlaylist r3, HlsMediaPlaylist r4) {
        if (r4.hasDiscontinuitySequence == true) goto L5;
        HlsMediaPlaylist r2 = this.primaryMediaPlaylistSnapshot;
        if (r2 == null) goto L9;
        int r22 = r2.discontinuitySequence;
    L10:
        if (r3 == null) goto L17;
        HlsMediaPlaylist.Segment r1 = getFirstOldOverlappingSegment(r3, r4);
        if (r1 != null) goto L15;
        return r22;
    L15:
        return (r3.discontinuitySequence + r1.relativeDiscontinuitySequence) - r4.segments.get(0).relativeDiscontinuitySequence;
    L17:
        return r22;
    L9:
        r22 = 0;
        goto L10
    L5:
        return r4.discontinuitySequence;
    }

    private long getLoadedPlaylistStartTimeUs(HlsMediaPlaylist r9, HlsMediaPlaylist r10) {
        if (r10.hasProgramDateTime == true) goto L5;
        HlsMediaPlaylist r8 = this.primaryMediaPlaylistSnapshot;
        if (r8 == null) goto L9;
        long r0 = r8.startTimeUs;
    L10:
        if (r9 == null) goto L20;
        int r82 = r9.segments.size();
        HlsMediaPlaylist.Segment r2 = getFirstOldOverlappingSegment(r9, r10);
        if (r2 == null) goto L17;
        return r9.startTimeUs + r2.relativeStartTimeUs;
    L17:
        if (r82 != (r10.mediaSequence - r9.mediaSequence)) goto L20;
        return r9.getEndTimeUs();
    L20:
        return r0;
    L9:
        r0 = 0;
        goto L10
    L5:
        return r10.startTimeUs;
    }

    private Uri getRequestUriForPrimaryChange(Uri r3) {
        HlsMediaPlaylist r2 = this.primaryMediaPlaylistSnapshot;
        if (r2 != null) goto L5;
    L13:
        return r3;
    L5:
        if (r2.serverControl.canBlockReload == false) goto L13;
        HlsMediaPlaylist.RenditionReport r22 = r2.renditionReports.get(r3);
        if (r22 == null) goto L13;
        Uri.Builder r32 = r3.buildUpon();
        r32.appendQueryParameter("_HLS_msn", String.valueOf(r22.lastMediaSequence));
        int r23 = r22.lastPartIndex;
        if (r23 == (-1)) goto L12;
        r32.appendQueryParameter("_HLS_part", String.valueOf(r23));
    L12:
        return r32.build();
    }

    private boolean isVariantUrl(Uri r4) {
        List<HlsMultivariantPlaylist.Variant> r3 = this.multivariantPlaylist.variants;
        int r1 = 0;
    L4:
        if (r1 >= r3.size()) goto L10;
        if (r4.equals(r3.get(r1).url) == true) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return true;
    L10:
        return false;
    }

    private void maybeActivateForPlayback(Uri r2) {
        MediaPlaylistBundle r1 = this.playlistBundles.get(r2);
        HlsMediaPlaylist r22 = r1.getPlaylistSnapshot();
        if (r1.isActiveForPlayback() == true) goto L13;
        r1.setActiveForPlayback(true);
        if (r22 != null) goto L8;
        return;
    L8:
        if (r22.hasEndTag == true) goto L12;
        r1.loadPlaylist(true);
        return;
    L12:
        return;
    }

    private boolean maybeSelectNewPrimaryUrl() {
        List<HlsMultivariantPlaylist.Variant> r0 = this.multivariantPlaylist.variants;
        int r1 = r0.size();
        long r2 = SystemClock.elapsedRealtime();
        int r5 = 0;
    L3:
        if (r5 >= r1) goto L9;
        MediaPlaylistBundle r6 = (MediaPlaylistBundle) Assertions.checkNotNull(this.playlistBundles.get(r0.get(r5).url));
        if (r2 > MediaPlaylistBundle.access$300(r6)) goto L6;
        r5 = r5 + 1;
        goto L3
    L6:
        Uri r02 = MediaPlaylistBundle.access$400(r6);
        this.primaryMediaPlaylistUrl = r02;
        MediaPlaylistBundle.access$500(r6, getRequestUriForPrimaryChange(r02));
        return true;
    L9:
        return false;
    }

    private void maybeSetPrimaryUrl(Uri r4) {
        if (r4.equals(this.primaryMediaPlaylistUrl) == false) goto L5;
        return;
    L5:
        if (isVariantUrl(r4) == false) goto L20;
        HlsMediaPlaylist r0 = this.primaryMediaPlaylistSnapshot;
        if (r0 != null) goto L9;
    L11:
        this.primaryMediaPlaylistUrl = r4;
        MediaPlaylistBundle r02 = this.playlistBundles.get(r4);
        HlsMediaPlaylist r1 = MediaPlaylistBundle.access$600(r02);
        if (r1 != null) goto L14;
    L17:
        MediaPlaylistBundle.access$500(r02, getRequestUriForPrimaryChange(r4));
        return;
    L14:
        if (r1.hasEndTag == false) goto L17;
        this.primaryMediaPlaylistSnapshot = r1;
        this.primaryPlaylistListener.onPrimaryPlaylistRefreshed(r1);
        return;
    L9:
        if (r0.hasEndTag == false) goto L11;
        return;
    }

    private boolean notifyPlaylistError(Uri r3, LoadErrorHandlingPolicy.LoadErrorInfo r4, boolean r5) {
        Iterator<HlsPlaylistTracker.PlaylistEventListener> r2 = this.listeners.iterator();
        boolean r0 = false;
    L4:
        if (r2.hasNext() == false) goto L6;
        r0 = r0 | (!r2.next().onPlaylistError(r3, r4, r5));
        goto L4
    L6:
        return r0;
    }

    private void onPlaylistUpdated(Uri r3, HlsMediaPlaylist r4) {
        if (r3.equals(this.primaryMediaPlaylistUrl) == true) goto L5;
    L8:
        Iterator<HlsPlaylistTracker.PlaylistEventListener> r2 = this.listeners.iterator();
    L10:
        if (r2.hasNext() == false) goto L12;
        r2.next().onPlaylistChanged();
        goto L10
    L12:
        return;
    L5:
        if (this.primaryMediaPlaylistSnapshot != null) goto L7;
        this.isLive = !r4.hasEndTag;
        this.initialStartTimeUs = r4.startTimeUs;
    L7:
        this.primaryMediaPlaylistSnapshot = r4;
        this.primaryPlaylistListener.onPrimaryPlaylistRefreshed(r4);
        goto L8
    }

    @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker
    public void addListener(HlsPlaylistTracker.PlaylistEventListener r1) {
        Assertions.checkNotNull(r1);
        this.listeners.add(r1);
    }

    @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker
    public void deactivatePlaylistForPlayback(Uri r1) {
        MediaPlaylistBundle r0 = this.playlistBundles.get(r1);
        if (r0 == null) goto L6;
        r0.setActiveForPlayback(false);
        return;
    }

    @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker
    public boolean excludeMediaPlaylist(Uri r1, long r2) {
        if (this.playlistBundles.get(r1) != null) goto L5;
        return false;
    L5:
        return !MediaPlaylistBundle.access$000(r0, r2);
    }

    @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker
    public long getInitialStartTimeUs() {
        return this.initialStartTimeUs;
    }

    @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker
    public HlsMultivariantPlaylist getMultivariantPlaylist() {
        return this.multivariantPlaylist;
    }

    @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker
    public HlsMediaPlaylist getPlaylistSnapshot(Uri r2, boolean r3) {
        HlsMediaPlaylist r0 = this.playlistBundles.get(r2).getPlaylistSnapshot();
        if (r0 == null) goto L6;
        if (r3 == false) goto L6;
        maybeSetPrimaryUrl(r2);
        maybeActivateForPlayback(r2);
    L6:
        return r0;
    }

    @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker
    public boolean isLive() {
        return this.isLive;
    }

    @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker
    public boolean isSnapshotValid(Uri r1) {
        return this.playlistBundles.get(r1).isSnapshotValid();
    }

    @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker
    public void maybeThrowPlaylistRefreshError(Uri r1) {
        this.playlistBundles.get(r1).maybeThrowPlaylistRefreshError();
    }

    @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker
    public void maybeThrowPrimaryPlaylistRefreshError() {
        Loader r0 = this.initialPlaylistLoader;
        if (r0 == null) goto L5;
        r0.maybeThrowError();
    L5:
        Uri r02 = this.primaryMediaPlaylistUrl;
        if (r02 == null) goto L9;
        maybeThrowPlaylistRefreshError(r02);
        return;
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public /* bridge */ /* synthetic */ void onLoadCanceled(Loader.Loadable r1, long r2, long r4, boolean r6) {
        onLoadCanceled((ParsingLoadable) r1, r2, r4, r6);
    }

    public void onLoadCanceled(ParsingLoadable<HlsPlaylist> r13, long r14, long r16, boolean r18) {
        LoadEventInfo r0 = new LoadEventInfo(r13.loadTaskId, r13.dataSpec, r13.getUri(), r13.getResponseHeaders(), r14, r16, r13.bytesLoaded());
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(r13.loadTaskId);
        this.eventDispatcher.loadCanceled(r0, 4);
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public /* bridge */ /* synthetic */ void onLoadCompleted(Loader.Loadable r1, long r2, long r4) {
        onLoadCompleted((ParsingLoadable) r1, r2, r4);
    }

    public void onLoadCompleted(ParsingLoadable<HlsPlaylist> r20, long r21, long r23) {
        HlsPlaylist r2 = r20.getResult();
        boolean r3 = r2 instanceof HlsMediaPlaylist;
        if (r3 == false) goto L5;
        HlsMultivariantPlaylist r4 = HlsMultivariantPlaylist.createSingleVariantMultivariantPlaylist(r2.baseUri);
    L6:
        this.multivariantPlaylist = r4;
        this.primaryMediaPlaylistUrl = r4.variants.get(0).url;
        this.listeners.add(new FirstPrimaryMediaPlaylistListener(this, null));
        createBundles(r4.mediaPlaylistUrls);
        LoadEventInfo r7 = new LoadEventInfo(r20.loadTaskId, r20.dataSpec, r20.getUri(), r20.getResponseHeaders(), r21, r23, r20.bytesLoaded());
        MediaPlaylistBundle r42 = this.playlistBundles.get(this.primaryMediaPlaylistUrl);
        if (r3 == false) goto L9;
        MediaPlaylistBundle.access$200(r42, (HlsMediaPlaylist) r2, r7);
    L10:
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(r20.loadTaskId);
        this.eventDispatcher.loadCompleted(r7, 4);
        return;
    L9:
        r42.loadPlaylist(false);
        goto L10
    L5:
        r4 = (HlsMultivariantPlaylist) r2;
        goto L6
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public /* bridge */ /* synthetic */ Loader.LoadErrorAction onLoadError(Loader.Loadable r1, long r2, long r4, IOException r6, int r7) {
        return onLoadError((ParsingLoadable) r1, r2, r4, r6, r7);
    }

    public Loader.LoadErrorAction onLoadError(ParsingLoadable<HlsPlaylist> r15, long r16, long r18, IOException r20, int r21) {
        LoadEventInfo r2 = new LoadEventInfo(r15.loadTaskId, r15.dataSpec, r15.getUri(), r15.getResponseHeaders(), r16, r18, r15.bytesLoaded());
        long r3 = this.loadErrorHandlingPolicy.getRetryDelayMsFor(new LoadErrorHandlingPolicy.LoadErrorInfo(r2, new MediaLoadData(r15.type), r20, r21));
        if (r3 != (-9223372036854775807L)) goto L5;
        boolean r5 = true;
    L6:
        this.eventDispatcher.loadError(r2, r15.type, r20, r5);
        if (r5 == false) goto L9;
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(r15.loadTaskId);
    L9:
        if (r5 == false) goto L13;
        return Loader.DONT_RETRY_FATAL;
    L13:
        return Loader.createRetryAction(false, r3);
    L5:
        r5 = false;
        goto L6
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public /* bridge */ /* synthetic */ void onLoadStarted(Loader.Loadable r1, long r2, long r4, int r6) {
        onLoadStarted((ParsingLoadable) r1, r2, r4, r6);
    }

    public void onLoadStarted(ParsingLoadable<HlsPlaylist> r16, long r17, long r19, int r21) {
        if (r21 != 0) goto L5;
        LoadEventInfo r2 = new LoadEventInfo(r16.loadTaskId, r16.dataSpec, r17);
    L6:
        this.eventDispatcher.loadStarted(r2, r16.type, r21);
        return;
    L5:
        r2 = new LoadEventInfo(r16.loadTaskId, r16.dataSpec, r16.getUri(), r16.getResponseHeaders(), r17, r19, r16.bytesLoaded());
        goto L6
    }

    @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker
    public void refreshPlaylist(Uri r1) {
        this.playlistBundles.get(r1).loadPlaylist(true);
    }

    @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker
    public void removeListener(HlsPlaylistTracker.PlaylistEventListener r1) {
        this.listeners.remove(r1);
    }

    @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker
    public void start(Uri r4, MediaSourceEventListener.EventDispatcher r5, HlsPlaylistTracker.PrimaryPlaylistListener r6) {
        this.playlistRefreshHandler = Util.createHandlerForCurrentLooper();
        this.eventDispatcher = r5;
        this.primaryPlaylistListener = r6;
        DataSpec.Builder r42 = new DataSpec.Builder().setUri(r4);
        boolean r52 = true;
        DataSpec r43 = r42.setFlags(1).build();
        ParsingLoadable r62 = new ParsingLoadable(this.dataSourceFactory.createDataSource(4), r43, 4, this.playlistParserFactory.createPlaylistParser());
        if (this.initialPlaylistLoader == null) goto L6;
        r52 = false;
    L6:
        Assertions.checkState(r52);
        Loader r44 = new Loader("DefaultHlsPlaylistTracker:MultivariantPlaylist");
        this.initialPlaylistLoader = r44;
        r44.startLoading(r62, this, this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(r62.type));
    }

    @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker
    public void stop() {
        this.primaryMediaPlaylistUrl = null;
        this.primaryMediaPlaylistSnapshot = null;
        this.multivariantPlaylist = null;
        this.initialStartTimeUs = -9223372036854775807L;
        this.initialPlaylistLoader.release();
        this.initialPlaylistLoader = null;
        Iterator<MediaPlaylistBundle> r1 = this.playlistBundles.values().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().release();
        goto L4
    L6:
        this.playlistRefreshHandler.removeCallbacksAndMessages(null);
        this.playlistRefreshHandler = null;
        this.playlistBundles.clear();
    }
}
