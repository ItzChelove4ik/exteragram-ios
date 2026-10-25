package androidx.media3.exoplayer.dash;

import android.util.Pair;
import android.util.SparseArray;
import androidx.media3.common.Format;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.dash.DashChunkSource;
import androidx.media3.exoplayer.dash.PlayerEmsgHandler;
import androidx.media3.exoplayer.dash.manifest.AdaptationSet;
import androidx.media3.exoplayer.dash.manifest.DashManifest;
import androidx.media3.exoplayer.dash.manifest.Descriptor;
import androidx.media3.exoplayer.dash.manifest.EventStream;
import androidx.media3.exoplayer.dash.manifest.Period;
import androidx.media3.exoplayer.dash.manifest.Representation;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.source.CompositeSequenceableLoaderFactory;
import androidx.media3.exoplayer.source.EmptySampleStream;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.source.SampleStream;
import androidx.media3.exoplayer.source.SequenceableLoader;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.source.chunk.ChunkSampleStream;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.LoaderErrorThrower;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
final class DashMediaPeriod implements MediaPeriod, SequenceableLoader.Callback<ChunkSampleStream<DashChunkSource>>, ChunkSampleStream.ReleaseCallback<DashChunkSource> {
    private static final Pattern CEA608_SERVICE_DESCRIPTOR_REGEX = null;
    private static final Pattern CEA708_SERVICE_DESCRIPTOR_REGEX = null;
    private final Allocator allocator;
    private final BaseUrlExclusionList baseUrlExclusionList;
    private MediaPeriod.Callback callback;
    private boolean canReportInitialDiscontinuity;
    private final DashChunkSource.Factory chunkSourceFactory;
    private SequenceableLoader compositeSequenceableLoader;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final DrmSessionEventListener.EventDispatcher drmEventDispatcher;
    private final DrmSessionManager drmSessionManager;
    private final long elapsedRealtimeOffsetMs;
    private EventSampleStream[] eventSampleStreams;
    private List<EventStream> eventStreams;
    final int id;
    private long initialStartTimeUs;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private DashManifest manifest;
    private final LoaderErrorThrower manifestLoaderErrorThrower;
    private final MediaSourceEventListener.EventDispatcher mediaSourceEventDispatcher;
    private int periodIndex;
    private final PlayerEmsgHandler playerEmsgHandler;
    private final PlayerId playerId;
    private ChunkSampleStream<DashChunkSource>[] sampleStreams;
    private final IdentityHashMap<ChunkSampleStream<DashChunkSource>, PlayerEmsgHandler.PlayerTrackEmsgHandler> trackEmsgHandlerBySampleStream;
    private final TrackGroupInfo[] trackGroupInfos;
    private final TrackGroupArray trackGroups;
    private final TransferListener transferListener;

    public static final class TrackGroupInfo {
        public final int[] adaptationSetIndices;
        public final int embeddedClosedCaptionTrackGroupIndex;
        public final ImmutableList<Format> embeddedClosedCaptionTrackOriginalFormats;
        public final int embeddedEventMessageTrackGroupIndex;
        public final int eventStreamGroupIndex;
        public final int primaryTrackGroupIndex;
        public final int trackGroupCategory;
        public final int trackType;

        private TrackGroupInfo(int r1, int r2, int[] r3, int r4, int r5, int r6, int r7, ImmutableList<Format> r8) {
            this.trackType = r1;
            this.adaptationSetIndices = r3;
            this.trackGroupCategory = r2;
            this.primaryTrackGroupIndex = r4;
            this.embeddedEventMessageTrackGroupIndex = r5;
            this.embeddedClosedCaptionTrackGroupIndex = r6;
            this.eventStreamGroupIndex = r7;
            this.embeddedClosedCaptionTrackOriginalFormats = r8;
        }

        public static TrackGroupInfo embeddedClosedCaptionTrack(int[] r9, int r10, ImmutableList<Format> r11) {
            return new TrackGroupInfo(3, 1, r9, r10, -1, -1, -1, r11);
        }

        public static TrackGroupInfo embeddedEmsgTrack(int[] r9, int r10) {
            return new TrackGroupInfo(5, 1, r9, r10, -1, -1, -1, ImmutableList.of());
        }

