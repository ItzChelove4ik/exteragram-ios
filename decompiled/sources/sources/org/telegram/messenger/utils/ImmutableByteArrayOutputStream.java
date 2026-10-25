package org.telegram.messenger.utils;

import java.io.OutputStream;
import java.util.Arrays;

/* loaded from: classes.dex */
public class ImmutableByteArrayOutputStream extends OutputStream {
    public byte[] buf;
    protected int count;

    public ImmutableByteArrayOutputStream() {
        this(32);
    }

    public ImmutableByteArrayOutputStream(int r1) {
        this.buf = new byte[r1];
    }

    private void ensureCapacity(int r2) {
        if ((r2 - this.buf.length) <= 0) goto L6;
        grow(r2);
        return;
    }

    private void grow(int r3) {
        int r0 = this.buf.length << 1;
        if ((r0 - r3) >= 0) goto L6;
        r0 = r3;
    L6:
        if ((r0 - 2147483639) <= 0) goto L8;
        r0 = hugeCapacity(r3);
    L8:
        this.buf = Arrays.copyOf(this.buf, r0);
    }

    private static int hugeCapacity(int r1) {
        if (r1 < 0) goto L9;
        if (r1 <= 2147483639) goto L7;
        return Integer.MAX_VALUE;
    L7:
        return 2147483639;
    L9:
        throw new OutOfMemoryError();
    }

    public int count() {
        return this.count;
    }

    public synchronized void reset() {
        monitor-enter(this);
        this.count = 0;     // Catch: Throwable -> L7
        monitor-exit(this);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // java.io.OutputStream
    public synchronized void write(int r3) {
        monitor-enter(this);
        ensureCapacity(this.count + 1);     // Catch: Throwable -> L6
        byte[] r0 = this.buf;     // Catch: Throwable -> L6
        int r1 = this.count;     // Catch: Throwable -> L6
        r0[r1] = (byte) r3;     // Catch: Throwable -> L6
        this.count = r1 + 1;     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // java.io.OutputStream
    public synchronized void write(byte[] r3, int r4, int r5) {
        monitor-enter(this);
        if (r4 < 0) goto L15;
    L12:
        th = move-exception;
        throw th;
    L5:
        if (r4 > r3.length) goto L15;
        if (r5 < 0) goto L15;
        if (((r4 + r5) - r3.length) > 0) goto L15;
        ensureCapacity(this.count + r5);     // Catch: Throwable -> L12
        System.arraycopy(r3, r4, this.buf, this.count, r5);     // Catch: Throwable -> L12
        this.count += r5;
        monitor-exit(this);
        return;
    L15:
        throw new IndexOutOfBoundsException();     // Catch: Throwable -> L12
    }

    public void writeInt(int r5) {
        ensureCapacity(this.count + 4);
        byte[] r0 = this.buf;
        int r1 = this.count;
        r0[r1] = (byte) (r5 >>> 24);
        r0[r1 + 1] = (byte) (r5 >>> 16);
        r0[r1 + 2] = (byte) (r5 >>> 8);
        r0[r1 + 3] = (byte) r5;
        this.count = r1 + 4;
    }

    public void writeLong(long r7) {
        ensureCapacity(this.count + 8);
        byte[] r0 = this.buf;
        int r2 = this.count;
        r0[r2] = (byte) (r7 >>> 56);
        r0[r2 + 1] = (byte) (r7 >>> 48);
        r0[r2 + 2] = (byte) (r7 >>> 40);
        r0[r2 + 3] = (byte) (r7 >>> 32);
        r0[r2 + 4] = (byte) (r7 >>> 24);
        r0[r2 + 5] = (byte) (r7 >>> 16);
        r0[r2 + 6] = (byte) (r7 >>> 8);
        r0[r2 + 7] = (byte) r7;
        this.count = r2 + 8;
    }
}
