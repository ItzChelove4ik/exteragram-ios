package com.android.dx.io.instructions;

import c.f$$ExternalSyntheticBUOutline1;
import kotlin.UByte;

/* loaded from: classes4.dex */
public final class ShortArrayCodeOutput extends BaseCodeCursor implements CodeOutput {
    private final short[] array;

    public ShortArrayCodeOutput(int r1) {
        if (r1 < 0) goto L6;
        this.array = new short[r1];
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("maxSize < 0");
        throw null;
    }

    public short[] getArray() {
        int r0 = cursor();
        short[] r3 = this.array;
        if (r0 != r3.length) goto L5;
        return r3;
    L5:
        short[] r1 = new short[r0];
        System.arraycopy(r3, 0, r1, 0, r0);
        return r1;
    }

    @Override // com.android.dx.io.instructions.CodeOutput
    public void write(short r3) {
        this.array[cursor()] = r3;
        advance(1);
    }

    @Override // com.android.dx.io.instructions.CodeOutput
    public void write(short r1, short r2) {
        write(r1);
        write(r2);
    }

    @Override // com.android.dx.io.instructions.CodeOutput
    public void write(short r1, short r2, short r3) {
        write(r1);
        write(r2);
        write(r3);
    }

    @Override // com.android.dx.io.instructions.CodeOutput
    public void write(short r1, short r2, short r3, short r4) {
        write(r1);
        write(r2);
        write(r3);
        write(r4);
    }

    @Override // com.android.dx.io.instructions.CodeOutput
    public void write(short r1, short r2, short r3, short r4, short r5) {
        write(r1);
        write(r2);
        write(r3);
        write(r4);
        write(r5);
    }

    @Override // com.android.dx.io.instructions.CodeOutput
    public void write(byte[] r8) {
        int r0 = r8.length;
        int r3 = 0;
        int r5 = 0;
        boolean r4 = true;
    L3:
        if (r3 >= r0) goto L9;
        byte r6 = r8[r3];
        if (r4 == false) goto L7;
        r5 = r6 & UByte.MAX_VALUE;
        r4 = false;
    L8:
        r3 = r3 + 1;
        goto L3
    L7:
        int r42 = (r6 << 8) | r5;
        write((short) r42);
        r5 = r42;
        r4 = true;
        goto L8
    L9:
        if (r4 == true) goto L15;
        write((short) r5);
        return;
    }

    @Override // com.android.dx.io.instructions.CodeOutput
    public void write(int[] r4) {
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        writeInt(r4[r1]);
        r1 = r1 + 1;
        goto L3
    }

    @Override // com.android.dx.io.instructions.CodeOutput
    public void write(long[] r5) {
        int r0 = r5.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        writeLong(r5[r1]);
        r1 = r1 + 1;
        goto L3
    }

    @Override // com.android.dx.io.instructions.CodeOutput
    public void write(short[] r4) {
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        write(r4[r1]);
        r1 = r1 + 1;
        goto L3
    }

    @Override // com.android.dx.io.instructions.CodeOutput
    public void writeInt(int r2) {
        write((short) r2);
        write((short) (r2 >> 16));
    }

    @Override // com.android.dx.io.instructions.CodeOutput
    public void writeLong(long r3) {
        write((short) r3);
        write((short) (r3 >> 16));
        write((short) (r3 >> 32));
        write((short) (r3 >> 48));
    }
}
