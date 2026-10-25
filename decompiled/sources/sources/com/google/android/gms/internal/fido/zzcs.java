package com.google.android.gms.internal.fido;

import java.util.Comparator;

/* loaded from: classes4.dex */
public abstract class zzcs implements Comparator {
    public zzcs() {
    }

    @Override // java.util.Comparator
    public abstract int compare(Object r1, Object r2);

    public zzcs zza() {
        return new zzcx(this);
    }
}
