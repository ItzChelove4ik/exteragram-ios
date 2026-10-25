package com.carrotsearch.randomizedtesting;

/* loaded from: classes.dex */
abstract class MurmurHash3 {
    public static long hash(long r3) {
        long r32 = (r3 ^ (r3 >>> 33)) * (-49064778989728563L);
        long r33 = (r32 ^ (r32 >>> 33)) * (-4265267296055464877L);
        return r33 ^ (r33 >>> 33);
    }
}
