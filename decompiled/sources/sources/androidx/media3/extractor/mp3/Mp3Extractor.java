package androidx.media3.extractor.mp3;

import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.DiscardingTrackOutput;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.GaplessInfoHolder;
import androidx.media3.extractor.Id3Peeker;
import androidx.media3.extractor.MpegAudioUtil;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.metadata.id3.Id3Decoder;
import androidx.media3.extractor.metadata.id3.MlltFrame;
import androidx.media3.extractor.metadata.id3.TextInformationFrame;
import androidx.media3.extractor.mp3.Seeker;
import com.google.common.math.LongMath;
import com.google.common.primitives.Ints;
import java.io.EOFException;
import java.math.RoundingMode;
import okio.Buffer$$ExternalSyntheticBUOutline1;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes4.dex */
public final class Mp3Extractor implements Extractor {
    public static final ExtractorsFactory FACTORY = null;
    private static final Id3Decoder.FramePredicate REQUIRED_ID3_FRAME_PREDICATE = null;
    private long basisTimeUs;
    private TrackOutput currentTrackOutput;
    private boolean disableSeeking;
    private long endPositionOfLastSampleRead;
    private ExtractorOutput extractorOutput;
    private long firstSamplePosition;
    private final int flags;
    private final long forcedFirstSampleTimestampUs;
    private final GaplessInfoHolder gaplessInfoHolder;
    private final Id3Peeker id3Peeker;
    private boolean isSeekInProgress;
    private Metadata metadata;
    private TrackOutput realTrackOutput;
    private int sampleBytesRemaining;
    private long samplesRead;
    private final ParsableByteArray scratch;
    private long seekTimeUs;
    private Seeker seeker;
    private final TrackOutput skippingTrackOutput;
    private final MpegAudioUtil.Header synchronizedHeader;
    private int synchronizedHeaderData;

    public static /* synthetic */ Extractor[] $r8$lambda$VlN23tvbrkcLKliae6icwKqXLAE() {
        return new Extractor[]{new Mp3Extractor()};
    }

    public static /* synthetic */ boolean $r8$lambda$bVD6_snQ71sbMsHH4YoyozOnKO8(int r3, int r4, int r5, int r6, int r7) {
        if (r4 == 67) goto L5;
    L9:
        if (r4 == 77) goto L11;
        return false;
    L11:
        if (r5 != 76) goto L23;
        if (r6 == 76) goto L14;
        return false;
    L14:
        if (r7 == 84) goto L21;
        if (r3 != 2) goto L25;
        return true;
    L25:
        return false;
    L21:
        return true;
    L23:
        return false;
    L5:
        if (r5 != 79) goto L9;
        if (r6 != 77) goto L9;
        if (r7 == 77) goto L16;
        if (r3 != 2) goto L9;
        return true;
    L16:
        return true;
    }

    static {
        FACTORY = new Mp3Extractor$$ExternalSyntheticLambda0();
        REQUIRED_ID3_FRAME_PREDICATE = new Mp3Extractor$$ExternalSyntheticLambda1();
    }

    public Mp3Extractor() {
        this(0);
    }

    public Mp3Extractor(int r3) {
        this(r3, -9223372036854775807L);
    }

    public Mp3Extractor(int r2, long r3) {
        if ((r2 & 2) == 0) goto L5;
        r2 = r2 | 1;
    L5:
        this.flags = r2;
        this.forcedFirstSampleTimestampUs = r3;
        this.scratch = new ParsableByteArray(10);
        this.synchronizedHeader = new MpegAudioUtil.Header();
        this.gaplessInfoHolder = new GaplessInfoHolder();
        this.basisTimeUs = -9223372036854775807L;
        this.id3Peeker = new Id3Peeker();
        DiscardingTrackOutput r22 = new DiscardingTrackOutput();
        this.skippingTrackOutput = r22;
        this.currentTrackOutput = r22;
        this.endPositionOfLastSampleRead = -1;
    }

    @EnsuresNonNull({"extractorOutput", "realTrackOutput"})
    private void assertInitialized() {
        Assertions.checkStateNotNull(this.realTrackOutput);
        Util.castNonNull(this.extractorOutput);
    }

