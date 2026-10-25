package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.android.gms.internal.mlkit_genai_prompt.zzoc;

/* loaded from: classes4.dex */
final class zzpg extends zzoc.zzf implements Runnable {
    private final Runnable zza;

    public zzpg(Runnable r1) {
        r1.getClass();
        this.zza = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.run();     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        zzi(th);
        throw th;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzoc
    public final String zzd() {
        String r2 = this.zza.toString();
        StringBuilder r1 = new StringBuilder(r2.length() + 7);
        r1.append("task=[");
        r1.append(r2);
        r1.append("]");
        return r1.toString();
    }
}
