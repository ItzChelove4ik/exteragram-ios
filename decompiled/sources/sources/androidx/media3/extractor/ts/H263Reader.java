package androidx.media3.extractor.ts;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import java.util.Arrays;
import java.util.Collections;
import kotlin.UByte;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class H263Reader implements ElementaryStreamReader {
    private static final float[] PIXEL_WIDTH_HEIGHT_RATIO_BY_ASPECT_RATIO_INFO = null;
    private final String containerMimeType;
    private final CsdBuffer csdBuffer;
    private String formatId;
    private boolean hasOutputFormat;
    private TrackOutput output;
    private long pesTimeUs;
    private final boolean[] prefixFlags;
    private SampleReader sampleReader;
    private long totalBytesWritten;
    private final NalUnitTargetBuffer userData;
    private final ParsableByteArray userDataParsable;
    private final UserDataReader userDataReader;

    public static final class CsdBuffer {
        private static final byte[] START_CODE = null;
        public byte[] data;
        private boolean isFilling;
        public int length;
        private int state;
        public int volStartPosition;

        static {
            START_CODE = new byte[]{0, 0, 1};
        }

        public CsdBuffer(int r1) {
            this.data = new byte[r1];
        }

        public void onData(byte[] r5, int r6, int r7) {
            if (this.isFilling == true) goto L5;
            return;
        L5:
            int r72 = r7 - r6;
            byte[] r0 = this.data;
            int r1 = r0.length;
            int r2 = this.length;
            if (r1 >= (r2 + r72)) goto L8;
            this.data = Arrays.copyOf(r0, (r2 + r72) * 2);
        L8:
            System.arraycopy(r5, r6, this.data, this.length, r72);
            this.length += r72;
        }

        public boolean onStartCode(int r9, int r10) {
            int r0 = this.state;
            if (r0 == 0) goto L30;
            if (r0 != 1) goto L7;
            if (r9 == 181) goto L28;
            Log.w("H263Reader", "Unexpected start code value");
            reset();
        L32:
            byte[] r92 = START_CODE;
            onData(r92, 0, r92.length);
            return false;
        L28:
            this.state = 2;
            goto L32
        L7:
            if (r0 == 2) goto L23;
            if (r0 == 3) goto L19;
            if (r0 == 4) goto L12;
            MethodWriter$$ExternalSyntheticBUOutline0.m();
            return false;
        L12:
            if (r9 == 179) goto L14;
            if (r9 != 181) goto L32;
        L14:
            this.length -= r10;
            this.isFilling = false;
            return true;
        L19:
            if ((r9 & 240) == 32) goto L21;
            Log.w("H263Reader", "Unexpected start code value");
            reset();
            goto L32
        L21:
            this.volStartPosition = this.length;
            this.state = 4;
            goto L32
        L23:
            if (r9 <= 31) goto L25;
            Log.w("H263Reader", "Unexpected start code value");
            reset();
            goto L32
        L25:
            this.state = 3;
            goto L32
        L30:
            if (r9 != 176) goto L32;
            this.state = 1;
            this.isFilling = true;
            goto L32
        }

        public void reset() {
            this.isFilling = false;
            this.length = 0;
            this.state = 0;
        }
    }

    public static final class SampleReader {
        private boolean lookingForVopCodingType;
        private final TrackOutput output;
        private boolean readingSample;
        private boolean sampleIsKeyframe;
        private long samplePosition;
        private long sampleTimeUs;
        private int startCodeValue;
        private int vopBytesRead;

        public SampleReader(TrackOutput r1) {
            this.output = r1;
        }

        public void onData(byte[] r3, int r4, int r5) {
            if (this.lookingForVopCodingType == false) goto L14;
            int r1 = this.vopBytesRead;
            int r0 = (r4 + 1) - r1;
            if (r0 < r5) goto L7;
            this.vopBytesRead = r1 + (r5 - r4);
            return;
        L7:
            if (((r3[r0] & 192) >> 6) != 0) goto L9;
            boolean r32 = true;
        L10:
            this.sampleIsKeyframe = r32;
            this.lookingForVopCodingType = false;
            return;
        L9:
            r32 = false;
            goto L10
        }

        public void onDataEnd(long r10, int r12, boolean r13) {
            if (this.sampleTimeUs == (-9223372036854775807L)) goto L5;
            boolean r0 = true;
        L6:
            Assertions.checkState(r0);
            if (this.startCodeValue != 182) goto L13;
            if (r13 == false) goto L13;
            if (this.readingSample == false) goto L13;
            int r6 = (int) (r10 - this.samplePosition);
            boolean r5 = this.sampleIsKeyframe;
            this.output.sampleMetadata(this.sampleTimeUs, r5 ? 1 : 0, r6, r12, null);
        L13:
            if (this.startCodeValue == 179) goto L16;
            this.samplePosition = r10;
            return;
        L16:
            return;
        L5:
            r0 = false;
            goto L6
        }

        public void onStartCode(int r5, long r6) {
            this.startCodeValue = r5;
            this.sampleIsKeyframe = false;
            boolean r1 = true;
            if (r5 != 182) goto L5;
        L8:
            boolean r3 = true;
        L9:
            this.readingSample = r3;
            if (r5 == 182) goto L13;
            r1 = false;
        L13:
            this.lookingForVopCodingType = r1;
            this.vopBytesRead = 0;
            this.sampleTimeUs = r6;
            return;
        L5:
            if (r5 == 179) goto L8;
            r3 = false;
            goto L9
        }

        public void reset() {
            this.readingSample = false;
            this.lookingForVopCodingType = false;
            this.sampleIsKeyframe = false;
            this.startCodeValue = -1;
        }
    }

    static {
        PIXEL_WIDTH_HEIGHT_RATIO_BY_ASPECT_RATIO_INFO = new float[]{1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 1.0f};
    }

    public H263Reader(UserDataReader r4, String r5) {
        this.userDataReader = r4;
        this.containerMimeType = r5;
        this.prefixFlags = new boolean[4];
        this.csdBuffer = new CsdBuffer(128);
        this.pesTimeUs = -9223372036854775807L;
        if (r4 == null) goto L6;
        this.userData = new NalUnitTargetBuffer(178, 128);
        this.userDataParsable = new ParsableByteArray();
        return;
    L6:
        this.userData = null;
        this.userDataParsable = null;
    }

    private static Format parseCsdBuffer(CsdBuffer r8, int r9, String r10, String r11) {
        byte[] r82 = Arrays.copyOf(r8.data, r8.length);
        ParsableBitArray r0 = new ParsableBitArray(r82);
        r0.skipBytes(r9);
        r0.skipBytes(4);
        r0.skipBit();
        r0.skipBits(8);
        if (r0.readBit() == false) goto L5;
        r0.skipBits(4);
        r0.skipBits(3);
    L5:
        int r92 = r0.readBits(4);
        float r2 = 1.0f;
        if (r92 != 15) goto L11;
        int r93 = r0.readBits(8);
        int r1 = r0.readBits(8);
        if (r1 != 0) goto L10;
        Log.w("H263Reader", "Invalid aspect ratio");
    L16:
        if (r0.readBit() == false) goto L21;
        r0.skipBits(2);
        r0.skipBits(1);
        if (r0.readBit() == false) goto L21;
        r0.skipBits(15);
        r0.skipBit();
        r0.skipBits(15);
        r0.skipBit();
        r0.skipBits(15);
        r0.skipBit();
        r0.skipBits(3);
        r0.skipBits(11);
        r0.skipBit();
        r0.skipBits(15);
        r0.skipBit();
    L21:
        if (r0.readBits(2) == 0) goto L23;
        Log.w("H263Reader", "Unhandled video object layer shape");
    L23:
        r0.skipBit();
        int r94 = r0.readBits(16);
        r0.skipBit();
        if (r0.readBit() == false) goto L31;
        if (r94 != 0) goto L27;
        Log.w("H263Reader", "Invalid vop_increment_time_resolution");
        goto L31
    L27:
        int r95 = r94 - 1;
        int r12 = 0;
    L28:
        if (r95 <= 0) goto L30;
        r12 = r12 + 1;
        r95 = r95 >> 1;
        goto L28
    L30:
        r0.skipBits(r12);
    L31:
        r0.skipBit();
        int r13 = r0.readBits(13);
        r0.skipBit();
        int r96 = r0.readBits(13);
        r0.skipBit();
        r0.skipBit();
        return new Format.Builder().setId(r10).setContainerMimeType(r11).setSampleMimeType("video/mp4v-es").setWidth(r13).setHeight(r96).setPixelWidthHeightRatio(r2).setInitializationData(Collections.singletonList(r82)).build();
    L10:
        r2 = r93 / r1;
        goto L16
    L11:
        float[] r14 = PIXEL_WIDTH_HEIGHT_RATIO_BY_ASPECT_RATIO_INFO;
        if (r92 >= r14.length) goto L14;
        r2 = r14[r92];
        goto L16
    L14:
        Log.w("H263Reader", "Invalid aspect ratio");
        goto L16
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray r15) {
        Assertions.checkStateNotNull(this.sampleReader);
        Assertions.checkStateNotNull(this.output);
        int r0 = r15.getPosition();
        int r1 = r15.limit();
        byte[] r2 = r15.getData();
        this.totalBytesWritten += r15.bytesLeft();
        this.output.sampleData(r15, r15.bytesLeft());
    L3:
        int r3 = NalUnitUtil.findNalUnit(r2, r0, r1, this.prefixFlags);
        if (r3 == r1) goto L6;
        int r5 = r3 + 3;
        int r4 = r15.getData()[r5] & UByte.MAX_VALUE;
        int r6 = r3 - r0;
        int r9 = 0;
        if (this.hasOutputFormat == true) goto L22;
        if (r6 <= 0) goto L16;
        this.csdBuffer.onData(r2, r0, r3);
    L16:
        if (r6 >= 0) goto L18;
        int r7 = -r6;
    L20:
        if (this.csdBuffer.onStartCode(r4, r7) == false) goto L22;
        TrackOutput r72 = this.output;
        CsdBuffer r10 = this.csdBuffer;
        r72.format(parseCsdBuffer(r10, r10.volStartPosition, (String) Assertions.checkNotNull(this.formatId), this.containerMimeType));
        this.hasOutputFormat = true;
        goto L22
    L18:
        r7 = 0;
    L22:
        this.sampleReader.onData(r2, r0, r3);
        NalUnitTargetBuffer r73 = this.userData;
        if (r73 == null) goto L35;
        if (r6 <= 0) goto L26;
        r73.appendToNalUnit(r2, r0, r3);
    L28:
        if (this.userData.endNalUnit(r9) == false) goto L31;
        NalUnitTargetBuffer r02 = this.userData;
        ((ParsableByteArray) Util.castNonNull(this.userDataParsable)).reset(this.userData.nalData, NalUnitUtil.unescapeStream(r02.nalData, r02.nalLength));
        ((UserDataReader) Util.castNonNull(this.userDataReader)).consume(this.pesTimeUs, this.userDataParsable);
    L31:
        if (r4 != 178) goto L35;
        if (r15.getData()[r3 + 2] != 1) goto L35;
        this.userData.startNalUnit(r4);
        goto L35
    L26:
        r9 = -r6;
    L35:
        int r03 = r1 - r3;
        this.sampleReader.onDataEnd(this.totalBytesWritten - r03, r03, this.hasOutputFormat);
        this.sampleReader.onStartCode(r4, this.pesTimeUs);
        r0 = r5;
        goto L3
    L6:
        if (this.hasOutputFormat == true) goto L8;
        this.csdBuffer.onData(r2, r0, r1);
    L8:
        this.sampleReader.onData(r2, r0, r1);
        NalUnitTargetBuffer r14 = this.userData;
        if (r14 == null) goto L41;
        r14.appendToNalUnit(r2, r0, r1);
        return;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput r3, TsPayloadReader.TrackIdGenerator r4) {
        r4.generateNewId();
        this.formatId = r4.getFormatId();
        TrackOutput r0 = r3.track(r4.getTrackId(), 2);
        this.output = r0;
        this.sampleReader = new SampleReader(r0);
        UserDataReader r2 = this.userDataReader;
        if (r2 == null) goto L6;
        r2.createTracks(r3, r4);
        return;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetFinished(boolean r5) {
        Assertions.checkStateNotNull(this.sampleReader);
        if (r5 == false) goto L6;
        this.sampleReader.onDataEnd(this.totalBytesWritten, 0, this.hasOutputFormat);
        this.sampleReader.reset();
        return;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetStarted(long r1, int r3) {
        this.pesTimeUs = r1;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void seek() {
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.csdBuffer.reset();
        SampleReader r0 = this.sampleReader;
        if (r0 == null) goto L5;
        r0.reset();
    L5:
        NalUnitTargetBuffer r02 = this.userData;
        if (r02 == null) goto L8;
        r02.reset();
    L8:
        this.totalBytesWritten = 0;
        this.pesTimeUs = -9223372036854775807L;
    }
}
