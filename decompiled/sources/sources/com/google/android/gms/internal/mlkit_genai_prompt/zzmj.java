package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.List;

/* loaded from: classes4.dex */
final class zzmj extends zzml {
    private final transient zzml zza;

    public zzmj(zzml r1) {
        this.zza = r1;
    }

    private final int zzt(int r1) {
        return (this.zza.size() - 1) - r1;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzml, com.google.android.gms.internal.mlkit_genai_prompt.zzmg, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object r1) {
        return this.zza.contains(r1);
    }

    @Override // java.util.List
    public final Object get(int r4) {
        zzml r0 = this.zza;
        zzla.zzf(r4, r0.size(), "index");
        return r0.get(zzt(r4));
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzml, java.util.List
    public final int indexOf(Object r2) {
        int r22 = this.zza.lastIndexOf(r2);
        if (r22 >= 0) goto L5;
        return -1;
    L5:
        return zzt(r22);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzml, java.util.List
    public final int lastIndexOf(Object r2) {
        int r22 = this.zza.indexOf(r2);
        if (r22 >= 0) goto L5;
        return -1;
    L5:
        return zzt(r22);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzml, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int r1, int r2) {
        return zzi(r1, r2);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmg
    public final boolean zzf() {
        return this.zza.zzf();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzml
    public final zzml zzh() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzml
    public final zzml zzi(int r2, int r3) {
        zzml r1 = this.zza;
        zzla.zzh(r2, r3, r1.size());
        return r1.zzi(r1.size() - r3, r1.size() - r2).zzh();
    }
}
