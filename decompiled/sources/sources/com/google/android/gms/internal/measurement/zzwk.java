package com.google.android.gms.internal.measurement;

import androidx.collection.SimpleArrayMap;
import com.google.common.base.Preconditions;

/* loaded from: classes4.dex */
final class zzwk extends zzwl {
    static final zzwl zza = null;
    static final zzwl zzb = null;

    static {
        zzwl r0 = new zzwk(null, new SimpleArrayMap(0)).zzb();
        zza = r0;
        zzwk r1 = new zzwk(r0, new SimpleArrayMap(), null);
        boolean r02 = !r1.zzh();
        Boolean r2 = Boolean.TRUE;
        Preconditions.checkState(r02, "Can't mutate after handing to trace");
        Preconditions.checkNotNull(r2);
        zzwj r03 = zzwl.zzf();
        Preconditions.checkState(!r1.zzd(r03), "Key already present");
        r1.zzg().put(r03, r2);
        zzb = r1.zzb();
    }

    private zzwk(zzwl r1, SimpleArrayMap r2) {
        super(null, r2, null);
    }

    public /* synthetic */ zzwk(zzwl r1, SimpleArrayMap r2, byte[] r3) {
        super(r1, r2, null);
    }
}
