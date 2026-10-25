package com.google.android.gms.internal.play_billing;

/* loaded from: classes5.dex */
abstract class zzda {
    public static void zza(Throwable r1) {
        if ((r1 instanceof Error) == true) goto L5;
        return;
    L5:
        if ((r1 instanceof StackOverflowError) == false) goto L8;
        return;
    L8:
        throw ((Error) r1);
    }
}
