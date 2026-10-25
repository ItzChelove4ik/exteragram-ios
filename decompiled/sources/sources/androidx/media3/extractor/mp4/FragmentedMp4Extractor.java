package androidx.media3.extractor.mp4;

import android.util.Pair;
import android.util.SparseArray;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.common.util.Util;
import androidx.media3.container.Mp4Box;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.container.ReorderingBufferQueue;
import androidx.media3.extractor.Ac4Util;
import androidx.media3.extractor.CeaUtil;
import androidx.media3.extractor.ChunkIndex;
import androidx.media3.extractor.ChunkIndexMerger;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.GaplessInfoHolder;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SniffFailure;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.metadata.emsg.EventMessage;
import androidx.media3.extractor.metadata.emsg.EventMessageEncoder;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.SubtitleTranscodingExtractorOutput;
import com.google.common.collect.ImmutableList;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import kotlin.UByte;
import kotlin.jvm.internal.LongCompanionObject;
import org.telegram.messenger.MediaController;

/* loaded from: classes4.dex */
public class FragmentedMp4Extractor implements Extractor {
    private static final Format EMSG_FORMAT = null;

    @Deprecated
    public static final ExtractorsFactory FACTORY = null;
    private static final byte[] PIFF_SAMPLE_ENCRYPTION_BOX_EXTENDED_TYPE = null;
    private final TrackOutput additionalEmsgTrackOutput;
    private ParsableByteArray atomData;
    private final ParsableByteArray atomHeader;
    private int atomHeaderBytesRead;
    private long atomSize;
    private int atomType;
    private TrackOutput[] ceaTrackOutputs;
    private final ChunkIndexMerger chunkIndexMerger;
    private final List<Format> closedCaptionFormats;
    private final ArrayDeque<Mp4Box.ContainerBox> containerAtoms;
    private TrackBundle currentTrackBundle;
    private long durationUs;
    private TrackOutput[] emsgTrackOutputs;
    private long endOfMdatPosition;
    private final EventMessageEncoder eventMessageEncoder;
    private ExtractorOutput extractorOutput;
    private final int flags;
    private boolean haveOutputSeekMap;
    private boolean haveOutputSeekMapFromMultipleSidx;
    private boolean isSampleDependedOn;
    private ImmutableList<SniffFailure> lastSniffFailures;
    private final ParsableByteArray nalPrefix;
    private final ParsableByteArray nalStartCode;
    private final ParsableByteArray nalUnitWithoutHeaderBuffer;
    private int parserState;
    private int pendingMetadataSampleBytes;
    private final ArrayDeque<MetadataSampleInfo> pendingMetadataSampleInfos;
    private long pendingSeekTimeUs;
    private boolean processSeiNalUnitPayload;
    private final ReorderingBufferQueue reorderingBufferQueue;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private int sampleSize;
    private final ParsableByteArray scratch;
    private final byte[] scratchBytes;
    private long seekPositionBeforeSidxProcessing;
    private long segmentIndexEarliestPresentationTimeUs;
    private final Track sideloadedTrack;
    private final SubtitleParser.Factory subtitleParserFactory;
    private final TimestampAdjuster timestampAdjuster;
    private final SparseArray<TrackBundle> trackBundles;

    public static final class MetadataSampleInfo {
        public final boolean sampleTimeIsRelative;
        public final long sampleTimeUs;
        public final int size;

        public MetadataSampleInfo(long r1, boolean r3, int r4) {
            this.sampleTimeUs = r1;
            this.sampleTimeIsRelative = r3;
            this.size = r4;
        }
    }

    public static final class TrackBundle {
        private final Format baseFormat;
        public int currentSampleInTrackRun;
        public int currentSampleIndex;
        public int currentTrackRunIndex;
        private boolean currentlyInFragment;
        private final ParsableByteArray defaultInitializationVector;
        public DefaultSampleValues defaultSampleValues;
        private final ParsableByteArray encryptionSignalByte;
        public int firstSampleToOutputIndex;
        public final TrackFragment fragment;
        public TrackSampleTable moovSampleTable;
        public final TrackOutput output;
        public final ParsableByteArray scratch;

        public TrackBundle(TrackOutput r1, TrackSampleTable r2, DefaultSampleValues r3, Format r4) {
            this.output = r1;
            this.moovSampleTable = r2;
            this.defaultSampleValues = r3;
            this.baseFormat = r4;
            this.fragment = new TrackFragment();
            this.scratch = new ParsableByteArray();
            this.encryptionSignalByte = new ParsableByteArray(1);
            this.defaultInitializationVector = new ParsableByteArray();
            reset(r2, r3);
        }

        public static /* synthetic */ boolean access$000(TrackBundle r0) {
            return r0.currentlyInFragment;
        }

        public static /* synthetic */ boolean access$002(TrackBundle r0, boolean r1) {
            r0.currentlyInFragment = r1;
            return r1;
        }

        public int getCurrentSampleFlags() {
            if (this.currentlyInFragment == true) goto L6;
            int r0 = this.moovSampleTable.flags[this.currentSampleIndex];
        L10:
            if (getEncryptionBoxIfEncrypted() != null) goto L12;
            return r0;
        L12:
            return 1073741824 | r0;
        L6:
            if (this.fragment.sampleIsSyncFrameTable[this.currentSampleIndex] == false) goto L8;
            r0 = 1;
            goto L10
        L8:
            r0 = 0;
            goto L10
        }

        public long getCurrentSampleOffset() {
            if (this.currentlyInFragment == true) goto L7;
            return this.moovSampleTable.offsets[this.currentSampleIndex];
        L7:
            return this.fragment.trunDataPosition[this.currentTrackRunIndex];
        }

        public long getCurrentSamplePresentationTimeUs() {
            if (this.currentlyInFragment == true) goto L7;
            return this.moovSampleTable.timestampsUs[this.currentSampleIndex];
        L7:
            return this.fragment.getSamplePresentationTimeUs(this.currentSampleIndex);
        }

        public int getCurrentSampleSize() {
            if (this.currentlyInFragment == true) goto L7;
            return this.moovSampleTable.sizes[this.currentSampleIndex];
        L7:
            return this.fragment.sampleSizeTable[this.currentSampleIndex];
        }

        public TrackEncryptionBox getEncryptionBoxIfEncrypted() {
            if (this.currentlyInFragment == true) goto L5;
            return null;
        L5:
            int r0 = ((DefaultSampleValues) Util.castNonNull(this.fragment.header)).sampleDescriptionIndex;
            TrackEncryptionBox r2 = this.fragment.trackEncryptionBox;
            if (r2 != null) goto L9;
            r2 = this.moovSampleTable.track.getSampleDescriptionEncryptionBox(r0);
        L9:
            if (r2 != null) goto L11;
        L13:
            return null;
        L11:
            if (r2.isEncrypted == false) goto L13;
            return r2;
        }

        public boolean next() {
            this.currentSampleIndex++;
            if (this.currentlyInFragment == true) goto L5;
            return false;
        L5:
            int r0 = this.currentSampleInTrackRun + 1;
            this.currentSampleInTrackRun = r0;
            int[] r3 = this.fragment.trunLength;
            int r4 = this.currentTrackRunIndex;
            if (r0 != r3[r4]) goto L9;
            this.currentTrackRunIndex = r4 + 1;
            this.currentSampleInTrackRun = 0;
            return false;
        L9:
            return true;
        }

        public int outputSampleEncryptionData(int r11, int r12) {
            TrackEncryptionBox r0 = getEncryptionBoxIfEncrypted();
            if (r0 != null) goto L5;
            return 0;
        L5:
            int r2 = r0.perSampleIvSize;
            if (r2 == 0) goto L8;
            ParsableByteArray r02 = this.fragment.sampleEncryptionData;
        L9:
            boolean r3 = this.fragment.sampleHasSubsampleEncryptionTable(this.currentSampleIndex);
            if (r3 == true) goto L14;
            if (r12 != 0) goto L14;
            boolean r5 = false;
        L15:
            byte[] r6 = this.encryptionSignalByte.getData();
            if (r5 == false) goto L18;
            int r7 = 128;
        L19:
            r6[0] = (byte) (r7 | r2);
            this.encryptionSignalByte.setPosition(0);
            this.output.sampleData(this.encryptionSignalByte, 1, 1);
            this.output.sampleData(r02, r2, 1);
            if (r5 == true) goto L24;
            return r2 + 1;
        L24:
            if (r3 == true) goto L27;
            this.scratch.reset(8);
            byte[] r32 = this.scratch.getData();
            r32[0] = 0;
            r32[1] = 1;
            r32[2] = (byte) ((r12 >> 8) & 255);
            r32[3] = (byte) (r12 & 255);
            r32[4] = (byte) ((r11 >> 24) & 255);
            r32[5] = (byte) ((r11 >> 16) & 255);
            r32[6] = (byte) ((r11 >> 8) & 255);
            r32[7] = (byte) (r11 & 255);
            this.output.sampleData(this.scratch, 8, 1);
            return r2 + 9;
        L27:
            ParsableByteArray r112 = this.fragment.sampleEncryptionData;
            int r33 = r112.readUnsignedShort();
            r112.skipBytes(-2);
            int r34 = (r33 * 6) + 2;
            if (r12 == 0) goto L30;
            this.scratch.reset(r34);
            byte[] r03 = this.scratch.getData();
            r112.readBytes(r03, 0, r34);
            int r113 = (((r03[2] & UByte.MAX_VALUE) << 8) | (r03[3] & UByte.MAX_VALUE)) + r12;
            r03[2] = (byte) ((r113 >> 8) & 255);
            r03[3] = (byte) (r113 & 255);
            r112 = this.scratch;
        L30:
            this.output.sampleData(r112, r34, 1);
            return (r2 + 1) + r34;
        L18:
            r7 = 0;
        L14:
            r5 = true;
            goto L15
        L8:
            byte[] r04 = (byte[]) Util.castNonNull(r0.defaultInitializationVector);
            this.defaultInitializationVector.reset(r04, r04.length);
            ParsableByteArray r22 = this.defaultInitializationVector;
            r2 = r04.length;
            r02 = r22;
            goto L9
        }

