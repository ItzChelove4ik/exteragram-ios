package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public final class zzdl implements Executor {
    public static final zzdl zza = null;
    private final Handler zzb;

    static {
        zza = new zzdl();
    }

    public zzdl() {
        this.zzb = new Handler(Looper.getMainLooper());
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable r1) {
        this.zzb.post(r1);
    }
}
