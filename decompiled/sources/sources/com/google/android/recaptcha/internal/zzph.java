package com.google.android.recaptcha.internal;

import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzph extends zzox {
    final /* synthetic */ zzpj zza;
    private final Object zzb;
    private int zzc;

    public zzph(zzpj r1, int r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        this.zzb = r1.zzn(r2);
        this.zzc = r2;
    }

    private final void zza() {
        int r0 = this.zzc;
        if (r0 == (-1)) goto L10;
        zzpj r1 = this.zza;
        if (r0 >= r1.size()) goto L10;
        if (Objects.equals(this.zzb, r1.zzn(this.zzc)) == false) goto L10;
        return;
    L10:
        this.zzc = this.zza.zzh(this.zzb);
    }

    @Override // com.google.android.recaptcha.internal.zzox, java.util.Map.Entry
    public final Object getKey() {
        return this.zzb;
    }

    @Override // com.google.android.recaptcha.internal.zzox, java.util.Map.Entry
    public final Object getValue() {
        zzpj r0 = this.zza;
        Map r1 = r0.zzb();
        if (r1 != null) goto L5;
        zza();
        int r2 = this.zzc;
        if (r2 != (-1)) goto L11;
        return null;
    L11:
        return r0.zzo(r2);
    L5:
        return r1.get(this.zzb);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object r4) {
        zzpj r0 = this.zza;
        Map r1 = r0.zzb();
        if (r1 != null) goto L5;
        zza();
        int r12 = this.zzc;
        if (r12 != (-1)) goto L10;
        r0.put(this.zzb, r4);
        return null;
    L10:
        Object r13 = r0.zzo(r12);
        r0.zzp(this.zzc, r4);
        return r13;
    L5:
        return r1.put(this.zzb, r4);
    }
}
