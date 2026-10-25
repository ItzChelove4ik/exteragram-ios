package com.google.android.gms.internal.measurement;

import java.io.OutputStream;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzsw implements zzrt {
    private zzro[] zza;

    private zzsw() {
    }

    public static zzsw zzb() {
        return new zzsw();
    }

    @Override // com.google.android.gms.internal.measurement.zzrt
    public final /* bridge */ /* synthetic */ Object zza(zzrs r3) {
        List r32 = r3.zzd(r3.zza().zzj(r3.zzb()));
        zzro[] r2 = this.zza;
        if (r2 == null) goto L6;
        r2[0].zza(r32);
    L6:
        return (OutputStream) r32.get(0);
    }

    public final zzsw zzc(zzro... r1) {
        this.zza = r1;
        return this;
    }
}
