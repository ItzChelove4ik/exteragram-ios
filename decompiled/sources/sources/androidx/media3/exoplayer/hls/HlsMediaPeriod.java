package androidx.media3.exoplayer.hls;

import android.net.Uri;
import android.text.TextUtils;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Label;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.hls.HlsSampleStreamWrapper;
import androidx.media3.exoplayer.hls.playlist.HlsMultivariantPlaylist;
import androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker;
import androidx.media3.exoplayer.source.CompositeSequenceableLoaderFactory;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.source.SampleStream;
import androidx.media3.exoplayer.source.SequenceableLoader;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import com.google.android.gms.cast.MediaTrack;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes4.dex */
final class HlsMediaPeriod implements MediaPeriod, HlsPlaylistTracker.PlaylistEventListener {
    private final Allocator allocator;
    private final boolean allowChunklessPreparation;
    private int audioVideoSampleStreamWrapperCount;
    private SequenceableLoader compositeSequenceableLoader;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final HlsDataSourceFactory dataSourceFactory;
    private final DrmSessionEventListener.EventDispatcher drmEventDispatcher;
    private final DrmSessionManager drmSessionManager;
    private HlsSampleStreamWrapper[] enabledSampleStreamWrappers;
    private final MediaSourceEventListener.EventDispatcher eventDispatcher;
    private final HlsExtractorFactory extractorFactory;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private int[][] manifestUrlIndicesPerWrapper;
    private MediaPeriod.Callback mediaPeriodCallback;
    private final TransferListener mediaTransferListener;
    private final int metadataType;
    private int pendingPrepareCount;
    private final PlayerId playerId;
    private final HlsPlaylistTracker playlistTracker;
    private final HlsSampleStreamWrapper.Callback sampleStreamWrapperCallback;
    private HlsSampleStreamWrapper[] sampleStreamWrappers;
    private final IdentityHashMap<SampleStream, Integer> streamWrapperIndices;
    private final long timestampAdjusterInitializationTimeoutMs;
    private final TimestampAdjusterProvider timestampAdjusterProvider;
    private TrackGroupArray trackGroups;
    private final boolean useSessionKeys;

    /* renamed from: androidx.media3.exoplayer.hls.HlsMediaPeriod$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public class SampleStreamWrapperCallback implements HlsSampleStreamWrapper.Callback {
        final /* synthetic */ HlsMediaPeriod this$0;

        private SampleStreamWrapperCallback(HlsMediaPeriod r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ SampleStreamWrapperCallback(HlsMediaPeriod r1, AnonymousClass1 r2) {
            this(r1);
        }

        public void onContinueLoadingRequested(HlsSampleStreamWrapper r1) {
            HlsMediaPeriod.access$400(this.this$0).onContinueLoadingRequested(this.this$0);
        }

        @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
        public /* bridge */ /* synthetic */ void onContinueLoadingRequested(SequenceableLoader r1) {
            onContinueLoadingRequested((HlsSampleStreamWrapper) r1);
        }

        @Override // androidx.media3.exoplayer.hls.HlsSampleStreamWrapper.Callback
        public void onPlaylistRefreshRequired(Uri r1) {
            HlsMediaPeriod.access$500(this.this$0).refreshPlaylist(r1);
        }

        @Override // androidx.media3.exoplayer.hls.HlsSampleStreamWrapper.Callback
        public void onPrepared() {
            if (HlsMediaPeriod.access$106(this.this$0) <= 0) goto L5;
            return;
        L5:
            HlsSampleStreamWrapper[] r0 = HlsMediaPeriod.access$200(this.this$0);
            int r1 = r0.length;
            int r3 = 0;
            int r4 = 0;
        L6:
            if (r3 >= r1) goto L8;
            r4 = r4 + r0[r3].getTrackGroups().length;
            r3 = r3 + 1;
            goto L6
        L8:
            TrackGroup[] r02 = new TrackGroup[r4];
            HlsSampleStreamWrapper[] r12 = HlsMediaPeriod.access$200(this.this$0);
            int r32 = r12.length;
            int r42 = 0;
            int r5 = 0;
        L9:
            if (r42 >= r32) goto L14;
            HlsSampleStreamWrapper r6 = r12[r42];
            int r7 = r6.getTrackGroups().length;
            int r8 = 0;
        L11:
            if (r8 >= r7) goto L13;
            r02[r5] = r6.getTrackGroups().get(r8);
            r8 = r8 + 1;
            r5 = r5 + 1;
            goto L11
        L13:
            r42 = r42 + 1;
            goto L9
        L14:
            HlsMediaPeriod.access$302(this.this$0, new TrackGroupArray(r02));
            HlsMediaPeriod.access$400(this.this$0).onPrepared(this.this$0);
        }
    }

