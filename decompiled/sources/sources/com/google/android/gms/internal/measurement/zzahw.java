package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* loaded from: classes4.dex */
public final class zzahw implements Supplier {
    private static final zzahw zza = null;
    private final Supplier zzb;

    static {
        zza = new zzahw();
    }

    public zzahw() {
        this.zzb = Suppliers.ofInstance(new zzahy());
    }

    @SideEffectFree
    public static boolean zza() {
        return zza.zzb().zza();
    }

    @Override // com.google.common.base.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return zzb();
    }

    public final zzahx zzb() {
        return (zzahx) this.zzb.get();
    }
}
