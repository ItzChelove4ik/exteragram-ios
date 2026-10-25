package com.google.android.gms.internal.mlkit_vision_label;

import java.util.Map;

/* loaded from: classes4.dex */
final class zzas extends zzah {
    final /* synthetic */ zzau zza;
    private final Object zzb;
    private int zzc;

    public zzas(zzau r1, int r2) {
        this.zza = r1;
        this.zzb = zzau.zzg(r1, r2);
        this.zzc = r2;
    }

    private final void zza() {
        int r0 = this.zzc;
        if (r0 != (-1)) goto L5;
    L10:
        this.zzc = zzau.zzc(this.zza, this.zzb);
        return;
    L5:
        if (r0 >= this.zza.size()) goto L10;
        if (zzo.zza(this.zzb, zzau.zzg(this.zza, this.zzc)) == false) goto L10;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzah, java.util.Map.Entry
    public final Object getKey() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzah, java.util.Map.Entry
    public final Object getValue() {
        Map r0 = this.zza.zzl();
        if (r0 != null) goto L5;
        zza();
        int r02 = this.zzc;
        if (r02 != (-1)) goto L11;
        return null;
    L11:
        return zzau.zzj(this.zza, r02);
    L5:
        return r0.get(this.zzb);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object r4) {
        Map r0 = this.zza.zzl();
        if (r0 != null) goto L5;
        zza();
        int r02 = this.zzc;
        zzau r1 = this.zza;
        if (r02 != (-1)) goto L10;
        r1.put(this.zzb, r4);
        return null;
    L10:
        Object r03 = zzau.zzj(r1, r02);
        zzau.zzm(this.zza, this.zzc, r4);
        return r03;
    L5:
        return r0.put(this.zzb, r4);
    }
}
