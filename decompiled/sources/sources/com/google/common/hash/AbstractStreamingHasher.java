package com.google.common.hash;

import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes5.dex */
abstract class AbstractStreamingHasher extends AbstractHasher {
    private final ByteBuffer buffer;
    private final int bufferSize;
    private final int chunkSize;

    public AbstractStreamingHasher(int r1) {
        this(r1, r1);
    }

    public AbstractStreamingHasher(int r3, int r4) {
        if ((r4 % r3) != 0) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkArgument(r0);
        this.buffer = ByteBuffer.allocate(r4 + 7).order(ByteOrder.LITTLE_ENDIAN);
        this.bufferSize = r4;
        this.chunkSize = r3;
        return;
    L5:
        r0 = false;
        goto L6
    }

    private void munch() {
        Java8Compatibility.flip(this.buffer);
    L3:
        int r0 = this.buffer.remaining();
        int r1 = this.chunkSize;
        ByteBuffer r2 = this.buffer;
        if (r0 < r1) goto L6;
        process(r2);
        goto L3
    L6:
        r2.compact();
    }

    private void munchIfFull() {
        if (this.buffer.remaining() >= 8) goto L6;
        munch();
        return;
    }

    private Hasher putBytesInternal(ByteBuffer r5) {
        if (r5.remaining() > this.buffer.remaining()) goto L6;
        this.buffer.put(r5);
        munchIfFull();
        return this;
    L6:
        int r0 = this.bufferSize - this.buffer.position();
        int r1 = 0;
    L7:
        if (r1 >= r0) goto L9;
        this.buffer.put(r5.get());
        r1 = r1 + 1;
        goto L7
    L9:
        munch();
    L11:
        if (r5.remaining() < this.chunkSize) goto L13;
        process(r5);
        goto L11
    L13:
        this.buffer.put(r5);
        return this;
    }

    @Override // com.google.common.hash.Hasher
    public final HashCode hash() {
        munch();
        Java8Compatibility.flip(this.buffer);
        if (this.buffer.remaining() <= 0) goto L6;
        processRemaining(this.buffer);
        ByteBuffer r0 = this.buffer;
        Java8Compatibility.position(r0, r0.limit());
    L6:
        return makeHash();
    }

    public abstract HashCode makeHash();

    public abstract void process(ByteBuffer r1);

    public abstract void processRemaining(ByteBuffer r1);

    @Override // com.google.common.hash.Hasher
    public final Hasher putByte(byte r2) {
        this.buffer.put(r2);
        munchIfFull();
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher
    public final Hasher putBytes(byte[] r1, int r2, int r3) {
        return putBytesInternal(ByteBuffer.wrap(r1, r2, r3).order(ByteOrder.LITTLE_ENDIAN));
    }
}