        public void reset(TrackSampleTable r1, DefaultSampleValues r2) {
            this.moovSampleTable = r1;
            this.defaultSampleValues = r2;
            this.output.format(this.baseFormat);
            resetFragmentInfo();
        }

        public void resetFragmentInfo() {
            this.fragment.reset();
            this.currentSampleIndex = 0;
            this.currentTrackRunIndex = 0;
            this.currentSampleInTrackRun = 0;
            this.firstSampleToOutputIndex = 0;
            this.currentlyInFragment = false;
        }

        public void seek(long r4) {
            int r0 = this.currentSampleIndex;
        L3:
            TrackFragment r1 = this.fragment;
            if (r0 >= r1.sampleCount) goto L11;
            if (r1.getSamplePresentationTimeUs(r0) > r4) goto L16;
            if (this.fragment.sampleIsSyncFrameTable[r0] == false) goto L10;
            this.firstSampleToOutputIndex = r0;
        L10:
            r0 = r0 + 1;
            goto L3
        L16:
            return;
        }

        public void skipSampleEncryptionData() {
            TrackEncryptionBox r0 = getEncryptionBoxIfEncrypted();
            if (r0 == null) goto L13;
            ParsableByteArray r1 = this.fragment.sampleEncryptionData;
            int r02 = r0.perSampleIvSize;
            if (r02 == 0) goto L9;
            r1.skipBytes(r02);
        L9:
            if (this.fragment.sampleHasSubsampleEncryptionTable(this.currentSampleIndex) == false) goto L12;
            r1.skipBytes(r1.readUnsignedShort() * 6);
            return;
        L12:
            return;
        }

        public void updateDrmInitData(DrmInitData r3) {
            TrackEncryptionBox r0 = this.moovSampleTable.track.getSampleDescriptionEncryptionBox(((DefaultSampleValues) Util.castNonNull(this.fragment.header)).sampleDescriptionIndex);
            if (r0 == null) goto L5;
            String r02 = r0.schemeType;
        L6:
            DrmInitData r32 = r3.copyWithSchemeType(r02);
            Format r33 = this.baseFormat.buildUpon().setDrmInitData(r32).build();
            this.output.format(r33);
            return;
        L5:
            r02 = null;
            goto L6
        }
    }

    /* renamed from: $r8$lambda$mshaahP_GfzlK-XpoPIIJ17EX54, reason: not valid java name */
    public static /* synthetic */ Extractor[] m857$r8$lambda$mshaahP_GfzlKXpoPIIJ17EX54() {
        return new Extractor[]{new FragmentedMp4Extractor(SubtitleParser.Factory.UNSUPPORTED, 32)};
    }

    public static /* synthetic */ void $r8$lambda$tBYVtDHCMH8xrQ1aRWBKD8kAvDc(FragmentedMp4Extractor r0, long r1, ParsableByteArray r3) {
        CeaUtil.consume(r1, r3, r0.ceaTrackOutputs);
    }

    static {
        FACTORY = new FragmentedMp4Extractor$$ExternalSyntheticLambda1();
        PIFF_SAMPLE_ENCRYPTION_BOX_EXTENDED_TYPE = new byte[]{-94, 57, 79, 82, 90, -101, 79, 20, -94, 68, 108, 66, 124, 100, -115, -12};
        EMSG_FORMAT = new Format.Builder().setSampleMimeType("application/x-emsg").build();
    }

    public FragmentedMp4Extractor(SubtitleParser.Factory r8, int r9) {
        this(r8, r9, null, null, ImmutableList.of(), null);
    }

    public FragmentedMp4Extractor(SubtitleParser.Factory r1, int r2, TimestampAdjuster r3, Track r4, List<Format> r5, TrackOutput r6) {
        this.subtitleParserFactory = r1;
        this.flags = r2;
        this.timestampAdjuster = r3;
        this.sideloadedTrack = r4;
        this.closedCaptionFormats = Collections.unmodifiableList(r5);
        this.additionalEmsgTrackOutput = r6;
        this.eventMessageEncoder = new EventMessageEncoder();
        this.atomHeader = new ParsableByteArray(16);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalPrefix = new ParsableByteArray(6);
        this.nalUnitWithoutHeaderBuffer = new ParsableByteArray();
        byte[] r12 = new byte[16];
        this.scratchBytes = r12;
        this.scratch = new ParsableByteArray(r12);
        this.containerAtoms = new ArrayDeque();
        this.pendingMetadataSampleInfos = new ArrayDeque();
        this.trackBundles = new SparseArray();
        this.lastSniffFailures = ImmutableList.of();
        this.durationUs = -9223372036854775807L;
        this.pendingSeekTimeUs = -9223372036854775807L;
        this.segmentIndexEarliestPresentationTimeUs = -9223372036854775807L;
        this.extractorOutput = ExtractorOutput.PLACEHOLDER;
        this.emsgTrackOutputs = new TrackOutput[0];
        this.ceaTrackOutputs = new TrackOutput[0];
        this.reorderingBufferQueue = new ReorderingBufferQueue(new FragmentedMp4Extractor$$ExternalSyntheticLambda2(this));
        this.chunkIndexMerger = new ChunkIndexMerger();
        this.seekPositionBeforeSidxProcessing = -1;
    }

    private boolean canReadWithinGopSampleDependencies(Format r4) {
        if (Objects.equals(r4.sampleMimeType, MediaController.VIDEO_MIME_TYPE) == false) goto L9;
        if ((this.flags & 64) == 0) goto L7;
        return true;
    L7:
        return false;
    L9:
        if (Objects.equals(r4.sampleMimeType, "video/hevc") == true) goto L11;
    L13:
        return false;
    L11:
        if ((this.flags & 128) == 0) goto L13;
        return true;
    }

    private static int checkNonNegative(int r2) {
        if (r2 < 0) goto L5;
        return r2;
    L5:
        throw ParserException.createForMalformedContainer("Unexpected negative value: " + r2, null);
    }

    public static int codecsToParseWithinGopSampleDependenciesAsFlags(int r1) {
        if ((r1 & 1) == 0) goto L5;
        int r0 = 64;
    L7:
        if ((r1 & 2) != 0) goto L9;
        return r0;
    L9:
        return r0 | 128;
    L5:
        r0 = 0;
        goto L7
    }

    private void enterReadingAtomHeaderState() {
        this.parserState = 0;
        this.atomHeaderBytesRead = 0;
    }

    private DefaultSampleValues getDefaultSampleValues(SparseArray<DefaultSampleValues> r2, int r3) {
        if (r2.size() != 1) goto L7;
        return r2.valueAt(0);
    L7:
        return (DefaultSampleValues) Assertions.checkNotNull(r2.get(r3));
    }

    private static DrmInitData getDrmInitDataFromAtoms(List<Mp4Box.LeafBox> r8) {
        int r0 = r8.size();
        int r2 = 0;
        ArrayList r3 = null;
    L3:
        if (r2 >= r0) goto L13;
        Mp4Box.LeafBox r4 = r8.get(r2);
        if (r4.type != 1886614376) goto L12;
        if (r3 != null) goto L8;
        r3 = new ArrayList();
    L8:
        byte[] r42 = r4.data.getData();
        UUID r5 = PsshAtomUtil.parseUuid(r42);
        if (r5 != null) goto L11;
        Log.w("FragmentedMp4Extractor", "Skipped pssh atom (failed to extract uuid)");
        goto L12
    L11:
        r3.add(new DrmInitData.SchemeData(r5, "video/mp4", r42));
    L12:
        r2 = r2 + 1;
        goto L3
    L13:
        if (r3 != null) goto L16;
        return null;
    L16:
        return new DrmInitData(r3);
    }

    private static TrackBundle getNextTrackBundle(SparseArray<TrackBundle> r9) {
        int r0 = r9.size();
        TrackBundle r1 = null;
        long r2 = LongCompanionObject.MAX_VALUE;
        int r4 = 0;
    L3:
        if (r4 >= r0) goto L17;
        TrackBundle r5 = r9.valueAt(r4);
        if (TrackBundle.access$000(r5) == true) goto L9;
        if (r5.currentSampleIndex != r5.moovSampleTable.sampleCount) goto L9;
    L16:
        r4 = r4 + 1;
    L9:
        if (TrackBundle.access$000(r5) == true) goto L11;
    L13:
        long r6 = r5.getCurrentSampleOffset();
        if (r6 >= r2) goto L16;
        r1 = r5;
        r2 = r6;
        goto L16
    L11:
        if (r5.currentTrackRunIndex != r5.fragment.trunCount) goto L13;
    L17:
        return r1;
    }

