package com.android.dx.util;

import com.android.dex.util.ByteOutput;

/* loaded from: classes.dex */
public interface Output extends ByteOutput {
    void alignTo(int r1);

    void assertCursor(int r1);

    int getCursor();

    void write(ByteArray r1);

    void write(byte[] r1);

    void write(byte[] r1, int r2, int r3);

    @Override // com.android.dex.util.ByteOutput
    void writeByte(int r1);

    void writeInt(int r1);

    void writeLong(long r1);

    void writeShort(int r1);

    int writeSleb128(int r1);

    int writeUleb128(int r1);

    void writeZeroes(int r1);
}
