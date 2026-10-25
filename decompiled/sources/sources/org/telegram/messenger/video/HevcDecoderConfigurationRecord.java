package org.telegram.messenger.video;

import com.android.dx.io.Opcodes;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.boxes.sampleentry.VisualSampleEntry;
import com.googlecode.mp4parser.authoring.tracks.CleanInputStream;
import com.googlecode.mp4parser.util.ByteBufferByteChannel;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes6.dex */
public class HevcDecoderConfigurationRecord {
    List<Array> arrays;
    int avgFrameRate;
    int bitDepthChromaMinus8;
    int bitDepthLumaMinus8;
    int chromaFormat;
    int configurationVersion;
    int constantFrameRate;
    boolean frame_only_constraint_flag;
    long general_constraint_indicator_flags;
    int general_level_idc;
    long general_profile_compatibility_flags;
    int general_profile_idc;
    int general_profile_space;
    boolean general_tier_flag;
    boolean interlaced_source_flag;
    int lengthSizeMinusOne;
    int min_spatial_segmentation_idc;
    boolean non_packed_constraint_flag;
    int numTemporalLayers;
    int parallelismType;
    boolean progressive_source_flag;
    int reserved1;
    int reserved2;
    int reserved3;
    int reserved4;
    int reserved5;
    boolean temporalIdNested;

    public static class Array {
        public boolean array_completeness;
        public List<byte[]> nalUnits;
        public int nal_unit_type;
        public boolean reserved;

        public Array() {
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L36:
            return false;
        L8:
            if (getClass() != r5.getClass()) goto L36;
            Array r52 = (Array) r5;
            if (this.array_completeness == r52.array_completeness) goto L14;
            return false;
        L14:
            if (this.nal_unit_type == r52.nal_unit_type) goto L17;
            return false;
        L17:
            if (this.reserved == r52.reserved) goto L19;
            return false;
        L19:
            ListIterator<byte[]> r4 = this.nalUnits.listIterator();
            ListIterator<byte[]> r53 = r52.nalUnits.listIterator();
        L21:
            if (r4.hasNext() == false) goto L32;
            if (r53.hasNext() == false) goto L32;
            byte[] r2 = r4.next();
            byte[] r3 = r53.next();
            if (r2 == null) goto L26;
            if (Arrays.equals(r2, r3) == true) goto L21;
        L30:
            return false;
        L26:
            if (r3 != null) goto L30;
        L32:
            if (r4.hasNext() == true) goto L36;
            if (r53.hasNext() == true) goto L36;
            return true;
        }

        public int hashCode() {
            int r0 = (((((this.array_completeness ? 1 : 0) * 31) + (this.reserved ? 1 : 0)) * 31) + this.nal_unit_type) * 31;
            List<byte[]> r2 = this.nalUnits;
            if (r2 == null) goto L5;
            int r22 = r2.hashCode();
        L7:
            return r0 + r22;
        L5:
            r22 = 0;
            goto L7
        }

        public String toString() {
            return "Array{nal_unit_type=" + this.nal_unit_type + ", reserved=" + this.reserved + ", array_completeness=" + this.array_completeness + ", num_nals=" + this.nalUnits.size() + '}';
        }
    }

    public static class H265NalUnitHeader {
        public int forbiddenZeroFlag;
        public int nalUnitType;
        public int nuhLayerId;
        public int nuhTemporalIdPlusOne;

        public H265NalUnitHeader() {
        }
    }

    public HevcDecoderConfigurationRecord() {
        this.reserved1 = 15;
        this.reserved2 = 63;
        this.reserved3 = 63;
        this.reserved4 = 31;
        this.reserved5 = 31;
        this.arrays = new ArrayList();
    }

