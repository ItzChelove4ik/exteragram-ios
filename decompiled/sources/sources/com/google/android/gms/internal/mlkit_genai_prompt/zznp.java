package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.Iterator;

/* loaded from: classes4.dex */
final class zznp extends zzms {
    final transient Object zza;

    public zznp(Object r1) {
        r1.getClass();
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmg, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object r1) {
        return this.zza.equals(r1);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzms, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.zza.hashCode();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return new zzmw(this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return 1;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        String r2 = this.zza.toString();
        StringBuilder r1 = new StringBuilder(String.valueOf(r2).length() + 2);
        r1.append("[");
        r1.append(r2);
        r1.append("]");
        return r1.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzms
    public final zzns zza() {
        return new zzmw(this.zza);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzms, com.google.android.gms.internal.mlkit_genai_prompt.zzmg
    public final zzml zze() {
        return zzml.zzl(this.zza);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmg
    public final int zzg(Object[] r1, int r2) {
        r1[0] = this.zza;
        return 1;
    }
}
