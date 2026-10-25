package androidx.media3.extractor.mp4;

import android.util.Pair;
import androidx.car.app.media.CarAudioRecord;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.container.DolbyVisionConfig;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.container.Mp4AlternateGroupData;
import androidx.media3.container.Mp4Box;
import androidx.media3.container.Mp4LocationData;
import androidx.media3.container.Mp4TimestampData;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.Ac3Util;
import androidx.media3.extractor.Ac4Util;
import androidx.media3.extractor.AvcConfig;
import androidx.media3.extractor.ExtractorUtil;
import androidx.media3.extractor.GaplessInfoHolder;
import androidx.media3.extractor.HevcConfig;
import androidx.media3.extractor.OpusUtil;
import androidx.media3.extractor.VorbisUtil;
import androidx.media3.extractor.mp4.FixedSampleSizeRechunker;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Ints;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.MediaController;

/* loaded from: classes4.dex */
public abstract class BoxParser {
    private static final byte[] opusMagic = null;

    public static final class BtrtData {
        private final long avgBitrate;
        private final long maxBitrate;

        public BtrtData(long r1, long r3) {
            this.avgBitrate = r1;
            this.maxBitrate = r3;
        }

        public static /* synthetic */ long access$1400(BtrtData r2) {
            return r2.maxBitrate;
        }

        public static /* synthetic */ long access$1500(BtrtData r2) {
            return r2.avgBitrate;
        }
    }

    public static final class ChunkIterator {
        private final ParsableByteArray chunkOffsets;
        private final boolean chunkOffsetsAreLongs;
        public int index;
        public final int length;
        private int nextSamplesPerChunkChangeIndex;
        public int numSamples;
        public long offset;
        private int remainingSamplesPerChunkChanges;
        private final ParsableByteArray stsc;

        public ChunkIterator(ParsableByteArray r1, ParsableByteArray r2, boolean r3) {
            this.stsc = r1;
            this.chunkOffsets = r2;
            this.chunkOffsetsAreLongs = r3;
            r2.setPosition(12);
            this.length = r2.readUnsignedIntToInt();
            r1.setPosition(12);
            this.remainingSamplesPerChunkChanges = r1.readUnsignedIntToInt();
            boolean r22 = true;
            if (r1.readInt() == 1) goto L6;
            r22 = false;
        L6:
            ExtractorUtil.checkContainerInput(r22, "first_chunk must be 1");
            this.index = -1;
        }

        public boolean moveNext() {
            int r0 = this.index + 1;
            this.index = r0;
            if (r0 != this.length) goto L6;
            return false;
        L6:
            boolean r02 = this.chunkOffsetsAreLongs;
            ParsableByteArray r2 = this.chunkOffsets;
            if (r02 == false) goto L9;
            long r22 = r2.readUnsignedLongToLong();
        L10:
            this.offset = r22;
            if (this.index != this.nextSamplesPerChunkChangeIndex) goto L17;
            this.numSamples = this.stsc.readUnsignedIntToInt();
            this.stsc.skipBytes(4);
            int r03 = this.remainingSamplesPerChunkChanges - 1;
            this.remainingSamplesPerChunkChanges = r03;
            if (r03 <= 0) goto L15;
            int r04 = this.stsc.readUnsignedIntToInt() - 1;
        L16:
            this.nextSamplesPerChunkChangeIndex = r04;
            goto L17
        L15:
            r04 = -1;
        L17:
            return true;
        L9:
            r22 = r2.readUnsignedInt();
            goto L10
        }
    }

    public static final class EsdsData {
        private final long bitrate;
        private final byte[] initializationData;
        private final String mimeType;
        private final long peakBitrate;

        public EsdsData(String r1, byte[] r2, long r3, long r5) {
            this.mimeType = r1;
            this.initializationData = r2;
            this.bitrate = r3;
            this.peakBitrate = r5;
        }

        public static /* synthetic */ String access$1300(EsdsData r0) {
            return r0.mimeType;
        }

        public static /* synthetic */ long access$1600(EsdsData r2) {
            return r2.peakBitrate;
        }

        public static /* synthetic */ long access$1700(EsdsData r2) {
            return r2.bitrate;
        }

        public static /* synthetic */ byte[] access$700(EsdsData r0) {
            return r0.initializationData;
        }
    }

    public static final class EyesData {
        private final StriData striData;

        public EyesData(StriData r1) {
            this.striData = r1;
        }

        public static /* synthetic */ StriData access$1100(EyesData r0) {
            return r0.striData;
        }
    }

    public static final class MdhdData {
        private final String language;
        private final long mediaDurationUs;
        private final long timescale;

        public MdhdData(long r1, long r3, String r5) {
            this.timescale = r1;
            this.mediaDurationUs = r3;
            this.language = r5;
        }

        public static /* synthetic */ String access$100(MdhdData r0) {
            return r0.language;
        }

        public static /* synthetic */ long access$400(MdhdData r2) {
            return r2.timescale;
        }

        public static /* synthetic */ long access$500(MdhdData r2) {
            return r2.mediaDurationUs;
        }
    }

    public interface SampleSizeBox {
        int getFixedSampleSize();

        int getSampleCount();

        int readNextSampleSize();
    }

    public static final class StriData {
        private final boolean eyeViewsReversed;
        private final boolean hasLeftEyeView;
        private final boolean hasRightEyeView;

        public StriData(boolean r1, boolean r2, boolean r3) {
            this.hasLeftEyeView = r1;
            this.hasRightEyeView = r2;
            this.eyeViewsReversed = r3;
        }

        public static /* synthetic */ boolean access$1200(StriData r0) {
            return r0.eyeViewsReversed;
        }

        public static /* synthetic */ boolean access$1800(StriData r0) {
            return r0.hasLeftEyeView;
        }

        public static /* synthetic */ boolean access$1900(StriData r0) {
            return r0.hasRightEyeView;
        }
    }

    public static final class StsdData {
        public Format format;
        public int nalUnitLengthFieldLength;
        public int requiredSampleTransformation;
        public final TrackEncryptionBox[] trackEncryptionBoxes;

        public StsdData(int r1) {
            this.trackEncryptionBoxes = new TrackEncryptionBox[r1];
            this.requiredSampleTransformation = 0;
        }
    }

    public static final class StszSampleSizeBox implements SampleSizeBox {
        private final ParsableByteArray data;
        private final int fixedSampleSize;
        private final int sampleCount;

        public StszSampleSizeBox(Mp4Box.LeafBox r4, Format r5) {
            ParsableByteArray r42 = r4.data;
            this.data = r42;
            r42.setPosition(12);
            int r0 = r42.readUnsignedIntToInt();
            if ("audio/raw".equals(r5.sampleMimeType) == false) goto L9;
            int r52 = Util.getPcmFrameSize(r5.pcmEncoding, r5.channelCount);
            if (r0 != 0) goto L7;
        L8:
            Log.w("BoxParsers", "Audio sample size mismatch. stsd sample size: " + r52 + ", stsz sample size: " + r0);
            r0 = r52;
            goto L9
        L7:
            if ((r0 % r52) != 0) goto L8;
        L9:
            if (r0 != 0) goto L11;
            r0 = -1;
        L11:
            this.fixedSampleSize = r0;
            this.sampleCount = r42.readUnsignedIntToInt();
        }

        @Override // androidx.media3.extractor.mp4.BoxParser.SampleSizeBox
        public int getFixedSampleSize() {
            return this.fixedSampleSize;
        }

        @Override // androidx.media3.extractor.mp4.BoxParser.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // androidx.media3.extractor.mp4.BoxParser.SampleSizeBox
        public int readNextSampleSize() {
            int r0 = this.fixedSampleSize;
            if (r0 == (-1)) goto L5;
            return r0;
        L5:
            return this.data.readUnsignedIntToInt();
        }
    }

    public static final class Stz2SampleSizeBox implements SampleSizeBox {
        private int currentByte;
        private final ParsableByteArray data;
        private final int fieldSize;
        private final int sampleCount;
        private int sampleIndex;

        public Stz2SampleSizeBox(Mp4Box.LeafBox r2) {
            ParsableByteArray r22 = r2.data;
            this.data = r22;
            r22.setPosition(12);
            this.fieldSize = r22.readUnsignedIntToInt() & 255;
            this.sampleCount = r22.readUnsignedIntToInt();
        }

        @Override // androidx.media3.extractor.mp4.BoxParser.SampleSizeBox
        public int getFixedSampleSize() {
            return -1;
        }

        @Override // androidx.media3.extractor.mp4.BoxParser.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // androidx.media3.extractor.mp4.BoxParser.SampleSizeBox
        public int readNextSampleSize() {
            int r0 = this.fieldSize;
            if (r0 != 8) goto L7;
            return this.data.readUnsignedByte();
        L7:
            if (r0 == 16) goto L9;
            int r02 = this.sampleIndex;
            this.sampleIndex = r02 + 1;
            if ((r02 % 2) != 0) goto L15;
            int r03 = this.data.readUnsignedByte();
            this.currentByte = r03;
            return (r03 & 240) >> 4;
        L15:
            return this.currentByte & 15;
        L9:
            return this.data.readUnsignedShort();
        }
    }

    public static final class TkhdData {
        private final int alternateGroup;
        private final long duration;
        private final int height;
        private final int id;
        private final int rotationDegrees;
        private final int width;

        public TkhdData(int r1, long r2, int r4, int r5, int r6, int r7) {
            this.id = r1;
            this.duration = r2;
            this.alternateGroup = r4;
            this.rotationDegrees = r5;
            this.width = r6;
            this.height = r7;
        }

        public static /* synthetic */ long access$000(TkhdData r2) {
            return r2.duration;
        }

        public static /* synthetic */ int access$200(TkhdData r0) {
            return r0.alternateGroup;
        }

        public static /* synthetic */ int access$300(TkhdData r0) {
            return r0.id;
        }

        public static /* synthetic */ int access$600(TkhdData r0) {
            return r0.rotationDegrees;
        }

        public static /* synthetic */ int access$800(TkhdData r0) {
            return r0.width;
        }

        public static /* synthetic */ int access$900(TkhdData r0) {
            return r0.height;
        }
    }

    public static final class VexuData {
        private final EyesData eyesData;

        public VexuData(EyesData r1) {
            this.eyesData = r1;
        }

        public static /* synthetic */ EyesData access$1000(VexuData r0) {
            return r0.eyesData;
        }

        public boolean hasBothEyeViews() {
            EyesData r0 = this.eyesData;
            if (r0 != null) goto L5;
            return false;
        L5:
            if (StriData.access$1800(EyesData.access$1100(r0)) == true) goto L7;
            return false;
        L7:
            if (StriData.access$1900(EyesData.access$1100(this.eyesData)) == false) goto L13;
            return true;
        L13:
            return false;
        }
    }

    static {
        opusMagic = Util.getUtf8Bytes("OpusHead");
    }

    private static ByteBuffer allocateHdrStaticInfo() {
        return ByteBuffer.allocate(25).order(ByteOrder.LITTLE_ENDIAN);
    }

    private static boolean canApplyEditWithGaplessInfo(long[] r7, long r8, long r10, long r12) {
        int r0 = r7.length - 1;
        int r4 = Util.constrainValue(4, 0, r0);
        int r02 = Util.constrainValue(r7.length - 4, 0, r0);
        if (r7[0] <= r10) goto L5;
    L11:
        return false;
    L5:
        if (r10 >= r7[r4]) goto L11;
        if (r7[r02] >= r12) goto L11;
        if (r12 > r8) goto L11;
        return true;
    }

    private static int findBoxPosition(ParsableByteArray r6, int r7, int r8, int r9) {
        int r0 = r6.getPosition();
        if (r0 < r8) goto L5;
        boolean r3 = true;
    L6:
        ExtractorUtil.checkContainerInput(r3, null);
    L8:
        if ((r0 - r8) >= r9) goto L17;
        r6.setPosition(r0);
        int r32 = r6.readInt();
        if (r32 <= 0) goto L12;
        boolean r4 = true;
    L13:
        ExtractorUtil.checkContainerInput(r4, "childAtomSize must be positive");
        if (r6.readInt() == r7) goto L15;
        r0 = r0 + r32;
        goto L8
    L15:
        return r0;
    L12:
        r4 = false;
        goto L13
    L17:
        return -1;
    L5:
        r3 = false;
        goto L6
    }

    private static String formatVobsubIdx(byte[] r6, int r7, int r8) {
        int r2 = 0;
        if (r6.length != 64) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        ArrayList r02 = new ArrayList(16);
    L8:
        if (r2 >= (r6.length - 3)) goto L11;
        r02.add(String.format("%06x", new Object[]{Integer.valueOf(vobsubYuvToRgb(Ints.fromBytes(r6[r2], r6[r2 + 1], r6[r2 + 2], r6[r2 + 3])))}));
        r2 = r2 + 4;
        goto L8
    L11:
        return "size: " + r7 + "x" + r8 + "\npalette: " + Joiner.on(", ").join(r02) + "\n";
    L5:
        r0 = false;
        goto L6
    }

    private static String getLanguageFromCode(int r5) {
        int r4 = 0;
        char[] r3 = {(char) (((r5 >> 10) & 31) + 96), (char) (((r5 >> 5) & 31) + 96), (char) ((r5 & 31) + 96)};
    L3:
        if (r4 >= 3) goto L13;
        char r52 = r3[r4];
        if (r52 < 'a') goto L10;
        if (r52 > 'z') goto L17;
        r4 = r4 + 1;
        goto L3
    L17:
        return null;
    L10:
        return null;
    L13:
        return new String(r3);
    }