    private static VisualSampleEntry createSampleEntry(ArrayList<ByteBuffer> r8, ArrayList<ByteBuffer> r9, ArrayList<ByteBuffer> r10, SequenceParameterSetRbsp r11) {
        VisualSampleEntry r0 = new VisualSampleEntry("hvc1");
        r0.setDataReferenceIndex(1);
        r0.setDepth(24);
        r0.setFrameCount(1);
        r0.setHorizresolution(72.0d);
        r0.setVertresolution(72.0d);
        r0.setCompressorname("HEVC Coding");
        HevcConfigurationBox r2 = new HevcConfigurationBox();
        r2.getHevcDecoderConfigurationRecord().setConfigurationVersion(1);
        if (r11 == null) goto L5;
        r0.setWidth(r11.pic_width_in_luma_samples);
        r0.setHeight(r11.pic_height_in_luma_samples);
        HevcDecoderConfigurationRecord r3 = r2.getHevcDecoderConfigurationRecord();
        r3.setChromaFormat(r11.chroma_format_idc);
        r3.setGeneral_profile_idc(r11.general_profile_idc);
        r3.setGeneral_profile_compatibility_flags(r11.general_profile_compatibility_flags);
        r3.setGeneral_constraint_indicator_flags(r11.general_constraint_indicator_flags);
        r3.setGeneral_level_idc(r11.general_level_idc);
        r3.setGeneral_tier_flag(r11.general_tier_flag);
        r3.setGeneral_profile_space(r11.general_profile_space);
        r3.setBitDepthChromaMinus8(r11.bit_depth_chroma_minus8);
        r3.setBitDepthLumaMinus8(r11.bit_depth_luma_minus8);
        r3.setTemporalIdNested(r11.sps_temporal_id_nesting_flag);
    L5:
        r2.getHevcDecoderConfigurationRecord().setLengthSizeMinusOne(3);
        Array r112 = new Array();
        r112.array_completeness = true;
        r112.nal_unit_type = 32;
        r112.nalUnits = new ArrayList();
        int r32 = r10.size();
        int r4 = 0;
        int r5 = 0;
    L6:
        if (r5 >= r32) goto L8;
        ByteBuffer r6 = r10.get(r5);
        r5 = r5 + 1;
        r112.nalUnits.add(r6.array());
        goto L6
    L8:
        Array r102 = new Array();
        r102.array_completeness = true;
        r102.nal_unit_type = 33;
        r102.nalUnits = new ArrayList();
        int r33 = r8.size();
        int r52 = 0;
    L9:
        if (r52 >= r33) goto L11;
        ByteBuffer r62 = r8.get(r52);
        r52 = r52 + 1;
        r102.nalUnits.add(r62.array());
        goto L9
    L11:
        Array r82 = new Array();
        r82.array_completeness = true;
        r82.nal_unit_type = 34;
        r82.nalUnits = new ArrayList();
        int r1 = r9.size();
    L12:
        if (r4 >= r1) goto L14;
        ByteBuffer r34 = r9.get(r4);
        r4 = r4 + 1;
        r82.nalUnits.add(r34.array());
        goto L12
    L14:
        r2.getArrays().addAll(Arrays.asList(new Array[]{r112, r102, r82}));
        r0.addBox(r2);
        return r0;
    }

    private static H265NalUnitHeader getNalUnitHeader(ByteBuffer r2) {
        r2.position(0);
        int r22 = IsoTypeReader.readUInt16(r2);
        H265NalUnitHeader r0 = new H265NalUnitHeader();
        r0.forbiddenZeroFlag = (32768 & r22) >> 15;
        r0.nalUnitType = (r22 & 32256) >> 9;
        r0.nuhLayerId = (r22 & 504) >> 3;
        r0.nuhTemporalIdPlusOne = r22 & 7;
        return r0;
    }

    private boolean isVcl(H265NalUnitHeader r1) {
        int r0 = r1.nalUnitType;
        if (r0 >= 0) goto L5;
        return false;
    L5:
        if (r0 > 31) goto L10;
        return true;
    L10:
        return false;
    }