    public static /* synthetic */ List $r8$lambda$qsliuuup48tW5jDANMPYqBAx4tQ(HlsSampleStreamWrapper r0) {
        return r0.getTrackGroups().getTrackTypes();
    }

    public HlsMediaPeriod(HlsExtractorFactory r1, HlsPlaylistTracker r2, HlsDataSourceFactory r3, TransferListener r4, CmcdConfiguration r5, DrmSessionManager r6, DrmSessionEventListener.EventDispatcher r7, LoadErrorHandlingPolicy r8, MediaSourceEventListener.EventDispatcher r9, Allocator r10, CompositeSequenceableLoaderFactory r11, boolean r12, int r13, boolean r14, PlayerId r15, long r16) {
        this.extractorFactory = r1;
        this.playlistTracker = r2;
        this.dataSourceFactory = r3;
        this.mediaTransferListener = r4;
        this.drmSessionManager = r6;
        this.drmEventDispatcher = r7;
        this.loadErrorHandlingPolicy = r8;
        this.eventDispatcher = r9;
        this.allocator = r10;
        this.compositeSequenceableLoaderFactory = r11;
        this.allowChunklessPreparation = r12;
        this.metadataType = r13;
        this.useSessionKeys = r14;
        this.playerId = r15;
        this.timestampAdjusterInitializationTimeoutMs = r16;
        this.sampleStreamWrapperCallback = new SampleStreamWrapperCallback(this, null);
        this.compositeSequenceableLoader = r11.empty();
        this.streamWrapperIndices = new IdentityHashMap();
        this.timestampAdjusterProvider = new TimestampAdjusterProvider();
        this.sampleStreamWrappers = new HlsSampleStreamWrapper[0];
        this.enabledSampleStreamWrappers = new HlsSampleStreamWrapper[0];
        this.manifestUrlIndicesPerWrapper = new int[0][];
    }

    public static /* synthetic */ int access$106(HlsMediaPeriod r1) {
        int r0 = r1.pendingPrepareCount - 1;
        r1.pendingPrepareCount = r0;
        return r0;
    }

    public static /* synthetic */ HlsSampleStreamWrapper[] access$200(HlsMediaPeriod r0) {
        return r0.sampleStreamWrappers;
    }

    public static /* synthetic */ TrackGroupArray access$302(HlsMediaPeriod r0, TrackGroupArray r1) {
        r0.trackGroups = r1;
        return r1;
    }

    public static /* synthetic */ MediaPeriod.Callback access$400(HlsMediaPeriod r0) {
        return r0.mediaPeriodCallback;
    }

    public static /* synthetic */ HlsPlaylistTracker access$500(HlsMediaPeriod r0) {
        return r0.playlistTracker;
    }

