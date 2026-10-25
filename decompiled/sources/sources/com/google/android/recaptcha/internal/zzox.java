package com.google.android.recaptcha.internal;

import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
abstract class zzox implements Map.Entry {
    public zzox() {
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object r4) {
        if ((r4 instanceof Map.Entry) == false) goto L10;
        Map.Entry r42 = (Map.Entry) r4;
        if (Objects.equals(getKey(), r42.getKey()) == false) goto L10;
        if (Objects.equals(getValue(), r42.getValue()) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // java.util.Map.Entry
    public abstract Object getKey();

    @Override // java.util.Map.Entry
    public abstract Object getValue();

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Object r0 = getKey();
        Object r2 = getValue();
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

    public final String toString() {
        String r0 = String.valueOf(getKey());
        String r4 = String.valueOf(getValue());
        int r1 = r0.length();
        int r12 = r1 + 1;
        StringBuilder r3 = new StringBuilder(r12 + r4.length());
        r3.append(r0);
        r3.append("=");
        r3.append(r4);
        return r3.toString();
    }
}
