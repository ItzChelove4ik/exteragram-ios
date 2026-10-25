package com.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public class BitReaderBuffer {
    private ByteBuffer buffer;
    int initialPos;
    int position;

    public BitReaderBuffer(ByteBuffer r1) {
        this.buffer = r1;
        this.initialPos = r1.position();
    }

    public int readBits(int r7) {
        int r0 = this.buffer.get(this.initialPos + (this.position / 8));
        if (r0 >= 0) goto L5;
        r0 = r0 + 256;
    L5:
        int r1 = this.position;
        int r2 = 8 - (r1 % 8);
        if (r7 > r2) goto L8;
        int r02 = ((r0 << (r1 % 8)) & 255) >> ((r1 % 8) + (r2 - r7));
        this.position = r1 + r7;
    L9:
        this.buffer.position(this.initialPos + ((int) Math.ceil(this.position / 8.0d)));
        return r02;
    L8:
        int r72 = r7 - r2;
        r02 = (readBits(r2) << r72) + readBits(r72);
        goto L9
    }

    public boolean readBool() {
        if (readBits(1) != 1) goto L5;
        return true;
    L5:
        return false;
    }

    public int remainingBits() {
        return (this.buffer.limit() * 8) - this.position;
    }
}
