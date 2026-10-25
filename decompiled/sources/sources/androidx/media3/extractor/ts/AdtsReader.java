package androidx.media3.extractor.ts;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.DiscardingTrackOutput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import java.util.Arrays;
import java.util.Collections;
import kotlin.UByte;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.MediaController;

/* loaded from: classes4.dex */
public final class AdtsReader implements ElementaryStreamReader {
    private static final byte[] ID3_IDENTIFIER = null;
    private final ParsableBitArray adtsScratch;
    private int bytesRead;
    private final String containerMimeType;
    private int currentFrameVersion;
    private TrackOutput currentOutput;
    private long currentSampleDuration;
    private final boolean exposeId3;
    private int firstFrameSampleRateIndex;
    private int firstFrameVersion;
    private String formatId;
    private boolean foundFirstFrame;
    private boolean hasCrc;
    private boolean hasOutputFormat;
    private final ParsableByteArray id3HeaderBuffer;
    private TrackOutput id3Output;
    private final String language;
    private int matchState;
    private TrackOutput output;
    private final int roleFlags;
    private long sampleDurationUs;
    private int sampleSize;
    private int state;
    private long timeUs;

    static {
        ID3_IDENTIFIER = new byte[]{73, 68, 51};
    }

    public AdtsReader(boolean r3, String r4) {
        this(r3, null, 0, r4);
    }

    public AdtsReader(boolean r4, String r5, int r6, String r7) {
        this.adtsScratch = new ParsableBitArray(new byte[7]);
        this.id3HeaderBuffer = new ParsableByteArray(Arrays.copyOf(ID3_IDENTIFIER, 10));
        this.firstFrameVersion = -1;
        this.firstFrameSampleRateIndex = -1;
        this.sampleDurationUs = -9223372036854775807L;
        this.timeUs = -9223372036854775807L;
        this.exposeId3 = r4;
        this.language = r5;
        this.roleFlags = r6;
        this.containerMimeType = r7;
        setFindingSampleState();
    }

    @EnsuresNonNull({"output", "currentOutput", "id3Output"})
    private void assertTracksCreated() {
        Assertions.checkNotNull(this.output);
        Util.castNonNull(this.currentOutput);
        Util.castNonNull(this.id3Output);
    }

    private void checkAdtsHeader(ParsableByteArray r3) {
        if (r3.bytesLeft() != 0) goto L5;
        return;
    L5:
        this.adtsScratch.data[0] = r3.getData()[r3.getPosition()];
        this.adtsScratch.setPosition(2);
        int r32 = this.adtsScratch.readBits(4);
        int r0 = this.firstFrameSampleRateIndex;
        if (r0 == (-1)) goto L11;
        if (r32 == r0) goto L11;
        resetSync();
        return;
    L11:
        if (this.foundFirstFrame == true) goto L13;
        this.foundFirstFrame = true;
        this.firstFrameVersion = this.currentFrameVersion;
        this.firstFrameSampleRateIndex = r32;
    L13:
        setReadingAdtsHeaderState();
    }

    private boolean checkSyncPositionValid(ParsableByteArray r9, int r10) {
        r9.setPosition(r10 + 1);
        if (tryRead(r9, this.adtsScratch.data, 1) == true) goto L5;
        return false;
    L5:
        this.adtsScratch.setPosition(4);
        int r0 = this.adtsScratch.readBits(1);
        int r4 = this.firstFrameVersion;
        if (r4 == (-1)) goto L10;
        if (r0 == r4) goto L10;
        return false;
    L10:
        if (this.firstFrameSampleRateIndex == (-1)) goto L19;
        if (tryRead(r9, this.adtsScratch.data, 1) == true) goto L14;
        return true;
    L14:
        this.adtsScratch.setPosition(2);
        if (this.adtsScratch.readBits(4) == this.firstFrameSampleRateIndex) goto L17;
        return false;
    L17:
        r9.setPosition(r10 + 2);
    L19:
        if (tryRead(r9, this.adtsScratch.data, 4) == true) goto L21;
        return true;
    L21:
        this.adtsScratch.setPosition(14);
        int r3 = this.adtsScratch.readBits(13);
        if (r3 >= 7) goto L24;
        return false;
    L24:
        byte[] r42 = r9.getData();
        int r92 = r9.limit();
        int r102 = r10 + r3;
        if (r102 < r92) goto L27;
        return true;
    L27:
        byte r32 = r42[r102];
        if (r32 != (-1)) goto L39;
        int r103 = r102 + 1;
        if (r103 != r92) goto L33;
        return true;
    L33:
        if (isAdtsSyncBytes((byte) -1, r42[r103]) == true) goto L35;
    L37:
        return false;
    L35:
        if (((r42[r103] & 8) >> 3) != r0) goto L37;
        return true;
    L39:
        if (r32 == 73) goto L41;
        return false;
    L41:
        int r8 = r102 + 1;
        if (r8 != r92) goto L45;
        return true;
    L45:
        if (r42[r8] == 68) goto L47;
        return false;
    L47:
        int r104 = r102 + 2;
        if (r104 != r92) goto L51;
        return true;
    L51:
        if (r42[r104] != 51) goto L53;
        return true;
    L53:
        return false;
    }

