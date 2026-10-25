package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* loaded from: classes4.dex */
public final class zzahz implements Supplier {
    private static final zzahz zza = null;
    private final Supplier zzb;

    static {
        zza = new zzahz();
    }

    public zzahz() {
        this.zzb = Suppliers.ofInstance(new zzaib());
    }

    @SideEffectFree
    public static boolean zza() {
        return zza.zzb().zza();
    }

    @Override // com.google.common.base.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return zzb();
    }

    public final zzaia zzb() {
        return (zzaia) this.zzb.get();
    }
}
