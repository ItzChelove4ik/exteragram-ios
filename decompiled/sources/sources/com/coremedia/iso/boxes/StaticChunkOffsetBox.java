package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.googlecode.mp4parser.util.CastUtils;
import java.nio.ByteBuffer;
import okhttp3.internal.url._UrlKt;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.reflect.Factory;

/* loaded from: classes4.dex */
public class StaticChunkOffsetBox extends ChunkOffsetBox {
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_1 = null;
    private long[] chunkOffsets;

    static {
        ajc$preClinit();
    }

    public StaticChunkOffsetBox() {
        super("stco");
        this.chunkOffsets = new long[0];
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory r0 = new Factory("StaticChunkOffsetBox.java", StaticChunkOffsetBox.class);
        ajc$tjp_0 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "getChunkOffsets", "com.coremedia.iso.boxes.StaticChunkOffsetBox", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "[J"), 39);
        ajc$tjp_1 = r0.makeSJP("method-execution", r0.makeMethodSig("1", "setChunkOffsets", "com.coremedia.iso.boxes.StaticChunkOffsetBox", "[J", "chunkOffsets", _UrlKt.FRAGMENT_ENCODE_SET, "void"), 48);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer r6) {
        parseVersionAndFlags(r6);
        int r0 = CastUtils.l2i(IsoTypeReader.readUInt32(r6));
        this.chunkOffsets = new long[r0];
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L4;
        this.chunkOffsets[r1] = IsoTypeReader.readUInt32(r6);
        r1 = r1 + 1;
        goto L3
    }

    @Override // com.coremedia.iso.boxes.ChunkOffsetBox
    public long[] getChunkOffsets() {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_0, this, this);
        RequiresParseDetailAspect.aspectOf().before(r0);
        return this.chunkOffsets;
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer r5) {
        writeVersionAndFlags(r5);
        IsoTypeWriter.writeUInt32(r5, this.chunkOffsets.length);
        long[] r4 = this.chunkOffsets;
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L4;
        IsoTypeWriter.writeUInt32(r5, r4[r1]);
        r1 = r1 + 1;
        goto L3
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return (this.chunkOffsets.length * 4) + 8;
    }

    public void setChunkOffsets(long[] r3) {
        JoinPoint r0 = Factory.makeJP(ajc$tjp_1, this, this, r3);
        RequiresParseDetailAspect.aspectOf().before(r0);
        this.chunkOffsets = r3;
    }
}
