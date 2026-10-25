package com.google.android.gms.internal.measurement;

import java.util.Map;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzafs implements Map.Entry, Comparable {
    final /* synthetic */ zzafv zza;
    private final Comparable zzb;
    private Object zzc;

    public zzafs(zzafv r1, Comparable r2, Object r3) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    private static final boolean zzb(Object r0, Object r1) {
        if (r0 != null) goto L9;
        if (r1 == null) goto L6;
        return false;
    L6:
        return true;
    L9:
        return r0.equals(r1);
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object r1) {
        Comparable r12 = ((zzafs) r1).zzb;
        return this.zzb.compareTo(r12);
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof Map.Entry) == true) goto L8;
        return false;
    L8:
        Map.Entry r52 = (Map.Entry) r5;
        if (zzb(this.zzb, r52.getKey()) == true) goto L11;
    L13:
        return false;
    L11:
        if (zzb(this.zzc, r52.getValue()) == false) goto L13;
        return true;
    }

    @Override // java.util.Map.Entry
    public final /* synthetic */ Object getKey() {
        return this.zzb;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.zzc;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Comparable r0 = this.zzb;
        int r1 = 0;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        Object r2 = this.zzc;
        if (r2 == null) goto L11;
        r1 = r2.hashCode();
    L11:
        return r02 ^ r1;
    L5:
        r02 = r0.hashCode();
        goto L6
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object r2) {
        this.zza.zzh();
        Object r0 = this.zzc;
        this.zzc = r2;
        return r0;
    }

    public final String toString() {
        String r0 = String.valueOf(this.zzb);
        String r4 = String.valueOf(this.zzc);
        int r1 = r0.length();
        int r12 = r1 + 1;
        StringBuilder r3 = new StringBuilder(r12 + r4.length());
        r3.append(r0);
        r3.append("=");
        r3.append(r4);
        return r3.toString();
    }

    public final Comparable zza() {
        return this.zzb;
    }
}
