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
public class SampleToChunkBox extends AbstractFullBox {
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_3 = null;
    List<Entry> entries;

    public static class Entry {
        long firstChunk;
        long sampleDescriptionIndex;
        long samplesPerChunk;

        public Entry(long r1, long r3, long r5) {
            this.firstChunk = r1;
            this.samplesPerChunk = r3;
            this.sampleDescriptionIndex = r5;
        }

        public boolean equals(Object r7) {
            if (this != r7) goto L6;
            return true;
        L6:
            if (r7 != null) goto L8;
        L20:
            return false;
        L8:
            if (getClass() != r7.getClass()) goto L20;
            Entry r72 = (Entry) r7;
            if (this.firstChunk == r72.firstChunk) goto L14;
            return false;
        L14:
            if (this.sampleDescriptionIndex == r72.sampleDescriptionIndex) goto L17;
            return false;
        L17:
            if (this.samplesPerChunk == r72.samplesPerChunk) goto L19;
            return false;
        L19:
            return true;
        }

        public long getFirstChunk() {
            return this.firstChunk;
        }

        public long getSampleDescriptionIndex() {
            return this.sampleDescriptionIndex;
        }

        public long getSamplesPerChunk() {
            return this.samplesPerChunk;
        }

        public int hashCode() {
            long r0 = this.firstChunk;
            int r02 = ((int) (r0 ^ (r0 >>> 32))) * 31;
            long r3 = this.samplesPerChunk;
            int r03 = (r02 + ((int) (r3 ^ (r3 >>> 32)))) * 31;
            long r32 = this.sampleDescriptionIndex;
            return r03 + ((int) ((r32 >>> 32) ^ r32));
        }

        public String toString() {
            return "Entry{firstChunk=" + this.firstChunk + ", samplesPerChunk=" + this.samplesPerChunk + ", sampleDescriptionIndex=" + this.sampleDescriptionIndex + '}';
        }
    }

    static {
        ajc$preClinit();
    }

    public SampleToChunkBox() {
        super("stsc");
        this.entries = Collections.EMPTY_LIST;
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory r0 = new Factory("SampleToChunkBox.java", SampleToChunkBox.class);
        ajc$tjp_0 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getEntries", "com.coremedia.iso.boxes.SampleToChunkBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.util.List"), 47);
        ajc$tjp_1 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setEntries", "com.coremedia.iso.boxes.SampleToChunkBox", "java.util.List", "entries", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 51);
        ajc$tjp_2 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "toString", "com.coremedia.iso.boxes.SampleToChunkBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "java.lang.String"), 84);
        ajc$tjp_3 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "blowup", "com.coremedia.iso.boxes.SampleToChunkBox", "int", "chunkCount", _UrlKt.FRAGMENT_ENCODE_SET, "[J"), 95);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer r11) {
        parseVersionAndFlags(r11);
        int r0 = CastUtils.l2i(IsoTypeReader.readUInt32(r11));
        this.entries = new ArrayList(r0);
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L4;
        this.entries.add(new Entry(IsoTypeReader.readUInt32(r11), IsoTypeReader.readUInt32(r11), IsoTypeReader.readUInt32(r11)));
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
        IsoTypeWriter.writeUInt32(r4, r0.getFirstChunk());
        IsoTypeWriter.writeUInt32(r4, r0.getSamplesPerChunk());
        IsoTypeWriter.writeUInt32(r4, r0.getSampleDescriptionIndex());
        goto L4
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return (this.entries.size() * 12) + 8;
    }

    public List<Entry> getEntries() {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_0, this, this);
        RequiresParseDetailAspect.aspectOf().before(r0);
        return this.entries;
    }

    public void setEntries(List<Entry> r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_1, this, this, r3);
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.entries = r3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return "SampleToChunkBox[entryCount=" + this.entries.size() + "]";
    }
}