    private void initExtraTracks() {
        TrackOutput[] r0 = new TrackOutput[2];
        this.emsgTrackOutputs = r0;
        TrackOutput r1 = this.additionalEmsgTrackOutput;
        int r2 = 0;
        if (r1 == null) goto L5;
        r0[0] = r1;
        int r12 = 1;
    L6:
        int r4 = 100;
        if ((this.flags & 4) == 0) goto L9;
        r0[r12] = this.extractorOutput.track(100, 5);
        r4 = 101;
        r12 = r12 + 1;
    L9:
        TrackOutput[] r02 = (TrackOutput[]) Util.nullSafeArrayCopy(this.emsgTrackOutputs, r12);
        this.emsgTrackOutputs = r02;
        int r13 = r02.length;
        int r3 = 0;
    L10:
        if (r3 >= r13) goto L12;
        r02[r3].format(EMSG_FORMAT);
        r3 = r3 + 1;
        goto L10
    L12:
        this.ceaTrackOutputs = new TrackOutput[this.closedCaptionFormats.size()];
    L14:
        if (r2 >= this.ceaTrackOutputs.length) goto L16;
        TrackOutput r03 = this.extractorOutput.track(r4, 3);
        r03.format(this.closedCaptionFormats.get(r2));
        this.ceaTrackOutputs[r2] = r03;
        r2 = r2 + 1;
        r4 = r4 + 1;
        goto L14
    L16:
        return;
    L5:
        r12 = 0;
        goto L6
    }

    private static boolean isEdtsListDurationForEntireMediaTimeline(Track r12) {
        long[] r0 = r12.editListDurations;
        if (r0 != null) goto L5;
    L15:
        return false;
    L5:
        if (r0.length != 1) goto L15;
        if (r12.editListMediaTimes == null) goto L15;
        long r4 = r0[0];
        if (r4 != 0) goto L13;
        return true;
    L13:
        if ((Util.scaleLargeTimestamp(r4, 1000000, r12.movieTimescale) + Util.scaleLargeTimestamp(r12.editListMediaTimes[0], 1000000, r12.timescale)) < r12.durationUs) goto L15;
        return true;
    }

    private void maybeSkipRemainingMetaAtomHeaderBytes(ExtractorInput r4) {
        this.scratch.reset(8);
        r4.peekFully(this.scratch.getData(), 0, 8);
        BoxParser.maybeSkipRemainingMetaBoxHeaderBytes(this.scratch);
        r4.skipFully(this.scratch.getPosition());
        r4.resetPeekPosition();
    }

    private void onContainerAtomRead(Mp4Box.ContainerBox r3) {
        int r0 = r3.type;
        if (r0 != 1836019574) goto L7;
        onMoovContainerAtomRead(r3);
        return;
    L7:
        if (r0 != 1836019558) goto L11;
        onMoofContainerAtomRead(r3);
        return;
    L11:
        if (this.containerAtoms.isEmpty() == true) goto L14;
        this.containerAtoms.peek().add(r3);
        return;
    }

    private void onEmsgLeafAtomRead(ParsableByteArray r24) {
        if (this.emsgTrackOutputs.length == 0) goto L40;
        r24.setPosition(8);
        int r2 = BoxParser.parseFullBoxVersion(r24.readInt());
        if (r2 == 0) goto L12;
        if (r2 == 1) goto L10;
        Log.w("FragmentedMp4Extractor", "Skipping unsupported emsg version: " + r2);
        return;
    L10:
        long r10 = r24.readUnsignedInt();
        long r12 = Util.scaleLargeTimestamp(r24.readUnsignedLongToLong(), 1000000, r10);
        long r6 = Util.scaleLargeTimestamp(r24.readUnsignedInt(), 1000, r10);
        long r8 = r24.readUnsignedInt();
        String r22 = (String) Assertions.checkNotNull(r24.readNullTerminatedString());
        String r102 = (String) Assertions.checkNotNull(r24.readNullTerminatedString());
        long r18 = r6;
        long r20 = r8;
        long r62 = -9223372036854775807L;
    L11:
        String r16 = r22;
        String r17 = r102;
        byte[] r23 = new byte[r24.bytesLeft()];
        int r9 = 0;
        r24.readBytes(r23, 0, r24.bytesLeft());
        ParsableByteArray r1 = new ParsableByteArray(this.eventMessageEncoder.encode(new EventMessage(r16, r17, r18, r20, r23)));
        int r25 = r1.bytesLeft();
        TrackOutput[] r82 = this.emsgTrackOutputs;
        int r103 = r82.length;
        int r11 = 0;
    L18:
        if (r11 >= r103) goto L20;
        TrackOutput r14 = r82[r11];
        r1.setPosition(0);
        r14.sampleData(r1, r25);
        r11 = r11 + 1;
        goto L18
    L20:
        ArrayDeque<MetadataSampleInfo> r4 = this.pendingMetadataSampleInfos;
        if (r12 != (-9223372036854775807L)) goto L25;
        r4.addLast(new MetadataSampleInfo(r62, true, r25));
        this.pendingMetadataSampleBytes += r25;
        return;
    L25:
        if (r4.isEmpty() == true) goto L28;
        this.pendingMetadataSampleInfos.addLast(new MetadataSampleInfo(r12, false, r25));
        this.pendingMetadataSampleBytes += r25;
        return;
    L28:
        TimestampAdjuster r13 = this.timestampAdjuster;
        if (r13 != null) goto L31;
    L34:
        TimestampAdjuster r15 = this.timestampAdjuster;
        if (r15 == null) goto L37;
        r12 = r15.adjustSampleTimestamp(r12);
    L37:
        long r152 = r12;
        TrackOutput[] r0 = this.emsgTrackOutputs;
        int r19 = r0.length;
    L38:
        if (r9 >= r19) goto L43;
        r0[r9].sampleMetadata(r152, 1, r25, 0, null);
        r9 = r9 + 1;
        goto L38
    L43:
        return;
    L31:
        if (r13.isInitialized() == true) goto L34;
        this.pendingMetadataSampleInfos.addLast(new MetadataSampleInfo(r12, false, r25));
        this.pendingMetadataSampleBytes += r25;
        return;
    L12:
        r22 = (String) Assertions.checkNotNull(r24.readNullTerminatedString());
        r102 = (String) Assertions.checkNotNull(r24.readNullTerminatedString());
        long r153 = r24.readUnsignedInt();
        r62 = Util.scaleLargeTimestamp(r24.readUnsignedInt(), 1000000, r153);
        long r83 = this.segmentIndexEarliestPresentationTimeUs;
        if (r83 == (-9223372036854775807L)) goto L15;
        long r84 = r83 + r62;
    L16:
        r18 = Util.scaleLargeTimestamp(r24.readUnsignedInt(), 1000, r153);
        r20 = r24.readUnsignedInt();
        r12 = r84;
        goto L11
    L15:
        r84 = -9223372036854775807L;
        goto L16
    }

    private void onLeafAtomRead(Mp4Box.LeafBox r5, ExtractorInput r6) {
        if (this.containerAtoms.isEmpty() == true) goto L6;
        this.containerAtoms.peek().add(r5);
        return;
    L6:
        int r0 = r5.type;
        if (r0 != 1936286840) goto L21;
        Pair<Long, ChunkIndex> r52 = parseSidx(r5.data, r6.getPosition());
        this.chunkIndexMerger.add((ChunkIndex) r52.second);
        if (this.haveOutputSeekMap == true) goto L13;
        this.segmentIndexEarliestPresentationTimeUs = ((Long) r52.first).longValue();
        this.extractorOutput.seekMap((SeekMap) r52.second);
        this.haveOutputSeekMap = true;
        return;
    L13:
        if ((this.flags & 256) != 0) goto L15;
        return;
    L15:
        if (this.haveOutputSeekMapFromMultipleSidx == false) goto L17;
        return;
    L17:
        if (this.chunkIndexMerger.size() <= 1) goto L26;
        this.seekPositionBeforeSidxProcessing = r6.getPosition();
        return;
    L26:
        return;
    L21:
        if (r0 != 1701671783) goto L27;
        onEmsgLeafAtomRead(r5.data);
        return;
    }

    private void onMoofContainerAtomRead(Mp4Box.ContainerBox r8) {
        SparseArray<TrackBundle> r0 = this.trackBundles;
        int r2 = 0;
        if (this.sideloadedTrack == null) goto L5;
        boolean r1 = true;
    L6:
        parseMoof(r8, r0, r1, this.flags, this.scratchBytes);
        DrmInitData r82 = getDrmInitDataFromAtoms(r8.leafChildren);
        if (r82 == null) goto L12;
        int r02 = this.trackBundles.size();
        int r12 = 0;
    L9:
        if (r12 >= r02) goto L12;
        this.trackBundles.valueAt(r12).updateDrmInitData(r82);
        r12 = r12 + 1;
    L12:
        if (this.pendingSeekTimeUs == (-9223372036854775807L)) goto L20;
        int r83 = this.trackBundles.size();
    L14:
        if (r2 >= r83) goto L16;
        this.trackBundles.valueAt(r2).seek(this.pendingSeekTimeUs);
        r2 = r2 + 1;
        goto L14
    L16:
        this.pendingSeekTimeUs = -9223372036854775807L;
        return;
    L20:
        return;
    L5:
        r1 = false;
        goto L6
    }

