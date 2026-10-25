package androidx.media3.extractor.ts;

import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import com.google.common.base.Preconditions;
import java.util.Collections;
import kotlin.jvm.internal.ByteCompanionObject;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes4.dex */
public final class H265Reader implements ElementaryStreamReader {
    private final String containerMimeType;
    private String formatId;
    private boolean hasOutputFormat;
    private TrackOutput output;
    private long pesTimeUs;
    private final NalUnitTargetBuffer pps;
    private final boolean[] prefixFlags;
    private final NalUnitTargetBuffer prefixSei;
    private SampleReader sampleReader;
    private final SeiReader seiReader;
    private final ParsableByteArray seiWrapper;
    private final NalUnitTargetBuffer sps;
    private final NalUnitTargetBuffer suffixSei;
    private long totalBytesWritten;
    private final NalUnitTargetBuffer vps;

    public static final class SampleReader {
        private boolean isFirstPrefixNalUnit;
        private boolean isFirstSlice;
        private boolean lookingForFirstSliceFlag;
        private int nalUnitBytesRead;
        private boolean nalUnitHasKeyframeData;
        private long nalUnitPosition;
        private long nalUnitTimeUs;
        private final TrackOutput output;
        private boolean readingPrefix;
        private boolean readingSample;
        private boolean sampleIsKeyframe;
        private long samplePosition;
        private long sampleTimeUs;

        public SampleReader(TrackOutput r1) {
            this.output = r1;
        }

        private static boolean isPrefixNalUnit(int r1) {
            if (32 > r1) goto L7;
            if (r1 > 35) goto L7;
            return true;
        L7:
            if (r1 == 39) goto L12;
            return false;
        L12:
            return true;
        }

        private static boolean isVclBodyNalUnit(int r1) {
            if (r1 >= 32) goto L5;
            return true;
        L5:
            if (r1 == 40) goto L11;
            return false;
        L11:
            return true;
        }

        private void outputSample(int r10) {
            long r1 = this.sampleTimeUs;
            if (r1 == (-9223372036854775807L)) goto L9;
            long r3 = this.nalUnitPosition;
            long r5 = this.samplePosition;
            if (r3 == r5) goto L10;
            boolean r32 = this.sampleIsKeyframe;
            int r4 = (int) (r3 - r5);
            this.output.sampleMetadata(r1, r32 ? 1 : 0, r4, r10, null);
            return;
        L10:
            return;
        }

        public void endNalUnit(long r3, int r5, boolean r6) {
            if (this.readingPrefix == false) goto L9;
            if (this.isFirstSlice == false) goto L9;
            this.sampleIsKeyframe = this.nalUnitHasKeyframeData;
            this.readingPrefix = false;
            return;
        L9:
            if (this.isFirstPrefixNalUnit == false) goto L11;
        L14:
            if (r6 == true) goto L16;
        L18:
            this.samplePosition = this.nalUnitPosition;
            this.sampleTimeUs = this.nalUnitTimeUs;
            this.sampleIsKeyframe = this.nalUnitHasKeyframeData;
            this.readingSample = true;
            return;
        L16:
            if (this.readingSample == false) goto L18;
            outputSample(r5 + ((int) (r3 - this.nalUnitPosition)));
            goto L18
        L11:
            if (this.isFirstSlice == true) goto L14;
        }

        public void readNalUnitData(byte[] r3, int r4, int r5) {
            if (this.lookingForFirstSliceFlag == false) goto L14;
            int r1 = this.nalUnitBytesRead;
            int r0 = (r4 + 2) - r1;
            if (r0 < r5) goto L7;
            this.nalUnitBytesRead = r1 + (r5 - r4);
            return;
        L7:
            if ((r3[r0] & ByteCompanionObject.MIN_VALUE) == 0) goto L9;
            boolean r32 = true;
        L10:
            this.isFirstSlice = r32;
            this.lookingForFirstSliceFlag = false;
            return;
        L9:
            r32 = false;
            goto L10
        }

        public void reset() {
            this.lookingForFirstSliceFlag = false;
            this.isFirstSlice = false;
            this.isFirstPrefixNalUnit = false;
            this.readingSample = false;
            this.readingPrefix = false;
        }

