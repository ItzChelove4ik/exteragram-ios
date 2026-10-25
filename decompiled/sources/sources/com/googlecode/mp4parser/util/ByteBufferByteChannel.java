package com.googlecode.mp4parser.util;

import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;

/* loaded from: classes5.dex */
public class ByteBufferByteChannel implements ByteChannel {
    ByteBuffer byteBuffer;

    public ByteBufferByteChannel(ByteBuffer r1) {
        this.byteBuffer = r1;
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer r5) {
        int r0 = r5.remaining();
        if (this.byteBuffer.remaining() > 0) goto L6;
        return -1;
    L6:
        r5.put((ByteBuffer) this.byteBuffer.duplicate().limit(this.byteBuffer.position() + r5.remaining()));
        ByteBuffer r4 = this.byteBuffer;
        r4.position(r4.position() + r0);
        return r0;
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer r2) {
        int r0 = r2.remaining();
        this.byteBuffer.put(r2);
        return r0;
    }
}
