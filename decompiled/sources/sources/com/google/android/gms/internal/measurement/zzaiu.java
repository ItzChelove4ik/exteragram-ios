package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* loaded from: classes4.dex */
public final class zzaiu implements Supplier {
    private static final zzaiu zza = null;
    private final Supplier zzb;

    static {
        zza = new zzaiu();
    }

    public zzaiu() {
        this.zzb = Suppliers.ofInstance(new zzaiw());
    }

    @SideEffectFree
    public static boolean zza() {
        return zza.zzb().zza();
    }

    @Override // com.google.common.base.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return zzb();
    }

    public final zzaiv zzb() {
        return (zzaiv) this.zzb.get();
    }
}
