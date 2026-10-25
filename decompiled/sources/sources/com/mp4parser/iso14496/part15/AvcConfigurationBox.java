package com.mp4parser.iso14496.part15;

import com.googlecode.mp4parser.AbstractBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.Conversions;
import org.aspectj.runtime.reflect.Factory;

/* loaded from: classes5.dex */
public final class AvcConfigurationBox extends AbstractBox {
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_10 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_11 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_12 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_13 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_14 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_15 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_16 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_17 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_18 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_19 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_20 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_21 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_22 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_23 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_24 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_25 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_26 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_27 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_28 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_29 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_8 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_9 = null;
    public AvcDecoderConfigurationRecord avcDecoderConfigurationRecord;

    static {
        ajc$preClinit();
    }

    public AvcConfigurationBox() {
        super("avcC");
        this.avcDecoderConfigurationRecord = new AvcDecoderConfigurationRecord();
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory r0 = new Factory("AvcConfigurationBox.java", AvcConfigurationBox.class);
        ajc$tjp_0 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getConfigurationVersion", "com.mp4parser.iso14496.part15.AvcConfigurationBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "int"), 44);
        ajc$tjp_1 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getAvcProfileIndication", "com.mp4parser.iso14496.part15.AvcConfigurationBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "int"), 48);
        ajc$tjp_10 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setAvcLevelIndication", "com.mp4parser.iso14496.part15.AvcConfigurationBox", "int", "avcLevelIndication", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 84);
        ajc$tjp_11 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setLengthSizeMinusOne", "com.mp4parser.iso14496.part15.AvcConfigurationBox", "int", "lengthSizeMinusOne", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 88);
        ajc$tjp_12 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setSequenceParameterSets", "com.mp4parser.iso14496.part15.AvcConfigurationBox", "java.util.List", "sequenceParameterSets", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 92);
        ajc$tjp_13 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setPictureParameterSets", "com.mp4parser.iso14496.part15.AvcConfigurationBox", "java.util.List", "pictureParameterSets", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 96);
        ajc$tjp_14 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getChromaFormat", "com.mp4parser.iso14496.part15.AvcConfigurationBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "int"), 100);
        ajc$tjp_15 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setChromaFormat", "com.mp4parser.iso14496.part15.AvcConfigurationBox", "int", "chromaFormat", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 104);
        ajc$tjp_16 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getBitDepthLumaMinus8", "com.mp4parser.iso14496.part15.AvcConfigurationBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "int"), 108);
        ajc$tjp_17 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setBitDepthLumaMinus8", "com.mp4parser.iso14496.part15.AvcConfigurationBox", "int", "bitDepthLumaMinus8", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 112);
        ajc$tjp_18 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getBitDepthChromaMinus8", "com.mp4parser.iso14496.part15.AvcConfigurationBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "int"), 116);
        ajc$tjp_19 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setBitDepthChromaMinus8", "com.mp4parser.iso14496.part15.AvcConfigurationBox", "int", "bitDepthChromaMinus8", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 120);
        ajc$tjp_2 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getProfileCompatibility", "com.mp4parser.iso14496.part15.AvcConfigurationBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "int"), 52);
        ajc$tjp_20 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getSequenceParameterSetExts", "com.mp4parser.iso14496.part15.AvcConfigurationBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.util.List"), 124);
        ajc$tjp_21 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setSequenceParameterSetExts", "com.mp4parser.iso14496.part15.AvcConfigurationBox", "java.util.List", "sequenceParameterSetExts", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 128);
        ajc$tjp_22 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "hasExts", "com.mp4parser.iso14496.part15.AvcConfigurationBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "boolean"), 132);
        ajc$tjp_23 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setHasExts", "com.mp4parser.iso14496.part15.AvcConfigurationBox", "boolean", "hasExts", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 136);
        ajc$tjp_24 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getContentSize", "com.mp4parser.iso14496.part15.AvcConfigurationBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "long"), 147);
        ajc$tjp_25 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getContent", "com.mp4parser.iso14496.part15.AvcConfigurationBox", "java.nio.ByteBuffer", "byteBuffer", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 153);
        ajc$tjp_26 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getSPS", "com.mp4parser.iso14496.part15.AvcConfigurationBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "[Ljava.lang.String;"), 158);
        ajc$tjp_27 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getPPS", "com.mp4parser.iso14496.part15.AvcConfigurationBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "[Ljava.lang.String;"), 162);
        ajc$tjp_28 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getavcDecoderConfigurationRecord", "com.mp4parser.iso14496.part15.AvcConfigurationBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "com.mp4parser.iso14496.part15.AvcDecoderConfigurationRecord"), 167);
        ajc$tjp_29 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "toString", "com.mp4parser.iso14496.part15.AvcConfigurationBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.lang.String"), 172);
        ajc$tjp_3 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getAvcLevelIndication", "com.mp4parser.iso14496.part15.AvcConfigurationBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "int"), 56);
        ajc$tjp_4 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getLengthSizeMinusOne", "com.mp4parser.iso14496.part15.AvcConfigurationBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "int"), 60);
        ajc$tjp_5 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getSequenceParameterSets", "com.mp4parser.iso14496.part15.AvcConfigurationBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.util.List"), 64);
        ajc$tjp_6 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getPictureParameterSets", "com.mp4parser.iso14496.part15.AvcConfigurationBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.util.List"), 68);
        ajc$tjp_7 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setConfigurationVersion", "com.mp4parser.iso14496.part15.AvcConfigurationBox", "int", "configurationVersion", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 72);
        ajc$tjp_8 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setAvcProfileIndication", "com.mp4parser.iso14496.part15.AvcConfigurationBox", "int", "avcProfileIndication", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 76);
        ajc$tjp_9 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setProfileCompatibility", "com.mp4parser.iso14496.part15.AvcConfigurationBox", "int", "profileCompatibility", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 80);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer r2) {
        this.avcDecoderConfigurationRecord = new AvcDecoderConfigurationRecord(r2);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_25, this, this, r3);
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.avcDecoderConfigurationRecord.getContent(r3);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_24, this, this);
        RequiresParseDetailAspect.aspectOf().before(r0);
        return this.avcDecoderConfigurationRecord.getContentSize();
    }

    public void setAvcLevelIndication(int r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_10, this, this, Conversions.intObject(r3));
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.avcDecoderConfigurationRecord.avcLevelIndication = r3;
    }

    public void setAvcProfileIndication(int r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_8, this, this, Conversions.intObject(r3));
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.avcDecoderConfigurationRecord.avcProfileIndication = r3;
    }

    public void setBitDepthChromaMinus8(int r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_19, this, this, Conversions.intObject(r3));
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.avcDecoderConfigurationRecord.bitDepthChromaMinus8 = r3;
    }

    public void setBitDepthLumaMinus8(int r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_17, this, this, Conversions.intObject(r3));
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.avcDecoderConfigurationRecord.bitDepthLumaMinus8 = r3;
    }

    public void setChromaFormat(int r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_15, this, this, Conversions.intObject(r3));
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.avcDecoderConfigurationRecord.chromaFormat = r3;
    }

    public void setConfigurationVersion(int r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_7, this, this, Conversions.intObject(r3));
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.avcDecoderConfigurationRecord.configurationVersion = r3;
    }

    public void setLengthSizeMinusOne(int r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_11, this, this, Conversions.intObject(r3));
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.avcDecoderConfigurationRecord.lengthSizeMinusOne = r3;
    }

    public void setPictureParameterSets(List<byte[]> r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_13, this, this, r3);
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.avcDecoderConfigurationRecord.pictureParameterSets = r3;
    }

    public void setProfileCompatibility(int r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_9, this, this, Conversions.intObject(r3));
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.avcDecoderConfigurationRecord.profileCompatibility = r3;
    }

    public void setSequenceParameterSets(List<byte[]> r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_12, this, this, r3);
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.avcDecoderConfigurationRecord.sequenceParameterSets = r3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_29, this, this));
        return "AvcConfigurationBox{avcDecoderConfigurationRecord=" + this.avcDecoderConfigurationRecord + '}';
    }
}
