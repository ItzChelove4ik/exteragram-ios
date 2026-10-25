package com.googlecode.mp4parser.boxes.mp4;

import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.ObjectDescriptorFactory;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.url._UrlKt;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.reflect.Factory;

/* loaded from: classes5.dex */
public abstract class AbstractDescriptorBox extends AbstractFullBox {
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_4 = null;
    private static Logger log;
    protected ByteBuffer data;
    protected BaseDescriptor descriptor;

    static {
        ajc$preClinit();
        log = Logger.getLogger(AbstractDescriptorBox.class.getName());
    }

    public AbstractDescriptorBox(String r1) {
        super(r1);
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory r0 = new Factory("AbstractDescriptorBox.java", AbstractDescriptorBox.class);
        ajc$tjp_0 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getData", "com.googlecode.mp4parser.boxes.mp4.AbstractDescriptorBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.nio.ByteBuffer"), 42);
        ajc$tjp_1 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getDescriptor", "com.googlecode.mp4parser.boxes.mp4.AbstractDescriptorBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor"), 58);
        ajc$tjp_2 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getDescriptorAsString", "com.googlecode.mp4parser.boxes.mp4.AbstractDescriptorBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.lang.String"), 62);
        ajc$tjp_3 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setDescriptor", "com.googlecode.mp4parser.boxes.mp4.AbstractDescriptorBox", "com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor", "descriptor", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 66);
        ajc$tjp_4 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setData", "com.googlecode.mp4parser.boxes.mp4.AbstractDescriptorBox", "java.nio.ByteBuffer", "data", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 70);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer r4) {
        parseVersionAndFlags(r4);
        this.data = r4.slice();
        r4.position(r4.position() + r4.remaining());
        this.data.rewind();     // Catch: IndexOutOfBoundsException -> L5 IOException -> L7
        this.descriptor = ObjectDescriptorFactory.createFrom(-1, this.data);     // Catch: IndexOutOfBoundsException -> L5 IOException -> L7
        return;
    L7:
        e = move-exception;
        log.log(Level.WARNING, "Error parsing ObjectDescriptor", e);
        return;
    L5:
        e = move-exception;
        log.log(Level.WARNING, "Error parsing ObjectDescriptor", e);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer r2) {
        writeVersionAndFlags(r2);
        this.data.rewind();
        r2.put(this.data);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return this.data.limit() + 4;
    }

    public void setData(ByteBuffer r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_4, this, this, r3);
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.data = r3;
    }
}
