package androidx.media3.extractor.ts;

import android.util.Pair;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import java.util.Arrays;
import java.util.Collections;
import kotlin.UByte;

/* loaded from: classes4.dex */
public final class H262Reader implements ElementaryStreamReader {
    private static final double[] FRAME_RATE_VALUES = null;
    private final String containerMimeType;
    private final CsdBuffer csdBuffer;
    private String formatId;
    private long frameDurationUs;
    private boolean hasOutputFormat;
    private TrackOutput output;
    private long pesTimeUs;
    private final boolean[] prefixFlags;
    private boolean sampleHasPicture;
    private boolean sampleIsKeyframe;
    private long samplePosition;
    private long sampleTimeUs;
    private boolean startedFirstSample;
    private long totalBytesWritten;
    private final NalUnitTargetBuffer userData;
    private final ParsableByteArray userDataParsable;
    private final UserDataReader userDataReader;

    public static final class CsdBuffer {
        private static final byte[] START_CODE = null;
        public byte[] data;
        private boolean isFilling;
        public int length;
        public int sequenceExtensionPosition;

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

        public boolean onStartCode(int r4, int r5) {
            if (this.isFilling == false) goto L12;
            int r0 = this.length - r5;
            this.length = r0;
            if (this.sequenceExtensionPosition == 0) goto L7;
        L9:
            this.isFilling = false;
            return true;
        L7:
            if (r4 != 181) goto L9;
            this.sequenceExtensionPosition = r0;
        L14:
            byte[] r42 = START_CODE;
            onData(r42, 0, r42.length);
            return false;
        L12:
            if (r4 != 179) goto L14;
            this.isFilling = true;
            goto L14
        }

        public void reset() {
            this.isFilling = false;
            this.length = 0;
            this.sequenceExtensionPosition = 0;
        }
    }

    static {
        FRAME_RATE_VALUES = new double[]{23.976023976023978d, 24.0d, 25.0d, 29.97002997002997d, 30.0d, 50.0d, 59.94005994005994d, 60.0d};
    }

    public H262Reader(UserDataReader r2, String r3) {
        this.userDataReader = r2;
        this.containerMimeType = r3;
        this.prefixFlags = new boolean[4];
        this.csdBuffer = new CsdBuffer(128);
        if (r2 == null) goto L5;
        this.userData = new NalUnitTargetBuffer(178, 128);
        this.userDataParsable = new ParsableByteArray();
    L6:
        this.pesTimeUs = -9223372036854775807L;
        this.sampleTimeUs = -9223372036854775807L;
        return;
    L5:
        this.userData = null;
        this.userDataParsable = null;
        goto L6
    }

    public H262Reader(String r2) {
        this(null, r2);
    }

