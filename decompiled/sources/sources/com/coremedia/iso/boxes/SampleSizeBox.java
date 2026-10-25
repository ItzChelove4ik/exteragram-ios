package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.googlecode.mp4parser.util.CastUtils;
import java.nio.ByteBuffer;
import okhttp3.internal.url._UrlKt;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.reflect.Factory;

/* loaded from: classes4.dex */
public class SampleSizeBox extends AbstractFullBox {
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_6 = null;
    int sampleCount;
    private long sampleSize;
    private long[] sampleSizes;

    static {
        ajc$preClinit();
    }

    public SampleSizeBox() {
        super("stsz");
        this.sampleSizes = new long[0];
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory r0 = new Factory("SampleSizeBox.java", SampleSizeBox.class);
        ajc$tjp_0 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getSampleSize", "com.coremedia.iso.boxes.SampleSizeBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "long"), 50);
        ajc$tjp_1 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setSampleSize", "com.coremedia.iso.boxes.SampleSizeBox", "long", "sampleSize", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 54);
        ajc$tjp_2 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getSampleSizeAtIndex", "com.coremedia.iso.boxes.SampleSizeBox", "int", "index", _UrlKt.FRAGMENT_ENCODE_SET, "long"), 59);
        ajc$tjp_3 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getSampleCount", "com.coremedia.iso.boxes.SampleSizeBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "long"), 67);
        ajc$tjp_4 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getSampleSizes", "com.coremedia.iso.boxes.SampleSizeBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "[J"), 76);
        ajc$tjp_5 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setSampleSizes", "com.coremedia.iso.boxes.SampleSizeBox", "[J", "sampleSizes", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 80);
        ajc$tjp_6 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "toString", "com.coremedia.iso.boxes.SampleSizeBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.lang.String"), 119);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer r6) {
        parseVersionAndFlags(r6);
        this.sampleSize = IsoTypeReader.readUInt32(r6);
        int r0 = CastUtils.l2i(IsoTypeReader.readUInt32(r6));
        this.sampleCount = r0;
        if (this.sampleSize != 0) goto L9;
        this.sampleSizes = new long[r0];
        int r02 = 0;
    L6:
        if (r02 >= this.sampleCount) goto L11;
        this.sampleSizes[r02] = IsoTypeReader.readUInt32(r6);
        r02 = r02 + 1;
        goto L6
    L11:
        return;
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer r5) {
        writeVersionAndFlags(r5);
        IsoTypeWriter.writeUInt32(r5, this.sampleSize);
        if (this.sampleSize != 0) goto L8;
        IsoTypeWriter.writeUInt32(r5, this.sampleSizes.length);
        long[] r0 = this.sampleSizes;
        int r1 = r0.length;
        int r4 = 0;
    L5:
        if (r4 >= r1) goto L6;
        IsoTypeWriter.writeUInt32(r5, r0[r4]);
        r4 = r4 + 1;
        goto L5
    L6:
        return;
    L8:
        IsoTypeWriter.writeUInt32(r5, this.sampleCount);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        if (this.sampleSize != 0) goto L5;
        int r4 = this.sampleSizes.length * 4;
    L7:
        return r4 + 12;
    L5:
        r4 = 0;
        goto L7
    }

    public long getSampleCount() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this));
        if (this.sampleSize <= 0) goto L7;
        int r4 = this.sampleCount;
    L6:
        return r4;
    L7:
        r4 = this.sampleSizes.length;
        goto L6
    }

    public long getSampleSize() {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_0, this, this);
        RequiresParseDetailAspect.aspectOf().before(r0);
        return this.sampleSize;
    }

    public void setSampleSizes(long[] r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_5, this, this, r3);
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.sampleSizes = r3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this));
        return "SampleSizeBox[sampleSize=" + getSampleSize() + ";sampleCount=" + getSampleCount() + "]";
    }
}
