package org.telegram.messenger.audioinfo.util;

import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes5.dex */
public class PositionInputStream extends FilterInputStream implements InputStreamRetargetInterface {
    private long position;
    private long positionMark;

    public PositionInputStream(InputStream r3) {
        this(r3, 0);
    }

    public PositionInputStream(InputStream r1, long r2) {
        super(r1);
        this.position = r2;
    }

    public long getPosition() {
        return this.position;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int r3) {
        monitor-enter(this);
        this.positionMark = this.position;     // Catch: Throwable -> L6
        super.mark(r3);     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        int r0 = super.read();
        if (r0 < 0) goto L5;
        this.position++;
    L5:
        return r0;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] r3) {
        return read(r3, 0, r3.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] r3, int r4, int r5) {
        long r0 = this.position;
        int r32 = super.read(r3, r4, r5);
        if (r32 <= 0) goto L5;
        this.position = r0 + r32;
    L5:
        return r32;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() {
        monitor-enter(this);
        super.reset();     // Catch: Throwable -> L6
        this.position = this.positionMark;     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long r3) {
        long r0 = this.position;
        long r32 = super.skip(r3);
        this.position = r0 + r32;
        return r32;
    }

    @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
    public /* synthetic */ long transferTo(OutputStream r1) {
        return DesugarInputStream.transferTo(this, r1);
    }
}