    private boolean continueRead(ParsableByteArray r3, byte[] r4, int r5) {
        int r0 = Math.min(r3.bytesLeft(), r5 - this.bytesRead);
        r3.readBytes(r4, this.bytesRead, r0);
        int r32 = this.bytesRead + r0;
        this.bytesRead = r32;
        if (r32 != r5) goto L6;
        return true;
    L6:
        return false;
    }

    private void findNextSample(ParsableByteArray r10) {
        byte[] r0 = r10.getData();
        int r1 = r10.getPosition();
        int r2 = r10.limit();
    L3:
        if (r1 >= r2) goto L39;
        int r3 = r1 + 1;
        byte r4 = r0[r1];
        int r5 = r4 & UByte.MAX_VALUE;
        if (this.matchState == 512) goto L7;
    L22:
        int r42 = this.matchState;
        int r52 = r5 | r42;
        if (r52 == 329) goto L37;
        if (r52 == 511) goto L36;
        if (r52 == 836) goto L35;
        if (r52 == 1075) goto L33;
        if (r42 != 256) goto L32;
    L38:
        r1 = r3;
        goto L3
    L32:
        this.matchState = 256;
        goto L3
    L33:
        setReadingId3HeaderState();
        r10.setPosition(r3);
        return;
    L35:
        this.matchState = 1024;
        goto L38
    L36:
        this.matchState = 512;
        goto L38
    L37:
        this.matchState = 768;
        goto L38
    L7:
        if (isAdtsSyncBytes((byte) -1, (byte) r5) == false) goto L22;
        if (this.foundFirstFrame == true) goto L12;
        if (checkSyncPositionValid(r10, r1 - 1) == false) goto L22;
    L12:
        this.currentFrameVersion = (r4 & 8) >> 3;
        boolean r02 = true;
        if ((r4 & 1) == 0) goto L16;
        r02 = false;
    L16:
        this.hasCrc = r02;
        if (this.foundFirstFrame == true) goto L19;
        setCheckingAdtsHeaderState();
    L20:
        r10.setPosition(r3);
        return;
    L19:
        setReadingAdtsHeaderState();
        goto L20
    L39:
        r10.setPosition(r1);
    }

    private boolean isAdtsSyncBytes(byte r1, byte r2) {
        return isAdtsSyncWord(((r1 & UByte.MAX_VALUE) << 8) | (r2 & UByte.MAX_VALUE));
    }

    public static boolean isAdtsSyncWord(int r1) {
        if ((r1 & 65526) != 65520) goto L6;
        return true;
    L6:
        return false;
    }

    @RequiresNonNull({"output"})
    private void parseAdtsHeader() {
        this.adtsScratch.setPosition(0);
        boolean r0 = this.hasOutputFormat;
        ParsableBitArray r1 = this.adtsScratch;
        if (r0 == true) goto L9;
        int r02 = 2;
        int r12 = r1.readBits(2) + 1;
        if (r12 == 2) goto L7;
        Log.w("AdtsReader", "Detected audio object type: " + r12 + ", but assuming AAC LC.");
    L8:
        this.adtsScratch.skipBits(5);
        byte[] r03 = AacUtil.buildAudioSpecificConfig(r02, this.firstFrameSampleRateIndex, this.adtsScratch.readBits(3));
        AacUtil.Config r13 = AacUtil.parseAudioSpecificConfig(r03);
        Format r04 = new Format.Builder().setId(this.formatId).setContainerMimeType(this.containerMimeType).setSampleMimeType(MediaController.AUDIO_MIME_TYPE).setCodecs(r13.codecs).setChannelCount(r13.channelCount).setSampleRate(r13.sampleRateHz).setInitializationData(Collections.singletonList(r03)).setLanguage(this.language).setRoleFlags(this.roleFlags).build();
        this.sampleDurationUs = 1024000000 / r04.sampleRate;
        this.output.format(r04);
        this.hasOutputFormat = true;
    L10:
        this.adtsScratch.skipBits(4);
        int r05 = this.adtsScratch.readBits(13);
        int r14 = r05 - 7;
        if (this.hasCrc == false) goto L13;
        r14 = r05 - 9;
    L13:
        setReadingSampleState(this.output, this.sampleDurationUs, 0, r14);
        return;
    L7:
        r02 = r12;
        goto L8
    L9:
        r1.skipBits(10);
        goto L10
    }