        public static TrackGroupInfo mpdEventTrack(int r9) {
            return new TrackGroupInfo(5, 2, new int[0], -1, -1, -1, r9, ImmutableList.of());
        }

        public static TrackGroupInfo primaryTrack(int r9, int[] r10, int r11, int r12, int r13) {
            return new TrackGroupInfo(r9, 0, r10, r11, r12, r13, -1, ImmutableList.of());
        }
    }

    public static /* synthetic */ List $r8$lambda$cYhSkOlojW6qfDDtIihdUjaLg_s(ChunkSampleStream r0) {
        return ImmutableList.of(Integer.valueOf(r0.primaryTrackType));
    }

    static {
        CEA608_SERVICE_DESCRIPTOR_REGEX = Pattern.compile("CC([1-4])=(.+)");
        CEA708_SERVICE_DESCRIPTOR_REGEX = Pattern.compile("([1-4])=lang:(\\w+)(,.+)?");
    }

    public DashMediaPeriod(int r1, DashManifest r2, BaseUrlExclusionList r3, int r4, DashChunkSource.Factory r5, TransferListener r6, CmcdConfiguration r7, DrmSessionManager r8, DrmSessionEventListener.EventDispatcher r9, LoadErrorHandlingPolicy r10, MediaSourceEventListener.EventDispatcher r11, long r12, LoaderErrorThrower r14, Allocator r15, CompositeSequenceableLoaderFactory r16, PlayerEmsgHandler.PlayerEmsgCallback r17, PlayerId r18) {
        this.id = r1;
        this.manifest = r2;
        this.baseUrlExclusionList = r3;
        this.periodIndex = r4;
        this.chunkSourceFactory = r5;
        this.transferListener = r6;
        this.drmSessionManager = r8;
        this.drmEventDispatcher = r9;
        this.loadErrorHandlingPolicy = r10;
        this.mediaSourceEventDispatcher = r11;
        this.elapsedRealtimeOffsetMs = r12;
        this.manifestLoaderErrorThrower = r14;
        this.allocator = r15;
        this.compositeSequenceableLoaderFactory = r16;
        this.playerId = r18;
        this.canReportInitialDiscontinuity = true;
        this.playerEmsgHandler = new PlayerEmsgHandler(r2, r17, r15);
        this.sampleStreams = newSampleStreamArray(0);
        this.eventSampleStreams = new EventSampleStream[0];
        this.trackEmsgHandlerBySampleStream = new IdentityHashMap();
        this.compositeSequenceableLoader = r16.empty();
        Period r13 = r2.getPeriod(r4);
        List<EventStream> r22 = r13.eventStreams;
        this.eventStreams = r22;
        Pair<TrackGroupArray, TrackGroupInfo[]> r19 = buildTrackGroups(r8, r5, r13.adaptationSets, r22);
        this.trackGroups = (TrackGroupArray) r19.first;
        this.trackGroupInfos = (TrackGroupInfo[]) r19.second;
    }

    private static void buildManifestEventTrackGroupInfos(List<EventStream> r4, TrackGroup[] r5, TrackGroupInfo[] r6, int r7) {
        int r0 = 0;
    L4:
        if (r0 >= r4.size()) goto L6;
        EventStream r1 = r4.get(r0);
        r5[r7] = new TrackGroup(r1.id() + ":" + r0, new Format[]{new Format.Builder().setId(r1.id()).setSampleMimeType("application/x-emsg").build()});
        r6[r7] = TrackGroupInfo.mpdEventTrack(r0);
        r0 = r0 + 1;
        r7 = r7 + 1;
        goto L4
    }

