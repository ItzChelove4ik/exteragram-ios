package com.google.android.gms.internal.fido;

import java.util.Map;

/* loaded from: classes4.dex */
abstract class zzce extends zzcf {
    public zzce() {
    }

    @Override // com.google.android.gms.internal.fido.zzby, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object r3) {
        if ((r3 instanceof Map.Entry) == false) goto L10;
        Map.Entry r32 = (Map.Entry) r3;
        Object r2 = zzf().get(r32.getKey());
        if (r2 == null) goto L10;
        if (r2.equals(r32.getValue()) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // com.google.android.gms.internal.fido.zzcf, java.util.Collection, java.util.Set
    public final int hashCode() {
        return zzcy.zza(zzf().zzc());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return zzf().size();
    }

    public abstract zzcd zzf();

    @Override // com.google.android.gms.internal.fido.zzcf
    public final boolean zzg() {
        return false;
    }
}
