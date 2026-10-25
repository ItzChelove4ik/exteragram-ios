package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* loaded from: classes4.dex */
public final class zzajd implements Supplier {
    private static final zzajd zza = null;
    private final Supplier zzb;

    static {
        zza = new zzajd();
    }

    public zzajd() {
        this.zzb = Suppliers.ofInstance(new zzajf());
    }

    @SideEffectFree
    public static boolean zza() {
        return zza.zzb().zza();
    }

    @Override // com.google.common.base.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return zzb();
    }

    public final zzaje zzb() {
        return (zzaje) this.zzb.get();
    }
}
