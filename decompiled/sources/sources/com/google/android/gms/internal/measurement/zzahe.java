package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* loaded from: classes4.dex */
public final class zzahe implements Supplier {
    private static final zzahe zza = null;
    private final Supplier zzb;

    static {
        zza = new zzahe();
    }

    public zzahe() {
        this.zzb = Suppliers.ofInstance(new zzahg());
    }

    @SideEffectFree
    public static String zza() {
        return zza.zzd().zza();
    }

    @SideEffectFree
    public static String zzb() {
        return zza.zzd().zzb();
    }

    @SideEffectFree
    public static String zzc() {
        return zza.zzd().zzc();
    }

    @Override // com.google.common.base.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return zzd();
    }

    public final zzahf zzd() {
        return (zzahf) this.zzb.get();
    }
}
