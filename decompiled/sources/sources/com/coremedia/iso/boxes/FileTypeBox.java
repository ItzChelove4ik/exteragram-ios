package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.reflect.Factory;

/* loaded from: classes4.dex */
public class FileTypeBox extends AbstractBox {
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_5 = null;
    private List<String> compatibleBrands;
    private String majorBrand;
    private long minorVersion;

    static {
        ajc$preClinit();
    }

    public FileTypeBox(String r2, long r3, List<String> r5) {
        super("ftyp");
        List r0 = Collections.EMPTY_LIST;
        this.majorBrand = r2;
        this.minorVersion = r3;
        this.compatibleBrands = r5;
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory r0 = new Factory("FileTypeBox.java", FileTypeBox.class);
        ajc$tjp_0 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getMajorBrand", "com.coremedia.iso.boxes.FileTypeBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.lang.String"), 85);
        ajc$tjp_1 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setMajorBrand", "com.coremedia.iso.boxes.FileTypeBox", "java.lang.String", "majorBrand", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 94);
        ajc$tjp_2 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setMinorVersion", "com.coremedia.iso.boxes.FileTypeBox", "long", "minorVersion", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 103);
        ajc$tjp_3 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getMinorVersion", "com.coremedia.iso.boxes.FileTypeBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "long"), 113);
        ajc$tjp_4 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getCompatibleBrands", "com.coremedia.iso.boxes.FileTypeBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.util.List"), 122);
        ajc$tjp_5 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setCompatibleBrands", "com.coremedia.iso.boxes.FileTypeBox", "java.util.List", "compatibleBrands", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 126);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer r5) {
        this.majorBrand = IsoTypeReader.read4cc(r5);
        this.minorVersion = IsoTypeReader.readUInt32(r5);
        int r0 = r5.remaining() / 4;
        this.compatibleBrands = new LinkedList();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L4;
        this.compatibleBrands.add(IsoTypeReader.read4cc(r5));
        r1 = r1 + 1;
        goto L3
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer r3) {
        r3.put(IsoFile.fourCCtoBytes(this.majorBrand));
        IsoTypeWriter.writeUInt32(r3, this.minorVersion);
        Iterator<String> r2 = this.compatibleBrands.iterator();
    L4:
        if (r2.hasNext() == false) goto L5;
        r3.put(IsoFile.fourCCtoBytes(r2.next()));
        goto L4
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return (this.compatibleBrands.size() * 4) + 8;
    }

    public String getMajorBrand() {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_0, this, this);
        RequiresParseDetailAspect.aspectOf().before(r0);
        return this.majorBrand;
    }

    public long getMinorVersion() {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_3, this, this);
        RequiresParseDetailAspect.aspectOf().before(r0);
        return this.minorVersion;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("FileTypeBox[majorBrand=");
        r0.append(getMajorBrand());
        r0.append(";minorVersion=");
        r0.append(getMinorVersion());
        Iterator<String> r3 = this.compatibleBrands.iterator();
    L4:
        if (r3.hasNext() == false) goto L5;
        String r1 = r3.next();
        r0.append(";compatibleBrand=");
        r0.append(r1);
        goto L4
    L5:
        r0.append("]");
        return r0.toString();
    }
}
