package com.google.android.gms.internal.vision;

import java.util.Map;

/* loaded from: classes5.dex */
final class zzlm implements Comparable, Map.Entry {
    private final Comparable zza;
    private Object zzb;
    private final /* synthetic */ zzlh zzc;

    public zzlm(zzlh r1, Comparable r2, Object r3) {
        this.zzc = r1;
        this.zza = r2;
        this.zzb = r3;
    }

    public zzlm(zzlh r2, Map.Entry r3) {
        this(r2, (Comparable) r3.getKey(), r3.getValue());
    }

    private static boolean zza(Object r0, Object r1) {
        if (r0 != null) goto L9;
        if (r1 != null) goto L6;
        return true;
    L6:
        return false;
    L9:
        return r0.equals(r1);
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(Object r1) {
        return ((Comparable) getKey()).compareTo((Comparable) ((zzlm) r1).getKey());
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
        if (zza(this.zza, r52.getKey()) == true) goto L11;
    L13:
        return false;
    L11:
        if (zza(this.zzb, r52.getValue()) == false) goto L13;
        return true;
    }

    @Override // java.util.Map.Entry
    public final /* synthetic */ Object getKey() {
        return this.zza;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.zzb;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Comparable r0 = this.zza;
        int r1 = 0;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        Object r2 = this.zzb;
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
        zzlh.zza(this.zzc);
        Object r0 = this.zzb;
        this.zzb = r2;
        return r0;
    }

    public final String toString() {
        String r0 = String.valueOf(this.zza);
        String r3 = String.valueOf(this.zzb);
        StringBuilder r2 = new StringBuilder((r0.length() + 1) + r3.length());
        r2.append(r0);
        r2.append("=");
        r2.append(r3);
        return r2.toString();
    }
}
