package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.ParcelFileDescriptor;
import java.util.Objects;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
final class zzel extends zzba {
    final /* synthetic */ Executor zza;
    final /* synthetic */ zzeo zzb;

    public zzel(zzeo r1, Executor r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzbb
    public final void zzd(ParcelFileDescriptor r4) {
        zzeo r0 = this.zzb;
        new zzgp(r4, r0.zzb(), this.zza).zza(r0.zzp());
    }
}
