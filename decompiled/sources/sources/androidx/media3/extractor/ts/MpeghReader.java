package androidx.media3.extractor.ts;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.MpeghUtil;
import androidx.media3.extractor.ts.TsPayloadReader;
import com.google.common.collect.ImmutableList;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class MpeghReader implements ElementaryStreamReader {
    private boolean configFound;
    private final String containerMimeType;
    private boolean dataPending;
    private final ParsableByteArray dataScratchBytes;
    private int flags;
    private String formatId;
    private int frameBytes;
    private MpeghUtil.MhasPacketHeader header;
    private boolean headerDataFinished;
    private final ParsableBitArray headerScratchBits;
    private final ParsableByteArray headerScratchBytes;
    private long mainStreamLabel;
    private TrackOutput output;
    private int payloadBytesRead;
    private boolean rapPending;
    private int samplingRate;
    private int standardFrameLength;
    private int state;
    private int syncBytes;
    private double timeUs;
    private double timeUsPending;
    private int truncationSamples;

    public MpeghReader(String r3) {
        this.containerMimeType = r3;
        this.state = 0;
        this.headerScratchBytes = new ParsableByteArray(new byte[15], 2);
        this.headerScratchBits = new ParsableBitArray();
        this.dataScratchBytes = new ParsableByteArray();
        this.header = new MpeghUtil.MhasPacketHeader();
        this.samplingRate = -2147483647;
        this.standardFrameLength = -1;
        this.mainStreamLabel = -1;
        this.rapPending = true;
        this.headerDataFinished = true;
        this.timeUs = -9.223372036854776E18d;
        this.timeUsPending = -9.223372036854776E18d;
    }

    private void copyData(ParsableByteArray r4, ParsableByteArray r5, boolean r6) {
        int r3 = r4.getPosition();
        int r0 = Math.min(r4.bytesLeft(), r5.bytesLeft());
        r4.readBytes(r5.getData(), r5.getPosition(), r0);
        r5.skipBytes(r0);
        if (r6 == false) goto L6;
        r4.setPosition(r3);
        return;
    }

    @RequiresNonNull({"output"})
    private void finalizeFrame() {
        if (this.configFound == false) goto L5;
        this.rapPending = false;
        int r5 = 1;
    L6:
        double r2 = ((this.standardFrameLength - this.truncationSamples) * 1000000.0d) / this.samplingRate;
        long r6 = Math.round(this.timeUs);
        if (this.dataPending == false) goto L9;
        this.dataPending = false;
        this.timeUs = this.timeUsPending;
    L10:
        this.output.sampleMetadata(r6, r5, this.frameBytes, 0, null);
        this.configFound = false;
        this.truncationSamples = 0;
        this.frameBytes = 0;
        return;
    L9:
        this.timeUs += r2;
        goto L10
    L5:
        r5 = 0;
        goto L6
    }

    @RequiresNonNull({"output"})
    private void parseConfig(ParsableBitArray r5) {
        MpeghUtil.Mpegh3daConfig r52 = MpeghUtil.parseMpegh3daConfig(r5);
        this.samplingRate = r52.samplingFrequency;
        this.standardFrameLength = r52.standardFrameLength;
        long r0 = this.mainStreamLabel;
        long r2 = this.header.packetLabel;
        if (r0 == r2) goto L14;
        this.mainStreamLabel = r2;
        int r02 = r52.profileLevelIndication;
        String r22 = "mhm1";
        if (r02 == (-1)) goto L7;
        r22 = "mhm1".concat(String.format(".%02X", new Object[]{Integer.valueOf(r02)}));
    L7:
        byte[] r53 = r52.compatibleProfileLevelSet;
        if (r53 != null) goto L10;
    L12:
        ImmutableList r54 = null;
    L13:
        Format r55 = new Format.Builder().setId(this.formatId).setContainerMimeType(this.containerMimeType).setSampleMimeType("audio/mhm1").setSampleRate(this.samplingRate).setCodecs(r22).setInitializationData(r54).build();
        this.output.format(r55);
        goto L14
    L10:
        if (r53.length <= 0) goto L12;
        r54 = ImmutableList.of(Util.EMPTY_BYTE_ARRAY, r53);
    L14:
        this.configFound = true;
    }

    private boolean parseHeader() {
        int r0 = this.headerScratchBytes.limit();
        this.headerScratchBits.reset(this.headerScratchBytes.getData(), r0);
        boolean r1 = MpeghUtil.parseMhasPacketHeader(this.headerScratchBits, this.header);
        if (r1 == false) goto L5;
        this.payloadBytesRead = 0;
        this.frameBytes += this.header.packetLength + r0;
    L5:
        return r1;
    }

    private boolean shouldParsePacket(int r2) {
        if (r2 != 1) goto L5;
        return true;
    L5:
        if (r2 == 17) goto L10;
        return false;
    L10:
        return true;
    }

    private boolean skipToNextSync(ParsableByteArray r5) {
        int r0 = this.flags;
        if ((r0 & 2) != 0) goto L7;
        r5.setPosition(r5.limit());
        return false;
    L7:
        if ((r0 & 4) == 0) goto L9;
        return true;
    L9:
        if (r5.bytesLeft() <= 0) goto L14;
        int r02 = this.syncBytes << 8;
        this.syncBytes = r02;
        int r03 = r02 | r5.readUnsignedByte();
        this.syncBytes = r03;
        if (MpeghUtil.isSyncWord(r03) == false) goto L9;
        r5.setPosition(r5.getPosition() - 3);
        this.syncBytes = 0;
        return true;
    L14:
        return false;
    }

    @RequiresNonNull({"output"})
    private void writeSampleData(ParsableByteArray r4) {
        int r0 = Math.min(r4.bytesLeft(), this.header.packetLength - this.payloadBytesRead);
        this.output.sampleData(r4, r0);
        this.payloadBytesRead += r0;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray r6) {
        Assertions.checkStateNotNull(this.output);
    L4:
        if (r6.bytesLeft() <= 0) goto L38;
        int r0 = this.state;
        if (r0 != 0) goto L8;
        if (skipToNextSync(r6) == false) goto L4;
        this.state = 1;
        goto L4
    L8:
        if (r0 != 1) goto L9;
        copyData(r6, this.headerScratchBytes, false);
        if (this.headerScratchBytes.bytesLeft() == 0) goto L28;
        this.headerDataFinished = false;
        goto L4
    L28:
        boolean r02 = parseHeader();
        ParsableByteArray r4 = this.headerScratchBytes;
        if (r02 == true) goto L30;
        if (r4.limit() >= 15) goto L4;
        ParsableByteArray r03 = this.headerScratchBytes;
        r03.setLimit(r03.limit() + 1);
        this.headerDataFinished = false;
        goto L4
    L30:
        r4.setPosition(0);
        TrackOutput r04 = this.output;
        ParsableByteArray r3 = this.headerScratchBytes;
        r04.sampleData(r3, r3.limit());
        this.headerScratchBytes.reset(2);
        this.dataScratchBytes.reset(this.header.packetLength);
        this.headerDataFinished = true;
        this.state = 2;
        goto L4
    L9:
        if (r0 != 2) goto L24;
        if (shouldParsePacket(this.header.packetType) == false) goto L13;
        copyData(r6, this.dataScratchBytes, true);
    L13:
        writeSampleData(r6);
        int r05 = this.payloadBytesRead;
        MpeghUtil.MhasPacketHeader r32 = this.header;
        if (r05 != r32.packetLength) goto L4;
        int r06 = r32.packetType;
        if (r06 != 1) goto L19;
        parseConfig(new ParsableBitArray(this.dataScratchBytes.getData()));
    L23:
        this.state = 1;
        goto L4
    L19:
        if (r06 != 17) goto L21;
        this.truncationSamples = MpeghUtil.parseAudioTruncationInfo(new ParsableBitArray(this.dataScratchBytes.getData()));
        goto L23
    L21:
        if (r06 != 2) goto L23;
        finalizeFrame();
        goto L23
    L24:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput r2, TsPayloadReader.TrackIdGenerator r3) {
        r3.generateNewId();
        this.formatId = r3.getFormatId();
        this.output = r2.track(r3.getTrackId(), 1);
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetFinished(boolean r1) {
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetStarted(long r3, int r5) {
        this.flags = r5;
        if (this.rapPending == true) goto L10;
        if (this.frameBytes == 0) goto L7;
    L8:
        this.dataPending = true;
        goto L10
    L7:
        if (this.headerDataFinished == false) goto L8;
    L10:
        if (r3 != (-9223372036854775807L)) goto L12;
        return;
    L12:
        if (this.dataPending == false) goto L15;
        this.timeUsPending = r3;
        return;
    L15:
        this.timeUs = r3;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void seek() {
        this.state = 0;
        this.syncBytes = 0;
        this.headerScratchBytes.reset(2);
        this.payloadBytesRead = 0;
        this.frameBytes = 0;
        this.samplingRate = -2147483647;
        this.standardFrameLength = -1;
        this.truncationSamples = 0;
        this.mainStreamLabel = -1;
        this.configFound = false;
        this.dataPending = false;
        this.headerDataFinished = true;
        this.rapPending = true;
        this.timeUs = -9.223372036854776E18d;
        this.timeUsPending = -9.223372036854776E18d;
    }
}
