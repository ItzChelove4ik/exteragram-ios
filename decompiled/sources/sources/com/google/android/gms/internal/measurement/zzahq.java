package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* loaded from: classes4.dex */
public final class zzahq implements Supplier {
    private static final zzahq zza = null;
    private final Supplier zzb;

    static {
        zza = new zzahq();
    }

    public zzahq() {
        this.zzb = Suppliers.ofInstance(new zzahs());
    }

    @SideEffectFree
    public static boolean zza() {
        return zza.zzb().zza();
    }

    @Override // com.google.common.base.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return zzb();
    }

    public final zzahr zzb() {
        return (zzahr) this.zzb.get();
    }
}
