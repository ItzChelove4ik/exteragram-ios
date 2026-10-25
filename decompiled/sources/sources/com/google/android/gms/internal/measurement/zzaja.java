package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* loaded from: classes4.dex */
public final class zzaja implements Supplier {
    private static final zzaja zza = null;
    private final Supplier zzb;

    static {
        zza = new zzaja();
    }

    public zzaja() {
        this.zzb = Suppliers.ofInstance(new zzajc());
    }

    @SideEffectFree
    public static boolean zza() {
        zza.zzc().zza();
        return true;
    }

    @SideEffectFree
    public static boolean zzb() {
        return zza.zzc().zzb();
    }

    @Override // com.google.common.base.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return zzc();
    }

    public final zzajb zzc() {
        return (zzajb) this.zzb.get();
    }
}