    @RequiresNonNull({"id3Output"})
    private void parseId3Header() {
        this.id3Output.sampleData(this.id3HeaderBuffer, 10);
        this.id3HeaderBuffer.setPosition(6);
        setReadingSampleState(this.id3Output, 0, 10, this.id3HeaderBuffer.readSynchSafeInt() + 10);
    }

    @RequiresNonNull({"currentOutput"})
    private void readSample(ParsableByteArray r8) {
        int r0 = Math.min(r8.bytesLeft(), this.sampleSize - this.bytesRead);
        this.currentOutput.sampleData(r8, r0);
        int r82 = this.bytesRead + r0;
        this.bytesRead = r82;
        if (r82 == this.sampleSize) goto L5;
        return;
    L5:
        if (this.timeUs == (-9223372036854775807L)) goto L7;
        boolean r83 = true;
    L8:
        Assertions.checkState(r83);
        this.currentOutput.sampleMetadata(this.timeUs, 1, this.sampleSize, 0, null);
        this.timeUs += this.currentSampleDuration;
        setFindingSampleState();
        return;
    L7:
        r83 = false;
        goto L8
    }

    private void resetSync() {
        this.foundFirstFrame = false;
        setFindingSampleState();
    }

    private void setCheckingAdtsHeaderState() {
        this.state = 1;
        this.bytesRead = 0;
    }

    private void setFindingSampleState() {
        this.state = 0;
        this.bytesRead = 0;
        this.matchState = 256;
    }

    private void setReadingAdtsHeaderState() {
        this.state = 3;
        this.bytesRead = 0;
    }

    private void setReadingId3HeaderState() {
        this.state = 2;
        this.bytesRead = ID3_IDENTIFIER.length;
        this.sampleSize = 0;
        this.id3HeaderBuffer.setPosition(0);
    }

    private void setReadingSampleState(TrackOutput r2, long r3, int r5, int r6) {
        this.state = 4;
        this.bytesRead = r5;
        this.currentOutput = r2;
        this.currentSampleDuration = r3;
        this.sampleSize = r6;
    }

    private boolean tryRead(ParsableByteArray r2, byte[] r3, int r4) {
        if (r2.bytesLeft() >= r4) goto L5;
        return false;
    L5:
        r2.readBytes(r3, 0, r4);
        return true;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray r3) {
        assertTracksCreated();
    L4:
        if (r3.bytesLeft() <= 0) goto L30;
        int r0 = this.state;
        if (r0 != 0) goto L8;
        findNextSample(r3);
        goto L4
    L8:
        if (r0 != 1) goto L10;
        checkAdtsHeader(r3);
        goto L4
    L10:
        if (r0 != 2) goto L12;
        if (continueRead(r3, this.id3HeaderBuffer.getData(), 10) == false) goto L4;
        parseId3Header();
        goto L4
    L12:
        if (r0 != 3) goto L14;
        if (this.hasCrc == false) goto L21;
        int r02 = 7;
    L23:
        if (continueRead(r3, this.adtsScratch.data, r02) == false) goto L4;
        parseAdtsHeader();
        goto L4
    L21:
        r02 = 5;
        goto L23
    L14:
        if (r0 != 4) goto L16;
        readSample(r3);
        goto L4
    L16:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput r3, TsPayloadReader.TrackIdGenerator r4) {
        r4.generateNewId();
        this.formatId = r4.getFormatId();
        TrackOutput r0 = r3.track(r4.getTrackId(), 1);
        this.output = r0;
        this.currentOutput = r0;
        if (this.exposeId3 == false) goto L6;
        r4.generateNewId();
        TrackOutput r32 = r3.track(r4.getTrackId(), 5);
        this.id3Output = r32;
        r32.format(new Format.Builder().setId(r4.getFormatId()).setContainerMimeType(this.containerMimeType).setSampleMimeType("application/id3").build());
        return;
    L6:
        this.id3Output = new DiscardingTrackOutput();
    }

    public long getSampleDurationUs() {
        return this.sampleDurationUs;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetFinished(boolean r1) {
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetStarted(long r1, int r3) {
        this.timeUs = r1;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void seek() {
        this.timeUs = -9223372036854775807L;
        resetSync();
    }
}
