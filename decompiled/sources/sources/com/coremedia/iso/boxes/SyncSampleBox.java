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
public class SyncSampleBox extends AbstractFullBox {
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_2 = null;
    private long[] sampleNumber;

    static {
        ajc$preClinit();
    }

    public SyncSampleBox() {
        super("stss");
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory r0 = new Factory("SyncSampleBox.java", SyncSampleBox.class);
        ajc$tjp_0 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getSampleNumber", "com.coremedia.iso.boxes.SyncSampleBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "[J"), 46);
        ajc$tjp_1 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "toString", "com.coremedia.iso.boxes.SyncSampleBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.lang.String"), 77);
        ajc$tjp_2 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setSampleNumber", "com.coremedia.iso.boxes.SyncSampleBox", "[J", "sampleNumber", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 81);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer r6) {
        parseVersionAndFlags(r6);
        int r0 = CastUtils.l2i(IsoTypeReader.readUInt32(r6));
        this.sampleNumber = new long[r0];
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L4;
        this.sampleNumber[r1] = IsoTypeReader.readUInt32(r6);
        r1 = r1 + 1;
        goto L3
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer r5) {
        writeVersionAndFlags(r5);
        IsoTypeWriter.writeUInt32(r5, this.sampleNumber.length);
        long[] r4 = this.sampleNumber;
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L4;
        IsoTypeWriter.writeUInt32(r5, r4[r1]);
        r1 = r1 + 1;
        goto L3
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return (this.sampleNumber.length * 4) + 8;
    }

    public void setSampleNumber(long[] r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_2, this, this, r3);
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.sampleNumber = r3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this));
        return "SyncSampleBox[entryCount=" + this.sampleNumber.length + "]";
    }
}
