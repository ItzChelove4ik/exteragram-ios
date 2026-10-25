package com.coremedia.iso.boxes;

import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import okhttp3.internal.url._UrlKt;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.reflect.Factory;

/* loaded from: classes4.dex */
public class DataEntryUrlBox extends AbstractFullBox {
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;

    static {
        ajc$preClinit();
    }

    public DataEntryUrlBox() {
        super("url ");
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory r0 = new Factory("DataEntryUrlBox.java", DataEntryUrlBox.class);
        ajc$tjp_0 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "toString", "com.coremedia.iso.boxes.DataEntryUrlBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.lang.String"), 51);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer r1) {
        parseVersionAndFlags(r1);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer r1) {
        writeVersionAndFlags(r1);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return 4;
    }

    public String toString() {
        JoinPoint r1 = Factory.makeJP(ajc$tjp_0, this, this);
        RequiresParseDetailAspect.aspectOf().before(r1);
        return "DataEntryUrlBox[]";
    }
}