    private static int getTrackTypeForHdlr(int r1) {
        if (r1 != 1936684398) goto L7;
        return 1;
    L7:
        if (r1 != 1986618469) goto L11;
        return 2;
    L11:
        if (r1 != 1952807028) goto L13;
        return 3;
    L13:
        if (r1 != 1935832172) goto L15;
        return 3;
    L15:
        if (r1 != 1937072756) goto L17;
        return 3;
    L17:
        if (r1 != 1668047728) goto L19;
        return 3;
    L19:
        if (r1 != 1937072752) goto L22;
        return 3;
    L22:
        if (r1 != 1835365473) goto L25;
        return 5;
    L25:
        return -1;
    }

    public static void maybeSkipRemainingMetaBoxHeaderBytes(ParsableByteArray r3) {
        int r0 = r3.getPosition();
        r3.skipBytes(4);
        if (r3.readInt() == 1751411826) goto L5;
        r0 = r0 + 4;
    L5:
        r3.setPosition(r0);
    }

    private static ColorInfo parseApvc(ParsableByteArray r11) {
        ColorInfo.Builder r0 = new ColorInfo.Builder();
        ParsableBitArray r1 = new ParsableBitArray(r11.getData());
        r1.setPosition(r11.getPosition() * 8);
        r1.skipBytes(1);
        int r3 = r1.readBits(8);
        int r5 = 0;
    L3:
        if (r5 >= r3) goto L16;
        r1.skipBytes(1);
        int r6 = r1.readBits(8);
        int r7 = 0;
    L5:
        if (r7 >= r6) goto L14;
        r1.skipBits(6);
        boolean r8 = r1.readBit();
        r1.skipBit();
        r1.skipBytes(11);
        r1.skipBits(4);
        int r9 = r1.readBits(4) + 8;
        r0.setLumaBitdepth(r9);
        r0.setChromaBitdepth(r9);
        r1.skipBytes(1);
        if (r8 == false) goto L13;
        int r82 = r1.readBits(8);
        int r92 = r1.readBits(8);
        r1.skipBytes(1);
        boolean r10 = r1.readBit();
        ColorInfo.Builder r83 = r0.setColorSpace(ColorInfo.isoColorPrimariesToColorSpace(r82));
        if (r10 == false) goto L11;
        int r102 = 1;
    L12:
        r83.setColorRange(r102).setColorTransfer(ColorInfo.isoTransferCharacteristicsToColorTransfer(r92));
        goto L13
    L11:
        r102 = 2;
    L13:
        r7 = r7 + 1;
        goto L5
    L14:
        r5 = r5 + 1;
        goto L3
    L16:
        return r0.build();
    }

    private static void parseAudioSampleEntry(ParsableByteArray r26, int r27, int r28, int r29, int r30, String r31, boolean r32, DrmInitData r33, StsdData r34, int r35) {
        int r1 = r27;
        int r3 = r29;
        DrmInitData r6 = r33;
        r26.setPosition(r28 + 16);
        if (r32 == false) goto L5;
        int r11 = r26.readUnsignedShort();
        r26.skipBytes(6);
    L7:
        if (r11 == 0) goto L39;
        if (r11 == 1) goto L39;
        if (r11 != 2) goto L268;
        r26.skipBytes(16);
        int r8 = (int) Math.round(r26.readDouble());
        int r10 = r26.readUnsignedIntToInt();
        r26.skipBytes(4);
        int r112 = r26.readUnsignedIntToInt();
        int r19 = r26.readUnsignedIntToInt();
        if ((r19 & 1) == 0) goto L14;
        boolean r20 = true;
    L16:
        if ((r19 & 2) == 0) goto L18;
        boolean r192 = true;
    L19:
        if (r20 == true) goto L35;
        if (r112 != 8) goto L22;
        int r113 = 3;
    L38:
        r26.skipBytes(8);
        int r9 = r10;
        int r193 = 2;
        int r102 = 0;
    L44:
        if (r1 != 1767992678) goto L46;
        r8 = -1;
        r9 = -1;
    L51:
        int r15 = r26.getPosition();
        if (r1 != 1701733217) goto L61;
        Pair<Integer, TrackEncryptionBox> r14 = parseSampleEntryEncryptionData(r26, r28, r3);
        if (r14 == null) goto L60;
        r1 = ((Integer) r14.first).intValue();
        if (r6 != null) goto L58;
        r6 = null;
    L59:
        r34.trackEncryptionBoxes[r35] = (TrackEncryptionBox) r14.second;
        goto L60
    L58:
        r6 = r6.copyWithSchemeType(((TrackEncryptionBox) r14.second).schemeType);
    L60:
        r26.setPosition(r15);
    L61:
        String r142 = "audio/mhm1";
        if (r1 != 1633889587) goto L66;
        String r12 = "audio/ac3";
    L64:
        String r25 = r12;
        int r122 = r113;
        String r114 = r25;
    L138:
        int r23 = r122;
        List<byte[]> r2 = null;
        String r13 = null;
        EsdsData r16 = null;
        BtrtData r22 = null;
    L140:
        if ((r15 - r28) >= r3) goto L252;
        r26.setPosition(r15);
        int r123 = r26.readInt();
        if (r123 <= 0) goto L145;
        boolean r36 = true;
    L144:
        String r332 = r13;
        ExtractorUtil.checkContainerInput(r36, "childAtomSize must be positive");
        int r37 = r26.readInt();
        if (r37 != 1835557187) goto L160;
        r26.setPosition(r15 + 8);
        r26.skipBytes(1);
        int r38 = r26.readUnsignedByte();
        r26.skipBytes(1);
        if (Objects.equals(r114, r142) == false) goto L152;
        String r39 = String.format("mhm1.%02X", new Object[]{Integer.valueOf(r38)});
    L151:
        String r132 = r39;
        int r310 = r26.readUnsignedShort();
        r332 = r132;
        byte[] r133 = new byte[r310];
        String r352 = r142;
        r26.readBytes(r133, 0, r310);
        if (r2 != null) goto L156;
        r2 = ImmutableList.of(r133);
    L157:
        String r311 = r332;
    L251:
        r15 = r15 + r123;
        r142 = r352;
        r13 = r311;
        r3 = r29;
        goto L140
    L156:
        r2 = ImmutableList.of(r133, r2.get(0));
        goto L157
    L152:
        r39 = String.format("mha1.%02X", new Object[]{Integer.valueOf(r38)});
        goto L151
    L160:
        r352 = r142;
        if (r37 != 1835557200) goto L169;
        r26.setPosition(r15 + 8);
        int r312 = r26.readUnsignedByte();
        if (r312 <= 0) goto L157;
        byte[] r134 = new byte[r312];
        r26.readBytes(r134, 0, r312);
        if (r2 != null) goto L167;
        r2 = ImmutableList.of(r134);
        goto L157
    L167:
        r2 = ImmutableList.of(r2.get(0), r134);
        goto L157
    L169:
        if (r37 == 1702061171) goto L236;
        if (r32 == false) goto L175;
        if (r37 == 2002876005) goto L236;
    L175:
        if (r37 != 1651798644) goto L178;
        r22 = parseBtrtFromParent(r26, r15);
        goto L157
    L178:
        if (r37 != 1684103987) goto L182;
        r26.setPosition(r15 + 8);
        r34.format = Ac3Util.parseAc3AnnexFFormat(r26, Integer.toString(r30), r31, r6);
    L235:
        r311 = r332;
        goto L251
    L182:
        if (r37 != 1684366131) goto L185;
        r26.setPosition(r15 + 8);
        r34.format = Ac3Util.parseEAc3AnnexFFormat(r26, Integer.toString(r30), r31, r6);
        goto L235
    L185:
        if (r37 != 1684103988) goto L188;
        r26.setPosition(r15 + 8);
        r34.format = Ac4Util.parseAc4AnnexEFormat(r26, Integer.toString(r30), r31, r6);
        goto L235
    L188:
        if (r37 != 1684892784) goto L194;
        if (r102 <= 0) goto L192;
        r311 = r332;
        r8 = r102;
        r9 = r193;
        goto L251
    L192:
        throw ParserException.createForMalformedContainer("Invalid sample rate for Dolby TrueHD MLP stream: " + r102, null);
    L194:
        if (r37 == 1684305011) goto L234;
        if (r37 == 1969517683) goto L234;
        if (r37 != 1682927731) goto L202;
        int r24 = r123 - 8;
        byte[] r313 = opusMagic;
        byte[] r135 = Arrays.copyOf(r313, r313.length + r24);
        r26.setPosition(r15 + 8);
        r26.readBytes(r135, r313.length, r24);
        r2 = OpusUtil.buildInitializationData(r135);
        goto L157
    L202:
        if (r37 != 1684425825) goto L205;
        byte[] r314 = new byte[r123 - 8];
        r314[0] = 102;
        r314[1] = 76;
        r314[r193] = 97;
        r314[3] = 67;
        r26.setPosition(r15 + 12);
        r26.readBytes(r314, 4, r123 - 12);
        r2 = ImmutableList.of(r314);
        goto L157
    L205:
        if (r37 != 1634492771) goto L209;
        int r210 = r123 - 12;
        byte[] r315 = new byte[r210];
        r26.setPosition(r15 + 12);
        r26.readBytes(r315, 0, r210);
        Pair<Integer, Integer> r211 = CodecSpecificDataUtil.parseAlacAudioSpecificConfig(r315);
        r8 = ((Integer) r211.first).intValue();
        r9 = ((Integer) r211.second).intValue();
        r2 = ImmutableList.of(r315);
    L207:
        r311 = r332;
        goto L251
    L209:
        if (r37 != 1767990114) goto L212;
        r26.setPosition(r15 + 9);
        int r212 = r26.readUnsignedLeb128ToInt();
        byte[] r316 = new byte[r212];
        r26.readBytes(r316, 0, r212);
        String r213 = CodecSpecificDataUtil.buildIamfCodecString(r316);
        ImmutableList r317 = ImmutableList.of(r316);
        r311 = r213;
        r2 = r317;
        goto L251
    L212:
        if (r37 != 1885564227) goto L235;
        r26.setPosition(r15 + 12);
        if ((r26.readUnsignedByte() & 1) == 0) goto L216;
        ByteOrder r318 = ByteOrder.LITTLE_ENDIAN;
    L217:
        int r136 = r26.readUnsignedByte();
        if (r1 != 1768973165) goto L221;
        int r319 = Util.getPcmEncoding(r136, r318);
        int r137 = -1;
    L230:
        r23 = r319;
        if (r319 == r137) goto L207;
        r114 = "audio/raw";
        goto L207
    L221:
        if (r1 != 1718641517) goto L229;
        if (r136 != 32) goto L229;
        if (r318.equals(ByteOrder.LITTLE_ENDIAN) == false) goto L229;
        r319 = 4;
    L227:
        r137 = -1;
    L229:
        r319 = r23;
        goto L227
    L216:
        r318 = ByteOrder.BIG_ENDIAN;
    L234:
        r34.format = new Format.Builder().setId(r30).setSampleMimeType(r114).setChannelCount(r9).setSampleRate(r8).setDrmInitData(r6).setLanguage(r31).build();
    L236:
        if (r37 != 1702061171) goto L239;
        int r320 = r15;
    L240:
        if (r320 == (-1)) goto L207;
        r16 = parseEsdsFromParent(r26, r320);
        r114 = EsdsData.access$1300(r16);
        byte[] r321 = EsdsData.access$700(r16);
        if (r321 == null) goto L207;
        if ("audio/vorbis".equals(r114) == false) goto L247;
        r2 = VorbisUtil.parseVorbisCsdFromEsdsInitializationData(r321);
        goto L207
    L247:
        if (MediaController.AUDIO_MIME_TYPE.equals(r114) == false) goto L249;
        AacUtil.Config r214 = AacUtil.parseAudioSpecificConfig(r321);
        r8 = r214.sampleRateHz;
        r9 = r214.channelCount;
        String r215 = r214.codecs;
    L250:
        ImmutableList r322 = ImmutableList.of(r321);
        r311 = r215;
        r2 = r322;
        goto L251
    L249:
        r215 = r332;
        goto L250
    L239:
        r320 = findBoxPosition(r26, 1702061171, r15, r123);
        goto L240
    L145:
        r36 = false;
        goto L144
    L252:
        String r333 = r13;
        if (r34.format != null) goto L269;
        if (r114 == null) goto L270;
        Format.Builder r0 = new Format.Builder().setId(r30).setSampleMimeType(r114).setCodecs(r333).setChannelCount(r9).setSampleRate(r8).setPcmEncoding(r23).setInitializationData(r2).setDrmInitData(r6).setLanguage(r31);
        if (r16 == null) goto L258;
        r0.setAverageBitrate(Ints.saturatedCast(EsdsData.access$1700(r16))).setPeakBitrate(Ints.saturatedCast(EsdsData.access$1600(r16)));
    L260:
        r34.format = r0.build();
        return;
    L258:
        if (r22 == null) goto L260;
        r0.setAverageBitrate(Ints.saturatedCast(BtrtData.access$1500(r22))).setPeakBitrate(Ints.saturatedCast(BtrtData.access$1400(r22)));
        goto L260
    L270:
        return;
    L269:
        return;
    L66:
        if (r1 != 1700998451) goto L69;
        r12 = "audio/eac3";
        goto L64
    L69:
        if (r1 != 1633889588) goto L72;
        r12 = "audio/ac4";
        goto L64
    L72:
        if (r1 != 1685353315) goto L75;
        r12 = "audio/vnd.dts";
        goto L64
    L75:
        if (r1 != 1685353320) goto L77;
    L137:
        r12 = "audio/vnd.dts.hd";
        goto L64
    L77:
        if (r1 == 1685353324) goto L137;
        if (r1 != 1685353317) goto L83;
        r12 = "audio/vnd.dts.hd;profile=lbr";
        goto L64
    L83:
        if (r1 != 1685353336) goto L85;
        r12 = "audio/vnd.dts.uhd;profile=p2";
        goto L64
    L85:
        if (r1 != 1935764850) goto L88;
        r12 = "audio/3gpp";
        goto L64
    L88:
        if (r1 != 1935767394) goto L91;
        r12 = "audio/amr-wb";
        goto L64
    L91:
        if (r1 != 1936684916) goto L95;
    L92:
        r122 = r193;
    L93:
        r114 = "audio/raw";
        goto L138
    L95:
        if (r1 != 1953984371) goto L98;
        r114 = "audio/raw";
        r122 = 268435456;
        goto L138
    L98:
        if (r1 != 1819304813) goto L104;
        if (r113 == (-1)) goto L92;
        r122 = r113;
        goto L93
    L104:
        if (r1 != 778924082) goto L106;
    L136:
        r12 = "audio/mpeg";
        goto L64
    L106:
        if (r1 == 778924083) goto L136;
        if (r1 != 1835557169) goto L112;
        r12 = "audio/mha1";
        goto L64
    L112:
        if (r1 != 1835560241) goto L115;
        r122 = r113;
        r114 = "audio/mhm1";
        goto L138
    L115:
        if (r1 != 1634492771) goto L118;
        r12 = "audio/alac";
        goto L64
    L118:
        if (r1 != 1634492791) goto L121;
        r12 = "audio/g711-alaw";
        goto L64
    L121:
        if (r1 != 1970037111) goto L124;
        r12 = "audio/g711-mlaw";
        goto L64
    L124:
        if (r1 != 1332770163) goto L127;
        r12 = "audio/opus";
        goto L64
    L127:
        if (r1 != 1716281667) goto L130;
        r12 = "audio/flac";
        goto L64
    L130:
        if (r1 != 1835823201) goto L133;
        r12 = "audio/true-hd";
        goto L64
    L133:
        if (r1 != 1767992678) goto L135;
        r12 = "audio/iamf";
        goto L64
    L135:
        r122 = r113;
        r114 = null;
        goto L138
    L46:
        if (r1 != 1935764850) goto L49;
        r8 = 8000;
    L48:
        r9 = 1;
        goto L51
    L49:
        if (r1 != 1935767394) goto L51;
        r8 = CarAudioRecord.AUDIO_CONTENT_SAMPLING_RATE;
        goto L48
    L22:
        if (r112 != 16) goto L27;
        if (r192 == false) goto L25;
        r113 = 268435456;
        goto L38
    L25:
        r113 = 2;
        goto L38
    L27:
        if (r112 != 24) goto L31;
        if (r192 == false) goto L30;
        r113 = 1342177280;
        goto L38
    L30:
        r113 = 21;
        goto L38
    L31:
        if (r112 != 32) goto L37;
        if (r192 == false) goto L34;
        r113 = 1610612736;
        goto L38
    L34:
        r113 = 22;
    L37:
        r113 = -1;
        goto L38
    L35:
        if (r112 != 32) goto L37;
        r113 = 4;
        goto L38
    L18:
        r192 = false;
        goto L19
    L14:
        r20 = false;
        goto L16
    L268:
        return;
    L39:
        r9 = r26.readUnsignedShort();
        r26.skipBytes(6);
        r8 = r26.readUnsignedFixedPoint1616();
        r26.setPosition(r26.getPosition() - 4);
        r102 = r26.readInt();
        r193 = 2;
        if (r11 != 1) goto L42;
        r26.skipBytes(16);
    L42:
        r113 = -1;
        goto L44
    L5:
        r26.skipBytes(8);
        r11 = 0;
        goto L7
    }

