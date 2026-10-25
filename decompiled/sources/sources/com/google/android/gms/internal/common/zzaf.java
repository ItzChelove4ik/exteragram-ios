package com.google.android.gms.internal.common;

import java.util.List;

/* loaded from: classes.dex */
final class zzaf extends zzah {
    private final transient zzah zza;

    public zzaf(zzah r1) {
        this.zza = r1;
    }

    private final int zzs(int r1) {
        return (this.zza.size() - 1) - r1;
    }

    @Override // com.google.android.gms.internal.common.zzah, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object r1) {
        return this.zza.contains(r1);
    }

    @Override // java.util.List
    public final Object get(int r4) {
        zzah r0 = this.zza;
        zzr.zzb(r4, r0.size(), "index");
        return r0.get(zzs(r4));
    }

    @Override // com.google.android.gms.internal.common.zzah, java.util.List
    public final int indexOf(Object r2) {
        int r22 = this.zza.lastIndexOf(r2);
        if (r22 >= 0) goto L5;
        return -1;
    L5:
        return zzs(r22);
    }

    @Override // com.google.android.gms.internal.common.zzah, java.util.List
    public final int lastIndexOf(Object r2) {
        int r22 = this.zza.indexOf(r2);
        if (r22 >= 0) goto L5;
        return -1;
    L5:
        return zzs(r22);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.common.zzah, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int r1, int r2) {
        return zzi(r1, r2);
    }

    @Override // com.google.android.gms.internal.common.zzah
    public final zzah zzh() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.common.zzah
    public final zzah zzi(int r2, int r3) {
        zzah r1 = this.zza;
        zzr.zzd(r2, r3, r1.size());
        return r1.zzi(r1.size() - r3, r1.size() - r2).zzh();
    }
}