    private void buildAndPrepareAudioSampleStreamWrappers(long r22, List<HlsMultivariantPlaylist.Rendition> r24, List<HlsSampleStreamWrapper> r25, List<int[]> r26, Map<String, DrmInitData> r27) {
        ArrayList r1 = new ArrayList(r24.size());
        ArrayList r2 = new ArrayList(r24.size());
        ArrayList r3 = new ArrayList(r24.size());
        HashSet r4 = new HashSet();
        int r6 = 0;
    L4:
        if (r6 >= r24.size()) goto L24;
        String r7 = r24.get(r6).name;
        if (r4.add(r7) == false) goto L23;
        r1.clear();
        r2.clear();
        r3.clear();
        int r9 = 0;
        boolean r10 = true;
    L10:
        if (r9 >= r24.size()) goto L19;
        if (Objects.equals(r7, r24.get(r9).name) == false) goto L18;
        HlsMultivariantPlaylist.Rendition r11 = r24.get(r9);
        r3.add(Integer.valueOf(r9));
        r1.add(r11.url);
        r2.add(r11.format);
        if (Util.getCodecCountOfType(r11.format.codecs, 1) != 1) goto L16;
        boolean r112 = true;
    L17:
        r10 = r10 & r112;
        goto L18
    L16:
        r112 = false;
    L18:
        r9 = r9 + 1;
        goto L10
    L19:
        String r12 = "audio:" + r7;
        HlsSampleStreamWrapper r72 = buildSampleStreamWrapper(r12, 1, (Uri[]) r1.toArray((Uri[]) Util.castNonNullTypeArray(new Uri[0])), (Format[]) r2.toArray(new Format[0]), null, Collections.EMPTY_LIST, r27, r22);
        r26.add(Ints.toArray(r3));
        r25.add(r72);
        if (this.allowChunklessPreparation == false) goto L23;
        if (r10 == false) goto L23;
        r72.prepareWithMultivariantPlaylistInfo(new TrackGroup[]{new TrackGroup(r12, (Format[]) r2.toArray(new Format[0]))}, 0, new int[0]);
    L23:
        r6 = r6 + 1;
        goto L4
    }