        public void startNalUnit(long r2, int r4, int r5, long r6, boolean r8) {
            boolean r0 = false;
            this.isFirstSlice = false;
            this.isFirstPrefixNalUnit = false;
            this.nalUnitTimeUs = r6;
            this.nalUnitBytesRead = 0;
            this.nalUnitPosition = r2;
            if (isVclBodyNalUnit(r5) == true) goto L15;
            if (this.readingSample == false) goto L12;
            if (this.readingPrefix == true) goto L12;
            if (r8 == false) goto L10;
            outputSample(r4);
        L10:
            this.readingSample = false;
        L12:
            if (isPrefixNalUnit(r5) == false) goto L15;
            this.isFirstPrefixNalUnit = !this.readingPrefix;
            this.readingPrefix = true;
        L15:
            if (r5 >= 16) goto L17;
        L19:
            boolean r22 = false;
        L20:
            this.nalUnitHasKeyframeData = r22;
            if (r22 == false) goto L23;
        L24:
            r0 = true;
        L25:
            this.lookingForFirstSliceFlag = r0;
            return;
        L23:
            if (r5 > 9) goto L25;
        L17:
            if (r5 > 21) goto L19;
            r22 = true;
            goto L20
        }
    }

    public H265Reader(SeiReader r2, String r3) {
        this.seiReader = r2;
        this.containerMimeType = r3;
        this.prefixFlags = new boolean[3];
        this.vps = new NalUnitTargetBuffer(32, 128);
        this.sps = new NalUnitTargetBuffer(33, 128);
        this.pps = new NalUnitTargetBuffer(34, 128);
        this.prefixSei = new NalUnitTargetBuffer(39, 128);
        this.suffixSei = new NalUnitTargetBuffer(40, 128);
        this.pesTimeUs = -9223372036854775807L;
        this.seiWrapper = new ParsableByteArray();
    }

    @EnsuresNonNull({"output", "sampleReader"})
    private void assertTracksCreated() {
        Assertions.checkStateNotNull(this.output);
        Util.castNonNull(this.sampleReader);
    }

    @RequiresNonNull({"output", "sampleReader"})
    private void endNalUnit(long r3, int r5, int r6, long r7) {
        this.sampleReader.endNalUnit(r3, r5, this.hasOutputFormat);
        if (this.hasOutputFormat == true) goto L16;
        this.vps.endNalUnit(r6);
        this.sps.endNalUnit(r6);
        this.pps.endNalUnit(r6);
        if (this.vps.isCompleted() == false) goto L16;
        if (this.sps.isCompleted() == false) goto L16;
        if (this.pps.isCompleted() == false) goto L16;
        Format r32 = parseMediaFormat(this.formatId, this.vps, this.sps, this.pps, this.containerMimeType);
        this.output.format(r32);
        if (r32.maxNumReorderSamples == (-1)) goto L13;
        boolean r4 = true;
    L14:
        Preconditions.checkState(r4);
        this.seiReader.setReorderingQueueSize(r32.maxNumReorderSamples);
        this.hasOutputFormat = true;
        goto L16
    L13:
        r4 = false;
    L16:
        if (this.prefixSei.endNalUnit(r6) == false) goto L19;
        NalUnitTargetBuffer r33 = this.prefixSei;
        this.seiWrapper.reset(this.prefixSei.nalData, NalUnitUtil.unescapeStream(r33.nalData, r33.nalLength));
        this.seiWrapper.skipBytes(5);
        this.seiReader.consume(r7, this.seiWrapper);
    L19:
        if (this.suffixSei.endNalUnit(r6) == false) goto L22;
        NalUnitTargetBuffer r34 = this.suffixSei;
        this.seiWrapper.reset(this.suffixSei.nalData, NalUnitUtil.unescapeStream(r34.nalData, r34.nalLength));
        this.seiWrapper.skipBytes(5);
        this.seiReader.consume(r7, this.seiWrapper);
        return;
    }

    @RequiresNonNull({"sampleReader"})
    private void nalUnitData(byte[] r2, int r3, int r4) {
        this.sampleReader.readNalUnitData(r2, r3, r4);
        if (this.hasOutputFormat == true) goto L5;
        this.vps.appendToNalUnit(r2, r3, r4);
        this.sps.appendToNalUnit(r2, r3, r4);
        this.pps.appendToNalUnit(r2, r3, r4);
    L5:
        this.prefixSei.appendToNalUnit(r2, r3, r4);
        this.suffixSei.appendToNalUnit(r2, r3, r4);
    }

