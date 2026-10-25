package com.google.android.gms.internal.clearcut;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
final class zzdk implements zzdj {
    public zzdk() {
    }

    @Override // com.google.android.gms.internal.clearcut.zzdj
    public final int zzb(int r1, Object r2, Object r3) {
        zzdi r22 = (zzdi) r2;
        if (r22.isEmpty() == false) goto L5;
        return 0;
    L5:
        Iterator r0 = r22.entrySet().iterator();
        if (r0.hasNext() == true) goto L8;
        return 0;
    L8:
        Map.Entry r02 = (Map.Entry) r0.next();
        r02.getKey();
        r02.getValue();
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.clearcut.zzdj
    public final Object zzb(Object r1, Object r2) {
        zzdi r12 = (zzdi) r1;
        zzdi r22 = (zzdi) r2;
        if (r22.isEmpty() == false) goto L5;
    L8:
        return r12;
    L5:
        if (r12.isMutable() == true) goto L7;
        r12 = r12.zzca();
    L7:
        r12.zza(r22);
        goto L8
    }

    @Override // com.google.android.gms.internal.clearcut.zzdj
    public final Map<?, ?> zzg(Object r1) {
        return (zzdi) r1;
    }

    @Override // com.google.android.gms.internal.clearcut.zzdj
    public final Map<?, ?> zzh(Object r1) {
        return (zzdi) r1;
    }

    @Override // com.google.android.gms.internal.clearcut.zzdj
    public final boolean zzi(Object r1) {
        if (((zzdi) r1).isMutable() == true) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.gms.internal.clearcut.zzdj
    public final Object zzj(Object r1) {
        ((zzdi) r1).zzv();
        return r1;
    }

    @Override // com.google.android.gms.internal.clearcut.zzdj
    public final Object zzk(Object r1) {
        return zzdi.zzbz().zzca();
    }

    @Override // com.google.android.gms.internal.clearcut.zzdj
    public final zzdh<?, ?> zzl(Object r1) {
        throw new NoSuchMethodError();
    }
}
