package com.google.android.recaptcha.internal;

import java.io.Serializable;
import java.util.Map;

/* loaded from: classes5.dex */
public abstract class zzpz<K, V> extends zzoz<K, V> implements Serializable {
    final transient zzpw<K, ? extends zzpq<V>> map;
    final transient int size;

    public zzpz(zzpw r1, int r2) {
        this.map = r1;
        this.size = r2;
    }

    @Override // com.google.android.recaptcha.internal.zzoy, com.google.android.recaptcha.internal.zzqi
    public /* synthetic */ Map zza() {
        return this.map;
    }
}
