package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.googlecode.mp4parser.util.DateHelper;
import java.nio.ByteBuffer;
import java.util.Date;
import okhttp3.internal.url._UrlKt;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.Conversions;
import org.aspectj.runtime.reflect.Factory;

/* loaded from: classes4.dex */
public class MediaHeaderBox extends AbstractFullBox {
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_10 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_8 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_9 = null;
    private Date creationTime;
    private long duration;
    private String language;
    private Date modificationTime;
    private long timescale;

    static {
        ajc$preClinit();
    }

    public MediaHeaderBox() {
        super("mdhd");
        this.creationTime = new Date();
        this.modificationTime = new Date();
        this.language = "eng";
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory r0 = new Factory("MediaHeaderBox.java", MediaHeaderBox.class);
        ajc$tjp_0 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getCreationTime", "com.coremedia.iso.boxes.MediaHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.util.Date"), 46);
        ajc$tjp_1 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getModificationTime", "com.coremedia.iso.boxes.MediaHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.util.Date"), 50);
        ajc$tjp_10 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "toString", "com.coremedia.iso.boxes.MediaHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.lang.String"), 118);
        ajc$tjp_2 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getTimescale", "com.coremedia.iso.boxes.MediaHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "long"), 54);
        ajc$tjp_3 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getDuration", "com.coremedia.iso.boxes.MediaHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "long"), 58);
        ajc$tjp_4 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getLanguage", "com.coremedia.iso.boxes.MediaHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.lang.String"), 62);
        ajc$tjp_5 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setCreationTime", "com.coremedia.iso.boxes.MediaHeaderBox", "java.util.Date", "creationTime", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 79);
        ajc$tjp_6 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setModificationTime", "com.coremedia.iso.boxes.MediaHeaderBox", "java.util.Date", "modificationTime", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 83);
        ajc$tjp_7 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setTimescale", "com.coremedia.iso.boxes.MediaHeaderBox", "long", "timescale", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 87);
        ajc$tjp_8 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setDuration", "com.coremedia.iso.boxes.MediaHeaderBox", "long", "duration", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 91);
        ajc$tjp_9 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setLanguage", "com.coremedia.iso.boxes.MediaHeaderBox", "java.lang.String", "language", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 95);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer r3) {
        parseVersionAndFlags(r3);
        if (getVersion() != 1) goto L5;
        this.creationTime = DateHelper.convert(IsoTypeReader.readUInt64(r3));
        this.modificationTime = DateHelper.convert(IsoTypeReader.readUInt64(r3));
        this.timescale = IsoTypeReader.readUInt32(r3);
        this.duration = IsoTypeReader.readUInt64(r3);
    L6:
        this.language = IsoTypeReader.readIso639(r3);
        IsoTypeReader.readUInt16(r3);
        return;
    L5:
        this.creationTime = DateHelper.convert(IsoTypeReader.readUInt32(r3));
        this.modificationTime = DateHelper.convert(IsoTypeReader.readUInt32(r3));
        this.timescale = IsoTypeReader.readUInt32(r3);
        this.duration = IsoTypeReader.readUInt32(r3);
        goto L6
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer r4) {
        writeVersionAndFlags(r4);
        int r0 = getVersion();
        Date r1 = this.creationTime;
        if (r0 != 1) goto L5;
        IsoTypeWriter.writeUInt64(r4, DateHelper.convert(r1));
        IsoTypeWriter.writeUInt64(r4, DateHelper.convert(this.modificationTime));
        IsoTypeWriter.writeUInt32(r4, this.timescale);
        IsoTypeWriter.writeUInt64(r4, this.duration);
    L6:
        IsoTypeWriter.writeIso639(r4, this.language);
        IsoTypeWriter.writeUInt16(r4, 0);
        return;
    L5:
        IsoTypeWriter.writeUInt32(r4, DateHelper.convert(r1));
        IsoTypeWriter.writeUInt32(r4, DateHelper.convert(this.modificationTime));
        IsoTypeWriter.writeUInt32(r4, this.timescale);
        IsoTypeWriter.writeUInt32(r4, this.duration);
        goto L6
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        if (getVersion() != 1) goto L5;
        long r0 = 32;
    L7:
        return r0 + 4;
    L5:
        r0 = 20;
        goto L7
    }

    public Date getCreationTime() {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_0, this, this);
        RequiresParseDetailAspect.aspectOf().before(r0);
        return this.creationTime;
    }

    public long getDuration() {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_3, this, this);
        RequiresParseDetailAspect.aspectOf().before(r0);
        return this.duration;
    }

    public String getLanguage() {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_4, this, this);
        RequiresParseDetailAspect.aspectOf().before(r0);
        return this.language;
    }

    public Date getModificationTime() {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_1, this, this);
        RequiresParseDetailAspect.aspectOf().before(r0);
        return this.modificationTime;
    }

    public long getTimescale() {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_2, this, this);
        RequiresParseDetailAspect.aspectOf().before(r0);
        return this.timescale;
    }

    public void setCreationTime(Date r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_5, this, this, r3);
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.creationTime = r3;
    }

    public void setDuration(long r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_8, this, this, Conversions.longObject(r3));
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.duration = r3;
    }

    public void setLanguage(String r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_9, this, this, r3);
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.language = r3;
    }

    public void setTimescale(long r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_7, this, this, Conversions.longObject(r3));
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.timescale = r3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_10, this, this));
        return "MediaHeaderBox[creationTime=" + getCreationTime() + ";modificationTime=" + getModificationTime() + ";timescale=" + getTimescale() + ";duration=" + getDuration() + ";language=" + getLanguage() + "]";
    }
}
