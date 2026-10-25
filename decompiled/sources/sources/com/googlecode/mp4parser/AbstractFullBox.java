package com.googlecode.mp4parser;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.boxes.Box;
import java.nio.ByteBuffer;
import okhttp3.internal.url._UrlKt;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.Conversions;
import org.aspectj.runtime.reflect.Factory;

/* loaded from: classes5.dex */
public abstract class AbstractFullBox extends AbstractBox implements Box {
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_1 = null;
    private int flags;
    private int version;

    static {
        ajc$preClinit();
    }

    public AbstractFullBox(String r1) {
        super(r1);
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory r0 = new Factory("AbstractFullBox.java", AbstractFullBox.class);
        ajc$tjp_0 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setVersion", "com.googlecode.mp4parser.AbstractFullBox", "int", "version", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 51);
        ajc$tjp_1 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setFlags", "com.googlecode.mp4parser.AbstractFullBox", "int", "flags", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 64);
    }

    public int getFlags() {
        if (this.isParsed == true) goto L6;
        parseDetails();
    L6:
        return this.flags;
    }

    public int getVersion() {
        if (this.isParsed == true) goto L6;
        parseDetails();
    L6:
        return this.version;
    }

    public final long parseVersionAndFlags(ByteBuffer r2) {
        this.version = IsoTypeReader.readUInt8(r2);
        this.flags = IsoTypeReader.readUInt24(r2);
        return 4;
    }

    public void setFlags(int r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_1, this, this, Conversions.intObject(r3));
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.flags = r3;
    }

    public void setVersion(int r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_0, this, this, Conversions.intObject(r3));
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.version = r3;
    }

    public final void writeVersionAndFlags(ByteBuffer r2) {
        IsoTypeWriter.writeUInt8(r2, this.version);
        IsoTypeWriter.writeUInt24(r2, this.flags);
    }
}