    @RequiresNonNull({"realTrackOutput"})
    private Seeker computeSeeker(ExtractorInput r19) {
        Seeker r1 = maybeReadSeekFrame(r19);
        MlltSeeker r2 = maybeHandleSeekMetadata(this.metadata, r19.getPosition());
        if (this.disableSeeking == false) goto L7;
        return new Seeker.UnseekableSeeker();
    L7:
        if ((this.flags & 4) == 0) goto L16;
        if (r2 == null) goto L12;
        long r6 = r2.getDurationUs();
        long r12 = r2.getDataEndPosition();
    L10:
        long r13 = r12;
    L15:
        r1 = new IndexSeeker(r6, r19.getPosition(), r13);
    L21:
        if (r1 != null) goto L23;
    L41:
        if (r1 == null) goto L45;
        if (shouldFallbackToConstantBitrateSeeking(r1) == true) goto L45;
    L50:
        this.realTrackOutput.durationUs(r1.getDurationUs());
        return r1;
    L45:
        if ((this.flags & 2) == 0) goto L48;
        boolean r14 = true;
    L49:
        r1 = getConstantBitrateSeeker(r19, r14);
        goto L50
    L48:
        r14 = false;
        goto L49
    L23:
        if (shouldFallbackToConstantBitrateSeeking(r1) == false) goto L41;
        if (r1.getDurationUs() == (-9223372036854775807L)) goto L41;
        if (r1.getDataEndPosition() != (-1)) goto L31;
        if (r19.getLength() == (-1)) goto L41;
    L31:
        if (r1.getDataStartPosition() == (-1)) goto L34;
        long r22 = r1.getDataStartPosition();
    L33:
        long r9 = r22;
        if (r1.getDataEndPosition() == (-1)) goto L39;
        long r23 = r1.getDataEndPosition();
    L38:
        long r7 = r23;
        r1 = new ConstantBitrateSeeker(r7, r9, Ints.saturatedCast(Util.scaleLargeValue(r7 - r9, 8000000, r1.getDurationUs(), RoundingMode.HALF_UP)), -1, false);
        goto L50
    L39:
        r23 = r19.getLength();
        goto L38
    L34:
        r22 = 0;
        goto L33
    L12:
        if (r1 == null) goto L14;
        r6 = r1.getDurationUs();
        r12 = r1.getDataEndPosition();
        goto L10
    L14:
        r6 = getId3TlenUs(this.metadata);
        r13 = -1;
        goto L15
    L16:
        if (r2 == null) goto L18;
        r1 = r2;
        goto L21
    L18:
        if (r1 != null) goto L21;
        r1 = null;
        goto L21
    }

    private long computeTimeUs(long r5) {
        return this.basisTimeUs + ((r5 * 1000000) / this.synchronizedHeader.sampleRate);
    }

    private Seeker getConstantBitrateSeeker(long r15, XingFrame r17, long r18) {
        long r4 = r17.computeDurationUs();
        if (r4 != (-9223372036854775807L)) goto L5;
        return null;
    L5:
        long r2 = r17.dataSize;
        if (r2 == (-1)) goto L10;
        long r0 = r15 + r2;
        long r22 = r2 - r17.header.frameSize;
        long r7 = r0;
    L8:
        long r02 = r22;
        RoundingMode r6 = RoundingMode.HALF_UP;
        return new ConstantBitrateSeeker(r7, r15 + r17.header.frameSize, Ints.checkedCast(Util.scaleLargeValue(r02, 8000000, r4, r6)), Ints.checkedCast(LongMath.divide(r02, r17.frameCount, r6)), false);
    L10:
        if (r18 == (-1)) goto L14;
        r22 = (r18 - r15) - r17.header.frameSize;
        r7 = r18;
        goto L8
    L14:
        return null;
    }

    private Seeker getConstantBitrateSeeker(ExtractorInput r10, boolean r11) {
        r10.peekFully(this.scratch.getData(), 0, 4);
        this.scratch.setPosition(0);
        this.synchronizedHeader.setForHeaderData(this.scratch.readInt());
        return new ConstantBitrateSeeker(r10.getLength(), r10.getPosition(), this.synchronizedHeader, r11);
    }

    private static long getId3TlenUs(Metadata r6) {
        if (r6 == null) goto L12;
        int r0 = r6.length();
        int r2 = 0;
    L4:
        if (r2 >= r0) goto L18;
        Metadata.Entry r3 = r6.get(r2);
        if ((r3 instanceof TextInformationFrame) == false) goto L11;
        TextInformationFrame r32 = (TextInformationFrame) r3;
        if (r32.id.equals("TLEN") == false) goto L11;
        return Util.msToUs(Long.parseLong(r32.values.get(0)));
    L11:
        r2 = r2 + 1;
        goto L4
    L18:
        return -9223372036854775807L;
    L12:
        return -9223372036854775807L;
    }

