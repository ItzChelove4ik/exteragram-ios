package androidx.media3.extractor.ts;

import android.util.SparseArray;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.container.ParsableNalUnitBitArray;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import java.util.ArrayList;
import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.telegram.messenger.MediaController;

/* loaded from: classes4.dex */
public final class H264Reader implements ElementaryStreamReader {
    private final boolean allowNonIdrKeyframes;
    private final String containerMimeType;
    private final boolean detectAccessUnits;
    private String formatId;
    private boolean hasOutputFormat;
    private TrackOutput output;
    private long pesTimeUs;
    private final NalUnitTargetBuffer pps;
    private final boolean[] prefixFlags;
    private boolean randomAccessIndicator;
    private SampleReader sampleReader;
    private final NalUnitTargetBuffer sei;
    private final SeiReader seiReader;
    private final ParsableByteArray seiWrapper;
    private final NalUnitTargetBuffer sps;
    private long totalBytesWritten;

    /* renamed from: androidx.media3.extractor.ts.H264Reader$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class SampleReader {
        private final boolean allowNonIdrKeyframes;
        private final ParsableNalUnitBitArray bitArray;
        private byte[] buffer;
        private int bufferLength;
        private final boolean detectAccessUnits;
        private boolean isFilling;
        private long nalUnitStartPosition;
        private long nalUnitTimeUs;
        private int nalUnitType;
        private final TrackOutput output;
        private final SparseArray<NalUnitUtil.PpsData> pps;
        private SliceHeaderData previousSliceHeader;
        private boolean randomAccessIndicator;
        private boolean readingSample;
        private boolean sampleIsKeyframe;
        private long samplePosition;
        private long sampleTimeUs;
        private SliceHeaderData sliceHeader;
        private final SparseArray<NalUnitUtil.SpsData> sps;

        public static final class SliceHeaderData {
            private boolean bottomFieldFlag;
            private boolean bottomFieldFlagPresent;
            private int deltaPicOrderCnt0;
            private int deltaPicOrderCnt1;
            private int deltaPicOrderCntBottom;
            private boolean fieldPicFlag;
            private int frameNum;
            private boolean hasSliceType;
            private boolean idrPicFlag;
            private int idrPicId;
            private boolean isComplete;
            private int nalRefIdc;
            private int picOrderCntLsb;
            private int picParameterSetId;
            private int sliceType;
            private NalUnitUtil.SpsData spsData;

            private SliceHeaderData() {
            }

            public /* synthetic */ SliceHeaderData(AnonymousClass1 r1) {
                this();
            }

            public static /* synthetic */ boolean access$100(SliceHeaderData r0, SliceHeaderData r1) {
                return r0.isFirstVclNalUnitOfPicture(r1);
            }

            private boolean isFirstVclNalUnitOfPicture(SliceHeaderData r7) {
                if (this.isComplete == true) goto L6;
                return false;
            L6:
                if (r7.isComplete == true) goto L8;
                return true;
            L8:
                NalUnitUtil.SpsData r0 = (NalUnitUtil.SpsData) Assertions.checkStateNotNull(this.spsData);
                NalUnitUtil.SpsData r3 = (NalUnitUtil.SpsData) Assertions.checkStateNotNull(r7.spsData);
                if (this.frameNum == r7.frameNum) goto L11;
            L46:
                return true;
            L11:
                if (this.picParameterSetId != r7.picParameterSetId) goto L46;
                if (this.fieldPicFlag != r7.fieldPicFlag) goto L46;
                if (this.bottomFieldFlagPresent == true) goto L17;
            L20:
                int r4 = this.nalRefIdc;
                int r5 = r7.nalRefIdc;
                if (r4 == r5) goto L24;
                if (r4 == 0) goto L46;
                if (r5 == 0) goto L46;
            L24:
                int r02 = r0.picOrderCountType;
                if (r02 == 0) goto L27;
            L32:
                if (r02 == 1) goto L34;
            L39:
                boolean r03 = this.idrPicFlag;
                if (r03 != r7.idrPicFlag) goto L46;
                if (r03 == true) goto L43;
            L45:
                return false;
            L43:
                if (this.idrPicId == r7.idrPicId) goto L45;
            L34:
                if (r3.picOrderCountType != 1) goto L39;
                if (this.deltaPicOrderCnt0 != r7.deltaPicOrderCnt0) goto L46;
                if (this.deltaPicOrderCnt1 != r7.deltaPicOrderCnt1) goto L46;
            L27:
                if (r3.picOrderCountType != 0) goto L32;
                if (this.picOrderCntLsb != r7.picOrderCntLsb) goto L46;
                if (this.deltaPicOrderCntBottom != r7.deltaPicOrderCntBottom) goto L46;
            L17:
                if (r7.bottomFieldFlagPresent == false) goto L20;
                if (this.bottomFieldFlag != r7.bottomFieldFlag) goto L46;
                goto L20
            }

