package com.google.android.recaptcha.internal;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* loaded from: classes5.dex */
final class zzhw implements ThreadFactory {
    private final String zza;
    private final Integer zzb;
    private final ThreadFactory zzc;

    public zzhw(String r1, Integer r2) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = Executors.defaultThreadFactory();
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable r5) {
        Thread r52 = this.zzc.newThread(r5);
        Integer r0 = this.zzb;
        if (r0 == null) goto L5;
        r52.setPriority(r0.intValue());
    L5:
        String r4 = this.zza;
        String r02 = r52.getName();
        String r1 = String.valueOf(r02);
        StringBuilder r3 = new StringBuilder((r4.length() + 1) + r1.length());
        r3.append(r4);
        r3.append("_");
        r3.append(r02);
        r52.setName(r3.toString());
        return r52;
    }
}
