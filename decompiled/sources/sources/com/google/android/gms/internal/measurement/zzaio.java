package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* loaded from: classes4.dex */
public final class zzaio implements Supplier {
    private static final zzaio zza = null;
    private final Supplier zzb;

    static {
        zza = new zzaio();
    }

    public zzaio() {
        this.zzb = Suppliers.ofInstance(new zzaiq());
    }

    @SideEffectFree
    public static boolean zza() {
        return zza.zzb().zza();
    }

    @Override // com.google.common.base.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return zzb();
    }

    public final zzaip zzb() {
        return (zzaip) this.zzb.get();
    }
}
