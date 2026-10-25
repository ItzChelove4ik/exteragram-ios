package androidx.media3.extractor.ts;

import android.util.SparseArray;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.ts.TsPayloadReader;
import com.android.dx.io.Opcodes;
import com.google.android.gms.cast.MediaStatus;
import kotlin.UByte;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes4.dex */
public final class PsExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = null;
    private final PsDurationReader durationReader;
    private boolean foundAllTracks;
    private boolean foundAudioTrack;
    private boolean foundVideoTrack;
    private boolean hasOutputSeekMap;
    private long lastTrackPosition;
    private ExtractorOutput output;
    private PsBinarySearchSeeker psBinarySearchSeeker;
    private final ParsableByteArray psPacketBuffer;
    private final SparseArray<PesReader> psPayloadReaders;
    private final TimestampAdjuster timestampAdjuster;

    public static final class PesReader {
        private boolean dtsFlag;
        private int extendedHeaderLength;
        private final ElementaryStreamReader pesPayloadReader;
        private final ParsableBitArray pesScratch;
        private boolean ptsFlag;
        private boolean seenFirstDts;
        private long timeUs;
        private final TimestampAdjuster timestampAdjuster;

        public PesReader(ElementaryStreamReader r1, TimestampAdjuster r2) {
            this.pesPayloadReader = r1;
            this.timestampAdjuster = r2;
            this.pesScratch = new ParsableBitArray(new byte[64]);
        }

        private void parseHeader() {
            this.pesScratch.skipBits(8);
            this.ptsFlag = this.pesScratch.readBit();
            this.dtsFlag = this.pesScratch.readBit();
            this.pesScratch.skipBits(6);
            this.extendedHeaderLength = this.pesScratch.readBits(8);
        }

        private void parseHeaderExtension() {
            this.timeUs = 0;
            if (this.ptsFlag == false) goto L11;
            this.pesScratch.skipBits(4);
            this.pesScratch.skipBits(1);
            this.pesScratch.skipBits(1);
            long r3 = ((this.pesScratch.readBits(3) << 30) | (this.pesScratch.readBits(15) << 15)) | this.pesScratch.readBits(15);
            this.pesScratch.skipBits(1);
            if (this.seenFirstDts == false) goto L7;
        L9:
            this.timeUs = this.timestampAdjuster.adjustTsTimestamp(r3);
            return;
        L7:
            if (this.dtsFlag == false) goto L9;
            this.pesScratch.skipBits(4);
            this.pesScratch.skipBits(1);
            this.pesScratch.skipBits(1);
            this.pesScratch.skipBits(1);
            this.timestampAdjuster.adjustTsTimestamp(((this.pesScratch.readBits(3) << 30) | (this.pesScratch.readBits(15) << 15)) | this.pesScratch.readBits(15));
            this.seenFirstDts = true;
            goto L9
        }

        public void consume(ParsableByteArray r6) {
            r6.readBytes(this.pesScratch.data, 0, 3);
            this.pesScratch.setPosition(0);
            parseHeader();
            r6.readBytes(this.pesScratch.data, 0, this.extendedHeaderLength);
            this.pesScratch.setPosition(0);
            parseHeaderExtension();
            this.pesPayloadReader.packetStarted(this.timeUs, 4);
            this.pesPayloadReader.consume(r6);
            this.pesPayloadReader.packetFinished(false);
        }

        public void seek() {
            this.seenFirstDts = false;
            this.pesPayloadReader.seek();
        }
    }

    /* renamed from: $r8$lambda$14u-ALHsTnXYpDeGysDLBWdrlxM, reason: not valid java name */
    public static /* synthetic */ Extractor[] m862$r8$lambda$14uALHsTnXYpDeGysDLBWdrlxM() {
        return new Extractor[]{new PsExtractor()};
    }

    static {
        FACTORY = new PsExtractor$$ExternalSyntheticLambda0();
    }

    public PsExtractor() {
        this(new TimestampAdjuster(0));
    }

    public PsExtractor(TimestampAdjuster r2) {
        this.timestampAdjuster = r2;
        this.psPacketBuffer = new ParsableByteArray(4096);
        this.psPayloadReaders = new SparseArray();
        this.durationReader = new PsDurationReader();
    }

    @RequiresNonNull({"output"})
    private void maybeOutputSeekMap(long r8) {
        if (this.hasOutputSeekMap == true) goto L10;
        this.hasOutputSeekMap = true;
        if (this.durationReader.getDurationUs() == (-9223372036854775807L)) goto L8;
        PsBinarySearchSeeker r1 = new PsBinarySearchSeeker(this.durationReader.getScrTimestampAdjuster(), this.durationReader.getDurationUs(), r8);
        this.psBinarySearchSeeker = r1;
        this.output.seekMap(r1.getSeekMap());
        return;
    L8:
        this.output.seekMap(new SeekMap.Unseekable(this.durationReader.getDurationUs()));
        return;
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput r1) {
        this.output = r1;
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput r11, PositionHolder r12) {
        Assertions.checkStateNotNull(this.output);
        long r0 = r11.getLength();
        if (r0 != (-1)) goto L5;
    L8:
        maybeOutputSeekMap(r0);
        PsBinarySearchSeeker r5 = this.psBinarySearchSeeker;
        if (r5 != null) goto L11;
    L14:
        r11.resetPeekPosition();
        if (r0 == (-1)) goto L17;
        long r02 = r0 - r11.getPeekPosition();
    L19:
        if (r02 == (-1)) goto L24;
        if (r02 >= 4) goto L24;
        return -1;
    L24:
        if (r11.peekFully(this.psPacketBuffer.getData(), 0, 4, true) == true) goto L26;
        return -1;
    L26:
        this.psPacketBuffer.setPosition(0);
        int r122 = this.psPacketBuffer.readInt();
        if (r122 != 441) goto L30;
        return -1;
    L30:
        if (r122 != 442) goto L34;
        r11.peekFully(this.psPacketBuffer.getData(), 0, 10);
        this.psPacketBuffer.setPosition(9);
        r11.skipFully((this.psPacketBuffer.readUnsignedByte() & 7) + 14);
        return 0;
    L34:
        if (r122 != 443) goto L38;
        r11.peekFully(this.psPacketBuffer.getData(), 0, 2);
        this.psPacketBuffer.setPosition(0);
        r11.skipFully(this.psPacketBuffer.readUnsignedShort() + 6);
        return 0;
    L38:
        if (((r122 & (-256)) >> 8) == 1) goto L41;
        r11.skipFully(1);
        return 0;
    L41:
        int r03 = r122 & 255;
        PesReader r52 = this.psPayloadReaders.get(r03);
        if (this.foundAllTracks == true) goto L65;
        if (r52 != null) goto L57;
        if (r03 != 189) goto L48;
        ElementaryStreamReader r123 = new Ac3Reader("video/mp2p");
        this.foundAudioTrack = true;
        this.lastTrackPosition = r11.getPosition();
    L54:
        if (r123 == null) goto L57;
        r123.createTracks(this.output, new TsPayloadReader.TrackIdGenerator(r03, 256));
        r52 = new PesReader(r123, this.timestampAdjuster);
        this.psPayloadReaders.put(r03, r52);
        goto L57
    L48:
        if ((r122 & Opcodes.SHL_INT_LIT8) != 192) goto L51;
        r123 = new MpegAudioReader("video/mp2p");
        this.foundAudioTrack = true;
        this.lastTrackPosition = r11.getPosition();
        goto L54
    L51:
        if ((r122 & 240) != 224) goto L53;
        r123 = new H262Reader("video/mp2p");
        this.foundVideoTrack = true;
        this.lastTrackPosition = r11.getPosition();
        goto L54
    L53:
        r123 = null;
    L57:
        if (this.foundAudioTrack == true) goto L59;
    L61:
        long r6 = 1048576;
    L63:
        if (r11.getPosition() <= r6) goto L65;
        this.foundAllTracks = true;
        this.output.endTracks();
        goto L65
    L59:
        if (this.foundVideoTrack == false) goto L61;
        r6 = this.lastTrackPosition + MediaStatus.COMMAND_PLAYBACK_RATE;
    L65:
        r11.peekFully(this.psPacketBuffer.getData(), 0, 2);
        this.psPacketBuffer.setPosition(0);
        int r124 = this.psPacketBuffer.readUnsignedShort() + 6;
        if (r52 != null) goto L68;
        r11.skipFully(r124);
    L69:
        return 0;
    L68:
        this.psPacketBuffer.reset(r124);
        r11.readFully(this.psPacketBuffer.getData(), 0, r124);
        this.psPacketBuffer.setPosition(6);
        r52.consume(this.psPacketBuffer);
        ParsableByteArray r10 = this.psPacketBuffer;
        r10.setLimit(r10.capacity());
        goto L69
    L17:
        r02 = -1;
        goto L19
    L11:
        if (r5.isSeeking() == false) goto L14;
        return this.psBinarySearchSeeker.handlePendingSeek(r11, r12);
    L5:
        if (this.durationReader.isDurationReadFinished() == true) goto L8;
        return this.durationReader.readDuration(r11, r12);
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long r6, long r8) {
        int r7 = 0;
        boolean r2 = true;
        if (this.timestampAdjuster.getTimestampOffsetUs() != (-9223372036854775807L)) goto L5;
        boolean r62 = true;
    L6:
        if (r62 == true) goto L16;
        long r3 = this.timestampAdjuster.getFirstSampleTimestampUs();
        if (r3 != (-9223372036854775807L)) goto L10;
    L14:
        r2 = false;
    L15:
        r62 = r2;
        goto L16
    L10:
        if (r3 == 0) goto L14;
        if (r3 == r8) goto L14;
    L16:
        if (r62 == false) goto L18;
        this.timestampAdjuster.reset(r8);
    L18:
        PsBinarySearchSeeker r63 = this.psBinarySearchSeeker;
        if (r63 == null) goto L22;
        r63.setSeekTargetUs(r8);
    L22:
        if (r7 >= this.psPayloadReaders.size()) goto L24;
        this.psPayloadReaders.valueAt(r7).seek();
        r7 = r7 + 1;
        goto L22
    L24:
        return;
    L5:
        r62 = false;
        goto L6
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput r9) {
        byte[] r0 = new byte[14];
        r9.peekFully(r0, 0, 14);
        if (442 == (((((r0[0] & UByte.MAX_VALUE) << 24) | ((r0[1] & UByte.MAX_VALUE) << 16)) | ((r0[2] & UByte.MAX_VALUE) << 8)) | (r0[3] & UByte.MAX_VALUE))) goto L6;
        return false;
    L6:
        if ((r0[4] & 196) == 68) goto L9;
        return false;
    L9:
        if ((r0[6] & 4) == 4) goto L12;
        return false;
    L12:
        if ((r0[8] & 4) == 4) goto L15;
        return false;
    L15:
        if ((r0[9] & 1) == 1) goto L18;
        return false;
    L18:
        if ((r0[12] & 3) == 3) goto L20;
        return false;
    L20:
        r9.advancePeekPosition(r0[13] & 7);
        r9.peekFully(r0, 0, 3);
        if (1 != ((((r0[0] & UByte.MAX_VALUE) << 16) | ((r0[1] & UByte.MAX_VALUE) << 8)) | (r0[2] & UByte.MAX_VALUE))) goto L23;
        return true;
    L23:
        return false;
    }
}
