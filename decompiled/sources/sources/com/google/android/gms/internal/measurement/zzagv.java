package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* loaded from: classes4.dex */
public final class zzagv implements Supplier {
    private static final zzagv zza = null;
    private final Supplier zzb;

    static {
        zza = new zzagv();
    }

    public zzagv() {
        this.zzb = Suppliers.ofInstance(new zzagx());
    }

    @SideEffectFree
    public static long zza() {
        return zza.zzb().zza();
    }

    @Override // com.google.common.base.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return zzb();
    }

    public final zzagw zzb() {
        return (zzagw) this.zzb.get();
    }
}
