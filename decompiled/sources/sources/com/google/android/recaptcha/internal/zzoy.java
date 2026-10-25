package com.google.android.recaptcha.internal;

import java.util.Map;

/* loaded from: classes5.dex */
abstract class zzoy<K, V> implements zzqi<K, V> {
    public zzoy() {
    }

    public final boolean equals(Object r2) {
        if (r2 != this) goto L6;
        return true;
    L6:
        if ((r2 instanceof zzqi) == true) goto L10;
        return false;
    L10:
        return zza().equals(((zzqi) r2).zza());
    }

    public final int hashCode() {
        return zza().hashCode();
    }

    public final String toString() {
        return zza().toString();
    }

    @Override // com.google.android.recaptcha.internal.zzqi
    public abstract Map zza();
}
