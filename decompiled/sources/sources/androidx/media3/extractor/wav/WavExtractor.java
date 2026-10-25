package androidx.media3.extractor.wav;

import android.util.Pair;
import androidx.media3.common.Format;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.WavUtil;
import com.android.dx.io.Opcodes;
import kotlin.UByte;
import okhttp3.internal.http.HttpStatusCodesKt;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class WavExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = null;
    private long dataEndPosition;
    private int dataStartPosition;
    private ExtractorOutput extractorOutput;
    private OutputWriter outputWriter;
    private long rf64SampleDataSize;
    private int state;
    private TrackOutput trackOutput;

    public static final class ImaAdPcmOutputWriter implements OutputWriter {
        private static final int[] INDEX_TABLE = null;
        private static final int[] STEP_TABLE = null;
        private final ParsableByteArray decodedData;
        private final ExtractorOutput extractorOutput;
        private final Format format;
        private final int framesPerBlock;
        private final byte[] inputData;
        private long outputFrameCount;
        private int pendingInputBytes;
        private int pendingOutputBytes;
        private long startTimeUs;
        private final int targetSampleSizeFrames;
        private final TrackOutput trackOutput;
        private final WavFormat wavFormat;

        static {
            INDEX_TABLE = new int[]{-1, -1, -1, -1, 2, 4, 6, 8, -1, -1, -1, -1, 2, 4, 6, 8};
            STEP_TABLE = new int[]{7, 8, 9, 10, 11, 12, 13, 14, 16, 17, 19, 21, 23, 25, 28, 31, 34, 37, 41, 45, 50, 55, 60, 66, 73, 80, 88, 97, 107, 118, 130, 143, 157, 173, 190, Opcodes.RSUB_INT, 230, Opcodes.INVOKE_CUSTOM_RANGE, 279, HttpStatusCodesKt.HTTP_TEMP_REDIRECT, 337, 371, 408, 449, 494, 544, 598, 658, 724, 796, 876, 963, 1060, 1166, 1282, 1411, 1552, 1707, 1878, 2066, 2272, 2499, 2749, 3024, 3327, 3660, 4026, 4428, 4871, 5358, 5894, 6484, 7132, 7845, 8630, 9493, 10442, 11487, 12635, 13899, 15289, 16818, 18500, 20350, 22385, 24623, 27086, 29794, 32767};
        }

        public ImaAdPcmOutputWriter(ExtractorOutput r5, TrackOutput r6, WavFormat r7) {
            this.extractorOutput = r5;
            this.trackOutput = r6;
            this.wavFormat = r7;
            int r52 = Math.max(1, r7.frameRateHz / 10);
            this.targetSampleSizeFrames = r52;
            ParsableByteArray r0 = new ParsableByteArray(r7.extraData);
            r0.readLittleEndianUnsignedShort();
            int r02 = r0.readLittleEndianUnsignedShort();
            this.framesPerBlock = r02;
            int r1 = r7.numChannels;
            int r2 = (((r7.blockSize - (r1 * 4)) * 8) / (r7.bitsPerSample * r1)) + 1;
            if (r02 != r2) goto L7;
            int r62 = Util.ceilDivide(r52, r02);
            this.inputData = new byte[r7.blockSize * r62];
            this.decodedData = new ParsableByteArray(r62 * numOutputFramesToBytes(r02, r1));
            int r63 = ((r7.frameRateHz * r7.blockSize) * 8) / r02;
            this.format = new Format.Builder().setSampleMimeType("audio/raw").setAverageBitrate(r63).setPeakBitrate(r63).setMaxInputSize(numOutputFramesToBytes(r52, r1)).setChannelCount(r7.numChannels).setSampleRate(r7.frameRateHz).setPcmEncoding(2).build();
            return;
        L7:
            throw ParserException.createForMalformedContainer("Expected frames per block: " + r2 + "; got: " + r02, null);
        }

        private void decode(byte[] r5, int r6, ParsableByteArray r7) {
            int r1 = 0;
        L3:
            if (r1 >= r6) goto L9;
            int r2 = 0;
        L6:
            if (r2 >= this.wavFormat.numChannels) goto L8;
            decodeBlockForChannel(r5, r1, r2, r7.getData());
            r2 = r2 + 1;
            goto L6
        L8:
            r1 = r1 + 1;
            goto L3
        L9:
            int r4 = numOutputFramesToBytes(this.framesPerBlock * r6);
            r7.setPosition(0);
            r7.setLimit(r4);
        }

        private void decodeBlockForChannel(byte[] r10, int r11, int r12, byte[] r13) {
            WavFormat r0 = this.wavFormat;
            int r1 = r0.blockSize;
            int r02 = r0.numChannels;
            int r2 = (r11 * r1) + (r12 * 4);
            int r3 = (r02 * 4) + r2;
            int r14 = (r1 / r02) - 4;
            int r4 = (short) (((r10[r2 + 1] & UByte.MAX_VALUE) << 8) | (r10[r2] & UByte.MAX_VALUE));
            int r22 = Math.min(r10[r2 + 2] & UByte.MAX_VALUE, 88);
            int r5 = STEP_TABLE[r22];
            int r112 = (((r11 * this.framesPerBlock) * r02) + r12) * 2;
            r13[r112] = (byte) (r4 & 255);
            r13[r112 + 1] = (byte) (r4 >> 8);
            int r122 = 0;
        L4:
            if (r122 >= (r14 * 2)) goto L13;
            byte r6 = r10[((((r122 / 8) * r02) * 4) + r3) + ((r122 / 2) % 4)];
            int r7 = r6 & UByte.MAX_VALUE;
            if ((r122 % 2) != 0) goto L8;
            int r62 = r6 & 15;
        L9:
            int r52 = ((((r62 & 7) * 2) + 1) * r5) >> 3;
            if ((r62 & 8) == 0) goto L12;
            r52 = -r52;
        L12:
            r4 = Util.constrainValue(r4 + r52, -32768, 32767);
            r112 = r112 + (r02 * 2);
            r13[r112] = (byte) (r4 & 255);
            r13[r112 + 1] = (byte) (r4 >> 8);
            int r23 = r22 + INDEX_TABLE[r62];
            int[] r53 = STEP_TABLE;
            r22 = Util.constrainValue(r23, 0, r53.length - 1);
            r5 = r53[r22];
            r122 = r122 + 1;
            goto L4
        L8:
            r62 = r7 >> 4;
            goto L9
        }

        private int numOutputBytesToFrames(int r1) {
            return r1 / (this.wavFormat.numChannels * 2);
        }

        private int numOutputFramesToBytes(int r1) {
            return numOutputFramesToBytes(r1, this.wavFormat.numChannels);
        }

        private static int numOutputFramesToBytes(int r0, int r1) {
            return (r0 * 2) * r1;
        }

        private void writeSampleMetadata(int r12) {
            long r5 = this.startTimeUs + Util.scaleLargeTimestamp(this.outputFrameCount, 1000000, this.wavFormat.frameRateHz);
            int r8 = numOutputFramesToBytes(r12);
            this.trackOutput.sampleMetadata(r5, 1, r8, this.pendingOutputBytes - r8, null);
            this.outputFrameCount += r12;
            this.pendingOutputBytes -= r8;
        }

        @Override // androidx.media3.extractor.wav.WavExtractor.OutputWriter
        public void init(int r8, long r9) {
            WavSeekMap r0 = new WavSeekMap(this.wavFormat, this.framesPerBlock, r8, r9);
            this.extractorOutput.seekMap(r0);
            this.trackOutput.format(this.format);
            this.trackOutput.durationUs(r0.getDurationUs());
        }

        @Override // androidx.media3.extractor.wav.WavExtractor.OutputWriter
        public void reset(long r2) {
            this.pendingInputBytes = 0;
            this.startTimeUs = r2;
            this.pendingOutputBytes = 0;
            this.outputFrameCount = 0;
        }

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0035 -> B:4:0x001b). Please report as a decompilation issue!!! */
        @Override // androidx.media3.extractor.wav.WavExtractor.OutputWriter
        public boolean sampleData(ExtractorInput r7, long r8) {
            int r0 = Util.ceilDivide(this.targetSampleSizeFrames - numOutputBytesToFrames(this.pendingOutputBytes), this.framesPerBlock) * this.wavFormat.blockSize;
            if (r8 != 0) goto L5;
        L4:
            boolean r1 = true;
        L6:
            if (r1 == true) goto L13;
            if (this.pendingInputBytes >= r0) goto L13;
            int r3 = r7.read(this.inputData, this.pendingInputBytes, (int) Math.min(r0 - r3, r8));
            if (r3 == (-1)) goto L4;
            this.pendingInputBytes += r3;
        L13:
            int r72 = this.pendingInputBytes / this.wavFormat.blockSize;
            if (r72 <= 0) goto L18;
            decode(this.inputData, r72, this.decodedData);
            this.pendingInputBytes -= r72 * this.wavFormat.blockSize;
            int r73 = this.decodedData.limit();
            this.trackOutput.sampleData(this.decodedData, r73);
            int r82 = this.pendingOutputBytes + r73;
            this.pendingOutputBytes = r82;
            int r74 = numOutputBytesToFrames(r82);
            int r83 = this.targetSampleSizeFrames;
            if (r74 < r83) goto L18;
            writeSampleMetadata(r83);
        L18:
            if (r1 == false) goto L22;
            int r75 = numOutputBytesToFrames(this.pendingOutputBytes);
            if (r75 <= 0) goto L22;
            writeSampleMetadata(r75);
        L22:
            return r1;
        L5:
            r1 = false;
            goto L6
        }
    }

    public interface OutputWriter {
        void init(int r1, long r2);

        void reset(long r1);

        boolean sampleData(ExtractorInput r1, long r2);
    }

    public static final class PassthroughOutputWriter implements OutputWriter {
        private final ExtractorOutput extractorOutput;
        private final Format format;
        private long outputFrameCount;
        private int pendingOutputBytes;
        private long startTimeUs;
        private final int targetSampleSizeBytes;
        private final TrackOutput trackOutput;
        private final WavFormat wavFormat;

        public PassthroughOutputWriter(ExtractorOutput r3, TrackOutput r4, WavFormat r5, String r6, int r7) {
            this.extractorOutput = r3;
            this.trackOutput = r4;
            this.wavFormat = r5;
            int r32 = (r5.numChannels * r5.bitsPerSample) / 8;
            if (r5.blockSize != r32) goto L7;
            int r42 = r5.frameRateHz;
            int r0 = (r42 * r32) * 8;
            int r33 = Math.max(r32, (r42 * r32) / 10);
            this.targetSampleSizeBytes = r33;
            this.format = new Format.Builder().setContainerMimeType("audio/wav").setSampleMimeType(r6).setAverageBitrate(r0).setPeakBitrate(r0).setMaxInputSize(r33).setChannelCount(r5.numChannels).setSampleRate(r5.frameRateHz).setPcmEncoding(r7).build();
            return;
        L7:
            throw ParserException.createForMalformedContainer("Expected block size: " + r32 + "; got: " + r5.blockSize, null);
        }

        @Override // androidx.media3.extractor.wav.WavExtractor.OutputWriter
        public void init(int r8, long r9) {
            WavSeekMap r0 = new WavSeekMap(this.wavFormat, 1, r8, r9);
            this.extractorOutput.seekMap(r0);
            this.trackOutput.format(this.format);
            this.trackOutput.durationUs(r0.getDurationUs());
        }

        @Override // androidx.media3.extractor.wav.WavExtractor.OutputWriter
        public void reset(long r1) {
            this.startTimeUs = r1;
            this.pendingOutputBytes = 0;
            this.outputFrameCount = 0;
        }

        @Override // androidx.media3.extractor.wav.WavExtractor.OutputWriter
        public boolean sampleData(ExtractorInput r19, long r20) {
            long r1 = r20;
        L4:
            if (r1 <= 0) goto L11;
            if (this.pendingOutputBytes >= this.targetSampleSizeBytes) goto L11;
            int r5 = this.trackOutput.sampleData(r19, (int) Math.min(r8 - r7, r1), true);
            if (r5 == (-1)) goto L9;
            this.pendingOutputBytes += r5;
            r1 = r1 - r5;
            goto L4
        L9:
            r1 = 0;
        L11:
            int r2 = this.wavFormat.blockSize;
            int r3 = this.pendingOutputBytes / r2;
            if (r3 <= 0) goto L14;
            long r12 = this.startTimeUs + Util.scaleLargeTimestamp(this.outputFrameCount, 1000000, r1.frameRateHz);
            int r15 = r3 * r2;
            int r16 = this.pendingOutputBytes - r15;
            this.trackOutput.sampleMetadata(r12, 1, r15, r16, null);
            this.outputFrameCount += r3;
            this.pendingOutputBytes = r16;
        L14:
            if (r1 > 0) goto L16;
            return true;
        L16:
            return false;
        }
    }

    public static /* synthetic */ Extractor[] $r8$lambda$7GxPwZ_jAu5R5Nag4tFi5K4xcK4() {
        return new Extractor[]{new WavExtractor()};
    }

    static {
        FACTORY = new WavExtractor$$ExternalSyntheticLambda0();
    }

    public WavExtractor() {
        this.state = 0;
        this.rf64SampleDataSize = -1;
        this.dataStartPosition = -1;
        this.dataEndPosition = -1;
    }

    @EnsuresNonNull({"extractorOutput", "trackOutput"})
    private void assertInitialized() {
        Assertions.checkStateNotNull(this.trackOutput);
        Util.castNonNull(this.extractorOutput);
    }

    private void readFileType(ExtractorInput r7) {
        if (r7.getPosition() != 0) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        int r02 = this.dataStartPosition;
        if (r02 == (-1)) goto L11;
        r7.skipFully(r02);
        this.state = 4;
        return;
    L11:
        if (WavHeaderReader.checkFileType(r7) == false) goto L15;
        r7.skipFully((int) (r7.getPeekPosition() - r7.getPosition()));
        this.state = 1;
        return;
    L15:
        throw ParserException.createForMalformedContainer("Unsupported or unrecognized wav file type.", null);
    L5:
        r0 = false;
        goto L6
    }

    @RequiresNonNull({"extractorOutput", "trackOutput"})
    private void readFormat(ExtractorInput r7) {
        WavFormat r3 = WavHeaderReader.readFormat(r7);
        int r72 = r3.formatType;
        if (r72 != 17) goto L6;
        this.outputWriter = new ImaAdPcmOutputWriter(this.extractorOutput, this.trackOutput, r3);
    L14:
        this.state = 3;
        return;
    L6:
        if (r72 != 6) goto L9;
        this.outputWriter = new PassthroughOutputWriter(this.extractorOutput, this.trackOutput, r3, "audio/g711-alaw", -1);
        goto L14
    L9:
        if (r72 != 7) goto L11;
        this.outputWriter = new PassthroughOutputWriter(this.extractorOutput, this.trackOutput, r3, "audio/g711-mlaw", -1);
        goto L14
    L11:
        int r5 = WavUtil.getPcmEncodingForType(r72, r3.bitsPerSample);
        if (r5 == 0) goto L17;
        this.outputWriter = new PassthroughOutputWriter(this.extractorOutput, this.trackOutput, r3, "audio/raw", r5);
        goto L14
    L17:
        throw ParserException.createForUnsupportedContainerFeature("Unsupported WAV format type: " + r3.formatType);
    }

    private void readRf64SampleDataSize(ExtractorInput r3) {
        this.rf64SampleDataSize = WavHeaderReader.readRf64SampleDataSize(r3);
        this.state = 2;
    }

    private int readSampleData(ExtractorInput r7) {
        if (this.dataEndPosition == (-1)) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        if (((OutputWriter) Assertions.checkNotNull(this.outputWriter)).sampleData(r7, this.dataEndPosition - r7.getPosition()) == false) goto L10;
        return -1;
    L10:
        return 0;
    L5:
        r0 = false;
        goto L6
    }

    private void skipToSampleData(ExtractorInput r9) {
        Pair<Long, Long> r0 = WavHeaderReader.skipToSampleData(r9);
        this.dataStartPosition = ((Long) r0.first).intValue();
        long r02 = ((Long) r0.second).longValue();
        long r2 = this.rf64SampleDataSize;
        if (r2 != (-1)) goto L5;
    L7:
        this.dataEndPosition = this.dataStartPosition + r02;
        long r03 = r9.getLength();
        if (r03 != (-1)) goto L10;
    L12:
        ((OutputWriter) Assertions.checkNotNull(this.outputWriter)).init(this.dataStartPosition, this.dataEndPosition);
        this.state = 4;
        return;
    L10:
        if (this.dataEndPosition <= r03) goto L12;
        Log.w("WavExtractor", "Data exceeds input length: " + this.dataEndPosition + ", " + r03);
        this.dataEndPosition = r03;
        goto L12
    L5:
        if (r02 != 4294967295L) goto L7;
        r02 = r2;
        goto L7
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput r3) {
        this.extractorOutput = r3;
        this.trackOutput = r3.track(0, 1);
        r3.endTracks();
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput r3, PositionHolder r4) {
        assertInitialized();
        int r42 = this.state;
        if (r42 != 0) goto L5;
        readFileType(r3);
        return 0;
    L5:
        if (r42 != 1) goto L7;
        readRf64SampleDataSize(r3);
        return 0;
    L7:
        if (r42 != 2) goto L9;
        readFormat(r3);
        return 0;
    L9:
        if (r42 != 3) goto L11;
        skipToSampleData(r3);
        return 0;
    L11:
        if (r42 == 4) goto L13;
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return 0;
    L13:
        return readSampleData(r3);
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long r3, long r5) {
        if (r3 != 0) goto L5;
        int r32 = 0;
    L6:
        this.state = r32;
        OutputWriter r2 = this.outputWriter;
        if (r2 == null) goto L10;
        r2.reset(r5);
        return;
    L10:
        return;
    L5:
        r32 = 4;
        goto L6
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput r1) {
        return WavHeaderReader.checkFileType(r1);
    }
}