    private void onMoovContainerAtomRead(Mp4Box.ContainerBox r17) {
        int r9 = 0;
        boolean r10 = true;
        if (this.sideloadedTrack != null) goto L5;
        boolean r2 = true;
    L6:
        Assertions.checkState(r2, "Unexpected moov box.");
        DrmInitData r5 = getDrmInitDataFromAtoms(r17.leafChildren);
        Mp4Box.ContainerBox r22 = (Mp4Box.ContainerBox) Assertions.checkNotNull(r17.getContainerBoxOfType(1836475768));
        SparseArray<DefaultSampleValues> r11 = new SparseArray();
        int r3 = r22.leafChildren.size();
        long r6 = -9223372036854775807L;
        int r4 = 0;
    L7:
        if (r4 >= r3) goto L15;
        Mp4Box.LeafBox r8 = r22.leafChildren.get(r4);
        int r12 = r8.type;
        if (r12 != 1953654136) goto L12;
        Pair<Integer, DefaultSampleValues> r82 = parseTrex(r8.data);
        r11.put(((Integer) r82.first).intValue(), (DefaultSampleValues) r82.second);
    L14:
        r4 = r4 + 1;
        goto L7
    L12:
        if (r12 != 1835362404) goto L14;
        r6 = parseMehd(r8.data);
        goto L14
    L15:
        Mp4Box.ContainerBox r23 = r17.getContainerBoxOfType(1835365473);
        Metadata r32 = null;
        if (r23 == null) goto L18;
        Metadata r122 = BoxParser.parseMdtaFromMeta(r23);
    L19:
        GaplessInfoHolder r24 = new GaplessInfoHolder();
        Mp4Box.LeafBox r42 = r17.getLeafBoxOfType(1969517665);
        if (r42 == null) goto L22;
        r32 = BoxParser.parseUdta(r42);
        r24.setFromMetadata(r32);
    L22:
        Metadata r13 = r32;
        Metadata r14 = new Metadata(new Metadata.Entry[]{BoxParser.parseMvhd(((Mp4Box.LeafBox) Assertions.checkNotNull(r17.getLeafBoxOfType(1836476516))).data)});
        if ((this.flags & 16) == 0) goto L25;
        long r33 = r6;
        boolean r62 = true;
    L26:
        List<TrackSampleTable> r1 = BoxParser.parseTraks(r17, r24, r33, r5, r62, false, new FragmentedMp4Extractor$$ExternalSyntheticLambda0(this));
        int r34 = r1.size();
        if (this.trackBundles.size() != 0) goto L34;
        String r43 = MimeTypeResolver.getContainerMimeType(r1);
    L29:
        if (r9 >= r34) goto L31;
        TrackSampleTable r52 = r1.get(r9);
        Track r63 = r52.track;
        TrackOutput r7 = this.extractorOutput.track(r9, r63.type);
        r7.durationUs(r63.durationUs);
        Format.Builder r83 = r63.format.buildUpon();
        r83.setContainerMimeType(r43);
        MetadataUtil.setFormatGaplessInfo(r63.type, r24, r83);
        MetadataUtil.setFormatMetadata(r63.type, r122, r83, r63.format.metadata, new Metadata[]{r13, r14});
        this.trackBundles.put(r63.id, new TrackBundle(r7, r52, getDefaultSampleValues(r11, r63.id), r83.build()));
        this.durationUs = Math.max(this.durationUs, r63.durationUs);
        r9 = r9 + 1;
        r24 = r24;
        goto L29
    L31:
        this.extractorOutput.endTracks();
        return;
    L34:
        if (this.trackBundles.size() == r34) goto L37;
        r10 = false;
    L37:
        Assertions.checkState(r10);
    L38:
        if (r9 >= r34) goto L40;
        TrackSampleTable r25 = r1.get(r9);
        Track r44 = r25.track;
        this.trackBundles.get(r44.id).reset(r25, getDefaultSampleValues(r11, r44.id));
        r9 = r9 + 1;
        goto L38
    L40:
        return;
    L25:
        r33 = r6;
        r62 = false;
        goto L26
    L18:
        r122 = null;
        goto L19
    L5:
        r2 = false;
        goto L6
    }

    private void outputPendingMetadataSamples(long r12) {
    L3:
        if (this.pendingMetadataSampleInfos.isEmpty() == true) goto L13;
        MetadataSampleInfo r0 = this.pendingMetadataSampleInfos.removeFirst();
        this.pendingMetadataSampleBytes -= r0.size;
        long r1 = r0.sampleTimeUs;
        if (r0.sampleTimeIsRelative == false) goto L7;
        r1 = r1 + r12;
    L7:
        TimestampAdjuster r3 = this.timestampAdjuster;
        if (r3 == null) goto L10;
        r1 = r3.adjustSampleTimestamp(r1);
    L10:
        long r4 = r1;
        TrackOutput[] r13 = this.emsgTrackOutputs;
        int r2 = r13.length;
        int r10 = 0;
    L11:
        if (r10 >= r2) goto L3;
        r13[r10].sampleMetadata(r4, 1, r0.size, this.pendingMetadataSampleBytes, null);
        r10 = r10 + 1;
        goto L11
    }

    private static long parseMehd(ParsableByteArray r2) {
        r2.setPosition(8);
        if (BoxParser.parseFullBoxVersion(r2.readInt()) != 0) goto L7;
        return r2.readUnsignedInt();
    L7:
        return r2.readUnsignedLongToLong();
    }

    private static void parseMoof(Mp4Box.ContainerBox r5, SparseArray<TrackBundle> r6, boolean r7, int r8, byte[] r9) {
        int r0 = r5.containerChildren.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        Mp4Box.ContainerBox r2 = r5.containerChildren.get(r1);
        if (r2.type != 1953653094) goto L7;
        parseTraf(r2, r6, r7, r8, r9);
    L7:
        r1 = r1 + 1;
        goto L3
    }

    private static void parseSaio(ParsableByteArray r5, TrackFragment r6) {
        r5.setPosition(8);
        int r1 = r5.readInt();
        if ((BoxParser.parseFullBoxFlags(r1) & 1) != 1) goto L5;
        r5.skipBytes(8);
    L5:
        int r0 = r5.readUnsignedIntToInt();
        if (r0 != 1) goto L14;
        int r02 = BoxParser.parseFullBoxVersion(r1);
        long r12 = r6.auxiliaryDataPosition;
        if (r02 != 0) goto L10;
        long r3 = r5.readUnsignedInt();
    L11:
        r6.auxiliaryDataPosition = r12 + r3;
        return;
    L10:
        r3 = r5.readUnsignedLongToLong();
        goto L11
    L14:
        throw ParserException.createForMalformedContainer("Unexpected saio entry count: " + r0, null);
    }

    private static void parseSaiz(TrackEncryptionBox r7, ParsableByteArray r8, TrackFragment r9) {
        int r72 = r7.perSampleIvSize;
        r8.setPosition(8);
        boolean r2 = true;
        if ((BoxParser.parseFullBoxFlags(r8.readInt()) & 1) != 1) goto L5;
        r8.skipBytes(8);
    L5:
        int r0 = r8.readUnsignedByte();
        int r1 = r8.readUnsignedIntToInt();
        if (r1 > r9.sampleCount) goto L25;
        if (r0 != 0) goto L16;
        boolean[] r02 = r9.sampleHasSubsampleEncryptionTable;
        int r4 = 0;
        int r5 = 0;
    L10:
        if (r4 >= r1) goto L20;
        int r6 = r8.readUnsignedByte();
        r5 = r5 + r6;
        if (r6 <= r72) goto L14;
        boolean r62 = true;
    L15:
        r02[r4] = r62;
        r4 = r4 + 1;
        goto L10
    L14:
        r62 = false;
    L20:
        Arrays.fill(r9.sampleHasSubsampleEncryptionTable, r1, r9.sampleCount, false);
        if (r5 <= 0) goto L29;
        r9.initEncryptionData(r5);
        return;
    L29:
        return;
    L16:
        if (r0 > r72) goto L19;
        r2 = false;
    L19:
        r5 = r0 * r1;
        Arrays.fill(r9.sampleHasSubsampleEncryptionTable, 0, r1, r2);
        goto L20
    L25:
        throw ParserException.createForMalformedContainer("Saiz sample count " + r1 + " is greater than fragment sample count" + r9.sampleCount, null);
    }

