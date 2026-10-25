package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* loaded from: classes4.dex */
public final class zzahn implements Supplier {
    private static final zzahn zza = null;
    private final Supplier zzb;

    static {
        zza = new zzahn();
    }

    public zzahn() {
        this.zzb = Suppliers.ofInstance(new zzahp());
    }

    @SideEffectFree
    public static boolean zza() {
        zza.zze().zza();
        return true;
    }

    @SideEffectFree
    public static boolean zzb() {
        return zza.zze().zzb();
    }

    @SideEffectFree
    public static boolean zzc() {
        return zza.zze().zzc();
    }

    @SideEffectFree
    public static boolean zzd() {
        return zza.zze().zzd();
    }

    @Override // com.google.common.base.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return zze();
    }

    public final zzaho zze() {
        return (zzaho) this.zzb.get();
    }
}
