package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* loaded from: classes4.dex */
public final class zzaii implements Supplier {
    private static final zzaii zza = null;
    private final Supplier zzb;

    static {
        zza = new zzaii();
    }

    public zzaii() {
        this.zzb = Suppliers.ofInstance(new zzaik());
    }

    @SideEffectFree
    public static boolean zza() {
        return zza.zzb().zza();
    }

    @Override // com.google.common.base.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return zzb();
    }

    public final zzaij zzb() {
        return (zzaij) this.zzb.get();
    }
}
