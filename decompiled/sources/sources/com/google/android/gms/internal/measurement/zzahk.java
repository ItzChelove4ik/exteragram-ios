package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* loaded from: classes4.dex */
public final class zzahk implements Supplier {
    private static final zzahk zza = null;
    private final Supplier zzb;

    static {
        zza = new zzahk();
    }

    public zzahk() {
        this.zzb = Suppliers.ofInstance(new zzahm());
    }

    @SideEffectFree
    public static boolean zza() {
        zza.zzd().zza();
        return true;
    }

    @SideEffectFree
    public static boolean zzb() {
        return zza.zzd().zzb();
    }

    @SideEffectFree
    public static boolean zzc() {
        return zza.zzd().zzc();
    }

    @Override // com.google.common.base.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return zzd();
    }

    public final zzahl zzd() {
        return (zzahl) this.zzb.get();
    }
}