    private static int getSeekFrameHeader(ParsableByteArray r2, int r3) {
        if (r2.limit() < (r3 + 4)) goto L10;
        r2.setPosition(r3);
        int r32 = r2.readInt();
        if (r32 != 1483304551) goto L7;
    L8:
        return r32;
    L7:
        if (r32 == 1231971951) goto L8;
    L10:
        if (r2.limit() < 40) goto L14;
        r2.setPosition(36);
        if (r2.readInt() != 1447187017) goto L16;
        return 1447187017;
    L16:
        return 0;
    L14:
        return 0;
    }

    private static boolean headersMatch(int r4, long r5) {
        if ((r4 & (-128000)) != (r5 & (-128000))) goto L6;
        return true;
    L6:
        return false;
    }

    private static MlltSeeker maybeHandleSeekMetadata(Metadata r4, long r5) {
        if (r4 == null) goto L10;
        int r0 = r4.length();
        int r1 = 0;
    L4:
        if (r1 >= r0) goto L14;
        Metadata.Entry r2 = r4.get(r1);
        if ((r2 instanceof MlltFrame) == true) goto L8;
        r1 = r1 + 1;
        goto L4
    L8:
        return MlltSeeker.create(r5, (MlltFrame) r2, getId3TlenUs(r4));
    L14:
        return null;
    L10:
        return null;
    }

    private Seeker maybeReadSeekFrame(ExtractorInput r13) {
        ParsableByteArray r5 = new ParsableByteArray(this.synchronizedHeader.frameSize);
        r13.peekFully(r5.getData(), 0, this.synchronizedHeader.frameSize);
        MpegAudioUtil.Header r0 = this.synchronizedHeader;
        int r3 = 21;
        if ((r0.version & 1) == 0) goto L8;
        if (r0.channels == 1) goto L11;
        r3 = 36;
    L11:
        int r02 = getSeekFrameHeader(r5, r3);
        if (r02 != 1231971951) goto L14;
    L20:
        XingFrame r9 = XingFrame.parse(this.synchronizedHeader, r5);
        if (this.gaplessInfoHolder.hasGaplessInfo() == true) goto L27;
        int r1 = r9.encoderDelay;
        if (r1 == (-1)) goto L27;
        int r4 = r9.encoderPadding;
        if (r4 == (-1)) goto L27;
        GaplessInfoHolder r32 = this.gaplessInfoHolder;
        r32.encoderDelay = r1;
        r32.encoderPadding = r4;
    L27:
        long r7 = r13.getPosition();
        if (r13.getLength() != (-1)) goto L30;
    L34:
        r13.skipFully(this.synchronizedHeader.frameSize);
        if (r02 != 1483304551) goto L39;
        return XingSeeker.create(r9, r7);
    L39:
        return getConstantBitrateSeeker(r7, r9, r13.getLength());
    L30:
        if (r9.dataSize == (-1)) goto L34;
        if (r13.getLength() == (r9.dataSize + r7)) goto L34;
        Log.i("Mp3Extractor", "Data size mismatch between stream (" + r13.getLength() + ") and Xing frame (" + (r9.dataSize + r7) + "), using Xing value.");
        goto L34
    L14:
        if (r02 == 1447187017) goto L18;
        if (r02 == 1483304551) goto L20;
        r13.resetPeekPosition();
        return null;
    L18:
        VbriSeeker r03 = VbriSeeker.create(r13.getLength(), r13.getPosition(), this.synchronizedHeader, r5);
        r13.skipFully(this.synchronizedHeader.frameSize);
        return r03;
    L8:
        if (r0.channels != 1) goto L11;
        r3 = 13;
        goto L11
    }

    private void maybeUpdateCbrDurationToLastSample() {
        Seeker r0 = this.seeker;
        if ((r0 instanceof ConstantBitrateSeeker) == true) goto L5;
        return;
    L5:
        if (r0.isSeekable() == false) goto L13;
        long r02 = this.endPositionOfLastSampleRead;
        if (r02 != (-1)) goto L9;
        return;
    L9:
        if (r02 == this.seeker.getDataEndPosition()) goto L15;
        this.seeker = ((ConstantBitrateSeeker) this.seeker).copyWithNewDataEndPosition(this.endPositionOfLastSampleRead);
        ((ExtractorOutput) Assertions.checkNotNull(this.extractorOutput)).seekMap(this.seeker);
        ((TrackOutput) Assertions.checkNotNull(this.realTrackOutput)).durationUs(this.seeker.getDurationUs());
        return;
    L15:
        return;
    }