    private static void parseSampleGroups(Mp4Box.ContainerBox r17, String r18, TrackFragment r19) {
        byte[] r2 = null;
        ParsableByteArray r5 = null;
        ParsableByteArray r6 = null;
        int r4 = 0;
    L4:
        if (r4 >= r17.leafChildren.size()) goto L16;
        Mp4Box.LeafBox r7 = r17.leafChildren.get(r4);
        ParsableByteArray r8 = r7.data;
        int r72 = r7.type;
        if (r72 != 1935828848) goto L11;
        r8.setPosition(12);
        if (r8.readInt() != 1936025959) goto L15;
        r5 = r8;
    L15:
        r4 = r4 + 1;
        goto L4
    L11:
        if (r72 != 1936158820) goto L15;
        r8.setPosition(12);
        if (r8.readInt() != 1936025959) goto L15;
        r6 = r8;
        goto L15
    L16:
        if (r5 == null) goto L51;
        if (r6 == null) goto L58;
        r5.setPosition(8);
        int r42 = BoxParser.parseFullBoxVersion(r5.readInt());
        r5.skipBytes(4);
        if (r42 != 1) goto L23;
        r5.skipBytes(4);
    L23:
        if (r5.readInt() != 1) goto L50;
        r6.setPosition(8);
        int r0 = BoxParser.parseFullBoxVersion(r6.readInt());
        r6.skipBytes(4);
        if (r0 != 1) goto L32;
        if (r6.readUnsignedInt() != 0) goto L35;
        throw ParserException.createForUnsupportedContainerFeature("Variable length description in sgpd found (unsupported)");
    L35:
        if (r6.readUnsignedInt() != 1) goto L48;
        r6.skipBytes(1);
        int r02 = r6.readUnsignedByte();
        int r14 = (r02 & 240) >> 4;
        int r15 = r02 & 15;
        if (r6.readUnsignedByte() != 1) goto L39;
        boolean r10 = true;
    L40:
        if (r10 == false) goto L59;
        int r12 = r6.readUnsignedByte();
        byte[] r13 = new byte[16];
        r6.readBytes(r13, 0, 16);
        if (r12 != 0) goto L45;
        int r03 = r6.readUnsignedByte();
        r2 = new byte[r03];
        r6.readBytes(r2, 0, r03);
    L45:
        r19.definesEncryptionData = true;
        r19.trackEncryptionBox = new TrackEncryptionBox(r10, r18, r12, r13, r14, r15, r2);
        return;
    L59:
        return;
    L39:
        r10 = false;
        goto L40
    L48:
        throw ParserException.createForUnsupportedContainerFeature("Entry count in sgpd != 1 (unsupported).");
    L32:
        if (r0 < 2) goto L35;
        r6.skipBytes(4);
        goto L35
    L50:
        throw ParserException.createForUnsupportedContainerFeature("Entry count in sbgp != 1 (unsupported).");
    L58:
        return;
    }

    private static void parseSenc(ParsableByteArray r3, int r4, TrackFragment r5) {
        r3.setPosition(r4 + 8);
        int r42 = BoxParser.parseFullBoxFlags(r3.readInt());
        if ((r42 & 1) != 0) goto L19;
        if ((r42 & 2) == 0) goto L7;
        boolean r43 = true;
    L8:
        int r1 = r3.readUnsignedIntToInt();
        if (r1 != 0) goto L13;
        Arrays.fill(r5.sampleHasSubsampleEncryptionTable, 0, r5.sampleCount, false);
        return;
    L13:
        if (r1 != r5.sampleCount) goto L17;
        Arrays.fill(r5.sampleHasSubsampleEncryptionTable, 0, r1, r43);
        r5.initEncryptionData(r3.bytesLeft());
        r5.fillEncryptionData(r3);
        return;
    L17:
        throw ParserException.createForMalformedContainer("Senc sample count " + r1 + " is different from fragment sample count" + r5.sampleCount, null);
    L7:
        r43 = false;
        goto L8
    L19:
        throw ParserException.createForUnsupportedContainerFeature("Overriding TrackEncryptionBox parameters is unsupported.");
    }

    private static void parseSenc(ParsableByteArray r1, TrackFragment r2) {
        parseSenc(r1, 0, r2);
    }

    private static Pair<Long, ChunkIndex> parseSidx(ParsableByteArray r22, long r23) {
        r22.setPosition(8);
        int r1 = BoxParser.parseFullBoxVersion(r22.readInt());
        r22.skipBytes(4);
        long r7 = r22.readUnsignedInt();
        if (r1 != 0) goto L6;
        long r3 = r22.readUnsignedInt();
        long r5 = r22.readUnsignedInt();
    L5:
        long r52 = r23 + r5;
        long r11 = Util.scaleLargeTimestamp(r3, 1000000, r7);
        r22.skipBytes(2);
        int r12 = r22.readUnsignedShort();
        int[] r13 = new int[r12];
        long[] r14 = new long[r12];
        long[] r15 = new long[r12];
        long[] r53 = new long[r12];
        long r16 = r52;
        long r18 = r11;
        int r9 = 0;
    L8:
        if (r9 >= r12) goto L15;
        int r6 = r22.readInt();
        if ((Integer.MIN_VALUE & r6) != 0) goto L13;
        long r20 = r22.readUnsignedInt();
        r13[r9] = r6 & Integer.MAX_VALUE;
        r14[r9] = r16;
        r53[r9] = r18;
        r3 = r3 + r20;
        long[] r10 = r53;
        r18 = Util.scaleLargeTimestamp(r3, 1000000, r7);
        r15[r9] = r18 - r10[r9];
        r22.skipBytes(4);
        r16 = r16 + r13[r9];
        r9 = r9 + 1;
        r53 = r10;
        goto L8
    L13:
        throw ParserException.createForMalformedContainer("Unhandled indirect reference", null);
    L15:
        return Pair.create(Long.valueOf(r11), new ChunkIndex(r13, r14, r15, r53));
    L6:
        r3 = r22.readUnsignedLongToLong();
        r5 = r22.readUnsignedLongToLong();
        goto L5
    }

    private static long parseTfdt(ParsableByteArray r2) {
        r2.setPosition(8);
        if (BoxParser.parseFullBoxVersion(r2.readInt()) != 1) goto L7;
        return r2.readUnsignedLongToLong();
    L7:
        return r2.readUnsignedInt();
    }

    private static TrackBundle parseTfhd(ParsableByteArray r4, SparseArray<TrackBundle> r5, boolean r6) {
        r4.setPosition(8);
        int r0 = BoxParser.parseFullBoxFlags(r4.readInt());
        int r1 = r4.readInt();
        if (r6 == false) goto L6;
        TrackBundle r52 = r5.valueAt(0);
    L5:
        TrackBundle r53 = r52;
        if (r53 != null) goto L11;
        return null;
    L11:
        if ((r0 & 1) == 0) goto L13;
        long r12 = r4.readUnsignedLongToLong();
        TrackFragment r62 = r53.fragment;
        r62.dataPosition = r12;
        r62.auxiliaryDataPosition = r12;
    L13:
        DefaultSampleValues r63 = r53.defaultSampleValues;
        if ((r0 & 2) == 0) goto L16;
        int r13 = r4.readInt() - 1;
    L18:
        if ((r0 & 8) == 0) goto L20;
        int r2 = r4.readInt();
    L22:
        if ((r0 & 16) == 0) goto L24;
        int r3 = r4.readInt();
    L26:
        if ((r0 & 32) == 0) goto L28;
        int r42 = r4.readInt();
    L29:
        r53.fragment.header = new DefaultSampleValues(r13, r2, r3, r42);
        return r53;
    L28:
        r42 = r63.flags;
        goto L29
    L24:
        r3 = r63.size;
        goto L26
    L20:
        r2 = r63.duration;
        goto L22
    L16:
        r13 = r63.sampleDescriptionIndex;
        goto L18
    L6:
        r52 = r5.get(r1);
        goto L5
    }

    private static void parseTraf(Mp4Box.ContainerBox r6, SparseArray<TrackBundle> r7, boolean r8, int r9, byte[] r10) {
        TrackBundle r72 = parseTfhd(((Mp4Box.LeafBox) Assertions.checkNotNull(r6.getLeafBoxOfType(1952868452))).data, r7, r8);
        if (r72 == null) goto L29;
        TrackFragment r82 = r72.fragment;
        long r0 = r82.nextFragmentDecodeTime;
        boolean r2 = r82.nextFragmentDecodeTimeIncludesMoov;
        r72.resetFragmentInfo();
        TrackBundle.access$002(r72, true);
        Mp4Box.LeafBox r4 = r6.getLeafBoxOfType(1952867444);
        if (r4 != null) goto L8;
    L10:
        r82.nextFragmentDecodeTime = r0;
        r82.nextFragmentDecodeTimeIncludesMoov = r2;
    L11:
        parseTruns(r6, r72, r9);
        TrackEncryptionBox r73 = r72.moovSampleTable.track.getSampleDescriptionEncryptionBox(((DefaultSampleValues) Assertions.checkNotNull(r82.header)).sampleDescriptionIndex);
        Mp4Box.LeafBox r92 = r6.getLeafBoxOfType(1935763834);
        if (r92 == null) goto L14;
        parseSaiz((TrackEncryptionBox) Assertions.checkNotNull(r73), r92.data, r82);
    L14:
        Mp4Box.LeafBox r93 = r6.getLeafBoxOfType(1935763823);
        if (r93 == null) goto L17;
        parseSaio(r93.data, r82);
    L17:
        Mp4Box.LeafBox r94 = r6.getLeafBoxOfType(1936027235);
        if (r94 == null) goto L20;
        parseSenc(r94.data, r82);
    L20:
        if (r73 == null) goto L22;
        String r74 = r73.schemeType;
    L23:
        parseSampleGroups(r6, r74, r82);
        int r75 = r6.leafChildren.size();
        int r95 = 0;
    L24:
        if (r95 >= r75) goto L33;
        Mp4Box.LeafBox r02 = r6.leafChildren.get(r95);
        if (r02.type != 1970628964) goto L28;
        parseUuid(r02.data, r82, r10);
    L28:
        r95 = r95 + 1;
        goto L24
    L33:
        return;
    L22:
        r74 = null;
        goto L23
    L8:
        if ((r9 & 2) != 0) goto L10;
        r82.nextFragmentDecodeTime = parseTfdt(r4.data);
        r82.nextFragmentDecodeTimeIncludesMoov = true;
        goto L11
    }

