package com.google.common.collect;

import java.util.Arrays;
import java.util.Map;

/* loaded from: classes5.dex */
abstract class Platform {
    public static <T> T[] copy(Object[] r0, int r1, int r2, T[] r3) {
        return (T[]) Arrays.copyOfRange(r0, r1, r2, r3.getClass());
    }

    public static <K, V> Map<K, V> newHashMapWithExpectedSize(int r0) {
        return CompactHashMap.createWithExpectedSize(r0);
    }
}