    private boolean peekEndOfStreamOrHeader(ExtractorInput r9) {
        Seeker r0 = this.seeker;
        if (r0 == null) goto L13;
        long r2 = r0.getDataEndPosition();
        if (r2 == (-1)) goto L13;
        if (r9.getPeekPosition() <= (r2 - 4)) goto L13;
        return true;
    L13:
        return !r9.peekFully(this.scratch.getData(), 0, 4, true);
    L12:
        return true;
    }

    @RequiresNonNull({"extractorOutput", "realTrackOutput"})
    private int readInternal(ExtractorInput r6) {
        if (this.synchronizedHeaderData != 0) goto L10;
        synchronize(r6, false);     // Catch: EOFException -> L7
    L7:
        return -1;
    L10:
        if (this.seeker != null) goto L20;
        Seeker r0 = computeSeeker(r6);
        this.seeker = r0;
        this.extractorOutput.seekMap(r0);
        Format.Builder r02 = new Format.Builder().setContainerMimeType("audio/mpeg").setSampleMimeType(this.synchronizedHeader.mimeType).setMaxInputSize(4096).setChannelCount(this.synchronizedHeader.channels).setSampleRate(this.synchronizedHeader.sampleRate).setEncoderDelay(this.gaplessInfoHolder.encoderDelay).setEncoderPadding(this.gaplessInfoHolder.encoderPadding);
        if ((this.flags & 8) == 0) goto L14;
        Metadata r1 = null;
    L15:
        Format.Builder r03 = r02.setMetadata(r1);
        if (this.seeker.getAverageBitrate() == (-2147483647)) goto L18;
        r03.setAverageBitrate(this.seeker.getAverageBitrate());
    L18:
        this.currentTrackOutput.format(r03.build());
        this.firstSamplePosition = r6.getPosition();
    L25:
        return readSample(r6);
    L14:
        r1 = this.metadata;
        goto L15
    L20:
        if (this.firstSamplePosition == 0) goto L25;
        long r04 = r6.getPosition();
        long r2 = this.firstSamplePosition;
        if (r04 >= r2) goto L25;
        r6.skipFully((int) (r2 - r04));
        goto L25
    }

    @RequiresNonNull({"realTrackOutput", "seeker"})
    private int readSample(ExtractorInput r12) {
        if (this.sampleBytesRemaining != 0) goto L26;
        r12.resetPeekPosition();
        if (peekEndOfStreamOrHeader(r12) == false) goto L7;
        return -1;
    L7:
        this.scratch.setPosition(0);
        int r0 = this.scratch.readInt();
        if (headersMatch(r0, this.synchronizedHeaderData) == true) goto L10;
    L24:
        r12.skipFully(1);
        this.synchronizedHeaderData = 0;
        return 0;
    L10:
        if (MpegAudioUtil.getFrameSize(r0) == (-1)) goto L24;
        this.synchronizedHeader.setForHeaderData(r0);
        if (this.basisTimeUs != (-9223372036854775807L)) goto L17;
        this.basisTimeUs = this.seeker.getTimeUs(r12.getPosition());
        if (this.forcedFirstSampleTimestampUs == (-9223372036854775807L)) goto L17;
        this.basisTimeUs += this.forcedFirstSampleTimestampUs - this.seeker.getTimeUs(0);
    L17:
        this.sampleBytesRemaining = this.synchronizedHeader.frameSize;
        long r4 = r12.getPosition();
        MpegAudioUtil.Header r02 = this.synchronizedHeader;
        this.endPositionOfLastSampleRead = r4 + r02.frameSize;
        Seeker r42 = this.seeker;
        if ((r42 instanceof IndexSeeker) == false) goto L26;
        IndexSeeker r43 = (IndexSeeker) r42;
        r43.maybeAddSeekPoint(computeTimeUs(this.samplesRead + r02.samplesPerFrame), this.endPositionOfLastSampleRead);
        if (this.isSeekInProgress == false) goto L26;
        if (r43.isTimeUsInIndex(this.seekTimeUs) == false) goto L26;
        this.isSeekInProgress = false;
        this.currentTrackOutput = this.realTrackOutput;
    L26:
        int r122 = this.currentTrackOutput.sampleData(r12, this.sampleBytesRemaining, true);
        if (r122 != (-1)) goto L29;
        return -1;
    L29:
        int r03 = this.sampleBytesRemaining - r122;
        this.sampleBytesRemaining = r03;
        if (r03 <= 0) goto L32;
        return 0;
    L32:
        this.currentTrackOutput.sampleMetadata(computeTimeUs(this.samplesRead), 1, this.synchronizedHeader.frameSize, 0, null);
        this.samplesRead += this.synchronizedHeader.samplesPerFrame;
        this.sampleBytesRemaining = 0;
        return 0;
    }

