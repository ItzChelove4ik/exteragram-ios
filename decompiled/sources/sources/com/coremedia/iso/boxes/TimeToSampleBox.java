package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.googlecode.mp4parser.util.CastUtils;
import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import okhttp3.internal.url._UrlKt;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.reflect.Factory;

/* loaded from: classes4.dex */
public class TimeToSampleBox extends AbstractFullBox {
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_2 = null;
    static Map<List<Entry>, SoftReference<long[]>> cache;
    List<Entry> entries;

    public static class Entry {
        long count;
        long delta;

        public Entry(long r1, long r3) {
            this.count = r1;
            this.delta = r3;
        }

        public long getCount() {
            return this.count;
        }

        public long getDelta() {
            return this.delta;
        }

        public void setCount(long r1) {
            this.count = r1;
        }

        public String toString() {
            return "Entry{count=" + this.count + ", delta=" + this.delta + '}';
        }
    }

    static {
        ajc$preClinit();
        cache = new WeakHashMap();
    }

    public TimeToSampleBox() {
        super("stts");
        this.entries = Collections.EMPTY_LIST;
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory r0 = new Factory("TimeToSampleBox.java", TimeToSampleBox.class);
        ajc$tjp_0 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getEntries", "com.coremedia.iso.boxes.TimeToSampleBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.util.List"), 79);
        ajc$tjp_1 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setEntries", "com.coremedia.iso.boxes.TimeToSampleBox", "java.util.List", "entries", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 83);
        ajc$tjp_2 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "toString", "com.coremedia.iso.boxes.TimeToSampleBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.lang.String"), 87);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer r9) {
        parseVersionAndFlags(r9);
        int r0 = CastUtils.l2i(IsoTypeReader.readUInt32(r9));
        this.entries = new ArrayList(r0);
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L4;
        this.entries.add(new Entry(IsoTypeReader.readUInt32(r9), IsoTypeReader.readUInt32(r9)));
        r1 = r1 + 1;
        goto L3
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer r4) {
        writeVersionAndFlags(r4);
        IsoTypeWriter.writeUInt32(r4, this.entries.size());
        Iterator<Entry> r3 = this.entries.iterator();
    L4:
        if (r3.hasNext() == false) goto L5;
        Entry r0 = r3.next();
        IsoTypeWriter.writeUInt32(r4, r0.getCount());
        IsoTypeWriter.writeUInt32(r4, r0.getDelta());
        goto L4
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return (this.entries.size() * 8) + 8;
    }

    public void setEntries(List<Entry> r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_1, this, this, r3);
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.entries = r3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return "TimeToSampleBox[entryCount=" + this.entries.size() + "]";
    }
}
