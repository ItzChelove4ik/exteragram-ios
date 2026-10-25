package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
final class zzdm implements ThreadFactory {
    private final String zza;
    private final AtomicInteger zzb;

    public zzdm(String r3) {
        this.zzb = new AtomicInteger(1);
        this.zza = r3.concat("-thread-");
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable r6) {
        int r0 = this.zzb.getAndIncrement();
        int r2 = String.valueOf(r0).length();
        String r5 = this.zza;
        StringBuilder r3 = new StringBuilder(r5.length() + r2);
        r3.append(r5);
        r3.append(r0);
        return new Thread(r6, r3.toString());
    }
}
