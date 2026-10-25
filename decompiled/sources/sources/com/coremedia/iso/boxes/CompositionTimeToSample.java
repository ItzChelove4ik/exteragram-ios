package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.googlecode.mp4parser.util.CastUtils;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.reflect.Factory;

/* loaded from: classes4.dex */
public class CompositionTimeToSample extends AbstractFullBox {
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_1 = null;
    List<Entry> entries;

    public static class Entry {
        int count;
        int offset;

        public Entry(int r1, int r2) {
            this.count = r1;
            this.offset = r2;
        }

        public int getCount() {
            return this.count;
        }

        public int getOffset() {
            return this.offset;
        }

        public void setCount(int r1) {
            this.count = r1;
        }

        public String toString() {
            return "Entry{count=" + this.count + ", offset=" + this.offset + '}';
        }
    }

    static {
        ajc$preClinit();
    }

    public CompositionTimeToSample() {
        super("ctts");
        this.entries = Collections.EMPTY_LIST;
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory r0 = new Factory("CompositionTimeToSample.java", CompositionTimeToSample.class);
        ajc$tjp_0 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getEntries", "com.coremedia.iso.boxes.CompositionTimeToSample", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.util.List"), 57);
        ajc$tjp_1 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setEntries", "com.coremedia.iso.boxes.CompositionTimeToSample", "java.util.List", "entries", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 61);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer r6) {
        parseVersionAndFlags(r6);
        int r0 = CastUtils.l2i(IsoTypeReader.readUInt32(r6));
        this.entries = new ArrayList(r0);
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L4;
        Entry r2 = new Entry(CastUtils.l2i(IsoTypeReader.readUInt32(r6)), r6.getInt());
        this.entries.add(r2);
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
        r4.putInt(r0.getOffset());
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
}
