package com.google.android.gms.internal.measurement;

import com.google.common.collect.ImmutableSet;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public final class zzaa {
    private static final ImmutableSet zza = null;
    private String zzb;
    private final long zzc;
    private final Map zzd;

    static {
        zza = ImmutableSet.of("_syn", "_err", "_el");
    }

    public zzaa(String r1, long r2, Map r4) {
        this.zzb = r1;
        this.zzc = r2;
        HashMap r12 = new HashMap();
        this.zzd = r12;
        if (r4 == null) goto L6;
        r12.putAll(r4);
        return;
    }

    public static Object zzh(String r1, Object r2, Object r3) {
        if (zza.contains(r1) == false) goto L9;
        if ((r3 instanceof Double) == false) goto L9;
        return Long.valueOf(Math.round(((Double) r3).doubleValue()));
    L9:
        if (r1.startsWith("_") == false) goto L16;
        if ((r2 instanceof String) == false) goto L13;
        return r3;
    L13:
        if (r2 == null) goto L25;
        return r2;
    L25:
        return r3;
    L16:
        if ((r2 instanceof Double) == true) goto L25;
        if ((r2 instanceof Long) == false) goto L22;
        return Long.valueOf(Math.round(((Double) r3).doubleValue()));
    L22:
        if ((r2 instanceof String) == false) goto L25;
        return r3.toString();
    }

    public final /* bridge */ /* synthetic */ Object clone() {
        return zzg();
    }

    public final boolean equals(Object r7) {
        if (this != r7) goto L6;
        return true;
    L6:
        if ((r7 instanceof zzaa) == true) goto L8;
        return false;
    L8:
        zzaa r72 = (zzaa) r7;
        if (this.zzc == r72.zzc) goto L12;
        return false;
    L12:
        if (this.zzb.equals(r72.zzb) == true) goto L15;
        return false;
    L15:
        return this.zzd.equals(r72.zzd);
    }

    public final int hashCode() {
        int r0 = this.zzb.hashCode() * 31;
        long r1 = this.zzc;
        return ((r0 + ((int) (r1 ^ (r1 >>> 32)))) * 31) + this.zzd.hashCode();
    }

    public final String toString() {
        String r0 = this.zzb;
        String r1 = this.zzd.toString();
        int r2 = String.valueOf(r0).length();
        long r3 = this.zzc;
        int r6 = String.valueOf(r3).length();
        int r22 = ((r2 + 25) + r6) + 9;
        StringBuilder r62 = new StringBuilder((r22 + r1.length()) + 1);
        r62.append("Event{name='");
        r62.append(r0);
        r62.append("', timestamp=");
        r62.append(r3);
        r62.append(", params=");
        r62.append(r1);
        r62.append("}");
        return r62.toString();
    }

    public final long zza() {
        return this.zzc;
    }

    public final String zzb() {
        return this.zzb;
    }

    public final void zzc(String r1) {
        this.zzb = r1;
    }

    public final void zzd(String r2, Object r3) {
        Map r1 = this.zzd;
        if (r3 != null) goto L6;
        r1.remove(r2);
        return;
    L6:
        r1.put(r2, zzh(r2, r1.get(r2), r3));
    }

    public final Object zze(String r2) {
        Map r1 = this.zzd;
        if (r1.containsKey(r2) == true) goto L5;
        return null;
    L5:
        return r1.get(r2);
    }

    public final Map zzf() {
        return this.zzd;
    }

    public final zzaa zzg() {
        String r1 = this.zzb;
        HashMap r2 = new HashMap(this.zzd);
        return new zzaa(r1, this.zzc, r2);
    }
}
