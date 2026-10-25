package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* loaded from: classes4.dex */
public final class zzaht implements Supplier {
    private static final zzaht zza = null;
    private final Supplier zzb;

    static {
        zza = new zzaht();
    }

    public zzaht() {
        this.zzb = Suppliers.ofInstance(new zzahv());
    }

    @SideEffectFree
    public static boolean zza() {
        return zza.zzc().zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return zza.zzc().zzb();
    }

    @Override // com.google.common.base.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return zzc();
    }

    public final zzahu zzc() {
        return (zzahu) this.zzb.get();
    }
}