    public static VisualSampleEntry parseFromCsd(List<ByteBuffer> r7) {
        ArrayList r0 = new ArrayList();
        ArrayList r1 = new ArrayList();
        ArrayList r2 = new ArrayList();
        Iterator<ByteBuffer> r72 = r7.iterator();
        SequenceParameterSetRbsp r3 = null;
    L4:
        if (r72.hasNext() == false) goto L12;
        ByteBuffer r4 = r72.next();
        H265NalUnitHeader r5 = getNalUnitHeader(r4);
        r4.position(0);
        switch(r5.nalUnitType) {
            case 32: goto L10;
            case 33: goto L9;
            case 34: goto L8;
            default: goto L4;
        };
    L8:
        r1.add(r4.duplicate());
        goto L4
    L9:
        r0.add(r4.duplicate());
        r4.position(2);
        r3 = new SequenceParameterSetRbsp(new CleanInputStream(Channels.newInputStream(new ByteBufferByteChannel(r4.slice()))));
        goto L4
    L10:
        r2.add(r4.duplicate());
        goto L4
    L12:
        return createSampleEntry(r0, r1, r2, r3);
    }

    public boolean equals(Object r7) {
        if (this != r7) goto L6;
        return true;
    L6:
        if (r7 != null) goto L8;
    L84:
        return false;
    L8:
        if (getClass() != r7.getClass()) goto L84;
        HevcDecoderConfigurationRecord r72 = (HevcDecoderConfigurationRecord) r7;
        if (this.avgFrameRate == r72.avgFrameRate) goto L14;
        return false;
    L14:
        if (this.bitDepthChromaMinus8 == r72.bitDepthChromaMinus8) goto L17;
        return false;
    L17:
        if (this.bitDepthLumaMinus8 == r72.bitDepthLumaMinus8) goto L20;
        return false;
    L20:
        if (this.chromaFormat == r72.chromaFormat) goto L23;
        return false;
    L23:
        if (this.configurationVersion == r72.configurationVersion) goto L26;
        return false;
    L26:
        if (this.constantFrameRate == r72.constantFrameRate) goto L29;
        return false;
    L29:
        if (this.general_constraint_indicator_flags == r72.general_constraint_indicator_flags) goto L32;
        return false;
    L32:
        if (this.general_level_idc == r72.general_level_idc) goto L35;
        return false;
    L35:
        if (this.general_profile_compatibility_flags == r72.general_profile_compatibility_flags) goto L38;
        return false;
    L38:
        if (this.general_profile_idc == r72.general_profile_idc) goto L41;
        return false;
    L41:
        if (this.general_profile_space == r72.general_profile_space) goto L44;
        return false;
    L44:
        if (this.general_tier_flag == r72.general_tier_flag) goto L47;
        return false;
    L47:
        if (this.lengthSizeMinusOne == r72.lengthSizeMinusOne) goto L50;
        return false;
    L50:
        if (this.min_spatial_segmentation_idc == r72.min_spatial_segmentation_idc) goto L53;
        return false;
    L53:
        if (this.numTemporalLayers == r72.numTemporalLayers) goto L56;
        return false;
    L56:
        if (this.parallelismType == r72.parallelismType) goto L59;
        return false;
    L59:
        if (this.reserved1 == r72.reserved1) goto L62;
        return false;
    L62:
        if (this.reserved2 == r72.reserved2) goto L65;
        return false;
    L65:
        if (this.reserved3 == r72.reserved3) goto L68;
        return false;
    L68:
        if (this.reserved4 == r72.reserved4) goto L71;
        return false;
    L71:
        if (this.reserved5 == r72.reserved5) goto L74;
        return false;
    L74:
        if (this.temporalIdNested == r72.temporalIdNested) goto L76;
        return false;
    L76:
        List<Array> r6 = this.arrays;
        List<Array> r73 = r72.arrays;
        if (r6 != null) goto L79;
        if (r73 == null) goto L83;
    L82:
        return false;
    L83:
        return true;
    L79:
        if (r6.equals(r73) == true) goto L83;
        goto L83
    }

    public List<Array> getArrays() {
        return this.arrays;
    }

    public int getAvgFrameRate() {
        return this.avgFrameRate;
    }