    private static int buildPrimaryAndEmbeddedTrackGroupInfos(DrmSessionManager r16, DashChunkSource.Factory r17, List<AdaptationSet> r18, int[][] r19, int r20, boolean[] r21, Format[][] r22, TrackGroup[] r23, TrackGroupInfo[] r24) {
        int r4 = 0;
        int r5 = 0;
    L3:
        if (r4 >= r20) goto L28;
        int[] r6 = r19[r4];
        ArrayList r7 = new ArrayList();
        int r8 = r6.length;
        int r9 = 0;
    L5:
        if (r9 >= r8) goto L7;
        r7.addAll(r18.get(r6[r9]).representations);
        r9 = r9 + 1;
        goto L5
    L7:
        int r82 = r7.size();
        Format[] r92 = new Format[r82];
        int r10 = 0;
    L8:
        if (r10 >= r82) goto L10;
        Format r11 = ((Representation) r7.get(r10)).format;
        r92[r10] = r11.buildUpon().setCryptoType(r16.getCryptoType(r11)).build();
        r10 = r10 + 1;
        goto L8
    L10:
        AdaptationSet r72 = r18.get(r6[0]);
        long r102 = r72.id;
        if (r102 == (-1)) goto L13;
        String r83 = Long.toString(r102);
    L14:
        int r103 = r5 + 1;
        if (r21[r4] == false) goto L17;
        int r112 = r5 + 2;
    L19:
        if (r22[r4].length == 0) goto L21;
        int r14 = r112 + 1;
    L22:
        maybeUpdateFormatsForParsedText(r17, r92);
        r23[r5] = new TrackGroup(r83, r92);
        r24[r5] = TrackGroupInfo.primaryTrack(r72.type, r6, r5, r103, r112);
        if (r103 == (-1)) goto L25;
        String r73 = r83 + ":emsg";
        r23[r103] = new TrackGroup(r73, new Format[]{new Format.Builder().setId(r73).setSampleMimeType("application/x-emsg").build()});
        r24[r103] = TrackGroupInfo.embeddedEmsgTrack(r6, r5);
    L25:
        if (r112 == (-1)) goto L27;
        r24[r112] = TrackGroupInfo.embeddedClosedCaptionTrack(r6, r5, ImmutableList.copyOf(r22[r4]));
        maybeUpdateFormatsForParsedText(r17, r22[r4]);
        r23[r112] = new TrackGroup(r83 + ":cc", r22[r4]);
    L27:
        r4 = r4 + 1;
        r5 = r14;
        goto L3
    L21:
        r14 = r112;
        r112 = -1;
        goto L22
    L17:
        r112 = r103;
        r103 = -1;
        goto L19
    L13:
        r83 = "unset:" + r4;
        goto L14
    L28:
        return r5;
    }

    private ChunkSampleStream<DashChunkSource> buildSampleStream(TrackGroupInfo r23, ExoTrackSelection r24, long r25) {
        int r1 = r23.embeddedEventMessageTrackGroupIndex;
        int r3 = 0;
        if (r1 == (-1)) goto L5;
        boolean r16 = true;
    L6:
        PlayerEmsgHandler.PlayerTrackEmsgHandler r6 = null;
        if (r16 == false) goto L9;
        TrackGroup r12 = this.trackGroups.get(r1);
        int r7 = 1;
    L10:
        int r8 = r23.embeddedClosedCaptionTrackGroupIndex;
        if (r8 == (-1)) goto L13;
        ImmutableList<Format> r4 = this.trackGroupInfos[r8].embeddedClosedCaptionTrackOriginalFormats;
    L14:
        int r72 = r7 + r4.size();
        Format[] r82 = new Format[r72];
        int[] r73 = new int[r72];
        if (r16 == false) goto L17;
        r82[0] = r12.getFormat(0);
        r73[0] = 5;
        int r13 = 1;
    L18:
        ArrayList r9 = new ArrayList();
    L20:
        if (r3 >= r4.size()) goto L23;
        Format r10 = r4.get(r3);
        r82[r13] = r10;
        r73[r13] = 3;
        r9.add(r10);
        r13 = r13 + 1;
        r3 = r3 + 1;
        goto L20
    L23:
        if (this.manifest.dynamic == false) goto L26;
        if (r16 == false) goto L26;
        r6 = this.playerEmsgHandler.newPlayerTrackEmsgHandler();
    L26:
        PlayerEmsgHandler.PlayerTrackEmsgHandler r18 = r6;
        ChunkSampleStream<DashChunkSource> r14 = new ChunkSampleStream(r23.trackType, r73, r82, this.chunkSourceFactory.createDashChunkSource(this.manifestLoaderErrorThrower, this.manifest, this.baseUrlExclusionList, this.periodIndex, r23.adaptationSetIndices, r24, r23.trackType, this.elapsedRealtimeOffsetMs, r16, r9, r18, this.transferListener, this.playerId, null), this, this.allocator, r25, this.drmSessionManager, this.drmEventDispatcher, this.loadErrorHandlingPolicy, this.mediaSourceEventDispatcher, this.canReportInitialDiscontinuity, null);
        monitor-enter(this);
        this.trackEmsgHandlerBySampleStream.put(r14, r18);     // Catch: Throwable -> L31
        monitor-exit(this);     // Catch: Throwable -> L31
        return r14;
    L31:
        th = move-exception;
        throw th;
    L17:
        r13 = 0;
        goto L18
    L13:
        r4 = ImmutableList.of();
        goto L14
    L9:
        r7 = 0;
        r12 = null;
        goto L10
    L5:
        r16 = false;
        goto L6
    }

