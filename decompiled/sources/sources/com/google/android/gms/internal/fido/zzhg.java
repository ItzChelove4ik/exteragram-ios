package com.google.android.gms.internal.fido;

import java.util.ArrayList;
import java.util.Arrays;
import kotlin.jvm.internal.ByteCompanionObject;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzhg extends zzhp {
    private final zzcc zza;
    private final int zzb;

    public zzhg(zzcc r4) {
        r4.getClass();
        this.zza = r4;
        int r42 = 0;
        int r0 = 0;
    L3:
        zzcc r1 = this.zza;
        if (r42 >= r1.size()) goto L9;
        int r12 = ((zzhp) r1.get(r42)).zzb();
        if (r0 >= r12) goto L8;
        r0 = r12;
    L8:
        r42 = r42 + 1;
        goto L3
    L9:
        int r02 = r0 + 1;
        this.zzb = r02;
        if (r02 > 8) goto L13;
        return;
    L13:
        throw new zzhf("Exceeded cutoff limit for max depth of cbor value");
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object r5) {
        zzhp r52 = (zzhp) r5;
        if (zzhp.zzd(ByteCompanionObject.MIN_VALUE) != r52.zza()) goto L5;
        zzhg r53 = (zzhg) r52;
        zzcc r0 = this.zza;
        int r1 = r0.size();
        zzcc r2 = r53.zza;
        if (r1 != r2.size()) goto L9;
        int r12 = 0;
    L11:
        zzcc r22 = this.zza;
        if (r12 >= r22.size()) goto L17;
        int r23 = ((zzhp) r22.get(r12)).compareTo((zzhp) r53.zza.get(r12));
        if (r23 != 0) goto L15;
        r12 = r12 + 1;
        goto L11
    L15:
        return r23;
    L17:
        return 0;
    L9:
        return r0.size() - r2.size();
    L5:
        return zzhp.zzd(ByteCompanionObject.MIN_VALUE) - r52.zza();
    }

    public final boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if (r4 != null) goto L9;
        return false;
    L9:
        if (zzhg.class == r4.getClass()) goto L12;
        return false;
    L12:
        return this.zza.equals(((zzhg) r4).zza);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(zzhp.zzd(ByteCompanionObject.MIN_VALUE)), this.zza});
    }

    public final String toString() {
        if (this.zza.isEmpty() == false) goto L6;
        return _UrlKt.PATH_SEGMENT_ENCODE_SET_URI;
    L6:
        ArrayList r0 = new ArrayList();
        zzcc r6 = this.zza;
        int r1 = r6.size();
        int r2 = 0;
    L7:
        if (r2 >= r1) goto L9;
        r0.add(((zzhp) r6.get(r2)).toString().replace("\n", "\n  "));
        r2 = r2 + 1;
        goto L7
    L9:
        zzbd r62 = zzbd.zza(",\n  ");
        StringBuilder r12 = new StringBuilder("[\n  ");
        r62.zzc(r12, r0.iterator());
        r12.append("\n]");
        return r12.toString();
    }

    @Override // com.google.android.gms.internal.fido.zzhp
    public final int zza() {
        return zzhp.zzd(ByteCompanionObject.MIN_VALUE);
    }

    @Override // com.google.android.gms.internal.fido.zzhp
    public final int zzb() {
        return this.zzb;
    }
}