    public int getBitDepthChromaMinus8() {
        return this.bitDepthChromaMinus8;
    }

    public int getBitDepthLumaMinus8() {
        return this.bitDepthLumaMinus8;
    }

    public int getChromaFormat() {
        return this.chromaFormat;
    }

    public int getConfigurationVersion() {
        return this.configurationVersion;
    }

    public int getConstantFrameRate() {
        return this.constantFrameRate;
    }

    public long getGeneral_constraint_indicator_flags() {
        return this.general_constraint_indicator_flags;
    }

    public int getGeneral_level_idc() {
        return this.general_level_idc;
    }

    public long getGeneral_profile_compatibility_flags() {
        return this.general_profile_compatibility_flags;
    }

    public int getGeneral_profile_idc() {
        return this.general_profile_idc;
    }

    public int getGeneral_profile_space() {
        return this.general_profile_space;
    }

    public int getLengthSizeMinusOne() {
        return this.lengthSizeMinusOne;
    }

    public int getMin_spatial_segmentation_idc() {
        return this.min_spatial_segmentation_idc;
    }

    public int getNumTemporalLayers() {
        return this.numTemporalLayers;
    }

    public int getParallelismType() {
        return this.parallelismType;
    }

    public int getSize() {
        Iterator<Array> r3 = this.arrays.iterator();
        int r0 = 23;
    L4:
        if (r3.hasNext() == false) goto L9;
        r0 = r0 + 3;
        Iterator<byte[]> r1 = r3.next().nalUnits.iterator();
    L7:
        if (r1.hasNext() == false) goto L4;
        r0 = (r0 + 2) + r1.next().length;
        goto L7
    L9:
        return r0;
    }

    public int hashCode() {
        int r0 = ((((((this.configurationVersion * 31) + this.general_profile_space) * 31) + (this.general_tier_flag ? 1 : 0)) * 31) + this.general_profile_idc) * 31;
        long r1 = this.general_profile_compatibility_flags;
        int r02 = (r0 + ((int) (r1 ^ (r1 >>> 32)))) * 31;
        long r12 = this.general_constraint_indicator_flags;
        int r03 = (((((((((((((((((((((((((((((((((r02 + ((int) (r12 ^ (r12 >>> 32)))) * 31) + this.general_level_idc) * 31) + this.reserved1) * 31) + this.min_spatial_segmentation_idc) * 31) + this.reserved2) * 31) + this.parallelismType) * 31) + this.reserved3) * 31) + this.chromaFormat) * 31) + this.reserved4) * 31) + this.bitDepthLumaMinus8) * 31) + this.reserved5) * 31) + this.bitDepthChromaMinus8) * 31) + this.avgFrameRate) * 31) + this.constantFrameRate) * 31) + this.numTemporalLayers) * 31) + (this.temporalIdNested ? 1 : 0)) * 31) + this.lengthSizeMinusOne) * 31;
        List<Array> r6 = this.arrays;
        if (r6 == null) goto L5;
        int r62 = r6.hashCode();
    L7:
        return r03 + r62;
    L5:
        r62 = 0;
        goto L7
    }

    public boolean isFrame_only_constraint_flag() {
        return this.frame_only_constraint_flag;
    }

    public boolean isGeneral_tier_flag() {
        return this.general_tier_flag;
    }

    public boolean isInterlaced_source_flag() {
        return this.interlaced_source_flag;
    }

    public boolean isNon_packed_constraint_flag() {
        return this.non_packed_constraint_flag;
    }

    public boolean isProgressive_source_flag() {
        return this.progressive_source_flag;
    }

    public boolean isTemporalIdNested() {
        return this.temporalIdNested;
    }

