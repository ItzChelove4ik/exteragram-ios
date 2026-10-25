package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;

/* loaded from: classes4.dex */
final class zzpm extends zzol implements RunnableFuture {
    private volatile zzpa zza;

    public zzpm(Callable r2) {
        this.zza = new zzpl(this, r2);
    }

    public static zzpm zza(Runnable r1, Object r2) {
        return new zzpm(Executors.callable(r1, r2));
    }

    @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
    public final void run() {
        zzpa r0 = this.zza;
        if (r0 == null) goto L5;
        r0.run();
    L5:
        this.zza = null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzoc
    public final void zzc() {
        if (zzg() == false) goto L7;
        zzpa r0 = this.zza;
        if (r0 == null) goto L7;
        r0.zze();
    L7:
        this.zza = null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzoc
    public final String zzd() {
        zzpa r0 = this.zza;
        if (r0 == null) goto L7;
        String r2 = r0.toString();
        StringBuilder r1 = new StringBuilder(r2.length() + 7);
        r1.append("task=[");
        r1.append(r2);
        r1.append("]");
        return r1.toString();
    L7:
        return super.zzd();
    }
}
