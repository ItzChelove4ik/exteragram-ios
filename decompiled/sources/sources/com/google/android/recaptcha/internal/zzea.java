package com.google.android.recaptcha.internal;

import android.content.ContentResolver;
import android.os.Build;
import kotlin.coroutines.Continuation;

/* loaded from: classes5.dex */
public final class zzea implements zzev {
    private final ContentResolver zza;

    public zzea(ContentResolver r1) {
        this.zza = r1;
    }

    public static final /* synthetic */ ContentResolver zze(zzea r0) {
        return r0.zza;
    }

    @Override // com.google.android.recaptcha.internal.zzev
    public final int zza() {
        return 17;
    }

    @Override // com.google.android.recaptcha.internal.zzev
    public final boolean zzb() {
        return true;
    }

    @Override // com.google.android.recaptcha.internal.zzev
    public final Object zzc(String r1, zzaka r2, Continuation r3) {
        return new zzmc(new zzdz(this, null));
    }

    @Override // com.google.android.recaptcha.internal.zzev
    public final Object zzd(Exception r2, Continuation r3) {
        int r22 = Build.VERSION.SDK_INT;
        zzalw r32 = zzalx.zza();
        r32.zzb(16);
        if (r22 <= 34) goto L5;
        int r23 = 59;
    L6:
        r32.zzc(r23);
        return zzew.zzb(this, (zzalx) r32.zzp());
    L5:
        r23 = 58;
        goto L6
    }
}