    public void parse(ByteBuffer r12) {
        this.configurationVersion = IsoTypeReader.readUInt8(r12);
        int r0 = IsoTypeReader.readUInt8(r12);
        this.general_profile_space = (r0 & 192) >> 6;
        if ((r0 & 32) <= 0) goto L5;
        boolean r1 = true;
    L6:
        this.general_tier_flag = r1;
        this.general_profile_idc = r0 & 31;
        this.general_profile_compatibility_flags = IsoTypeReader.readUInt32(r12);
        long r02 = IsoTypeReader.readUInt48(r12);
        this.general_constraint_indicator_flags = r02;
        if (((r02 >> 44) & 8) <= 0) goto L9;
        boolean r5 = true;
    L10:
        this.frame_only_constraint_flag = r5;
        if (((r02 >> 44) & 4) <= 0) goto L13;
        boolean r52 = true;
    L14:
        this.non_packed_constraint_flag = r52;
        if (((r02 >> 44) & 2) <= 0) goto L17;
        boolean r53 = true;
    L18:
        this.interlaced_source_flag = r53;
        if (((r02 >> 44) & 1) <= 0) goto L21;
        boolean r4 = true;
    L22:
        this.progressive_source_flag = r4;
        this.general_constraint_indicator_flags = r02 & 140737488355327L;
        this.general_level_idc = IsoTypeReader.readUInt8(r12);
        int r03 = IsoTypeReader.readUInt16(r12);
        this.reserved1 = (61440 & r03) >> 12;
        this.min_spatial_segmentation_idc = r03 & 4095;
        int r04 = IsoTypeReader.readUInt8(r12);
        this.reserved2 = (r04 & Opcodes.INVOKE_CUSTOM) >> 2;
        this.parallelismType = r04 & 3;
        int r05 = IsoTypeReader.readUInt8(r12);
        this.reserved3 = (r05 & Opcodes.INVOKE_CUSTOM) >> 2;
        this.chromaFormat = r05 & 3;
        int r06 = IsoTypeReader.readUInt8(r12);
        this.reserved4 = (r06 & 248) >> 3;
        this.bitDepthLumaMinus8 = r06 & 7;
        int r07 = IsoTypeReader.readUInt8(r12);
        this.reserved5 = (r07 & 248) >> 3;
        this.bitDepthChromaMinus8 = r07 & 7;
        this.avgFrameRate = IsoTypeReader.readUInt16(r12);
        int r08 = IsoTypeReader.readUInt8(r12);
        this.constantFrameRate = (r08 & 192) >> 6;
        this.numTemporalLayers = (r08 & 56) >> 3;
        if ((r08 & 4) <= 0) goto L25;
        boolean r13 = true;
    L26:
        this.temporalIdNested = r13;
        this.lengthSizeMinusOne = r08 & 3;
        int r09 = IsoTypeReader.readUInt8(r12);
        this.arrays = new ArrayList();
        int r14 = 0;
    L27:
        if (r14 >= r09) goto L40;
        Array r42 = new Array();
        int r54 = IsoTypeReader.readUInt8(r12);
        if ((r54 & 128) <= 0) goto L31;
        boolean r6 = true;
    L32:
        r42.array_completeness = r6;
        if ((r54 & 64) <= 0) goto L35;
        boolean r62 = true;
    L36:
        r42.reserved = r62;
        r42.nal_unit_type = r54 & 63;
        int r55 = IsoTypeReader.readUInt16(r12);
        r42.nalUnits = new ArrayList();
        int r63 = 0;
    L37:
        if (r63 >= r55) goto L39;
        byte[] r7 = new byte[IsoTypeReader.readUInt16(r12)];
        r12.get(r7);
        r42.nalUnits.add(r7);
        r63 = r63 + 1;
        goto L37
    L39:
        this.arrays.add(r42);
        r14 = r14 + 1;
        goto L27
    L35:
        r62 = false;
        goto L36
    L31:
        r6 = false;
        goto L32
    L40:
        return;
    L25:
        r13 = false;
        goto L26
    L21:
        r4 = false;
        goto L22
    L17:
        r53 = false;
        goto L18
    L13:
        r52 = false;
        goto L14
    L9:
        r5 = false;
        goto L10
    L5:
        r1 = false;
        goto L6
    }

    public void setArrays(List<Array> r1) {
        this.arrays = r1;
    }

