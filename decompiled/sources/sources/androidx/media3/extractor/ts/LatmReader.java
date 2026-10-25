package androidx.media3.extractor.ts;

import androidx.media3.common.Format;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import com.android.dx.io.Opcodes;
import java.util.Collections;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.MediaController;

/* loaded from: classes4.dex */
public final class LatmReader implements ElementaryStreamReader {
    private int audioMuxVersionA;
    private int bytesRead;
    private int channelCount;
    private String codecs;
    private final String containerMimeType;
    private Format format;
    private String formatId;
    private int frameLengthType;
    private final String language;
    private int numSubframes;
    private long otherDataLenBits;
    private boolean otherDataPresent;
    private TrackOutput output;
    private final int roleFlags;
    private final ParsableBitArray sampleBitArray;
    private final ParsableByteArray sampleDataBuffer;
    private long sampleDurationUs;
    private int sampleRateHz;
    private int sampleSize;
    private int secondHeaderByte;
    private int state;
    private boolean streamMuxRead;
    private long timeUs;

    public LatmReader(String r1, int r2, String r3) {
        this.language = r1;
        this.roleFlags = r2;
        this.containerMimeType = r3;
        ParsableByteArray r12 = new ParsableByteArray(1024);
        this.sampleDataBuffer = r12;
        this.sampleBitArray = new ParsableBitArray(r12.getData());
        this.timeUs = -9223372036854775807L;
    }

    private static long latmGetValue(ParsableBitArray r2) {
        return r2.readBits((r2.readBits(2) + 1) * 8);
    }

    @RequiresNonNull({"output"})
    private void parseAudioMuxElement(ParsableBitArray r3) {
        if (r3.readBit() == true) goto L6;
        this.streamMuxRead = true;
        parseStreamMuxConfig(r3);
    L9:
        if (this.audioMuxVersionA != 0) goto L19;
        if (this.numSubframes != 0) goto L17;
        parsePayloadMux(r3, parsePayloadLengthInfo(r3));
        if (this.otherDataPresent == false) goto L20;
        r3.skipBits((int) this.otherDataLenBits);
        return;
    L20:
        return;
    L17:
        throw ParserException.createForMalformedContainer(null, null);
    L19:
        throw ParserException.createForMalformedContainer(null, null);
    L6:
        if (this.streamMuxRead == true) goto L9;
    }

    private int parseAudioSpecificConfig(ParsableBitArray r4) {
        int r0 = r4.bitsLeft();
        AacUtil.Config r1 = AacUtil.parseAudioSpecificConfig(r4, true);
        this.codecs = r1.codecs;
        this.sampleRateHz = r1.sampleRateHz;
        this.channelCount = r1.channelCount;
        return r0 - r4.bitsLeft();
    }

    private void parseFrameLength(ParsableBitArray r4) {
        int r1 = r4.readBits(3);
        this.frameLengthType = r1;
        if (r1 != 0) goto L5;
        r4.skipBits(8);
        return;
    L5:
        if (r1 != 1) goto L7;
        r4.skipBits(9);
        return;
    L7:
        if (r1 != 3) goto L9;
    L20:
        r4.skipBits(6);
        return;
    L9:
        if (r1 == 4) goto L20;
        if (r1 == 5) goto L20;
        if (r1 != 6) goto L14;
    L18:
        r4.skipBits(1);
        return;
    L14:
        if (r1 == 7) goto L18;
        MethodWriter$$ExternalSyntheticBUOutline0.m();
    }

    private int parsePayloadLengthInfo(ParsableBitArray r3) {
        if (this.frameLengthType != 0) goto L9;
        int r2 = 0;
    L5:
        int r0 = r3.readBits(8);
        r2 = r2 + r0;
        if (r0 == 255) goto L5;
        return r2;
    L9:
        throw ParserException.createForMalformedContainer(null, null);
    }

    @RequiresNonNull({"output"})
    private void parsePayloadMux(ParsableBitArray r12, int r13) {
        int r0 = r12.getPosition();
        int r1 = r0 & 7;
        ParsableByteArray r2 = this.sampleDataBuffer;
        boolean r3 = false;
        if (r1 != 0) goto L5;
        r2.setPosition(r0 >> 3);
    L6:
        this.output.sampleData(this.sampleDataBuffer, r13);
        if (this.timeUs == (-9223372036854775807L)) goto L9;
        r3 = true;
    L9:
        Assertions.checkState(r3);
        this.output.sampleMetadata(this.timeUs, 1, r13, 0, null);
        this.timeUs += this.sampleDurationUs;
        return;
    L5:
        r12.readBits(r2.getData(), 0, r13 * 8);
        this.sampleDataBuffer.setPosition(0);
        goto L6
    }

