package org.chromium.net.impl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes5.dex */
final class InputStreamChannel implements ReadableByteChannel {
    private final InputStream mInputStream;
    private final AtomicBoolean mIsOpen;

    private InputStreamChannel(InputStream r3) {
        this.mIsOpen = new AtomicBoolean(true);
        this.mInputStream = r3;
    }

    public static ReadableByteChannel wrap(InputStream r1) {
        if ((r1 instanceof FileInputStream) == false) goto L7;
        return ((FileInputStream) r1).getChannel();
    L7:
        return new InputStreamChannel(r1);
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.mIsOpen.compareAndSet(true, false) == false) goto L6;
        this.mInputStream.close();
        return;
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return this.mIsOpen.get();
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer r4) {
        boolean r0 = r4.hasArray();
        InputStream r1 = this.mInputStream;
        if (r0 == false) goto L8;
        int r3 = r1.read(r4.array(), r4.arrayOffset() + r4.position(), r4.remaining());
        if (r3 <= 0) goto L7;
        ByteBuffer r42 = (ByteBuffer) r4.position(r4.position() + r3);
    L7:
        return r3;
    L8:
        byte[] r02 = new byte[Math.min(16384, Math.min(Math.max(r1.available(), 4096), r4.remaining()))];
        int r32 = this.mInputStream.read(r02);
        if (r32 <= 0) goto L11;
        r4.put(r02, 0, r32);
    L11:
        return r32;
    }
}