    public void setAvgFrameRate(int r1) {
        this.avgFrameRate = r1;
    }

    public void setBitDepthChromaMinus8(int r1) {
        this.bitDepthChromaMinus8 = r1;
    }

    public void setBitDepthLumaMinus8(int r1) {
        this.bitDepthLumaMinus8 = r1;
    }

    public void setChromaFormat(int r1) {
        this.chromaFormat = r1;
    }

    public void setConfigurationVersion(int r1) {
        this.configurationVersion = r1;
    }

    public void setConstantFrameRate(int r1) {
        this.constantFrameRate = r1;
    }

    public void setFrame_only_constraint_flag(boolean r1) {
        this.frame_only_constraint_flag = r1;
    }

    public void setGeneral_constraint_indicator_flags(long r1) {
        this.general_constraint_indicator_flags = r1;
    }

    public void setGeneral_level_idc(int r1) {
        this.general_level_idc = r1;
    }

    public void setGeneral_profile_compatibility_flags(long r1) {
        this.general_profile_compatibility_flags = r1;
    }

    public void setGeneral_profile_idc(int r1) {
        this.general_profile_idc = r1;
    }

    public void setGeneral_profile_space(int r1) {
        this.general_profile_space = r1;
    }

    public void setGeneral_tier_flag(boolean r1) {
        this.general_tier_flag = r1;
    }

    public void setInterlaced_source_flag(boolean r1) {
        this.interlaced_source_flag = r1;
    }

    public void setLengthSizeMinusOne(int r1) {
        this.lengthSizeMinusOne = r1;
    }

    public void setMin_spatial_segmentation_idc(int r1) {
        this.min_spatial_segmentation_idc = r1;
    }

    public void setNon_packed_constraint_flag(boolean r1) {
        this.non_packed_constraint_flag = r1;
    }

    public void setNumTemporalLayers(int r1) {
        this.numTemporalLayers = r1;
    }

    public void setParallelismType(int r1) {
        this.parallelismType = r1;
    }

    public void setProgressive_source_flag(boolean r1) {
        this.progressive_source_flag = r1;
    }