    private static ColorInfo parseAv1c(ParsableByteArray r15) {
        ColorInfo.Builder r0 = new ColorInfo.Builder();
        ParsableBitArray r1 = new ParsableBitArray(r15.getData());
        r1.setPosition(r15.getPosition() * 8);
        int r152 = 1;
        r1.skipBytes(1);
        int r4 = r1.readBits(3);
        r1.skipBits(6);
        boolean r5 = r1.readBit();
        boolean r6 = r1.readBit();
        int r8 = 10;
        if (r4 != 2) goto L12;
        if (r5 == false) goto L12;
        if (r6 == false) goto L7;
        int r42 = 12;
    L8:
        r0.setLumaBitdepth(r42);
        if (r6 == false) goto L11;
        r8 = 12;
    L11:
        r0.setChromaBitdepth(r8);
    L21:
        r1.skipBits(13);
        r1.skipBit();
        int r62 = r1.readBits(4);
        if (r62 == 1) goto L26;
        Log.i("BoxParsers", "Unsupported obu_type: " + r62);
        return r0.build();
    L26:
        if (r1.readBit() == false) goto L29;
        Log.i("BoxParsers", "Unsupported obu_extension_flag");
        return r0.build();
    L29:
        boolean r63 = r1.readBit();
        r1.skipBit();
        if (r63 == true) goto L32;
    L35:
        int r64 = r1.readBits(3);
        r1.skipBit();
        if (r1.readBit() == false) goto L40;
        Log.i("BoxParsers", "Unsupported reduced_still_picture_header");
        return r0.build();
    L40:
        if (r1.readBit() == false) goto L44;
        Log.i("BoxParsers", "Unsupported timing_info_present_flag");
        return r0.build();
    L44:
        if (r1.readBit() == false) goto L47;
        Log.i("BoxParsers", "Unsupported initial_display_delay_present_flag");
        return r0.build();
    L47:
        int r10 = r1.readBits(5);
        boolean r11 = false;
        int r12 = 0;
    L49:
        if (r12 > r10) goto L54;
        r1.skipBits(12);
        if (r1.readBits(5) <= 7) goto L53;
        r1.skipBit();
    L53:
        r12 = r12 + 1;
        goto L49
    L54:
        int r7 = r1.readBits(4);
        int r52 = r1.readBits(4);
        r1.skipBits(r7 + 1);
        r1.skipBits(r52 + 1);
        if (r1.readBit() == false) goto L57;
        r1.skipBits(7);
    L57:
        r1.skipBits(7);
        boolean r53 = r1.readBit();
        if (r53 == false) goto L61;
        r1.skipBits(2);
    L61:
        if (r1.readBit() == false) goto L63;
        int r72 = 2;
    L64:
        if (r72 > 0) goto L66;
    L68:
        if (r53 == false) goto L70;
        r1.skipBits(3);
    L70:
        r1.skipBits(3);
        boolean r3 = r1.readBit();
        if (r64 != 2) goto L74;
        if (r3 == false) goto L74;
        r1.skipBit();
    L74:
        if (r64 == 1) goto L79;
        if (r1.readBit() == false) goto L79;
        r11 = true;
    L79:
        if (r1.readBit() == false) goto L93;
        int r32 = r1.readBits(8);
        int r54 = r1.readBits(8);
        int r2 = r1.readBits(8);
        if (r11 == true) goto L86;
        if (r32 != 1) goto L86;
        if (r54 != 13) goto L86;
        if (r2 != 0) goto L86;
        int r13 = 1;
    L87:
        ColorInfo.Builder r22 = r0.setColorSpace(ColorInfo.isoColorPrimariesToColorSpace(r32));
        if (r13 == 1) goto L91;
        r152 = 2;
    L91:
        r22.setColorRange(r152).setColorTransfer(ColorInfo.isoTransferCharacteristicsToColorTransfer(r54));
    L86:
        r13 = r1.readBits(1);
    L93:
        return r0.build();
    L66:
        if (r1.readBit() == true) goto L68;
        r1.skipBits(1);
        goto L68
    L63:
        r72 = r1.readBits(1);
        goto L64
    L32:
        if (r1.readBits(8) <= 127) goto L35;
        Log.i("BoxParsers", "Excessive obu_size");
        return r0.build();
    L7:
        r42 = 10;
    L12:
        if (r4 > 2) goto L21;
        if (r5 == false) goto L15;
        int r43 = 10;
    L16:
        r0.setLumaBitdepth(r43);
        if (r5 == true) goto L20;
        r8 = 8;
    L20:
        r0.setChromaBitdepth(r8);
        goto L21
    L15:
        r43 = 8;
        goto L16
    }

    private static BtrtData parseBtrtFromParent(ParsableByteArray r3, int r4) {
        r3.setPosition(r4 + 8);
        r3.skipBytes(4);
        long r0 = r3.readUnsignedInt();
        return new BtrtData(r3.readUnsignedInt(), r0);
    }

    public static Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent(ParsableByteArray r11, int r12, int r13) {
        int r0 = r12 + 8;
        boolean r2 = false;
        int r5 = -1;
        int r7 = 0;
        String r4 = null;
        Integer r6 = null;
    L4:
        if ((r0 - r12) >= r13) goto L16;
        r11.setPosition(r0);
        int r8 = r11.readInt();
        int r9 = r11.readInt();
        if (r9 != 1718775137) goto L9;
        r6 = Integer.valueOf(r11.readInt());
    L14:
        r0 = r0 + r8;
        goto L4
    L9:
        if (r9 != 1935894637) goto L12;
        r11.skipBytes(4);
        r4 = r11.readString(4);
        goto L14
    L12:
        if (r9 != 1935894633) goto L14;
        r5 = r0;
        r7 = r8;
        goto L14
    L16:
        if ("cenc".equals(r4) == true) goto L26;
        if ("cbc1".equals(r4) == true) goto L26;
        if ("cens".equals(r4) == true) goto L26;
        if ("cbcs".equals(r4) == true) goto L26;
        return null;
    L26:
        if (r6 == null) goto L28;
        boolean r132 = true;
    L29:
        ExtractorUtil.checkContainerInput(r132, "frma atom is mandatory");
        if (r5 == (-1)) goto L32;
        boolean r133 = true;
    L33:
        ExtractorUtil.checkContainerInput(r133, "schi atom is mandatory");
        TrackEncryptionBox r112 = parseSchiFromParent(r11, r5, r7, r4);
        if (r112 == null) goto L36;
        r2 = true;
    L36:
        ExtractorUtil.checkContainerInput(r2, "tenc atom is mandatory");
        return Pair.create(r6, (TrackEncryptionBox) Util.castNonNull(r112));
    L32:
        r133 = false;
        goto L33
    L28:
        r132 = false;
        goto L29
    }

    private static Pair<long[], long[]> parseEdts(Mp4Box.ContainerBox r9) {
        Mp4Box.LeafBox r92 = r9.getLeafBoxOfType(1701606260);
        if (r92 != null) goto L5;
        return null;
    L5:
        ParsableByteArray r93 = r92.data;
        r93.setPosition(8);
        int r1 = parseFullBoxVersion(r93.readInt());
        int r2 = r93.readUnsignedIntToInt();
        long[] r3 = new long[r2];
        long[] r4 = new long[r2];
        int r5 = 0;
    L6:
        if (r5 >= r2) goto L21;
        if (r1 != 1) goto L10;
        long r7 = r93.readUnsignedLongToLong();
    L11:
        r3[r5] = r7;
        if (r1 != 1) goto L14;
        long r72 = r93.readLong();
    L15:
        r4[r5] = r72;
        if (r93.readShort() != 1) goto L18;
        r93.skipBytes(2);
        r5 = r5 + 1;
        goto L6
    L18:
        f$$ExternalSyntheticBUOutline1.m("Unsupported media rate.");
        return null;
    L14:
        r72 = r93.readInt();
        goto L15
    L10:
        r7 = r93.readUnsignedInt();
        goto L11
    L21:
        return Pair.create(r3, r4);
    }

    private static EsdsData parseEsdsFromParent(ParsableByteArray r10, int r11) {
        r10.setPosition(r11 + 12);
        r10.skipBytes(1);
        parseExpandableClassSize(r10);
        r10.skipBytes(2);
        int r1 = r10.readUnsignedByte();
        if ((r1 & 128) == 0) goto L6;
        r10.skipBytes(2);
    L6:
        if ((r1 & 64) == 0) goto L9;
        r10.skipBytes(r10.readUnsignedByte());
    L9:
        if ((r1 & 32) == 0) goto L11;
        r10.skipBytes(2);
    L11:
        r10.skipBytes(1);
        parseExpandableClassSize(r10);
        String r2 = MimeTypes.getMimeTypeFromMp4ObjectType(r10.readUnsignedByte());
        if ("audio/mpeg".equals(r2) == true) goto L29;
        if ("audio/vnd.dts".equals(r2) == true) goto L29;
        if ("audio/vnd.dts.hd".equals(r2) == true) goto L29;
        r10.skipBytes(4);
        long r0 = r10.readUnsignedInt();
        long r3 = r10.readUnsignedInt();
        r10.skipBytes(1);
        int r112 = parseExpandableClassSize(r10);
        long r4 = r3;
        byte[] r32 = new byte[r112];
        r10.readBytes(r32, 0, r112);
        if (r4 > 0) goto L23;
        r4 = -1;
    L23:
        if (r0 <= 0) goto L25;
        long r6 = r0;
    L27:
        return new EsdsData(r2, r32, r4, r6);
    L25:
        r6 = -1;
    L29:
        return new EsdsData(r2, null, -1, -1);
    }

