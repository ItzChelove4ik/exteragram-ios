package com.google.android.gms.internal.measurement;

import com.google.common.base.Function;

/* loaded from: classes4.dex */
final /* synthetic */ class zzqz implements Function {
    static final /* synthetic */ zzqz zza = null;

    static {
        zza = new zzqz();
    }

    private /* synthetic */ zzqz() {
    }

    @Override // com.google.common.base.Function
    public final /* synthetic */ Object apply(Object r3) {
        zzmk r32 = (zzmk) r3;
        if (r32.zza() != 29514) goto L6;
        zznn r2 = zzno.zzd();
        zznh r33 = zzni.zzo();
        r33.zza(System.currentTimeMillis());
        r2.zza(r33);
        return (zzno) r2.zzbd();
    L6:
        throw r32;
    }
}
