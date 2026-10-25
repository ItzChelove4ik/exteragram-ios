package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* loaded from: classes4.dex */
public final class zzair implements Supplier {
    private static final zzair zza = null;
    private final Supplier zzb;

    static {
        zza = new zzair();
    }

    public zzair() {
        this.zzb = Suppliers.ofInstance(new zzait());
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

    public final zzais zzc() {
        return (zzais) this.zzb.get();
    }
}
