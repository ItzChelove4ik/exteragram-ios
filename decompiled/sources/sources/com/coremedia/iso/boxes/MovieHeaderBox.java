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

/* loaded from: classes4.dex */
public class MovieHeaderBox extends AbstractFullBox {
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
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_8 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_9 = null;
    private Date creationTime;
    private int currentTime;
    private long duration;
    private Matrix matrix;
    private Date modificationTime;
    private long nextTrackId;
    private int posterTime;
    private int previewDuration;
    private int previewTime;
    private double rate;
    private int selectionDuration;
    private int selectionTime;
    private long timescale;
    private float volume;

    static {
        ajc$preClinit();
    }

    public MovieHeaderBox() {
        super("mvhd");
        this.rate = 1.0d;
        this.volume = 1.0f;
        this.matrix = Matrix.ROTATE_0;
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory r0 = new Factory("MovieHeaderBox.java", MovieHeaderBox.class);
        ajc$tjp_0 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getCreationTime", "com.coremedia.iso.boxes.MovieHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.util.Date"), 63);
        ajc$tjp_1 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getModificationTime", "com.coremedia.iso.boxes.MovieHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.util.Date"), 67);
        ajc$tjp_10 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setModificationTime", "com.coremedia.iso.boxes.MovieHeaderBox", "java.util.Date", "modificationTime", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 203);
        ajc$tjp_11 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setTimescale", "com.coremedia.iso.boxes.MovieHeaderBox", "long", "timescale", _UrlKt.FRAGMENT_ENCODE_SET, "void"), Opcodes.DIV_INT_LIT16);
        ajc$tjp_12 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setDuration", "com.coremedia.iso.boxes.MovieHeaderBox", "long", "duration", _UrlKt.FRAGMENT_ENCODE_SET, "void"), Opcodes.XOR_INT_LIT16);
        ajc$tjp_13 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setRate", "com.coremedia.iso.boxes.MovieHeaderBox", "double", "rate", _UrlKt.FRAGMENT_ENCODE_SET, "void"), Opcodes.OR_INT_LIT8);
        ajc$tjp_14 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setVolume", "com.coremedia.iso.boxes.MovieHeaderBox", "float", MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, _UrlKt.FRAGMENT_ENCODE_SET, "void"), Opcodes.USHR_INT_LIT8);
        ajc$tjp_15 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setMatrix", "com.coremedia.iso.boxes.MovieHeaderBox", "com.googlecode.mp4parser.util.Matrix", "matrix", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 230);
        ajc$tjp_16 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setNextTrackId", "com.coremedia.iso.boxes.MovieHeaderBox", "long", "nextTrackId", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 234);
        ajc$tjp_17 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getPreviewTime", "com.coremedia.iso.boxes.MovieHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "int"), 238);
        ajc$tjp_18 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setPreviewTime", "com.coremedia.iso.boxes.MovieHeaderBox", "int", "previewTime", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 242);
        ajc$tjp_19 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getPreviewDuration", "com.coremedia.iso.boxes.MovieHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "int"), 246);
        ajc$tjp_2 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getTimescale", "com.coremedia.iso.boxes.MovieHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "long"), 71);
        ajc$tjp_20 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setPreviewDuration", "com.coremedia.iso.boxes.MovieHeaderBox", "int", "previewDuration", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 250);
        ajc$tjp_21 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getPosterTime", "com.coremedia.iso.boxes.MovieHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "int"), Opcodes.CONST_METHOD_HANDLE);
        ajc$tjp_22 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setPosterTime", "com.coremedia.iso.boxes.MovieHeaderBox", "int", "posterTime", _UrlKt.FRAGMENT_ENCODE_SET, "void"), GlobalMediaRouter.CallbackHandler.MSG_ROUTE_REMOVED);
        ajc$tjp_23 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getSelectionTime", "com.coremedia.iso.boxes.MovieHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "int"), GlobalMediaRouter.CallbackHandler.MSG_ROUTE_SELECTED);
        ajc$tjp_24 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setSelectionTime", "com.coremedia.iso.boxes.MovieHeaderBox", "int", "selectionTime", _UrlKt.FRAGMENT_ENCODE_SET, "void"), GlobalMediaRouter.CallbackHandler.MSG_ROUTE_DISCONNECTED);
        ajc$tjp_25 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getSelectionDuration", "com.coremedia.iso.boxes.MovieHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "int"), 270);
        ajc$tjp_26 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setSelectionDuration", "com.coremedia.iso.boxes.MovieHeaderBox", "int", "selectionDuration", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 274);
        ajc$tjp_27 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getCurrentTime", "com.coremedia.iso.boxes.MovieHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "int"), 278);
        ajc$tjp_28 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setCurrentTime", "com.coremedia.iso.boxes.MovieHeaderBox", "int", "currentTime", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 282);
        ajc$tjp_3 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getDuration", "com.coremedia.iso.boxes.MovieHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "long"), 75);
        ajc$tjp_4 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getRate", "com.coremedia.iso.boxes.MovieHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "double"), 79);
        ajc$tjp_5 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getVolume", "com.coremedia.iso.boxes.MovieHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "float"), 83);
        ajc$tjp_6 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getMatrix", "com.coremedia.iso.boxes.MovieHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "com.googlecode.mp4parser.util.Matrix"), 87);
        ajc$tjp_7 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getNextTrackId", "com.coremedia.iso.boxes.MovieHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "long"), 91);
        ajc$tjp_8 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "toString", "com.coremedia.iso.boxes.MovieHeaderBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.lang.String"), 139);
        ajc$tjp_9 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setCreationTime", "com.coremedia.iso.boxes.MovieHeaderBox", "java.util.Date", "creationTime", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 195);
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
        this.rate = IsoTypeReader.readFixedPoint1616(r3);
        this.volume = IsoTypeReader.readFixedPoint88(r3);
        IsoTypeReader.readUInt16(r3);
        IsoTypeReader.readUInt32(r3);
        IsoTypeReader.readUInt32(r3);
        this.matrix = Matrix.fromByteBuffer(r3);
        this.previewTime = r3.getInt();
        this.previewDuration = r3.getInt();
        this.posterTime = r3.getInt();
        this.selectionTime = r3.getInt();
        this.selectionDuration = r3.getInt();
        this.currentTime = r3.getInt();
        this.nextTrackId = IsoTypeReader.readUInt32(r3);
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
        IsoTypeWriter.writeFixedPoint1616(r4, this.rate);
        IsoTypeWriter.writeFixedPoint88(r4, this.volume);
        IsoTypeWriter.writeUInt16(r4, 0);
        IsoTypeWriter.writeUInt32(r4, 0);
        IsoTypeWriter.writeUInt32(r4, 0);
        this.matrix.getContent(r4);
        r4.putInt(this.previewTime);
        r4.putInt(this.previewDuration);
        r4.putInt(this.posterTime);
        r4.putInt(this.selectionTime);
        r4.putInt(this.selectionDuration);
        r4.putInt(this.currentTime);
        IsoTypeWriter.writeUInt32(r4, this.nextTrackId);
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
        return r0 + 80;
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

    public Date getModificationTime() {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_1, this, this);
        RequiresParseDetailAspect.aspectOf().before(r0);
        return this.modificationTime;
    }

    public long getNextTrackId() {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_7, this, this);
        RequiresParseDetailAspect.aspectOf().before(r0);
        return this.nextTrackId;
    }

    public double getRate() {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_4, this, this);
        RequiresParseDetailAspect.aspectOf().before(r0);
        return this.rate;
    }

    public long getTimescale() {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_2, this, this);
        RequiresParseDetailAspect.aspectOf().before(r0);
        return this.timescale;
    }

    public float getVolume() {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_5, this, this);
        RequiresParseDetailAspect.aspectOf().before(r0);
        return this.volume;
    }

    public void setCreationTime(Date r5) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_9, this, this, r5);
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.creationTime = r5;
        if (DateHelper.convert(r5) < 4294967296L) goto L6;
        setVersion(1);
        return;
    }

    public void setDuration(long r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_12, this, this, Conversions.longObject(r3));
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.duration = r3;
        if (r3 < 4294967296L) goto L6;
        setVersion(1);
        return;
    }

    public void setMatrix(Matrix r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_15, this, this, r3);
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.matrix = r3;
    }

    public void setModificationTime(Date r5) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_10, this, this, r5);
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.modificationTime = r5;
        if (DateHelper.convert(r5) < 4294967296L) goto L6;
        setVersion(1);
        return;
    }

    public void setNextTrackId(long r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_16, this, this, Conversions.longObject(r3));
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.nextTrackId = r3;
    }

    public void setTimescale(long r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_11, this, this, Conversions.longObject(r3));
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.timescale = r3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_8, this, this));
        return "MovieHeaderBox[creationTime=" + getCreationTime() + ";modificationTime=" + getModificationTime() + ";timescale=" + getTimescale() + ";duration=" + getDuration() + ";rate=" + getRate() + ";volume=" + getVolume() + ";matrix=" + this.matrix + ";nextTrackId=" + getNextTrackId() + "]";
    }
}
