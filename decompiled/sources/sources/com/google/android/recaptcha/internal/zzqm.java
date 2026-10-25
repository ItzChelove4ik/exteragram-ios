package com.google.android.recaptcha.internal;

import java.util.AbstractMap;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzqm extends zzpt {
    final /* synthetic */ zzqn zza;

    public zzqm(zzqn r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int r3) {
        zzqn r2 = this.zza;
        zzoq.zze(r3, r2.zzo(), "index");
        int r32 = r3 + r3;
        Object r0 = r2.zzn()[r32];
        Objects.requireNonNull(r0);
        Object r22 = r2.zzn()[r32 + 1];
        Objects.requireNonNull(r22);
        return new AbstractMap.SimpleImmutableEntry(r0, r22);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.zzo();
    }
}
