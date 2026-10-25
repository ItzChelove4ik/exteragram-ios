package com.googlecode.mp4parser;

import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.Container;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

/* loaded from: classes5.dex */
public abstract class AbstractContainerBox extends BasicContainer implements Box {
    protected boolean largeBox;
    Container parent;
    protected String type;

    public AbstractContainerBox(String r1) {
        this.type = r1;
    }

    public void getBox(WritableByteChannel r2) {
        r2.write(getHeader());
        writeContainer(r2);
    }

    public ByteBuffer getHeader() {
        if (this.largeBox == false) goto L5;
    L8:
        byte[] r0 = new byte[16];
        r0[3] = 1;
        r0[4] = this.type.getBytes()[0];
        r0[5] = this.type.getBytes()[1];
        r0[6] = this.type.getBytes()[2];
        r0[7] = this.type.getBytes()[3];
        ByteBuffer r02 = ByteBuffer.wrap(r0);
        r02.position(8);
        IsoTypeWriter.writeUInt64(r02, getSize());
    L9:
        r02.rewind();
        return r02;
    L5:
        if (getSize() >= 4294967296L) goto L8;
        r02 = ByteBuffer.wrap(new byte[]{0, 0, 0, 0, this.type.getBytes()[0], this.type.getBytes()[1], this.type.getBytes()[2], this.type.getBytes()[3]});
        IsoTypeWriter.writeUInt32(r02, getSize());
        goto L9
    }

    @Override // com.coremedia.iso.boxes.Box
    public Container getParent() {
        return this.parent;
    }

    public long getSize() {
        long r0 = getContainerSize();
        if (this.largeBox == false) goto L5;
    L8:
        int r6 = 16;
    L10:
        return r0 + r6;
    L5:
        if ((8 + r0) >= 4294967296L) goto L8;
        r6 = 8;
        goto L10
    }

    @Override // com.coremedia.iso.boxes.Box
    public String getType() {
        return this.type;
    }

    @Override // com.coremedia.iso.boxes.Box
    public void setParent(Container r1) {
        this.parent = r1;
    }
}
