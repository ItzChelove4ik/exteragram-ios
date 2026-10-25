package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* loaded from: classes4.dex */
public final class zzaif implements Supplier {
    private static final zzaif zza = null;
    private final Supplier zzb;

    static {
        zza = new zzaif();
    }

    public zzaif() {
        this.zzb = Suppliers.ofInstance(new zzaih());
    }

    @SideEffectFree
    public static boolean zza() {
        zza.zzh().zza();
        return true;
    }

    @SideEffectFree
    public static boolean zzb() {
        return zza.zzh().zzb();
    }

    @SideEffectFree
    public static boolean zzc() {
        return zza.zzh().zzc();
    }

    @SideEffectFree
    public static boolean zzd() {
        return zza.zzh().zzd();
    }

    @SideEffectFree
    public static boolean zze() {
        return zza.zzh().zze();
    }

    @SideEffectFree
    public static boolean zzf() {
        return zza.zzh().zzf();
    }

    @SideEffectFree
    public static boolean zzg() {
        return zza.zzh().zzg();
    }

    @Override // com.google.common.base.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return zzh();
    }

    public final zzaig zzh() {
        return (zzaig) this.zzb.get();
    }
}