    private static Pair<TrackGroupArray, TrackGroupInfo[]> buildTrackGroups(DrmSessionManager r9, DashChunkSource.Factory r10, List<AdaptationSet> r11, List<EventStream> r12) {
        int[][] r3 = getGroupedAdaptationSetIndices(r11);
        int r4 = r3.length;
        boolean[] r5 = new boolean[r4];
        Format[][] r6 = new Format[r4][];
        int r0 = (identifyEmbeddedTracks(r4, r11, r3, r5, r6) + r4) + r12.size();
        TrackGroup[] r7 = new TrackGroup[r0];
        TrackGroupInfo[] r8 = new TrackGroupInfo[r0];
        buildManifestEventTrackGroupInfos(r12, r7, r8, buildPrimaryAndEmbeddedTrackGroupInfos(r9, r10, r11, r3, r4, r5, r6, r7, r8));
        return Pair.create(new TrackGroupArray(r7), r8);
    }

    private static boolean canMergeAdaptationSets(AdaptationSet r4, AdaptationSet r5) {
        if (r4.type == r5.type) goto L6;
        return false;
    L6:
        if (r4.representations.isEmpty() == false) goto L8;
    L15:
        return true;
    L8:
        if (r5.representations.isEmpty() == true) goto L15;
        Format r42 = r4.representations.get(0).format;
        Format r52 = r5.representations.get(0).format;
        int r0 = r42.roleFlags & (-16385);
        int r3 = r52.roleFlags & (-16385);
        if (Objects.equals(r42.language, r52.language) == false) goto L14;
        if (r0 != r3) goto L14;
        return true;
    L14:
        return false;
    }

    private static Descriptor findAdaptationSetSwitchingProperty(List<Descriptor> r1) {
        return findDescriptor(r1, "urn:mpeg:dash:adaptation-set-switching:2016");
    }

    private static Descriptor findDescriptor(List<Descriptor> r3, String r4) {
        int r0 = 0;
    L4:
        if (r0 >= r3.size()) goto L9;
        Descriptor r1 = r3.get(r0);
        if (r4.equals(r1.schemeIdUri) == true) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r1;
    L9:
        return null;
    }

    private static Descriptor findTrickPlayProperty(List<Descriptor> r1) {
        return findDescriptor(r1, "http://dashif.org/guidelines/trickmode");
    }

    private static Format[] getClosedCaptionTrackFormats(List<AdaptationSet> r9, int[] r10) {
        int r0 = r10.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L18;
        int r3 = r10[r2];
        AdaptationSet r4 = r9.get(r3);
        List<Descriptor> r32 = r9.get(r3).accessibilityDescriptors;
        int r5 = 0;
    L6:
        if (r5 >= r32.size()) goto L16;
        Descriptor r6 = r32.get(r5);
        if ("urn:scte:dash:cc:cea-608:2015".equals(r6.schemeIdUri) == true) goto L10;
        if ("urn:scte:dash:cc:cea-708:2015".equals(r6.schemeIdUri) == true) goto L14;
        r5 = r5 + 1;
        goto L6
    L14:
        return parseClosedCaptionDescriptor(r6, CEA708_SERVICE_DESCRIPTOR_REGEX, new Format.Builder().setSampleMimeType("application/cea-708").setId(r4.id + ":cea708").build());
    L10:
        return parseClosedCaptionDescriptor(r6, CEA608_SERVICE_DESCRIPTOR_REGEX, new Format.Builder().setSampleMimeType("application/cea-608").setId(r4.id + ":cea608").build());
    L16:
        r2 = r2 + 1;
        goto L3
    L18:
        return new Format[0];
    }

