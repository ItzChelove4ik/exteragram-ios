package androidx.media3.extractor.amr;

import androidx.car.app.media.CarAudioRecord;
import androidx.media3.common.Format;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ConstantBitrateSeekMap;
import androidx.media3.extractor.DiscardingTrackOutput;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.IndexSeekMap;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import java.io.EOFException;
import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes4.dex */
public final class AmrExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = null;
    private static final byte[] amrSignatureNb = null;
    private static final byte[] amrSignatureWb = null;
    private static final int[] frameSizeBytesByTypeNb = null;
    private static final int[] frameSizeBytesByTypeWb = null;
    private int currentSampleBytesRemaining;
    private int currentSampleSize;
    private long currentSampleTimeUs;
    private TrackOutput currentTrackOutput;
    private ExtractorOutput extractorOutput;
    private long firstSamplePosition;
    private int firstSampleSize;
    private final int flags;
    private boolean hasOutputFormat;
    private boolean isSeekInProgress;
    private boolean isWideBand;
    private int numSamplesWithSameSize;
    private TrackOutput realTrackOutput;
    private final byte[] scratch;
    private SeekMap seekMap;
    private long seekTimeUs;
    private final TrackOutput skippingTrackOutput;
    private long timeOffsetUs;

    /* renamed from: $r8$lambda$SJh1mMR68eVuGS4--i-rR_1BeE4, reason: not valid java name */
    public static /* synthetic */ Extractor[] m855$r8$lambda$SJh1mMR68eVuGS4irR_1BeE4() {
        return new Extractor[]{new AmrExtractor()};
    }

    static {
        FACTORY = new AmrExtractor$$ExternalSyntheticLambda0();
        frameSizeBytesByTypeNb = new int[]{13, 14, 16, 18, 20, 21, 27, 32, 6, 7, 6, 6, 1, 1, 1, 1};
        frameSizeBytesByTypeWb = new int[]{18, 24, 33, 37, 41, 47, 51, 59, 61, 6, 1, 1, 1, 1, 1, 1};
        amrSignatureNb = Util.getUtf8Bytes("#!AMR\n");
        amrSignatureWb = Util.getUtf8Bytes("#!AMR-WB\n");
    }

    public AmrExtractor() {
        this(0);
    }

    public AmrExtractor(int r2) {
        if ((r2 & 2) == 0) goto L5;
        r2 = r2 | 1;
    L5:
        this.flags = r2;
        this.scratch = new byte[1];
        this.firstSampleSize = -1;
        DiscardingTrackOutput r22 = new DiscardingTrackOutput();
        this.skippingTrackOutput = r22;
        this.currentTrackOutput = r22;
    }

    @EnsuresNonNull({"extractorOutput", "realTrackOutput"})
    private void assertInitialized() {
        Assertions.checkStateNotNull(this.realTrackOutput);
        Util.castNonNull(this.extractorOutput);
    }

    private static int getBitrateFromFrameSize(int r4, long r5) {
        return (int) ((r4 * 8000000) / r5);
    }

    private SeekMap getConstantBitrateSeekMap(long r12, boolean r14) {
        int r8 = getBitrateFromFrameSize(this.firstSampleSize, 20000);
        return new ConstantBitrateSeekMap(r12, this.firstSamplePosition, r8, this.firstSampleSize, r14);
    }

    private int getFrameSizeInBytes(int r3) {
        boolean r0 = isValidFrameType(r3);
        boolean r2 = this.isWideBand;
        if (r0 == true) goto L10;
        StringBuilder r02 = new StringBuilder("Illegal AMR ");
        if (r2 == false) goto L7;
        String r22 = "WB";
    L8:
        r02.append(r22);
        r02.append(" frame type ");
        r02.append(r3);
        throw ParserException.createForMalformedContainer(r02.toString(), null);
    L7:
        r22 = "NB";
        goto L8
    L10:
        if (r2 == false) goto L14;
        return frameSizeBytesByTypeWb[r3];
    L14:
        return frameSizeBytesByTypeNb[r3];
    }

    private boolean isNarrowBandValidFrameType(int r1) {
        if (this.isWideBand == false) goto L5;
        return false;
    L5:
        if (r1 >= 12) goto L7;
        return true;
    L7:
        if (r1 <= 14) goto L13;
        return true;
    L13:
        return false;
    }

    private boolean isSeekTimeUsWithinRange(long r1, long r3) {
        if (Math.abs(r3 - r1) >= 20000) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isValidFrameType(int r2) {
        if (r2 >= 0) goto L4;
        return false;
    L4:
        if (r2 <= 15) goto L6;
        return false;
    L6:
        if (isWideBandValidFrameType(r2) == false) goto L8;
        return true;
    L8:
        if (isNarrowBandValidFrameType(r2) == false) goto L15;
        return true;
    L15:
        return false;
    }

    private boolean isWideBandValidFrameType(int r1) {
        if (this.isWideBand == true) goto L5;
        return false;
    L5:
        if (r1 >= 10) goto L7;
        return true;
    L7:
        if (r1 <= 13) goto L13;
        return true;
    L13:
        return false;
    }

    @RequiresNonNull({"realTrackOutput"})
    private void maybeOutputFormat() {
        if (this.hasOutputFormat == true) goto L19;
        this.hasOutputFormat = true;
        boolean r1 = this.isWideBand;
        String r2 = "audio/amr-wb";
        if (r1 == false) goto L7;
        String r3 = "audio/amr-wb";
    L8:
        if (r1 == true) goto L11;
        r2 = "audio/3gpp";
    L11:
        if (r1 == false) goto L13;
        int r4 = CarAudioRecord.AUDIO_CONTENT_SAMPLING_RATE;
    L14:
        if (r1 == false) goto L16;
        int r12 = frameSizeBytesByTypeWb[8];
    L17:
        this.realTrackOutput.format(new Format.Builder().setContainerMimeType(r3).setSampleMimeType(r2).setMaxInputSize(r12).setChannelCount(1).setSampleRate(r4).build());
        return;
    L16:
        r12 = frameSizeBytesByTypeNb[7];
        goto L17
    L13:
        r4 = 8000;
        goto L14
    L7:
        r3 = "audio/amr";
        goto L8
    }

    @RequiresNonNull({"extractorOutput", "realTrackOutput"})
    private void maybeOutputSeekMap(long r9, int r11) {
        if (this.seekMap != null) goto L28;
        int r0 = this.flags;
        boolean r2 = false;
        if ((r0 & 4) == 0) goto L9;
        this.seekMap = new IndexSeekMap(new long[]{this.firstSamplePosition}, new long[]{0}, -9223372036854775807L);
    L23:
        SeekMap r92 = this.seekMap;
        if (r92 == null) goto L27;
        this.extractorOutput.seekMap(r92);
        return;
    L27:
        return;
    L9:
        if ((r0 & 1) == 0) goto L22;
        int r1 = this.firstSampleSize;
        if (r1 == (-1)) goto L16;
        if (r1 != this.currentSampleSize) goto L22;
    L16:
        if (this.numSamplesWithSameSize >= 20) goto L19;
        if (r11 != (-1)) goto L23;
    L19:
        if ((r0 & 2) == 0) goto L21;
        r2 = true;
    L21:
        SeekMap r93 = getConstantBitrateSeekMap(r9, r2);
        this.seekMap = r93;
        this.realTrackOutput.durationUs(r93.getDurationUs());
    L22:
        this.seekMap = new SeekMap.Unseekable(-9223372036854775807L);
        goto L23
    }

    private static boolean peekAmrSignature(ExtractorInput r3, byte[] r4) {
        r3.resetPeekPosition();
        byte[] r0 = new byte[r4.length];
        r3.peekFully(r0, 0, r4.length);
        return Arrays.equals(r0, r4);
    }

    private int peekNextSampleSize(ExtractorInput r4) {
        r4.resetPeekPosition();
        r4.peekFully(this.scratch, 0, 1);
        byte r42 = this.scratch[0];
        if ((r42 & 131) > 0) goto L7;
        return getFrameSizeInBytes((r42 >> 3) & 15);
    L7:
        throw ParserException.createForMalformedContainer("Invalid padding bits for frame header " + r42, null);
    }

    private boolean readAmrHeader(ExtractorInput r5) {
        byte[] r0 = amrSignatureNb;
        if (peekAmrSignature(r5, r0) == false) goto L6;
        this.isWideBand = false;
        r5.skipFully(r0.length);
        return true;
    L6:
        byte[] r02 = amrSignatureWb;
        if (peekAmrSignature(r5, r02) == false) goto L10;
        this.isWideBand = true;
        r5.skipFully(r02.length);
        return true;
    L10:
        return false;
    }

    @RequiresNonNull({"realTrackOutput"})
    private int readSample(ExtractorInput r13) {
        if (this.currentSampleBytesRemaining == 0) goto L30;
    L22:
        int r132 = this.currentTrackOutput.sampleData(r13, this.currentSampleBytesRemaining, true);
        if (r132 != (-1)) goto L25;
        return -1;
    L25:
        int r0 = this.currentSampleBytesRemaining - r132;
        this.currentSampleBytesRemaining = r0;
        if (r0 <= 0) goto L28;
        return 0;
    L28:
        this.currentTrackOutput.sampleMetadata(this.timeOffsetUs + this.currentSampleTimeUs, 1, this.currentSampleSize, 0, null);
        this.currentSampleTimeUs += 20000;
        return 0;
    L30:
        int r02 = peekNextSampleSize(r13);     // Catch: EOFException -> L21
        this.currentSampleSize = r02;     // Catch: EOFException -> L21
        this.currentSampleBytesRemaining = r02;
        if (this.firstSampleSize != (-1)) goto L9;
        this.firstSamplePosition = r13.getPosition();
        this.firstSampleSize = this.currentSampleSize;
    L9:
        if (this.firstSampleSize != this.currentSampleSize) goto L11;
        this.numSamplesWithSameSize++;
    L11:
        SeekMap r03 = this.seekMap;
        if ((r03 instanceof IndexSeekMap) == false) goto L22;
        IndexSeekMap r04 = (IndexSeekMap) r03;
        long r6 = (this.timeOffsetUs + this.currentSampleTimeUs) + 20000;
        long r8 = r13.getPosition() + this.currentSampleSize;
        if (r04.isTimeUsInIndex(r6, 100000) == true) goto L17;
        r04.addSeekPoint(r6, r8);
    L17:
        if (this.isSeekInProgress == false) goto L22;
        if (isSeekTimeUsWithinRange(r6, this.seekTimeUs) == false) goto L22;
        this.isSeekInProgress = false;
        this.currentTrackOutput = this.realTrackOutput;
    L21:
        return -1;
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput r3) {
        this.extractorOutput = r3;
        TrackOutput r0 = r3.track(0, 1);
        this.realTrackOutput = r0;
        this.currentTrackOutput = r0;
        r3.endTracks();
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput r5, PositionHolder r6) {
        assertInitialized();
        if (r5.getPosition() == 0) goto L5;
    L9:
        maybeOutputFormat();
        int r62 = readSample(r5);
        maybeOutputSeekMap(r5.getLength(), r62);
        if (r62 != (-1)) goto L14;
        SeekMap r52 = this.seekMap;
        if ((r52 instanceof IndexSeekMap) == false) goto L14;
        long r0 = this.timeOffsetUs + this.currentSampleTimeUs;
        ((IndexSeekMap) r52).setDurationUs(r0);
        this.extractorOutput.seekMap(this.seekMap);
        this.realTrackOutput.durationUs(r0);
    L14:
        return r62;
    L5:
        if (readAmrHeader(r5) == true) goto L9;
        throw ParserException.createForMalformedContainer("Could not find AMR header.", null);
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long r4, long r6) {
        this.currentSampleTimeUs = 0;
        this.currentSampleSize = 0;
        this.currentSampleBytesRemaining = 0;
        this.seekTimeUs = r6;
        SeekMap r62 = this.seekMap;
        if ((r62 instanceof IndexSeekMap) == false) goto L9;
        long r42 = ((IndexSeekMap) r62).getTimeUs(r4);
        this.timeOffsetUs = r42;
        if (isSeekTimeUsWithinRange(r42, this.seekTimeUs) == true) goto L16;
        this.isSeekInProgress = true;
        this.currentTrackOutput = this.skippingTrackOutput;
        return;
    L16:
        return;
    L9:
        if (r4 != 0) goto L11;
    L14:
        this.timeOffsetUs = 0;
        return;
    L11:
        if ((r62 instanceof ConstantBitrateSeekMap) == false) goto L14;
        this.timeOffsetUs = ((ConstantBitrateSeekMap) r62).getTimeUsAtPosition(r4);
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput r1) {
        return readAmrHeader(r1);
    }
}
