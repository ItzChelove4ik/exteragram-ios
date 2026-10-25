package com.googlecode.mp4parser.h264.read;

import c.f$$ExternalSyntheticBUOutline1;
import com.googlecode.mp4parser.h264.CharCache;
import java.io.InputStream;

/* loaded from: classes5.dex */
public abstract class BitstreamReader {
    protected static int bitsRead;
    private int curByte;
    protected CharCache debugBits;
    private InputStream is;
    int nBit;
    private int nextByte;

    public BitstreamReader(InputStream r3) {
        this.debugBits = new CharCache(50);
        this.is = r3;
        this.curByte = r3.read();
        this.nextByte = r3.read();
    }

    private void advance() {
        this.curByte = this.nextByte;
        this.nextByte = this.is.read();
        this.nBit = 0;
    }

    public int read1Bit() {
        if (this.nBit != 8) goto L7;
        advance();
        if (this.curByte != (-1)) goto L7;
        return -1;
    L7:
        int r0 = this.curByte;
        int r1 = this.nBit;
        int r02 = (r0 >> (7 - r1)) & 1;
        this.nBit = r1 + 1;
        CharCache r3 = this.debugBits;
        if (r02 != 0) goto L10;
        char r12 = '0';
    L11:
        r3.append(r12);
        bitsRead++;
        return r02;
    L10:
        r12 = '1';
        goto L11
    }

    public boolean readBool() {
        if (read1Bit() != 1) goto L5;
        return true;
    L5:
        return false;
    }

    public int readByte() {
        if (this.nBit <= 0) goto L5;
        advance();
    L5:
        int r0 = this.curByte;
        advance();
        return r0;
    }

    public long readNBit(int r6) {
        if (r6 > 64) goto L8;
        long r0 = 0;
        int r2 = 0;
    L5:
        if (r2 >= r6) goto L6;
        r0 = (r0 << 1) | read1Bit();
        r2 = r2 + 1;
        goto L5
    L6:
        return r0;
    L8:
        f$$ExternalSyntheticBUOutline1.m("Can not readByte more then 64 bit");
        return 0;
    }
}