    private static Format parseMediaFormat(String r8, NalUnitTargetBuffer r9, NalUnitTargetBuffer r10, NalUnitTargetBuffer r11, String r12) {
        int r0 = r9.nalLength;
        byte[] r1 = new byte[(r10.nalLength + r0) + r11.nalLength];
        System.arraycopy(r9.nalData, 0, r1, 0, r0);
        System.arraycopy(r10.nalData, 0, r1, r9.nalLength, r10.nalLength);
        System.arraycopy(r11.nalData, 0, r1, r9.nalLength + r10.nalLength, r11.nalLength);
        String r02 = null;
        NalUnitUtil.H265SpsData r92 = NalUnitUtil.parseH265SpsNalUnit(r10.nalData, 3, r10.nalLength, null);
        NalUnitUtil.H265ProfileTierLevel r102 = r92.profileTierLevel;
        if (r102 == null) goto L6;
        r02 = CodecSpecificDataUtil.buildHevcCodecString(r102.generalProfileSpace, r102.generalTierFlag, r102.generalProfileIdc, r102.generalProfileCompatibilityFlags, r102.constraintBytes, r102.generalLevelIdc);
    L6:
        return new Format.Builder().setId(r8).setContainerMimeType(r12).setSampleMimeType("video/hevc").setCodecs(r02).setWidth(r92.width).setHeight(r92.height).setDecodedWidth(r92.decodedWidth).setDecodedHeight(r92.decodedHeight).setColorInfo(new ColorInfo.Builder().setColorSpace(r92.colorSpace).setColorRange(r92.colorRange).setColorTransfer(r92.colorTransfer).setLumaBitdepth(r92.bitDepthLumaMinus8 + 8).setChromaBitdepth(r92.bitDepthChromaMinus8 + 8).build()).setPixelWidthHeightRatio(r92.pixelWidthHeightRatio).setMaxNumReorderSamples(r92.maxNumReorderPics).setMaxSubLayers(r92.maxSubLayersMinus1 + 1).setInitializationData(Collections.singletonList(r1)).build();
    }

    @RequiresNonNull({"sampleReader"})
    private void startNalUnit(long r9, int r11, int r12, long r13) {
        this.sampleReader.startNalUnit(r9, r11, r12, r13, this.hasOutputFormat);
        if (this.hasOutputFormat == true) goto L5;
        this.vps.startNalUnit(r12);
        this.sps.startNalUnit(r12);
        this.pps.startNalUnit(r12);
    L5:
        this.prefixSei.startNalUnit(r12);
        this.suffixSei.startNalUnit(r12);
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray r16) {
        assertTracksCreated();
    L4:
        if (r16.bytesLeft() <= 0) goto L27;
        int r1 = r16.getPosition();
        int r7 = r16.limit();
        byte[] r8 = r16.getData();
        this.totalBytesWritten += r16.bytesLeft();
        this.output.sampleData(r16, r16.bytesLeft());
    L6:
        if (r1 >= r7) goto L4;
        int r2 = NalUnitUtil.findNalUnit(r8, r1, r7, this.prefixFlags);
        if (r2 == r7) goto L9;
        int r10 = NalUnitUtil.getH265NalUnitType(r8, r2);
        if (r2 > 0) goto L14;
    L17:
        int r3 = 3;
    L16:
        int r11 = r2;
        int r12 = r3;
        int r22 = r11 - r1;
        if (r22 <= 0) goto L21;
        nalUnitData(r8, r1, r11);
    L21:
        int r32 = r7 - r11;
        long r4 = this.totalBytesWritten - r32;
        if (r22 >= 0) goto L25;
        int r13 = -r22;
    L26:
        endNalUnit(r4, r32, r13, this.pesTimeUs);
        startNalUnit(r4, r32, r10, this.pesTimeUs);
        r1 = r11 + r12;
        goto L6
    L25:
        r13 = 0;
        goto L26
    L14:
        if (r8[r2 - 1] != 0) goto L17;
        r2 = r2 - 1;
        r3 = 4;
        goto L16
    L9:
        nalUnitData(r8, r1, r7);
        return;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput r3, TsPayloadReader.TrackIdGenerator r4) {
        r4.generateNewId();
        this.formatId = r4.getFormatId();
        TrackOutput r0 = r3.track(r4.getTrackId(), 2);
        this.output = r0;
        this.sampleReader = new SampleReader(r0);
        this.seiReader.createTracks(r3, r4);
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetFinished(boolean r15) {
        assertTracksCreated();
        if (r15 == false) goto L6;
        this.seiReader.flush();
        endNalUnit(this.totalBytesWritten, 0, 0, this.pesTimeUs);
        startNalUnit(this.totalBytesWritten, 0, 48, this.pesTimeUs);
        return;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetStarted(long r1, int r3) {
        this.pesTimeUs = r1;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void seek() {
        this.totalBytesWritten = 0;
        this.pesTimeUs = -9223372036854775807L;
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.vps.reset();
        this.sps.reset();
        this.pps.reset();
        this.prefixSei.reset();
        this.suffixSei.reset();
        this.seiReader.clear();
        SampleReader r2 = this.sampleReader;
        if (r2 == null) goto L6;
        r2.reset();
        return;
    }
}
