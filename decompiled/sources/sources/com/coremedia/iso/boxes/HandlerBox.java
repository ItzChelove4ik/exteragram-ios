package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.Utf8;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.reflect.Factory;
import org.chromium.support_lib_boundary.WebViewProviderFactoryBoundaryInterface;

/* loaded from: classes4.dex */
public class HandlerBox extends AbstractFullBox {
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_5 = null;
    public static final Map<String, String> readableTypes = null;

    /* renamed from: a, reason: collision with root package name */
    private long f36a;

    /* renamed from: b, reason: collision with root package name */
    private long f37b;

    /* renamed from: c, reason: collision with root package name */
    private long f38c;
    private String handlerType;
    private String name;
    private long shouldBeZeroButAppleWritesHereSomeValue;
    private boolean zeroTerm;

    static {
        ajc$preClinit();
        HashMap r0 = new HashMap();
        r0.put("odsm", "ObjectDescriptorStream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        r0.put("crsm", "ClockReferenceStream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        r0.put("sdsm", "SceneDescriptionStream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        r0.put("m7sm", "MPEG7Stream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        r0.put("ocsm", "ObjectContentInfoStream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        r0.put("ipsm", "IPMP Stream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        r0.put("mjsm", "MPEG-J Stream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        r0.put("mdir", "Apple Meta Data iTunes Reader");
        r0.put("mp7b", "MPEG-7 binary XML");
        r0.put("mp7t", "MPEG-7 XML");
        r0.put("vide", "Video Track");
        r0.put("soun", "Sound Track");
        r0.put(PluginsConstants.Settings.HINT, "Hint Track");
        r0.put("appl", "Apple specific");
        r0.put("meta", "Timed Metadata track - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        readableTypes = Collections.unmodifiableMap(r0);
    }

    public HandlerBox() {
        super("hdlr");
        this.name = null;
        this.zeroTerm = true;
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory r0 = new Factory("HandlerBox.java", HandlerBox.class);
        ajc$tjp_0 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getHandlerType", "com.coremedia.iso.boxes.HandlerBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.lang.String"), 78);
        ajc$tjp_1 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setName", "com.coremedia.iso.boxes.HandlerBox", "java.lang.String", "name", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 87);
        ajc$tjp_2 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setHandlerType", "com.coremedia.iso.boxes.HandlerBox", "java.lang.String", "handlerType", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 91);
        ajc$tjp_3 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getName", "com.coremedia.iso.boxes.HandlerBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.lang.String"), 95);
        ajc$tjp_4 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getHumanReadableTrackType", "com.coremedia.iso.boxes.HandlerBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.lang.String"), 99);
        ajc$tjp_5 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "toString", "com.coremedia.iso.boxes.HandlerBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.lang.String"), 149);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer r4) {
        parseVersionAndFlags(r4);
        this.shouldBeZeroButAppleWritesHereSomeValue = IsoTypeReader.readUInt32(r4);
        this.handlerType = IsoTypeReader.read4cc(r4);
        this.f36a = IsoTypeReader.readUInt32(r4);
        this.f37b = IsoTypeReader.readUInt32(r4);
        this.f38c = IsoTypeReader.readUInt32(r4);
        if (r4.remaining() <= 0) goto L10;
        String r42 = IsoTypeReader.readString(r4, r4.remaining());
        this.name = r42;
        if (r42.endsWith(WebViewProviderFactoryBoundaryInterface.MULTI_COOKIE_VALUE_SEPARATOR) == false) goto L8;
        String r43 = this.name;
        this.name = r43.substring(0, r43.length() - 1);
        this.zeroTerm = true;
        return;
    L8:
        this.zeroTerm = false;
        return;
    L10:
        this.zeroTerm = false;
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer r3) {
        writeVersionAndFlags(r3);
        IsoTypeWriter.writeUInt32(r3, this.shouldBeZeroButAppleWritesHereSomeValue);
        r3.put(IsoFile.fourCCtoBytes(this.handlerType));
        IsoTypeWriter.writeUInt32(r3, this.f36a);
        IsoTypeWriter.writeUInt32(r3, this.f37b);
        IsoTypeWriter.writeUInt32(r3, this.f38c);
        String r0 = this.name;
        if (r0 == null) goto L6;
        r3.put(Utf8.convert(r0));
    L6:
        if (this.zeroTerm == false) goto L9;
        r3.put((byte) 0);
        return;
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        boolean r0 = this.zeroTerm;
        String r2 = this.name;
        if (r0 == false) goto L7;
        int r22 = Utf8.utf8StringLengthInBytes(r2) + 25;
    L6:
        return r22;
    L7:
        r22 = Utf8.utf8StringLengthInBytes(r2) + 24;
        goto L6
    }

    public String getHandlerType() {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_0, this, this);
        RequiresParseDetailAspect.aspectOf().before(r0);
        return this.handlerType;
    }

    public String getName() {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_3, this, this);
        RequiresParseDetailAspect.aspectOf().before(r0);
        return this.name;
    }

    public void setHandlerType(String r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_2, this, this, r3);
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.handlerType = r3;
    }

    public void setName(String r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_1, this, this, r3);
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.name = r3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this));
        return "HandlerBox[handlerType=" + getHandlerType() + ";name=" + getName() + "]";
    }
}
