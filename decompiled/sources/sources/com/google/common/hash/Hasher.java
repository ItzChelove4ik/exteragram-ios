package com.google.common.hash;

/* loaded from: classes5.dex */
public interface Hasher {
    HashCode hash();

    Hasher putByte(byte r1);

    Hasher putBytes(byte[] r1);
}
