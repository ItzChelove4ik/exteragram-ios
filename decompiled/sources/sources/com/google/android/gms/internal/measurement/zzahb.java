package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* loaded from: classes4.dex */
public final class zzahb implements Supplier {
    private static final zzahb zza = null;
    private final Supplier zzb;

    static {
        zza = new zzahb();
    }

    public zzahb() {
        this.zzb = Suppliers.ofInstance(new zzahd());
    }

    @SideEffectFree
    public static boolean zza() {
        return zza.zzb().zza();
    }

    @Override // com.google.common.base.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return zzb();
    }

    public final zzahc zzb() {
        return (zzahc) this.zzb.get();
    }
}
