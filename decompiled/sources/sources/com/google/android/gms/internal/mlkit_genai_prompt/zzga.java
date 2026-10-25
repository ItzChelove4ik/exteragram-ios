package com.google.android.gms.internal.mlkit_genai_prompt;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.Locale;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzga extends zzbd {
    final /* synthetic */ zzdy zza;
    final /* synthetic */ zzdq zzb;
    final /* synthetic */ CallbackToFutureAdapter.Completer zzc;
    final /* synthetic */ zzgl zzd;

    public zzga(zzgl r1, zzdy r2, zzdq r3, CallbackToFutureAdapter.Completer r4) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        Objects.requireNonNull(r1);
        this.zzd = r1;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzbe
    public final void zzb(String r3, long r4) {
        zzfz r32 = new zzfz(this.zza, this.zzb, r4);
        this.zzd.zzr().execute(r32);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzbe
    public final void zzc(String r3, long r4) {
        zzfw r32 = new zzfw(this.zza, this.zzb, r4);
        this.zzd.zzr().execute(r32);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzbe
    public final void zzd(String r8, int r9, String r10) {
        String r82 = String.format(Locale.ENGLISH, "Feature %s failed with failure status %d and error %s.", new Object[]{r8, Integer.valueOf(r9), r10});
        if (r9 != 1) goto L5;
        int r102 = 501;
    L6:
        zzdo r6 = zzdo.zza(r102, r82, null);
        zzdq r4 = this.zzb;
        zzfx r1 = new zzfx(this, this.zza, r4, r9, r6);
        this.zzd.zzr().execute(r1);
        this.zzc.setException(r6);
        return;
    L5:
        r102 = 0;
        goto L6
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzbe
    public final void zze(String r3) {
        zzfy r32 = new zzfy(this.zza, this.zzb);
        this.zzd.zzr().execute(r32);
        this.zzc.set(null);
    }
}
