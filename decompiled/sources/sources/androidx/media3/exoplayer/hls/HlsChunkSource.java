package androidx.media3.exoplayer.hls;

import android.net.Uri;
import android.os.SystemClock;
import android.util.Pair;
import androidx.media3.common.Format;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UriUtil;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker;
import androidx.media3.exoplayer.source.BehindLiveWindowException;
import androidx.media3.exoplayer.source.chunk.BaseMediaChunkIterator;
import androidx.media3.exoplayer.source.chunk.Chunk;
import androidx.media3.exoplayer.source.chunk.DataChunk;
import androidx.media3.exoplayer.source.chunk.MediaChunk;
import androidx.media3.exoplayer.source.chunk.MediaChunkIterator;
import androidx.media3.exoplayer.trackselection.BaseTrackSelection;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
class HlsChunkSource {
    private final DataSource encryptionDataSource;
    private final HlsExtractorFactory extractorFactory;
    private IOException fatalError;
    private boolean independentSegments;
    private boolean isPrimaryTimestampSource;
    private final FullSegmentEncryptionKeyCache keyCache;
    private long lastChunkRequestRealtimeMs;
    private Uri lastPlaylistErrorUrl;
    private long liveEdgeInPeriodTimeUs;
    private final DataSource mediaDataSource;
    private final List<Format> muxedCaptionFormats;
    private Uri nextChunkStuckOnPlaylistUrl;
    private final PlayerId playerId;
    private final Format[] playlistFormats;
    private final HlsPlaylistTracker playlistTracker;
    private final Uri[] playlistUrls;
    private byte[] scratchSpace;
    private final long timestampAdjusterInitializationTimeoutMs;
    private final TimestampAdjusterProvider timestampAdjusterProvider;
    private final TrackGroup trackGroup;
    private ExoTrackSelection trackSelection;

    public static final class EncryptionKeyChunk extends DataChunk {
        private byte[] result;

        public EncryptionKeyChunk(DataSource r9, DataSpec r10, Format r11, int r12, Object r13, byte[] r14) {
            super(r9, r10, 3, r11, r12, r13, r14);
        }

        @Override // androidx.media3.exoplayer.source.chunk.DataChunk
        public void consume(byte[] r1, int r2) {
            this.result = Arrays.copyOf(r1, r2);
        }

        public byte[] getResult() {
            return this.result;
        }
    }

    public static final class HlsChunkHolder {
        public Chunk chunk;
        public boolean endOfStream;
        public Uri playlistUrl;

        public HlsChunkHolder() {
            clear();
        }

        public void clear() {
            this.chunk = null;
            this.endOfStream = false;
            this.playlistUrl = null;
        }
    }

    public static final class HlsMediaPlaylistSegmentIterator extends BaseMediaChunkIterator {
        private final String playlistBaseUri;
        private final List<HlsMediaPlaylist.SegmentBase> segmentBases;
        private final long startOfPlaylistInPeriodUs;