    private void buildAndPrepareMainSampleStreamWrapper(HlsMultivariantPlaylist r19, long r20, List<HlsSampleStreamWrapper> r22, List<int[]> r23, Map<String, DrmInitData> r24) {
        int r0 = r19.variants.size();
        int[] r1 = new int[r0];
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
    L4:
        if (r2 >= r19.variants.size()) goto L16;
        Format r5 = r19.variants.get(r2).format;
        if (r5.height <= 0) goto L8;
    L14:
        r1[r2] = 2;
        r3 = r3 + 1;
    L15:
        r2 = r2 + 1;
        goto L4
    L8:
        if (Util.getCodecsOfType(r5.codecs, 2) != null) goto L14;
        if (Util.getCodecsOfType(r5.codecs, 1) == null) goto L13;
        r1[r2] = 1;
        r4 = r4 + 1;
        goto L15
    L13:
        r1[r2] = -1;
        goto L15
    L16:
        if (r3 <= 0) goto L18;
        int r13 = r3;
        boolean r25 = false;
        boolean r02 = true;
    L21:
        Uri[] r32 = new Uri[r13];
        Format[] r42 = new Format[r13];
        int[] r14 = new int[r13];
        int r52 = 0;
        int r7 = 0;
    L23:
        if (r52 >= r19.variants.size()) goto L32;
        if (r02 == true) goto L26;
    L27:
        if (r25 == true) goto L29;
    L30:
        HlsMultivariantPlaylist.Variant r8 = r19.variants.get(r52);
        r32[r7] = r8.url;
        r42[r7] = r8.format;
        r14[r7] = r52;
        r7 = r7 + 1;
    L31:
        r52 = r52 + 1;
        goto L23
    L29:
        if (r1[r52] == 1) goto L31;
    L26:
        if (r1[r52] != 2) goto L31;
    L32:
        String r12 = r42[0].codecs;
        int r15 = Util.getCodecCountOfType(r12, 2);
        int r16 = Util.getCodecCountOfType(r12, 1);
        if (r16 == 1) goto L37;
        if (r16 == 0) goto L36;
    L41:
        boolean r162 = false;
    L42:
        if (r02 == true) goto L45;
        if (r16 <= 0) goto L45;
        int r26 = 1;
    L46:
        HlsSampleStreamWrapper r27 = buildSampleStreamWrapper(MediaTrack.ROLE_MAIN, r26, r32, r42, r19.muxedAudioFormat, r19.muxedCaptionFormats, r24, r20);
        r22.add(r27);
        r23.add(r14);
        if (this.allowChunklessPreparation == false) goto L84;
        if (r162 == false) goto L85;
        ArrayList r17 = new ArrayList();
        if (r15 <= 0) goto L67;
        Format[] r53 = new Format[r13];
        int r6 = 0;
    L52:
        if (r6 >= r13) goto L54;
        r53[r6] = deriveVideoFormat(r42[r6]);
        r6 = r6 + 1;
        goto L52
    L54:
        r17.add(new TrackGroup(MediaTrack.ROLE_MAIN, r53));
        if (r16 > 0) goto L57;
    L61:
        List<Format> r33 = r19.muxedCaptionFormats;
        if (r33 == null) goto L71;
        int r43 = 0;
    L65:
        if (r43 >= r33.size()) goto L71;
        r17.add(new TrackGroup("main:cc:" + r43, new Format[]{this.extractorFactory.getOutputTextFormat(r33.get(r43))}));
        r43 = r43 + 1;
    L71:
        TrackGroup r03 = new TrackGroup("main:id3", new Format[]{new Format.Builder().setId("ID3").setSampleMimeType("application/id3").build()});
        r17.add(r03);
        r27.prepareWithMultivariantPlaylistInfo((TrackGroup[]) r17.toArray(new TrackGroup[0]), 0, new int[]{r17.indexOf(r03)});
        return;
    L57:
        if (r19.muxedAudioFormat == null) goto L59;
    L60:
        r17.add(new TrackGroup("main:audio", new Format[]{deriveAudioFormat(r42[0], r19.muxedAudioFormat, false)}));
        goto L61
    L59:
        if (r19.audios.isEmpty() == false) goto L61;
    L67:
        Format[] r04 = new Format[r13];
        int r54 = 0;
    L68:
        if (r54 >= r13) goto L70;
        r04[r54] = deriveAudioFormat(r42[r54], r19.muxedAudioFormat, true);
        r54 = r54 + 1;
        goto L68
    L70:
        r17.add(new TrackGroup(MediaTrack.ROLE_MAIN, r04));
        goto L71
    L85:
        return;
    L84:
        return;
    L45:
        r26 = 0;
        goto L46
    L36:
        if (r19.audios.isEmpty() == false) goto L41;
    L37:
        if (r15 > 1) goto L41;
        if ((r16 + r15) <= 0) goto L41;
        r162 = true;
        goto L42
    L18:
        if (r4 >= r0) goto L20;
        r13 = r0 - r4;
        r02 = false;
        r25 = true;
        goto L21
    L20:
        r13 = r0;
        r02 = false;
        r25 = false;
        goto L21
    }

    private void buildAndPrepareSampleStreamWrappers(long r13) {
        HlsMultivariantPlaylist r2 = (HlsMultivariantPlaylist) Assertions.checkNotNull(this.playlistTracker.getMultivariantPlaylist());
        if (this.useSessionKeys == false) goto L6;
        Map<String, DrmInitData> r0 = deriveOverridingDrmInitData(r2.sessionKeyDrmInitData);
    L5:
        Map<String, DrmInitData> r9 = r0;
        boolean r02 = r2.variants.isEmpty();
        List<HlsMultivariantPlaylist.Rendition> r8 = r2.audios;
        List<HlsMultivariantPlaylist.Rendition> r10 = r2.subtitles;
        int r11 = 0;
        this.pendingPrepareCount = 0;
        ArrayList r7 = new ArrayList();
        ArrayList r6 = new ArrayList();
        if (r02 == true) goto L10;
        buildAndPrepareMainSampleStreamWrapper(r2, r13, r7, r6, r9);
        r7 = r7;
        long r4 = r13;
        ArrayList r82 = r6;
        List<HlsMultivariantPlaylist.Rendition> r62 = r8;
        HlsMediaPeriod r3 = this;
    L11:
        r3.buildAndPrepareAudioSampleStreamWrappers(r4, r62, r7, r82, r9);
        r3.audioVideoSampleStreamWrapperCount = r7.size();
        r3.buildAndPrepareSubtitleSampleStreamWrappers(r4, r10, r7, r82, r9);
        r3.sampleStreamWrappers = (HlsSampleStreamWrapper[]) r7.toArray(new HlsSampleStreamWrapper[0]);
        r3.manifestUrlIndicesPerWrapper = (int[][]) r82.toArray(new int[0][]);
        r3.pendingPrepareCount = r3.sampleStreamWrappers.length;
        int r12 = 0;
    L12:
        int r132 = r3.audioVideoSampleStreamWrapperCount;
        HlsSampleStreamWrapper[] r14 = r3.sampleStreamWrappers;
        if (r12 >= r132) goto L15;
        r14[r12].setIsPrimaryTimestampSource(true);
        r12 = r12 + 1;
        goto L12
    L15:
        int r122 = r14.length;
    L16:
        if (r11 >= r122) goto L18;
        r14[r11].continuePreparing();
        r11 = r11 + 1;
        goto L16
    L18:
        r3.enabledSampleStreamWrappers = r3.sampleStreamWrappers;
        return;
    L10:
        r82 = r6;
        r62 = r8;
        r3 = this;
        r4 = r13;
        goto L11
    L6:
        r0 = Collections.EMPTY_MAP;
        goto L5
    }