    private static int parseExpandableClassSize(ParsableByteArray r3) {
        int r0 = r3.readUnsignedByte();
        int r1 = r0 & 127;
    L4:
        if ((r0 & 128) != 128) goto L6;
        r0 = r3.readUnsignedByte();
        r1 = (r1 << 7) | (r0 & 127);
        goto L4
    L6:
        return r1;
    }

    public static int parseFullBoxFlags(int r1) {
        return r1 & 16777215;
    }

    public static int parseFullBoxVersion(int r0) {
        return (r0 >> 24) & 255;
    }

    private static int parseHdlr(ParsableByteArray r1) {
        r1.setPosition(16);
        return r1.readInt();
    }

    private static Metadata parseIlst(ParsableByteArray r2, int r3) {
        r2.skipBytes(8);
        ArrayList r0 = new ArrayList();
    L4:
        if (r2.getPosition() >= r3) goto L9;
        Metadata.Entry r1 = MetadataUtil.parseIlstElement(r2);
        if (r1 == null) goto L4;
        r0.add(r1);
        goto L4
    L9:
        if (r0.isEmpty() == false) goto L13;
        return null;
    L13:
        return new Metadata(r0);
    }

    private static MdhdData parseMdhd(ParsableByteArray r10) {
        int r0 = 8;
        r10.setPosition(8);
        int r1 = parseFullBoxVersion(r10.readInt());
        if (r1 != 0) goto L5;
        int r2 = 8;
    L6:
        r10.skipBytes(r2);
        long r4 = r10.readUnsignedInt();
        int r22 = r10.getPosition();
        if (r1 != 0) goto L9;
        r0 = 4;
    L9:
        int r3 = 0;
    L10:
        long r6 = -9223372036854775807L;
        if (r3 >= r0) goto L22;
        if (r10.getData()[r22 + r3] != (-1)) goto L14;
        r3 = r3 + 1;
        goto L10
    L14:
        if (r1 != 0) goto L16;
        long r02 = r10.readUnsignedInt();
    L18:
        if (r02 == 0) goto L24;
        long r03 = Util.scaleLargeTimestamp(r02, 1000000, r4);
        r4 = r4;
        r6 = r03;
    L24:
        return new MdhdData(r4, r6, getLanguageFromCode(r10.readUnsignedShort()));
    L16:
        r02 = r10.readUnsignedLongToLong();
        goto L18
    L22:
        r10.skipBytes(r0);
        goto L24
    L5:
        r2 = 16;
        goto L6
    }

    public static Metadata parseMdtaFromMeta(Mp4Box.ContainerBox r10) {
        Mp4Box.LeafBox r0 = r10.getLeafBoxOfType(1751411826);
        Mp4Box.LeafBox r1 = r10.getLeafBoxOfType(1801812339);
        Mp4Box.LeafBox r102 = r10.getLeafBoxOfType(1768715124);
        if (r0 == null) goto L29;
        if (r1 == null) goto L29;
        if (r102 == null) goto L29;
        if (parseHdlr(r0.data) != 1835299937) goto L29;
        ParsableByteArray r02 = r1.data;
        r02.setPosition(12);
        int r12 = r02.readInt();
        String[] r3 = new String[r12];
        int r4 = 0;
    L11:
        if (r4 >= r12) goto L13;
        int r6 = r02.readInt();
        r02.skipBytes(4);
        r3[r4] = r02.readString(r6 - 8);
        r4 = r4 + 1;
        goto L11
    L13:
        ParsableByteArray r103 = r102.data;
        r103.setPosition(8);
        ArrayList r03 = new ArrayList();
    L15:
        if (r103.bytesLeft() <= 8) goto L25;
        int r42 = r103.getPosition();
        int r62 = r103.readInt();
        int r7 = r103.readInt() - 1;
        if (r7 < 0) goto L22;
        if (r7 >= r12) goto L22;
        MdtaMetadataEntry r72 = MetadataUtil.parseMdtaMetadataEntryFromIlst(r103, r42 + r62, r3[r7]);
        if (r72 == null) goto L23;
        r03.add(r72);
    L23:
        r103.setPosition(r42 + r62);
    L22:
        Log.w("BoxParsers", "Skipped metadata with unknown key index: " + r7);
        goto L23
    L25:
        if (r03.isEmpty() == false) goto L28;
        return null;
    L28:
        return new Metadata(r03);
    L29:
        return null;
    }

    private static void parseMetaDataSampleEntry(ParsableByteArray r0, int r1, int r2, int r3, StsdData r4) {
        r0.setPosition(r2 + 16);
        if (r1 != 1835365492) goto L8;
        r0.readNullTerminatedString();
        String r02 = r0.readNullTerminatedString();
        if (r02 == null) goto L9;
        r4.format = new Format.Builder().setId(r3).setSampleMimeType(r02).build();
        return;
    L9:
        return;
    }

    public static Mp4TimestampData parseMvhd(ParsableByteArray r11) {
        r11.setPosition(8);
        if (parseFullBoxVersion(r11.readInt()) != 0) goto L6;
        long r0 = r11.readUnsignedInt();
        long r2 = r11.readUnsignedInt();
    L5:
        long r5 = r0;
        long r7 = r2;
        return new Mp4TimestampData(r5, r7, r11.readUnsignedInt());
    L6:
        r0 = r11.readLong();
        r2 = r11.readLong();
        goto L5
    }

    private static float parsePaspFromParent(ParsableByteArray r0, int r1) {
        r0.setPosition(r1 + 8);
        return r0.readUnsignedIntToInt() / r0.readUnsignedIntToInt();
    }

    private static byte[] parseProjFromParent(ParsableByteArray r4, int r5, int r6) {
        int r0 = r5 + 8;
    L4:
        if ((r0 - r5) >= r6) goto L10;
        r4.setPosition(r0);
        int r1 = r4.readInt();
        if (r4.readInt() == 1886547818) goto L8;
        r0 = r0 + r1;
        goto L4
    L8:
        return Arrays.copyOfRange(r4.getData(), r0, r1 + r0);
    L10:
        return null;
    }

    private static Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData(ParsableByteArray r4, int r5, int r6) {
        int r0 = r4.getPosition();
    L4:
        if ((r0 - r5) >= r6) goto L15;
        r4.setPosition(r0);
        int r1 = r4.readInt();
        if (r1 <= 0) goto L8;
        boolean r2 = true;
    L9:
        ExtractorUtil.checkContainerInput(r2, "childAtomSize must be positive");
        if (r4.readInt() != 1936289382) goto L14;
        Pair<Integer, TrackEncryptionBox> r22 = parseCommonEncryptionSinfFromParent(r4, r0, r1);
        if (r22 == null) goto L14;
        return r22;
    L14:
        r0 = r0 + r1;
        goto L4
    L8:
        r2 = false;
        goto L9
    L15:
        return null;
    }

