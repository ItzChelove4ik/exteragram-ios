package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.Iterator;

/* loaded from: classes4.dex */
final class zznj extends zzms {
    private final transient zzmo zza;
    private final transient zzml zzb;

    public zznj(zzmo r1, zzml r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmg, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object r1) {
        if (this.zza.get(r1) == null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return this.zzb.zzs(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzms
    public final zzns zza() {
        return this.zzb.zzs(0);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzms, com.google.android.gms.internal.mlkit_genai_prompt.zzmg
    public final zzml zze() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmg
    public final int zzg(Object[] r1, int r2) {
        return this.zzb.zzg(r1, 0);
    }
}
