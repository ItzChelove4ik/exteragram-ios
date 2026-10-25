package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import java.util.Map;

/* loaded from: classes4.dex */
abstract class zzx implements Map.Entry {
    public zzx() {
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object r4) {
        if ((r4 instanceof Map.Entry) == false) goto L10;
        Map.Entry r42 = (Map.Entry) r4;
        if (zze.zza(getKey(), r42.getKey()) == false) goto L10;
        if (zze.zza(getValue(), r42.getValue()) == false) goto L10;
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
        return String.valueOf(getKey()) + "=" + String.valueOf(getValue());
    }
}