    private static TrackEncryptionBox parseSchiFromParent(ParsableByteArray r11, int r12, int r13, String r14) {
        int r0 = r12 + 8;
    L3:
        byte[] r2 = null;
        if ((r0 - r12) >= r13) goto L22;
        r11.setPosition(r0);
        int r1 = r11.readInt();
        if (r11.readInt() == 1952804451) goto L7;
        r0 = r0 + r1;
        goto L3
    L7:
        int r122 = parseFullBoxVersion(r11.readInt());
        r11.skipBytes(1);
        if (r122 != 0) goto L10;
        r11.skipBytes(1);
        int r8 = 0;
        int r9 = 0;
    L12:
        if (r11.readUnsignedByte() != 1) goto L14;
        boolean r4 = true;
    L15:
        int r6 = r11.readUnsignedByte();
        byte[] r7 = new byte[16];
        r11.readBytes(r7, 0, 16);
        if (r4 == false) goto L20;
        if (r6 != 0) goto L20;
        int r123 = r11.readUnsignedByte();
        r2 = new byte[r123];
        r11.readBytes(r2, 0, r123);
    L20:
        return new TrackEncryptionBox(r4, r14, r6, r7, r8, r9, r2);
    L14:
        r4 = false;
        goto L15
    L10:
        int r124 = r11.readUnsignedByte();
        r9 = r124 & 15;
        r8 = (r124 & 240) >> 4;
        goto L12
    L22:
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8, types: [boolean] */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v15, types: [boolean] */
    public static TrackSampleTable parseStbl(Track r36, Mp4Box.ContainerBox r37, GaplessInfoHolder r38) {
        Track r1 = r36;
        Mp4Box.LeafBox r3 = r37.getLeafBoxOfType(1937011578);
        if (r3 == null) goto L5;
        SampleSizeBox r5 = new StszSampleSizeBox(r3, r1.format);
    L8:
        int r32 = r5.getSampleCount();
        if (r32 != 0) goto L13;
        return new TrackSampleTable(r1, new long[0], new int[0], 0, new long[0], new int[0], 0);
    L13:
        if (r1.type != 2) goto L17;
        long r11 = r1.mediaDurationUs;
        if (r11 <= 0) goto L17;
        r1 = r1.copyWithFormat(r1.format.buildUpon().setFrameRate(r32 / (r11 / 1000000.0f)).build());
    L17:
        Mp4Box.LeafBox r7 = r37.getLeafBoxOfType(1937007471);
        if (r7 != null) goto L20;
        r7 = (Mp4Box.LeafBox) Assertions.checkNotNull(r37.getLeafBoxOfType(1668232756));
        boolean r12 = true;
    L21:
        ParsableByteArray r72 = r7.data;
        ParsableByteArray r13 = ((Mp4Box.LeafBox) Assertions.checkNotNull(r37.getLeafBoxOfType(1937011555))).data;
        ParsableByteArray r14 = ((Mp4Box.LeafBox) Assertions.checkNotNull(r37.getLeafBoxOfType(1937011827))).data;
        Mp4Box.LeafBox r15 = r37.getLeafBoxOfType(1937011571);
        if (r15 == null) goto L25;
        ParsableByteArray r152 = r15.data;
    L26:
        Mp4Box.LeafBox r0 = r37.getLeafBoxOfType(1668576371);
        if (r0 == null) goto L29;
        ParsableByteArray r02 = r0.data;
    L30:
        ChunkIterator r9 = new ChunkIterator(r13, r72, r12);
        r14.setPosition(12);
        int r10 = r14.readUnsignedIntToInt() - 1;
        int r122 = r14.readUnsignedIntToInt();
        int r132 = r14.readUnsignedIntToInt();
        if (r02 == null) goto L33;
        r02.setPosition(12);
        int r18 = r02.readUnsignedIntToInt();
    L35:
        if (r152 == null) goto L40;
        r152.setPosition(12);
        int r73 = r152.readUnsignedIntToInt();
        if (r73 <= 0) goto L39;
        int r19 = r152.readUnsignedIntToInt() - 1;
        int r20 = 0;
    L41:
        int r6 = r5.getFixedSampleSize();
        String r8 = r1.format.sampleMimeType;
        if (r6 != (-1)) goto L44;
    L53:
        int r4 = r20;
    L54:
        if (r4 == 0) goto L60;
        int r03 = r9.length;
        long[] r42 = new long[r03];
        int[] r04 = new int[r03];
    L57:
        if (r9.moveNext() == false) goto L59;
        int r52 = r9.index;
        r42[r52] = r9.offset;
        r04[r52] = r9.numSamples;
        goto L57
    L59:
        FixedSampleSizeRechunker.Results r05 = FixedSampleSizeRechunker.rechunk(r6, r42, r04, r132);
        long[] r43 = r05.offsets;
        int[] r53 = r05.sizes;
        int r62 = r05.maximumSize;
        long[] r74 = r05.timestamps;
        int[] r82 = r05.flags;
        long r92 = r05.duration;
        long r123 = r05.totalSize;
        long r22 = r92;
        int r362 = 1;
        long[] r93 = r74;
        int[] r102 = r82;
        int r83 = r62;
        int[] r75 = r53;
        long[] r63 = r43;
    L116:
        long r44 = r1.mediaDurationUs;
        if (r44 <= 0) goto L123;
        long r45 = Util.scaleLargeValue(r123 * 8, 1000000, r44, RoundingMode.HALF_DOWN);
        if (r45 <= 0) goto L123;
        if (r45 >= 2147483647L) goto L123;
        r1 = r1.copyWithFormat(r1.format.buildUpon().setAverageBitrate((int) r45).build());
    L123:
        Track r54 = r1;
        long r112 = Util.scaleLargeTimestamp(r22, 1000000, r54.timescale);
        long[] r06 = r54.editListDurations;
        if (r06 != null) goto L127;
        Util.scaleLargeTimestampsInPlace(r93, 1000000, r54.timescale);
        return new TrackSampleTable(r54, r63, r75, r83, r93, r102, r112);
    L127:
        int[] r46 = r102;
        int r103 = r362;
        if (r06.length != r103) goto L147;
        if (r54.type != r103) goto L147;
        if (r93.length < 2) goto L147;
        long r124 = ((long[]) Assertions.checkNotNull(r54.editListMediaTimes))[r20];
        long r142 = r124 + Util.scaleLargeTimestamp(r54.editListDurations[r20], r54.timescale, r54.movieTimescale);
        if (canApplyEditWithGaplessInfo(r93, r22, r124, r142) == false) goto L147;
        long r104 = Util.scaleLargeTimestamp(r124 - r93[r20], r54.format.sampleRate, r54.timescale);
        long r125 = Util.scaleLargeTimestamp(r22 - r142, r54.format.sampleRate, r54.timescale);
        if (r104 != 0) goto L142;
        if (r125 == 0) goto L147;
    L142:
        if (r104 > 2147483647L) goto L147;
        if (r125 > 2147483647L) goto L147;
        r38.encoderDelay = (int) r104;
        r38.encoderPadding = (int) r125;
        Util.scaleLargeTimestampsInPlace(r93, 1000000, r54.timescale);
        return new TrackSampleTable(r54, r63, r75, r83, r93, r46, Util.scaleLargeTimestamp(r54.editListDurations[r20], 1000000, r54.movieTimescale));
    L147:
        long[] r07 = r54.editListDurations;
        if (r07.length != 1) goto L158;
        if (r07[r20] != 0) goto L158;
        long r16 = ((long[]) Assertions.checkNotNull(r54.editListMediaTimes))[r20];
        int r08 = r20;
    L153:
        if (r08 >= r93.length) goto L156;
        r93[r08] = Util.scaleLargeTimestamp(r93[r08] - r16, 1000000, r54.timescale);
        r08 = r08 + 1;
        goto L153
    L156:
        return new TrackSampleTable(r54, r63, r75, r83, r93, r46, Util.scaleLargeTimestamp(r22 - r16, 1000000, r54.timescale));
    L158:
        if (r54.type != 1) goto L160;
        ?? r17 = 1;
    L161:
        int[] r2 = new int[r07.length];
        int[] r09 = new int[r07.length];
        long[] r47 = (long[]) Assertions.checkNotNull(r54.editListMediaTimes);
        int r113 = r20;
        int r126 = r113;
        int r133 = r126;
        int r143 = r133;
    L162:
        long[] r153 = r54.editListDurations;
        int[] r182 = r09;
        if (r113 >= r153.length) goto L195;
        int r010 = r113;
        int r372 = r126;
        long r114 = r47[r010];
        if (r114 == (-1)) goto L193;
        long r222 = r153[r010];
        int[] r154 = r75;
        int r382 = r83;
        long r76 = Util.scaleLargeTimestamp(r222, r54.timescale, r54.movieTimescale);
        int r192 = r010;
        r2[r192] = Util.binarySearchFloor(r93, r114, true, true);
        long r115 = r114 + r76;
        ?? r84 = r20;
        r182[r192] = Util.binarySearchCeil(r93, r115, r17, r84);
        int r77 = r2[r192];
    L167:
        int r202 = r2[r192];
        if (r202 < 0) goto L172;
        if ((r46[r202] & 1) != 0) goto L172;
        r2[r192] = r202 - 1;
    L172:
        if (r202 >= 0) goto L180;
        r2[r192] = r77;
    L174:
        int r011 = r2[r192];
        if (r011 >= r182[r192]) goto L180;
        if ((r46[r011] & 1) != 0) goto L180;
        r2[r192] = r011 + 1;
    L180:
        if (r54.type == 2) goto L182;
    L188:
        int r012 = r182[r192];
        int r78 = r2[r192];
        r133 = r133 + (r012 - r78);
        if (r143 == r78) goto L191;
        int r79 = 1;
    L192:
        r126 = r372 | r79;
        r143 = r012;
    L194:
        r113 = r192 + 1;
        r20 = r84;
        r75 = r154;
        r09 = r182;
        r83 = r382;
        goto L162
    L191:
        r79 = r84 == true ? 1 : 0;
        goto L192
    L182:
        if (r2[r192] == r182[r192]) goto L188;
    L183:
        int r013 = r182[r192];
        if (r013 >= (r93.length - 1)) goto L188;
        if (r93[r013 + 1] > r115) goto L188;
        r182[r192] = r013 + 1;
        goto L183
    L193:
        r192 = r010;
        r154 = r75;
        r382 = r83;
        r84 = r20;
        r126 = r372;
        goto L194
    L195:
        int[] r155 = r75;
        int r383 = r83;
        int r373 = r126;
        int r85 = r20;
        if (r133 == r32) goto L198;
        int r014 = 1;
    L199:
        int r015 = r373 | r014;
        if (r015 == 0) goto L202;
        long[] r110 = new long[r133];
    L203:
        if (r015 == 0) goto L205;
        int[] r710 = new int[r133];
    L206:
        if (r015 == 0) goto L208;
        int r33 = r85;
    L209:
        if (r015 == 0) goto L211;
        int[] r48 = new int[r133];
    L212:
        long[] r116 = new long[r133];
        int r23 = r33;
        int r34 = r85;
        int r127 = r34;
        long r24 = 0;
    L214:
        if (r85 >= r54.editListDurations.length) goto L230;
        long r193 = r54.editListMediaTimes[r85];
        int r134 = r2[r85];
        int r144 = r182[r85];
        int r374 = r015;
        if (r015 == 0) goto L218;
        int r016 = r144 - r134;
        System.arraycopy(r63, r134, r110, r127, r016);
        System.arraycopy(r155, r134, r710, r127, r016);
        System.arraycopy(r46, r134, r48, r127, r016);
    L218:
        int r017 = r23;
    L219:
        if (r134 >= r144) goto L229;
        long[] r223 = r110;
        int[] r21 = r2;
        long r111 = Util.scaleLargeTimestamp(r24, 1000000, r54.movieTimescale);
        long r117 = Util.scaleLargeTimestamp(r93[r134] - r193, 1000000, r54.timescale);
        if (r117 >= 0) goto L223;
        r34 = 1;
    L223:
        r116[r127] = r111 + r117;
        if (r374 == 0) goto L228;
        if (r710[r127] <= r017) goto L228;
        r017 = r155[r134];
    L228:
        r127 = r127 + 1;
        r134 = r134 + 1;
        r2 = r21;
        r110 = r223;
        goto L219
    L229:
        r24 = r24 + r54.editListDurations[r85];
        r85 = r85 + 1;
        r23 = r017;
        r110 = r110;
        r015 = r374;
        goto L214
    L230:
        long[] r224 = r110;
        long r26 = Util.scaleLargeTimestamp(r24, 1000000, r54.movieTimescale);
        if (r34 == 0) goto L234;
        r54 = r54.copyWithFormat(r54.format.buildUpon().setHasPrerollSamples(true).build());
    L234:
        return new TrackSampleTable(r54, r224, r710, r23, r116, r48, r26);
    L211:
        r48 = r46;
        goto L212
    L208:
        r33 = r383;
        goto L209
    L205:
        r710 = r155;
        goto L206
    L202:
        r110 = r63;
        goto L203
    L198:
        r014 = r85;
        goto L199
    L160:
        r17 = r20;
        goto L161
    L60:
        long[] r49 = new long[r32];
        int[] r64 = new int[r32];
        long[] r86 = new long[r32];
        r362 = 1;
        int[] r118 = new int[r32];
        ParsableByteArray r375 = r02;
        SampleSizeBox r232 = r5;
        int r018 = r132;
        ParsableByteArray r27 = r152;
        long r25 = 0;
        long r29 = 0;
        int r225 = r18;
        int r55 = r19;
        int r145 = r20;
        int r156 = r145;
        int r28 = r156;
        int r31 = r28;
        int r135 = r122;
        long r183 = 0;
        int r128 = r10;
        int r105 = r73;
        int r711 = r31;
    L62:
        if (r145 >= r32) goto L95;
        long r322 = r29;
        int r292 = r28;
        boolean r282 = true;
    L64:
        if (r292 != 0) goto L68;
        r282 = r9.moveNext();
        if (r282 == false) goto L68;
        int r30 = r128;
        long r129 = r9.offset;
        r292 = r9.numSamples;
        r322 = r129;
        r128 = r30;
        r135 = r135;
        r32 = r32;
    L68:
        int r35 = r32;
        int r302 = r128;
        int r342 = r135;
        if (r282 == false) goto L70;
        if (r375 == null) goto L77;
        int r210 = r31;
    L73:
        if (r210 != 0) goto L76;
        if (r225 <= 0) goto L76;
        r210 = r375.readUnsignedIntToInt();
        r156 = r375.readInt();
        r225 = r225 - 1;
    L76:
        r31 = r210 - 1;
    L77:
        r49[r145] = r322;
        int r211 = r232.readNextSampleSize();
        r64[r145] = r211;
        r183 = r183 + r211;
        if (r211 <= r711) goto L80;
        r711 = r211;
    L80:
        r86[r145] = r25 + r156;
        if (r27 != null) goto L83;
        int r212 = 1;
    L84:
        r118[r145] = r212;
        if (r145 != r55) goto L89;
        r118[r145] = 1;
        r105 = r105 - 1;
        if (r105 <= 0) goto L89;
        r55 = ((ParsableByteArray) Assertions.checkNotNull(r27)).readUnsignedIntToInt() - 1;
    L89:
        r25 = r25 + r018;
        r135 = r342 - 1;
        if (r135 != 0) goto L93;
        if (r302 <= 0) goto L93;
        r128 = r302 - 1;
        r135 = r14.readUnsignedIntToInt();
        r018 = r14.readInt();
    L94:
        long r213 = r322 + r64[r145];
        r28 = r292 - 1;
        r145 = r145 + 1;
        r29 = r213;
        r32 = r35;
    L93:
        r128 = r302;
        goto L94
    L83:
        r212 = r20;
        goto L84
    L70:
        Log.w("BoxParsers", "Unexpected end of chunk data");
        long[] r019 = Arrays.copyOf(r49, r145);
        int[] r39 = Arrays.copyOf(r64, r145);
        r86 = Arrays.copyOf(r86, r145);
        r118 = Arrays.copyOf(r118, r145);
        r49 = r019;
        int[] r56 = r39;
        r32 = r145;
        int r020 = r292;
    L96:
        long r1210 = r25 + r156;
        if (r375 == null) goto L103;
    L98:
        if (r225 <= 0) goto L103;
        if (r375.readUnsignedIntToInt() != 0) goto L101;
        r375.readInt();
        r225 = r225 - 1;
        goto L98
    L101:
        int r65 = r20;
    L104:
        if (r105 != 0) goto L110;
        if (r342 != 0) goto L110;
        if (r020 != 0) goto L110;
        if (r302 != 0) goto L110;
        if (r31 != 0) goto L110;
        if (r65 == 0) goto L110;
    L115:
        r93 = r86;
        r102 = r118;
        r22 = r1210;
        r123 = r183;
        r83 = r711;
        r63 = r49;
        r75 = r56;
    L110:
        StringBuilder r94 = new StringBuilder("Inconsistent stbl box for track ");
        r94.append(r1.id);
        r94.append(": remainingSynchronizationSamples ");
        r94.append(r105);
        r94.append(", remainingSamplesAtTimestampDelta ");
        r94.append(r342);
        r94.append(", remainingSamplesInChunk ");
        r94.append(r020);
        r94.append(", remainingTimestampDeltaChanges ");
        r94.append(r302);
        r94.append(", remainingSamplesAtTimestampOffset ");
        r94.append(r31);
        if (r65 != 0) goto L113;
        String r021 = ", ctts invalid";
    L114:
        r94.append(r021);
        Log.w("BoxParsers", r94.toString());
        goto L115
    L113:
        r021 = _UrlKt.FRAGMENT_ENCODE_SET;
    L103:
        r65 = 1;
        goto L104
    L95:
        r302 = r128;
        r342 = r135;
        r56 = r64;
        r020 = r28;
        goto L96
    L44:
        if ("audio/raw".equals(r8) == false) goto L46;
    L49:
        if (r10 != 0) goto L53;
        if (r18 != 0) goto L53;
        if (r73 != 0) goto L53;
        r4 = 1;
        goto L54
    L46:
        if ("audio/g711-mlaw".equals(r8) == true) goto L49;
        if ("audio/g711-alaw".equals(r8) == false) goto L53;
    L39:
        r19 = -1;
        r20 = 0;
        r152 = null;
        goto L41
    L40:
        r19 = -1;
        r73 = 0;
        r20 = 0;
        goto L41
    L33:
        r18 = 0;
        goto L35
    L29:
        r02 = null;
        goto L30
    L25:
        r152 = null;
        goto L26
    L20:
        r12 = false;
        goto L21
    L5:
        Mp4Box.LeafBox r310 = r37.getLeafBoxOfType(1937013298);
        if (r310 == null) goto L236;
        r5 = new Stz2SampleSizeBox(r310);
        goto L8
    L236:
        throw ParserException.createForMalformedContainer("Track has no sample table size information", null);
    }

    private static EyesData parseStereoViewBox(ParsableByteArray r6, int r7, int r8) {
        r6.setPosition(r7 + 8);
        int r0 = r6.getPosition();
    L4:
        if ((r0 - r7) >= r8) goto L25;
        r6.setPosition(r0);
        int r1 = r6.readInt();
        boolean r2 = false;
        if (r1 <= 0) goto L8;
        boolean r4 = true;
    L9:
        ExtractorUtil.checkContainerInput(r4, "childAtomSize must be positive");
        if (r6.readInt() == 1937011305) goto L11;
        r0 = r0 + r1;
        goto L4
    L11:
        r6.skipBytes(4);
        int r62 = r6.readUnsignedByte();
        if ((r62 & 1) != 1) goto L14;
        boolean r02 = true;
    L16:
        if ((r62 & 2) != 2) goto L18;
        boolean r12 = true;
    L20:
        if ((r62 & 8) != 8) goto L23;
        r2 = true;
    L23:
        return new EyesData(new StriData(r02, r12, r2));
    L18:
        r12 = false;
        goto L20
    L14:
        r02 = false;
        goto L16
    L8:
        r4 = false;
        goto L9
    L25:
        return null;
    }

    private static StsdData parseStsd(ParsableByteArray r12, TkhdData r13, String r14, DrmInitData r15, boolean r16) {
        r12.setPosition(12);
        int r0 = r12.readInt();
        StsdData r9 = new StsdData(r0);
        int r10 = 0;
    L3:
        if (r10 >= r0) goto L137;
        int r3 = r12.getPosition();
        int r4 = r12.readInt();
        if (r4 <= 0) goto L7;
        boolean r1 = true;
    L8:
        ExtractorUtil.checkContainerInput(r1, "childAtomSize must be positive");
        int r2 = r12.readInt();
        if (r2 != 1635148593) goto L11;
    L135:
        parseVideoSampleEntry(r12, r2, r3, r4, TkhdData.access$300(r13), r14, TkhdData.access$600(r13), r15, r9, r10);
    L136:
        r12.setPosition(r3 + r4);
        r10 = r10 + 1;
        goto L3
    L11:
        if (r2 == 1635148595) goto L135;
        if (r2 == 1701733238) goto L135;
        if (r2 == 1831958048) goto L135;
        if (r2 == 1836070006) goto L135;
        if (r2 == 1752589105) goto L135;
        if (r2 == 1751479857) goto L135;
        if (r2 == 1932670515) goto L135;
        if (r2 == 1211250227) goto L135;
        if (r2 == 1748121139) goto L135;
        if (r2 == 1987063864) goto L135;
        if (r2 == 1987063865) goto L135;
        if (r2 == 1635135537) goto L135;
        if (r2 == 1685479798) goto L135;
        if (r2 == 1685479729) goto L135;
        if (r2 == 1685481573) goto L135;
        if (r2 == 1685481521) goto L135;
        if (r2 == 1634760241) goto L135;
        if (r2 == 1634743416) goto L135;
        if (r2 == 1634743400) goto L135;
        if (r2 == 1634755432) goto L135;
        if (r2 == 1634755438) goto L135;
        if (r2 == 1634755443) goto L135;
        if (r2 == 1634755439) goto L135;
        if (r2 != 1836069985) goto L60;
    L134:
        parseAudioSampleEntry(r12, r2, r3, r4, TkhdData.access$300(r13), r14, r16, r15, r9, r10);
        goto L136
    L60:
        if (r2 == 1701733217) goto L134;
        if (r2 == 1633889587) goto L134;
        if (r2 == 1700998451) goto L134;
        if (r2 == 1633889588) goto L134;
        if (r2 == 1835823201) goto L134;
        if (r2 == 1685353315) goto L134;
        if (r2 == 1685353317) goto L134;
        if (r2 == 1685353320) goto L134;
        if (r2 == 1685353324) goto L134;
        if (r2 == 1685353336) goto L134;
        if (r2 == 1935764850) goto L134;
        if (r2 == 1935767394) goto L134;
        if (r2 == 1819304813) goto L134;
        if (r2 == 1936684916) goto L134;
        if (r2 == 1953984371) goto L134;
        if (r2 == 778924082) goto L134;
        if (r2 == 778924083) goto L134;
        if (r2 == 1835557169) goto L134;
        if (r2 == 1835560241) goto L134;
        if (r2 == 1634492771) goto L134;
        if (r2 == 1634492791) goto L134;
        if (r2 == 1970037111) goto L134;
        if (r2 == 1332770163) goto L134;
        if (r2 == 1716281667) goto L134;
        if (r2 == 1767992678) goto L134;
        if (r2 == 1768973165) goto L134;
        if (r2 == 1718641517) goto L134;
        if (r2 != 1414810956) goto L117;
    L126:
        StsdData r7 = r9;
        parseTextSampleEntry(r12, r2, r3, r4, r13, r14, r7);
        r9 = r7;
        goto L136
    L117:
        if (r2 == 1954034535) goto L126;
        if (r2 == 2004251764) goto L126;
        if (r2 == 1937010800) goto L126;
        if (r2 == 1664495672) goto L126;
        if (r2 == 1836070003) goto L126;
        if (r2 != 1835365492) goto L131;
        parseMetaDataSampleEntry(r12, r2, r3, TkhdData.access$300(r13), r9);
        goto L136
    L131:
        if (r2 != 1667329389) goto L136;
        r9.format = new Format.Builder().setId(TkhdData.access$300(r13)).setSampleMimeType("application/x-camera-motion").build();
        goto L136
    L7:
        r1 = false;
        goto L8
    L137:
        return r9;
    }

    private static void parseTextSampleEntry(ParsableByteArray r4, int r5, int r6, int r7, TkhdData r8, String r9, StsdData r10) {
        r4.setPosition(r6 + 16);
        String r0 = "application/ttml+xml";
        ImmutableList r1 = null;
        long r2 = LongCompanionObject.MAX_VALUE;
        if (r5 != 1414810956) goto L6;
    L28:
        if (r0 == null) goto L34;
        r10.format = new Format.Builder().setId(TkhdData.access$300(r8)).setSampleMimeType(r0).setLanguage(r9).setSubsampleOffsetUs(r2).setInitializationData(r1).build();
        return;
    L34:
        return;
    L6:
        if (r5 != 1954034535) goto L9;
        int r72 = r7 - 16;
        byte[] r52 = new byte[r72];
        r4.readBytes(r52, 0, r72);
        r1 = ImmutableList.of(r52);
        r0 = "application/x-quicktime-tx3g";
        goto L28
    L9:
        if (r5 != 2004251764) goto L12;
        r0 = "application/x-mp4-vtt";
        goto L28
    L12:
        if (r5 != 1937010800) goto L15;
        r2 = 0;
        goto L28
    L15:
        if (r5 != 1664495672) goto L18;
        r10.requiredSampleTransformation = 1;
        r0 = "application/x-mp4-cea-608";
        goto L28
    L18:
        if (r5 != 1836070003) goto L31;
        int r53 = r4.getPosition();
        r4.skipBytes(4);
        if (r4.readInt() != 1702061171) goto L27;
        EsdsData r42 = parseEsdsFromParent(r4, r53);
        if (EsdsData.access$700(r42) != null) goto L24;
        return;
    L24:
        if (EsdsData.access$700(r42).length != 64) goto L35;
        r1 = ImmutableList.of(Util.getUtf8Bytes(formatVobsubIdx(EsdsData.access$700(r42), TkhdData.access$800(r8), TkhdData.access$900(r8))));
        r0 = "application/vobsub";
        goto L28
    L35:
        return;
    L27:
        r0 = null;
        goto L28
    L31:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
    }

    private static TkhdData parseTkhd(ParsableByteArray r12) {
        int r0 = 8;
        r12.setPosition(8);
        int r1 = parseFullBoxVersion(r12.readInt());
        if (r1 != 0) goto L5;
        int r3 = 8;
    L6:
        r12.skipBytes(r3);
        int r5 = r12.readInt();
        r12.skipBytes(4);
        int r4 = r12.getPosition();
        if (r1 != 0) goto L9;
        r0 = 4;
    L9:
        int r7 = 0;
    L10:
        long r8 = -9223372036854775807L;
        if (r7 >= r0) goto L22;
        if (r12.getData()[r4 + r7] != (-1)) goto L14;
        r7 = r7 + 1;
        goto L10
    L14:
        if (r1 != 0) goto L16;
        long r02 = r12.readUnsignedInt();
    L18:
        if (r02 == 0) goto L23;
        r8 = r02;
    L23:
        r12.skipBytes(10);
        int r03 = 0;
        long r6 = r8;
        int r82 = r12.readUnsignedShort();
        r12.skipBytes(4);
        int r13 = r12.readInt();
        int r42 = r12.readInt();
        r12.skipBytes(4);
        int r32 = r12.readInt();
        int r9 = r12.readInt();
        if (r13 != 0) goto L31;
        if (r42 != 65536) goto L31;
        if (r32 == (-65536)) goto L28;
        if (r32 != 65536) goto L31;
    L28:
        if (r9 != 0) goto L31;
        r03 = 90;
    L30:
        int r92 = r03;
        r12.skipBytes(16);
        short r10 = r12.readShort();
        r12.skipBytes(2);
        return new TkhdData(r5, r6, r82, r92, r10, r12.readShort());
    L31:
        if (r13 != 0) goto L37;
        if (r42 != (-65536)) goto L37;
        if (r32 == 65536) goto L35;
        if (r32 != (-65536)) goto L37;
    L35:
        if (r9 != 0) goto L37;
        r03 = 270;
    L37:
        if (r13 == (-65536)) goto L39;
        if (r13 != 65536) goto L30;
    L39:
        if (r42 != 0) goto L30;
        if (r32 != 0) goto L30;
        if (r9 != (-65536)) goto L30;
        r03 = 180;
        goto L30
    L16:
        r02 = r12.readUnsignedLongToLong();
        goto L18
    L22:
        r12.skipBytes(r0);
        goto L23
    L5:
        r3 = 16;
        goto L6
    }

    public static Track parseTrak(Mp4Box.ContainerBox r20, Mp4Box.LeafBox r21, long r22, DrmInitData r24, boolean r25, boolean r26) {
        Mp4Box.ContainerBox r1 = (Mp4Box.ContainerBox) Assertions.checkNotNull(r20.getContainerBoxOfType(1835297121));
        int r5 = getTrackTypeForHdlr(parseHdlr(((Mp4Box.LeafBox) Assertions.checkNotNull(r1.getLeafBoxOfType(1751411826))).data));
        if (r5 != (-1)) goto L5;
        return null;
    L5:
        TkhdData r2 = parseTkhd(((Mp4Box.LeafBox) Assertions.checkNotNull(r20.getLeafBoxOfType(1953196132))).data);
        long r6 = -9223372036854775807L;
        if (r22 != (-9223372036854775807L)) goto L9;
        long r10 = TkhdData.access$000(r2);
    L10:
        long r14 = parseMvhd(r21.data).timescale;
        if (r10 != (-9223372036854775807L)) goto L13;
    L12:
        long r102 = r6;
        Mp4Box.ContainerBox r4 = (Mp4Box.ContainerBox) Assertions.checkNotNull(((Mp4Box.ContainerBox) Assertions.checkNotNull(r1.getContainerBoxOfType(1835626086))).getContainerBoxOfType(1937007212));
        MdhdData r12 = parseMdhd(((Mp4Box.LeafBox) Assertions.checkNotNull(r1.getLeafBoxOfType(1835296868))).data);
        Mp4Box.LeafBox r42 = r4.getLeafBoxOfType(1937011556);
        if (r42 == null) goto L38;
        StsdData r43 = parseStsd(r42.data, r2, MdhdData.access$100(r12), r24, r26);
        if (r25 == true) goto L23;
        Mp4Box.ContainerBox r0 = r20.getContainerBoxOfType(1701082227);
        if (r0 == null) goto L23;
        Pair<long[], long[]> r02 = parseEdts(r0);
        if (r02 == null) goto L23;
        long[] r62 = (long[]) r02.first;
        long[] r19 = (long[]) r02.second;
        long[] r18 = r62;
    L25:
        if (r43.format != null) goto L28;
        return null;
    L28:
        if (TkhdData.access$200(r2) == 0) goto L34;
        Mp4AlternateGroupData r03 = new Mp4AlternateGroupData(TkhdData.access$200(r2));
        Format.Builder r3 = r43.format.buildUpon();
        Metadata r63 = r43.format.metadata;
        if (r63 == null) goto L32;
        Metadata r04 = r63.copyWithAppendedEntries(new Metadata.Entry[]{r03});
    L33:
        Format r05 = r3.setMetadata(r04).build();
    L36:
        return new Track(TkhdData.access$300(r2), r5, MdhdData.access$400(r12), r14, r102, MdhdData.access$500(r12), r05, r43.requiredSampleTransformation, r43.trackEncryptionBoxes, r43.nalUnitLengthFieldLength, r18, r19);
    L32:
        r04 = new Metadata(new Metadata.Entry[]{r03});
        goto L33
    L34:
        r05 = r43.format;
    L23:
        r18 = null;
        r19 = null;
        goto L25
    L38:
        throw ParserException.createForMalformedContainer("Malformed sample table (stbl) missing sample description (stsd)", null);
    L13:
        r6 = Util.scaleLargeTimestamp(r10, 1000000, r14);
        goto L12
    L9:
        r10 = r22;
        goto L10
    }

    public static List<TrackSampleTable> parseTraks(Mp4Box.ContainerBox r10, GaplessInfoHolder r11, long r12, DrmInitData r14, boolean r15, boolean r16, Function<Track, Track> r17) {
        ArrayList r0 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= r10.containerChildren.size()) goto L13;
        Mp4Box.ContainerBox r3 = r10.containerChildren.get(r1);
        if (r3.type != 1953653099) goto L12;
        Track r2 = r17.apply(parseTrak(r3, (Mp4Box.LeafBox) Assertions.checkNotNull(r10.getLeafBoxOfType(1836476516)), r12, r14, r15, r16));
        if (r2 == null) goto L12;
        r0.add(parseStbl(r2, (Mp4Box.ContainerBox) Assertions.checkNotNull(((Mp4Box.ContainerBox) Assertions.checkNotNull(((Mp4Box.ContainerBox) Assertions.checkNotNull(r3.getContainerBoxOfType(1835297121))).getContainerBoxOfType(1835626086))).getContainerBoxOfType(1937007212)), r11));
    L12:
        r1 = r1 + 1;
        goto L4
    L13:
        return r0;
    }

