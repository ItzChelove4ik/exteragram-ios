package com.google.android.recaptcha.internal;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzpe extends AbstractSet {
    final /* synthetic */ zzpj zza;

    public /* synthetic */ zzpe(zzpj r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.zza.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object r4) {
        zzpj r3 = this.zza;
        Map r0 = r3.zzb();
        if (r0 == null) goto L7;
        return r0.entrySet().contains(r4);
    L7:
        if ((r4 instanceof Map.Entry) == false) goto L14;
        Map.Entry r42 = (Map.Entry) r4;
        int r02 = r3.zzh(r42.getKey());
        if (r02 == (-1)) goto L14;
        if (Objects.equals(r3.zzo(r02), r42.getValue()) == false) goto L14;
        return true;
    L14:
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        zzpj r1 = this.zza;
        Map r0 = r1.zzb();
        if (r0 == null) goto L7;
        return r0.entrySet().iterator();
    L7:
        return new zzpc(r1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object r9) {
        zzpj r8 = this.zza;
        Map r0 = r8.zzb();
        if (r0 == null) goto L7;
        return r0.entrySet().remove(r9);
    L7:
        if ((r9 instanceof Map.Entry) == false) goto L15;
        Map.Entry r92 = (Map.Entry) r9;
        if (r8.zza() == true) goto L18;
        int r3 = r8.zzg();
        int r93 = zzpk.zze(r92.getKey(), r92.getValue(), r3, r8.zzj(), r8.zzk(), r8.zzl(), r8.zzm());
        if (r93 == (-1)) goto L17;
        r8.zzd(r93, r3);
        r8.zzt(r8.zzs() - 1);
        r8.zzc();
        return true;
    L17:
        return false;
    L18:
        return false;
    L15:
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }
}
