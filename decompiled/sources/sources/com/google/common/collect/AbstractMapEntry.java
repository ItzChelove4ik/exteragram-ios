package com.google.common.collect;

import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
abstract class AbstractMapEntry<K, V> implements Map.Entry<K, V> {
    public AbstractMapEntry() {
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object r4) {
        if ((r4 instanceof Map.Entry) == false) goto L10;
        Map.Entry r42 = (Map.Entry) r4;
        if (Objects.equals(getKey(), r42.getKey()) == false) goto L10;
        if (Objects.equals(getValue(), r42.getValue()) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // java.util.Map.Entry
    public abstract K getKey();

    @Override // java.util.Map.Entry
    public abstract V getValue();

    @Override // java.util.Map.Entry
    public int hashCode() {
        K r0 = getKey();
        V r2 = getValue();
        int r1 = 0;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        if (r2 == null) goto L10;
        r1 = r2.hashCode();
    L10:
        return r02 ^ r1;
    L5:
        r02 = r0.hashCode();
        goto L6
    }

    public String toString() {
        return getKey() + "=" + getValue();
    }
}
