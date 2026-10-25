package com.google.common.hash;

/* loaded from: classes5.dex */
public abstract class Hashing {
    static final int GOOD_FAST_HASH_SEED = 0;

    static {
        GOOD_FAST_HASH_SEED = (int) System.currentTimeMillis();
    }

    public static HashFunction murmur3_128() {
        return Murmur3_128HashFunction.MURMUR3_128;
    }
}