    private static Pair<Integer, DefaultSampleValues> parseTrex(ParsableByteArray r5) {
        r5.setPosition(12);
        return Pair.create(Integer.valueOf(r5.readInt()), new DefaultSampleValues(r5.readInt() - 1, r5.readInt(), r5.readInt(), r5.readInt()));
    }

    private static int parseTrun(TrackBundle r28, int r29, int r30, ParsableByteArray r31, int r32) {
        r31.setPosition(8);
        int r1 = BoxParser.parseFullBoxFlags(r31.readInt());
        Track r3 = r28.moovSampleTable.track;
        TrackFragment r4 = r28.fragment;
        DefaultSampleValues r5 = (DefaultSampleValues) Util.castNonNull(r4.header);
        r4.trunLength[r29] = r31.readUnsignedIntToInt();
        long[] r6 = r4.trunDataPosition;
        long r7 = r4.dataPosition;
        r6[r29] = r7;
        if ((r1 & 1) == 0) goto L6;
        r6[r29] = r7 + r31.readInt();
    L6:
        if ((r1 & 4) == 0) goto L8;
        boolean r62 = true;
    L9:
        int r9 = r5.flags;
        if (r62 == false) goto L13;
        r9 = r31.readInt();
    L13:
        if ((r1 & 256) == 0) goto L15;
        boolean r10 = true;
    L17:
        if ((r1 & 512) == 0) goto L19;
        boolean r11 = true;
    L21:
        if ((r1 & 1024) == 0) goto L23;
        boolean r12 = true;
    L25:
        if ((r1 & 2048) == 0) goto L27;
        boolean r13 = true;
    L29:
        if (isEdtsListDurationForEntireMediaTimeline(r3) == false) goto L31;
        long r14 = ((long[]) Util.castNonNull(r3.editListMediaTimes))[0];
    L32:
        int[] r132 = r4.sampleSizeTable;
        long[] r72 = r4.samplePresentationTimesUs;
        boolean[] r8 = r4.sampleIsSyncFrameTable;
        boolean r17 = r13;
        if (r3.type == 2) goto L35;
    L37:
        boolean r15 = false;
    L38:
        int r2 = r32 + r4.trunLength[r29];
        boolean r24 = r62;
        long r63 = r3.timescale;
        long r64 = r4.nextFragmentDecodeTime;
        int r33 = r32;
    L39:
        if (r33 >= r2) goto L68;
        if (r10 == false) goto L42;
        int r18 = r31.readInt();
        boolean r302 = r15;
    L43:
        int r16 = checkNonNegative(r18);
        if (r11 == false) goto L46;
        int r182 = r31.readInt();
        int r292 = r2;
    L47:
        int r22 = checkNonNegative(r182);
        if (r12 == false) goto L50;
        int r322 = r22;
        int r23 = r31.readInt();
    L54:
        if (r17 == false) goto L57;
        int r26 = r23;
        int r25 = r31.readInt();
    L56:
        int r27 = r33;
        long r210 = Util.scaleLargeTimestamp((r25 + r64) - r14, 1000000, r63);
        r72[r27] = r210;
        if (r4.nextFragmentDecodeTimeIncludesMoov == true) goto L61;
        r72[r27] = r210 + r28.moovSampleTable.durationUs;
    L61:
        r132[r27] = r322;
        if (((r26 >> 16) & 1) != 0) goto L66;
        if (r302 == false) goto L65;
        if (r27 != 0) goto L66;
    L65:
        boolean r211 = true;
    L67:
        r8[r27] = r211;
        r64 = r64 + r16;
        r33 = r27 + 1;
        r2 = r292;
        r15 = r302;
    L66:
        r211 = false;
        goto L67
    L57:
        r26 = r23;
        r25 = 0;
        goto L56
    L50:
        if (r33 != 0) goto L53;
        if (r24 == false) goto L53;
        r322 = r22;
        r23 = r9;
    L53:
        r322 = r22;
        r23 = r5.flags;
        goto L54
    L46:
        r292 = r2;
        r182 = r5.size;
        goto L47
    L42:
        r302 = r15;
        r18 = r5.duration;
        goto L43
    L68:
        int r293 = r2;
        r4.nextFragmentDecodeTime = r64;
        return r293;
    L35:
        if ((r30 & 1) == 0) goto L37;
        r15 = true;
        goto L38
    L31:
        r14 = 0;
        goto L32
    L27:
        r13 = false;
        goto L29
    L23:
        r12 = false;
        goto L25
    L19:
        r11 = false;
        goto L21
    L15:
        r10 = false;
        goto L17
    L8:
        r62 = false;
        goto L9
    }

    private static void parseTruns(Mp4Box.ContainerBox r8, TrackBundle r9, int r10) {
        List<Mp4Box.LeafBox> r82 = r8.leafChildren;
        int r0 = r82.size();
        int r1 = 0;
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
    L4:
        if (r2 >= r0) goto L11;
        Mp4Box.LeafBox r6 = r82.get(r2);
        if (r6.type != 1953658222) goto L10;
        ParsableByteArray r5 = r6.data;
        r5.setPosition(12);
        int r52 = r5.readUnsignedIntToInt();
        if (r52 <= 0) goto L10;
        r4 = r4 + r52;
        r3 = r3 + 1;
    L10:
        r2 = r2 + 1;
        goto L4
    L11:
        r9.currentTrackRunIndex = 0;
        r9.currentSampleInTrackRun = 0;
        r9.currentSampleIndex = 0;
        r9.fragment.initTables(r3, r4);
        int r22 = 0;
        int r32 = 0;
    L12:
        if (r1 >= r0) goto L17;
        Mp4Box.LeafBox r42 = r82.get(r1);
        if (r42.type != 1953658222) goto L16;
        r32 = parseTrun(r9, r22, r10, r42.data, r32);
        r22 = r22 + 1;
    L16:
        r1 = r1 + 1;
        goto L12
    }

    private static void parseUuid(ParsableByteArray r2, TrackFragment r3, byte[] r4) {
        r2.setPosition(8);
        r2.readBytes(r4, 0, 16);
        if (Arrays.equals(r4, PIFF_SAMPLE_ENCRYPTION_BOX_EXTENDED_TYPE) == true) goto L5;
        return;
    L5:
        parseSenc(r2, 16, r3);
    }

    private void processAtomEnded(long r3) {
    L3:
        if (this.containerAtoms.isEmpty() == true) goto L7;
        if (this.containerAtoms.peek().endPosition != r3) goto L7;
        onContainerAtomRead(this.containerAtoms.pop());
    L7:
        enterReadingAtomHeaderState();
    }

