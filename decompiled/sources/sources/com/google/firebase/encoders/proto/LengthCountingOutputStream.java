package com.google.firebase.encoders.proto;

import java.io.OutputStream;

/* loaded from: classes5.dex */
final class LengthCountingOutputStream extends OutputStream {
    private long length;

    public LengthCountingOutputStream() {
        this.length = 0;
    }

    public long getLength() {
        return this.length;
    }

    @Override // java.io.OutputStream
    public void write(int r5) {
        this.length++;
    }

    @Override // java.io.OutputStream
    public void write(byte[] r5) {
        this.length += r5.length;
    }

    @Override // java.io.OutputStream
    public void write(byte[] r3, int r4, int r5) {
        if (r4 < 0) goto L12;
        if (r4 > r3.length) goto L12;
        if (r5 < 0) goto L12;
        int r42 = r4 + r5;
        if (r42 > r3.length) goto L12;
        if (r42 < 0) goto L12;
        this.length += r5;
        return;
    L12:
        throw new IndexOutOfBoundsException();
    }
}
