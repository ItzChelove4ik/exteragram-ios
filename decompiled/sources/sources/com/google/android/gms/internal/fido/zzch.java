package com.google.android.gms.internal.fido;

import java.util.AbstractMap;

/* loaded from: classes4.dex */
final class zzch extends zzcc {
    final /* synthetic */ zzci zza;

    public zzch(zzci r1) {
        this.zza = r1;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int r3) {
        return new AbstractMap.SimpleImmutableEntry(zzcj.zzk(this.zza.zza).zzd.get(r3), zzcj.zze(this.zza.zza).get(r3));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.zza.size();
    }
}