    private static int[][] getGroupedAdaptationSetIndices(List<AdaptationSet> r13) {
        int r0 = r13.size();
        HashMap r1 = Maps.newHashMapWithExpectedSize(r0);
        ArrayList r2 = new ArrayList(r0);
        SparseArray r3 = new SparseArray(r0);
        int r4 = 0;
        int r5 = 0;
    L3:
        if (r5 >= r0) goto L5;
        r1.put(Long.valueOf(r13.get(r5).id), Integer.valueOf(r5));
        ArrayList r6 = new ArrayList();
        r6.add(Integer.valueOf(r5));
        r2.add(r6);
        r3.put(r5, r6);
        r5 = r5 + 1;
        goto L3
    L5:
        int r52 = 0;
    L6:
        if (r52 >= r0) goto L31;
        AdaptationSet r62 = r13.get(r52);
        Descriptor r7 = findTrickPlayProperty(r62.essentialProperties);
        if (r7 != null) goto L10;
        r7 = findTrickPlayProperty(r62.supplementalProperties);
    L10:
        if (r7 == null) goto L16;
        Integer r72 = (Integer) r1.get(Long.valueOf(Long.parseLong(r7.value)));
        if (r72 == null) goto L16;
        if (canMergeAdaptationSets(r62, r13.get(r72.intValue())) == false) goto L16;
        int r73 = r72.intValue();
    L17:
        if (r73 != r52) goto L28;
        Descriptor r8 = findAdaptationSetSwitchingProperty(r62.supplementalProperties);
        if (r8 == null) goto L28;
        String[] r82 = Util.split(r8.value, ",");
        int r9 = r82.length;
        int r10 = 0;
    L21:
        if (r10 >= r9) goto L28;
        Integer r11 = (Integer) r1.get(Long.valueOf(Long.parseLong(r82[r10])));
        if (r11 == null) goto L27;
        if (canMergeAdaptationSets(r62, r13.get(r11.intValue())) == false) goto L27;
        r73 = Math.min(r73, r11.intValue());
    L27:
        r10 = r10 + 1;
    L28:
        if (r73 == r52) goto L30;
        List r63 = (List) r3.get(r52);
        List r74 = (List) r3.get(r73);
        r74.addAll(r63);
        r3.put(r52, r74);
        r2.remove(r63);
    L30:
        r52 = r52 + 1;
    L16:
        r73 = r52;
        goto L17
    L31:
        int r132 = r2.size();
        int[][] r02 = new int[r132][];
    L32:
        if (r4 >= r132) goto L34;
        int[] r12 = Ints.toArray((Collection) r2.get(r4));
        r02[r4] = r12;
        Arrays.sort(r12);
        r4 = r4 + 1;
        goto L32
    L34:
        return r02;
    }

    private int getPrimaryStreamIndex(int r5, int[] r6) {
        int r52 = r6[r5];
        if (r52 != (-1)) goto L5;
        return -1;
    L5:
        int r53 = this.trackGroupInfos[r52].primaryTrackGroupIndex;
        int r1 = 0;
    L7:
        if (r1 >= r6.length) goto L14;
        int r2 = r6[r1];
        if (r2 != r53) goto L13;
        if (this.trackGroupInfos[r2].trackGroupCategory != 0) goto L13;
        return r1;
    L13:
        r1 = r1 + 1;
        goto L7
    L14:
        return -1;
    }

    private int[] getStreamIndexToTrackGroupIndex(ExoTrackSelection[] r5) {
        int[] r0 = new int[r5.length];
        int r1 = 0;
    L4:
        if (r1 >= r5.length) goto L10;
        ExoTrackSelection r2 = r5[r1];
        if (r2 == null) goto L8;
        r0[r1] = this.trackGroups.indexOf(r2.getTrackGroup());
    L9:
        r1 = r1 + 1;
        goto L4
    L8:
        r0[r1] = -1;
        goto L9
    L10:
        return r0;
    }

    private static boolean hasEventMessageTrack(List<AdaptationSet> r6, int[] r7) {
        int r0 = r7.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L13;
        List<Representation> r3 = r6.get(r7[r2]).representations;
        int r4 = 0;
    L6:
        if (r4 >= r3.size()) goto L12;
        if (r3.get(r4).inbandEventStreams.isEmpty() == false) goto L9;
        r4 = r4 + 1;
        goto L6
    L9:
        return true;
    L12:
        r2 = r2 + 1;
        goto L3
    L13:
        return false;
    }

