package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.ParcelFileDescriptor;
import android.util.Pair;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public final class zzgp {
    private final zzml zza;
    private final Executor zzb;

    public zzgp(ParcelFileDescriptor r1, ParcelFileDescriptor r2, Executor r3) {
        this.zza = zzml.zzl(Pair.create(r1, r2));
        this.zzb = r3;
    }

    public final void zza(zzwh r2) {
        zzgo r0 = new zzgo(this, r2);
        this.zza.forEach(r0);
    }

    public final /* synthetic */ void zzb(zzwh r2, Pair r3) {
        zzgn r0 = new zzgn(this, r3, r2);
        this.zzb.execute(r0);
    }
}