    public void setTemporalIdNested(boolean r1) {
        this.temporalIdNested = r1;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("HEVCDecoderConfigurationRecord{configurationVersion=");
        r0.append(this.configurationVersion);
        r0.append(", general_profile_space=");
        r0.append(this.general_profile_space);
        r0.append(", general_tier_flag=");
        r0.append(this.general_tier_flag);
        r0.append(", general_profile_idc=");
        r0.append(this.general_profile_idc);
        r0.append(", general_profile_compatibility_flags=");
        r0.append(this.general_profile_compatibility_flags);
        r0.append(", general_constraint_indicator_flags=");
        r0.append(this.general_constraint_indicator_flags);
        r0.append(", general_level_idc=");
        r0.append(this.general_level_idc);
        int r1 = this.reserved1;
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r1 == 15) goto L5;
        String r12 = ", reserved1=" + this.reserved1;
    L6:
        r0.append(r12);
        r0.append(", min_spatial_segmentation_idc=");
        r0.append(this.min_spatial_segmentation_idc);
        if (this.reserved2 == 63) goto L9;
        String r13 = ", reserved2=" + this.reserved2;
    L10:
        r0.append(r13);
        r0.append(", parallelismType=");
        r0.append(this.parallelismType);
        if (this.reserved3 == 63) goto L13;
        String r14 = ", reserved3=" + this.reserved3;
    L14:
        r0.append(r14);
        r0.append(", chromaFormat=");
        r0.append(this.chromaFormat);
        if (this.reserved4 == 31) goto L17;
        String r15 = ", reserved4=" + this.reserved4;
    L18:
        r0.append(r15);
        r0.append(", bitDepthLumaMinus8=");
        r0.append(this.bitDepthLumaMinus8);
        if (this.reserved5 == 31) goto L21;
        r3 = ", reserved5=" + this.reserved5;
    L21:
        r0.append(r3);
        r0.append(", bitDepthChromaMinus8=");
        r0.append(this.bitDepthChromaMinus8);
        r0.append(", avgFrameRate=");
        r0.append(this.avgFrameRate);
        r0.append(", constantFrameRate=");
        r0.append(this.constantFrameRate);
        r0.append(", numTemporalLayers=");
        r0.append(this.numTemporalLayers);
        r0.append(", temporalIdNested=");
        r0.append(this.temporalIdNested);
        r0.append(", lengthSizeMinusOne=");
        r0.append(this.lengthSizeMinusOne);
        r0.append(", arrays=");
        r0.append(this.arrays);
        r0.append('}');
        return r0.toString();
    L17:
        r15 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L18
    L13:
        r14 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L14
    L9:
        r13 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L10
    L5:
        r12 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    public void write(ByteBuffer r6) {
        IsoTypeWriter.writeUInt8(r6, this.configurationVersion);
        int r0 = this.general_profile_space << 6;
        if (this.general_tier_flag == false) goto L5;
        int r1 = 32;
    L6:
        IsoTypeWriter.writeUInt8(r6, (r0 + r1) + this.general_profile_idc);
        IsoTypeWriter.writeUInt32(r6, this.general_profile_compatibility_flags);
        long r02 = this.general_constraint_indicator_flags;
        if (this.frame_only_constraint_flag == false) goto L10;
        r02 = r02 | 140737488355328L;
    L10:
        if (this.non_packed_constraint_flag == false) goto L13;
        r02 = r02 | 70368744177664L;
    L13:
        if (this.interlaced_source_flag == false) goto L16;
        r02 = r02 | 35184372088832L;
    L16:
        if (this.progressive_source_flag == false) goto L18;
        r02 = r02 | 17592186044416L;
    L18:
        IsoTypeWriter.writeUInt48(r6, r02);
        IsoTypeWriter.writeUInt8(r6, this.general_level_idc);
        IsoTypeWriter.writeUInt16(r6, (this.reserved1 << 12) + this.min_spatial_segmentation_idc);
        IsoTypeWriter.writeUInt8(r6, (this.reserved2 << 2) + this.parallelismType);
        IsoTypeWriter.writeUInt8(r6, (this.reserved3 << 2) + this.chromaFormat);
        IsoTypeWriter.writeUInt8(r6, (this.reserved4 << 3) + this.bitDepthLumaMinus8);
        IsoTypeWriter.writeUInt8(r6, (this.reserved5 << 3) + this.bitDepthChromaMinus8);
        IsoTypeWriter.writeUInt16(r6, this.avgFrameRate);
        int r03 = (this.constantFrameRate << 6) + (this.numTemporalLayers << 3);
        if (this.temporalIdNested == false) goto L21;
        int r12 = 4;
    L22:
        IsoTypeWriter.writeUInt8(r6, (r03 + r12) + this.lengthSizeMinusOne);
        IsoTypeWriter.writeUInt8(r6, this.arrays.size());
        Iterator<Array> r5 = this.arrays.iterator();
    L24:
        if (r5.hasNext() == false) goto L37;
        Array r04 = r5.next();
        if (r04.array_completeness == false) goto L28;
        int r13 = 128;
    L30:
        if (r04.reserved == false) goto L32;
        int r3 = 64;
    L33:
        IsoTypeWriter.writeUInt8(r6, (r13 + r3) + r04.nal_unit_type);
        IsoTypeWriter.writeUInt16(r6, r04.nalUnits.size());
        Iterator<byte[]> r05 = r04.nalUnits.iterator();
    L35:
        if (r05.hasNext() == false) goto L24;
        byte[] r14 = r05.next();
        IsoTypeWriter.writeUInt16(r6, r14.length);
        r6.put(r14);
        goto L35
    L32:
        r3 = 0;
        goto L33
    L28:
        r13 = 0;
        goto L30
    L37:
        return;
    L21:
        r12 = 0;
        goto L22
    L5:
        r1 = 0;
        goto L6
    }
}