    @RequiresNonNull({"output"})
    private void parseStreamMuxConfig(ParsableBitArray r9) {
        int r1 = r9.readBits(1);
        if (r1 != 1) goto L5;
        int r3 = r9.readBits(1);
    L6:
        this.audioMuxVersionA = r3;
        if (r3 != 0) goto L36;
        if (r1 != 1) goto L11;
        latmGetValue(r9);
    L11:
        if (r9.readBit() == false) goto L34;
        this.numSubframes = r9.readBits(6);
        int r32 = r9.readBits(4);
        int r5 = r9.readBits(3);
        if (r32 != 0) goto L32;
        if (r5 != 0) goto L32;
        if (r1 != 0) goto L20;
        int r4 = r9.getPosition();
        int r52 = parseAudioSpecificConfig(r9);
        r9.setPosition(r4);
        byte[] r42 = new byte[(r52 + 7) / 8];
        r9.readBits(r42, 0, r52);
        Format r2 = new Format.Builder().setId(this.formatId).setContainerMimeType(this.containerMimeType).setSampleMimeType(MediaController.AUDIO_MIME_TYPE).setCodecs(this.codecs).setChannelCount(this.channelCount).setSampleRate(this.sampleRateHz).setInitializationData(Collections.singletonList(r42)).setLanguage(this.language).setRoleFlags(this.roleFlags).build();
        if (r2.equals(this.format) == true) goto L21;
        this.format = r2;
        this.sampleDurationUs = 1024000000 / r2.sampleRate;
        this.output.format(r2);
    L21:
        parseFrameLength(r9);
        boolean r22 = r9.readBit();
        this.otherDataPresent = r22;
        this.otherDataLenBits = 0;
        if (r22 == false) goto L28;
        if (r1 != 1) goto L25;
        this.otherDataLenBits = latmGetValue(r9);
    L25:
        boolean r0 = r9.readBit();
        this.otherDataLenBits = (this.otherDataLenBits << 8) + r9.readBits(8);
        if (r0 == true) goto L25;
    L28:
        if (r9.readBit() == false) goto L39;
        r9.skipBits(8);
        return;
    L39:
        return;
    L20:
        r9.skipBits(((int) latmGetValue(r9)) - parseAudioSpecificConfig(r9));
    L32:
        throw ParserException.createForMalformedContainer(null, null);
    L34:
        throw ParserException.createForMalformedContainer(null, null);
    L36:
        throw ParserException.createForMalformedContainer(null, null);
    L5:
        r3 = 0;
        goto L6
    }

    private void resetBufferForSize(int r2) {
        this.sampleDataBuffer.reset(r2);
        this.sampleBitArray.reset(this.sampleDataBuffer.getData());
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray r7) {
        Assertions.checkStateNotNull(this.output);
    L4:
        if (r7.bytesLeft() <= 0) goto L29;
        int r0 = this.state;
        if (r0 != 0) goto L8;
        if (r7.readUnsignedByte() != 86) goto L4;
        this.state = 1;
        goto L4
    L8:
        if (r0 != 1) goto L10;
        int r02 = r7.readUnsignedByte();
        if ((r02 & Opcodes.SHL_INT_LIT8) == 224) goto L23;
        if (r02 == 86) goto L4;
        this.state = 0;
        goto L4
    L23:
        this.secondHeaderByte = r02;
        this.state = 2;
        goto L4
    L10:
        if (r0 != 2) goto L11;
        int r03 = ((this.secondHeaderByte & (-225)) << 8) | r7.readUnsignedByte();
        this.sampleSize = r03;
        if (r03 <= this.sampleDataBuffer.getData().length) goto L20;
        resetBufferForSize(this.sampleSize);
    L20:
        this.bytesRead = 0;
        this.state = 3;
        goto L4
    L11:
        if (r0 != 3) goto L15;
        int r04 = Math.min(r7.bytesLeft(), this.sampleSize - this.bytesRead);
        r7.readBytes(this.sampleBitArray.data, this.bytesRead, r04);
        int r1 = this.bytesRead + r04;
        this.bytesRead = r1;
        if (r1 != this.sampleSize) goto L4;
        this.sampleBitArray.setPosition(0);
        parseAudioMuxElement(this.sampleBitArray);
        this.state = 0;
        goto L4
    L15:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput r3, TsPayloadReader.TrackIdGenerator r4) {
        r4.generateNewId();
        this.output = r3.track(r4.getTrackId(), 1);
        this.formatId = r4.getFormatId();
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
        this.state = 0;
        this.timeUs = -9223372036854775807L;
        this.streamMuxRead = false;
    }
}
