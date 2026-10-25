package com.coremedia.iso.boxes.sampleentry;

import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.Utf8;
import com.coremedia.iso.boxes.Container;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class VisualSampleEntry extends AbstractSampleEntry implements Container {
    private String compressorname;
    private int depth;
    private int frameCount;
    private int height;
    private double horizresolution;
    private long[] predefined;
    private double vertresolution;
    private int width;

    static {
    }

    public VisualSampleEntry(String r3) {
        super(r3);
        this.horizresolution = 72.0d;
        this.vertresolution = 72.0d;
        this.frameCount = 1;
        this.compressorname = _UrlKt.FRAGMENT_ENCODE_SET;
        this.depth = 24;
        this.predefined = new long[3];
    }

    @Override // com.googlecode.mp4parser.AbstractContainerBox, com.coremedia.iso.boxes.Box
    public void getBox(WritableByteChannel r6) {
        r6.write(getHeader());
        ByteBuffer r0 = ByteBuffer.allocate(78);
        r0.position(6);
        IsoTypeWriter.writeUInt16(r0, this.dataReferenceIndex);
        IsoTypeWriter.writeUInt16(r0, 0);
        IsoTypeWriter.writeUInt16(r0, 0);
        IsoTypeWriter.writeUInt32(r0, this.predefined[0]);
        IsoTypeWriter.writeUInt32(r0, this.predefined[1]);
        IsoTypeWriter.writeUInt32(r0, this.predefined[2]);
        IsoTypeWriter.writeUInt16(r0, getWidth());
        IsoTypeWriter.writeUInt16(r0, getHeight());
        IsoTypeWriter.writeFixedPoint1616(r0, getHorizresolution());
        IsoTypeWriter.writeFixedPoint1616(r0, getVertresolution());
        IsoTypeWriter.writeUInt32(r0, 0);
        IsoTypeWriter.writeUInt16(r0, getFrameCount());
        IsoTypeWriter.writeUInt8(r0, Utf8.utf8StringLengthInBytes(getCompressorname()));
        r0.put(Utf8.convert(getCompressorname()));
        int r2 = Utf8.utf8StringLengthInBytes(getCompressorname());
    L4:
        if (r2 >= 31) goto L5;
        r2 = r2 + 1;
        r0.put((byte) 0);
        goto L4
    L5:
        IsoTypeWriter.writeUInt16(r0, getDepth());
        IsoTypeWriter.writeUInt16(r0, 65535);
        r6.write((ByteBuffer) r0.rewind());
        writeContainer(r6);
    }

    public String getCompressorname() {
        return this.compressorname;
    }

    public int getDepth() {
        return this.depth;
    }

    public int getFrameCount() {
        return this.frameCount;
    }

    public int getHeight() {
        return this.height;
    }

    public double getHorizresolution() {
        return this.horizresolution;
    }

    @Override // com.googlecode.mp4parser.AbstractContainerBox, com.coremedia.iso.boxes.Box
    public long getSize() {
        long r0 = getContainerSize();
        long r2 = 78 + r0;
        if (this.largeBox == false) goto L5;
    L8:
        int r6 = 16;
    L10:
        return r2 + r6;
    L5:
        if ((r0 + 86) >= 4294967296L) goto L8;
        r6 = 8;
        goto L10
    }

    public double getVertresolution() {
        return this.vertresolution;
    }

    public int getWidth() {
        return this.width;
    }

    public void setCompressorname(String r1) {
        this.compressorname = r1;
    }

    public void setDepth(int r1) {
        this.depth = r1;
    }

    public void setFrameCount(int r1) {
        this.frameCount = r1;
    }

    public void setHeight(int r1) {
        this.height = r1;
    }

    public void setHorizresolution(double r1) {
        this.horizresolution = r1;
    }

    public void setVertresolution(double r1) {
        this.vertresolution = r1;
    }

    public void setWidth(int r1) {
        this.width = r1;
    }
}
