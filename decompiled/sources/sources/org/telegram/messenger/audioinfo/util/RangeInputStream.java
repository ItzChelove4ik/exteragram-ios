package org.telegram.messenger.audioinfo.util;

import java.io.InputStream;

/* loaded from: classes5.dex */
public class RangeInputStream extends PositionInputStream {
    private final long endPosition;

    public RangeInputStream(InputStream r1, long r2, long r4) {
        super(r1, r2);
        this.endPosition = r2 + r4;
    }

    public long getRemainingLength() {
        return this.endPosition - getPosition();
    }

    @Override // org.telegram.messenger.audioinfo.util.PositionInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() {
        if (getPosition() != this.endPosition) goto L7;
        return -1;
    L7:
        return super.read();
    }

    @Override // org.telegram.messenger.audioinfo.util.PositionInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] r5, int r6, int r7) {
        long r0 = getPosition() + r7;
        long r2 = this.endPosition;
        if (r0 <= r2) goto L9;
        r7 = (int) (r2 - getPosition());
        if (r7 != 0) goto L9;
        return -1;
    L9:
        return super.read(r5, r6, r7);
    }

    @Override // org.telegram.messenger.audioinfo.util.PositionInputStream, java.io.FilterInputStream, java.io.InputStream
    public long skip(long r5) {
        long r0 = getPosition() + r5;
        long r2 = this.endPosition;
        if (r0 <= r2) goto L6;
        r5 = (int) (r2 - getPosition());
    L6:
        return super.skip(r5);
    }
}