    private void buildAndPrepareSubtitleSampleStreamWrappers(long r21, List<HlsMultivariantPlaylist.Rendition> r23, List<HlsSampleStreamWrapper> r24, List<int[]> r25, Map<String, DrmInitData> r26) {
        List<HlsMultivariantPlaylist.Rendition> r0 = r23;
        ArrayList r1 = new ArrayList(r0.size());
        ArrayList r2 = new ArrayList(r0.size());
        ArrayList r3 = new ArrayList(r0.size());
        HashSet r4 = new HashSet();
        int r5 = 0;
        int r6 = 0;
    L4:
        if (r6 >= r0.size()) goto L20;
        String r7 = r0.get(r6).name;
        if (r4.add(r7) == false) goto L19;
        r1.clear();
        r2.clear();
        r3.clear();
        int r8 = r5;
    L10:
        if (r8 >= r0.size()) goto L15;
        if (Objects.equals(r7, r0.get(r8).name) == false) goto L14;
        HlsMultivariantPlaylist.Rendition r9 = r0.get(r8);
        r3.add(Integer.valueOf(r8));
        r1.add(r9.url);
        r2.add(r9.format);
    L14:
        r8 = r8 + 1;
        goto L10
    L15:
        String r11 = "subtitle:" + r7;
        Format[] r14 = (Format[]) r2.toArray(new Format[r5]);
        HlsSampleStreamWrapper r72 = buildSampleStreamWrapper(r11, 3, (Uri[]) r1.toArray((Uri[]) Util.castNonNullTypeArray(new Uri[r5])), r14, null, ImmutableList.of(), r26, r21);
        r25.add(Ints.toArray(r3));
        r24.add(r72);
        int r10 = r14.length;
        Format[] r12 = new Format[r10];
        int r13 = r5;
    L16:
        if (r13 >= r10) goto L18;
        r12[r13] = this.extractorFactory.getOutputTextFormat(r14[r13]);
        r13 = r13 + 1;
        goto L16
    L18:
        r5 = 0;
        r72.prepareWithMultivariantPlaylistInfo(new TrackGroup[]{new TrackGroup(r11, r12)}, 0, new int[0]);
    L19:
        r6 = r6 + 1;
        r0 = r23;
        goto L4
    }

    private HlsSampleStreamWrapper buildSampleStreamWrapper(String r16, int r17, Uri[] r18, Format[] r19, Format r20, List<Format> r21, Map<String, DrmInitData> r22, long r23) {
        HlsChunkSource r0 = new HlsChunkSource(this.extractorFactory, this.playlistTracker, r18, r19, this.dataSourceFactory, this.mediaTransferListener, this.timestampAdjusterProvider, this.timestampAdjusterInitializationTimeoutMs, r21, this.playerId, null);
        return new HlsSampleStreamWrapper(r16, r17, this.sampleStreamWrapperCallback, r0, r22, this.allocator, r23, r20, this.drmSessionManager, this.drmEventDispatcher, this.loadErrorHandlingPolicy, this.eventDispatcher, this.metadataType);
    }