    private boolean shouldFallbackToConstantBitrateSeeking(Seeker r1) {
        if (r1.isSeekable() == false) goto L5;
        return false;
    L5:
        if ((this.flags & 1) == 0) goto L9;
        return true;
    L9:
        return false;
    }

    private boolean synchronize(ExtractorInput r11, boolean r12) {
        if (r12 == false) goto L4;
        int r0 = 32768;
    L5:
        r11.resetPeekPosition();
        if (r11.getPosition() == 0) goto L8;
        int r1 = 0;
        int r3 = 0;
    L18:
        int r4 = r3;
        int r5 = r4;
    L21:
        if (peekEndOfStreamOrHeader(r11) == true) goto L22;
        this.scratch.setPosition(0);
        int r6 = this.scratch.readInt();
        if (r3 != 0) goto L29;
    L30:
        int r8 = MpegAudioUtil.getFrameSize(r6);
        if (r8 == (-1)) goto L32;
        r4 = r4 + 1;
        if (r4 != 1) goto L46;
        this.synchronizedHeader.setForHeaderData(r6);
        r3 = r6;
    L52:
        r11.advancePeekPosition(r8 - 4);
        goto L21
    L46:
        if (r4 != 4) goto L52;
    L47:
        if (r12 == false) goto L49;
        r11.skipFully(r1 + r5);
    L50:
        this.synchronizedHeaderData = r3;
        return true;
    L49:
        r11.resetPeekPosition();
    L32:
        int r32 = r5 + 1;
        if (r5 == r0) goto L34;
        if (r12 == false) goto L40;
        r11.resetPeekPosition();
        r11.advancePeekPosition(r1 + r32);
    L41:
        r4 = 0;
        r5 = r32;
        r3 = 0;
        goto L21
    L40:
        r11.skipFully(1);
        goto L41
    L34:
        if (r12 == false) goto L36;
        return false;
    L36:
        maybeUpdateCbrDurationToLastSample();
        Buffer$$ExternalSyntheticBUOutline1.m();
        return false;
    L29:
        if (headersMatch(r6, r3) == false) goto L32;
    L22:
        if (r4 > 0) goto L47;
        maybeUpdateCbrDurationToLastSample();
        Buffer$$ExternalSyntheticBUOutline1.m();
        return false;
    L8:
        if ((this.flags & 8) != 0) goto L10;
        Id3Decoder.FramePredicate r13 = null;
    L11:
        Metadata r14 = this.id3Peeker.peekId3Data(r11, r13);
        this.metadata = r14;
        if (r14 == null) goto L14;
        this.gaplessInfoHolder.setFromMetadata(r14);
    L14:
        r1 = (int) r11.getPeekPosition();
        if (r12 == true) goto L17;
        r11.skipFully(r1);
    L17:
        r3 = 0;
        goto L18
    L10:
        r13 = REQUIRED_ID3_FRAME_PREDICATE;
        goto L11
    L4:
        r0 = 131072;
        goto L5
    }

    public void disableSeeking() {
        this.disableSeeking = true;
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput r3) {
        this.extractorOutput = r3;
        TrackOutput r32 = r3.track(0, 1);
        this.realTrackOutput = r32;
        this.currentTrackOutput = r32;
        this.extractorOutput.endTracks();
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput r5, PositionHolder r6) {
        assertInitialized();
        int r52 = readInternal(r5);
        if (r52 == (-1)) goto L5;
    L9:
        return r52;
    L5:
        if ((this.seeker instanceof IndexSeeker) == false) goto L9;
        long r0 = computeTimeUs(this.samplesRead);
        if (this.seeker.getDurationUs() == r0) goto L9;
        ((IndexSeeker) this.seeker).setDurationUs(r0);
        this.extractorOutput.seekMap(this.seeker);
        this.realTrackOutput.durationUs(this.seeker.getDurationUs());
        goto L9
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long r3, long r5) {
        this.synchronizedHeaderData = 0;
        this.basisTimeUs = -9223372036854775807L;
        this.samplesRead = 0;
        this.sampleBytesRemaining = 0;
        this.seekTimeUs = r5;
        Seeker r32 = this.seeker;
        if ((r32 instanceof IndexSeeker) == true) goto L5;
        return;
    L5:
        if (((IndexSeeker) r32).isTimeUsInIndex(r5) == true) goto L9;
        this.isSeekInProgress = true;
        this.currentTrackOutput = this.skippingTrackOutput;
        return;
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput r2) {
        return synchronize(r2, true);
    }
}
