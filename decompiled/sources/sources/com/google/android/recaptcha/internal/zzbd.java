package com.google.android.recaptcha.internal;

import android.view.Window;

/* loaded from: classes5.dex */
public final class zzbd {
    private final Window zza;
    private final String zzb;
    private final zzav zzc;

    public zzbd(Window r1, String r2, zzav r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public static final /* synthetic */ zzav zzb(zzbd r0) {
        return r0.zzc;
    }

    public static final /* synthetic */ String zzc(zzbd r0) {
        return r0.zzb;
    }

    public final void zza() {
        Window r0 = this.zza;
        r0.setCallback(new zzbc(r0.getCallback(), this));
    }
}
