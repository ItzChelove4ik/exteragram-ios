package com.android.dx.io.instructions;

import c.f$$ExternalSyntheticBUOutline2;
import okio.Buffer$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class ShortArrayCodeInput extends BaseCodeCursor implements CodeInput {
    private final short[] array;

    public ShortArrayCodeInput(short[] r1) {
        if (r1 == null) goto L6;
        this.array = r1;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("array == null");
        throw null;
    }

    @Override // com.android.dx.io.instructions.CodeInput
    public boolean hasMore() {
        if (cursor() >= this.array.length) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.android.dx.io.instructions.CodeInput
    public int read() {
        short r0 = this.array[cursor()];     // Catch: ArrayIndexOutOfBoundsException -> L5
        advance(1);     // Catch: ArrayIndexOutOfBoundsException -> L5
        return 65535 & r0;
    L5:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return 0;
    }

    @Override // com.android.dx.io.instructions.CodeInput
    public int readInt() {
        int r0 = read();
        return (read() << 16) | r0;
    }

    @Override // com.android.dx.io.instructions.CodeInput
    public long readLong() {
        return ((read() | (read() << 16)) | (read() << 32)) | (read() << 48);
    }
}