    private static int identifyEmbeddedTracks(int r3, List<AdaptationSet> r4, int[][] r5, boolean[] r6, Format[][] r7) {
        int r0 = 0;
        int r1 = 0;
    L3:
        if (r0 >= r3) goto L11;
        if (hasEventMessageTrack(r4, r5[r0]) == false) goto L7;
        r6[r0] = true;
        r1 = r1 + 1;
    L7:
        Format[] r2 = getClosedCaptionTrackFormats(r4, r5[r0]);
        r7[r0] = r2;
        if (r2.length == 0) goto L10;
        r1 = r1 + 1;
    L10:
        r0 = r0 + 1;
        goto L3
    L11:
        return r1;
    }

    private static void maybeUpdateFormatsForParsedText(DashChunkSource.Factory r2, Format[] r3) {
        int r0 = 0;
    L4:
        if (r0 >= r3.length) goto L6;
        r3[r0] = r2.getOutputTextFormat(r3[r0]);
        r0 = r0 + 1;
        goto L4
    }

    private static ChunkSampleStream<DashChunkSource>[] newSampleStreamArray(int r0) {
        return new ChunkSampleStream[r0];
    }

    private static Format[] parseClosedCaptionDescriptor(Descriptor r7, Pattern r8, Format r9) {
        String r72 = r7.value;
        if (r72 == null) goto L5;
        String[] r73 = Util.split(r72, ";");
        Format[] r0 = new Format[r73.length];
        int r1 = 0;
    L8:
        if (r1 >= r73.length) goto L14;
        Matcher r2 = r8.matcher(r73[r1]);
        if (r2.matches() == false) goto L12;
        int r3 = Integer.parseInt(r2.group(1));
        r0[r1] = r9.buildUpon().setId(r9.id + ":" + r3).setAccessibilityChannel(r3).setLanguage(r2.group(2)).build();
        r1 = r1 + 1;
        goto L8
    L12:
        return new Format[]{r9};
    L14:
        return r0;
    L5:
        return new Format[]{r9};
    }

    private void releaseDisabledStreams(ExoTrackSelection[] r4, boolean[] r5, SampleStream[] r6) {
        int r0 = 0;
    L4:
        if (r0 >= r4.length) goto L17;
        if (r4[r0] != null) goto L8;
    L9:
        SampleStream r1 = r6[r0];
        if ((r1 instanceof ChunkSampleStream) == false) goto L13;
        ((ChunkSampleStream) r1).release(this);
    L15:
        r6[r0] = null;
    L16:
        r0 = r0 + 1;
        goto L4
    L13:
        if ((r1 instanceof ChunkSampleStream.EmbeddedSampleStream) == false) goto L15;
        ((ChunkSampleStream.EmbeddedSampleStream) r1).release();
        goto L15
    L8:
        if (r5[r0] == true) goto L16;
    }

    private void releaseOrphanEmbeddedStreams(ExoTrackSelection[] r6, SampleStream[] r7, int[] r8) {
        int r1 = 0;
    L4:
        if (r1 >= r6.length) goto L24;
        SampleStream r2 = r7[r1];
        if ((r2 instanceof EmptySampleStream) == false) goto L8;
    L9:
        int r22 = getPrimaryStreamIndex(r1, r8);
        if (r22 != (-1)) goto L12;
        boolean r23 = r7[r1] instanceof EmptySampleStream;
    L18:
        if (r23 == true) goto L23;
        SampleStream r24 = r7[r1];
        if ((r24 instanceof ChunkSampleStream.EmbeddedSampleStream) == false) goto L22;
        ((ChunkSampleStream.EmbeddedSampleStream) r24).release();
    L22:
        r7[r1] = null;
    L23:
        r1 = r1 + 1;
        goto L4
    L12:
        SampleStream r3 = r7[r1];
        if ((r3 instanceof ChunkSampleStream.EmbeddedSampleStream) == true) goto L15;
    L17:
        r23 = false;
        goto L18
    L15:
        if (((ChunkSampleStream.EmbeddedSampleStream) r3).parent != r7[r22]) goto L17;
        r23 = true;
        goto L18
    L8:
        if ((r2 instanceof ChunkSampleStream.EmbeddedSampleStream) == false) goto L23;
    }

