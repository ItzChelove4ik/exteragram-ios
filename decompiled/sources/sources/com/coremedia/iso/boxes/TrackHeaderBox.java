package com.coremedia.iso.boxes;

import androidx.mediarouter.media.GlobalMediaRouter;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.android.dx.io.Opcodes;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.googlecode.mp4parser.util.DateHelper;
import com.googlecode.mp4parser.util.Matrix;
import java.nio.ByteBuffer;
import java.util.Date;
import okhttp3.internal.url._UrlKt;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.Conversions;
import org.aspectj.runtime.reflect.Factory;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public class TrackHeaderBox extends AbstractFullBox {
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
    private int alternateGroup;
    private Date creationTime;
    private long duration;
    private double height;
    private int layer;
    private Matrix matrix;
    private Date modificationTime;
    private long trackId;
    private float volume;
    private double width;

    static {
        ajc$preClinit();
    }

    public TrackHeaderBox() {
        super("tkhd");
        this.matrix = Matrix.ROTATE_0;
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory r0 = new Factory("TrackHeaderBox.java", TrackHeaderBox.class);
        ajc$tjp_0 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getCreationTime", "com.coremedia.iso.boxes.TrackHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.util.Date"), 60);
        ajc$tjp_1 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getModificationTime", "com.coremedia.iso.boxes.TrackHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.util.Date"), 64);
        ajc$tjp_10 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getContent", "com.coremedia.iso.boxes.TrackHeaderBox", "java.nio.ByteBuffer", "byteBuffer", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 142);
        ajc$tjp_11 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "toString", "com.coremedia.iso.boxes.TrackHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.lang.String"), 170);
        ajc$tjp_12 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setCreationTime", "com.coremedia.iso.boxes.TrackHeaderBox", "java.util.Date", "creationTime", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 196);
        ajc$tjp_13 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setModificationTime", "com.coremedia.iso.boxes.TrackHeaderBox", "java.util.Date", "modificationTime", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 203);
        ajc$tjp_14 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setTrackId", "com.coremedia.iso.boxes.TrackHeaderBox", "long", "trackId", _UrlKt.FRAGMENT_ENCODE_SET, "void"), Opcodes.DIV_INT_LIT16);
        ajc$tjp_15 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setDuration", "com.coremedia.iso.boxes.TrackHeaderBox", "long", "duration", _UrlKt.FRAGMENT_ENCODE_SET, "void"), Opcodes.XOR_INT_LIT16);
        ajc$tjp_16 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setLayer", "com.coremedia.iso.boxes.TrackHeaderBox", "int", "layer", _UrlKt.FRAGMENT_ENCODE_SET, "void"), Opcodes.OR_INT_LIT8);
        ajc$tjp_17 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setAlternateGroup", "com.coremedia.iso.boxes.TrackHeaderBox", "int", "alternateGroup", _UrlKt.FRAGMENT_ENCODE_SET, "void"), Opcodes.USHR_INT_LIT8);
        ajc$tjp_18 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setVolume", "com.coremedia.iso.boxes.TrackHeaderBox", "float", MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, _UrlKt.FRAGMENT_ENCODE_SET, "void"), 230);
        ajc$tjp_19 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setMatrix", "com.coremedia.iso.boxes.TrackHeaderBox", "com.googlecode.mp4parser.util.Matrix", "matrix", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 234);
        ajc$tjp_2 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getTrackId", "com.coremedia.iso.boxes.TrackHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "long"), 68);
        ajc$tjp_20 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setWidth", "com.coremedia.iso.boxes.TrackHeaderBox", "double", "width", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 238);
        ajc$tjp_21 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setHeight", "com.coremedia.iso.boxes.TrackHeaderBox", "double", "height", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 242);
        ajc$tjp_22 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "isEnabled", "com.coremedia.iso.boxes.TrackHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "boolean"), 247);
        ajc$tjp_23 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "isInMovie", "com.coremedia.iso.boxes.TrackHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "boolean"), Opcodes.INVOKE_POLYMORPHIC_RANGE);
        ajc$tjp_24 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "isInPreview", "com.coremedia.iso.boxes.TrackHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "boolean"), 255);
        ajc$tjp_25 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "isInPoster", "com.coremedia.iso.boxes.TrackHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "boolean"), GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED);
        ajc$tjp_26 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setEnabled", "com.coremedia.iso.boxes.TrackHeaderBox", "boolean", "enabled", _UrlKt.FRAGMENT_ENCODE_SET, "void"), GlobalMediaRouter.CallbackHandler.MSG_ROUTE_UNSELECTED);
        ajc$tjp_27 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setInMovie", "com.coremedia.iso.boxes.TrackHeaderBox", "boolean", "inMovie", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 271);
        ajc$tjp_28 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setInPreview", "com.coremedia.iso.boxes.TrackHeaderBox", "boolean", "inPreview", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 279);
        ajc$tjp_29 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setInPoster", "com.coremedia.iso.boxes.TrackHeaderBox", "boolean", "inPoster", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 287);
        ajc$tjp_3 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getDuration", "com.coremedia.iso.boxes.TrackHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "long"), 72);
        ajc$tjp_4 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getLayer", "com.coremedia.iso.boxes.TrackHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "int"), 76);
        ajc$tjp_5 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getAlternateGroup", "com.coremedia.iso.boxes.TrackHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "int"), 80);
        ajc$tjp_6 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getVolume", "com.coremedia.iso.boxes.TrackHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "float"), 84);
        ajc$tjp_7 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getMatrix", "com.coremedia.iso.boxes.TrackHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "com.googlecode.mp4parser.util.Matrix"), 88);
        ajc$tjp_8 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getWidth", "com.coremedia.iso.boxes.TrackHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "double"), 92);
        ajc$tjp_9 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getHeight", "com.coremedia.iso.boxes.TrackHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "double"), 96);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer r5) {
        parseVersionAndFlags(r5);
        if (getVersion() != 1) goto L9;
        this.creationTime = DateHelper.convert(IsoTypeReader.readUInt64(r5));
        this.modificationTime = DateHelper.convert(IsoTypeReader.readUInt64(r5));
        this.trackId = IsoTypeReader.readUInt32(r5);
        IsoTypeReader.readUInt32(r5);
        long r0 = r5.getLong();
        this.duration = r0;
        if (r0 >= (-1)) goto L10;
        GlShader$$ExternalSyntheticBUOutline1.m("The tracks duration is bigger than Long.MAX_VALUE");
        return;
    L10:
        IsoTypeReader.readUInt32(r5);
        IsoTypeReader.readUInt32(r5);
        this.layer = IsoTypeReader.readUInt16(r5);
        this.alternateGroup = IsoTypeReader.readUInt16(r5);
        this.volume = IsoTypeReader.readFixedPoint88(r5);
        IsoTypeReader.readUInt16(r5);
        this.matrix = Matrix.fromByteBuffer(r5);
        this.width = IsoTypeReader.readFixedPoint1616(r5);
        this.height = IsoTypeReader.readFixedPoint1616(r5);
        return;
    L9:
        this.creationTime = DateHelper.convert(IsoTypeReader.readUInt32(r5));
        this.modificationTime = DateHelper.convert(IsoTypeReader.readUInt32(r5));
        this.trackId = IsoTypeReader.readUInt32(r5);
        IsoTypeReader.readUInt32(r5);
        this.duration = IsoTypeReader.readUInt32(r5);
        goto L10
    }

    public int getAlternateGroup() {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_5, this, this);
        RequiresParseDetailAspect.aspectOf().before(r0);
        return this.alternateGroup;
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer r6) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_10, this, this, r6));
        writeVersionAndFlags(r6);
        int r0 = getVersion();
        Date r1 = this.creationTime;
        if (r0 != 1) goto L5;
        IsoTypeWriter.writeUInt64(r6, DateHelper.convert(r1));
        IsoTypeWriter.writeUInt64(r6, DateHelper.convert(this.modificationTime));
        IsoTypeWriter.writeUInt32(r6, this.trackId);
        IsoTypeWriter.writeUInt32(r6, 0);
        IsoTypeWriter.writeUInt64(r6, this.duration);
    L6:
        IsoTypeWriter.writeUInt32(r6, 0);
        IsoTypeWriter.writeUInt32(r6, 0);
        IsoTypeWriter.writeUInt16(r6, this.layer);
        IsoTypeWriter.writeUInt16(r6, this.alternateGroup);
        IsoTypeWriter.writeFixedPoint88(r6, this.volume);
        IsoTypeWriter.writeUInt16(r6, 0);
        this.matrix.getContent(r6);
        IsoTypeWriter.writeFixedPoint1616(r6, this.width);
        IsoTypeWriter.writeFixedPoint1616(r6, this.height);
        return;
    L5:
        IsoTypeWriter.writeUInt32(r6, DateHelper.convert(r1));
        IsoTypeWriter.writeUInt32(r6, DateHelper.convert(this.modificationTime));
        IsoTypeWriter.writeUInt32(r6, this.trackId);
        IsoTypeWriter.writeUInt32(r6, 0);
        IsoTypeWriter.writeUInt32(r6, this.duration);
        goto L6
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        if (getVersion() != 1) goto L5;
        long r0 = 36;
    L7:
        return r0 + 60;
    L5:
        r0 = 24;
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

    public double getHeight() {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_9, this, this);
        RequiresParseDetailAspect.aspectOf().before(r0);
        return this.height;
    }

    public int getLayer() {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_4, this, this);
        RequiresParseDetailAspect.aspectOf().before(r0);
        return this.layer;
    }

    public Date getModificationTime() {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_1, this, this);
        RequiresParseDetailAspect.aspectOf().before(r0);
        return this.modificationTime;
    }

    public long getTrackId() {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_2, this, this);
        RequiresParseDetailAspect.aspectOf().before(r0);
        return this.trackId;
    }

    public float getVolume() {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_6, this, this);
        RequiresParseDetailAspect.aspectOf().before(r0);
        return this.volume;
    }

    public double getWidth() {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_8, this, this);
        RequiresParseDetailAspect.aspectOf().before(r0);
        return this.width;
    }

    public void setAlternateGroup(int r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_17, this, this, Conversions.intObject(r3));
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.alternateGroup = r3;
    }

    public void setCreationTime(Date r5) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_12, this, this, r5);
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.creationTime = r5;
        if (DateHelper.convert(r5) < 4294967296L) goto L6;
        setVersion(1);
        return;
    }

    public void setDuration(long r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_15, this, this, Conversions.longObject(r3));
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.duration = r3;
        if (r3 < 4294967296L) goto L6;
        setFlags(1);
        return;
    }

    public void setEnabled(boolean r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_26, this, this, Conversions.booleanObject(r3));
        RequiresParseDetailAspect.aspectOf().before(r0);
        if (r3 == false) goto L6;
        setFlags(getFlags() | 1);
        return;
    L6:
        setFlags(getFlags() & (-2));
    }

    public void setHeight(double r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_21, this, this, Conversions.doubleObject(r3));
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.height = r3;
    }

    public void setInMovie(boolean r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_27, this, this, Conversions.booleanObject(r3));
        RequiresParseDetailAspect.aspectOf().before(r0);
        if (r3 == false) goto L6;
        setFlags(getFlags() | 2);
        return;
    L6:
        setFlags(getFlags() & (-3));
    }

    public void setInPreview(boolean r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_28, this, this, Conversions.booleanObject(r3));
        RequiresParseDetailAspect.aspectOf().before(r0);
        if (r3 == false) goto L6;
        setFlags(getFlags() | 4);
        return;
    L6:
        setFlags(getFlags() & (-5));
    }

    public void setLayer(int r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_16, this, this, Conversions.intObject(r3));
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.layer = r3;
    }

    public void setMatrix(Matrix r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_19, this, this, r3);
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.matrix = r3;
    }

    public void setModificationTime(Date r5) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_13, this, this, r5);
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.modificationTime = r5;
        if (DateHelper.convert(r5) < 4294967296L) goto L6;
        setVersion(1);
        return;
    }

    public void setTrackId(long r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_14, this, this, Conversions.longObject(r3));
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.trackId = r3;
    }

    public void setVolume(float r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_18, this, this, Conversions.floatObject(r3));
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.volume = r3;
    }

    public void setWidth(double r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_20, this, this, Conversions.doubleObject(r3));
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.width = r3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_11, this, this));
        return "TrackHeaderBox[creationTime=" + getCreationTime() + ";modificationTime=" + getModificationTime() + ";trackId=" + getTrackId() + ";duration=" + getDuration() + ";layer=" + getLayer() + ";alternateGroup=" + getAlternateGroup() + ";volume=" + getVolume() + ";matrix=" + this.matrix + ";width=" + getWidth() + ";height=" + getHeight() + "]";
    }
}