    private boolean readAtomHeader(ExtractorInput r12) {
        if (this.atomHeaderBytesRead == 0) goto L5;
    L8:
        long r4 = this.atomSize;
        if (r4 != 1) goto L12;
        r12.readFully(this.atomHeader.getData(), 8, 8);
        this.atomHeaderBytesRead += 8;
        this.atomSize = this.atomHeader.readUnsignedLongToLong();
    L21:
        long r42 = this.atomSize;
        int r0 = this.atomHeaderBytesRead;
        if (r42 < r0) goto L75;
        if (this.seekPositionBeforeSidxProcessing != (-1)) goto L26;
        long r43 = r12.getPosition() - this.atomHeaderBytesRead;
        int r02 = this.atomType;
        if (r02 == 1836019558) goto L35;
        if (r02 == 1835295092) goto L35;
    L38:
        if (this.atomType != 1836019558) goto L42;
        int r03 = this.trackBundles.size();
        int r7 = 0;
    L40:
        if (r7 >= r03) goto L42;
        TrackFragment r8 = this.trackBundles.valueAt(r7).fragment;
        r8.atomPosition = r43;
        r8.auxiliaryDataPosition = r43;
        r8.dataPosition = r43;
        r7 = r7 + 1;
    L42:
        int r04 = this.atomType;
        if (r04 != 1835295092) goto L47;
        this.currentTrackBundle = null;
        this.endOfMdatPosition = r43 + this.atomSize;
        this.parserState = 2;
        return true;
    L47:
        if (shouldParseContainerAtom(r04) == false) goto L58;
        long r05 = r12.getPosition();
        long r44 = this.atomSize;
        long r06 = (r05 + r44) - 8;
        if (r44 != this.atomHeaderBytesRead) goto L51;
    L53:
        this.containerAtoms.push(new Mp4Box.ContainerBox(this.atomType, r06));
        if (this.atomSize != this.atomHeaderBytesRead) goto L56;
        processAtomEnded(r06);
    L71:
        return true;
    L56:
        enterReadingAtomHeaderState();
        goto L71
    L51:
        if (this.atomType != 1835365473) goto L53;
        maybeSkipRemainingMetaAtomHeaderBytes(r12);
        goto L53
    L58:
        if (shouldParseLeafAtom(this.atomType) == false) goto L69;
        if (this.atomHeaderBytesRead != 8) goto L67;
        if (this.atomSize > 2147483647L) goto L65;
        ParsableByteArray r122 = new ParsableByteArray((int) this.atomSize);
        System.arraycopy(this.atomHeader.getData(), 0, r122.getData(), 0, 8);
        this.atomData = r122;
        this.parserState = 1;
        goto L71
    L65:
        throw ParserException.createForUnsupportedContainerFeature("Leaf atom with length > 2147483647 (unsupported).");
    L67:
        throw ParserException.createForUnsupportedContainerFeature("Leaf atom defines extended atom size (unsupported).");
    L69:
        if (this.atomSize > 2147483647L) goto L73;
        this.atomData = null;
        this.parserState = 1;
        goto L71
    L73:
        throw ParserException.createForUnsupportedContainerFeature("Skipping atom with length > 2147483647 (unsupported).");
    L35:
        if (this.haveOutputSeekMap == true) goto L38;
        this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs, r43));
        this.haveOutputSeekMap = true;
        goto L38
    L26:
        if (this.atomType != 1936286840) goto L28;
        this.scratch.reset((int) r42);
        System.arraycopy(this.atomHeader.getData(), 0, this.scratch.getData(), 0, 8);
        r12.readFully(this.scratch.getData(), 8, (int) (this.atomSize - this.atomHeaderBytesRead));
        this.chunkIndexMerger.add((ChunkIndex) parseSidx(new Mp4Box.LeafBox(1936286840, this.scratch).data, r12.getPeekPosition()).second);
    L29:
        enterReadingAtomHeaderState();
        return true;
    L28:
        r12.skipFully((int) (r42 - r0), true);
        goto L29
    L75:
        throw ParserException.createForUnsupportedContainerFeature("Atom size less than header length (unsupported).");
    L12:
        if (r4 != 0) goto L21;
        long r45 = r12.getLength();
        if (r45 != (-1)) goto L19;
        if (this.containerAtoms.isEmpty() == true) goto L19;
        r45 = this.containerAtoms.peek().endPosition;
    L19:
        if (r45 == (-1)) goto L21;
        this.atomSize = (r45 - r12.getPosition()) + this.atomHeaderBytesRead;
        goto L21
    L5:
        if (r12.readFully(this.atomHeader.getData(), 0, 8, true) == true) goto L7;
        return false;
    L7:
        this.atomHeaderBytesRead = 8;
        this.atomHeader.setPosition(0);
        this.atomSize = this.atomHeader.readUnsignedInt();
        this.atomType = this.atomHeader.readInt();
        goto L8
    }

    private void readAtomPayload(ExtractorInput r5) {
        int r0 = (int) (this.atomSize - this.atomHeaderBytesRead);
        ParsableByteArray r1 = this.atomData;
        if (r1 == null) goto L5;
        r5.readFully(r1.getData(), 8, r0);
        onLeafAtomRead(new Mp4Box.LeafBox(this.atomType, r1), r5);
    L6:
        processAtomEnded(r5.getPosition());
        return;
    L5:
        r5.skipFully(r0);
        goto L6
    }

    private void readEncryptionData(ExtractorInput r10) {
        int r0 = this.trackBundles.size();
        long r2 = LongCompanionObject.MAX_VALUE;
        int r4 = 0;
        TrackBundle r5 = null;
    L3:
        if (r4 >= r0) goto L10;
        TrackFragment r6 = this.trackBundles.valueAt(r4).fragment;
        if (r6.sampleEncryptionDataNeedsFill == false) goto L9;
        long r62 = r6.auxiliaryDataPosition;
        if (r62 >= r2) goto L9;
        r5 = this.trackBundles.valueAt(r4);
        r2 = r62;
    L9:
        r4 = r4 + 1;
        goto L3
    L10:
        if (r5 != null) goto L13;
        this.parserState = 3;
        return;
    L13:
        int r9 = (int) (r2 - r10.getPosition());
        if (r9 < 0) goto L18;
        r10.skipFully(r9);
        r5.fragment.fillEncryptionData(r10);
        return;
    L18:
        throw ParserException.createForMalformedContainer("Offset to encryption data was negative.", null);
    }

    private boolean readSample(ExtractorInput r17) {
        TrackBundle r2 = this.currentTrackBundle;
        if (r2 != null) goto L17;
        r2 = getNextTrackBundle(this.trackBundles);
        if (r2 != null) goto L12;
        int r22 = (int) (this.endOfMdatPosition - r17.getPosition());
        if (r22 < 0) goto L11;
        r17.skipFully(r22);
        enterReadingAtomHeaderState();
        return false;
    L11:
        throw ParserException.createForMalformedContainer("Offset to end of mdat was negative.", null);
    L12:
        int r5 = (int) (r2.getCurrentSampleOffset() - r17.getPosition());
        if (r5 >= 0) goto L15;
        Log.w("FragmentedMp4Extractor", "Ignoring negative offset to sample data.");
        r5 = 0;
    L15:
        r17.skipFully(r5);
        this.currentTrackBundle = r2;
    L17:
        if (this.parserState != 3) goto L33;
        this.sampleSize = r2.getCurrentSampleSize();
        this.isSampleDependedOn = !canReadWithinGopSampleDependencies(r2.moovSampleTable.track.format);
        if (r2.currentSampleIndex >= r2.firstSampleToOutputIndex) goto L26;
        r17.skipFully(this.sampleSize);
        r2.skipSampleEncryptionData();
        if (r2.next() == true) goto L23;
        this.currentTrackBundle = null;
    L23:
        this.parserState = 3;
        return true;
    L26:
        if (r2.moovSampleTable.track.sampleTransformation != 1) goto L28;
        this.sampleSize -= 8;
        r17.skipFully(8);
    L28:
        boolean r52 = "audio/ac4".equals(r2.moovSampleTable.track.format.sampleMimeType);
        int r9 = this.sampleSize;
        if (r52 == false) goto L31;
        this.sampleBytesWritten = r2.outputSampleEncryptionData(r9, 7);
        Ac4Util.getAc4SampleHeader(this.sampleSize, this.scratch);
        r2.output.sampleData(this.scratch, 7);
        this.sampleBytesWritten += 7;
    L32:
        this.sampleSize += this.sampleBytesWritten;
        this.parserState = 4;
        this.sampleCurrentNalBytesRemaining = 0;
        goto L33
    L31:
        this.sampleBytesWritten = r2.outputSampleEncryptionData(r9, 0);
    L33:
        Track r53 = r2.moovSampleTable.track;
        TrackOutput r92 = r2.output;
        long r10 = r2.getCurrentSamplePresentationTimeUs();
        TimestampAdjuster r12 = this.timestampAdjuster;
        if (r12 == null) goto L37;
        r10 = r12.adjustSampleTimestamp(r10);
    L37:
        if (r53.nalUnitLengthFieldLength == 0) goto L85;
        byte[] r122 = this.nalPrefix.getData();
        r122[0] = 0;
        r122[1] = 0;
        r122[2] = 0;
        int r13 = 4 - r53.nalUnitLengthFieldLength;
    L40:
        if (this.sampleBytesWritten >= this.sampleSize) goto L88;
        int r14 = this.sampleCurrentNalBytesRemaining;
        if (r14 != 0) goto L71;
        if (this.ceaTrackOutputs.length <= 0) goto L46;
    L47:
        int r142 = NalUnitUtil.numberOfBytesInNalUnitHeader(r53.format);
        if ((r53.nalUnitLengthFieldLength + r142) <= (this.sampleSize - this.sampleBytesWritten)) goto L51;
    L50:
        r142 = 0;
    L51:
        r17.readFully(r122, r13, r53.nalUnitLengthFieldLength + r142);
        this.nalPrefix.setPosition(0);
        int r3 = this.nalPrefix.readInt();
        if (r3 < 0) goto L69;
        this.sampleCurrentNalBytesRemaining = r3 - r142;
        this.nalStartCode.setPosition(0);
        r92.sampleData(this.nalStartCode, 4);
        this.sampleBytesWritten += 4;
        this.sampleSize += r13;
        if (this.ceaTrackOutputs.length <= 0) goto L59;
        if (r142 <= 0) goto L59;
        if (NalUnitUtil.isNalUnitSei(r53.format, r122[4]) == false) goto L59;
        boolean r32 = true;
    L60:
        this.processSeiNalUnitPayload = r32;
        r92.sampleData(this.nalPrefix, r142);
        this.sampleBytesWritten += r142;
        if (r142 <= 0) goto L40;
        if (this.isSampleDependedOn == true) goto L40;
        if (NalUnitUtil.isDependedOn(r122, 4, r142, r53.format) == false) goto L40;
        this.isSampleDependedOn = true;
    L59:
        r32 = false;
        goto L60
    L69:
        throw ParserException.createForMalformedContainer("Invalid NAL length", null);
    L46:
        if (this.isSampleDependedOn == true) goto L50;
    L71:
        if (this.processSeiNalUnitPayload == false) goto L83;
        this.nalUnitWithoutHeaderBuffer.reset(r14);
        r17.readFully(this.nalUnitWithoutHeaderBuffer.getData(), 0, this.sampleCurrentNalBytesRemaining);
        r92.sampleData(this.nalUnitWithoutHeaderBuffer, this.sampleCurrentNalBytesRemaining);
        int r33 = this.sampleCurrentNalBytesRemaining;
        int r6 = NalUnitUtil.unescapeStream(this.nalUnitWithoutHeaderBuffer.getData(), this.nalUnitWithoutHeaderBuffer.limit());
        this.nalUnitWithoutHeaderBuffer.setPosition(0);
        this.nalUnitWithoutHeaderBuffer.setLimit(r6);
        int r62 = r53.format.maxNumReorderSamples;
        ReorderingBufferQueue r143 = this.reorderingBufferQueue;
        if (r62 == (-1)) goto L75;
        int r63 = r143.getMaxSize();
        int r144 = r53.format.maxNumReorderSamples;
        if (r63 == r144) goto L80;
        this.reorderingBufferQueue.setMaxSize(r144);
    L80:
        this.reorderingBufferQueue.add(r10, this.nalUnitWithoutHeaderBuffer);
        if ((r2.getCurrentSampleFlags() & 4) == 0) goto L84;
        this.reorderingBufferQueue.flush();
    L84:
        this.sampleBytesWritten += r33;
        this.sampleCurrentNalBytesRemaining -= r33;
        goto L40
    L75:
        if (r143.getMaxSize() == 0) goto L80;
        this.reorderingBufferQueue.setMaxSize(0);
        goto L80
    L83:
        r33 = r92.sampleData(r17, r14, false);
    L88:
        int r1 = r2.getCurrentSampleFlags();
        if (this.isSampleDependedOn == true) goto L91;
        r1 = r1 | 67108864;
    L91:
        int r123 = r1;
        TrackEncryptionBox r15 = r2.getEncryptionBoxIfEncrypted();
        if (r15 == null) goto L94;
        TrackOutput.CryptoData r152 = r15.cryptoData;
    L95:
        r92.sampleMetadata(r10, r123, this.sampleSize, 0, r152);
        outputPendingMetadataSamples(r10);
        if (r2.next() == true) goto L98;
        this.currentTrackBundle = null;
    L98:
        this.parserState = 3;
        return true;
    L94:
        r152 = null;
    L85:
        int r34 = this.sampleBytesWritten;
        int r54 = this.sampleSize;
        if (r34 >= r54) goto L88;
        this.sampleBytesWritten += r92.sampleData(r17, r54 - r34, false);
        goto L85
    }

    private static boolean shouldParseContainerAtom(int r1) {
        if (r1 != 1836019574) goto L5;
        return true;
    L5:
        if (r1 != 1953653099) goto L7;
        return true;
    L7:
        if (r1 != 1835297121) goto L9;
        return true;
    L9:
        if (r1 != 1835626086) goto L11;
        return true;
    L11:
        if (r1 != 1937007212) goto L13;
        return true;
    L13:
        if (r1 != 1836019558) goto L15;
        return true;
    L15:
        if (r1 != 1953653094) goto L17;
        return true;
    L17:
        if (r1 != 1836475768) goto L19;
        return true;
    L19:
        if (r1 != 1701082227) goto L21;
        return true;
    L21:
        if (r1 == 1835365473) goto L35;
        return false;
    L35:
        return true;
    }

    private static boolean shouldParseLeafAtom(int r1) {
        if (r1 != 1751411826) goto L5;
        return true;
    L5:
        if (r1 != 1835296868) goto L7;
        return true;
    L7:
        if (r1 != 1836476516) goto L9;
        return true;
    L9:
        if (r1 != 1936286840) goto L11;
        return true;
    L11:
        if (r1 != 1937011556) goto L13;
        return true;
    L13:
        if (r1 != 1937011827) goto L15;
        return true;
    L15:
        if (r1 != 1668576371) goto L17;
        return true;
    L17:
        if (r1 != 1937011555) goto L19;
        return true;
    L19:
        if (r1 != 1937011578) goto L21;
        return true;
    L21:
        if (r1 != 1937013298) goto L23;
        return true;
    L23:
        if (r1 != 1937007471) goto L25;
        return true;
    L25:
        if (r1 != 1668232756) goto L27;
        return true;
    L27:
        if (r1 != 1937011571) goto L29;
        return true;
    L29:
        if (r1 != 1952867444) goto L31;
        return true;
    L31:
        if (r1 != 1952868452) goto L33;
        return true;
    L33:
        if (r1 != 1953196132) goto L35;
        return true;
    L35:
        if (r1 != 1953654136) goto L37;
        return true;
    L37:
        if (r1 != 1953658222) goto L39;
        return true;
    L39:
        if (r1 != 1886614376) goto L41;
        return true;
    L41:
        if (r1 != 1935763834) goto L43;
        return true;
    L43:
        if (r1 != 1935763823) goto L45;
        return true;
    L45:
        if (r1 != 1936027235) goto L47;
        return true;
    L47:
        if (r1 != 1970628964) goto L49;
        return true;
    L49:
        if (r1 != 1935828848) goto L51;
        return true;
    L51:
        if (r1 != 1936158820) goto L53;
        return true;
    L53:
        if (r1 != 1701606260) goto L55;
        return true;
    L55:
        if (r1 != 1835362404) goto L57;
        return true;
    L57:
        if (r1 != 1701671783) goto L59;
        return true;
    L59:
        if (r1 != 1969517665) goto L61;
        return true;
    L61:
        if (r1 != 1801812339) goto L63;
        return true;
    L63:
        if (r1 == 1768715124) goto L98;
        return false;
    L98:
        return true;
    }

    @Override // androidx.media3.extractor.Extractor
    public ImmutableList<SniffFailure> getSniffFailureDetails() {
        return this.lastSniffFailures;
    }

    @Override // androidx.media3.extractor.Extractor
    public /* bridge */ /* synthetic */ List getSniffFailureDetails() {
        return getSniffFailureDetails();
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput r14) {
        if ((this.flags & 32) != 0) goto L5;
        r14 = new SubtitleTranscodingExtractorOutput(r14, this.subtitleParserFactory);
    L5:
        this.extractorOutput = r14;
        enterReadingAtomHeaderState();
        initExtraTracks();
        Track r142 = this.sideloadedTrack;
        if (r142 == null) goto L9;
        Format.Builder r143 = r142.format.buildUpon();
        r143.setContainerMimeType(MimeTypeResolver.getContainerMimeType(this.sideloadedTrack.format));
        TrackBundle r0 = new TrackBundle(this.extractorOutput.track(0, this.sideloadedTrack.type), new TrackSampleTable(this.sideloadedTrack, new long[0], new int[0], 0, new long[0], new int[0], 0), new DefaultSampleValues(0, 0, 0, 0), r143.build());
        this.trackBundles.put(0, r0);
        this.extractorOutput.endTracks();
        return;
    }

    public Track modifyTrack(Track r1) {
        return r1;
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput r7, PositionHolder r8) {
    L2:
        int r0 = this.parserState;
        if (r0 != 0) goto L4;
        if (readAtomHeader(r7) == true) goto L2;
        long r2 = this.seekPositionBeforeSidxProcessing;
        if (r2 == (-1)) goto L19;
        r8.position = r2;
        this.seekPositionBeforeSidxProcessing = -1;
        this.extractorOutput.seekMap(this.chunkIndexMerger.merge());
        this.haveOutputSeekMapFromMultipleSidx = true;
        return 1;
    L19:
        this.reorderingBufferQueue.flush();
        return -1;
    L4:
        if (r0 != 1) goto L6;
        readAtomPayload(r7);
        goto L2
    L6:
        if (r0 != 2) goto L8;
        readEncryptionData(r7);
        goto L2
    L8:
        if (readSample(r7) == false) goto L2;
        return 0;
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long r3, long r5) {
        int r32 = this.trackBundles.size();
        int r0 = 0;
    L3:
        if (r0 >= r32) goto L5;
        this.trackBundles.valueAt(r0).resetFragmentInfo();
        r0 = r0 + 1;
        goto L3
    L5:
        this.pendingMetadataSampleInfos.clear();
        this.pendingMetadataSampleBytes = 0;
        this.reorderingBufferQueue.clear();
        this.pendingSeekTimeUs = r5;
        this.containerAtoms.clear();
        enterReadingAtomHeaderState();
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput r2) {
        SniffFailure r22 = Sniffer.sniffFragmented(r2);
        if (r22 == null) goto L5;
        ImmutableList<SniffFailure> r0 = ImmutableList.of(r22);
    L6:
        this.lastSniffFailures = r0;
        if (r22 != null) goto L10;
        return true;
    L10:
        return false;
    L5:
        r0 = ImmutableList.of();
        goto L6
    }
}
