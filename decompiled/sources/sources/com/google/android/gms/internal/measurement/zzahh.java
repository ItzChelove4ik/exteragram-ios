package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* loaded from: classes4.dex */
public final class zzahh implements Supplier {
    private static final zzahh zza = null;
    private final Supplier zzb;

    static {
        zza = new zzahh();
    }

    public zzahh() {
        this.zzb = Suppliers.ofInstance(new zzahj());
    }

    @SideEffectFree
    public static boolean zza() {
        zza.zzc().zza();
        return true;
    }

    @SideEffectFree
    public static boolean zzb() {
        return zza.zzc().zzb();
    }

    @Override // com.google.common.base.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return zzc();
    }

    public final zzahi zzc() {
        return (zzahi) this.zzb.get();
    }
}
