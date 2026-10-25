package com.android.dx.io.instructions;

/* loaded from: classes4.dex */
public interface CodeOutput extends CodeCursor {
    void write(short r1);

    void write(short r1, short r2);

    void write(short r1, short r2, short r3);

    void write(short r1, short r2, short r3, short r4);

    void write(short r1, short r2, short r3, short r4, short r5);

    void write(byte[] r1);

    void write(int[] r1);

    void write(long[] r1);

    void write(short[] r1);

    void writeInt(int r1);

    void writeLong(long r1);
}