            public void clear() {
                this.hasSliceType = false;
                this.isComplete = false;
            }

            public boolean isISlice() {
                if (this.hasSliceType == false) goto L10;
                int r1 = this.sliceType;
                if (r1 != 7) goto L7;
                return true;
            L7:
                if (r1 != 2) goto L13;
                return true;
            L13:
                return false;
            L10:
                return false;
            }

            public void setAll(NalUnitUtil.SpsData r1, int r2, int r3, int r4, int r5, boolean r6, boolean r7, boolean r8, boolean r9, int r10, int r11, int r12, int r13, int r14) {
                this.spsData = r1;
                this.nalRefIdc = r2;
                this.sliceType = r3;
                this.frameNum = r4;
                this.picParameterSetId = r5;
                this.fieldPicFlag = r6;
                this.bottomFieldFlagPresent = r7;
                this.bottomFieldFlag = r8;
                this.idrPicFlag = r9;
                this.idrPicId = r10;
                this.picOrderCntLsb = r11;
                this.deltaPicOrderCntBottom = r12;
                this.deltaPicOrderCnt0 = r13;
                this.deltaPicOrderCnt1 = r14;
                this.isComplete = true;
                this.hasSliceType = true;
            }

            public void setSliceType(int r1) {
                this.sliceType = r1;
                this.hasSliceType = true;
            }
        }

        public SampleReader(TrackOutput r1, boolean r2, boolean r3) {
            this.output = r1;
            this.allowNonIdrKeyframes = r2;
            this.detectAccessUnits = r3;
            this.sps = new SparseArray();
            this.pps = new SparseArray();
            this.previousSliceHeader = new SliceHeaderData(null);
            this.sliceHeader = new SliceHeaderData(null);
            byte[] r12 = new byte[128];
            this.buffer = r12;
            this.bitArray = new ParsableNalUnitBitArray(r12, 0, 0);
            reset();
        }

        private void outputSample(int r10) {
            long r1 = this.sampleTimeUs;
            if (r1 == (-9223372036854775807L)) goto L9;
            long r3 = this.nalUnitStartPosition;
            long r5 = this.samplePosition;
            if (r3 == r5) goto L10;
            boolean r32 = this.sampleIsKeyframe;
            int r4 = (int) (r3 - r5);
            this.output.sampleMetadata(r1, r32 ? 1 : 0, r4, r10, null);
            return;
        L10:
            return;
        }

        private void setSampleIsKeyframe() {
            if (this.allowNonIdrKeyframes == false) goto L5;
            boolean r0 = this.sliceHeader.isISlice();
        L6:
            boolean r1 = this.sampleIsKeyframe;
            int r2 = this.nalUnitType;
            boolean r4 = true;
            if (r2 == 5) goto L12;
            if (r0 == false) goto L11;
            if (r2 == 1) goto L12;
        L11:
            r4 = false;
        L12:
            this.sampleIsKeyframe = r1 | r4;
            return;
        L5:
            r0 = this.randomAccessIndicator;
            goto L6
        }

        public void appendToNalUnit(byte[] r24, int r25, int r26) {
            if (this.isFilling == false) goto L93;
            int r2 = r26 - r25;
            byte[] r3 = this.buffer;
            int r4 = r3.length;
            int r5 = this.bufferLength;
            if (r4 >= (r5 + r2)) goto L8;
            this.buffer = Arrays.copyOf(r3, (r5 + r2) * 2);
        L8:
            System.arraycopy(r24, r25, this.buffer, this.bufferLength, r2);
            int r1 = this.bufferLength + r2;
            this.bufferLength = r1;
            this.bitArray.reset(this.buffer, 0, r1);
            if (this.bitArray.canReadBits(8) == false) goto L94;
            this.bitArray.skipBit();
            int r10 = this.bitArray.readBits(2);
            this.bitArray.skipBits(5);
            if (this.bitArray.canReadExpGolombCodedNum() == false) goto L95;
            this.bitArray.readUnsignedExpGolombCodedInt();
            if (this.bitArray.canReadExpGolombCodedNum() == false) goto L96;
            int r11 = this.bitArray.readUnsignedExpGolombCodedInt();
            if (this.detectAccessUnits == true) goto L22;
            this.isFilling = false;
            this.sliceHeader.setSliceType(r11);
            return;
        L22:
            if (this.bitArray.canReadExpGolombCodedNum() == false) goto L97;
            int r13 = this.bitArray.readUnsignedExpGolombCodedInt();
            if (this.pps.indexOfKey(r13) >= 0) goto L28;
            this.isFilling = false;
            return;
        L28:
            NalUnitUtil.PpsData r12 = this.pps.get(r13);
            NalUnitUtil.SpsData r9 = this.sps.get(r12.seqParameterSetId);
            if (r9.separateColorPlaneFlag == false) goto L35;
            if (this.bitArray.canReadBits(2) == false) goto L98;
            this.bitArray.skipBits(2);
            goto L35
        L98:
            return;
        L35:
            if (this.bitArray.canReadBits(r9.frameNumLength) == false) goto L99;
            int r122 = this.bitArray.readBits(r9.frameNumLength);
            if (r9.frameMbsOnlyFlag == false) goto L40;
            boolean r14 = false;
            boolean r15 = false;
        L49:
            boolean r16 = r15;
        L52:
            if (this.nalUnitType != 5) goto L54;
            boolean r17 = true;
        L55:
            if (r17 == true) goto L57;
            int r18 = 0;
        L61:
            int r22 = r9.picOrderCountType;
            if (r22 == 0) goto L64;
            if (r22 == 1) goto L78;
        L90:
            int r19 = 0;
            int r20 = 0;
        L75:
            int r21 = r20;
        L73:
            int r222 = r21;
        L91:
            this.sliceHeader.setAll(r9, r10, r11, r122, r13, r14, r15, r16, r17, r18, r19, r20, r21, r222);
            this.isFilling = false;
            return;
        L78:
            if (r9.deltaPicOrderAlwaysZeroFlag == true) goto L90;
            if (this.bitArray.canReadExpGolombCodedNum() == false) goto L105;
            int r23 = this.bitArray.readSignedExpGolombCodedInt();
            if (r12.bottomFieldPicOrderInFramePresentFlag == false) goto L89;
            if (r14 == true) goto L89;
            if (this.bitArray.canReadExpGolombCodedNum() == true) goto L88;
            return;
        L88:
            r222 = this.bitArray.readSignedExpGolombCodedInt();
            r21 = r23;
            r19 = 0;
            r20 = 0;
        L89:
            r21 = r23;
            r19 = 0;
            r20 = 0;
            r222 = 0;
            goto L91
        L105:
            return;
        L64:
            if (this.bitArray.canReadBits(r9.picOrderCntLsbLength) == false) goto L103;
            int r27 = this.bitArray.readBits(r9.picOrderCntLsbLength);
            if (r12.bottomFieldPicOrderInFramePresentFlag == false) goto L74;
            if (r14 == true) goto L74;
            if (this.bitArray.canReadExpGolombCodedNum() == false) goto L104;
            r20 = this.bitArray.readSignedExpGolombCodedInt();
            r19 = r27;
            r21 = 0;
            goto L73
        L104:
            return;
        L74:
            r19 = r27;
            r20 = 0;
            goto L75
        L103:
            return;
        L57:
            if (this.bitArray.canReadExpGolombCodedNum() == false) goto L102;
            r18 = this.bitArray.readUnsignedExpGolombCodedInt();
            goto L61
        L102:
            return;
        L54:
            r17 = false;
            goto L55
        L40:
            if (this.bitArray.canReadBits(1) == false) goto L100;
            boolean r32 = this.bitArray.readBit();
            if (r32 == true) goto L45;
            r14 = r32;
            r15 = false;
            goto L49
        L45:
            if (this.bitArray.canReadBits(1) == false) goto L101;
            r14 = r32;
            r15 = true;
            r16 = this.bitArray.readBit();
            goto L52
        L101:
            return;
        L100:
            return;
        L99:
            return;
        L97:
            return;
        L96:
            return;
        L95:
            return;
        L94:
            return;
        }

        public boolean endNalUnit(long r3, int r5, boolean r6) {
            if (this.nalUnitType != 9) goto L5;
        L8:
            if (r6 == true) goto L10;
        L12:
            this.samplePosition = this.nalUnitStartPosition;
            this.sampleTimeUs = this.nalUnitTimeUs;
            this.sampleIsKeyframe = false;
            this.readingSample = true;
        L13:
            setSampleIsKeyframe();
            this.nalUnitType = 24;
            return this.sampleIsKeyframe;
        L10:
            if (this.readingSample == false) goto L12;
            outputSample(r5 + ((int) (r3 - this.nalUnitStartPosition)));
            goto L12
        L5:
            if (this.detectAccessUnits == false) goto L13;
            if (SliceHeaderData.access$100(this.sliceHeader, this.previousSliceHeader) == false) goto L13;
            goto L8
        }

        public boolean needsSpsPps() {
            return this.detectAccessUnits;
        }

        public void putPps(NalUnitUtil.PpsData r2) {
            this.pps.append(r2.picParameterSetId, r2);
        }

        public void putSps(NalUnitUtil.SpsData r2) {
            this.sps.append(r2.seqParameterSetId, r2);
        }

        public void reset() {
            this.isFilling = false;
            this.readingSample = false;
            this.sliceHeader.clear();
        }

        public void startNalUnit(long r1, int r3, long r4, boolean r6) {
            this.nalUnitType = r3;
            this.nalUnitTimeUs = r4;
            this.nalUnitStartPosition = r1;
            this.randomAccessIndicator = r6;
            if (this.allowNonIdrKeyframes == false) goto L6;
            if (r3 != 1) goto L6;
        L12:
            SliceHeaderData r12 = this.previousSliceHeader;
            this.previousSliceHeader = this.sliceHeader;
            this.sliceHeader = r12;
            r12.clear();
            this.bufferLength = 0;
            this.isFilling = true;
            return;
        L6:
            if (this.detectAccessUnits == true) goto L8;
            return;
        L8:
            if (r3 == 5) goto L12;
            if (r3 == 1) goto L12;
            if (r3 == 2) goto L12;
        }
    }

    public H264Reader(SeiReader r1, boolean r2, boolean r3, String r4) {
        this.seiReader = r1;
        this.allowNonIdrKeyframes = r2;
        this.detectAccessUnits = r3;
        this.containerMimeType = r4;
        this.prefixFlags = new boolean[3];
        this.sps = new NalUnitTargetBuffer(7, 128);
        this.pps = new NalUnitTargetBuffer(8, 128);
        this.sei = new NalUnitTargetBuffer(6, 128);
        this.pesTimeUs = -9223372036854775807L;
        this.seiWrapper = new ParsableByteArray();
    }

    @EnsuresNonNull({"output", "sampleReader"})
    private void assertTracksCreated() {
        Assertions.checkStateNotNull(this.output);
        Util.castNonNull(this.sampleReader);
    }

    @RequiresNonNull({"output", "sampleReader"})
    private void endNalUnit(long r8, int r10, int r11, long r12) {
        if (this.hasOutputFormat == true) goto L5;
    L6:
        this.sps.endNalUnit(r11);
        this.pps.endNalUnit(r11);
        boolean r0 = this.hasOutputFormat;
        NalUnitTargetBuffer r1 = this.sps;
        if (r0 == true) goto L14;
        if (r1.isCompleted() == true) goto L11;
    L20:
        if (this.sei.endNalUnit(r11) == false) goto L23;
        NalUnitTargetBuffer r112 = this.sei;
        int r113 = NalUnitUtil.unescapeStream(r112.nalData, r112.nalLength);
        this.seiWrapper.reset(this.sei.nalData, r113);
        this.seiWrapper.setPosition(4);
        this.seiReader.consume(r12, this.seiWrapper);
    L23:
        if (this.sampleReader.endNalUnit(r8, r10, this.hasOutputFormat) == false) goto L26;
        this.randomAccessIndicator = false;
        return;
    L26:
        return;
    L11:
        if (this.pps.isCompleted() == false) goto L20;
        ArrayList r02 = new ArrayList();
        NalUnitTargetBuffer r13 = this.sps;
        r02.add(Arrays.copyOf(r13.nalData, r13.nalLength));
        NalUnitTargetBuffer r14 = this.pps;
        r02.add(Arrays.copyOf(r14.nalData, r14.nalLength));
        NalUnitTargetBuffer r15 = this.sps;
        NalUnitUtil.SpsData r16 = NalUnitUtil.parseSpsNalUnit(r15.nalData, 3, r15.nalLength);
        NalUnitTargetBuffer r3 = this.pps;
        NalUnitUtil.PpsData r2 = NalUnitUtil.parsePpsNalUnit(r3.nalData, 3, r3.nalLength);
        String r32 = CodecSpecificDataUtil.buildAvcCodecString(r16.profileIdc, r16.constraintsFlagsAndReservedZero2Bits, r16.levelIdc);
        this.output.format(new Format.Builder().setId(this.formatId).setContainerMimeType(this.containerMimeType).setSampleMimeType(MediaController.VIDEO_MIME_TYPE).setCodecs(r32).setWidth(r16.width).setHeight(r16.height).setColorInfo(new ColorInfo.Builder().setColorSpace(r16.colorSpace).setColorRange(r16.colorRange).setColorTransfer(r16.colorTransfer).setLumaBitdepth(r16.bitDepthLumaMinus8 + 8).setChromaBitdepth(r16.bitDepthChromaMinus8 + 8).build()).setPixelWidthHeightRatio(r16.pixelWidthHeightRatio).setInitializationData(r02).setMaxNumReorderSamples(r16.maxNumReorderFrames).build());
        this.hasOutputFormat = true;
        this.seiReader.setReorderingQueueSize(r16.maxNumReorderFrames);
        this.sampleReader.putSps(r16);
        this.sampleReader.putPps(r2);
        this.sps.reset();
        this.pps.reset();
        goto L20
    L14:
        if (r1.isCompleted() == false) goto L17;
        NalUnitTargetBuffer r03 = this.sps;
        NalUnitUtil.SpsData r04 = NalUnitUtil.parseSpsNalUnit(r03.nalData, 3, r03.nalLength);
        this.seiReader.setReorderingQueueSize(r04.maxNumReorderFrames);
        this.sampleReader.putSps(r04);
        this.sps.reset();
        goto L20
    L17:
        if (this.pps.isCompleted() == false) goto L20;
        NalUnitTargetBuffer r05 = this.pps;
        NalUnitUtil.PpsData r06 = NalUnitUtil.parsePpsNalUnit(r05.nalData, 3, r05.nalLength);
        this.sampleReader.putPps(r06);
        this.pps.reset();
        goto L20
    L5:
        if (this.sampleReader.needsSpsPps() == false) goto L20;
        goto L6
    }

    @RequiresNonNull({"sampleReader"})
    private void nalUnitData(byte[] r2, int r3, int r4) {
        if (this.hasOutputFormat == true) goto L5;
    L6:
        this.sps.appendToNalUnit(r2, r3, r4);
        this.pps.appendToNalUnit(r2, r3, r4);
    L7:
        this.sei.appendToNalUnit(r2, r3, r4);
        this.sampleReader.appendToNalUnit(r2, r3, r4);
        return;
    L5:
        if (this.sampleReader.needsSpsPps() == false) goto L7;
        goto L6
    }

    @RequiresNonNull({"sampleReader"})
    private void startNalUnit(long r9, int r11, long r12) {
        if (this.hasOutputFormat == true) goto L5;
    L6:
        this.sps.startNalUnit(r11);
        this.pps.startNalUnit(r11);
    L7:
        this.sei.startNalUnit(r11);
        this.sampleReader.startNalUnit(r9, r11, r12, this.randomAccessIndicator);
        return;
    L5:
        if (this.sampleReader.needsSpsPps() == false) goto L7;
        goto L6
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray r15) {
        assertTracksCreated();
        int r1 = r15.getPosition();
        int r7 = r15.limit();
        byte[] r8 = r15.getData();
        this.totalBytesWritten += r15.bytesLeft();
        this.output.sampleData(r15, r15.bytesLeft());
    L3:
        int r2 = NalUnitUtil.findNalUnit(r8, r1, r7, this.prefixFlags);
        if (r2 == r7) goto L5;
        int r9 = NalUnitUtil.getNalUnitType(r8, r2);
        if (r2 > 0) goto L10;
    L13:
        int r3 = 3;
    L12:
        int r10 = r2;
        int r11 = r3;
        int r22 = r10 - r1;
        if (r22 <= 0) goto L17;
        nalUnitData(r8, r1, r10);
    L17:
        int r32 = r7 - r10;
        long r4 = this.totalBytesWritten - r32;
        if (r22 >= 0) goto L21;
        int r12 = -r22;
    L22:
        endNalUnit(r4, r32, r12, this.pesTimeUs);
        startNalUnit(r4, r9, this.pesTimeUs);
        r1 = r10 + r11;
        goto L3
    L21:
        r12 = 0;
        goto L22
    L10:
        if (r8[r2 - 1] != 0) goto L13;
        r2 = r2 - 1;
        r3 = 4;
        goto L12
    L5:
        nalUnitData(r8, r1, r7);
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput r5, TsPayloadReader.TrackIdGenerator r6) {
        r6.generateNewId();
        this.formatId = r6.getFormatId();
        TrackOutput r0 = r5.track(r6.getTrackId(), 2);
        this.output = r0;
        this.sampleReader = new SampleReader(r0, this.allowNonIdrKeyframes, this.detectAccessUnits);
        this.seiReader.createTracks(r5, r6);
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetFinished(boolean r15) {
        assertTracksCreated();
        if (r15 == false) goto L6;
        this.seiReader.flush();
        endNalUnit(this.totalBytesWritten, 0, 0, this.pesTimeUs);
        startNalUnit(this.totalBytesWritten, 9, this.pesTimeUs);
        endNalUnit(this.totalBytesWritten, 0, 0, this.pesTimeUs);
        return;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetStarted(long r1, int r3) {
        this.pesTimeUs = r1;
        boolean r12 = this.randomAccessIndicator;
        if ((r3 & 2) == 0) goto L5;
        boolean r2 = true;
    L6:
        this.randomAccessIndicator = r12 | r2;
        return;
    L5:
        r2 = false;
        goto L6
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void seek() {
        this.totalBytesWritten = 0;
        this.randomAccessIndicator = false;
        this.pesTimeUs = -9223372036854775807L;
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.sps.reset();
        this.pps.reset();
        this.sei.reset();
        this.seiReader.clear();
        SampleReader r2 = this.sampleReader;
        if (r2 == null) goto L6;
        r2.reset();
        return;
    }
}
