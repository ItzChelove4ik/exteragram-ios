package com.google.android.recaptcha.internal;

import java.util.Comparator;

/* loaded from: classes5.dex */
public class zzqe<K, V> extends zzpz<K, V> implements zzqi<K, V> {
    private final transient zzqb<V> emptySet;
    private transient zzqb zza;

    public zzqe(zzpw r1, int r2, Comparator r3) {
        super(r1, r2);
        this.emptySet = zzqr.zza;
    }

    public final zzqb zzb() {
        zzqb r0 = this.zza;
        if (r0 != null) goto L6;
        zzqd r02 = new zzqd(this);
        this.zza = r02;
        return r02;
    L6:
        return r0;
    }
}
