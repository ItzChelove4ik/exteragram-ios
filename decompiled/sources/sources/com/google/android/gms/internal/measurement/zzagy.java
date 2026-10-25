package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* loaded from: classes4.dex */
public final class zzagy implements Supplier {
    private static final zzagy zza = null;
    private final Supplier zzb;

    static {
        zza = new zzagy();
    }

    public zzagy() {
        this.zzb = Suppliers.ofInstance(new zzaha());
    }

    @SideEffectFree
    public static boolean zza() {
        return zza.zzc().zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return zza.zzc().zzb();
    }

    @Override // com.google.common.base.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return zzc();
    }

    public final zzagz zzc() {
        return (zzagz) this.zzb.get();
    }
}