    private static Pair<Format, Long> parseCsdBuffer(CsdBuffer r8, String r9, String r10) {
        byte[] r0 = Arrays.copyOf(r8.data, r8.length);
        int r2 = r0[4] & UByte.MAX_VALUE;
        byte r4 = r0[5];
        int r22 = (r2 << 4) | ((r4 & UByte.MAX_VALUE) >> 4);
        int r42 = ((r4 & 15) << 8) | (r0[6] & UByte.MAX_VALUE);
        int r6 = (r0[7] & 240) >> 4;
        if (r6 != 2) goto L5;
        float r1 = r42 * 4;
        int r62 = r22 * 3;
    L9:
        float r12 = r1 / r62;
    L12:
        Format r92 = new Format.Builder().setId(r9).setContainerMimeType(r10).setSampleMimeType("video/mpeg2").setWidth(r22).setHeight(r42).setPixelWidthHeightRatio(r12).setInitializationData(Collections.singletonList(r0)).build();
        int r102 = (r0[7] & 15) - 1;
        if (r102 < 0) goto L20;
        double[] r13 = FRAME_RATE_VALUES;
        if (r102 >= r13.length) goto L20;
        double r43 = r13[r102];
        byte r82 = r0[r8.sequenceExtensionPosition + 9];
        int r103 = (r82 & 96) >> 5;
        if (r103 == (r82 & 31)) goto L19;
        r43 = r43 * ((r103 + 1.0d) / (r8 + 1));
    L19:
        long r02 = (long) (1000000.0d / r43);
    L22:
        return Pair.create(r92, Long.valueOf(r02));
    L20:
        r02 = 0;
        goto L22
    L5:
        if (r6 == 3) goto L10;
        if (r6 == 4) goto L8;
        r12 = 1.0f;
        goto L12
    L8:
        r1 = r42 * 121;
        r62 = r22 * 100;
        goto L9
    L10:
        r1 = r42 * 16;
        r62 = r22 * 9;
        goto L9
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray r20) {
        Assertions.checkStateNotNull(this.output);
        int r1 = r20.getPosition();
        int r2 = r20.limit();
        byte[] r3 = r20.getData();
        this.totalBytesWritten += r20.bytesLeft();
        this.output.sampleData(r20, r20.bytesLeft());
    L3:
        int r4 = NalUnitUtil.findNalUnit(r3, r1, r2, this.prefixFlags);
        if (r4 == r2) goto L6;
        int r7 = r4 + 3;
        int r5 = r20.getData()[r7] & UByte.MAX_VALUE;
        int r8 = r4 - r1;
        boolean r10 = false;
        if (this.hasOutputFormat == true) goto L22;
        if (r8 <= 0) goto L16;
        this.csdBuffer.onData(r3, r1, r4);
    L16:
        if (r8 >= 0) goto L18;
        int r9 = -r8;
    L20:
        if (this.csdBuffer.onStartCode(r5, r9) == false) goto L22;
        Pair<Format, Long> r92 = parseCsdBuffer(this.csdBuffer, (String) Assertions.checkNotNull(this.formatId), this.containerMimeType);
        this.output.format((Format) r92.first);
        this.frameDurationUs = ((Long) r92.second).longValue();
        this.hasOutputFormat = true;
        goto L22
    L18:
        r9 = 0;
    L22:
        NalUnitTargetBuffer r93 = this.userData;
        if (r93 == null) goto L35;
        if (r8 <= 0) goto L26;
        r93.appendToNalUnit(r3, r1, r4);
        int r12 = 0;
    L28:
        if (this.userData.endNalUnit(r12) == false) goto L31;
        NalUnitTargetBuffer r13 = this.userData;
        ((ParsableByteArray) Util.castNonNull(this.userDataParsable)).reset(this.userData.nalData, NalUnitUtil.unescapeStream(r13.nalData, r13.nalLength));
        ((UserDataReader) Util.castNonNull(this.userDataReader)).consume(this.sampleTimeUs, this.userDataParsable);
    L31:
        if (r5 != 178) goto L35;
        if (r20.getData()[r4 + 2] != 1) goto L35;
        this.userData.startNalUnit(r5);
        goto L35
    L26:
        r12 = -r8;
    L35:
        if (r5 != 0) goto L37;
    L43:
        int r17 = r2 - r4;
        if (this.sampleHasPicture == true) goto L46;
    L51:
        int r42 = r2;
    L53:
        if (this.startedFirstSample == true) goto L55;
    L58:
        this.samplePosition = this.totalBytesWritten - r17;
        long r14 = this.pesTimeUs;
        if (r14 != (-9223372036854775807L)) goto L65;
        long r15 = this.sampleTimeUs;
        if (r15 == (-9223372036854775807L)) goto L64;
        r14 = r15 + this.frameDurationUs;
        goto L65
    L64:
        r14 = -9223372036854775807L;
    L65:
        this.sampleTimeUs = r14;
        this.sampleIsKeyframe = false;
        this.pesTimeUs = -9223372036854775807L;
        boolean r16 = true;
        this.startedFirstSample = true;
    L66:
        if (r5 != 0) goto L68;
        r10 = r16;
    L68:
        this.sampleHasPicture = r10;
    L69:
        r2 = r42;
        r1 = r7;
        goto L3
    L55:
        if (this.sampleHasPicture == true) goto L58;
        r16 = true;
        goto L66
    L46:
        if (this.hasOutputFormat == false) goto L51;
        long r132 = this.sampleTimeUs;
        if (r132 == (-9223372036854775807L)) goto L51;
        boolean r152 = this.sampleIsKeyframe;
        r42 = r2;
        this.output.sampleMetadata(r132, r152 ? 1 : 0, ((int) (this.totalBytesWritten - this.samplePosition)) - r17, r17, null);
        goto L53
    L37:
        if (r5 == 179) goto L43;
        if (r5 != 184) goto L42;
        this.sampleIsKeyframe = true;
    L42:
        r42 = r2;
        goto L69
    L6:
        if (this.hasOutputFormat == true) goto L8;
        this.csdBuffer.onData(r3, r1, r2);
    L8:
        NalUnitTargetBuffer r0 = this.userData;
        if (r0 == null) goto L73;
        r0.appendToNalUnit(r3, r1, r2);
        return;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput r3, TsPayloadReader.TrackIdGenerator r4) {
        r4.generateNewId();
        this.formatId = r4.getFormatId();
        this.output = r3.track(r4.getTrackId(), 2);
        UserDataReader r2 = this.userDataReader;
        if (r2 == null) goto L6;
        r2.createTracks(r3, r4);
        return;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetFinished(boolean r9) {
        Assertions.checkStateNotNull(this.output);
        if (r9 == false) goto L6;
        boolean r4 = this.sampleIsKeyframe;
        int r5 = (int) (this.totalBytesWritten - this.samplePosition);
        this.output.sampleMetadata(this.sampleTimeUs, r4 ? 1 : 0, r5, 0, null);
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
        NalUnitTargetBuffer r0 = this.userData;
        if (r0 == null) goto L5;
        r0.reset();
    L5:
        this.totalBytesWritten = 0;
        this.startedFirstSample = false;
        this.pesTimeUs = -9223372036854775807L;
        this.sampleTimeUs = -9223372036854775807L;
    }
}
