package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.AbstractMap;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zznh extends zzml {
    final /* synthetic */ zzni zza;

    public zznh(zzni r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int r3) {
        zzni r2 = this.zza;
        zzla.zzf(r3, r2.zzm(), "index");
        int r32 = r3 + r3;
        Object r0 = r2.zzl()[r32];
        Objects.requireNonNull(r0);
        Object r22 = r2.zzl()[r32 + 1];
        Objects.requireNonNull(r22);
        return new AbstractMap.SimpleImmutableEntry(r0, r22);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.zzm();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmg
    public final boolean zzf() {
        return true;
    }
}