    private void selectNewStreams(ExoTrackSelection[] r7, SampleStream[] r8, boolean[] r9, long r10, int[] r12) {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r1 >= r7.length) goto L21;
        ExoTrackSelection r2 = r7[r1];
        if (r2 == null) goto L19;
        SampleStream r4 = r8[r1];
        if (r4 != null) goto L17;
        r9[r1] = true;
        TrackGroupInfo r3 = this.trackGroupInfos[r12[r1]];
        int r42 = r3.trackGroupCategory;
        if (r42 != 0) goto L14;
        r8[r1] = buildSampleStream(r3, r2, r10);
        goto L19
    L14:
        if (r42 != 2) goto L19;
        r8[r1] = new EventSampleStream(this.eventStreams.get(r3.eventStreamGroupIndex), r2.getTrackGroup().getFormat(0), this.manifest.dynamic);
        goto L19
    L17:
        if ((r4 instanceof ChunkSampleStream) == false) goto L19;
        ((DashChunkSource) ((ChunkSampleStream) r4).getChunkSource()).updateTrackSelection(r2);
    L19:
        r1 = r1 + 1;
    L21:
        if (r0 >= r7.length) goto L33;
        if (r8[r0] != null) goto L32;
        if (r7[r0] == null) goto L32;
        TrackGroupInfo r92 = this.trackGroupInfos[r12[r0]];
        if (r92.trackGroupCategory != 1) goto L32;
        int r13 = getPrimaryStreamIndex(r0, r12);
        if (r13 != (-1)) goto L31;
        r8[r0] = new EmptySampleStream();
        goto L32
    L31:
        r8[r0] = ((ChunkSampleStream) r8[r13]).selectEmbeddedTrack(r10, r92.trackType);
    L32:
        r0 = r0 + 1;
        goto L21
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(LoadingInfo r1) {
        return this.compositeSequenceableLoader.continueLoading(r1);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void discardBuffer(long r4, boolean r6) {
        ChunkSampleStream<DashChunkSource>[] r3 = this.sampleStreams;
        int r0 = r3.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3[r1].discardBuffer(r4, r6);
        r1 = r1 + 1;
        goto L3
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long r6, SeekParameters r8) {
        ChunkSampleStream<DashChunkSource>[] r5 = this.sampleStreams;
        int r0 = r5.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        ChunkSampleStream<DashChunkSource> r2 = r5[r1];
        if (r2.primaryTrackType == 2) goto L7;
        r1 = r1 + 1;
        goto L3
    L7:
        return r2.getAdjustedSeekPositionUs(r6, r8);
    L9:
        return r6;
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
        return this.trackGroups;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean isLoading() {
        return this.compositeSequenceableLoader.isLoading();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void maybeThrowPrepareError() {
        this.manifestLoaderErrorThrower.maybeThrowError();
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
    public /* bridge */ /* synthetic */ void onContinueLoadingRequested(SequenceableLoader r1) {
        onContinueLoadingRequested((ChunkSampleStream) r1);
    }

    public void onContinueLoadingRequested(ChunkSampleStream<DashChunkSource> r1) {
        this.callback.onContinueLoadingRequested(this);
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkSampleStream.ReleaseCallback
    public synchronized void onSampleStreamReleased(ChunkSampleStream<DashChunkSource> r2) {
        monitor-enter(this);
        PlayerEmsgHandler.PlayerTrackEmsgHandler r22 = this.trackEmsgHandlerBySampleStream.remove(r2);     // Catch: Throwable -> L7
        if (r22 == null) goto L9;
        r22.release();     // Catch: Throwable -> L7
    L9:
        monitor-exit(this);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void prepare(MediaPeriod.Callback r1, long r2) {
        this.callback = r1;
        r1.onPrepared(this);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long readDiscontinuity() {
        ChunkSampleStream<DashChunkSource>[] r0 = this.sampleStreams;
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L9;
        if (r0[r2].consumeInitialDiscontinuity() == true) goto L7;
        r2 = r2 + 1;
        goto L3
    L7:
        return this.initialStartTimeUs;
    L9:
        return -9223372036854775807L;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long r7) {
        ChunkSampleStream<DashChunkSource>[] r0 = this.sampleStreams;
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L8;
        ChunkSampleStream<DashChunkSource> r3 = r0[r2];
        if (r3.isLoading() == true) goto L7;
        r3.discardUpstreamSamplesForClippedDuration(this.manifest.getPeriodDurationUs(this.periodIndex));
    L7:
        r2 = r2 + 1;
        goto L3
    L8:
        this.compositeSequenceableLoader.reevaluateBuffer(r7);
    }

    public void release() {
        this.playerEmsgHandler.release();
        ChunkSampleStream<DashChunkSource>[] r0 = this.sampleStreams;
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r0[r2].release(this);
        r2 = r2 + 1;
        goto L3
    L5:
        this.callback = null;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long seekToUs(long r6) {
        ChunkSampleStream<DashChunkSource>[] r0 = this.sampleStreams;
        int r1 = r0.length;
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L5;
        r0[r3].seekToUs(r6);
        r3 = r3 + 1;
        goto L3
    L5:
        EventSampleStream[] r5 = this.eventSampleStreams;
        int r02 = r5.length;
    L6:
        if (r2 >= r02) goto L8;
        r5[r2].seekToUs(r6);
        r2 = r2 + 1;
        goto L6
    L8:
        return r6;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long selectTracks(ExoTrackSelection[] r5, boolean[] r6, SampleStream[] r7, boolean[] r8, long r9) {
        int[] r10 = getStreamIndexToTrackGroupIndex(r5);
        releaseDisabledStreams(r5, r6, r7);
        releaseOrphanEmbeddedStreams(r5, r7, r10);
        selectNewStreams(r5, r7, r8, r9, r10);
        ArrayList r52 = new ArrayList();
        ArrayList r72 = new ArrayList();
        int r102 = r7.length;
        int r1 = 0;
    L3:
        if (r1 >= r102) goto L11;
        SampleStream r2 = r7[r1];
        if ((r2 instanceof ChunkSampleStream) == false) goto L8;
        r52.add((ChunkSampleStream) r2);
    L10:
        r1 = r1 + 1;
        goto L3
    L8:
        if ((r2 instanceof EventSampleStream) == false) goto L10;
        r72.add((EventSampleStream) r2);
        goto L10
    L11:
        ChunkSampleStream<DashChunkSource>[] r62 = newSampleStreamArray(r52.size());
        this.sampleStreams = r62;
        r52.toArray(r62);
        EventSampleStream[] r63 = new EventSampleStream[r72.size()];
        this.eventSampleStreams = r63;
        r72.toArray(r63);
        this.compositeSequenceableLoader = this.compositeSequenceableLoaderFactory.create(r52, Lists.transform(r52, new DashMediaPeriod$$ExternalSyntheticLambda0()));
        if (this.canReportInitialDiscontinuity == false) goto L14;
        this.canReportInitialDiscontinuity = false;
        this.initialStartTimeUs = r9;
    L14:
        return r9;
    }

    public void updateManifest(DashManifest r10, int r11) {
        this.manifest = r10;
        this.periodIndex = r11;
        this.playerEmsgHandler.updateManifest(r10);
        ChunkSampleStream<DashChunkSource>[] r0 = this.sampleStreams;
        if (r0 == null) goto L8;
        int r2 = r0.length;
        int r3 = 0;
    L5:
        if (r3 >= r2) goto L7;
        ((DashChunkSource) r0[r3].getChunkSource()).updateManifest(r10, r11);
        r3 = r3 + 1;
        goto L5
    L7:
        this.callback.onContinueLoadingRequested(this);
    L8:
        this.eventStreams = r10.getPeriod(r11).eventStreams;
        EventSampleStream[] r02 = this.eventSampleStreams;
        int r22 = r02.length;
        int r32 = 0;
    L9:
        if (r32 >= r22) goto L22;
        EventSampleStream r4 = r02[r32];
        Iterator<EventStream> r5 = this.eventStreams.iterator();
    L12:
        if (r5.hasNext() == false) goto L21;
        EventStream r6 = r5.next();
        if (r6.id().equals(r4.eventStreamId()) == false) goto L12;
        boolean r7 = true;
        int r52 = r10.getPeriodCount() - 1;
        if (r10.dynamic == false) goto L19;
        if (r11 != r52) goto L19;
    L20:
        r4.updateEventStream(r6, r7);
    L19:
        r7 = false;
    L21:
        r32 = r32 + 1;
        goto L9
    }
}
