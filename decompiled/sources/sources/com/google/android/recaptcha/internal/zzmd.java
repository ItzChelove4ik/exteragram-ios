package com.google.android.recaptcha.internal;

import java.util.UUID;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
public final class zzmd {
    private final zzlj zza;
    private final zzht zzb;
    private final String zzc;
    private final String zzd;
    private final int zze;

    public zzmd(zzlj r1, zzht r2, String r3, int r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zze = r4;
        this.zzd = UUID.randomUUID().toString();
    }

    public final String zza() {
        return this.zzc;
    }

    public final String zzb() {
        return this.zzd;
    }

    public final Object zzc(Function2 r4, Continuation r5) {
        return r4.invoke(new zzmg(this.zza, this.zzb, this), r5);
    }

    public final int zzd() {
        return this.zze;
    }
}
