package com.googlecode.mp4parser.boxes.mp4;

import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import okhttp3.internal.url._UrlKt;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.reflect.Factory;

/* loaded from: classes5.dex */
public class ESDescriptorBox extends AbstractDescriptorBox {
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_3 = null;

    static {
        ajc$preClinit();
    }

    public ESDescriptorBox() {
        super("esds");
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory r0 = new Factory("ESDescriptorBox.java", ESDescriptorBox.class);
        ajc$tjp_0 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getEsDescriptor", "com.googlecode.mp4parser.boxes.mp4.ESDescriptorBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "com.googlecode.mp4parser.boxes.mp4.objectdescriptors.ESDescriptor"), 33);
        ajc$tjp_1 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setEsDescriptor", "com.googlecode.mp4parser.boxes.mp4.ESDescriptorBox", "com.googlecode.mp4parser.boxes.mp4.objectdescriptors.ESDescriptor", "esDescriptor", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 37);
        ajc$tjp_2 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "equals", "com.googlecode.mp4parser.boxes.mp4.ESDescriptorBox", "java.lang.Object", "o", _UrlKt.FRAGMENT_ENCODE_SET, "boolean"), 42);
        ajc$tjp_3 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "hashCode", "com.googlecode.mp4parser.boxes.mp4.ESDescriptorBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "int"), 53);
    }

    public boolean equals(Object r5) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_2, this, this, r5);
        RequiresParseDetailAspect.aspectOf().before(r0);
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L18:
        return false;
    L8:
        if (getClass() != r5.getClass()) goto L18;
        ByteBuffer r4 = this.data;
        ByteBuffer r52 = ((ESDescriptorBox) r5).data;
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

    public int hashCode() {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_3, this, this);
        RequiresParseDetailAspect.aspectOf().before(r0);
        ByteBuffer r2 = this.data;
        if (r2 != null) goto L5;
        return 0;
    L5:
        return r2.hashCode();
    }
}