        public HlsMediaPlaylistSegmentIterator(String r5, long r6, List<HlsMediaPlaylist.SegmentBase> r8) {
            super(0, r8.size() - 1);
            this.playlistBaseUri = r5;
            this.startOfPlaylistInPeriodUs = r6;
            this.segmentBases = r8;
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public long getChunkEndTimeUs() {
            checkInBounds();
            HlsMediaPlaylist.SegmentBase r0 = this.segmentBases.get((int) getCurrentIndex());
            return (this.startOfPlaylistInPeriodUs + r0.relativeStartTimeUs) + r0.durationUs;
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public long getChunkStartTimeUs() {
            checkInBounds();
            return this.startOfPlaylistInPeriodUs + this.segmentBases.get((int) getCurrentIndex()).relativeStartTimeUs;
        }
    }

    public static final class InitializationTrackSelection extends BaseTrackSelection {
        private int selectedIndex;

        public InitializationTrackSelection(TrackGroup r2, int[] r3) {
            super(r2, r3);
            this.selectedIndex = indexOf(r2.getFormat(r3[0]));
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public int getSelectedIndex() {
            return this.selectedIndex;
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public Object getSelectionData() {
            return null;
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public int getSelectionReason() {
            return 0;
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void updateSelectedTrack(long r1, long r3, long r5, List<? extends MediaChunk> r7, MediaChunkIterator[] r8) {
            long r12 = SystemClock.elapsedRealtime();
            if (isTrackExcluded(this.selectedIndex, r12) == true) goto L5;
            return;
        L5:
            int r32 = this.length - 1;
        L6:
            if (r32 < 0) goto L12;
            if (isTrackExcluded(r32, r12) == false) goto L9;
            r32 = r32 - 1;
            goto L6
        L9:
            this.selectedIndex = r32;
            return;
        L12:
            MethodWriter$$ExternalSyntheticBUOutline0.m();
        }
    }

    public static final class SegmentBaseHolder {
        public final boolean isPreload;
        public final long mediaSequence;
        public final int partIndex;
        public final HlsMediaPlaylist.SegmentBase segmentBase;

        public SegmentBaseHolder(HlsMediaPlaylist.SegmentBase r1, long r2, int r4) {
            this.segmentBase = r1;
            this.mediaSequence = r2;
            this.partIndex = r4;
            if ((r1 instanceof HlsMediaPlaylist.Part) == true) goto L5;
        L7:
            boolean r12 = false;
        L8:
            this.isPreload = r12;
            return;
        L5:
            if (((HlsMediaPlaylist.Part) r1).isPreload == false) goto L7;
            r12 = true;
            goto L8
        }
    }

    public HlsChunkSource(HlsExtractorFactory r1, HlsPlaylistTracker r2, Uri[] r3, Format[] r4, HlsDataSourceFactory r5, TransferListener r6, TimestampAdjusterProvider r7, long r8, List<Format> r10, PlayerId r11, CmcdConfiguration r12) {
        this.extractorFactory = r1;
        this.playlistTracker = r2;
        this.playlistUrls = r3;
        this.playlistFormats = r4;
        this.timestampAdjusterProvider = r7;
        this.timestampAdjusterInitializationTimeoutMs = r8;
        this.muxedCaptionFormats = r10;
        this.playerId = r11;
        this.lastChunkRequestRealtimeMs = -9223372036854775807L;
        this.keyCache = new FullSegmentEncryptionKeyCache(4);
        this.scratchSpace = Util.EMPTY_BYTE_ARRAY;
        this.liveEdgeInPeriodTimeUs = -9223372036854775807L;
        DataSource r13 = r5.createDataSource(1);
        this.mediaDataSource = r13;
        if (r6 == null) goto L5;
        r13.addTransferListener(r6);
    L5:
        this.encryptionDataSource = r5.createDataSource(3);
        this.trackGroup = new TrackGroup(r4);
        ArrayList r14 = new ArrayList();
        int r22 = 0;
    L7:
        if (r22 >= r3.length) goto L12;
        if ((r4[r22].roleFlags & 16384) != 0) goto L11;
        r14.add(Integer.valueOf(r22));
    L11:
        r22 = r22 + 1;
        goto L7
    L12:
        this.trackSelection = new InitializationTrackSelection(this.trackGroup, Ints.toArray(r14));
    }

    private void deactivatePlaylistForSelectedTrack() {
        int r0 = this.trackSelection.getSelectedIndexInTrackGroup();
        this.playlistTracker.deactivatePlaylistForPlayback(this.playlistUrls[r0]);
    }

    private static Uri getFullEncryptionKeyUri(HlsMediaPlaylist r0, HlsMediaPlaylist.SegmentBase r1) {
        if (r1 == null) goto L8;
        String r12 = r1.fullSegmentEncryptionKeyUri;
        if (r12 != null) goto L7;
        return null;
    L7:
        return UriUtil.resolveToUri(r0.baseUri, r12);
    L8:
        return null;
    }

    private Pair<Long, Integer> getNextMediaSequenceAndPartIndex(HlsMediaChunk r7, boolean r8, HlsMediaPlaylist r9, long r10, long r12) {
        int r1 = -1;
        if (r7 == null) goto L20;
        if (r8 == true) goto L20;
        if (r7.isLoadCompleted() == false) goto L19;
        if (r7.partIndex != (-1)) goto L11;
        long r82 = r7.getNextChunkIndex();
    L12:
        Long r83 = Long.valueOf(r82);
        int r72 = r7.partIndex;
        if (r72 == (-1)) goto L17;
        r1 = r72 + 1;
    L17:
        return new Pair(r83, Integer.valueOf(r1));
    L11:
        r82 = r7.chunkIndex;
        goto L12
    L19:
        return new Pair(Long.valueOf(r7.chunkIndex), Integer.valueOf(r7.partIndex));
    L20:
        long r2 = r9.durationUs + r10;
        if (r7 == null) goto L27;
        if (this.independentSegments == true) goto L27;
        r12 = r7.startTimeUs;
    L27:
        if (r9.hasEndTag == false) goto L29;
    L32:
        long r122 = r12 - r10;
        List<HlsMediaPlaylist.Segment> r84 = r9.segments;
        Long r102 = Long.valueOf(r122);
        int r11 = 0;
        if (this.playlistTracker.isLive() == false) goto L37;
        if (r7 == null) goto L37;
        boolean r6 = false;
    L38:
        int r62 = Util.binarySearchFloor(r84, r102, true, r6);
        long r73 = r62 + r9.mediaSequence;
        if (r62 < 0) goto L57;
        HlsMediaPlaylist.Segment r63 = r9.segments.get(r62);
        if (r122 >= (r63.relativeStartTimeUs + r63.durationUs)) goto L43;
        List<HlsMediaPlaylist.Part> r64 = r63.parts;
    L45:
        if (r11 >= r64.size()) goto L57;
        HlsMediaPlaylist.Part r103 = r64.get(r11);
        if (r122 < (r103.relativeStartTimeUs + r103.durationUs)) goto L49;
        r11 = r11 + 1;
        goto L45
    L49:
        if (r103.isIndependent == false) goto L57;
        if (r64 != r9.trailingParts) goto L53;
        long r92 = 1;
    L54:
        r73 = r73 + r92;
        r1 = r11;
        goto L57
    L53:
        r92 = 0;
        goto L54
    L43:
        r64 = r9.trailingParts;
    L57:
        return new Pair(Long.valueOf(r73), Integer.valueOf(r1));
    L37:
        r6 = true;
        goto L38
    L29:
        if (r12 < r2) goto L32;
        return new Pair(Long.valueOf(r9.mediaSequence + r9.segments.size()), -1);
    }

    private static SegmentBaseHolder getNextSegmentHolder(HlsMediaPlaylist r7, long r8, int r10) {
        int r0 = (int) (r8 - r7.mediaSequence);
        if (r0 != r7.segments.size()) goto L12;
        if (r10 != (-1)) goto L8;
        r10 = 0;
    L8:
        if (r10 < r7.trailingParts.size()) goto L10;
        return null;
    L10:
        return new SegmentBaseHolder(r7.trailingParts.get(r10), r8, r10);
    L12:
        HlsMediaPlaylist.Segment r1 = r7.segments.get(r0);
        if (r10 != (-1)) goto L17;
        return new SegmentBaseHolder(r1, r8, -1);
    L17:
        if (r10 < r1.parts.size()) goto L19;
        int r02 = r0 + 1;
        if (r02 >= r7.segments.size()) goto L25;
        return new SegmentBaseHolder(r7.segments.get(r02), r8 + 1, -1);
    L25:
        if (r7.trailingParts.isEmpty() == false) goto L27;
        return null;
    L27:
        return new SegmentBaseHolder(r7.trailingParts.get(0), r8 + 1, 0);
    L19:
        return new SegmentBaseHolder(r1.parts.get(r10), r8, r10);
    }

    public static List<HlsMediaPlaylist.SegmentBase> getSegmentBaseList(HlsMediaPlaylist r7, long r8, int r10) {
        int r82 = (int) (r8 - r7.mediaSequence);
        if (r82 < 0) goto L29;
        if (r7.segments.size() < r82) goto L29;
        ArrayList r9 = new ArrayList();
        int r1 = 0;
        if (r82 >= r7.segments.size()) goto L19;
        if (r10 == (-1)) goto L17;
        HlsMediaPlaylist.Segment r0 = r7.segments.get(r82);
        if (r10 != 0) goto L14;
        r9.add(r0);
    L16:
        r82 = r82 + 1;
        goto L17
    L14:
        if (r10 >= r0.parts.size()) goto L16;
        List<HlsMediaPlaylist.Part> r02 = r0.parts;
        r9.addAll(r02.subList(r10, r02.size()));
    L17:
        List<HlsMediaPlaylist.Segment> r102 = r7.segments;
        r9.addAll(r102.subList(r82, r102.size()));
        r10 = 0;
    L19:
        if (r7.partTargetDurationUs == (-9223372036854775807L)) goto L27;
        if (r10 == (-1)) goto L24;
        r1 = r10;
    L24:
        if (r1 >= r7.trailingParts.size()) goto L27;
        List<HlsMediaPlaylist.Part> r72 = r7.trailingParts;
        r9.addAll(r72.subList(r1, r72.size()));
    L27:
        return Collections.unmodifiableList(r9);
    L29:
        return ImmutableList.of();
    }

    private static boolean isIndependent(SegmentBaseHolder r2, HlsMediaPlaylist r3) {
        HlsMediaPlaylist.SegmentBase r0 = r2.segmentBase;
        if ((r0 instanceof HlsMediaPlaylist.Part) == false) goto L16;
        if (((HlsMediaPlaylist.Part) r0).isIndependent == false) goto L7;
        return true;
    L7:
        if (r2.partIndex == 0) goto L9;
        return false;
    L9:
        if (r3.hasIndependentSegments == true) goto L18;
        return false;
    L18:
        return true;
    L16:
        return r3.hasIndependentSegments;
    }

    private Chunk maybeCreateEncryptionChunkFor(Uri r8, int r9, boolean r10, CmcdData.Factory r11) {
        if (r8 != null) goto L5;
        return null;
    L5:
        byte[] r112 = this.keyCache.remove(r8);
        if (r112 == null) goto L9;
        this.keyCache.put(r8, r112);
        return null;
    L9:
        DataSpec r2 = new DataSpec.Builder().setUri(r8).setFlags(1).build();
        return new EncryptionKeyChunk(this.encryptionDataSource, r2, this.playlistFormats[r9], this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData(), this.scratchSpace);
    }

    private long resolveTimeToLiveEdgeUs(long r5) {
        long r0 = this.liveEdgeInPeriodTimeUs;
        if (r0 != (-9223372036854775807L)) goto L5;
        return -9223372036854775807L;
    L5:
        return r0 - r5;
    }

    private static boolean shouldForceKeepCurrentTrackSelection(boolean r3, HlsMediaPlaylist r4, long r5, int r7, HlsMediaChunk r8, long r9, long r11) {
        if (r3 == true) goto L5;
        return false;
    L5:
        if (r8 != null) goto L8;
        return false;
    L8:
        if (r5 >= r4.mediaSequence) goto L10;
        return true;
    L10:
        SegmentBaseHolder r32 = getNextSegmentHolder(r4, r5, r7);
        if (r32 != null) goto L14;
        return false;
    L14:
        if ((r9 + r32.segmentBase.relativeStartTimeUs) >= r11) goto L16;
        return true;
    L16:
        return false;
    }

    private void updateLiveEdgeTimeUs(HlsMediaPlaylist r5) {
        if (r5.hasEndTag == false) goto L5;
        long r0 = -9223372036854775807L;
    L6:
        this.liveEdgeInPeriodTimeUs = r0;
        return;
    L5:
        r0 = r5.getEndTimeUs() - this.playlistTracker.getInitialStartTimeUs();
        goto L6
    }

    public MediaChunkIterator[] createMediaChunkIterators(HlsMediaChunk r14, long r15) {
        if (r14 != null) goto L5;
        int r2 = -1;
    L4:
        int r8 = r2;
        int r9 = this.trackSelection.length();
        MediaChunkIterator[] r10 = new MediaChunkIterator[r9];
        int r12 = 0;
    L7:
        if (r12 >= r9) goto L18;
        int r22 = this.trackSelection.getIndexInTrackGroup(r12);
        Uri r3 = this.playlistUrls[r22];
        if (this.playlistTracker.isSnapshotValid(r3) == true) goto L11;
        r10[r12] = MediaChunkIterator.EMPTY;
    L17:
        r12 = r12 + 1;
        goto L7
    L11:
        HlsMediaPlaylist r32 = this.playlistTracker.getPlaylistSnapshot(r3, false);
        Assertions.checkNotNull(r32);
        long r4 = r32.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
        if (r22 == r8) goto L15;
        boolean r23 = true;
    L16:
        Pair<Long, Integer> r24 = getNextMediaSequenceAndPartIndex(r14, r23, r32, r4, r15);
        r10[r12] = new HlsMediaPlaylistSegmentIterator(r32.baseUri, r4, getSegmentBaseList(r32, ((Long) r24.first).longValue(), ((Integer) r24.second).intValue()));
        goto L17
    L15:
        r23 = false;
        goto L16
    L18:
        return r10;
    L5:
        r2 = this.trackGroup.indexOf(r14.trackFormat);
        goto L4
    }

    public long getAdjustedSeekPositionUs(long r12, SeekParameters r14) {
        int r0 = this.trackSelection.getSelectedIndex();
        Uri[] r1 = this.playlistUrls;
        if (r0 < r1.length) goto L5;
    L7:
        HlsMediaPlaylist r02 = null;
    L8:
        if (r02 != null) goto L10;
    L21:
        return r12;
    L10:
        if (r02.segments.isEmpty() == true) goto L21;
        long r13 = r02.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
        long r5 = r12 - r13;
        int r11 = Util.binarySearchFloor(r02.segments, Long.valueOf(r5), true, true);
        long r7 = r02.segments.get(r11).relativeStartTimeUs;
        if (r02.hasIndependentSegments == true) goto L15;
    L18:
        long r9 = r7;
    L20:
        return r14.resolveSeekPositionUs(r5, r7, r9) + r13;
    L15:
        if (r11 == (r02.segments.size() - 1)) goto L18;
        r9 = r02.segments.get(r11 + 1).relativeStartTimeUs;
        goto L20
    L5:
        if (r0 == (-1)) goto L7;
        r02 = this.playlistTracker.getPlaylistSnapshot(r1[this.trackSelection.getSelectedIndexInTrackGroup()], true);
        goto L8
    }

    public int getChunkPublicationState(HlsMediaChunk r8) {
        if (r8.partIndex != (-1)) goto L5;
        return 1;
    L5:
        Uri r0 = this.playlistUrls[this.trackGroup.indexOf(r8.trackFormat)];
        HlsMediaPlaylist r7 = (HlsMediaPlaylist) Assertions.checkNotNull(this.playlistTracker.getPlaylistSnapshot(r0, false));
        int r02 = (int) (r8.chunkIndex - r7.mediaSequence);
        if (r02 >= 0) goto L9;
        return 1;
    L9:
        if (r02 >= r7.segments.size()) goto L11;
        List<HlsMediaPlaylist.Part> r03 = r7.segments.get(r02).parts;
    L13:
        if (r8.partIndex < r03.size()) goto L15;
        return 2;
    L15:
        HlsMediaPlaylist.Part r04 = r03.get(r8.partIndex);
        if (r04.isPreload == false) goto L19;
        return 0;
    L19:
        if (Objects.equals(Uri.parse(UriUtil.resolve(r7.baseUri, r04.url)), r8.dataSpec.uri) == false) goto L21;
        return 1;
    L21:
        return 2;
    L11:
        r03 = r7.trailingParts;
        goto L13
    }

    public void getNextChunk(LoadingInfo r35, long r36, long r38, List<HlsMediaChunk> r40, boolean r41, HlsChunkHolder r42) {
        if (r40.isEmpty() == false) goto L5;
        HlsMediaChunk r1 = null;
    L6:
        if (r1 != null) goto L9;
        int r11 = -1;
    L10:
        long r13 = r35.playbackPositionUs;
        long r2 = r36 - r13;
        long r4 = resolveTimeToLiveEdgeUs(r13);
        if (r1 != null) goto L13;
    L17:
        MediaChunkIterator[] r20 = createMediaChunkIterators(r1, r36);
        this.trackSelection.updateSelectedTrack(r13, r2, r4, r40, r20);
        int r9 = this.trackSelection.getSelectedIndexInTrackGroup();
        if (r11 == r9) goto L20;
        boolean r22 = true;
    L21:
        Uri r132 = this.playlistUrls[r9];
        if (this.playlistTracker.isSnapshotValid(r132) == true) goto L25;
        r42.playlistUrl = r132;
        this.nextChunkStuckOnPlaylistUrl = r132;
        return;
    L25:
        HlsMediaPlaylist r3 = this.playlistTracker.getPlaylistSnapshot(r132, true);
        Assertions.checkNotNull(r3);
        this.independentSegments = r3.hasIndependentSegments;
        updateLiveEdgeTimeUs(r3);
        long r16 = r3.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
        Pair<Long, Integer> r14 = getNextMediaSequenceAndPartIndex(r1, r22, r3, r16, r36);
        long r43 = ((Long) r14.first).longValue();
        int r142 = ((Integer) r14.second).intValue();
        boolean r10 = r22;
        int r23 = r11;
        HlsMediaPlaylist r112 = r3;
        HlsMediaChunk r15 = r1;
        long r12 = r43;
        if (shouldForceKeepCurrentTrackSelection(r10, r112, r12, r142, r15, r16, r38) == false) goto L29;
        Uri r133 = this.playlistUrls[r23];
        HlsMediaPlaylist r17 = this.playlistTracker.getPlaylistSnapshot(r133, true);
        Assertions.checkNotNull(r17);
        long r5 = r17.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
        int r102 = r23;
        boolean r152 = false;
        Pair<Long, Integer> r24 = getNextMediaSequenceAndPartIndex(r15, false, r17, r5, r36);
        long r6 = ((Long) r24.first).longValue();
        r142 = ((Integer) r24.second).intValue();
        r112 = r17;
        Uri r32 = r133;
        r12 = r6;
        long r62 = r5;
        r9 = r102;
    L30:
        if (r9 == r102) goto L34;
        if (r102 == (-1)) goto L34;
        this.playlistTracker.deactivatePlaylistForPlayback(this.playlistUrls[r102]);
    L34:
        if (r12 >= r112.mediaSequence) goto L37;
        this.fatalError = new BehindLiveWindowException();
        return;
    L37:
        SegmentBaseHolder r44 = getNextSegmentHolder(r112, r12, r142);
        if (r44 == null) goto L40;
        boolean r18 = true;
        SegmentBaseHolder r52 = r44;
    L52:
        this.nextChunkStuckOnPlaylistUrl = null;
        this.lastChunkRequestRealtimeMs = SystemClock.elapsedRealtime();
        Uri r103 = getFullEncryptionKeyUri(r112, r52.segmentBase.initializationSegment);
        Chunk r19 = maybeCreateEncryptionChunkFor(r103, r9, r18, null);
        r42.chunk = r19;
        if (r19 != null) goto L65;
        Uri r134 = getFullEncryptionKeyUri(r112, r52.segmentBase);
        Chunk r110 = maybeCreateEncryptionChunkFor(r134, r9, r152, null);
        r42.chunk = r110;
        if (r110 != null) goto L66;
        boolean r29 = isIndependent(r52, r112);
        boolean r28 = HlsMediaChunk.shouldSpliceIn(r15, r36, r32, r29, r52, r62);
        Uri r172 = r32;
        if (r28 == true) goto L61;
    L63:
        long r135 = r62;
        r42.chunk = HlsMediaChunk.createInstance(this.extractorFactory, this.mediaDataSource, this.playlistFormats[r9], r135, r112, r52, r172, this.muxedCaptionFormats, this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData(), this.isPrimaryTimestampSource, this.timestampAdjusterProvider, this.timestampAdjusterInitializationTimeoutMs, r15, this.keyCache.get(r134), this.keyCache.get(r103), r28, r29, this.playerId, null);
        return;
    L61:
        if (r52.isPreload == false) goto L63;
        return;
    L66:
        return;
    L65:
        return;
    L40:
        if (r112.hasEndTag == true) goto L43;
        r42.playlistUrl = r32;
        this.nextChunkStuckOnPlaylistUrl = r32;
        return;
    L43:
        if (r41 == true) goto L49;
        if (r112.segments.isEmpty() == true) goto L49;
        r52 = new SegmentBaseHolder((HlsMediaPlaylist.SegmentBase) Iterables.getLast(r112.segments), (r112.mediaSequence + r112.segments.size()) - 1, -1);
        r18 = true;
    L49:
        r42.endOfStream = true;
        return;
    L29:
        r102 = r23;
        r152 = false;
        r32 = r132;
        r62 = r16;
        goto L30
    L20:
        r22 = false;
        goto L21
    L13:
        if (this.independentSegments == true) goto L17;
        long r153 = r1.getDurationUs();
        r2 = Math.max(0, r2 - r153);
        if (r4 == (-9223372036854775807L)) goto L17;
        r4 = Math.max(0, r4 - r153);
        goto L17
    L9:
        r11 = this.trackGroup.indexOf(r1.trackFormat);
        goto L10
    L5:
        r1 = (HlsMediaChunk) Iterables.getLast(r40);
        goto L6
    }

    public int getPreferredQueueSize(long r3, List<? extends MediaChunk> r5) {
        if (this.fatalError != null) goto L10;
        if (this.trackSelection.length() < 2) goto L10;
        return this.trackSelection.evaluateQueueSize(r3, r5);
    L10:
        return r5.size();
    }

    public long getPublishedPartDurationUs(HlsMediaChunk r5) {
        if (r5.partIndex == (-1)) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        HlsMediaPlaylist r4 = (HlsMediaPlaylist) Assertions.checkNotNull(this.playlistTracker.getPlaylistSnapshot(this.playlistUrls[this.trackGroup.indexOf(r5.trackFormat)], false));
        int r02 = (int) (r5.chunkIndex - r4.mediaSequence);
        if (r02 >= 0) goto L11;
        return 0;
    L11:
        if (r02 >= r4.segments.size()) goto L13;
        List<HlsMediaPlaylist.Part> r42 = r4.segments.get(r02).parts;
    L15:
        return r42.get(r5.partIndex).durationUs;
    L13:
        r42 = r4.trailingParts;
        goto L15
    L5:
        r0 = false;
        goto L6
    }

    public TrackGroup getTrackGroup() {
        return this.trackGroup;
    }

    public ExoTrackSelection getTrackSelection() {
        return this.trackSelection;
    }

    public boolean hasIndependentSegments() {
        return this.independentSegments;
    }

    public boolean maybeExcludeTrack(Chunk r2, long r3) {
        ExoTrackSelection r0 = this.trackSelection;
        return r0.excludeTrack(r0.indexOf(this.trackGroup.indexOf(r2.trackFormat)), r3);
    }

    public void maybeThrowError() {
        IOException r0 = this.fatalError;
        if (r0 != null) goto L10;
        Uri r02 = this.lastPlaylistErrorUrl;
        if (r02 != null) goto L7;
        return;
    L7:
        if (r02.equals(this.nextChunkStuckOnPlaylistUrl) == false) goto L12;
        this.playlistTracker.maybeThrowPlaylistRefreshError(this.lastPlaylistErrorUrl);
        return;
    L12:
        return;
    L10:
        throw r0;
    }

    public boolean obtainsChunksForPlaylist(Uri r1) {
        return Util.contains(this.playlistUrls, r1);
    }

    public void onChunkLoadCompleted(Chunk r2) {
        if ((r2 instanceof EncryptionKeyChunk) == false) goto L6;
        EncryptionKeyChunk r22 = (EncryptionKeyChunk) r2;
        this.scratchSpace = r22.getDataHolder();
        this.keyCache.put(r22.dataSpec.uri, (byte[]) Assertions.checkNotNull(r22.getResult()));
        return;
    }

    public boolean onPlaylistError(Uri r6, long r7) {
        int r1 = 0;
    L3:
        Uri[] r2 = this.playlistUrls;
        if (r1 >= r2.length) goto L9;
        if (r2[r1].equals(r6) == true) goto L11;
        r1 = r1 + 1;
    L11:
        if (r1 != (-1)) goto L13;
        return true;
    L13:
        int r12 = this.trackSelection.indexOf(r1);
        if (r12 != (-1)) goto L16;
        return true;
    L16:
        this.lastPlaylistErrorUrl = r6;
        if (r7 != (-9223372036854775807L)) goto L19;
    L23:
        return false;
    L19:
        if (this.trackSelection.excludeTrack(r12, r7) == false) goto L23;
        if (this.playlistTracker.excludeMediaPlaylist(r6, r7) == false) goto L23;
        return true;
    L9:
        r1 = -1;
        goto L11
    }

    public void reset() {
        deactivatePlaylistForSelectedTrack();
        this.fatalError = null;
    }

    public void setIsPrimaryTimestampSource(boolean r1) {
        this.isPrimaryTimestampSource = r1;
    }

    public void setTrackSelection(ExoTrackSelection r1) {
        deactivatePlaylistForSelectedTrack();
        this.trackSelection = r1;
    }

    public boolean shouldCancelLoad(long r2, Chunk r4, List<? extends MediaChunk> r5) {
        if (this.fatalError == null) goto L7;
        return false;
    L7:
        return this.trackSelection.shouldCancelChunkLoad(r2, r4, r5);
    }
}
