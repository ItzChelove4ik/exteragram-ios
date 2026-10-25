package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractContainerBox;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

/* loaded from: classes4.dex */
public class DataReferenceBox extends AbstractContainerBox implements Box {
    private int flags;
    private int version;

    public DataReferenceBox() {
        super("dref");
    }

    @Override // com.googlecode.mp4parser.AbstractContainerBox, com.coremedia.iso.boxes.Box
    public void getBox(WritableByteChannel r4) {
        r4.write(getHeader());
        ByteBuffer r0 = ByteBuffer.allocate(8);
        IsoTypeWriter.writeUInt8(r0, this.version);
        IsoTypeWriter.writeUInt24(r0, this.flags);
        IsoTypeWriter.writeUInt32(r0, getBoxes().size());
        r4.write((ByteBuffer) r0.rewind());
        writeContainer(r4);
    }

    @Override // com.googlecode.mp4parser.AbstractContainerBox, com.coremedia.iso.boxes.Box
    public long getSize() {
        long r0 = getContainerSize();
        long r2 = 8 + r0;
        if (this.largeBox == false) goto L5;
    L8:
        int r6 = 16;
    L10:
        return r2 + r6;
    L5:
        if ((r0 + 16) >= 4294967296L) goto L8;
        r6 = 8;
        goto L10
    }
}
