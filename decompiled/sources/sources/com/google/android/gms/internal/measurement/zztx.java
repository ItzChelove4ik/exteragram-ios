package com.google.android.gms.internal.measurement;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.Futures;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
final class zztx extends zzuw {
    private static final zzuw zza = null;

    static {
        zza = new zztx();
    }

    private zztx() {
    }

    public static /* synthetic */ zzuw zzc() {
        return zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzuw
    public final String zza(zzti r1) {
        Preconditions.checkNotNull(r1);
        return "singleproc";
    }

    @Override // com.google.android.gms.internal.measurement.zzuw
    public final /* bridge */ /* synthetic */ zzuv zzb(zztr r9, String r10, Executor r11, zzru r12, zzti r13) {
        Preconditions.checkNotNull(r13);
        if (r9.zzf() == false) goto L5;
        zzadf r8 = zzadf.zzb();
    L6:
        zzve r3 = zzve.zzd(r9.zzb(), r8);
        return new zzui(r10, Futures.immediateFuture(r9.zza()), r3, r11, r12, r9.zzc(), zzwb.zzb());
    L5:
        r8 = zzadf.zza();
        goto L6
    }
}
