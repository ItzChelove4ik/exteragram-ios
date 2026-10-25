package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
abstract class zzpj {
    public static void zza(Throwable r0) {
        if ((r0 instanceof InterruptedException) == false) goto L6;
        Thread.currentThread().interrupt();
        return;
    }

    public static void zzb(Throwable r1) {
        if ((r1 instanceof Error) == true) goto L5;
        return;
    L5:
        if ((r1 instanceof StackOverflowError) == false) goto L8;
        return;
    L8:
        throw ((Error) r1);
    }
}
