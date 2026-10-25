package com.google.android.gms.internal.fido;

import java.util.List;

/* loaded from: classes4.dex */
final class zzca extends zzcc {
    private final transient zzcc zza;

    public zzca(zzcc r1) {
        this.zza = r1;
    }

    private final int zzl(int r1) {
        return (this.zza.size() - 1) - r1;
    }

    @Override // com.google.android.gms.internal.fido.zzcc, com.google.android.gms.internal.fido.zzby, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object r1) {
        return this.zza.contains(r1);
    }

    @Override // java.util.List
    public final Object get(int r3) {
        zzbm.zza(r3, this.zza.size(), "index");
        return this.zza.get(zzl(r3));
    }

    @Override // com.google.android.gms.internal.fido.zzcc, java.util.List
    public final int indexOf(Object r2) {
        int r22 = this.zza.lastIndexOf(r2);
        if (r22 >= 0) goto L5;
        return -1;
    L5:
        return zzl(r22);
    }

    @Override // com.google.android.gms.internal.fido.zzcc, java.util.List
    public final int lastIndexOf(Object r2) {
        int r22 = this.zza.indexOf(r2);
        if (r22 >= 0) goto L5;
        return -1;
    L5:
        return zzl(r22);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.fido.zzcc, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int r1, int r2) {
        return zzg(r1, r2);
    }

    @Override // com.google.android.gms.internal.fido.zzcc
    public final zzcc zzf() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.fido.zzcc
    public final zzcc zzg(int r3, int r4) {
        zzbm.zze(r3, r4, this.zza.size());
        zzcc r0 = this.zza;
        return r0.zzg(r0.size() - r4, this.zza.size() - r3).zzf();
    }
}
