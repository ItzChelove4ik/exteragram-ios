package com.google.android.recaptcha.internal;

import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;

/* loaded from: classes5.dex */
public interface zzmx {
    static /* synthetic */ Object zzc(zzmx r0, String r1, byte[] r2, Map r3, Continuation r4, int r5, Object r6) {
        return r0.zzb("https://www.gstatic.com/recaptcha/verify_key/orcas/prod/android/verify_key.txt", null, MapsKt.emptyMap(), r4);
    }

    Object zza(String r1, byte[] r2, Continuation r3);

    Object zzb(String r1, byte[] r2, Map r3, Continuation r4);
}
