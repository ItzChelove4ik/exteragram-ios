package com.googlecode.mp4parser.authoring.tracks;

import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes5.dex */
public class CleanInputStream extends FilterInputStream implements InputStreamRetargetInterface {
    int prev;
    int prevprev;

    public CleanInputStream(InputStream r1) {
        super(r1);
        this.prevprev = -1;
        this.prev = -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        int r0 = super.read();
        if (r0 == 3) goto L5;
    L9:
        this.prevprev = this.prev;
        this.prev = r0;
        return r0;
    L5:
        if (this.prevprev != 0) goto L9;
        if (this.prev != 0) goto L9;
        this.prevprev = -1;
        this.prev = -1;
        r0 = super.read();
        goto L9
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] r5, int r6, int r7) {
        r5.getClass();
        if (r6 < 0) goto L23;
        if (r7 < 0) goto L23;
        if (r7 > (r5.length - r6)) goto L23;
        if (r7 != 0) goto L10;
        return 0;
    L10:
        int r0 = read();
        if (r0 != (-1)) goto L13;
        return -1;
    L13:
        r5[r6] = (byte) r0;
        int r02 = 1;
    L14:
        if (r02 >= r7) goto L21;
        int r2 = read();     // Catch: IOException -> L24
        if (r2 == (-1)) goto L21;
        r5[r6 + r02] = (byte) r2;     // Catch: IOException -> L24
        r02 = r02 + 1;
    L21:
        return r02;
    L23:
        throw new IndexOutOfBoundsException();
    }

    @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
    public /* synthetic */ long transferTo(OutputStream r1) {
        return DesugarInputStream.transferTo(this, r1);
    }
}
