package org.telegram.messenger.video;

import com.googlecode.mp4parser.AbstractBox;
import java.nio.ByteBuffer;
import java.util.List;
import org.telegram.messenger.video.HevcDecoderConfigurationRecord;

/* loaded from: classes6.dex */
public class HevcConfigurationBox extends AbstractBox {
    public static final String TYPE = "hvcC";
    private HevcDecoderConfigurationRecord hevcDecoderConfigurationRecord;

    public HevcConfigurationBox() {
        super(TYPE);
        this.hevcDecoderConfigurationRecord = new HevcDecoderConfigurationRecord();
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer r1) {
        this.hevcDecoderConfigurationRecord.parse(r1);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L18:
        return false;
    L8:
        if (getClass() != r5.getClass()) goto L18;
        HevcDecoderConfigurationRecord r4 = this.hevcDecoderConfigurationRecord;
        HevcDecoderConfigurationRecord r52 = ((HevcConfigurationBox) r5).hevcDecoderConfigurationRecord;
        if (r4 != null) goto L13;
        if (r52 == null) goto L17;
    L16:
        return false;
    L17:
        return true;
    L13:
        if (r4.equals(r52) == true) goto L17;
        goto L17
    }

    public List<HevcDecoderConfigurationRecord.Array> getArrays() {
        return this.hevcDecoderConfigurationRecord.arrays;
    }

    public int getAvgFrameRate() {
        return this.hevcDecoderConfigurationRecord.avgFrameRate;
    }

    public int getBitDepthChromaMinus8() {
        return this.hevcDecoderConfigurationRecord.bitDepthChromaMinus8;
    }

    public int getBitDepthLumaMinus8() {
        return this.hevcDecoderConfigurationRecord.bitDepthLumaMinus8;
    }

    public int getChromaFormat() {
        return this.hevcDecoderConfigurationRecord.chromaFormat;
    }

    public int getConfigurationVersion() {
        return this.hevcDecoderConfigurationRecord.configurationVersion;
    }

    public int getConstantFrameRate() {
        return this.hevcDecoderConfigurationRecord.constantFrameRate;
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer r1) {
        this.hevcDecoderConfigurationRecord.write(r1);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return this.hevcDecoderConfigurationRecord.getSize();
    }

    public long getGeneral_constraint_indicator_flags() {
        return this.hevcDecoderConfigurationRecord.general_constraint_indicator_flags;
    }

    public int getGeneral_level_idc() {
        return this.hevcDecoderConfigurationRecord.general_level_idc;
    }

    public long getGeneral_profile_compatibility_flags() {
        return this.hevcDecoderConfigurationRecord.general_profile_compatibility_flags;
    }

    public int getGeneral_profile_idc() {
        return this.hevcDecoderConfigurationRecord.general_profile_idc;
    }

    public int getGeneral_profile_space() {
        return this.hevcDecoderConfigurationRecord.general_profile_space;
    }

    public HevcDecoderConfigurationRecord getHevcDecoderConfigurationRecord() {
        return this.hevcDecoderConfigurationRecord;
    }

    public int getLengthSizeMinusOne() {
        return this.hevcDecoderConfigurationRecord.lengthSizeMinusOne;
    }

    public int getMin_spatial_segmentation_idc() {
        return this.hevcDecoderConfigurationRecord.min_spatial_segmentation_idc;
    }

    public int getNumTemporalLayers() {
        return this.hevcDecoderConfigurationRecord.numTemporalLayers;
    }

    public int getParallelismType() {
        return this.hevcDecoderConfigurationRecord.parallelismType;
    }

    public int hashCode() {
        HevcDecoderConfigurationRecord r0 = this.hevcDecoderConfigurationRecord;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.hashCode();
    }

    public boolean isGeneral_tier_flag() {
        return this.hevcDecoderConfigurationRecord.general_tier_flag;
    }

    public boolean isTemporalIdNested() {
        return this.hevcDecoderConfigurationRecord.temporalIdNested;
    }

    public void setHevcDecoderConfigurationRecord(HevcDecoderConfigurationRecord r1) {
        this.hevcDecoderConfigurationRecord = r1;
    }
}