    private static Format deriveAudioFormat(Format r12, Format r13, boolean r14) {
        ImmutableList r0 = ImmutableList.of();
        int r1 = -1;
        if (r13 == null) goto L5;
        String r02 = r13.codecs;
        Metadata r2 = r13.metadata;
        int r3 = r13.channelCount;
        int r4 = r13.selectionFlags;
        int r5 = r13.roleFlags;
        String r6 = r13.language;
        String r7 = r13.label;
        List<Label> r132 = r13.labels;
    L9:
        String r8 = MimeTypes.getMediaMimeType(r02);
        if (r14 == false) goto L12;
        int r9 = r12.averageBitrate;
    L13:
        if (r14 == false) goto L16;
        r1 = r12.peakBitrate;
    L16:
        return new Format.Builder().setId(r12.id).setLabel(r7).setLabels(r132).setContainerMimeType(r12.containerMimeType).setSampleMimeType(r8).setCodecs(r02).setMetadata(r2).setAverageBitrate(r9).setPeakBitrate(r1).setChannelCount(r3).setSelectionFlags(r4).setRoleFlags(r5).setLanguage(r6).build();
    L12:
        r9 = -1;
        goto L13
    L5:
        String r133 = Util.getCodecsOfType(r12.codecs, 1);
        r2 = r12.metadata;
        if (r14 == false) goto L8;
        r3 = r12.channelCount;
        r4 = r12.selectionFlags;
        r5 = r12.roleFlags;
        r6 = r12.language;
        r7 = r12.label;
        r02 = r133;
        r132 = r12.labels;
        goto L9
    L8:
        r4 = 0;
        r6 = null;
        r02 = r133;
        r132 = r0;
        r3 = -1;
        r5 = 0;
        r7 = null;
        goto L9
    }

    private static Map<String, DrmInitData> deriveOverridingDrmInitData(List<DrmInitData> r8) {
        ArrayList r0 = new ArrayList(r8);
        HashMap r1 = new HashMap();
        int r2 = 0;
    L4:
        if (r2 >= r0.size()) goto L13;
        DrmInitData r3 = r8.get(r2);
        String r4 = r3.schemeType;
        r2 = r2 + 1;
        int r5 = r2;
    L7:
        if (r5 >= r0.size()) goto L12;
        DrmInitData r6 = (DrmInitData) r0.get(r5);
        if (TextUtils.equals(r6.schemeType, r4) == true) goto L10;
        r5 = r5 + 1;
        goto L7
    L10:
        r3 = r3.merge(r6);
        r0.remove(r5);
        goto L7
    L12:
        r1.put(r4, r3);
        goto L4
    L13:
        return r1;
    }

