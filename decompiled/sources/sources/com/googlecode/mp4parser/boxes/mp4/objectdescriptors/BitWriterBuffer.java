package com.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public class BitWriterBuffer {
    private ByteBuffer buffer;
    int initialPos;
    int position;

    static {
    }

    public BitWriterBuffer(ByteBuffer r2) {
        this.position = 0;
        this.buffer = r2;
        this.initialPos = r2.position();
    }

    public void writeBits(int r6, int r7) {
        int r0 = this.position;
        int r1 = 8 - (r0 % 8);
        int r2 = 1;
        if (r7 > r1) goto L11;
        int r02 = this.buffer.get(this.initialPos + (r0 / 8));
        if (r02 >= 0) goto L7;
        r02 = r02 + 256;
    L7:
        int r03 = r02 + (r6 << (r1 - r7));
        ByteBuffer r62 = this.buffer;
        int r12 = this.initialPos + (this.position / 8);
        if (r03 <= 127) goto L10;
        r03 = r03 - 256;
    L10:
        r62.put(r12, (byte) r03);
        this.position += r7;
    L12:
        ByteBuffer r63 = this.buffer;
        int r72 = this.initialPos;
        int r5 = this.position;
        int r73 = r72 + (r5 / 8);
        if ((r5 % 8) > 0) goto L16;
        r2 = 0;
    L16:
        r63.position(r73 + r2);
        return;
    L11:
        int r74 = r7 - r1;
        writeBits(r6 >> r74, r1);
        writeBits(r6 & ((1 << r74) - 1), r74);
        goto L12
    }
}
