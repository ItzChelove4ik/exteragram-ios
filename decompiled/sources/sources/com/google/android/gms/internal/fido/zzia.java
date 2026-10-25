package com.google.android.gms.internal.fido;

import org.checkerframework.dataflow.qual.SideEffectFree;

/* loaded from: classes4.dex */
public final class zzia implements zzbp {
    private static final zzia zza = null;
    private final zzbp zzb;

    static {
        zza = new zzia();
    }

    public zzia() {
        this.zzb = zzbt.zzb(new zzic());
    }

    @SideEffectFree
    public static boolean zzc() {
        return zza.zzb().zza();
    }

    @Override // com.google.android.gms.internal.fido.zzbp
    public final /* bridge */ /* synthetic */ Object zza() {
        throw null;
    }

    public final zzib zzb() {
        return (zzib) this.zzb.zza();
    }
}