    private static Format deriveVideoFormat(Format r4) {
        String r0 = Util.getCodecsOfType(r4.codecs, 2);
        String r1 = MimeTypes.getMediaMimeType(r0);
        return new Format.Builder().setId(r4.id).setLabel(r4.label).setLabels(r4.labels).setContainerMimeType(r4.containerMimeType).setSampleMimeType(r1).setCodecs(r0).setMetadata(r4.metadata).setAverageBitrate(r4.averageBitrate).setPeakBitrate(r4.peakBitrate).setWidth(r4.width).setHeight(r4.height).setFrameRate(r4.frameRate).setSelectionFlags(r4.selectionFlags).setRoleFlags(r4.roleFlags).build();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(LoadingInfo r4) {
        if (this.trackGroups != null) goto L9;
        HlsSampleStreamWrapper[] r3 = this.sampleStreamWrappers;
        int r42 = r3.length;
        int r1 = 0;
    L5:
        if (r1 >= r42) goto L7;
        r3[r1].continuePreparing();
        r1 = r1 + 1;
        goto L5
    L7:
        return false;
    L9:
        return this.compositeSequenceableLoader.continueLoading(r4);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void discardBuffer(long r4, boolean r6) {
        HlsSampleStreamWrapper[] r3 = this.enabledSampleStreamWrappers;
        int r0 = r3.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3[r1].discardBuffer(r4, r6);
        r1 = r1 + 1;
        goto L3
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long r5, SeekParameters r7) {
        HlsSampleStreamWrapper[] r4 = this.enabledSampleStreamWrappers;
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        HlsSampleStreamWrapper r2 = r4[r1];
        if (r2.isVideoSampleStream() == true) goto L7;
        r1 = r1 + 1;
        goto L3
    L7:
        return r2.getAdjustedSeekPositionUs(r5, r7);
    L9:
        return r5;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getBufferedPositionUs() {
        return this.compositeSequenceableLoader.getBufferedPositionUs();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        return this.compositeSequenceableLoader.getNextLoadPositionUs();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return (TrackGroupArray) Assertions.checkNotNull(this.trackGroups);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean isLoading() {
        return this.compositeSequenceableLoader.isLoading();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void maybeThrowPrepareError() {
        HlsSampleStreamWrapper[] r3 = this.sampleStreamWrappers;
        int r0 = r3.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3[r1].maybeThrowPrepareError();
        r1 = r1 + 1;
        goto L3
    }

    @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker.PlaylistEventListener
    public void onPlaylistChanged() {
        HlsSampleStreamWrapper[] r0 = this.sampleStreamWrappers;
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r0[r2].onPlaylistUpdated();
        r2 = r2 + 1;
        goto L3
    L5:
        this.mediaPeriodCallback.onContinueLoadingRequested(this);
    }

    @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker.PlaylistEventListener
    public boolean onPlaylistError(Uri r6, LoadErrorHandlingPolicy.LoadErrorInfo r7, boolean r8) {
        HlsSampleStreamWrapper[] r0 = this.sampleStreamWrappers;
        int r1 = r0.length;
        boolean r2 = true;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L5;
        r2 = r2 & r0[r3].onPlaylistError(r6, r7, r8);
        r3 = r3 + 1;
        goto L3
    L5:
        this.mediaPeriodCallback.onContinueLoadingRequested(this);
        return r2;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void prepare(MediaPeriod.Callback r1, long r2) {
        this.mediaPeriodCallback = r1;
        this.playlistTracker.addListener(this);
        buildAndPrepareSampleStreamWrappers(r2);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long readDiscontinuity() {
        return -9223372036854775807L;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long r1) {
        this.compositeSequenceableLoader.reevaluateBuffer(r1);
    }

    public void release() {
        this.playlistTracker.removeListener(this);
        HlsSampleStreamWrapper[] r0 = this.sampleStreamWrappers;
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r0[r2].release();
        r2 = r2 + 1;
        goto L3
    L5:
        this.mediaPeriodCallback = null;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long seekToUs(long r5) {
        HlsSampleStreamWrapper[] r0 = this.enabledSampleStreamWrappers;
        if (r0.length <= 0) goto L10;
        boolean r02 = r0[0].seekToUs(r5, false);
        int r1 = 1;
    L5:
        HlsSampleStreamWrapper[] r2 = this.enabledSampleStreamWrappers;
        if (r1 >= r2.length) goto L8;
        r2[r1].seekToUs(r5, r02);
        r1 = r1 + 1;
        goto L5
    L8:
        if (r02 == false) goto L10;
        this.timestampAdjusterProvider.reset();
    L10:
        return r5;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long selectTracks(ExoTrackSelection[] r21, boolean[] r22, SampleStream[] r23, boolean[] r24, long r25) {
        int[] r3 = new int[r21.length];
        int[] r4 = new int[r21.length];
        int r6 = 0;
    L4:
        if (r6 >= r21.length) goto L19;
        SampleStream r7 = r23[r6];
        if (r7 != null) goto L8;
        int r72 = -1;
    L9:
        r3[r6] = r72;
        r4[r6] = -1;
        ExoTrackSelection r73 = r21[r6];
        if (r73 == null) goto L18;
        TrackGroup r74 = r73.getTrackGroup();
        int r9 = 0;
    L12:
        HlsSampleStreamWrapper[] r10 = this.sampleStreamWrappers;
        if (r9 >= r10.length) goto L18;
        if (r10[r9].getTrackGroups().indexOf(r74) != (-1)) goto L16;
        r9 = r9 + 1;
        goto L12
    L16:
        r4[r6] = r9;
    L18:
        r6 = r6 + 1;
        goto L4
    L8:
        r72 = this.streamWrapperIndices.get(r7).intValue();
        goto L9
    L19:
        this.streamWrapperIndices.clear();
        int r62 = r21.length;
        SampleStream[] r75 = new SampleStream[r62];
        SampleStream[] r12 = new SampleStream[r21.length];
        ExoTrackSelection[] r102 = new ExoTrackSelection[r21.length];
        HlsSampleStreamWrapper[] r8 = new HlsSampleStreamWrapper[this.sampleStreamWrappers.length];
        int r92 = 0;
        int r11 = 0;
        boolean r16 = false;
    L21:
        if (r92 >= this.sampleStreamWrappers.length) goto L63;
        int r13 = 0;
    L24:
        if (r13 >= r21.length) goto L33;
        ExoTrackSelection r15 = null;
        if (r3[r13] != r92) goto L28;
        SampleStream r14 = r23[r13];
    L29:
        r12[r13] = r14;
        if (r4[r13] != r92) goto L32;
        r15 = r21[r13];
    L32:
        r102[r13] = r15;
        r13 = r13 + 1;
        goto L24
    L28:
        r14 = null;
        goto L29
    L33:
        HlsSampleStreamWrapper r132 = this.sampleStreamWrappers[r92];
        int[] r18 = r3;
        int r32 = r92;
        int r5 = r11;
        boolean r19 = r132.selectTracks(r102, r22, r12, r24, r25, r16);
        int r112 = 0;
        boolean r133 = false;
    L35:
        if (r112 >= r21.length) goto L46;
        SampleStream r142 = r12[r112];
        if (r4[r112] != r32) goto L40;
        Assertions.checkNotNull(r142);
        r75[r112] = r142;
        this.streamWrapperIndices.put(r142, Integer.valueOf(r32));
        r133 = true;
    L45:
        r112 = r112 + 1;
        goto L35
    L40:
        if (r18[r112] != r32) goto L45;
        if (r142 != null) goto L43;
        boolean r152 = true;
    L44:
        Assertions.checkState(r152);
        goto L45
    L43:
        r152 = false;
        goto L44
    L46:
        if (r133 == false) goto L61;
        r8[r5] = r132;
        r11 = r5 + 1;
        if (r5 != 0) goto L57;
        r132.setIsPrimaryTimestampSource(true);
        if (r19 == true) goto L55;
        HlsSampleStreamWrapper[] r134 = this.enabledSampleStreamWrappers;
        if (r134.length == 0) goto L55;
        if (r132 != r134[0]) goto L55;
    L62:
        r92 = r32 + 1;
        r3 = r18;
    L55:
        this.timestampAdjusterProvider.reset();
        r16 = true;
        goto L62
    L57:
        if (r32 >= this.audioVideoSampleStreamWrapperCount) goto L59;
        boolean r153 = true;
    L60:
        r132.setIsPrimaryTimestampSource(r153);
        goto L62
    L59:
        r153 = false;
        goto L60
    L61:
        r11 = r5;
        goto L62
    L63:
        System.arraycopy(r75, 0, r23, 0, r62);
        HlsSampleStreamWrapper[] r1 = (HlsSampleStreamWrapper[]) Util.nullSafeArrayCopy(r8, r11);
        this.enabledSampleStreamWrappers = r1;
        ImmutableList r17 = ImmutableList.copyOf(r1);
        this.compositeSequenceableLoader = this.compositeSequenceableLoaderFactory.create(r17, Lists.transform(r17, new HlsMediaPeriod$$ExternalSyntheticLambda0()));
        return r25;
    }
}
