package com.google.protobuf;

import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public abstract class ByteOutput {
    public ByteOutput() {
    }

    public abstract void writeLazy(ByteBuffer r1);

    public abstract void writeLazy(byte[] r1, int r2, int r3);
}