    public static Metadata parseUdta(Mp4Box.LeafBox r6) {
        ParsableByteArray r62 = r6.data;
        r62.setPosition(8);
        Metadata r1 = new Metadata(new Metadata.Entry[0]);
    L4:
        if (r62.bytesLeft() < 8) goto L15;
        int r2 = r62.getPosition();
        int r3 = r62.readInt();
        int r4 = r62.readInt();
        if (r4 != 1835365473) goto L9;
        r62.setPosition(r2);
        r1 = r1.copyWithAppendedEntriesFrom(parseUdtaMeta(r62, r2 + r3));
    L14:
        r62.setPosition(r2 + r3);
        goto L4
    L9:
        if (r4 != 1936553057) goto L12;
        r62.setPosition(r2);
        r1 = r1.copyWithAppendedEntriesFrom(SmtaAtomUtil.parseSmta(r62, r2 + r3));
        goto L14
    L12:
        if (r4 != (-1451722374)) goto L14;
        r1 = r1.copyWithAppendedEntriesFrom(parseXyz(r62));
        goto L14
    L15:
        return r1;
    }

    private static Metadata parseUdtaMeta(ParsableByteArray r4, int r5) {
        r4.skipBytes(8);
        maybeSkipRemainingMetaBoxHeaderBytes(r4);
    L4:
        if (r4.getPosition() >= r5) goto L10;
        int r0 = r4.getPosition();
        int r1 = r4.readInt();
        if (r4.readInt() == 1768715124) goto L7;
        r4.setPosition(r0 + r1);
        goto L4
    L7:
        r4.setPosition(r0);
        return parseIlst(r4, r0 + r1);
    L10:
        return null;
    }

