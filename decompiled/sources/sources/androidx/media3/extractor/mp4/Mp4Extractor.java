package androidx.media3.extractor.mp4;

import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.container.Mp4Box;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.Ac4Util;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.GaplessInfoHolder;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;
import androidx.media3.extractor.SniffFailure;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.TrueHdSampleRechunker;
import androidx.media3.extractor.metadata.mp4.MotionPhotoMetadata;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.SubtitleTranscodingExtractorOutput;
import com.google.android.gms.cast.MediaStatus;
import com.google.common.collect.ImmutableList;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.MediaController;

/* loaded from: classes4.dex */
public final class Mp4Extractor implements Extractor, SeekMap {

    @Deprecated
    public static final ExtractorsFactory FACTORY = null;
    private long[][] accumulatedSampleSizes;
    private ParsableByteArray atomData;
    private final ParsableByteArray atomHeader;
    private int atomHeaderBytesRead;
    private long atomSize;
    private int atomType;
    private long axteAtomOffset;
    private final ArrayDeque<Mp4Box.ContainerBox> containerAtoms;
    private long durationUs;
    private ExtractorOutput extractorOutput;
    private int fileType;
    private int firstVideoTrackIndex;
    private final int flags;
    private boolean isSampleDependedOn;
    private ImmutableList<SniffFailure> lastSniffFailures;
    private MotionPhotoMetadata motionPhotoMetadata;
    private final ParsableByteArray nalPrefix;
    private final ParsableByteArray nalStartCode;
    private int parserState;
    private boolean readingAuxiliaryTracks;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private long sampleOffsetForAuxiliaryTracks;
    private int sampleTrackIndex;
    private final ParsableByteArray scratch;
    private boolean seekToAxteAtom;
    private boolean seenFtypAtom;
    private final SefReader sefReader;
    private final List<Metadata.Entry> slowMotionMetadataEntries;
    private final SubtitleParser.Factory subtitleParserFactory;
    private Mp4Track[] tracks;

    public static final class Mp4Track {
        public int sampleIndex;
        public final TrackSampleTable sampleTable;
        public final Track track;
        public final TrackOutput trackOutput;
        public final TrueHdSampleRechunker trueHdSampleRechunker;

        public Mp4Track(Track r1, TrackSampleTable r2, TrackOutput r3) {
            this.track = r1;
            this.sampleTable = r2;
            this.trackOutput = r3;
            if ("audio/true-hd".equals(r1.format.sampleMimeType) == false) goto L5;
            TrueHdSampleRechunker r12 = new TrueHdSampleRechunker();
        L6:
            this.trueHdSampleRechunker = r12;
            return;
        L5:
            r12 = null;
            goto L6
        }
    }

    public static /* synthetic */ Track $r8$lambda$4CAN4eHl8XkrIzIa5pdC06sGtK4(Track r0) {
        return r0;
    }

    public static /* synthetic */ Extractor[] $r8$lambda$_HDNZs8ow3xj06Rh7_zCMxG71jc() {
        return new Extractor[]{new Mp4Extractor(SubtitleParser.Factory.UNSUPPORTED, 16)};
    }

    static {
        FACTORY = new Mp4Extractor$$ExternalSyntheticLambda1();
    }

    public Mp4Extractor(SubtitleParser.Factory r2, int r3) {
        this.subtitleParserFactory = r2;
        this.flags = r3;
        this.lastSniffFailures = ImmutableList.of();
        if ((r3 & 4) == 0) goto L5;
        int r22 = 3;
    L6:
        this.parserState = r22;
        this.sefReader = new SefReader();
        this.slowMotionMetadataEntries = new ArrayList();
        this.atomHeader = new ParsableByteArray(16);
        this.containerAtoms = new ArrayDeque();
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalPrefix = new ParsableByteArray(6);
        this.scratch = new ParsableByteArray();
        this.sampleTrackIndex = -1;
        this.extractorOutput = ExtractorOutput.PLACEHOLDER;
        this.tracks = new Mp4Track[0];
        return;
    L5:
        r22 = 0;
        goto L6
    }

    private static int brandToFileType(int r1) {
        if (r1 != 1751476579) goto L5;
        return 2;
    L5:
        if (r1 == 1903435808) goto L8;
        return 0;
    L8:
        return 1;
    }

