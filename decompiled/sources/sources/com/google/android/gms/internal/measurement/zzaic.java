package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* loaded from: classes4.dex */
public final class zzaic implements Supplier {
    private static final zzaic zza = null;
    private final Supplier zzb;

    static {
        zza = new zzaic();
    }

    public zzaic() {
        this.zzb = Suppliers.ofInstance(new zzaie());
    }

    @SideEffectFree
    public static boolean zza() {
        return zza.zzg().zza();
    }

    @SideEffectFree
    public static long zzb() {
        return zza.zzg().zzb();
    }

    @SideEffectFree
    public static double zzc() {
        return zza.zzg().zzc();
    }

    @SideEffectFree
    public static long zzd() {
        return zza.zzg().zzd();
    }

    @SideEffectFree
    public static long zze() {
        return zza.zzg().zze();
    }

    @SideEffectFree
    public static String zzf() {
        return zza.zzg().zzf();
    }

    @Override // com.google.common.base.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return zzg();
    }

    public final zzaid zzg() {
        return (zzaid) this.zzb.get();
    }
}