    public static VexuData parseVideoExtendedUsageBox(ParsableByteArray r6, int r7, int r8) {
        r6.setPosition(r7 + 8);
        int r0 = r6.getPosition();
        EyesData r2 = null;
    L4:
        if ((r0 - r7) >= r8) goto L13;
        r6.setPosition(r0);
        int r3 = r6.readInt();
        if (r3 <= 0) goto L8;
        boolean r4 = true;
    L9:
        ExtractorUtil.checkContainerInput(r4, "childAtomSize must be positive");
        if (r6.readInt() != 1702454643) goto L12;
        r2 = parseStereoViewBox(r6, r0, r3);
    L12:
        r0 = r0 + r3;
        goto L4
    L8:
        r4 = false;
        goto L9
    L13:
        if (r2 != null) goto L16;
        return null;
    L16:
        return new VexuData(r2);
    }

    private static void parseVideoSampleEntry(ParsableByteArray r41, int r42, int r43, int r44, int r45, String r46, int r47, DrmInitData r48, StsdData r49, int r50) {
        int r1 = r43;
        int r2 = r44;
        DrmInitData r3 = r48;
        StsdData r4 = r49;
        r41.setPosition(r1 + 16);
        r41.skipBytes(16);
        int r5 = r41.readUnsignedShort();
        int r6 = r41.readUnsignedShort();
        r41.skipBytes(50);
        int r7 = r41.getPosition();
        int r10 = r42;
        if (r10 != 1701733238) goto L12;
        Pair<Integer, TrackEncryptionBox> r8 = parseSampleEntryEncryptionData(r41, r1, r2);
        if (r8 == null) goto L11;
        r10 = ((Integer) r8.first).intValue();
        if (r3 != null) goto L9;
        r3 = null;
    L10:
        r4.trackEncryptionBoxes[r50] = (TrackEncryptionBox) r8.second;
        goto L11
    L9:
        r3 = r3.copyWithSchemeType(((TrackEncryptionBox) r8.second).schemeType);
    L11:
        r41.setPosition(r7);
    L12:
        String r11 = "video/3gpp";
        if (r10 != 1831958048) goto L16;
        String r82 = "video/mpeg";
    L33:
        float r14 = 1.0f;
        int r15 = 8;
        int r13 = 8;
        ByteBuffer r16 = null;
        List<byte[]> r17 = null;
        String r18 = null;
        byte[] r19 = null;
        int r20 = -1;
        int r21 = -1;
        int r22 = -1;
        int r23 = -1;
        int r24 = -1;
        int r25 = -1;
        int r26 = -1;
        int r27 = -1;
        BtrtData r28 = null;
        EsdsData r29 = null;
        NalUnitUtil.H265VpsData r30 = null;
        boolean r31 = false;
    L35:
        if ((r7 - r1) >= r2) goto L40;
        r41.setPosition(r7);
        int r12 = r41.getPosition();
        int r9 = r41.readInt();
        if (r9 == 0) goto L39;
    L41:
        if (r9 <= 0) goto L44;
        boolean r110 = true;
    L45:
        ExtractorUtil.checkContainerInput(r110, "childAtomSize must be positive");
        int r111 = r41.readInt();
        if (r111 != 1635148611) goto L55;
        if (r82 != null) goto L50;
        boolean r112 = true;
    L51:
        ExtractorUtil.checkContainerInput(r112, null);
        r41.setPosition(r12 + 8);
        AvcConfig r113 = AvcConfig.parse(r41);
        List<byte[]> r210 = r113.initializationData;
        r4.nalUnitLengthFieldLength = r113.nalUnitLengthFieldLength;
        if (r31 == true) goto L54;
        r14 = r113.pixelWidthHeightRatio;
    L54:
        String r83 = r113.codecs;
        int r122 = r113.maxNumReorderFrames;
        int r132 = r113.colorSpace;
        int r152 = r113.colorRange;
        r17 = r210;
        int r211 = r113.colorTransfer;
        int r212 = r113.bitdepthLuma;
        NalUnitUtil.H265VpsData r252 = r30;
        DrmInitData r302 = r3;
        NalUnitUtil.H265VpsData r32 = r252;
        int r253 = r113.bitdepthChroma;
        int r322 = r7;
        int r262 = r10;
        String r33 = r11;
        int r34 = r132;
        int r35 = r152;
        r27 = r211;
        r15 = r212;
        r18 = r83;
        r82 = MediaController.VIDEO_MIME_TYPE;
        r21 = r122;
    L262:
        r7 = r322 + r9;
        DrmInitData r114 = r302;
        r30 = r32;
        r3 = r114;
        r1 = r43;
        r2 = r44;
        r4 = r49;
        r13 = r253;
        r10 = r262;
        r11 = r33;
        r25 = r34;
        r26 = r35;
        goto L35
    L50:
        r112 = false;
        goto L51
    L55:
        r322 = r7;
        if (r111 != 1752589123) goto L69;
        if (r82 != null) goto L60;
        boolean r115 = true;
    L61:
        ExtractorUtil.checkContainerInput(r115, null);
        r41.setPosition(r12 + 8);
        HevcConfig r116 = HevcConfig.parse(r41);
        List<byte[]> r213 = r116.initializationData;
        r4.nalUnitLengthFieldLength = r116.nalUnitLengthFieldLength;
        if (r31 == true) goto L64;
        r14 = r116.pixelWidthHeightRatio;
    L64:
        int r84 = r116.maxNumReorderPics;
        int r123 = r116.maxSubLayers;
        String r133 = r116.codecs;
        int r153 = r116.stereoMode;
        r17 = r213;
        if (r153 == (-1)) goto L67;
        r20 = r153;
    L67:
        int r214 = r116.decodedWidth;
        int r154 = r116.decodedHeight;
        int r215 = r116.colorSpace;
        int r216 = r116.colorRange;
        int r217 = r116.colorTransfer;
        int r218 = r116.bitdepthLuma;
        r24 = r154;
        r15 = r218;
        r253 = r116.bitdepthChroma;
        r302 = r3;
        r262 = r10;
        r33 = r11;
        r34 = r215;
        r35 = r216;
        r27 = r217;
        r32 = r116.vpsData;
        r21 = r84;
        r22 = r123;
        r23 = r214;
        r82 = "video/hevc";
        r18 = r133;
        goto L262
    L60:
        r115 = false;
        goto L61
    L69:
        r33 = r11;
        if (r111 != 1818785347) goto L116;
        ExtractorUtil.checkContainerInput("video/hevc".equals(r82), "lhvC must follow hvcC atom");
        NalUnitUtil.H265VpsData r219 = r30;
        if (r219 != null) goto L74;
    L76:
        boolean r117 = false;
    L77:
        ExtractorUtil.checkContainerInput(r117, "must have at least two layers");
        r41.setPosition(r12 + 8);
        HevcConfig r118 = HevcConfig.parseLayered(r41, (NalUnitUtil.H265VpsData) Assertions.checkNotNull(r219));
        if (r4.nalUnitLengthFieldLength != r118.nalUnitLengthFieldLength) goto L80;
        boolean r72 = true;
    L81:
        ExtractorUtil.checkContainerInput(r72, "nalUnitLengthFieldLength must be same for both hvcC and lhvC atoms");
        int r73 = r118.colorSpace;
        int r119 = r25;
        if (r73 == (-1)) goto L87;
        if (r119 != r73) goto L85;
        boolean r74 = true;
    L86:
        ExtractorUtil.checkContainerInput(r74, "colorSpace must be the same for both views");
        goto L87
    L85:
        r74 = false;
    L87:
        int r75 = r118.colorRange;
        int r124 = r26;
        if (r75 == (-1)) goto L93;
        if (r124 != r75) goto L91;
        boolean r76 = true;
    L92:
        ExtractorUtil.checkContainerInput(r76, "colorRange must be the same for both views");
        goto L93
    L91:
        r76 = false;
    L93:
        int r77 = r118.colorTransfer;
        if (r77 == (-1)) goto L101;
        int r85 = r27;
        if (r85 != r77) goto L99;
        boolean r78 = true;
    L98:
        int r182 = r85;
        ExtractorUtil.checkContainerInput(r78, "colorTransfer must be the same for both views");
    L103:
        if (r15 != r118.bitdepthLuma) goto L105;
        boolean r79 = true;
    L106:
        ExtractorUtil.checkContainerInput(r79, "bitdepthLuma must be the same for both views");
        if (r13 != r118.bitdepthChroma) goto L109;
        boolean r710 = true;
    L110:
        ExtractorUtil.checkContainerInput(r710, "bitdepthChroma must be the same for both views");
        List<byte[]> r711 = r17;
        if (r711 == null) goto L113;
        r711 = ImmutableList.builder().addAll(r711).addAll(r118.initializationData).build();
        int r172 = r119;
    L114:
        r82 = "video/mv-hevc";
        r302 = r3;
        r262 = r10;
        r35 = r124;
        r253 = r13;
        r34 = r172;
        r27 = r182;
        r18 = r118.codecs;
        r32 = r219;
        r17 = r711;
        goto L262
    L113:
        r172 = r119;
        ExtractorUtil.checkContainerInput(false, "initializationData must be already set from hvcC atom");
        goto L114
    L109:
        r710 = false;
        goto L110
    L105:
        r79 = false;
        goto L106
    L99:
        r78 = false;
        goto L98
    L101:
        r182 = r27;
        goto L103
    L80:
        r72 = false;
        goto L81
    L74:
        if (r219.layerInfos.size() < 2) goto L76;
        r117 = true;
        goto L77
    L116:
        List<byte[]> r712 = r17;
        r34 = r25;
        r35 = r26;
        int r36 = r27;
        NalUnitUtil.H265VpsData r220 = r30;
        if (r111 != 1986361461) goto L136;
        VexuData r120 = parseVideoExtendedUsageBox(r41, r12, r9);
        if (r120 != null) goto L121;
    L126:
        int r1110 = r20;
    L134:
        r20 = r1110;
    L135:
        r302 = r3;
        r17 = r712;
        r262 = r10;
        r253 = r13;
        r27 = r36;
        r32 = r220;
        goto L262
    L121:
        if (VexuData.access$1000(r120) == null) goto L126;
        if (r220 != null) goto L124;
    L127:
        r1110 = r20;
        if (r1110 != (-1)) goto L134;
        if (StriData.access$1200(EyesData.access$1100(VexuData.access$1000(r120))) == false) goto L133;
        int r121 = 5;
    L132:
        r20 = r121;
        goto L135
    L133:
        r121 = 4;
        goto L132
    L124:
        if (r220.layerInfos.size() < 2) goto L127;
        ExtractorUtil.checkContainerInput(r120.hasBothEyeViews(), "both eye views must be marked as available");
        ExtractorUtil.checkContainerInput(!StriData.access$1200(EyesData.access$1100(VexuData.access$1000(r120))), "for MV-HEVC, eye_views_reversed must be set to false");
        goto L126
    L136:
        int r1111 = r20;
        if (r111 != 1685480259) goto L139;
    L142:
        r302 = r3;
        r262 = r10;
        int r37 = r1111;
        r253 = r13;
        float r40 = r14;
        int r272 = r15;
        int r38 = r36;
        int r410 = r9 - 8;
        byte[] r102 = new byte[r410];
        r41.readBytes(r102, 0, r410);
        if (r712 == null) goto L257;
        r17 = ImmutableList.builder().addAll(r712).add(r102).build();
    L258:
        r41.setPosition(r12 + 8);
        DolbyVisionConfig r411 = DolbyVisionConfig.parse(r41);
        if (r411 == null) goto L261;
        r82 = "video/dolby-vision";
        r18 = r411.codecs;
    L261:
        r34 = r34;
        r15 = r272;
        r14 = r40;
    L253:
        r27 = r38;
        r32 = r220;
        r20 = r37;
        goto L262
    L257:
        ExtractorUtil.checkContainerInput(false, "initializationData must already be set from hvcC or avcC atom");
        r17 = r712;
        goto L258
    L139:
        if (r111 == 1685485123) goto L142;
        if (r111 == 1685485379) goto L142;
        if (r111 != 1987076931) goto L165;
        if (r82 != null) goto L148;
        boolean r125 = true;
    L149:
        ExtractorUtil.checkContainerInput(r125, null);
        if (r10 != 1987063864) goto L152;
        String r126 = "video/x-vnd.on2.vp8";
    L153:
        r41.setPosition(r12 + 12);
        byte r86 = (byte) r41.readUnsignedByte();
        byte r127 = (byte) r41.readUnsignedByte();
        int r134 = r41.readUnsignedByte();
        r15 = r134 >> 4;
        r262 = r10;
        byte r103 = (byte) ((r134 >> 1) & 7);
        if (r126.equals("video/x-vnd.on2.vp9") == false) goto L157;
        r712 = CodecSpecificDataUtil.buildVp9CodecPrivateInitializationData(r86, r127, (byte) r15, r103);
    L157:
        if ((r134 & 1) == 0) goto L159;
        boolean r221 = true;
    L160:
        int r87 = r41.readUnsignedByte();
        int r104 = r41.readUnsignedByte();
        int r254 = ColorInfo.isoColorPrimariesToColorSpace(r87);
        if (r221 == false) goto L163;
        int r173 = 1;
    L164:
        r27 = ColorInfo.isoTransferCharacteristicsToColorTransfer(r104);
        r82 = r126;
        r302 = r3;
        r35 = r173;
        r32 = r220;
        r34 = r254;
        r17 = r712;
        r20 = r1111;
        r253 = r15;
        goto L262
    L163:
        r173 = 2;
        goto L164
    L159:
        r221 = false;
        goto L160
    L152:
        r126 = "video/x-vnd.on2.vp9";
        goto L153
    L148:
        r125 = false;
        goto L149
    L165:
        r262 = r10;
        if (r111 != 1635135811) goto L170;
        int r128 = r9 - 8;
        byte[] r222 = new byte[r128];
        r41.readBytes(r222, 0, r128);
        r17 = ImmutableList.of(r222);
        r41.setPosition(r12 + 8);
        ColorInfo r129 = parseAv1c(r41);
        int r223 = r129.lumaBitdepth;
        int r713 = r129.chromaBitdepth;
        int r88 = r129.colorSpace;
        int r105 = r129.colorRange;
        r27 = r129.colorTransfer;
        r15 = r223;
        r302 = r3;
        r253 = r713;
        r34 = r88;
        r35 = r105;
        r82 = "video/av01";
        r32 = r220;
    L168:
        r20 = r1111;
        goto L262
    L170:
        if (r111 != 1668050025) goto L176;
        if (r16 != null) goto L173;
        ByteBuffer r130 = allocateHdrStaticInfo();
    L174:
        r130.position(21);
        r130.putShort(r41.readShort());
        r130.putShort(r41.readShort());
        r16 = r130;
        r302 = r3;
        r17 = r712;
        r253 = r13;
        r32 = r220;
        r27 = r36;
        goto L168
    L173:
        r130 = r16;
        goto L174
    L176:
        if (r111 != 1835295606) goto L181;
        if (r16 != null) goto L179;
        ByteBuffer r131 = allocateHdrStaticInfo();
    L180:
        short r224 = r41.readShort();
        short r106 = r41.readShort();
        short r1210 = r41.readShort();
        short r412 = r41.readShort();
        r253 = r13;
        short r135 = r41.readShort();
        int r273 = r15;
        short r155 = r41.readShort();
        r302 = r3;
        short r39 = r41.readShort();
        short r1112 = r41.readShort();
        long r162 = r41.readUnsignedInt();
        long r382 = r41.readUnsignedInt();
        r131.position(1);
        r131.putShort(r135);
        r131.putShort(r155);
        r131.putShort(r224);
        r131.putShort(r106);
        r131.putShort(r1210);
        r131.putShort(r412);
        r131.putShort(r39);
        r131.putShort(r1112);
        r131.putShort((short) (r162 / NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS));
        r131.putShort((short) (r382 / NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS));
        r16 = r131;
        r17 = r712;
        r32 = r220;
        r15 = r273;
        r27 = r36;
        r20 = r1111;
        r14 = r14;
        goto L262
    L179:
        r131 = r16;
        goto L180
    L181:
        r302 = r3;
        r37 = r1111;
        r253 = r13;
        float r402 = r14;
        int r274 = r15;
        if (r111 != 1681012275) goto L191;
        if (r82 != null) goto L186;
        boolean r136 = true;
    L187:
        ExtractorUtil.checkContainerInput(r136, null);
        r17 = r712;
        r32 = r220;
        r15 = r274;
        r82 = r33;
    L188:
        r27 = r36;
    L189:
        r20 = r37;
        r14 = r402;
        goto L262
    L186:
        r136 = false;
        goto L187
    L191:
        if (r111 != 1702061171) goto L202;
        if (r82 != null) goto L194;
        boolean r137 = true;
    L195:
        ExtractorUtil.checkContainerInput(r137, null);
        r29 = parseEsdsFromParent(r41, r12);
        String r138 = EsdsData.access$1300(r29);
        byte[] r310 = EsdsData.access$700(r29);
        if (r310 == null) goto L198;
        r17 = ImmutableList.of(r310);
    L199:
        r82 = r138;
    L200:
        r32 = r220;
        r15 = r274;
        goto L188
    L198:
        r17 = r712;
        goto L199
    L194:
        r137 = false;
        goto L195
    L202:
        if (r111 != 1651798644) goto L206;
        r28 = parseBtrtFromParent(r41, r12);
    L204:
        r17 = r712;
        goto L200
    L206:
        if (r111 != 1885434736) goto L209;
        r14 = parsePaspFromParent(r41, r12);
        r17 = r712;
        r32 = r220;
        r15 = r274;
        r27 = r36;
        r20 = r37;
        r31 = true;
        goto L262
    L209:
        if (r111 != 1937126244) goto L212;
        r19 = parseProjFromParent(r41, r12, r9);
        goto L204
    L212:
        if (r111 != 1936995172) goto L228;
        int r139 = r41.readUnsignedByte();
        r41.skipBytes(3);
        if (r139 != 0) goto L204;
        int r140 = r41.readUnsignedByte();
        if (r140 != 0) goto L218;
        r37 = 0;
        goto L204
    L218:
        if (r140 != 1) goto L220;
        r37 = 1;
        goto L204
    L220:
        if (r140 == 2) goto L224;
        if (r140 != 3) goto L204;
        r37 = 3;
        goto L204
    L224:
        r37 = 2;
        goto L204
    L228:
        if (r111 != 1634760259) goto L231;
        int r141 = r9 - 12;
        byte[] r311 = new byte[r141];
        r41.setPosition(r12 + 12);
        r41.readBytes(r311, 0, r141);
        r17 = ImmutableList.of(r311);
        ColorInfo r142 = parseApvc(new ParsableByteArray(r311));
        int r312 = r142.lumaBitdepth;
        int r413 = r142.chromaBitdepth;
        int r714 = r142.colorSpace;
        int r89 = r142.colorRange;
        r27 = r142.colorTransfer;
        r15 = r312;
        r253 = r413;
        r34 = r714;
        r35 = r89;
        r82 = "video/apv";
        r32 = r220;
        goto L189
    L231:
        if (r111 != 1668246642) goto L251;
        r38 = r36;
        if (r34 != (-1)) goto L252;
        if (r38 != (-1)) goto L252;
        int r414 = r41.readInt();
        if (r414 != 1852009592) goto L238;
    L241:
        int r313 = r41.readUnsignedShort();
        int r415 = r41.readUnsignedShort();
        int r1211 = 2;
        r41.skipBytes(2);
        if (r9 == 19) goto L244;
    L246:
        boolean r1113 = false;
    L247:
        int r314 = ColorInfo.isoColorPrimariesToColorSpace(r313);
        if (r1113 == false) goto L250;
        r1211 = 1;
    L250:
        r34 = r314;
        r17 = r712;
        r35 = r1211;
        r32 = r220;
        r15 = r274;
        r20 = r37;
        r14 = r402;
        r27 = ColorInfo.isoTransferCharacteristicsToColorTransfer(r415);
        goto L262
    L244:
        if ((r41.readUnsignedByte() & 128) == 0) goto L246;
        r1113 = true;
        goto L247
    L238:
        if (r414 == 1852009571) goto L241;
        Log.w("BoxParsers", "Unsupported color type: " + Mp4Box.getBoxTypeString(r414));
    L252:
        r17 = r712;
        r34 = r34;
        r15 = r274;
        r14 = r402;
        goto L253
    L251:
        r38 = r36;
        goto L252
    L44:
        r110 = false;
        goto L45
    L39:
        if ((r41.getPosition() - r43) != r2) goto L41;
    L40:
        DrmInitData r303 = r3;
        float r403 = r14;
        List<byte[]> r715 = r17;
        int r372 = r20;
        int r1114 = r25;
        int r352 = r26;
        int r315 = r27;
        int r255 = r13;
        int r275 = r15;
        if (r82 != null) goto L265;
        return;
    L265:
        Format.Builder r0 = new Format.Builder().setId(r45).setSampleMimeType(r82).setCodecs(r18).setWidth(r5).setHeight(r6).setDecodedWidth(r23).setDecodedHeight(r24).setPixelWidthHeightRatio(r403).setRotationDegrees(r47).setProjectionData(r19).setStereoMode(r372).setInitializationData(r715).setMaxNumReorderSamples(r21).setMaxSubLayers(r22).setDrmInitData(r303).setLanguage(r46);
        ColorInfo.Builder r143 = new ColorInfo.Builder().setColorSpace(r1114).setColorRange(r352).setColorTransfer(r315);
        if (r16 == null) goto L268;
        byte[] r92 = r16.array();
    L269:
        Format.Builder r02 = r0.setColorInfo(r143.setHdrStaticInfo(r92).setLumaBitdepth(r275).setChromaBitdepth(r255).build());
        if (r28 == null) goto L272;
        r02.setAverageBitrate(Ints.saturatedCast(BtrtData.access$1500(r28))).setPeakBitrate(Ints.saturatedCast(BtrtData.access$1400(r28)));
    L274:
        r49.format = r02.build();
        return;
    L272:
        if (r29 == null) goto L274;
        r02.setAverageBitrate(Ints.saturatedCast(EsdsData.access$1700(r29))).setPeakBitrate(Ints.saturatedCast(EsdsData.access$1600(r29)));
        goto L274
    L268:
        r92 = null;
        goto L269
    L16:
        if (r10 != 1211250227) goto L19;
        r82 = "video/3gpp";
        goto L33
    L19:
        if (r10 != 1634743416) goto L21;
    L32:
        r82 = "video/prores";
        goto L33
    L21:
        if (r10 == 1634743400) goto L32;
        if (r10 == 1634755432) goto L32;
        if (r10 == 1634755438) goto L32;
        if (r10 == 1634755443) goto L32;
        if (r10 == 1634755439) goto L32;
        r82 = null;
        goto L33
    }

    private static Metadata parseXyz(ParsableByteArray r5) {
        short r0 = r5.readShort();
        r5.skipBytes(2);
        String r52 = r5.readString(r0);
        int r02 = Math.max(r52.lastIndexOf(43), r52.lastIndexOf(45));
        return new Metadata(new Metadata.Entry[]{new Mp4LocationData(Float.parseFloat(r52.substring(0, r02)), Float.parseFloat(r52.substring(r02, r52.length() - 1)))});
    L5:
        return null;
    }

    private static int vobsubYuvToRgb(int r5) {
        int r0 = (r5 >> 16) & 255;
        int r2 = ((r5 >> 8) & 255) - 128;
        int r52 = (r5 & 255) - 128;
        return Util.constrainValue(r0 + ((r52 * 17790) / 10000), 0, 255) | ((Util.constrainValue(((r2 * 14075) / 10000) + r0, 0, 255) << 16) | (Util.constrainValue((r0 - ((r52 * 3455) / 10000)) - ((r2 * 7169) / 10000), 0, 255) << 8));
    }
}