    private static long[][] calculateAccumulatedSampleSizes(Mp4Track[] r15) {
        long[][] r0 = new long[r15.length][];
        int[] r1 = new int[r15.length];
        long[] r2 = new long[r15.length];
        boolean[] r3 = new boolean[r15.length];
        int r5 = 0;
    L4:
        if (r5 >= r15.length) goto L6;
        r0[r5] = new long[r15[r5].sampleTable.sampleCount];
        r2[r5] = r15[r5].sampleTable.timestampsUs[0];
        r5 = r5 + 1;
        goto L4
    L6:
        long r52 = 0;
        int r7 = 0;
    L8:
        if (r7 >= r15.length) goto L22;
        long r8 = LongCompanionObject.MAX_VALUE;
        int r10 = -1;
        int r11 = 0;
    L11:
        if (r11 >= r15.length) goto L18;
        if (r3[r11] == true) goto L17;
        long r12 = r2[r11];
        if (r12 > r8) goto L17;
        r10 = r11;
        r8 = r12;
    L17:
        r11 = r11 + 1;
        goto L11
    L18:
        int r82 = r1[r10];
        long[] r9 = r0[r10];
        r9[r82] = r52;
        TrackSampleTable r112 = r15[r10].sampleTable;
        r52 = r52 + r112.sizes[r82];
        int r83 = r82 + 1;
        r1[r10] = r83;
        if (r83 < r9.length) goto L20;
        r3[r10] = true;
        r7 = r7 + 1;
        goto L8
    L20:
        r2[r10] = r112.timestampsUs[r83];
        goto L8
    L22:
        return r0;
    }

