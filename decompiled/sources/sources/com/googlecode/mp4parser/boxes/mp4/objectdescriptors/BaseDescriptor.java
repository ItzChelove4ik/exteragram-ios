package com.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import com.coremedia.iso.IsoTypeReader;
import java.nio.ByteBuffer;

@Descriptor(tags = {0})
/* loaded from: classes5.dex */
public abstract class BaseDescriptor {
    int sizeBytes;
    int sizeOfInstance;
    int tag;

    static {
    }

    public BaseDescriptor() {
    }

    public int getSize() {
        return (this.sizeOfInstance + 1) + this.sizeBytes;
    }

    public int getSizeBytes() {
        return this.sizeBytes;
    }

    public int getSizeOfInstance() {
        return this.sizeOfInstance;
    }

    public final void parse(int r5, ByteBuffer r6) {
        this.tag = r5;
        int r52 = IsoTypeReader.readUInt8(r6);
        this.sizeOfInstance = r52 & 127;
        int r1 = 1;
    L4:
        if ((r52 >>> 7) != 1) goto L5;
        r52 = IsoTypeReader.readUInt8(r6);
        r1 = r1 + 1;
        this.sizeOfInstance = (this.sizeOfInstance << 7) | (r52 & 127);
        goto L4
    L5:
        this.sizeBytes = r1;
        ByteBuffer r53 = r6.slice();
        r53.limit(this.sizeOfInstance);
        parseDetail(r53);
        r6.position(r6.position() + this.sizeOfInstance);
    }

    public abstract void parseDetail(ByteBuffer r1);
}
