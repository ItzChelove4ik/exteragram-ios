package com.google.android.gms.internal.fido;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import okio.Buffer$$ExternalSyntheticBUOutline2;

/* loaded from: classes4.dex */
public final class zzhm extends zzhp {
    private final int zza;
    private final zzcj zzb;

    public zzhm(zzcj r4) {
        r4.getClass();
        this.zzb = r4;
        zzdc r42 = r4.zzc().zzd();
        int r0 = 0;
    L4:
        if (r42.hasNext() == false) goto L11;
        Map.Entry r1 = (Map.Entry) r42.next();
        int r2 = ((zzhp) r1.getKey()).zzb();
        if (r0 >= r2) goto L8;
        r0 = r2;
    L8:
        int r12 = ((zzhp) r1.getValue()).zzb();
        if (r0 >= r12) goto L4;
        r0 = r12;
        goto L4
    L11:
        int r02 = r0 + 1;
        this.zza = r02;
        if (r02 > 8) goto L15;
        return;
    L15:
        throw new zzhf("Exceeded cutoff limit for max depth of cbor value");
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object r5) {
        zzhp r52 = (zzhp) r5;
        int r0 = r52.zza();
        if (zzhp.zzd((byte) -96) == r0) goto L6;
        int r4 = r52.zza();
        return zzhp.zzd((byte) -96) - r4;
    L6:
        zzhm r53 = (zzhm) r52;
        int r02 = this.zzb.size();
        int r1 = r53.zzb.size();
        zzcj r42 = this.zzb;
        if (r02 != r1) goto L9;
        zzdc r43 = r42.zzc().zzd();
        zzdc r54 = r53.zzb.zzc().zzd();
    L12:
        if (r43.hasNext() == false) goto L14;
    L18:
        Map.Entry r03 = (Map.Entry) r43.next();
        Map.Entry r12 = (Map.Entry) r54.next();
        int r2 = ((zzhp) r03.getKey()).compareTo((zzhp) r12.getKey());
        if (r2 != 0) goto L20;
        int r04 = ((zzhp) r03.getValue()).compareTo((zzhp) r12.getValue());
        if (r04 == 0) goto L12;
        return r04;
    L20:
        return r2;
    L14:
        if (r54.hasNext() == true) goto L18;
        return 0;
    L9:
        return r42.size() - r53.zzb.size();
    }

    public final boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if (r4 != null) goto L9;
        return false;
    L9:
        if (zzhm.class == r4.getClass()) goto L12;
        return false;
    L12:
        return this.zzb.equals(((zzhm) r4).zzb);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(zzhp.zzd((byte) -96)), this.zzb});
    }

    public final String toString() {
        if (this.zzb.isEmpty() == false) goto L6;
        return "{}";
    L6:
        LinkedHashMap r0 = new LinkedHashMap();
        zzdc r5 = this.zzb.zzc().zzd();
    L8:
        if (r5.hasNext() == false) goto L10;
        Map.Entry r1 = (Map.Entry) r5.next();
        r0.put(((zzhp) r1.getKey()).toString().replace("\n", "\n  "), ((zzhp) r1.getValue()).toString().replace("\n", "\n  "));
        goto L8
    L10:
        zzbd r52 = zzbd.zza(",\n  ");
        StringBuilder r12 = new StringBuilder("{\n  ");
        zzbc.zza(r12, r0.entrySet().iterator(), r52, " : ");     // Catch: IOException -> L14
        r12.append("\n}");
        return r12.toString();
    L14:
        e = move-exception;
        Buffer$$ExternalSyntheticBUOutline2.m(e);
        return null;
    }

    @Override // com.google.android.gms.internal.fido.zzhp
    public final int zza() {
        return zzhp.zzd((byte) -96);
    }

    @Override // com.google.android.gms.internal.fido.zzhp
    public final int zzb() {
        return this.zza;
    }

    public final zzcj zzc() {
        return this.zzb;
    }
}
