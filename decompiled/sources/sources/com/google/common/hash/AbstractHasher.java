package com.google.common.hash;

/* loaded from: classes5.dex */
abstract class AbstractHasher implements Hasher {
    public AbstractHasher() {
    }

    @Override // com.google.common.hash.Hasher
    public Hasher putBytes(byte[] r3) {
        return putBytes(r3, 0, r3.length);
    }

    public abstract Hasher putBytes(byte[] r1, int r2, int r3);
}