    private boolean canReadWithinGopSampleDependencies(Format r4) {
        if (Objects.equals(r4.sampleMimeType, MediaController.VIDEO_MIME_TYPE) == false) goto L9;
        if ((this.flags & 32) == 0) goto L7;
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

    public static int codecsToParseWithinGopSampleDependenciesAsFlags(int r1) {
        if ((r1 & 1) == 0) goto L5;
        int r0 = 32;
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

    private List<Integer> getAuxiliaryTrackTypesForAuxiliaryTracks(Metadata r6) {
        List<Integer> r5 = ((MdtaMetadataEntry) Assertions.checkStateNotNull(MetadataUtil.findMdtaMetadataEntryWithKey(r6, "auxiliary.tracks.map"))).getAuxiliaryTrackTypesFromMap();
        ArrayList r62 = new ArrayList(r5.size());
        int r1 = 0;
    L4:
        if (r1 >= r5.size()) goto L16;
        int r2 = r5.get(r1).intValue();
        int r3 = 1;
        if (r2 == 0) goto L15;
        if (r2 == 1) goto L14;
        r3 = 3;
        if (r2 == 2) goto L15;
        if (r2 == 3) goto L13;
        r3 = 0;
        goto L15
    L13:
        r3 = 4;
        goto L15
    L14:
        r3 = 2;
    L15:
        r62.add(Integer.valueOf(r3));
        r1 = r1 + 1;
        goto L4
    L16:
        return r62;
    }

    private static int getSynchronizationSampleIndex(TrackSampleTable r2, long r3) {
        int r0 = r2.getIndexOfEarlierOrEqualSynchronizationSample(r3);
        if (r0 == (-1)) goto L5;
        return r0;
    L5:
        return r2.getIndexOfLaterOrEqualSynchronizationSample(r3);
    }

    private int getTrackIndexOfNextReadSample(long r23) {
        int r4 = -1;
        int r6 = -1;
        int r7 = 0;
        long r8 = LongCompanionObject.MAX_VALUE;
        boolean r10 = true;
        long r11 = LongCompanionObject.MAX_VALUE;
        boolean r13 = true;
        long r14 = LongCompanionObject.MAX_VALUE;
    L3:
        Mp4Track[] r1 = this.tracks;
        if (r7 >= r1.length) goto L26;
        Mp4Track r12 = r1[r7];
        int r2 = r12.sampleIndex;
        TrackSampleTable r15 = r12.sampleTable;
        if (r2 == r15.sampleCount) goto L24;
        long r18 = r15.offsets[r2];
        long r22 = ((long[][]) Util.castNonNull(this.accumulatedSampleSizes))[r7][r2];
        long r182 = r18 - r23;
        if (r182 >= 0) goto L11;
    L14:
        boolean r16 = true;
    L15:
        if (r16 == true) goto L17;
        if (r13 == false) goto L17;
    L20:
        r13 = r16;
        r11 = r22;
        r6 = r7;
        r14 = r182;
    L22:
        if (r22 >= r8) goto L24;
        r10 = r16;
        r8 = r22;
        r4 = r7;
    L17:
        if (r16 != r13) goto L22;
        if (r182 >= r14) goto L22;
    L11:
        if (r182 >= MediaStatus.COMMAND_STREAM_TRANSFER) goto L14;
        r16 = false;
    L24:
        r7 = r7 + 1;
        goto L3
    L26:
        if (r8 == LongCompanionObject.MAX_VALUE) goto L32;
        if (r10 == false) goto L32;
        if (r11 < (r8 + 524288)) goto L32;
        return r4;
    L32:
        return r6;
    }

    private static long maybeAdjustSeekOffset(TrackSampleTable r0, long r1, long r3) {
        int r12 = getSynchronizationSampleIndex(r0, r1);
        if (r12 != (-1)) goto L6;
        return r3;
    L6:
        return Math.min(r0.offsets[r12], r3);
    }

    private void maybeSetDefaultSampleOffsetForAuxiliaryTracks(Metadata r5) {
        MdtaMetadataEntry r52 = MetadataUtil.findMdtaMetadataEntryWithKey(r5, "auxiliary.tracks.interleaved");
        if (r52 != null) goto L5;
        return;
    L5:
        if (r52.value[0] != 0) goto L9;
        this.sampleOffsetForAuxiliaryTracks = this.axteAtomOffset + 16;
        return;
    }

    private void maybeSkipRemainingMetaAtomHeaderBytes(ExtractorInput r4) {
        this.scratch.reset(8);
        r4.peekFully(this.scratch.getData(), 0, 8);
        BoxParser.maybeSkipRemainingMetaBoxHeaderBytes(this.scratch);
        r4.skipFully(this.scratch.getPosition());
        r4.resetPeekPosition();
    }

    private void processAtomEnded(long r5) {
    L3:
        if (this.containerAtoms.isEmpty() == true) goto L15;
        if (this.containerAtoms.peek().endPosition != r5) goto L15;
        Mp4Box.ContainerBox r0 = this.containerAtoms.pop();
        if (r0.type == 1836019574) goto L8;
        if (this.containerAtoms.isEmpty() == true) goto L3;
        this.containerAtoms.peek().add(r0);
        goto L3
    L8:
        processMoovAtom(r0);
        this.containerAtoms.clear();
        if (this.seekToAxteAtom == true) goto L3;
        this.parserState = 2;
    L15:
        if (this.parserState == 2) goto L29;
        enterReadingAtomHeaderState();
        return;
    }

    private void processEndOfStreamReadingAtomHeader() {
        if (this.fileType == 2) goto L5;
        return;
    L5:
        if ((this.flags & 2) == 0) goto L13;
        TrackOutput r0 = this.extractorOutput.track(0, 4);
        MotionPhotoMetadata r1 = this.motionPhotoMetadata;
        if (r1 != null) goto L9;
        Metadata r12 = null;
    L10:
        r0.format(new Format.Builder().setMetadata(r12).build());
        this.extractorOutput.endTracks();
        this.extractorOutput.seekMap(new SeekMap.Unseekable(-9223372036854775807L));
        return;
    L9:
        r12 = new Metadata(new Metadata.Entry[]{r1});
        goto L10
    }

    private static int processFtypAtom(ParsableByteArray r1) {
        r1.setPosition(8);
        int r0 = brandToFileType(r1.readInt());
        if (r0 == 0) goto L5;
        return r0;
    L5:
        r1.skipBytes(4);
    L7:
        if (r1.bytesLeft() <= 0) goto L11;
        int r02 = brandToFileType(r1.readInt());
        if (r02 == 0) goto L7;
        return r02;
    L11:
        return 0;
    }

    private void processMoovAtom(Mp4Box.ContainerBox r25) {
        Mp4Box.ContainerBox r2 = r25.getContainerBoxOfType(1835365473);
        List<Integer> r3 = new ArrayList();
        if (r2 == null) goto L12;
        Metadata r22 = BoxParser.parseMdtaFromMeta(r2);
        if (this.readingAuxiliaryTracks == false) goto L9;
        Assertions.checkStateNotNull(r22);
        maybeSetDefaultSampleOffsetForAuxiliaryTracks(r22);
        r3 = getAuxiliaryTrackTypesForAuxiliaryTracks(r22);
    L7:
        Metadata r11 = r22;
        List<Integer> r12 = r3;
    L13:
        ArrayList r13 = new ArrayList();
        if (this.fileType != 1) goto L16;
        boolean r7 = true;
    L17:
        GaplessInfoHolder r23 = new GaplessInfoHolder();
        Mp4Box.LeafBox r32 = r25.getLeafBoxOfType(1969517665);
        if (r32 == null) goto L20;
        Metadata r33 = BoxParser.parseUdta(r32);
        r23.setFromMetadata(r33);
        Metadata r15 = r33;
    L21:
        Metadata r34 = new Metadata(new Metadata.Entry[]{BoxParser.parseMvhd(((Mp4Box.LeafBox) Assertions.checkNotNull(r25.getLeafBoxOfType(1836476516))).data)});
        if ((this.flags & 1) == 0) goto L24;
        boolean r6 = true;
    L25:
        List<TrackSampleTable> r1 = BoxParser.parseTraks(r25, r23, -9223372036854775807L, null, r6, r7, new Mp4Extractor$$ExternalSyntheticLambda0());
        if (this.readingAuxiliaryTracks == true) goto L28;
    L32:
        String r35 = MimeTypeResolver.getContainerMimeType(r1);
        int r72 = 0;
        int r10 = 0;
        long r4 = -9223372036854775807L;
        int r8 = -1;
    L34:
        if (r72 >= r1.size()) goto L69;
        TrackSampleTable r14 = r1.get(r72);
        if (r14.sampleCount != 0) goto L38;
        List<TrackSampleTable> r19 = r1;
        GaplessInfoHolder r232 = r23;
        String r24 = r35;
        Metadata r36 = r11;
        ArrayList r62 = r13;
    L68:
        r72 = r72 + 1;
        r11 = r36;
        r13 = r62;
        r1 = r19;
        r35 = r24;
        r23 = r232;
        goto L34
    L38:
        Track r63 = r14.track;
        r19 = r1;
        ArrayList r20 = r13;
        int r21 = r10 + 1;
        String r222 = r35;
        Mp4Track r16 = new Mp4Track(r63, r14, this.extractorOutput.track(r10, r63.type));
        r36 = r11;
        long r102 = r63.durationUs;
        if (r102 != (-9223372036854775807L)) goto L42;
        r102 = r14.durationUs;
    L42:
        r16.trackOutput.durationUs(r102);
        r4 = Math.max(r4, r102);
        boolean r103 = "audio/true-hd".equals(r63.format.sampleMimeType);
        int r112 = r14.maximumSize;
        if (r103 == false) goto L45;
        int r113 = r112 * 16;
    L46:
        Format.Builder r104 = r63.format.buildUpon();
        r104.setMaxInputSize(r113);
        if (r63.type != 2) goto L59;
        int r114 = r63.format.roleFlags;
        if ((this.flags & 8) == 0) goto L56;
        if (r8 != (-1)) goto L53;
        int r142 = 1;
    L54:
        r114 = r114 | r142;
        goto L56
    L53:
        r142 = 2;
    L56:
        if (this.readingAuxiliaryTracks == false) goto L58;
        r114 = r114 | 32768;
        r104.setAuxiliaryTrackType(r12.get(r72).intValue());
    L58:
        r104.setRoleFlags(r114);
    L59:
        MetadataUtil.setFormatGaplessInfo(r63.type, r23, r104);
        int r115 = r63.type;
        Metadata r143 = r63.format.metadata;
        if (this.slowMotionMetadataEntries.isEmpty() == false) goto L62;
        r232 = r23;
        Metadata r132 = null;
    L63:
        MetadataUtil.setFormatMetadata(r115, r36, r104, r143, new Metadata[]{r132, r15, r34});
        r24 = r222;
        r104.setContainerMimeType(r24);
        r16.trackOutput.format(r104.build());
        if (r63.type != 2) goto L67;
        if (r8 != (-1)) goto L67;
        r8 = r20.size();
    L67:
        r62 = r20;
        r62.add(r16);
        r10 = r21;
        goto L68
    L62:
        r232 = r23;
        r132 = new Metadata(this.slowMotionMetadataEntries);
        goto L63
    L45:
        r113 = r112 + 30;
        goto L46
    L69:
        this.firstVideoTrackIndex = r8;
        this.durationUs = r4;
        Mp4Track[] r17 = (Mp4Track[]) r13.toArray(new Mp4Track[0]);
        this.tracks = r17;
        this.accumulatedSampleSizes = calculateAccumulatedSampleSizes(r17);
        this.extractorOutput.endTracks();
        this.extractorOutput.seekMap(this);
        return;
    L28:
        if (r12.size() != r1.size()) goto L30;
        boolean r37 = true;
    L31:
        Assertions.checkState(r37, String.format(Locale.US, "The number of auxiliary track types from metadata (%d) is not same as the number of auxiliary tracks (%d)", new Object[]{Integer.valueOf(r12.size()), Integer.valueOf(r1.size())}));
        goto L32
    L30:
        r37 = false;
        goto L31
    L24:
        r6 = false;
        goto L25
    L20:
        r15 = null;
        goto L21
    L16:
        r7 = false;
        goto L17
    L9:
        if (shouldSeekToAxteAtom(r22) == false) goto L7;
        this.seekToAxteAtom = true;
        return;
    L12:
        r12 = r3;
        r11 = null;
        goto L13
    }

    private void processUnparsedAtom(long r14) {
        if (this.atomType != 1836086884) goto L6;
        int r0 = this.atomHeaderBytesRead;
        this.motionPhotoMetadata = new MotionPhotoMetadata(0, r14, -9223372036854775807L, r14 + r0, this.atomSize - r0);
        return;
    }

    private boolean readAtomHeader(ExtractorInput r9) {
        if (this.atomHeaderBytesRead == 0) goto L5;
    L9:
        long r4 = this.atomSize;
        if (r4 != 1) goto L13;
        r9.readFully(this.atomHeader.getData(), 8, 8);
        this.atomHeaderBytesRead += 8;
        this.atomSize = this.atomHeader.readUnsignedLongToLong();
    L23:
        if (this.atomSize < this.atomHeaderBytesRead) goto L49;
        if (shouldParseContainerAtom(this.atomType) == false) goto L36;
        long r2 = r9.getPosition();
        long r42 = this.atomSize;
        int r0 = this.atomHeaderBytesRead;
        long r22 = (r2 + r42) - r0;
        if (r42 != r0) goto L29;
    L31:
        this.containerAtoms.push(new Mp4Box.ContainerBox(this.atomType, r22));
        if (this.atomSize != this.atomHeaderBytesRead) goto L34;
        processAtomEnded(r22);
    L47:
        return true;
    L34:
        enterReadingAtomHeaderState();
        goto L47
    L29:
        if (this.atomType != 1835365473) goto L31;
        maybeSkipRemainingMetaAtomHeaderBytes(r9);
        goto L31
    L36:
        if (shouldParseLeafAtom(this.atomType) == true) goto L38;
        processUnparsedAtom(r9.getPosition() - this.atomHeaderBytesRead);
        this.atomData = null;
        this.parserState = 1;
        goto L47
    L38:
        if (this.atomHeaderBytesRead != 8) goto L40;
        boolean r92 = true;
    L41:
        Assertions.checkState(r92);
        if (this.atomSize > 2147483647L) goto L44;
        boolean r93 = true;
    L45:
        Assertions.checkState(r93);
        ParsableByteArray r94 = new ParsableByteArray((int) this.atomSize);
        System.arraycopy(this.atomHeader.getData(), 0, r94.getData(), 0, 8);
        this.atomData = r94;
        this.parserState = 1;
        goto L47
    L44:
        r93 = false;
        goto L45
    L40:
        r92 = false;
        goto L41
    L49:
        throw ParserException.createForUnsupportedContainerFeature("Atom size less than header length (unsupported).");
    L13:
        if (r4 != 0) goto L23;
        long r43 = r9.getLength();
        if (r43 != (-1)) goto L20;
        Mp4Box.ContainerBox r02 = this.containerAtoms.peek();
        if (r02 == null) goto L20;
        r43 = r02.endPosition;
    L20:
        if (r43 == (-1)) goto L23;
        this.atomSize = (r43 - r9.getPosition()) + this.atomHeaderBytesRead;
        goto L23
    L5:
        if (r9.readFully(this.atomHeader.getData(), 0, 8, true) == true) goto L8;
        processEndOfStreamReadingAtomHeader();
        return false;
    L8:
        this.atomHeaderBytesRead = 8;
        this.atomHeader.setPosition(0);
        this.atomSize = this.atomHeader.readUnsignedInt();
        this.atomType = this.atomHeader.readInt();
        goto L9
    }

    private boolean readAtomPayload(ExtractorInput r10, PositionHolder r11) {
        long r0 = this.atomSize - this.atomHeaderBytesRead;
        long r2 = r10.getPosition() + r0;
        ParsableByteArray r4 = this.atomData;
        if (r4 == null) goto L11;
        r10.readFully(r4.getData(), this.atomHeaderBytesRead, (int) r0);
        if (this.atomType != 1718909296) goto L8;
        this.seenFtypAtom = true;
        this.fileType = processFtypAtom(r4);
    L18:
        boolean r102 = false;
    L20:
        processAtomEnded(r2);
        if (this.seekToAxteAtom == false) goto L23;
        this.readingAuxiliaryTracks = true;
        r11.position = this.axteAtomOffset;
        this.seekToAxteAtom = false;
        r102 = true;
    L23:
        if (r102 == true) goto L25;
    L27:
        return false;
    L25:
        if (this.parserState == 2) goto L27;
        return true;
    L8:
        if (this.containerAtoms.isEmpty() == true) goto L18;
        this.containerAtoms.peek().add(new Mp4Box.LeafBox(this.atomType, r4));
        goto L18
    L11:
        if (this.seenFtypAtom == true) goto L16;
        if (this.atomType != 1835295092) goto L16;
        this.fileType = 1;
    L16:
        if (r0 >= MediaStatus.COMMAND_STREAM_TRANSFER) goto L19;
        r10.skipFully((int) r0);
        goto L18
    L19:
        r11.position = r10.getPosition() + r0;
        r102 = true;
        goto L20
    }

    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r1v9 */
    private int readSample(ExtractorInput r17, PositionHolder r18) {
        long r2 = r17.getPosition();
        if (this.sampleTrackIndex != (-1)) goto L7;
        int r4 = getTrackIndexOfNextReadSample(r2);
        this.sampleTrackIndex = r4;
        if (r4 != (-1)) goto L7;
        return -1;
    L7:
        Mp4Track r42 = this.tracks[this.sampleTrackIndex];
        TrackOutput r6 = r42.trackOutput;
        int r14 = r42.sampleIndex;
        TrackSampleTable r7 = r42.sampleTable;
        long r9 = r7.offsets[r14] + this.sampleOffsetForAuxiliaryTracks;
        int r72 = r7.sizes[r14];
        TrueHdSampleRechunker r8 = r42.trueHdSampleRechunker;
        long r22 = (r9 - r2) + this.sampleBytesRead;
        if (r22 < 0) goto L66;
        if (r22 >= MediaStatus.COMMAND_STREAM_TRANSFER) goto L66;
        if (r42.track.sampleTransformation != 1) goto L15;
        r22 = r22 + 8;
        r72 = r72 - 8;
    L15:
        r17.skipFully((int) r22);
        if (canReadWithinGopSampleDependencies(r42.track.format) == true) goto L18;
        this.isSampleDependedOn = true;
    L18:
        Track r23 = r42.track;
        if (r23.nalUnitLengthFieldLength == 0) goto L44;
        byte[] r24 = this.nalPrefix.getData();
        r24[0] = 0;
        r24[1] = 0;
        r24[2] = 0;
        int r3 = 4 - r42.track.nalUnitLengthFieldLength;
        r72 = r72 + r3;
    L22:
        if (this.sampleBytesWritten >= r72) goto L42;
        int r12 = this.sampleCurrentNalBytesRemaining;
        if (r12 != 0) goto L41;
        Track r122 = r42.track;
        int r13 = r122.nalUnitLengthFieldLength;
        if (this.isSampleDependedOn == false) goto L28;
    L30:
        int r5 = 0;
    L31:
        r17.readFully(r24, r3, r13);
        this.sampleBytesRead += r13;
        this.nalPrefix.setPosition(0);
        int r92 = this.nalPrefix.readInt();
        if (r92 < 0) goto L40;
        this.sampleCurrentNalBytesRemaining = r92 - r5;
        this.nalStartCode.setPosition(0);
        r6.sampleData(this.nalStartCode, 4);
        this.sampleBytesWritten += 4;
        if (r5 <= 0) goto L22;
        r6.sampleData(this.nalPrefix, r5);
        this.sampleBytesWritten += r5;
        if (NalUnitUtil.isDependedOn(r24, 4, r5, r42.track.format) == false) goto L22;
        this.isSampleDependedOn = true;
        goto L22
    L40:
        throw ParserException.createForMalformedContainer("Invalid NAL length", null);
    L28:
        if ((NalUnitUtil.numberOfBytesInNalUnitHeader(r122.format) + r13) > (r42.sampleTable.sizes[r14] - this.sampleBytesRead)) goto L30;
        r5 = NalUnitUtil.numberOfBytesInNalUnitHeader(r42.track.format);
        r13 = r42.track.nalUnitLengthFieldLength + r5;
        goto L31
    L41:
        int r93 = r6.sampleData(r17, r12, false);
        this.sampleBytesRead += r93;
        this.sampleBytesWritten += r93;
        this.sampleCurrentNalBytesRemaining -= r93;
        goto L22
    L42:
        TrackOutput.CryptoData r52 = null;
    L43:
        int r11 = r72;
        TrackSampleTable r1 = r42.sampleTable;
        long r123 = r1.timestampsUs[r14];
        int r15 = r1.flags[r14];
        if (this.isSampleDependedOn == true) goto L58;
        r15 = r15 | 67108864;
    L58:
        int r94 = r15;
        if (r8 == null) goto L63;
        boolean r16 = false;
        r8.sampleMetadata(r6, r123, r94, r11, 0, null);
        ?? r19 = r16;
        if ((r14 + 1) != r42.sampleTable.sampleCount) goto L64;
        r8.outputPendingSampleMetadata(r6, r52);
        r19 = r16;
    L64:
        r42.sampleIndex++;
        this.sampleTrackIndex = -1;
        this.sampleBytesRead = r19;
        this.sampleBytesWritten = r19;
        this.sampleCurrentNalBytesRemaining = r19;
        this.isSampleDependedOn = r19;
        return r19;
    L63:
        r19 = 0;
        r6.sampleMetadata(r123, r94, r11, 0, null);
        goto L64
    L44:
        r52 = null;
        if ("audio/ac4".equals(r23.format.sampleMimeType) == true) goto L47;
        if (r8 == null) goto L52;
        r8.startSample(r17);
    L52:
        int r25 = this.sampleBytesWritten;
        if (r25 >= r72) goto L43;
        int r26 = r6.sampleData(r17, r72 - r25, false);
        this.sampleBytesRead += r26;
        this.sampleBytesWritten += r26;
        this.sampleCurrentNalBytesRemaining -= r26;
        goto L52
    L47:
        if (this.sampleBytesWritten != 0) goto L49;
        Ac4Util.getAc4SampleHeader(r72, this.scratch);
        r6.sampleData(this.scratch, 7);
        this.sampleBytesWritten += 7;
    L49:
        r72 = r72 + 7;
    L66:
        r18.position = r9;
        return 1;
    }

    private int readSefData(ExtractorInput r5, PositionHolder r6) {
        int r52 = this.sefReader.read(r5, r6, this.slowMotionMetadataEntries);
        if (r52 == 1) goto L5;
    L7:
        return r52;
    L5:
        if (r6.position != 0) goto L7;
        enterReadingAtomHeaderState();
        goto L7
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
        if (r1 != 1701082227) goto L15;
        return true;
    L15:
        if (r1 != 1835365473) goto L17;
        return true;
    L17:
        if (r1 == 1635284069) goto L29;
        return false;
    L29:
        return true;
    }

    private static boolean shouldParseLeafAtom(int r1) {
        if (r1 != 1835296868) goto L5;
        return true;
    L5:
        if (r1 != 1836476516) goto L7;
        return true;
    L7:
        if (r1 != 1751411826) goto L9;
        return true;
    L9:
        if (r1 != 1937011556) goto L11;
        return true;
    L11:
        if (r1 != 1937011827) goto L13;
        return true;
    L13:
        if (r1 != 1937011571) goto L15;
        return true;
    L15:
        if (r1 != 1668576371) goto L17;
        return true;
    L17:
        if (r1 != 1701606260) goto L19;
        return true;
    L19:
        if (r1 != 1937011555) goto L21;
        return true;
    L21:
        if (r1 != 1937011578) goto L23;
        return true;
    L23:
        if (r1 != 1937013298) goto L25;
        return true;
    L25:
        if (r1 != 1937007471) goto L27;
        return true;
    L27:
        if (r1 != 1668232756) goto L29;
        return true;
    L29:
        if (r1 != 1953196132) goto L31;
        return true;
    L31:
        if (r1 != 1718909296) goto L33;
        return true;
    L33:
        if (r1 != 1969517665) goto L35;
        return true;
    L35:
        if (r1 != 1801812339) goto L37;
        return true;
    L37:
        if (r1 == 1768715124) goto L59;
        return false;
    L59:
        return true;
    }

    private boolean shouldSeekToAxteAtom(Metadata r6) {
        if (r6 != null) goto L6;
        return false;
    L6:
        if ((this.flags & 64) == 0) goto L13;
        MdtaMetadataEntry r62 = MetadataUtil.findMdtaMetadataEntryWithKey(r6, "auxiliary.tracks.offset");
        if (r62 == null) goto L13;
        long r1 = new ParsableByteArray(r62.value).readUnsignedLongToLong();
        if (r1 <= 0) goto L13;
        this.axteAtomOffset = r1;
        return true;
    L13:
        return false;
    }

    private void updateSampleIndex(Mp4Track r3, long r4) {
        TrackSampleTable r2 = r3.sampleTable;
        int r0 = r2.getIndexOfEarlierOrEqualSynchronizationSample(r4);
        if (r0 != (-1)) goto L5;
        r0 = r2.getIndexOfLaterOrEqualSynchronizationSample(r4);
    L5:
        r3.sampleIndex = r0;
    }

    @Override // androidx.media3.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // androidx.media3.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long r2) {
        return getSeekPoints(r2, -1);
    }

    public SeekMap.SeekPoints getSeekPoints(long r17, int r19) {
        Mp4Track[] r4 = this.tracks;
        if (r4.length != 0) goto L7;
        return new SeekMap.SeekPoints(SeekPoint.START);
    L7:
        if (r19 == (-1)) goto L9;
        int r6 = r19;
    L11:
        if (r6 == (-1)) goto L24;
        TrackSampleTable r42 = r4[r6].sampleTable;
        int r62 = getSynchronizationSampleIndex(r42, r17);
        if (r62 == (-1)) goto L15;
        long r12 = r42.timestampsUs[r62];
        long r14 = r42.offsets[r62];
        if (r12 < r17) goto L19;
    L25:
        long r1 = -1;
        long r9 = -9223372036854775807L;
    L26:
        if (r19 != (-1)) goto L37;
        int r3 = 0;
    L28:
        Mp4Track[] r43 = this.tracks;
        if (r3 >= r43.length) goto L37;
        if (r3 == this.firstVideoTrackIndex) goto L36;
        TrackSampleTable r44 = r43[r3].sampleTable;
        long r5 = maybeAdjustSeekOffset(r44, r12, r14);
        if (r9 == (-9223372036854775807L)) goto L35;
        r1 = maybeAdjustSeekOffset(r44, r9, r1);
    L35:
        r14 = r5;
    L36:
        r3 = r3 + 1;
    L37:
        SeekPoint r0 = new SeekPoint(r12, r14);
        if (r9 != (-9223372036854775807L)) goto L42;
        return new SeekMap.SeekPoints(r0);
    L42:
        return new SeekMap.SeekPoints(r0, new SeekPoint(r9, r1));
    L19:
        if (r62 >= (r42.sampleCount - 1)) goto L25;
        int r13 = r42.getIndexOfLaterOrEqualSynchronizationSample(r17);
        if (r13 == (-1)) goto L25;
        if (r13 == r62) goto L25;
        r9 = r42.timestampsUs[r13];
        r1 = r42.offsets[r13];
        goto L26
    L15:
        return new SeekMap.SeekPoints(SeekPoint.START);
    L24:
        r14 = LongCompanionObject.MAX_VALUE;
        r12 = r17;
        goto L25
    L9:
        r6 = this.firstVideoTrackIndex;
        goto L11
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
    public void init(ExtractorOutput r3) {
        if ((this.flags & 16) != 0) goto L5;
        r3 = new SubtitleTranscodingExtractorOutput(r3, this.subtitleParserFactory);
    L5:
        this.extractorOutput = r3;
    }

    @Override // androidx.media3.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput r3, PositionHolder r4) {
    L2:
        int r0 = this.parserState;
        if (r0 != 0) goto L5;
        if (readAtomHeader(r3) == true) goto L2;
        return -1;
    L5:
        if (r0 != 1) goto L7;
        if (readAtomPayload(r3, r4) == false) goto L2;
        return 1;
    L7:
        if (r0 == 2) goto L15;
        if (r0 == 3) goto L11;
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return 0;
    L11:
        return readSefData(r3, r4);
    L15:
        return readSample(r3, r4);
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long r4, long r6) {
        this.containerAtoms.clear();
        int r0 = 0;
        this.atomHeaderBytesRead = 0;
        this.sampleTrackIndex = -1;
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.isSampleDependedOn = false;
        if (r4 == 0) goto L5;
        Mp4Track[] r42 = this.tracks;
        int r5 = r42.length;
    L11:
        if (r0 >= r5) goto L16;
        Mp4Track r1 = r42[r0];
        updateSampleIndex(r1, r6);
        TrueHdSampleRechunker r12 = r1.trueHdSampleRechunker;
        if (r12 == null) goto L15;
        r12.reset();
    L15:
        r0 = r0 + 1;
        goto L11
    L16:
        return;
    L5:
        if (this.parserState == 3) goto L8;
        enterReadingAtomHeaderState();
        return;
    L8:
        this.sefReader.reset();
        this.slowMotionMetadataEntries.clear();
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput r4) {
        if ((this.flags & 2) == 0) goto L5;
        boolean r0 = true;
    L6:
        SniffFailure r42 = Sniffer.sniffUnfragmented(r4, r0);
        if (r42 == null) goto L9;
        ImmutableList<SniffFailure> r02 = ImmutableList.of(r42);
    L10:
        this.lastSniffFailures = r02;
        if (r42 != null) goto L13;
        return true;
    L13:
        return false;
    L9:
        r02 = ImmutableList.of();
        goto L10
    L5:
        r0 = false;
        goto L6
    }
}
